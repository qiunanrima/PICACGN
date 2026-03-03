package com.picacomic.fregata.c;

import java.util.Locale;
/* loaded from: picacg_1.jar:com/picacomic/fregata/c/b.class */
public class b {
    public static String uP;

    public static Locale aD(String str) {
        return str.equalsIgnoreCase("cantonese") ? new Locale("yue", "HK") : str.equalsIgnoreCase("japanese") ? Locale.JAPANESE : str.equalsIgnoreCase("english") ? Locale.ENGLISH : Locale.CHINESE;
    }
}
