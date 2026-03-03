package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.GuardedBy;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.support.v4.graphics.TypefaceCompat;
import android.support.v4.graphics.fonts.FontResult;
import android.support.v4.os.ResultReceiver;
import android.support.v4.provider.FontRequest;
import android.support.v4.provider.FontsContractCompat;
import android.support.v4.provider.FontsContractInternal;
import android.support.v4.util.LruCache;
import android.support.v7.widget.ActivityChooserView;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(14)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v4/graphics/TypefaceCompatBaseImpl.class */
public class TypefaceCompatBaseImpl implements TypefaceCompat.TypefaceCompatImpl {
    private static final String CACHE_FILE_PREFIX = "cached_font_";
    private static final int SYNC_FETCH_TIMEOUT_MS = 500;
    private static final String TAG = "TypefaceCompatBaseImpl";
    private static final boolean VERBOSE_TRACING = false;
    @GuardedBy("sLock")
    private static FontsContractInternal sFontsContract;
    private final Context mApplicationContext;
    private static final LruCache<String, TypefaceCompat.TypefaceHolder> sDynamicTypefaceCache = new LruCache<>(16);
    private static final Object sLock = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:android/support/v4/graphics/TypefaceCompatBaseImpl$WaitableCallback.class */
    public static final class WaitableCallback extends TypefaceCompat.FontRequestCallback {
        private static final int FINISHED = 2;
        private static final int NOT_STARTED = 0;
        private static final int WAITING = 1;
        private final String mFontTitle;
        @GuardedBy("mLock")
        private Typeface mTypeface;
        private final ReentrantLock mLock = new ReentrantLock();
        private final Condition mCond = this.mLock.newCondition();
        @GuardedBy("mLock")
        private int mState = 0;

        WaitableCallback(String str) {
            this.mFontTitle = str;
        }

        @Override // android.support.v4.graphics.TypefaceCompat.FontRequestCallback
        public void onTypefaceRequestFailed(int i) {
            Log.w(TypefaceCompatBaseImpl.TAG, "Remote font fetch failed(" + i + "): " + this.mFontTitle);
            this.mLock.lock();
            try {
                if (this.mState == 1) {
                    this.mTypeface = null;
                    this.mState = 2;
                }
                this.mCond.signal();
            } finally {
                this.mLock.unlock();
            }
        }

        @Override // android.support.v4.graphics.TypefaceCompat.FontRequestCallback
        public void onTypefaceRetrieved(Typeface typeface) {
            this.mLock.lock();
            try {
                if (this.mState == 1) {
                    this.mTypeface = typeface;
                    this.mState = 2;
                }
                this.mCond.signal();
            } finally {
                this.mLock.unlock();
            }
        }

        public Typeface waitWithTimeout(long j) {
            long j2;
            Typeface typeface;
            this.mLock.lock();
            try {
                if (this.mState != 2) {
                    if (this.mState == 0) {
                        this.mState = 1;
                        long nanos = TimeUnit.MILLISECONDS.toNanos(j);
                        while (true) {
                            if (this.mState != 1) {
                                break;
                            }
                            try {
                                j2 = this.mCond.awaitNanos(nanos);
                            } catch (InterruptedException e) {
                                j2 = nanos;
                            }
                            if (this.mState != 2) {
                                nanos = j2;
                                if (j2 < 0) {
                                    Log.w(TypefaceCompatBaseImpl.TAG, "Remote font fetch timed out: " + this.mFontTitle);
                                    this.mState = 2;
                                    break;
                                }
                            } else {
                                long millis = TimeUnit.NANOSECONDS.toMillis(j2);
                                Log.w(TypefaceCompatBaseImpl.TAG, "Remote font fetched in " + (j - millis) + "ms :" + this.mFontTitle);
                                typeface = this.mTypeface;
                                break;
                            }
                        }
                    }
                    this.mLock.unlock();
                    return null;
                }
                typeface = this.mTypeface;
                this.mLock.unlock();
                return typeface;
            } catch (Throwable th) {
                this.mLock.unlock();
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TypefaceCompatBaseImpl(Context context) {
        this.mApplicationContext = context.getApplicationContext();
    }

    static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing stream", e);
            }
        }
    }

