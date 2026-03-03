package com.just.agentweb;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.webkit.JsPromptResult;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: picacg_1.jar:com/just/agentweb/AgentWebView.class */
public class AgentWebView extends WebView {
    private static final String TAG = "AgentWebView";
    private Map<String, ac> dG;
    private Map<String, String> dH;
    private c dI;
    private boolean dJ;
    private Boolean dK;

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWebView$a.class */
    public static class a extends ah {
        private AgentWebView dL;

        private a(AgentWebView agentWebView) {
            this.dL = agentWebView;
        }

        @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            ac acVar;
            String str4 = AgentWebView.TAG;
            Log.i(str4, "onJsPrompt:" + str + "  message:" + str2 + "  d:" + str3 + "  ");
            if (this.dL.dG == null || !ac.o(str2)) {
                return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            }
            JSONObject p = ac.p(str2);
            String a = ac.a(p);
            if (a == null || (acVar = (ac) this.dL.dG.get(a)) == null) {
                return true;
            }
            jsPromptResult.confirm(acVar.a(webView, p));
            return true;
        }

        @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
        public void onProgressChanged(WebView webView, int i) {
            if (this.dL.dG != null) {
                this.dL.P();
                if (ag.ap()) {
                    String str = AgentWebView.TAG;
                    Log.d(str, "injectJavaScript, onProgressChanged.newProgress = " + i + ", url = " + webView.getUrl());
                }
            }
            if (this.dL.dH != null) {
                this.dL.Q();
            }
            super.onProgressChanged(webView, i);
        }

        @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
        public void onReceivedTitle(WebView webView, String str) {
            this.dL.dI.X();
            super.onReceivedTitle(webView, str);
        }
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWebView$b.class */
    public static class b extends ai {
        private AgentWebView dL;

        private b(AgentWebView agentWebView) {
            this.dL = agentWebView;
        }

        @Override // com.just.agentweb.aw, android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            this.dL.dI.f(webView);
            if (ag.ap()) {
                String str2 = AgentWebView.TAG;
                Log.d(str2, "onPageFinished.url = " + webView.getUrl());
            }
        }

