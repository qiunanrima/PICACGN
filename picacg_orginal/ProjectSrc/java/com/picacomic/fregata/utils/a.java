package com.picacomic.fregata.utils;

import com.picacomic.fregata.MyApplication;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/a.class */
public class a {
    public static String dS() {
        if (MyApplication.by() != null) {
            return e.am(MyApplication.by()) + "/android/font";
        }
        return "http://pica-juicy.picacomic.com/android/font";
    }

    public static String dT() {
        if (MyApplication.by() != null) {
            return e.am(MyApplication.by()) + "/android/chat";
        }
        return "http://pica-juicy.picacomic.com/android/chat";
    }

    public static String dU() {
        if (MyApplication.by() != null) {
            return e.am(MyApplication.by()) + "/android/cat";
        }
        return "http://pica-juicy.picacomic.com/android/cat";
    }

    public static String getDetail() {
        if (MyApplication.by() != null) {
            return e.am(MyApplication.by()) + "/android/comic_detail";
        }
        return "http://pica-juicy.picacomic.com/android/comic_detail";
    }
}