    static void closeQuietly(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Error closing input stream", e);
            }
        }
    }

    private File copyToCacheFile(InputStream inputStream) {
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            File file = new File(this.mApplicationContext.getCacheDir(), CACHE_FILE_PREFIX + Thread.currentThread().getId());
            fileOutputStream = new FileOutputStream(file, false);
            fileOutputStream2 = fileOutputStream;
            try {
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            closeQuietly(inputStream);
                            closeQuietly(fileOutputStream);
                            return file;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                } catch (IOException e) {
                    e = e;
                    fileOutputStream2 = fileOutputStream;
                    Log.e(TAG, "Error copying font file descriptor to temp local file.", e);
                    closeQuietly(inputStream);
                    closeQuietly(fileOutputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                closeQuietly(inputStream);
                closeQuietly(fileOutputStream2);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(inputStream);
            closeQuietly(fileOutputStream2);
            throw th;
        }
    }

    /* JADX WARN: Not initialized variable reg: 10, insn: 0x00b8: MOVE  (r0 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r10 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:30:0x00b8 */
    private File copyToCacheFile(ByteBuffer byteBuffer) {
        Closeable closeable;
        FileOutputStream fileOutputStream;
        Closeable closeable2;
        try {
            try {
                File file = new File(this.mApplicationContext.getCacheDir(), CACHE_FILE_PREFIX + Thread.currentThread().getId());
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                try {
                    byte[] bArr = new byte[1024];
                    while (byteBuffer.hasRemaining()) {
                        int min = Math.min(1024, byteBuffer.remaining());
                        byteBuffer.get(bArr, 0, min);
                        fileOutputStream2.write(bArr, 0, min);
                    }
                    closeQuietly(fileOutputStream2);
                    return file;
                } catch (IOException e) {
                    fileOutputStream = fileOutputStream2;
                    e = e;
                    Log.e(TAG, "Error copying font file descriptor to temp local file.", e);
                    closeQuietly(fileOutputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                closeable = closeable2;
                closeQuietly(closeable);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            closeable = null;
            closeQuietly(closeable);
            throw th;
        }
    }

    private static String createAssetUid(Resources resources, int i, int i2) {
        return resources.getResourcePackageName(i) + "-" + i + "-" + i2;
    }

    @Nullable
    private TypefaceCompat.TypefaceHolder createFromResources(FontResourcesParserCompat.ProviderResourceEntry providerResourceEntry) {
        TypefaceCompat.TypefaceHolder findFromCache = findFromCache(providerResourceEntry.getAuthority(), providerResourceEntry.getQuery());
        if (findFromCache != null) {
            return findFromCache;
        }
        FontRequest fontRequest = new FontRequest(providerResourceEntry.getAuthority(), providerResourceEntry.getPackage(), providerResourceEntry.getQuery(), providerResourceEntry.getCerts());
        WaitableCallback waitableCallback = new WaitableCallback(providerResourceEntry.getAuthority() + "/" + providerResourceEntry.getQuery());
        create(fontRequest, waitableCallback);
        return new TypefaceCompat.TypefaceHolder(waitableCallback.waitWithTimeout(500L), 400, false);
    }

    private static String createProviderUid(String str, String str2) {
        return "provider:" + str + "-" + str2;
    }

    private FontResourcesParserCompat.FontFileResourceEntry findBestEntry(FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, int i, boolean z) {
        int i2;
        FontResourcesParserCompat.FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
        int length = entries.length;
        FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = null;
        int i3 = 0;
        int i4 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        while (true) {
            int i5 = i4;
            if (i3 >= length) {
                return fontFileResourceEntry;
            }
            FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry2 = entries[i3];
            int abs = (Math.abs(fontFileResourceEntry2.getWeight() - i) * 2) + (z == fontFileResourceEntry2.isItalic() ? 0 : 1);
            if (fontFileResourceEntry != null) {
                i2 = i5;
                if (i5 <= abs) {
                    i3++;
                    i4 = i2;
                }
            }
            fontFileResourceEntry = fontFileResourceEntry2;
            i2 = abs;
            i3++;
            i4 = i2;
        }
    }

    private static TypefaceCompat.TypefaceHolder findFromCache(String str, String str2) {
        synchronized (sDynamicTypefaceCache) {
            TypefaceCompat.TypefaceHolder typefaceHolder = sDynamicTypefaceCache.get(createProviderUid(str, str2));
            if (typefaceHolder != null) {
                return typefaceHolder;
            }
            return null;
        }
    }

    static void putInCache(String str, String str2, TypefaceCompat.TypefaceHolder typefaceHolder) {
        String createProviderUid = createProviderUid(str, str2);
        synchronized (sDynamicTypefaceCache) {
            sDynamicTypefaceCache.put(createProviderUid, typefaceHolder);
        }
    }

    public void create(@NonNull final FontRequest fontRequest, @NonNull final TypefaceCompat.FontRequestCallback fontRequestCallback) {
        TypefaceCompat.TypefaceHolder findFromCache = findFromCache(fontRequest.getProviderAuthority(), fontRequest.getQuery());
        if (findFromCache != null) {
            fontRequestCallback.onTypefaceRetrieved(findFromCache.getTypeface());
        }
        synchronized (sLock) {
            if (sFontsContract == null) {
                sFontsContract = new FontsContractInternal(this.mApplicationContext);
            }
            sFontsContract.getFont(fontRequest, new ResultReceiver(null) { // from class: android.support.v4.graphics.TypefaceCompatBaseImpl.1
                @Override // android.support.v4.os.ResultReceiver
                public void onReceiveResult(int i, Bundle bundle) {
                    TypefaceCompatBaseImpl.this.receiveResult(fontRequest, fontRequestCallback, i, bundle);
                }
            });
        }
    }

    @Nullable
    TypefaceCompat.TypefaceHolder createFromResources(FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i, int i2) {
        InputStream inputStream;
        FontResourcesParserCompat.FontFileResourceEntry findBestEntry = findBestEntry(fontFamilyFilesResourceEntry, (i2 & 1) == 0 ? 400 : 700, (i2 & 2) != 0);
        if (findBestEntry == null) {
            return null;
        }
        try {
            inputStream = resources.openRawResource(findBestEntry.getResourceId());
        } catch (IOException e) {
            inputStream = null;
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            Typeface createTypeface = createTypeface(resources, inputStream);
            if (createTypeface == null) {
                closeQuietly(inputStream);
                return null;
            }
            Typeface create = Typeface.create(createTypeface, i2);
            if (create == null) {
                closeQuietly(inputStream);
                return null;
            }
            TypefaceCompat.TypefaceHolder typefaceHolder = new TypefaceCompat.TypefaceHolder(create, findBestEntry.getWeight(), findBestEntry.isItalic());
            closeQuietly(inputStream);
            return typefaceHolder;
        } catch (IOException e2) {
            closeQuietly(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(inputStream);
            throw th;
        }
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public TypefaceCompat.TypefaceHolder createFromResourcesFamilyXml(FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry, Resources resources, int i, int i2) {
        if (familyResourceEntry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
            return createFromResources((FontResourcesParserCompat.ProviderResourceEntry) familyResourceEntry);
        }
        TypefaceCompat.TypefaceHolder createFromResources = createFromResources((FontResourcesParserCompat.FontFamilyFilesResourceEntry) familyResourceEntry, resources, i, i2);
        if (createFromResources != null) {
            sDynamicTypefaceCache.put(createAssetUid(resources, i, i2), createFromResources);
        }
        return createFromResources;
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public TypefaceCompat.TypefaceHolder createFromResourcesFontFile(Resources resources, int i, int i2) {
        InputStream inputStream;
        try {
            inputStream = resources.openRawResource(i);
        } catch (IOException e) {
            inputStream = null;
        } catch (Throwable th) {
            th = th;
            inputStream = null;
        }
        try {
            Typeface createTypeface = createTypeface(resources, inputStream);
            if (createTypeface == null) {
                closeQuietly(inputStream);
                return null;
            }
            Typeface create = Typeface.create(createTypeface, i2);
            if (create == null) {
                closeQuietly(inputStream);
                return null;
            }
            TypefaceCompat.TypefaceHolder typefaceHolder = new TypefaceCompat.TypefaceHolder(create, 400, false);
            sDynamicTypefaceCache.put(createAssetUid(resources, i, i2), typefaceHolder);
            closeQuietly(inputStream);
            return typefaceHolder;
        } catch (IOException e2) {
            closeQuietly(inputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(inputStream);
            throw th;
        }
    }

    Typeface createTypeface(Resources resources, InputStream inputStream) throws IOException {
        File copyToCacheFile = copyToCacheFile(inputStream);
        if (copyToCacheFile != null) {
            try {
                try {
                    return Typeface.createFromFile(copyToCacheFile.getPath());
                } catch (RuntimeException e) {
                    Log.e(TAG, "Failed to create font", e);
                    copyToCacheFile.delete();
                    return null;
                }
            } finally {
                copyToCacheFile.delete();
            }
        }
        return null;
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public TypefaceCompat.TypefaceHolder createTypeface(List<FontResult> list) {
        Typeface createFromFile;
        FontResult fontResult = list.get(0);
        File copyToCacheFile = copyToCacheFile(new FileInputStream(fontResult.getFileDescriptor().getFileDescriptor()));
        if (copyToCacheFile != null) {
            try {
                createFromFile = Typeface.createFromFile(copyToCacheFile.getPath());
                copyToCacheFile.delete();
            } catch (RuntimeException e) {
                copyToCacheFile.delete();
                return null;
            } catch (Throwable th) {
                copyToCacheFile.delete();
                throw th;
            }
        } else {
            createFromFile = null;
        }
        if (createFromFile == null) {
            return null;
        }
        return new TypefaceCompat.TypefaceHolder(createFromFile, fontResult.getWeight(), fontResult.getItalic());
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public TypefaceCompat.TypefaceHolder createTypeface(@NonNull FontsContractCompat.FontInfo[] fontInfoArr, Map<Uri, ByteBuffer> map) {
        Typeface createFromFile;
        if (fontInfoArr.length < 1) {
            return null;
        }
        FontsContractCompat.FontInfo fontInfo = fontInfoArr[0];
        File copyToCacheFile = copyToCacheFile(map.get(fontInfo.getUri()));
        if (copyToCacheFile != null) {
            try {
                createFromFile = Typeface.createFromFile(copyToCacheFile.getPath());
                copyToCacheFile.delete();
            } catch (RuntimeException e) {
                copyToCacheFile.delete();
                return null;
            } catch (Throwable th) {
                copyToCacheFile.delete();
                throw th;
            }
        } else {
            createFromFile = null;
        }
        if (createFromFile == null) {
            return null;
        }
        return new TypefaceCompat.TypefaceHolder(createFromFile, fontInfo.getWeight(), fontInfo.isItalic());
    }

    @Override // android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public TypefaceCompat.TypefaceHolder findFromCache(Resources resources, int i, int i2) {
        TypefaceCompat.TypefaceHolder typefaceHolder;
        String createAssetUid = createAssetUid(resources, i, i2);
        synchronized (sDynamicTypefaceCache) {
            typefaceHolder = sDynamicTypefaceCache.get(createAssetUid);
        }
        return typefaceHolder;
    }

    @VisibleForTesting
    void receiveResult(FontRequest fontRequest, TypefaceCompat.FontRequestCallback fontRequestCallback, int i, Bundle bundle) {
        TypefaceCompat.TypefaceHolder findFromCache = findFromCache(fontRequest.getProviderAuthority(), fontRequest.getQuery());
        if (findFromCache != null) {
            fontRequestCallback.onTypefaceRetrieved(findFromCache.getTypeface());
        } else if (i != 0) {
            fontRequestCallback.onTypefaceRequestFailed(i);
        } else if (bundle == null) {
            fontRequestCallback.onTypefaceRequestFailed(1);
        } else {
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("font_results");
            if (parcelableArrayList == null || parcelableArrayList.isEmpty()) {
                fontRequestCallback.onTypefaceRequestFailed(1);
                return;
            }
            TypefaceCompat.TypefaceHolder createTypeface = createTypeface(parcelableArrayList);
            if (createTypeface != null) {
                putInCache(fontRequest.getProviderAuthority(), fontRequest.getQuery(), createTypeface);
                fontRequestCallback.onTypefaceRetrieved(createTypeface.getTypeface());
                return;
            }
            Log.e(TAG, "Error creating font " + fontRequest.getQuery());
            fontRequestCallback.onTypefaceRequestFailed(-3);
        }
    }
}
