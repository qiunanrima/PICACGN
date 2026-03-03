package com.just.agentweb;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
/* loaded from: picacg_1.jar:com/just/agentweb/b.class */
public abstract class b {
    public static boolean cl = false;
    private WebParentLayout cm;
    protected b co;
    private Activity mActivity;
    private volatile boolean cn = false;
    protected String TAG = getClass().getSimpleName();

    static {
        try {
            Class.forName("android.support.design.widget.Snackbar");
            Class.forName("android.support.design.widget.BottomSheetDialog");
            cl = true;
        } catch (Throwable th) {
            cl = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Dialog dialog) {
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        dialog.dismiss();
    }

    public abstract void a(WebView webView, int i, String str, String str2);

    public abstract void a(WebView webView, String str, Handler.Callback callback);

    public abstract void a(WebView webView, String str, String str2);

    public abstract void a(WebView webView, String str, String str2, JsResult jsResult);

    public abstract void a(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(WebParentLayout webParentLayout, Activity activity) {
        synchronized (this) {
            if (!this.cn) {
                this.cn = true;
                this.cm = webParentLayout;
                this.mActivity = activity;
                b(webParentLayout, activity);
            }
        }
    }

    public abstract void a(String str, Handler.Callback callback);

    public abstract void a(String str, String str2);

    public abstract void a(String[] strArr, String str, String str2);

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void b(WebParentLayout webParentLayout, Activity activity);

    protected b o() {
        return cl ? new l() : new m();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b p() {
        b bVar = this.co;
        b bVar2 = bVar;
        if (bVar == null) {
            bVar2 = o();
            this.co = bVar2;
        }
        return bVar2;
    }

    public abstract void q();
}
