package com.just.agentweb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StatFs;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.os.EnvironmentCompat;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;
import com.just.agentweb.al;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: picacg_1.jar:com/just/agentweb/h.class */
public class h {
    private static final String TAG = "h";
    private static WeakReference<Snackbar> dE;
    private static Toast dF;
    private static Handler mHandler;

    private h() {
        throw new UnsupportedOperationException("u can't init me");
    }

    public static long N() {
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().toString());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            }
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (RuntimeException e) {
            return 0L;
        }
    }

    public static boolean O() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    static Uri a(Context context, File file) {
        return Build.VERSION.SDK_INT >= 24 ? b(context, file) : Uri.fromFile(file);
    }

    public static File a(Context context, String str, boolean z) throws IOException {
        String d = d(context);
        if (TextUtils.isEmpty(d)) {
            return null;
        }
        File file = new File(d, str);
        if (!file.exists()) {
            file.createNewFile();
        } else if (z) {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    private static String a(File file) {
        String name = file.getName();
        String lowerCase = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
        return lowerCase.equals("pdf") ? "application/pdf" : (lowerCase.equals("m4a") || lowerCase.equals("mp3") || lowerCase.equals("mid") || lowerCase.equals("xmf") || lowerCase.equals("ogg") || lowerCase.equals("wav")) ? "audio/*" : (lowerCase.equals("3gp") || lowerCase.equals("mp4")) ? "video/*" : (lowerCase.equals("jpg") || lowerCase.equals("gif") || lowerCase.equals("png") || lowerCase.equals("jpeg") || lowerCase.equals("bmp")) ? "image/*" : lowerCase.equals("apk") ? "application/vnd.android.package-archive" : (lowerCase.equals("pptx") || lowerCase.equals("ppt")) ? "application/vnd.ms-powerpoint" : (lowerCase.equals("docx") || lowerCase.equals("doc")) ? "application/vnd.ms-word" : (lowerCase.equals("xlsx") || lowerCase.equals("xls")) ? "application/vnd.ms-excel" : "*/*";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Method a(Object obj, String str, Class... clsArr) {
        if (obj == null) {
            return null;
        }
        try {
            Method declaredMethod = obj.getClass().getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable th) {
            if (ag.ap()) {
                th.printStackTrace();
                return null;
            }
            return null;
        }
    }

    public static List<String> a(Activity activity, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            if (!a((Context) activity, strArr[i])) {
                arrayList.add(strArr[i]);
            }
        }
        return arrayList;
    }

    static void a(Context context, Intent intent, String str, File file, boolean z) {
        if (Build.VERSION.SDK_INT < 24) {
            intent.setDataAndType(Uri.fromFile(file), str);
            return;
        }
        intent.setDataAndType(a(context, file), str);
        intent.addFlags(1);
        if (z) {
            intent.addFlags(2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(Context context, String str) {
        if (dF == null) {
            dF = Toast.makeText(context.getApplicationContext(), str, 0);
        } else {
            dF.setText(str);
        }
        dF.show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(View view, CharSequence charSequence, int i, @ColorInt int i2, @ColorInt int i3, CharSequence charSequence2, @ColorInt int i4, View.OnClickListener onClickListener) {
        SpannableString spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new ForegroundColorSpan(i2), 0, spannableString.length(), 33);
        dE = new WeakReference<>(Snackbar.make(view, spannableString, i));
        Snackbar snackbar = dE.get();
        snackbar.getView().setBackgroundColor(i3);
        if (charSequence2 != null && charSequence2.length() > 0 && onClickListener != null) {
            snackbar.setActionTextColor(i4);
            snackbar.setAction(charSequence2, onClickListener);
        }
        snackbar.show();
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Activity activity, WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams, aj ajVar, ValueCallback valueCallback2, String str, Handler.Callback callback) {
        try {
            Object invoke = Class.forName("com.just.agentweb.filechooser.a").getDeclaredMethod("newBuilder", Activity.class, WebView.class).invoke(null, activity, webView);
            Class<?> cls = invoke.getClass();
            if (valueCallback != null) {
                Method declaredMethod = cls.getDeclaredMethod("setUriValueCallbacks", ValueCallback.class);
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(invoke, valueCallback);
            }
            if (fileChooserParams != null) {
                Method declaredMethod2 = cls.getDeclaredMethod("setFileChooserParams", WebChromeClient.FileChooserParams.class);
                declaredMethod2.setAccessible(true);
                declaredMethod2.invoke(invoke, fileChooserParams);
            }
            if (valueCallback2 != null) {
                Method declaredMethod3 = cls.getDeclaredMethod("setUriValueCallback", ValueCallback.class);
                declaredMethod3.setAccessible(true);
                declaredMethod3.invoke(invoke, valueCallback2);
            }
            if (!TextUtils.isEmpty(str)) {
                Method declaredMethod4 = cls.getDeclaredMethod("setAcceptType", String.class);
                declaredMethod4.setAccessible(true);
                declaredMethod4.invoke(invoke, str);
            }
            if (callback != null) {
                Method declaredMethod5 = cls.getDeclaredMethod("setJsChannelCallback", Handler.Callback.class);
                declaredMethod5.setAccessible(true);
                declaredMethod5.invoke(invoke, callback);
            }
            Method declaredMethod6 = cls.getDeclaredMethod("setPermissionInterceptor", aj.class);
            declaredMethod6.setAccessible(true);
            declaredMethod6.invoke(invoke, ajVar);
            Method declaredMethod7 = cls.getDeclaredMethod("build", new Class[0]);
            declaredMethod7.setAccessible(true);
            Object invoke2 = declaredMethod7.invoke(invoke, new Object[0]);
            Method declaredMethod8 = invoke2.getClass().getDeclaredMethod("openFileChooser", new Class[0]);
            declaredMethod8.setAccessible(true);
            declaredMethod8.invoke(invoke2, new Object[0]);
            return true;
        } catch (Throwable th) {
            if (ag.ap()) {
                th.printStackTrace();
            }
            if (valueCallback != null) {
                ag.c(TAG, "onReceiveValue empty");
                return false;
            } else if (valueCallback2 != null) {
                valueCallback2.onReceiveValue(null);
                return true;
            } else {
                return true;
            }
        }
    }

    public static boolean a(@NonNull Context context, @NonNull List<String> list) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String str : list) {
            if (ContextCompat.checkSelfPermission(context, str) == -1) {
                return false;
            }
            String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
            if (!TextUtils.isEmpty(permissionToOp) && AppOpsManagerCompat.noteProxyOp(context, permissionToOp, context.getPackageName()) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(@NonNull Context context, @NonNull String... strArr) {
        return a(context, Arrays.asList(strArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Object obj, String str, String str2, Class... clsArr) {
        boolean z;
        ag.c(TAG, "  methodName:" + str + "   method:" + str2);
        if (obj == null) {
            return false;
        }
        try {
            z = !obj.getClass().getMethod(str, clsArr).toGenericString().contains(str2);
        } catch (Exception e) {
            z = false;
            if (ag.ap()) {
                e.printStackTrace();
                z = false;
            }
        }
        ag.c(TAG, "isOverriedMethod:" + z);
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    static Uri b(Context context, File file) {
        return FileProvider.getUriForFile(context, context.getPackageName() + ".AgentWebFileProvider", file);
    }

    public static Intent c(Context context, File file) {
        Intent action = new Intent().setAction("android.intent.action.VIEW");
        a(context, action, a(file), file, false);
        return action;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void c(WebView webView) {
        if (webView != null && Looper.myLooper() == Looper.getMainLooper()) {
            webView.loadUrl("about:blank");
            webView.stopLoading();
            if (webView.getHandler() != null) {
                webView.getHandler().removeCallbacksAndMessages(null);
            }
            webView.removeAllViews();
            ViewGroup viewGroup = (ViewGroup) webView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(webView);
            }
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.setTag(null);
            webView.clearHistory();
            webView.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (str.startsWith("[")) {
                new JSONArray(str);
            } else {
                new JSONObject(str);
            }
            z = true;
        } catch (JSONException e) {
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Intent d(Context context, File file) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        Uri a = a(context, file);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("output", a);
        return intent;
    }

    public static b d(WebView webView) {
        return e(webView).aw();
    }

    static String d(Context context) {
        if (TextUtils.isEmpty(c.dt)) {
            File file = new File(f(context), "agentweb-cache");
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
            } catch (Throwable th) {
                ag.c(TAG, "create dir exception");
            }
            String str = TAG;
            ag.c(str, "path:" + file.getAbsolutePath() + "  path:" + file.getPath());
            String absolutePath = file.getAbsolutePath();
            c.dt = absolutePath;
            return absolutePath;
        }
        return c.dt;
    }

    public static String d(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            if (ag.ap()) {
                e.printStackTrace();
                return "";
            }
            return "";
        }
    }

    public static int e(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type == 6 || type == 9) {
            return 1;
        }
        switch (type) {
            case 0:
                int subtype = activeNetworkInfo.getSubtype();
                switch (subtype) {
                    case 1:
                    case 2:
                        return 4;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        return 3;
                    default:
                        switch (subtype) {
                            case 12:
                                return 3;
                            case 13:
                            case 14:
                            case 15:
                                return 2;
                            default:
                                return 0;
                        }
                }
            case 1:
                return 1;
            default:
                return 0;
        }
    }

    static WebParentLayout e(WebView webView) {
        if (!(webView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("please check webcreator's create method was be called ?");
        }
        ViewGroup viewGroup = (ViewGroup) webView.getParent();
        while (true) {
            ViewGroup viewGroup2 = viewGroup;
            if (viewGroup2 == null) {
                throw new IllegalStateException("please check webcreator's create method was be called ?");
            }
            String str = TAG;
            ag.c(str, "ViewGroup:" + viewGroup2);
            if (viewGroup2.getId() == al.b.web_parent_layout_id) {
                WebParentLayout webParentLayout = (WebParentLayout) viewGroup2;
                ag.c(TAG, "found WebParentLayout");
                return webParentLayout;
            }
            ViewParent parent = viewGroup2.getParent();
            viewGroup = parent instanceof ViewGroup ? (ViewGroup) parent : null;
        }
    }

    static String f(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if ("mounted".equals(EnvironmentCompat.getStorageState(externalCacheDir))) {
            return externalCacheDir.getAbsolutePath();
        }
        return null;
    }

    public static boolean g(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = false;
        if (activeNetworkInfo != null) {
            z = false;
            if (activeNetworkInfo.isConnected()) {
                z = false;
                if (activeNetworkInfo.getType() == 1) {
                    z = true;
                }
            }
        }
        return z;
    }

    public static boolean h(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        boolean z = false;
        if (activeNetworkInfo != null) {
            z = false;
            if (activeNetworkInfo.isConnected()) {
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static File i(Context context) {
        File file;
        try {
            file = a(context, String.format("aw_%s.jpg", new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()).format(new Date())), true);
        } catch (Throwable th) {
            file = null;
        }
        return file;
    }

    public static String j(Context context) {
        PackageManager packageManager;
        ApplicationInfo applicationInfo;
        PackageManager packageManager2;
        try {
            packageManager2 = context.getApplicationContext().getPackageManager();
        } catch (PackageManager.NameNotFoundException e) {
            packageManager = null;
        }
        try {
            ApplicationInfo applicationInfo2 = packageManager2.getApplicationInfo(context.getPackageName(), 0);
            packageManager = packageManager2;
            applicationInfo = applicationInfo2;
        } catch (PackageManager.NameNotFoundException e2) {
            packageManager = packageManager2;
            applicationInfo = null;
            return (String) packageManager.getApplicationLabel(applicationInfo);
        }
        return (String) packageManager.getApplicationLabel(applicationInfo);
    }
}
