package com.just.agentweb;

import android.app.Activity;
import android.webkit.DownloadListener;
import android.webkit.WebView;
/* loaded from: picacg_1.jar:com/just/agentweb/f.class */
public class f extends a {
    private AgentWeb mAgentWeb;

    @Override // com.just.agentweb.a, com.just.agentweb.ar
    public ar a(WebView webView, DownloadListener downloadListener) {
        DownloadListener downloadListener2;
        try {
            downloadListener2 = Class.forName("com.just.agentweb.download.DefaultDownloadImpl").getDeclaredMethod("create", Activity.class, WebView.class, Class.forName("com.just.agentweb.download.c"), Class.forName("com.just.agentweb.download.g"), aj.class).invoke(null, (Activity) webView.getContext(), webView, null, null, this.mAgentWeb.w());
        } catch (Throwable th) {
            downloadListener2 = null;
            if (ag.ap()) {
                th.printStackTrace();
                downloadListener2 = null;
            }
        }
        if (downloadListener2 != null) {
            downloadListener = downloadListener2;
        }
        return super.a(webView, downloadListener);
    }

    @Override // com.just.agentweb.a
    protected void b(AgentWeb agentWeb) {
        this.mAgentWeb = agentWeb;
    }
}
