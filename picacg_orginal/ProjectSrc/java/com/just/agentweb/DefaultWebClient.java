package com.just.agentweb;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.alipay.sdk.app.H5PayCallback;
import com.alipay.sdk.app.PayTask;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: picacg_1.jar:com/just/agentweb/DefaultWebClient.class */
public class DefaultWebClient extends ai {
    private static final String TAG = "DefaultWebClient";
    private static final boolean ed;
    private WebViewClient cA;
    private boolean cR;
    private int cS;
    private WeakReference<b> dS;
    private WebView db;
    private WeakReference<Activity> dm;
    private boolean ec;
    private Handler.Callback ee;
    private Method ef;
    private Object eg;
    private Set<String> eh;
    private Set<String> ei;

    /* loaded from: picacg_1.jar:com/just/agentweb/DefaultWebClient$OpenOtherPageWays.class */
    public enum OpenOtherPageWays {
        DERECT(PointerIconCompat.TYPE_CONTEXT_MENU),
        ASK(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION),
        DISALLOW(62);
        
        int code;

        OpenOtherPageWays(int i) {
            this.code = i;
        }
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/DefaultWebClient$a.class */
    public static class a {
        private boolean cP;
        private aj cQ;
        private int cS;
        private WebView db;
        private WebViewClient em;
        private boolean en;
        private Activity mActivity;

        public a a(Activity activity) {
            this.mActivity = activity;
            return this;
        }

        public a a(aj ajVar) {
            this.cQ = ajVar;
            return this;
        }

        public a a(boolean z) {
            this.cP = z;
            return this;
        }

        public DefaultWebClient aa() {
            return new DefaultWebClient(this);
        }

        public a b(WebViewClient webViewClient) {
            this.em = webViewClient;
            return this;
        }

        public a b(boolean z) {
            this.en = z;
            return this;
        }

        public a e(int i) {
            this.cS = i;
            return this;
        }

        public a g(WebView webView) {
            this.db = webView;
            return this;
        }
    }

    static {
        boolean z;
        try {
            Class.forName("com.alipay.sdk.app.PayTask");
            z = true;
        } catch (Throwable th) {
            z = false;
        }
        ed = z;
        ag.c(TAG, "HAS_ALIPAY_LIB:" + ed);
    }

    DefaultWebClient(a aVar) {
        super(aVar.em);
        this.dm = null;
        this.ec = true;
        this.cS = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        this.cR = true;
        this.dS = null;
        this.ee = null;
        this.ef = null;
        this.eh = new HashSet();
        this.ei = new HashSet();
        this.db = aVar.db;
        this.cA = aVar.em;
        this.dm = new WeakReference<>(aVar.mActivity);
        this.ec = aVar.cP;
        this.dS = new WeakReference<>(h.d(aVar.db));
        this.cR = aVar.en;
        if (aVar.cS <= 0) {
            this.cS = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        } else {
            this.cS = aVar.cS;
        }
    }

