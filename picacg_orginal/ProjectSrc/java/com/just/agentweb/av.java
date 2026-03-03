package com.just.agentweb;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.webkit.WebView;
import com.just.agentweb.AgentWeb;
/* loaded from: picacg_1.jar:com/just/agentweb/av.class */
public class av implements as {
    private String TAG = getClass().getSimpleName();

    public static av ax() {
        return new av();
    }

    @Override // com.just.agentweb.as
    public void a(ArrayMap<String, Object> arrayMap, AgentWeb.SecurityType securityType) {
        if (securityType != AgentWeb.SecurityType.STRICT_CHECK || c.dv == 2 || Build.VERSION.SDK_INT >= 17) {
            return;
        }
        ag.d(this.TAG, "Give up all inject objects");
        arrayMap.clear();
        System.gc();
    }

    @Override // com.just.agentweb.as
    @TargetApi(11)
    public void j(WebView webView) {
        if (11 > Build.VERSION.SDK_INT || Build.VERSION.SDK_INT > 17) {
            return;
        }
        webView.removeJavascriptInterface("searchBoxJavaBridge_");
        webView.removeJavascriptInterface("accessibility");
        webView.removeJavascriptInterface("accessibilityTraversal");
    }
}
