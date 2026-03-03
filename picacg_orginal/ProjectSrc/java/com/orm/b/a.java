package com.orm.b;

import android.content.Context;
import android.util.Log;
/* loaded from: picacg_1.jar:com/orm/b/a.class */
public class a {
    private static String b(Context context, String str) {
        String str2;
        try {
            str2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(str);
        } catch (Exception e) {
            Log.d("sugar", "Couldn't find config value: " + str);
            str2 = null;
        }
        return str2;
    }

    private static Integer c(Context context, String str) {
        Integer num;
        try {
            num = Integer.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt(str));
        } catch (Exception e) {
            Log.d("sugar", "Couldn't find config value: " + str);
            num = null;
        }
        return num;
    }

    private static Boolean d(Context context, String str) {
        Boolean bool;
        try {
            bool = Boolean.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean(str));
        } catch (Exception e) {
            Log.d("sugar", "Couldn't find config value: " + str);
            bool = false;
        }
        return bool;
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        if (r0.intValue() == 0) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int k(android.content.Context r3) {
        /*
            r0 = r3
            java.lang.String r1 = "VERSION"
            java.lang.Integer r0 = c(r0, r1)
            r4 = r0
            r0 = r4
            if (r0 == 0) goto L14
            r0 = r4
            r3 = r0
            r0 = r4
            int r0 = r0.intValue()
            if (r0 != 0) goto L19
        L14:
            r0 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3 = r0
        L19:
            r0 = r3
            int r0 = r0.intValue()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.orm.b.a.k(android.content.Context):int");
    }

    public static String l(Context context) {
        String b = b(context, "DOMAIN_PACKAGE_NAME");
        String str = b;
        if (b == null) {
            str = "";
        }
        return str;
    }

    public static String m(Context context) {
        String b = b(context, "DATABASE");
        String str = b;
        if (b == null) {
            str = "Sugar.db";
        }
        return str;
    }

    public static boolean n(Context context) {
        return d(context, "QUERY_LOG").booleanValue();
    }
}
