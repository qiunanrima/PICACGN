package com.picacomic.fregata.utils;

import java.io.UnsupportedEncodingException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/d.class */
public class d {
    public static final String TAG = "d";
    protected static final char[] uq = "0123456789abcdef".toCharArray();
    String uo;

    public static String a(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = uq[i2 >>> 4];
            cArr[i3 + 1] = uq[i2 & 15];
        }
        return new String(cArr);
    }

    public String C(String str, String str2) {
        byte[] bArr;
        String str3;
        synchronized (this) {
            try {
                bArr = str2.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                bArr = new byte[0];
            }
            String lowerCase = str.toLowerCase();
            String str4 = TAG;
            f.D(str4, "RAW SIGNATURE = " + lowerCase);
            this.uo = a(lowerCase, bArr);
            str3 = this.uo;
        }
        return str3;
    }

    protected String a(String str, byte[] bArr) {
        String str2;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
            str2 = a(mac.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            str2 = null;
        }
        return str2;
    }
}
