package com.just.agentweb;

import android.os.Build;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/* loaded from: picacg_1.jar:com/just/agentweb/a.class */
public abstract class a implements ar, r {
    private static final String TAG = "a";
    private WebSettings ck;
    protected AgentWeb mAgentWeb;

    private void b(WebView webView) {
        this.ck = webView.getSettings();
        this.ck.setJavaScriptEnabled(true);
        this.ck.setSupportZoom(true);
        this.ck.setBuiltInZoomControls(false);
        this.ck.setSavePassword(false);
        if (h.h(webView.getContext())) {
            this.ck.setCacheMode(-1);
        } else {
            this.ck.setCacheMode(1);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            this.ck.setMixedContentMode(0);
            webView.setLayerType(2, null);
        } else if (Build.VERSION.SDK_INT >= 19) {
            webView.setLayerType(2, null);
        } else if (Build.VERSION.SDK_INT < 19) {
            webView.setLayerType(1, null);
        }
        this.ck.setTextZoom(100);
        this.ck.setDatabaseEnabled(true);
        this.ck.setAppCacheEnabled(true);
        this.ck.setLoadsImagesAutomatically(true);
        this.ck.setSupportMultipleWindows(false);
        this.ck.setBlockNetworkImage(false);
        this.ck.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= 16) {
            this.ck.setAllowFileAccessFromFileURLs(false);
            this.ck.setAllowUniversalAccessFromFileURLs(false);
        }
        this.ck.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= 19) {
            this.ck.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            this.ck.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        this.ck.setLoadWithOverviewMode(false);
        this.ck.setUseWideViewPort(false);
        this.ck.setDomStorageEnabled(true);
        this.ck.setNeedInitialFocus(true);
        this.ck.setDefaultTextEncodingName("utf-8");
        this.ck.setDefaultFontSize(16);
        this.ck.setMinimumFontSize(12);
        this.ck.setGeolocationEnabled(true);
        String a = c.a(webView.getContext());
        String str = TAG;
        ag.c(str, "dir:" + a + "   appcache:" + c.a(webView.getContext()));
        this.ck.setGeolocationDatabasePath(a);
        this.ck.setDatabasePath(a);
        this.ck.setAppCachePath(a);
        this.ck.setAppCacheMaxSize(Long.MAX_VALUE);
        this.ck.setUserAgentString(n().getUserAgentString().concat(" agentweb/4.0.2 ").concat(" UCBrowser/11.6.4.950 "));
        String str2 = TAG;
        ag.c(str2, "UserAgentString : " + this.ck.getUserAgentString());
    }

    public static a m() {
        return new f();
    }

    @Override // com.just.agentweb.ar
    public ar a(WebView webView, DownloadListener downloadListener) {
        webView.setDownloadListener(downloadListener);
        return this;
    }

    @Override // com.just.agentweb.ar
    public ar a(WebView webView, WebChromeClient webChromeClient) {
        webView.setWebChromeClient(webChromeClient);
        return this;
    }

    @Override // com.just.agentweb.ar
    public ar a(WebView webView, WebViewClient webViewClient) {
        webView.setWebViewClient(webViewClient);
        return this;
    }

    @Override // com.just.agentweb.r
    public r a(WebView webView) {
        b(webView);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(AgentWeb agentWeb) {
        this.mAgentWeb = agentWeb;
        b(agentWeb);
    }

    protected abstract void b(AgentWeb agentWeb);

    @Override // com.just.agentweb.r
    public WebSettings n() {
        return this.ck;
    }
}
