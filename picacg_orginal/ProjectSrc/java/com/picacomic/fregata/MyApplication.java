package com.picacomic.fregata;

import android.content.Context;
import com.orm.SugarApp;
import com.picacomic.fregata.b.b;
import com.picacomic.fregata.utils.d;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.squareup.picasso.Picasso;
import okhttp3.OkHttpClient;
/* loaded from: picacg_1.jar:com/picacomic/fregata/MyApplication.class */
public class MyApplication extends SugarApp {
    public static final String TAG = "MyApplication";
    private static MyApplication hk;
    private static Context mAppContext;
    private d hl;

    static {
        System.loadLibrary("JniTest");
    }

    public static void bw() {
        f.D(TAG, "SET PICASSO INSTANCE");
        Picasso.setSingletonInstance(new Picasso.Builder(mAppContext).downloader(new com.a.a.a(new OkHttpClient().newBuilder().dns(new b()).build())).build());
    }

    public static MyApplication bx() {
        return hk;
    }

    public static Context by() {
        return mAppContext;
    }

    public boolean bz() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStringComFromNative());
        sb.append("");
        return sb.toString().equalsIgnoreCase("1");
    }

    public String c(String[] strArr) {
        if (this.hl == null) {
            this.hl = new d();
        }
        String str = "";
        for (int i = 0; i < strArr.length; i++) {
            str = str + strArr[i] + ", ";
        }
        f.D(TAG, "RAW parameters = " + str);
        String stringConFromNative = getStringConFromNative(strArr);
        f.D(TAG, "CONCAT parameters = " + stringConFromNative);
        String stringSigFromNative = getStringSigFromNative();
        f.D(TAG, "CONCAT KEY = " + stringSigFromNative);
        return this.hl.C(stringConFromNative, getStringSigFromNative());
    }

    public native String getStringComFromNative();

    public native String getStringConFromNative(String[] strArr);

    public native String getStringSigFromNative();

    @Override // com.orm.SugarApp, android.app.Application
    public void onCreate() {
        super.onCreate();
        hk = this;
        s(getApplicationContext());
        if (e.al(this) == 0) {
            setTheme(2131755014);
        } else {
            setTheme(2131755019);
        }
    }

    public void s(Context context) {
        mAppContext = context;
    }
}
