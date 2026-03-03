package com.just.agentweb;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
/* loaded from: picacg_1.jar:com/just/agentweb/j.class */
public abstract class j implements z {
    public static final String TAG = "j";
    private WebView db;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(WebView webView) {
        this.db = webView;
    }

    private String b(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (h.c(str)) {
                sb.append(str);
            } else {
                sb.append("\"");
                sb.append(str);
                sb.append("\"");
            }
            if (i != strArr.length - 1) {
                sb.append(" , ");
            }
        }
        return sb.toString();
    }

    private void b(String str, final ValueCallback<String> valueCallback) {
        this.db.evaluateJavascript(str, new ValueCallback<String>() { // from class: com.just.agentweb.j.1
            @Override // android.webkit.ValueCallback
            /* renamed from: g */
            public void onReceiveValue(String str2) {
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(str2);
                }
            }
        });
    }

    private void f(String str) {
        this.db.loadUrl(str);
    }

    public void a(String str, ValueCallback<String> valueCallback) {
        if (Build.VERSION.SDK_INT >= 19) {
            b(str, valueCallback);
        } else {
            f(str);
        }
    }

    public void a(String str, ValueCallback<String> valueCallback, String... strArr) {
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:" + str);
        if (strArr == null || strArr.length == 0) {
            sb.append("()");
        } else {
            sb.append("(");
            sb.append(b(strArr));
            sb.append(")");
        }
        a(sb.toString(), valueCallback);
    }

    @Override // com.just.agentweb.ak
    public void a(String str, String... strArr) {
        a(str, null, strArr);
    }
}
