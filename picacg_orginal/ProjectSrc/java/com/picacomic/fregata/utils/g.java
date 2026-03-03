package com.picacomic.fregata.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.picacomic.fregata.MyApplication;
import com.picacomic.fregata.objects.ThumbnailObject;
import com.picacomic.fregata.objects.databaseTable.DownloadComicEpisodeObject;
import com.picacomic.fregata.services.DownloadService;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/g.class */
public class g {
    public static final String TAG = "g";

    public static void A(Context context, String str) {
        String str2 = str;
        if (!str.startsWith("http://")) {
            str2 = str;
            if (!str.startsWith("https://")) {
                str2 = "http://" + str;
            }
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    public static boolean A(String str) {
        return Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(str).matches();
    }

    public static String B(Context context, String str) {
        if (str == null) {
            return "";
        }
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            calendar.setTime(simpleDateFormat.parse(str));
            Calendar calendar2 = Calendar.getInstance();
            calendar2.add(13, -60);
            if (calendar2.compareTo(calendar) <= 0) {
                calendar2.add(13, 60);
                return ((calendar2.getTimeInMillis() - calendar.getTimeInMillis()) / 1000) + context.getString(2131689962);
            }
            Calendar calendar3 = Calendar.getInstance();
            calendar3.add(12, -60);
            if (calendar3.compareTo(calendar) <= 0) {
                calendar3.add(12, 60);
                return ((calendar3.getTimeInMillis() - calendar.getTimeInMillis()) / 60000) + context.getString(2131689961);
            }
            Calendar calendar4 = Calendar.getInstance();
            if (calendar4.get(1) == calendar.get(1) && calendar4.get(6) == calendar.get(6)) {
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm");
                return context.getString(2131689963) + " " + simpleDateFormat2.format(calendar.getTime());
            }
            Calendar calendar5 = Calendar.getInstance();
            calendar5.add(6, -1);
            if (calendar5.get(1) == calendar.get(1) && calendar5.get(6) == calendar.get(6)) {
                SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("HH:mm");
                return context.getString(2131689964) + " " + simpleDateFormat3.format(calendar.getTime());
            } else if (Calendar.getInstance().get(1) == calendar.get(1)) {
                SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("M月d日 HH:mm");
                return "" + simpleDateFormat4.format(calendar.getTime());
            } else {
                SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                return "" + simpleDateFormat5.format(calendar.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean C(Context context, String str) {
        String[] split = context.getString(2131689625).split("\\.");
        if (str != null) {
            String[] split2 = str.split("\\.");
            if (split.length != split2.length || split2.length < 6) {
                return true;
            }
            try {
                return Integer.parseInt(split2[5]) > Integer.parseInt(split[5]);
            } catch (Exception e) {
                return true;
            }
        }
        return true;
    }

    public static boolean D(Context context, String str) {
        String[] split = context.getString(2131689625).split("\\.");
        if (str != null) {
            String[] split2 = str.split("\\.");
            if (split.length != split2.length || split2.length < 6) {
                return true;
            }
            try {
                return Integer.parseInt(split2[4]) > Integer.parseInt(split[5]);
            } catch (Exception e) {
                return true;
            }
        }
        return true;
    }

    public static String E(Context context, String str) {
        return str != null ? str.equalsIgnoreCase(com.picacomic.fregata.c.a.uN[0]) ? context.getString(2131689800) : str.equalsIgnoreCase(com.picacomic.fregata.c.a.uN[1]) ? context.getString(2131689799) : str.equalsIgnoreCase(com.picacomic.fregata.c.a.uN[2]) ? context.getString(2131689798) : context.getString(2131689801) : context.getString(2131689801);
    }

    public static int F(Context context, String str) {
        if (str != null && str.endsWith("@picacomic.com")) {
            return str.startsWith("ruff") ? 2 : 1;
        }
        return 0;
    }

    public static File G(String str, String str2) {
        byte[] decode = Base64.decode(str, 0);
        File file = new File(str2);
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bufferedOutputStream.write(decode);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            file = null;
        }
        return file;
    }

    public static int Z(int i) {
        int i2 = (i * 2) - 1;
        return ((i2 * i2) - 1) * 25;
    }

    public static int a(LinearLayout linearLayout, View[] viewArr, Context context, View view) {
        int width;
        int i;
        if (linearLayout == null || viewArr == null || context == null) {
            return 0;
        }
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        linearLayout.removeAllViews();
        if (view != null) {
            view.measure(-2, -2);
            width = (defaultDisplay.getWidth() - view.getMeasuredWidth()) - 40;
        } else {
            width = defaultDisplay.getWidth() - 40;
        }
        linearLayout.setOrientation(1);
        LinearLayout linearLayout2 = new LinearLayout(context);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout2.setGravity(3);
        linearLayout2.setOrientation(0);
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (true) {
            int i5 = i4;
            if (i2 >= viewArr.length) {
                linearLayout.addView(linearLayout2);
                return i5;
            }
            LinearLayout linearLayout3 = new LinearLayout(context);
            linearLayout3.setOrientation(0);
            linearLayout3.setGravity(81);
            linearLayout3.setLayoutParams(new AbsListView.LayoutParams(-2, -2));
            viewArr[i2].measure(0, 0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(viewArr[i2].getMeasuredWidth(), -2);
            layoutParams.setMargins(5, 10, 5, 0);
            linearLayout3.addView(viewArr[i2], layoutParams);
            linearLayout3.measure(0, 0);
            int measuredWidth = i3 + viewArr[i2].getMeasuredWidth();
            if (measuredWidth >= width) {
                linearLayout.addView(linearLayout2);
                linearLayout2 = new LinearLayout(context);
                linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                linearLayout2.setOrientation(0);
                linearLayout2.setGravity(3);
                linearLayout2.addView(linearLayout3, new LinearLayout.LayoutParams(linearLayout3.getMeasuredWidth(), linearLayout3.getMeasuredHeight()));
                measuredWidth = linearLayout3.getMeasuredWidth();
                i = i5 + 1;
            } else {
                linearLayout2.addView(linearLayout3);
                i = i5;
            }
            i2++;
            i3 = measuredWidth;
            i4 = i;
        }
    }

    public static Bitmap a(Context context, Uri uri, int i, int i2) {
        if (uri != null) {
            try {
                InputStream openInputStream = context.getContentResolver().openInputStream(uri);
                if (i <= 0 || i2 <= 0) {
                    return BitmapFactory.decodeStream(openInputStream);
                }
                Bitmap createScaledBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(openInputStream), i, i2, true);
                String str = TAG;
                f.D(str, "Height = " + createScaledBitmap.getHeight() + " Width = " + createScaledBitmap.getWidth());
                return createScaledBitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Bitmap a(Bitmap bitmap, float f, int i) {
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, Math.round(bitmap.getWidth() * f), Math.round(bitmap.getHeight() * f), false);
        Bitmap copy = createScaledBitmap.copy(createScaledBitmap.getConfig(), true);
        if (i < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i2 = width * height;
        int[] iArr = new int[i2];
        Log.e("pix", width + " " + height + " " + iArr.length);
        copy.getPixels(iArr, 0, width, 0, 0, width, height);
        int i3 = width - 1;
        int i4 = height - 1;
        int i5 = i + i + 1;
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        int[] iArr4 = new int[i2];
        int[] iArr5 = new int[Math.max(width, height)];
        int i6 = (i5 + 1) >> 1;
        int i7 = i6 * i6;
        int i8 = i7 * 256;
        int[] iArr6 = new int[i8];
        for (int i9 = 0; i9 < i8; i9++) {
            iArr6[i9] = i9 / i7;
        }
        int[][] iArr7 = new int[i5][3];
        int i10 = i + 1;
        int i11 = 0;
        int i12 = 0;
        for (int i13 = 0; i13 < height; i13++) {
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            for (int i23 = -i; i23 <= i; i23++) {
                int i24 = iArr[i11 + Math.min(i3, Math.max(i23, 0))];
                int[] iArr8 = iArr7[i23 + i];
                iArr8[0] = (i24 & 16711680) >> 16;
                iArr8[1] = (i24 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr8[2] = i24 & 255;
                int abs = i10 - Math.abs(i23);
                i14 += iArr8[0] * abs;
                i15 += iArr8[1] * abs;
                i16 += iArr8[2] * abs;
                if (i23 > 0) {
                    i17 += iArr8[0];
                    i18 += iArr8[1];
                    i19 += iArr8[2];
                } else {
                    i20 += iArr8[0];
                    i21 += iArr8[1];
                    i22 += iArr8[2];
                }
            }
            int i25 = i18;
            int i26 = i17;
            int i27 = i;
            for (int i28 = 0; i28 < width; i28++) {
                iArr2[i11] = iArr6[i14];
                iArr3[i11] = iArr6[i15];
                iArr4[i11] = iArr6[i16];
                int[] iArr9 = iArr7[((i27 - i) + i5) % i5];
                int i29 = iArr9[0];
                int i30 = iArr9[1];
                int i31 = iArr9[2];
                if (i13 == 0) {
                    iArr5[i28] = Math.min(i28 + i + 1, i3);
                }
                int i32 = iArr[i12 + iArr5[i28]];
                iArr9[0] = (i32 & 16711680) >> 16;
                iArr9[1] = (i32 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr9[2] = i32 & 255;
                int i33 = i26 + iArr9[0];
                int i34 = i25 + iArr9[1];
                int i35 = i19 + iArr9[2];
                i14 = (i14 - i20) + i33;
                i15 = (i15 - i21) + i34;
                i16 = (i16 - i22) + i35;
                i27 = (i27 + 1) % i5;
                int[] iArr10 = iArr7[i27 % i5];
                i20 = (i20 - i29) + iArr10[0];
                i21 = (i21 - i30) + iArr10[1];
                i22 = (i22 - i31) + iArr10[2];
                i26 = i33 - iArr10[0];
                i25 = i34 - iArr10[1];
                i19 = i35 - iArr10[2];
                i11++;
            }
            i12 += width;
        }
        for (int i36 = 0; i36 < width; i36++) {
            int i37 = -i;
            int i38 = i37 * width;
            int i39 = 0;
            int i40 = 0;
            int i41 = 0;
            int i42 = 0;
            int i43 = 0;
            int i44 = 0;
            int i45 = 0;
            int i46 = 0;
            int i47 = 0;
            while (i37 <= i) {
                int max = Math.max(0, i38) + i36;
                int[] iArr11 = iArr7[i37 + i];
                iArr11[0] = iArr2[max];
                iArr11[1] = iArr3[max];
                iArr11[2] = iArr4[max];
                int abs2 = i10 - Math.abs(i37);
                int i48 = i39 + (iArr2[max] * abs2);
                i40 += iArr3[max] * abs2;
                i41 += iArr4[max] * abs2;
                if (i37 > 0) {
                    i42 += iArr11[0];
                    i43 += iArr11[1];
                    i44 += iArr11[2];
                } else {
                    i45 += iArr11[0];
                    i46 += iArr11[1];
                    i47 += iArr11[2];
                }
                int i49 = i38;
                if (i37 < i4) {
                    i49 = i38 + width;
                }
                i37++;
                i38 = i49;
                i39 = i48;
            }
            int i50 = i43;
            int i51 = i44;
            int i52 = i;
            int i53 = i42;
            int i54 = i41;
            int i55 = i40;
            int i56 = i39;
            int i57 = i36;
            int i58 = i51;
            for (int i59 = 0; i59 < height; i59++) {
                iArr[i57] = (iArr[i57] & ViewCompat.MEASURED_STATE_MASK) | (iArr6[i56] << 16) | (iArr6[i55] << 8) | iArr6[i54];
                int[] iArr12 = iArr7[((i52 - i) + i5) % i5];
                int i60 = iArr12[0];
                int i61 = iArr12[1];
                int i62 = iArr12[2];
                if (i36 == 0) {
                    iArr5[i59] = Math.min(i59 + i10, i4) * width;
                }
                int i63 = iArr5[i59] + i36;
                iArr12[0] = iArr2[i63];
                iArr12[1] = iArr3[i63];
                iArr12[2] = iArr4[i63];
                int i64 = i53 + iArr12[0];
                int i65 = i50 + iArr12[1];
                int i66 = i58 + iArr12[2];
                i56 = (i56 - i45) + i64;
                i55 = (i55 - i46) + i65;
                i54 = (i54 - i47) + i66;
                i52 = (i52 + 1) % i5;
                int[] iArr13 = iArr7[i52];
                i45 = (i45 - i60) + iArr13[0];
                i46 = (i46 - i61) + iArr13[1];
                i47 = (i47 - i62) + iArr13[2];
                i53 = i64 - iArr13[0];
                i50 = i65 - iArr13[1];
                i58 = i66 - iArr13[2];
                i57 += width;
            }
        }
        Log.e("pix", width + " " + height + " " + iArr.length);
        copy.setPixels(iArr, 0, width, 0, 0, width, height);
        return copy;
    }

    public static String a(Context context, Bitmap bitmap) {
        String str;
        if (context == null) {
            return null;
        }
        try {
            str = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "pica_" + System.currentTimeMillis(), "Pica Image");
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        f.D(TAG, "ImagePath = " + str);
        if (str == null) {
            Toast.makeText(context, 2131689604, 0).show();
        } else {
            Toast.makeText(context, 2131689605, 0).show();
        }
        return str;
    }

    public static void a(Context context, TextView textView, String str, int i, boolean z) {
        textView.setText(str + "(" + i + "P)");
        if (z) {
            SpannableString spannableString = new SpannableString(context.getResources().getString(2131689730));
            spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(2131099705)), 0, spannableString.length(), 33);
            spannableString.setSpan(new RelativeSizeSpan(0.8f), 0, spannableString.length(), 0);
            textView.append(spannableString);
        }
    }

    public static boolean a(String str, File file, boolean z) throws Exception {
        Response execute = new OkHttpClient().newCall(new Request.Builder().url(str).build()).execute();
        if (execute.isSuccessful()) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(execute.body().bytes());
            fileOutputStream.close();
            return false;
        }
        throw new IOException("Failed to download file: " + execute);
    }

    public static String aB(String str) {
        if (str == null || str.equalsIgnoreCase("")) {
            return null;
        }
        File file = new File(str);
        byte[] bArr = new byte[(int) file.length()];
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(bArr, 0, bArr.length);
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return Base64.encodeToString(bArr, 0);
    }

    public static String aC(String str) {
        int parseInt = Integer.parseInt(str.substring(str.indexOf("storage") + 7, str.indexOf(".picacomic")));
        return "http://www.picacomic.com/download/apk/" + (parseInt + 100) + "/";
    }

    public static int ac(int i) {
        return i - ((i + 1) / 21);
    }

    public static int ad(int i) {
        return i + (i / 20);
    }

    public static String ae(int i) {
        return new String(Character.toChars(i));
    }

    public static String af(int i) {
        if (i == 1) {
            return a.dS() + "?color=black";
        }
        return a.dS();
    }

    public static String ag(int i) {
        if (i == 1) {
            return a.getDetail() + "?color=black";
        }
        return a.getDetail();
    }

    public static boolean ar(Context context) {
        StackTraceElement[] stackTrace;
        try {
            throw new Exception("blah");
        } catch (Exception e) {
            for (StackTraceElement stackTraceElement : e.getStackTrace()) {
                if (stackTraceElement.getClassName().contains("xposed") || stackTraceElement.getMethodName().contains("xposed") || stackTraceElement.getClassName().contains("Xposed") || stackTraceElement.getMethodName().contains("Xposed")) {
                    return false;
                }
            }
            if (MyApplication.bx().bz()) {
                return true;
            }
            ((Activity) context).finish();
            return false;
        }
    }

    public static int as(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int at(Context context) {
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static String au(Context context) {
        return com.picacomic.fregata.c.d.tu;
    }

    public static void av(final Context context) {
        final List find = DownloadComicEpisodeObject.find(DownloadComicEpisodeObject.class, "status != ? and status != ?", "0", "4");
        if (find == null || find.size() <= 0) {
            return;
        }
        AlertDialogCenter.continueDownloadComic(context, new View.OnClickListener() { // from class: com.picacomic.fregata.utils.g.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (int i = 0; i < find.size(); i++) {
                    f.E(g.TAG, "Start Download Service");
                    Intent intent = new Intent(context, DownloadService.class);
                    intent.putExtra("COMIC_ID", ((DownloadComicEpisodeObject) find.get(i)).getComicId());
                    intent.putExtra("EPISODE_ID", ((DownloadComicEpisodeObject) find.get(i)).getEpisodeId());
                    context.startService(intent);
                }
            }
        });
    }

    public static void aw(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            if (e.al(context) == 0) {
                context.setTheme(2131755014);
            } else {
                context.setTheme(2131755019);
            }
        }
    }

    public static int ax(Context context) {
        return F(context, e.u(context));
    }

    public static Bitmap b(Context context, Uri uri) {
        return a(context, uri, -1, -1);
    }

    public static String b(ThumbnailObject thumbnailObject) {
        if (thumbnailObject != null) {
            if (thumbnailObject.getFileServer().equalsIgnoreCase("http://lorempixel.com")) {
                return thumbnailObject.getFileServer() + thumbnailObject.getPath();
            } else if (MyApplication.bx() != null && e.S(MyApplication.bx()) != null) {
                return e.S(MyApplication.bx()) + thumbnailObject.getPath();
            } else {
                return thumbnailObject.getFileServer() + "/static/" + thumbnailObject.getPath();
            }
        }
        return null;
    }

    public static Bitmap c(Context context, Uri uri, int i) {
        return a(context, uri, i, i);
    }

    public static String f(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            String str = TAG;
            f.D(str, "BASE64 = " + encodeToString);
            return "data:image/jpeg;base64," + encodeToString;
        }
        return "";
    }

    public static void g(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                g(file2);
            }
        }
        file.delete();
    }

    public static void k(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setSupportMultipleWindows(true);
        settings.setCacheMode(2);
        webView.setWebViewClient(new WebViewClient() { // from class: com.picacomic.fregata.utils.g.1
            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView2, int i, String str, String str2) {
                super.onReceivedError(webView2, i, str, str2);
                webView2.loadUrl("file:///android_res/raw/page_not_found.html");
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, WebResourceRequest webResourceRequest) {
                if (Build.VERSION.SDK_INT >= 21) {
                    String str = g.TAG;
                    f.F(str, "Version URL = " + webResourceRequest.getUrl().toString());
                    if (webResourceRequest.getUrl().toString().contains(e.an(MyApplication.by()))) {
                        return false;
                    }
                    g.A(MyApplication.by(), webResourceRequest.getUrl().toString());
                    return true;
                }
                return false;
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView2, String str) {
                String str2 = g.TAG;
                f.F(str2, "URL = " + str);
                if (str.contains(e.an(MyApplication.by()))) {
                    return false;
                }
                g.A(MyApplication.by(), str);
                return true;
            }
        });
    }
}
