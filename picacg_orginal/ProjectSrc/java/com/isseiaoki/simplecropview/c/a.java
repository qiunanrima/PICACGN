package com.isseiaoki.simplecropview.c;

import android.util.Log;
/* loaded from: picacg_1.jar:com/isseiaoki/simplecropview/c/a.class */
public class a {
    public static boolean enabled = false;

    public static void a(String str, Throwable th) {
        if (enabled) {
            Log.e("SimpleCropView", str, th);
        }
    }
}
