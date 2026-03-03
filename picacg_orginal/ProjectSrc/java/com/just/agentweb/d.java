package com.just.agentweb;

import android.app.Activity;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import java.lang.ref.WeakReference;
/* loaded from: picacg_1.jar:com/just/agentweb/d.class */
public class d {
    private String TAG = getClass().getSimpleName();
    private WeakReference<AgentWeb> dy;
    private WeakReference<Activity> dz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(AgentWeb agentWeb, Activity activity) {
        this.dy = null;
        this.dz = null;
        this.dy = new WeakReference<>(agentWeb);
        this.dz = new WeakReference<>(activity);
    }

    @JavascriptInterface
    public void uploadFile() {
        uploadFile("*/*");
    }

    @JavascriptInterface
    public void uploadFile(String str) {
        String str2 = this.TAG;
        ag.c(str2, str + "  " + this.dz.get() + "  " + this.dy.get());
        if (this.dz.get() == null || this.dy.get() == null) {
            return;
        }
        h.a(this.dz.get(), this.dy.get().z().getWebView(), (ValueCallback<Uri[]>) null, (WebChromeClient.FileChooserParams) null, this.dy.get().w(), (ValueCallback) null, str, new Handler.Callback() { // from class: com.just.agentweb.d.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (d.this.dy.get() != null) {
                    ((AgentWeb) d.this.dy.get()).y().a("uploadFileResult", message.obj instanceof String ? (String) message.obj : null);
                    return true;
                }
                return true;
            }
        });
    }
}