        @Override // com.just.agentweb.aw, android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            if (this.dL.dG != null) {
                this.dL.P();
                if (ag.ap()) {
                    String str2 = AgentWebView.TAG;
                    Log.d(str2, "injectJavaScript, onPageStarted.url = " + webView.getUrl());
                }
            }
            if (this.dL.dH != null) {
                this.dL.Q();
            }
            this.dL.dI.W();
            this.dL.e(str);
        }
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWebView$c.class */
    private static class c {
        private WebChromeClient cz;
        private boolean dM;

        private c() {
        }

        public void W() {
            this.dM = false;
        }

        public void X() {
            this.dM = true;
        }

        public void f(WebView webView) {
            WebBackForwardList webBackForwardList;
            if (this.dM || this.cz == null) {
                return;
            }
            try {
                webBackForwardList = webView.copyBackForwardList();
            } catch (NullPointerException e) {
                webBackForwardList = null;
                if (ag.ap()) {
                    e.printStackTrace();
                    webBackForwardList = null;
                }
            }
            if (webBackForwardList == null || webBackForwardList.getSize() <= 0 || webBackForwardList.getCurrentIndex() < 0 || webBackForwardList.getItemAtIndex(webBackForwardList.getCurrentIndex()) == null) {
                return;
            }
            this.cz.onReceivedTitle(webView, webBackForwardList.getItemAtIndex(webBackForwardList.getCurrentIndex()).getTitle());
        }

        public void setWebChromeClient(WebChromeClient webChromeClient) {
            this.cz = webChromeClient;
        }
    }

    public AgentWebView(Context context) {
        this(context, null);
    }

    public AgentWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        T();
        this.dJ = true;
        this.dI = new c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void P() {
        for (Map.Entry<String, ac> entry : this.dG.entrySet()) {
            loadUrl(b(entry.getKey(), entry.getValue().ao()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        for (Map.Entry<String, String> entry : this.dH.entrySet()) {
            loadUrl(b(entry.getKey(), entry.getValue()));
        }
    }

    private void R() {
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeAllViewsInLayout();
        }
    }

    private void S() {
        if (Build.VERSION.SDK_INT < 16) {
            try {
                Field declaredField = WebView.class.getDeclaredField("mWebViewCore").getType().getDeclaredField("mBrowserFrame").getType().getDeclaredField("sConfigCallback");
                declaredField.setAccessible(true);
                declaredField.set(null, null);
            } catch (IllegalAccessException e) {
                if (ag.ap()) {
                    e.printStackTrace();
                }
            } catch (NoSuchFieldException e2) {
                if (ag.ap()) {
                    e2.printStackTrace();
                }
            }
        } else if (Build.VERSION.SDK_INT < 19) {
            try {
                Field declaredField2 = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (declaredField2 != null) {
                    declaredField2.setAccessible(true);
                    declaredField2.set(null, null);
                }
            } catch (ClassNotFoundException e3) {
                if (ag.ap()) {
                    e3.printStackTrace();
                }
            } catch (IllegalAccessException e4) {
                if (ag.ap()) {
                    e4.printStackTrace();
                }
            } catch (NoSuchFieldException e5) {
                if (ag.ap()) {
                    e5.printStackTrace();
                }
            }
        }
    }

    private void U() {
        if (this.dK != null) {
            setAccessibilityEnabled(this.dK.booleanValue());
        }
    }

    public static Pair<Boolean, String> a(Throwable th) {
        String th2 = th.getCause() == null ? th.toString() : th.getCause().toString();
        String stackTraceString = Log.getStackTraceString(th);
        if (stackTraceString.contains("android.content.pm.PackageManager$NameNotFoundException") || stackTraceString.contains("java.lang.RuntimeException: Cannot load WebView") || stackTraceString.contains("android.webkit.WebViewFactory$MissingWebViewPackageException: Failed to load WebView provider: No WebView installed")) {
            ag.a(TAG, "isWebViewPackageException", th);
            return new Pair<>(true, "WebView load failed, " + th2);
        }
        return new Pair<>(false, th2);
    }

    private boolean isAccessibilityEnabled() {
        return ((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled();
    }

    private void setAccessibilityEnabled(boolean z) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        try {
            Method declaredMethod = accessibilityManager.getClass().getDeclaredMethod("setAccessibilityState", Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(accessibilityManager, Boolean.valueOf(z));
            declaredMethod.setAccessible(false);
        } catch (Throwable th) {
            if (ag.ap()) {
                ag.b(TAG, "setAccessibilityEnabled", th);
            }
        }
    }

    @TargetApi(11)
    protected boolean T() {
        try {
            if (Build.VERSION.SDK_INT < 11 || Build.VERSION.SDK_INT >= 17) {
                return false;
            }
            getClass().getMethod("removeJavascriptInterface", String.class).invoke(this, "searchBoxJavaBridge_");
            return true;
        } catch (Exception e) {
            if (ag.ap()) {
                e.printStackTrace();
                return false;
            }
            return false;
        }
    }

    protected void a(Object obj, String str) {
    }

    @Override // android.webkit.WebView
    @Deprecated
    public final void addJavascriptInterface(Object obj, String str) {
        if (Build.VERSION.SDK_INT >= 17) {
            super.addJavascriptInterface(obj, str);
            Log.i(TAG, "注入");
            return;
        }
        String str2 = TAG;
        Log.i(str2, "use mJsCallJavas:" + str);
        String str3 = TAG;
        ag.c(str3, "addJavascriptInterface:" + obj + "   interfaceName:" + str);
        if (this.dG == null) {
            this.dG = new HashMap();
        }
        this.dG.put(str, new ac(obj, str));
        P();
        if (ag.ap()) {
            String str4 = TAG;
            Log.d(str4, "injectJavaScript, addJavascriptInterface.interfaceObj = " + obj + ", interfaceName = " + str);
        }
        a(obj, str);
    }

    public String b(String str, String str2) {
        String format = String.format("__injectFlag_%1$s__", str);
        return "javascript:try{(function(){if(window." + format + "){console.log('" + format + " has been injected');return;}window." + format + "=true;" + str2 + "}())}catch(e){console.warn(e)}";
    }

    @Override // android.webkit.WebView
    public void clearHistory() {
        if (this.dJ) {
            super.clearHistory();
        }
    }

    @Override // android.webkit.WebView
    public void destroy() {
        setVisibility(8);
        if (this.dG != null) {
            this.dG.clear();
        }
        if (this.dH != null) {
            this.dH.clear();
        }
        removeAllViewsInLayout();
        R();
        S();
        if (this.dJ) {
            U();
            ag.c(TAG, "destroy web");
            super.destroy();
        }
    }

    protected void e(String str) {
        if (Build.VERSION.SDK_INT == 16 && getSettings().getJavaScriptEnabled() && this.dK == null && isAccessibilityEnabled()) {
            try {
                try {
                    URLEncoder.encode(String.valueOf(new URI(str)), "utf-8");
                } catch (IllegalArgumentException e) {
                    if ("bad parameter".equals(e.getMessage())) {
                        this.dK = true;
                        setAccessibilityEnabled(false);
                        String str2 = TAG;
                        ag.a(str2, "fixedAccessibilityInjectorExceptionForOnPageFinished.url = " + str, e);
                    }
                }
            } catch (Throwable th) {
                if (ag.ap()) {
                    ag.b(TAG, "fixedAccessibilityInjectorExceptionForOnPageFinished", th);
                }
            }
        }
    }

    @Override // android.webkit.WebView
    public boolean isPrivateBrowsingEnabled() {
        if (Build.VERSION.SDK_INT == 15 && getSettings() == null) {
            return false;
        }
        return super.isPrivateBrowsingEnabled();
    }

    @Override // android.webkit.WebView, android.view.View
    public void setOverScrollMode(int i) {
        try {
            super.setOverScrollMode(i);
        } catch (Throwable th) {
            Pair<Boolean, String> a2 = a(th);
            if (!((Boolean) a2.first).booleanValue()) {
                throw th;
            }
            Toast.makeText(getContext(), (CharSequence) a2.second, 0).show();
            destroy();
        }
    }

    @Override // android.webkit.WebView
    public final void setWebChromeClient(WebChromeClient webChromeClient) {
        a aVar = new a();
        aVar.a(webChromeClient);
        this.dI.setWebChromeClient(webChromeClient);
        super.setWebChromeClient(aVar);
        setWebChromeClientSupport(aVar);
    }

    protected final void setWebChromeClientSupport(WebChromeClient webChromeClient) {
    }

    @Override // android.webkit.WebView
    public final void setWebViewClient(WebViewClient webViewClient) {
        b bVar = new b();
        bVar.c(webViewClient);
        super.setWebViewClient(bVar);
        setWebViewClientSupport(bVar);
    }

    public final void setWebViewClientSupport(WebViewClient webViewClient) {
    }
}
