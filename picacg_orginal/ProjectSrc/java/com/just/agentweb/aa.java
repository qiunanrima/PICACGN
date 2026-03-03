package com.just.agentweb;

import android.os.Handler;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebView;
/* loaded from: picacg_1.jar:com/just/agentweb/aa.class */
public class aa extends j {
    private WebView db;
    private Handler mHandler;

    private aa(WebView webView) {
        super(webView);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.db = webView;
    }

    private void c(final String str, final ValueCallback valueCallback) {
        this.mHandler.post(new Runnable() { // from class: com.just.agentweb.aa.1
            @Override // java.lang.Runnable
            public void run() {
                aa.this.a(str, valueCallback);
            }
        });
    }

    public static aa h(WebView webView) {
        return new aa(webView);
    }

    @Override // com.just.agentweb.j
    public void a(String str, ValueCallback<String> valueCallback) {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            c(str, valueCallback);
        } else {
            super.a(str, valueCallback);
        }
    }
}
