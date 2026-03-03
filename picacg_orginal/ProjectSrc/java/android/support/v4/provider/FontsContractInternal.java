package android.support.v4.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.fonts.FontResult;
import android.support.v4.os.ResultReceiver;
import android.support.v4.provider.FontsContractCompat;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v4/provider/FontsContractInternal.class */
public class FontsContractInternal {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final String PARCEL_FONT_RESULTS = "font_results";
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int RESULT_CODE_PROVIDER_NOT_FOUND = -1;
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static final int RESULT_CODE_WRONG_CERTIFICATES = -2;
    private static final String TAG = "FontsContractCompat";
    private static final int THREAD_RENEWAL_THRESHOLD_MS = 10000;
    private static final Comparator<byte[]> sByteArrayComparator = new Comparator<byte[]>() { // from class: android.support.v4.provider.FontsContractInternal.3
        @Override // java.util.Comparator
        public int compare(byte[] bArr, byte[] bArr2) {
            if (bArr.length != bArr2.length) {
                return bArr.length - bArr2.length;
            }
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return bArr[i] - bArr2[i];
                }
            }
            return 0;
        }
    };
    private final Context mContext;
    @GuardedBy("mLock")
    private Handler mHandler;
    private final PackageManager mPackageManager;
    @GuardedBy("mLock")
    private HandlerThread mThread;
    private final Object mLock = new Object();
    private final Runnable mReplaceDispatcherThreadRunnable = new Runnable() { // from class: android.support.v4.provider.FontsContractInternal.1
        @Override // java.lang.Runnable
        public void run() {
            synchronized (FontsContractInternal.this.mLock) {
                if (FontsContractInternal.this.mThread != null) {
                    FontsContractInternal.this.mThread.quit();
                    FontsContractInternal.this.mThread = null;
                    FontsContractInternal.this.mHandler = null;
                }
            }
        }
    };

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public FontsContractInternal(Context context) {
        this.mContext = context.getApplicationContext();
        this.mPackageManager = this.mContext.getPackageManager();
    }

    @VisibleForTesting
    FontsContractInternal(Context context, PackageManager packageManager) {
        this.mContext = context;
        this.mPackageManager = packageManager;
    }

    private List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature signature : signatureArr) {
            arrayList.add(signature.toByteArray());
        }
        return arrayList;
    }

    private boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private List<List<byte[]>> getCertificates(FontRequest fontRequest) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(this.mContext.getResources(), fontRequest.getCertificatesArrayResId());
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void getFont(final FontRequest fontRequest, final ResultReceiver resultReceiver) {
        synchronized (this.mLock) {
            if (this.mHandler == null) {
                this.mThread = new HandlerThread("fonts", 10);
                this.mThread.start();
                this.mHandler = new Handler(this.mThread.getLooper());
            }
            this.mHandler.post(new Runnable() { // from class: android.support.v4.provider.FontsContractInternal.2
                @Override // java.lang.Runnable
                public void run() {
                    ProviderInfo provider = FontsContractInternal.this.getProvider(fontRequest, resultReceiver);
                    if (provider == null) {
                        return;
                    }
                    FontsContractInternal.this.getFontFromProvider(fontRequest, resultReceiver, provider.authority);
                }
            });
            this.mHandler.removeCallbacks(this.mReplaceDispatcherThreadRunnable);
            this.mHandler.postDelayed(this.mReplaceDispatcherThreadRunnable, 10000L);
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:84:0x028a -> B:27:0x0135). Please submit an issue!!! */
    @VisibleForTesting
    void getFontFromProvider(FontRequest fontRequest, ResultReceiver resultReceiver, String str) {
        Cursor cursor;
        int i;
        boolean z;
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        Uri build = new Uri.Builder().scheme("content").authority(str).build();
        Uri build2 = new Uri.Builder().scheme("content").authority(str).appendPath("file").build();
        try {
            Cursor query = this.mContext.getContentResolver().query(build, new String[]{"_id", FontsContractCompat.Columns.FILE_ID, FontsContractCompat.Columns.TTC_INDEX, FontsContractCompat.Columns.VARIATION_SETTINGS, FontsContractCompat.Columns.WEIGHT, FontsContractCompat.Columns.ITALIC, FontsContractCompat.Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        int columnIndex = query.getColumnIndex(FontsContractCompat.Columns.RESULT_CODE);
                        int columnIndex2 = query.getColumnIndex("_id");
                        int columnIndex3 = query.getColumnIndex(FontsContractCompat.Columns.FILE_ID);
                        int columnIndex4 = query.getColumnIndex(FontsContractCompat.Columns.TTC_INDEX);
                        int columnIndex5 = query.getColumnIndex(FontsContractCompat.Columns.VARIATION_SETTINGS);
                        int columnIndex6 = query.getColumnIndex(FontsContractCompat.Columns.WEIGHT);
                        int columnIndex7 = query.getColumnIndex(FontsContractCompat.Columns.ITALIC);
                        while (query.moveToNext()) {
                            int i2 = columnIndex != -1 ? query.getInt(columnIndex) : 0;
                            if (i2 != 0) {
                                int i3 = i2 < 0 ? 1 : i2;
                                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                                    try {
                                        ((FontResult) arrayList.get(i4)).getFileDescriptor().close();
                                    } catch (IOException e) {
                                    }
                                }
                                resultReceiver.send(i3, null);
                                if (query != null) {
                                    query.close();
                                    return;
                                }
                                return;
                            }
                            try {
                                ParcelFileDescriptor openFileDescriptor = this.mContext.getContentResolver().openFileDescriptor(columnIndex3 == -1 ? ContentUris.withAppendedId(build, query.getLong(columnIndex2)) : ContentUris.withAppendedId(build2, query.getLong(columnIndex3)), "r");
                                if (openFileDescriptor != null) {
                                    int i5 = columnIndex4 != -1 ? query.getInt(columnIndex4) : 0;
                                    String string = columnIndex5 != -1 ? query.getString(columnIndex5) : null;
                                    if (columnIndex6 == -1 || columnIndex7 == -1) {
                                        i = 400;
                                        z = false;
                                    } else {
                                        try {
                                            i = query.getInt(columnIndex6);
                                            z = query.getInt(columnIndex7) == 1;
                                        } catch (FileNotFoundException e2) {
                                            e = e2;
                                            Log.e(TAG, "FileNotFoundException raised when interacting with content provider " + str, e);
                                        }
                                    }
                                    arrayList.add(new FontResult(openFileDescriptor, i5, string, i, z));
                                }
                            } catch (FileNotFoundException e3) {
                                e = e3;
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            if (arrayList.isEmpty()) {
                resultReceiver.send(1, null);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("font_results", arrayList);
            resultReceiver.send(0, bundle);
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    @VisibleForTesting
    ProviderInfo getProvider(FontRequest fontRequest, ResultReceiver resultReceiver) {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = this.mPackageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            Log.e(TAG, "Can't find content provider " + providerAuthority);
            resultReceiver.send(-1, null);
            return null;
        } else if (!resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            Log.e(TAG, "Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
            resultReceiver.send(-1, null);
            return null;
        } else {
            try {
                List<byte[]> convertToByteArrayList = convertToByteArrayList(this.mPackageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
                Collections.sort(convertToByteArrayList, sByteArrayComparator);
                List<List<byte[]>> certificates = getCertificates(fontRequest);
                for (int i = 0; i < certificates.size(); i++) {
                    ArrayList arrayList = new ArrayList(certificates.get(i));
                    Collections.sort(arrayList, sByteArrayComparator);
                    if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                        return resolveContentProvider;
                    }
                }
                Log.e(TAG, "Certificates don't match for given provider " + providerAuthority);
                resultReceiver.send(-2, null);
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Can't find content provider " + providerAuthority, e);
                resultReceiver.send(-1, null);
                return null;
            }
        }
    }
}
