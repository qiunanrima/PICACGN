package com.just.agentweb.download;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import com.just.agentweb.ag;
import com.just.agentweb.download.j;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/just/agentweb/download/e.class */
public class e {
    private static final String TAG = "e";
    private int fM;
    private NotificationCompat.Action fO;
    private String fp;
    private NotificationCompat.Builder mBuilder;
    private String mChannelId;
    private Context mContext;
    private File mFile;
    private Notification mNotification;
    private NotificationManager mNotificationManager;
    int fL = (int) SystemClock.uptimeMillis();
    private volatile boolean fN = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(Context context, int i) {
        this.mChannelId = "";
        this.fM = i;
        this.mContext = context;
        this.mNotificationManager = (NotificationManager) this.mContext.getSystemService("notification");
        try {
            if (Build.VERSION.SDK_INT < 26) {
                this.mBuilder = new NotificationCompat.Builder(this.mContext);
                return;
            }
            Context context2 = this.mContext;
            String concat = this.mContext.getPackageName().concat(" agentweb/4.0.2 ");
            this.mChannelId = concat;
            this.mBuilder = new NotificationCompat.Builder(context2, concat);
            ((NotificationManager) this.mContext.getSystemService("notification")).createNotificationChannel(new NotificationChannel(this.mChannelId, com.just.agentweb.h.j(context), 3));
        } catch (Throwable th) {
            if (ag.ap()) {
                th.printStackTrace();
            }
            throw th;
        }
    }

    private PendingIntent a(Context context, int i, String str) {
        Intent intent = new Intent(context, NotificationCancelReceiver.class);
        intent.setAction("com.agentweb.cancelled");
        intent.putExtra("TAG", str);
        int i2 = i << 3;
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i2, intent, 134217728);
        String str2 = TAG;
        ag.c(str2, "id<<3:" + i2);
        return broadcast;
    }

    private void a(int i, int i2, boolean z) {
        this.mBuilder.setProgress(i, i2, z);
        aQ();
    }

    private void a(PendingIntent pendingIntent) {
        this.mBuilder.getNotification().deleteIntent = pendingIntent;
    }

    private boolean aP() {
        return this.mBuilder.getNotification().deleteIntent != null;
    }

    private void aQ() {
        this.mNotification = this.mBuilder.build();
        this.mNotificationManager.notify(this.fM, this.mNotification);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(DownloadTask downloadTask) {
        String string = TextUtils.isEmpty(downloadTask.aV().getName()) ? this.mContext.getString(j.b.agentweb_file_download) : downloadTask.aV().getName();
        String str = string;
        if (string.length() > 20) {
            str = "..." + string.substring(string.length() - 20, string.length());
        }
        this.mBuilder.setContentIntent(PendingIntent.getActivity(this.mContext, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION, new Intent(), 134217728));
        this.mBuilder.setSmallIcon(downloadTask.aW());
        this.mBuilder.setTicker(this.mContext.getString(j.b.agentweb_trickter));
        this.mBuilder.setContentTitle(str);
        this.mBuilder.setContentText(this.mContext.getString(j.b.agentweb_coming_soon_download));
        this.mBuilder.setWhen(System.currentTimeMillis());
        this.mBuilder.setAutoCancel(true);
        this.mBuilder.setPriority(2);
        this.fp = downloadTask.getUrl();
        this.mFile = downloadTask.aV();
        this.mBuilder.setDeleteIntent(a(this.mContext, downloadTask.getId(), downloadTask.getUrl()));
        this.mBuilder.setDefaults(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void aR() {
        aQ();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void aS() {
        try {
            Field declaredField = this.mBuilder.getClass().getDeclaredField("mActions");
            ArrayList arrayList = declaredField != null ? (ArrayList) declaredField.get(this.mBuilder) : null;
            if (arrayList != null) {
                int indexOf = arrayList.indexOf(this.fO);
                if (indexOf != -1) {
                    arrayList.remove(indexOf);
                }
            }
        } catch (Throwable th) {
            if (ag.ap()) {
                th.printStackTrace();
            }
        }
        Intent c = com.just.agentweb.h.c(this.mContext, this.mFile);
        a((PendingIntent) null);
        if (c != null) {
            if (!(this.mContext instanceof Activity)) {
                c.addFlags(268435456);
            }
            PendingIntent activity = PendingIntent.getActivity(this.mContext, this.fM << 4, c, 134217728);
            this.mBuilder.setContentText(this.mContext.getString(j.b.agentweb_click_open));
            this.mBuilder.setProgress(100, 100, false);
            this.mBuilder.setContentIntent(activity);
            aQ();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel() {
        this.mNotificationManager.cancel(this.fM);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void f(int i) {
        if (!aP()) {
            a(a(this.mContext, this.fM, this.fp));
        }
        if (!this.fN) {
            this.fN = true;
            this.fO = new NotificationCompat.Action(j.a.ic_cancel_transparent_2dp, this.mContext.getString(17039360), a(this.mContext, this.fM, this.fp));
            this.mBuilder.addAction(this.fO);
        }
        NotificationCompat.Builder builder = this.mBuilder;
        Context context = this.mContext;
        int i2 = j.b.agentweb_current_downloading_progress;
        builder.setContentText(context.getString(i2, i + "%"));
        a(100, i, false);
        aQ();
    }
}
