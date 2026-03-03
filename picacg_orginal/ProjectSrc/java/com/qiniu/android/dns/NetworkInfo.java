package com.qiniu.android.dns;
/* loaded from: picacg_1.jar:com/qiniu/android/dns/NetworkInfo.class */
public final class NetworkInfo {
    public static final NetworkInfo uV = new NetworkInfo(NetSatus.NO_NETWORK, 0);
    public static final NetworkInfo uW = new NetworkInfo(NetSatus.WIFI, 0);
    public final int uX;
    public final NetSatus uY;

    /* loaded from: picacg_1.jar:com/qiniu/android/dns/NetworkInfo$NetSatus.class */
    public enum NetSatus {
        NO_NETWORK,
        WIFI,
        MOBILE
    }

    public NetworkInfo(NetSatus netSatus, int i) {
        this.uY = netSatus;
        this.uX = i;
    }
}
