package com.just.agentweb;

import android.content.Context;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.io.File;
/* loaded from: picacg_1.jar:com/just/agentweb/c.class */
public class c {
    private static final String TAG;
    static String dt;
    static final boolean du;
    static int dv;
    private static volatile boolean dw;
    public static int dx;
    static final String ds = File.separator + "agentweb-cache";
    public static boolean DEBUG = false;

    static {
        du = Build.VERSION.SDK_INT <= 19;
        dv = 1;
        dw = false;
        TAG = c.class.getSimpleName();
        dx = 5242880;
    }

    public static String a(Context context) {
        return context.getCacheDir().getAbsolutePath() + ds;
    }

    public static String b(String str) {
        return CookieManager.getInstance() == null ? null : CookieManager.getInstance().getCookie(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(Context context) {
        synchronized (c.class) {
            try {
                if (!dw) {
                    c(context);
                    dw = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static void c(Context context) {
        if (Build.VERSION.SDK_INT < 21) {
            CookieSyncManager.createInstance(context);
        }
    }
}
