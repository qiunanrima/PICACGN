package com.just.agentweb;

import android.app.Activity;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
import com.just.agentweb.al;
/* loaded from: picacg_1.jar:com/just/agentweb/l.class */
public class l extends m {
    private WebParentLayout cm;
    private Activity mActivity = null;
    private LayoutInflater mLayoutInflater;

    private void a(WebView webView, String str) {
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        try {
            h.a(webView, str, -1, -1, activity.getResources().getColor(al.a.black), (CharSequence) null, -1, (View.OnClickListener) null);
        } catch (Throwable th) {
            if (ag.ap()) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.just.agentweb.m, com.just.agentweb.b
    public void a(WebView webView, String str, String str2) {
        a(webView, str2);
    }

    @Override // com.just.agentweb.m, com.just.agentweb.b
    public void a(WebView webView, String str, String str2, JsResult jsResult) {
        super.a(webView, str, str2, jsResult);
    }

    @Override // com.just.agentweb.m, com.just.agentweb.b
    public void a(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        super.a(webView, str, str2, str3, jsPromptResult);
    }

    @Override // com.just.agentweb.m, com.just.agentweb.b
    public void a(String str, Handler.Callback callback) {
        super.a(str, callback);
    }

    @Override // com.just.agentweb.m, com.just.agentweb.b
    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str2) || !str2.contains("performDownload")) {
            a(this.cm.getWebView(), str);
        }
    }

    @Override // com.just.agentweb.m, com.just.agentweb.b
    protected void b(WebParentLayout webParentLayout, Activity activity) {
        super.b(webParentLayout, activity);
        this.mActivity = activity;
        this.cm = webParentLayout;
        this.mLayoutInflater = LayoutInflater.from(this.mActivity);
    }
}
