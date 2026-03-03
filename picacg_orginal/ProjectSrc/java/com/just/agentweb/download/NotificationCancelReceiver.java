package com.just.agentweb.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.just.agentweb.ag;
/* loaded from: picacg_1.jar:com/just/agentweb/download/NotificationCancelReceiver.class */
public class NotificationCancelReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("com.agentweb.cancelled")) {
            try {
                a.aG().r(intent.getStringExtra("TAG"));
            } catch (Throwable th) {
                if (ag.ap()) {
                    th.printStackTrace();
                }
            }
        }
    }
}
