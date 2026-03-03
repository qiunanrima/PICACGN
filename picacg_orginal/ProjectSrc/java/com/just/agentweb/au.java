package com.just.agentweb;

import android.os.Build;
import android.support.v4.util.ArrayMap;
import android.webkit.WebView;
import com.just.agentweb.AgentWeb;
/* loaded from: picacg_1.jar:com/just/agentweb/au.class */
public class au implements at<as> {
    private AgentWeb.SecurityType cJ;
    private WebView db;
    private ArrayMap<String, Object> fk;

    public au(WebView webView, ArrayMap<String, Object> arrayMap, AgentWeb.SecurityType securityType) {
        this.db = webView;
        this.fk = arrayMap;
        this.cJ = securityType;
    }

    @Override // com.just.agentweb.at
    /* renamed from: a */
    public void b(as asVar) {
        if (Build.VERSION.SDK_INT > 11) {
            asVar.j(this.db);
        }
        if (this.fk == null || this.cJ != AgentWeb.SecurityType.STRICT_CHECK || this.fk.isEmpty()) {
            return;
        }
        asVar.a(this.fk, this.cJ);
    }
}
