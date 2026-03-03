package com.just.agentweb;

import android.webkit.WebView;
import com.just.agentweb.AgentWeb;
import java.util.Map;
/* loaded from: picacg_1.jar:com/just/agentweb/af.class */
public class af extends ab {
    private static final String TAG = "af";
    private AgentWeb.SecurityType cJ;
    private WebView db;

    af(WebView webView, AgentWeb.SecurityType securityType) {
        super(securityType);
        this.db = webView;
        this.cJ = securityType;
    }

    private ae a(String str, Object obj) {
        String str2 = TAG;
        ag.c(str2, "k:" + str + "  v:" + obj);
        this.db.addJavascriptInterface(obj, str);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static af a(WebView webView, AgentWeb.SecurityType securityType) {
        return new af(webView, securityType);
    }

    @Override // com.just.agentweb.ae
    public ae a(Map<String, Object> map) {
        if (!an()) {
            ag.d(TAG, "The injected object is not safe, give up injection");
            return this;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (!a(value)) {
                throw new JsInterfaceObjectException("This object has not offer method javascript to call ,please check addJavascriptInterface annotation was be added");
            }
            a(entry.getKey(), value);
        }
        return this;
    }
}
