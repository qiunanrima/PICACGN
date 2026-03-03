package com.orm.b;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
/* loaded from: picacg_1.jar:com/orm/b/c.class */
public class c {
    private static final String gW = "code_cache" + File.separator + "secondary-dexes";

    private static SharedPreferences o(Context context) {
        return context.getSharedPreferences("multidex.version", Build.VERSION.SDK_INT < 11 ? 0 : 4);
    }

    public static List<String> p(Context context) throws PackageManager.NameNotFoundException, IOException {
        ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
        File file = new File(applicationInfo.sourceDir);
        File file2 = new File(applicationInfo.dataDir, gW);
        ArrayList arrayList = new ArrayList();
        arrayList.add(applicationInfo.sourceDir);
        String str = file.getName() + ".classes";
        int i = o(context).getInt("dex.number", 1);
        for (int i2 = 2; i2 <= i; i2++) {
            File file3 = new File(file2, str + i2 + ".zip");
            if (file3.isFile()) {
                arrayList.add(file3.getAbsolutePath());
            }
        }
        return arrayList;
    }

    public static List<String> q(Context context) throws PackageManager.NameNotFoundException, IOException {
        ArrayList arrayList = new ArrayList();
        for (String str : p(context)) {
            try {
                Enumeration<String> entries = (str.endsWith(".zip") ? DexFile.loadDex(str, str + ".tmp", 0) : new DexFile(str)).entries();
                while (entries.hasMoreElements()) {
                    arrayList.add(entries.nextElement());
                }
            } catch (IOException e) {
                throw new IOException("Error at loading dex file '" + str + "'");
            }
        }
        return arrayList;
    }
}
