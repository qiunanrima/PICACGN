package com.qiniu.android.dns;

import com.qiniu.android.dns.util.LruCache;
/* loaded from: picacg_1.jar:com/qiniu/android/dns/a.class */
public final class a {
    private volatile int index;
    private final b[] uS;
    private final LruCache<String, Object[]> uT;
    private volatile NetworkInfo uU;

    private void clearCache() {
        synchronized (this.uT) {
            this.uT.clear();
        }
    }

    public void a(NetworkInfo networkInfo) {
        clearCache();
        NetworkInfo networkInfo2 = networkInfo;
        if (networkInfo == null) {
            networkInfo2 = NetworkInfo.uW;
        }
        this.uU = networkInfo2;
        synchronized (this.uS) {
            this.index = 0;
        }
    }
}
