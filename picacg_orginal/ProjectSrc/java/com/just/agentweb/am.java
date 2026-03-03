package com.just.agentweb;

import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
/* loaded from: picacg_1.jar:com/just/agentweb/am.class */
public class am implements t {
    private q cZ;
    private WebView db;
    private Handler mHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public am(WebView webView, q qVar) {
        this.mHandler = null;
        this.db = webView;
        if (this.db == null) {
            new NullPointerException("webview cannot be null .");
        }
        this.cZ = qVar;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    private void q(final String str) {
        this.mHandler.post(new Runnable() { // from class: com.just.agentweb.am.1
            @Override // java.lang.Runnable
            public void run() {
                am.this.loadUrl(str);
            }
        });
    }

    @Override // com.just.agentweb.t
    public void loadUrl(String str) {
        if (!h.O()) {
            q(str);
        } else if (this.cZ == null || this.cZ.ai()) {
            this.db.loadUrl(str);
        } else {
            this.db.loadUrl(str, this.cZ.getHeaders());
        }
    }
}
