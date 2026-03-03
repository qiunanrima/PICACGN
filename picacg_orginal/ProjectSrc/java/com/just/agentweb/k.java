package com.just.agentweb;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import com.just.agentweb.ActionActivity;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;
/* loaded from: picacg_1.jar:com/just/agentweb/k.class */
public class k extends ah {
    private String TAG;
    private u cO;
    private aj cQ;
    private ActionActivity.b ct;
    private x cy;
    private WebChromeClient cz;
    private boolean dP;
    private String dQ;
    private GeolocationPermissions.Callback dR;
    private WeakReference<b> dS;
    private WebView db;
    private WeakReference<Activity> dz;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(Activity activity, x xVar, WebChromeClient webChromeClient, @Nullable u uVar, aj ajVar, WebView webView) {
        super(webChromeClient);
        this.dz = null;
        this.TAG = k.class.getSimpleName();
        boolean z = false;
        this.dP = false;
        this.dQ = null;
        this.dR = null;
        this.dS = null;
        this.ct = new ActionActivity.b() { // from class: com.just.agentweb.k.1
            @Override // com.just.agentweb.ActionActivity.b
            public void a(@NonNull String[] strArr, @NonNull int[] iArr, Bundle bundle) {
                if (bundle.getInt("KEY_FROM_INTENTION") == 96) {
                    boolean a = h.a((Context) k.this.dz.get(), strArr);
                    if (k.this.dR != null) {
                        if (a) {
                            k.this.dR.invoke(k.this.dQ, true, false);
                        } else {
                            k.this.dR.invoke(k.this.dQ, false, false);
                        }
                        k.this.dR = null;
                        k.this.dQ = null;
                    }
                    if (a || k.this.dS.get() == null) {
                        return;
                    }
                    ((b) k.this.dS.get()).a(e.dC, "Location", "Location");
                }
            }
        };
        this.cy = xVar;
        this.dP = webChromeClient != null ? true : z;
        this.cz = webChromeClient;
        this.dz = new WeakReference<>(activity);
        this.cO = uVar;
        this.cQ = ajVar;
        this.db = webView;
        this.dS = new WeakReference<>(h.d(webView));
    }

    private void a(String str, GeolocationPermissions.Callback callback) {
        if (this.cQ != null && this.cQ.a(this.db.getUrl(), e.dC, "location")) {
            callback.invoke(str, false, false);
            return;
        }
        Activity activity = this.dz.get();
        if (activity == null) {
            callback.invoke(str, false, false);
            return;
        }
        List<String> a = h.a(activity, e.dC);
        if (a.isEmpty()) {
            ag.c(this.TAG, "onGeolocationPermissionsShowPromptInternal:true");
            callback.invoke(str, true, false);
            return;
        }
        Action a2 = Action.a((String[]) a.toArray(new String[0]));
        a2.c(96);
        ActionActivity.a(this.ct);
        this.dR = callback;
        this.dQ = str;
        ActionActivity.a(activity, a2);
    }

    private boolean a(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        String str = this.TAG;
        ag.c(str, "fileChooserParams:" + fileChooserParams.getAcceptTypes() + "  getTitle:" + ((Object) fileChooserParams.getTitle()) + " accept:" + Arrays.toString(fileChooserParams.getAcceptTypes()) + " length:" + fileChooserParams.getAcceptTypes().length + "  :" + fileChooserParams.isCaptureEnabled() + "  " + fileChooserParams.getFilenameHint() + "  intent:" + fileChooserParams.createIntent().toString() + "   mode:" + fileChooserParams.getMode());
        Activity activity = this.dz.get();
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        return h.a(activity, this.db, valueCallback, fileChooserParams, this.cQ, (ValueCallback) null, (String) null, (Handler.Callback) null);
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        super.onConsoleMessage(consoleMessage);
        return true;
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        if (h.a(this.cz, "onExceededDatabaseQuota", "android.webkit.WebChromeClient.onExceededDatabaseQuota", String.class, String.class, Long.TYPE, Long.TYPE, Long.TYPE, WebStorage.QuotaUpdater.class)) {
            super.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
        } else {
            quotaUpdater.updateQuota(j3 * 2);
        }
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt();
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        if (h.a(this.cz, "onGeolocationPermissionsShowPrompt", "public void android.webkit.WebChromeClient.onGeolocationPermissionsShowPrompt", String.class, GeolocationPermissions.Callback.class)) {
            super.onGeolocationPermissionsShowPrompt(str, callback);
        } else {
            a(str, callback);
        }
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onHideCustomView() {
        if (h.a(this.cz, "onHideCustomView", "android.webkit.WebChromeClient.onHideCustomView", new Class[0])) {
            super.onHideCustomView();
        } else if (this.cO != null) {
            this.cO.onHideCustomView();
        }
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        if (h.a(this.cz, "onJsAlert", "public boolean android.webkit.WebChromeClient.onJsAlert", WebView.class, String.class, String.class, JsResult.class)) {
            return super.onJsAlert(webView, str, str2, jsResult);
        }
        if (this.dS.get() != null) {
            this.dS.get().a(webView, str, str2);
        }
        jsResult.confirm();
        return true;
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        if (h.a(this.cz, "onJsConfirm", "public boolean android.webkit.WebChromeClient.onJsConfirm", WebView.class, String.class, String.class, JsResult.class)) {
            return super.onJsConfirm(webView, str, str2, jsResult);
        }
        if (this.dS.get() != null) {
            this.dS.get().a(webView, str, str2, jsResult);
            return true;
        }
        return true;
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        try {
            if (h.a(this.cz, "onJsPrompt", "public boolean android.webkit.WebChromeClient.onJsPrompt", WebView.class, String.class, String.class, String.class, JsPromptResult.class)) {
                return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            }
            if (this.dS.get() != null) {
                this.dS.get().a(this.db, str, str2, str3, jsPromptResult);
                return true;
            }
            return true;
        } catch (Exception e) {
            if (ag.ap()) {
                e.printStackTrace();
                return true;
            }
            return true;
        }
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (this.cy != null) {
            this.cy.a(webView, i);
        }
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        if (h.a(this.cz, "onReachedMaxAppCacheSize", "android.webkit.WebChromeClient.onReachedMaxAppCacheSize", Long.TYPE, Long.TYPE, WebStorage.QuotaUpdater.class)) {
            super.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
        } else {
            quotaUpdater.updateQuota(j * 2);
        }
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onReceivedIcon(WebView webView, Bitmap bitmap) {
        super.onReceivedIcon(webView, bitmap);
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        if (this.dP) {
            super.onReceivedTitle(webView, str);
        }
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        if (h.a(this.cz, "onShowCustomView", "android.webkit.WebChromeClient.onShowCustomView", View.class, WebChromeClient.CustomViewCallback.class)) {
            super.onShowCustomView(view, customViewCallback);
        } else if (this.cO != null) {
            this.cO.onShowCustomView(view, customViewCallback);
        }
    }

    @Override // com.just.agentweb.ao, android.webkit.WebChromeClient
    @RequiresApi(api = 21)
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        ag.c(this.TAG, "openFileChooser>=5.0");
        return h.a(this.cz, "onShowFileChooser", "android.webkit.WebChromeClient.onShowFileChooser", WebView.class, ValueCallback.class, WebChromeClient.FileChooserParams.class) ? super.onShowFileChooser(webView, valueCallback, fileChooserParams) : a(webView, valueCallback, fileChooserParams);
    }
}