    public static a Z() {
        return new a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x005a, code lost:
        if (r13 != null) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.webkit.WebView r8, int r9, java.lang.String r10, java.lang.String r11) {
        /*
            r7 = this;
            r0 = r7
            java.util.Set<java.lang.String> r0 = r0.eh
            r1 = r11
            boolean r0 = r0.add(r1)
            r0 = r7
            android.webkit.WebViewClient r0 = r0.cA
            if (r0 == 0) goto L98
            r0 = r7
            boolean r0 = r0.ec
            if (r0 == 0) goto L98
            r0 = r7
            java.lang.reflect.Method r0 = r0.ef
            r12 = r0
            r0 = r12
            r13 = r0
            r0 = r12
            if (r0 != 0) goto L5d
            r0 = r7
            android.webkit.WebViewClient r0 = r0.cA
            java.lang.String r1 = "onMainFrameError"
            r2 = 5
            java.lang.Class[] r2 = new java.lang.Class[r2]
            r3 = r2
            r4 = 0
            java.lang.Class<com.just.agentweb.b> r5 = com.just.agentweb.b.class
            r3[r4] = r5
            r3 = r2
            r4 = 1
            java.lang.Class<android.webkit.WebView> r5 = android.webkit.WebView.class
            r3[r4] = r5
            r3 = r2
            r4 = 2
            java.lang.Class r5 = java.lang.Integer.TYPE
            r3[r4] = r5
            r3 = r2
            r4 = 3
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r4] = r5
            r3 = r2
            r4 = 4
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r3[r4] = r5
            java.lang.reflect.Method r0 = com.just.agentweb.h.a(r0, r1, r2)
            r13 = r0
            r0 = r7
            r1 = r13
            r0.ef = r1
            r0 = r13
            if (r0 == 0) goto L98
        L5d:
            r0 = r13
            r1 = r7
            android.webkit.WebViewClient r1 = r1.cA     // Catch: java.lang.Throwable -> L8c
            r2 = 5
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L8c
            r3 = r2
            r4 = 0
            r5 = r7
            java.lang.ref.WeakReference<com.just.agentweb.b> r5 = r5.dS     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r5 = r5.get()     // Catch: java.lang.Throwable -> L8c
            r3[r4] = r5     // Catch: java.lang.Throwable -> L8c
            r3 = r2
            r4 = 1
            r5 = r8
            r3[r4] = r5     // Catch: java.lang.Throwable -> L8c
            r3 = r2
            r4 = 2
            r5 = r9
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L8c
            r3[r4] = r5     // Catch: java.lang.Throwable -> L8c
            r3 = r2
            r4 = 3
            r5 = r10
            r3[r4] = r5     // Catch: java.lang.Throwable -> L8c
            r3 = r2
            r4 = 4
            r5 = r11
            r3[r4] = r5     // Catch: java.lang.Throwable -> L8c
            java.lang.Object r0 = r0.invoke(r1, r2)     // Catch: java.lang.Throwable -> L8c
            goto L97
        L8c:
            r8 = move-exception
            boolean r0 = com.just.agentweb.ag.ap()
            if (r0 == 0) goto L97
            r0 = r8
            r0.printStackTrace()
        L97:
            return
        L98:
            r0 = r7
            java.lang.ref.WeakReference<com.just.agentweb.b> r0 = r0.dS
            java.lang.Object r0 = r0.get()
            if (r0 == 0) goto Lb4
            r0 = r7
            java.lang.ref.WeakReference<com.just.agentweb.b> r0 = r0.dS
            java.lang.Object r0 = r0.get()
            com.just.agentweb.b r0 = (com.just.agentweb.b) r0
            r1 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.a(r1, r2, r3, r4)
        Lb4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.just.agentweb.DefaultWebClient.a(android.webkit.WebView, int, java.lang.String, java.lang.String):void");
    }

    private boolean b(final WebView webView, String str) {
        try {
            Activity activity = this.dm.get();
            if (activity == null) {
                return false;
            }
            if (this.eg == null) {
                this.eg = Class.forName("com.alipay.sdk.app.PayTask").getConstructor(Activity.class).newInstance(activity);
            }
            boolean payInterceptorWithUrl = ((PayTask) this.eg).payInterceptorWithUrl(str, true, new H5PayCallback() { // from class: com.just.agentweb.DefaultWebClient.1
            });
            if (payInterceptorWithUrl) {
                String str2 = TAG;
                ag.c(str2, "alipay-isIntercepted:" + payInterceptorWithUrl + "  url:" + str);
            }
            return payInterceptorWithUrl;
        } catch (Throwable th) {
            if (c.DEBUG) {
                th.printStackTrace();
                return false;
            }
            return false;
        }
    }

    private boolean h(String str) {
        int i = this.cS;
        if (i != 250) {
            if (i != 1001) {
                return false;
            }
            k(str);
            return true;
        } else if (this.dS.get() != null) {
            this.dS.get().a(this.db, this.db.getUrl(), n(str));
            return true;
        } else {
            return true;
        }
    }

    private int i(String str) {
        try {
            if (this.dm.get() == null) {
                return 0;
            }
            List<ResolveInfo> queryIntentActivities = this.dm.get().getPackageManager().queryIntentActivities(Intent.parseUri(str, 1), 65536);
            return queryIntentActivities == null ? 0 : queryIntentActivities.size();
        } catch (URISyntaxException e) {
            if (ag.ap()) {
                e.printStackTrace();
                return 0;
            }
            return 0;
        }
    }

    private void j(String str) {
        try {
            if (!TextUtils.isEmpty(str) && str.startsWith("intent://")) {
                if (k(str)) {
                }
            }
        } catch (Throwable th) {
            if (ag.ap()) {
                th.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean k(String str) {
        try {
            Activity activity = this.dm.get();
            if (activity == null) {
                return true;
            }
            PackageManager packageManager = activity.getPackageManager();
            Intent parseUri = Intent.parseUri(str, 1);
            if (packageManager.resolveActivity(parseUri, 65536) != null) {
                activity.startActivity(parseUri);
                return true;
            }
            return false;
        } catch (Throwable th) {
            if (ag.ap()) {
                th.printStackTrace();
                return false;
            }
            return false;
        }
    }

    private boolean l(String str) {
        if (str.startsWith("tel:") || str.startsWith("sms:") || str.startsWith("mailto:") || str.startsWith("geo:0,0?q=")) {
            try {
                Activity activity = this.dm.get();
                if (activity == null) {
                    return false;
                }
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(str));
                activity.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                if (c.DEBUG) {
                    e.printStackTrace();
                    return true;
                }
                return true;
            }
        }
        return false;
    }

    private void m(String str) {
        try {
            if (this.dm.get() == null) {
                return;
            }
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            this.dm.get().startActivity(intent);
        } catch (Exception e) {
            if (ag.ap()) {
                e.printStackTrace();
            }
        }
    }

    private Handler.Callback n(final String str) {
        if (this.ee != null) {
            return this.ee;
        }
        Handler.Callback callback = new Handler.Callback() { // from class: com.just.agentweb.DefaultWebClient.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (message.what != 1) {
                    return true;
                }
                DefaultWebClient.this.k(str);
                return true;
            }
        };
        this.ee = callback;
        return callback;
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        if (this.eh.contains(str) || !this.ei.contains(str)) {
            webView.setVisibility(0);
        } else if (this.dS.get() != null) {
            this.dS.get().q();
        }
        if (this.ei.contains(str)) {
            this.ei.remove(str);
        }
        if (!this.eh.isEmpty()) {
            this.eh.clear();
        }
        super.onPageFinished(webView, str);
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        if (!this.ei.contains(str)) {
            this.ei.add(str);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (h.a(this.cA, "onReceivedError", "android.webkit.WebViewClient.onReceivedError", WebView.class, Integer.TYPE, String.class, String.class)) {
            super.onReceivedError(webView, i, str, str2);
        }
        String str3 = TAG;
        ag.c(str3, "onReceivedError：" + str + "  CODE:" + i);
        a(webView, i, str, str2);
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (h.a(this.cA, "onReceivedError", "android.webkit.WebViewClient.onReceivedError", WebView.class, WebResourceRequest.class, WebResourceError.class)) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }
        if (webResourceRequest.isForMainFrame()) {
            a(webView, webResourceError.getErrorCode(), webResourceError.getDescription().toString(), webResourceRequest.getUrl().toString());
        }
        String str = TAG;
        ag.c(str, "onReceivedError:" + webResourceError.toString());
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) {
        if (h.a(this.cA, "onScaleChanged", "android.webkit.WebViewClient.onScaleChanged", WebView.class, Float.TYPE, Float.TYPE)) {
            super.onScaleChanged(webView, f, f2);
            return;
        }
        String str = TAG;
        ag.c(str, "onScaleChanged:" + f + "   n:" + f2);
        if (f2 - f > 7.0f) {
            webView.setInitialScale((int) ((f / f2) * 100.0f));
        }
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return super.shouldInterceptRequest(webView, str);
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        return super.shouldOverrideKeyEvent(webView, keyEvent);
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        char c;
        if (!h.a(this.cA, "shouldOverrideUrlLoading", "android.webkit.WebViewClient.shouldOverrideUrlLoading", WebView.class, WebResourceRequest.class)) {
            c = 65535;
        } else if (super.shouldOverrideUrlLoading(webView, webResourceRequest)) {
            return true;
        } else {
            c = 1;
        }
        String uri = webResourceRequest.getUrl().toString();
        if (uri.startsWith("http://") || uri.startsWith("https://")) {
            boolean z = false;
            if (this.ec) {
                z = false;
                if (ed) {
                    z = false;
                    if (b(webView, uri)) {
                        z = true;
                    }
                }
            }
            return z;
        } else if (this.ec) {
            if (l(uri)) {
                return true;
            }
            if (uri.startsWith("intent://")) {
                j(uri);
                ag.c(TAG, "intent url ");
                return true;
            } else if (uri.startsWith("weixin://wap/pay?")) {
                ag.c(TAG, "lookup wechat to pay ~~");
                m(uri);
                return true;
            } else if (uri.startsWith("alipays://") && k(uri)) {
                ag.c(TAG, "alipays url lookup alipay ~~ ");
                return true;
            } else if (i(uri) > 0 && h(uri)) {
                ag.c(TAG, "intercept url:" + uri);
                return true;
            } else if (!this.cR) {
                if (c > 0) {
                    return false;
                }
                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            } else {
                ag.c(TAG, "intercept mIsInterceptUnkownUrl :" + webResourceRequest.getUrl());
                return true;
            }
        } else {
            return false;
        }
    }

    @Override // com.just.agentweb.aw, android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        char c;
        if (!h.a(this.cA, "shouldOverrideUrlLoading", "android.webkit.WebViewClient.shouldOverrideUrlLoading", WebView.class, String.class)) {
            c = 65535;
        } else if (super.shouldOverrideUrlLoading(webView, str)) {
            return true;
        } else {
            c = 1;
        }
        if (str.startsWith("http://") || str.startsWith("https://")) {
            boolean z = false;
            if (this.ec) {
                z = false;
                if (ed) {
                    z = false;
                    if (b(webView, str)) {
                        z = true;
                    }
                }
            }
            return z;
        } else if (this.ec) {
            if (l(str)) {
                return true;
            }
            if (str.startsWith("intent://")) {
                j(str);
                return true;
            } else if (str.startsWith("weixin://wap/pay?")) {
                m(str);
                return true;
            } else if (str.startsWith("alipays://") && k(str)) {
                return true;
            } else {
                if (i(str) > 0 && h(str)) {
                    ag.c(TAG, "intercept OtherAppScheme");
                    return true;
                } else if (!this.cR) {
                    if (c > 0) {
                        return false;
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                } else {
                    ag.c(TAG, "intercept InterceptUnkownScheme : " + str);
                    return true;
                }
            }
        } else {
            return false;
        }
    }
}
