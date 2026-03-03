package com.qiniu.android.dns;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
/* loaded from: picacg_1.jar:com/qiniu/android/dns/NetworkReceiver.class */
public final class NetworkReceiver extends BroadcastReceiver {
    private static final Uri vd = Uri.parse("content://telephony/carriers/preferapn");
    private static a ve;

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0072, code lost:
        if (r0.startsWith("ctnet") != false) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00db, code lost:
        if (r0.equals("uniwap") != false) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.qiniu.android.dns.NetworkInfo a(android.net.NetworkInfo r7, android.content.Context r8) {
        /*
            Method dump skipped, instructions count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qiniu.android.dns.NetworkReceiver.a(android.net.NetworkInfo, android.content.Context):com.qiniu.android.dns.NetworkInfo");
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (ve == null) {
            return;
        }
        ve.a(a(((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo(), context));
    }
}
