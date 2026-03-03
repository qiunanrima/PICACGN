package com.just.agentweb;

import android.app.Activity;
import android.os.Handler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
/* loaded from: picacg_1.jar:com/just/agentweb/g.class */
public class g extends b {
    public static b M() {
        return new g();
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, int i, String str, String str2) {
        p().a(webView, i, str, str2);
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, String str, Handler.Callback callback) {
        p().a(webView, str, callback);
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, String str, String str2) {
        p().a(webView, str, str2);
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, String str, String str2, JsResult jsResult) {
        p().a(webView, str, str2, jsResult);
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        p().a(webView, str, str2, str3, jsPromptResult);
    }

    @Override // com.just.agentweb.b
    public void a(String str, Handler.Callback callback) {
        p().a(str, callback);
    }

    @Override // com.just.agentweb.b
    public void a(String str, String str2) {
        p().a(str, str2);
    }

    @Override // com.just.agentweb.b
    public void a(String[] strArr, String str, String str2) {
        p().a(strArr, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.just.agentweb.b
    public void b(WebParentLayout webParentLayout, Activity activity) {
        p().b(webParentLayout, activity);
    }

    @Override // com.just.agentweb.b
    public void q() {
        p().q();
    }
}
