package com.just.agentweb;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebView;
import android.widget.EditText;
import com.just.agentweb.al;
/* loaded from: picacg_1.jar:com/just/agentweb/m.class */
public class m extends b {
    private WebParentLayout cm;
    protected AlertDialog dU;
    private Activity mActivity;
    private JsPromptResult dV = null;
    private JsResult dW = null;
    private AlertDialog dX = null;
    private AlertDialog dY = null;
    private Resources mResources = null;

    private void a(final Handler.Callback callback) {
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        new AlertDialog.Builder(activity).setTitle(this.mResources.getString(al.d.agentweb_tips)).setMessage(this.mResources.getString(al.d.agentweb_honeycomblow)).setNegativeButton(this.mResources.getString(al.d.agentweb_download), new DialogInterface.OnClickListener() { // from class: com.just.agentweb.m.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
                if (callback != null) {
                    callback.handleMessage(Message.obtain());
                }
            }
        }).setPositiveButton(this.mResources.getString(al.d.agentweb_cancel), new DialogInterface.OnClickListener() { // from class: com.just.agentweb.m.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (dialogInterface != null) {
                    dialogInterface.dismiss();
                }
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JsResult jsResult) {
        if (jsResult != null) {
            jsResult.cancel();
        }
    }

    private void a(String str, JsResult jsResult) {
        String str2 = this.TAG;
        ag.c(str2, "activity:" + this.mActivity.hashCode() + "  ");
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            a(jsResult);
            return;
        }
        if (this.dU == null) {
            this.dU = new AlertDialog.Builder(activity).setMessage(str).setNegativeButton(17039360, new DialogInterface.OnClickListener() { // from class: com.just.agentweb.m.10
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    m.this.a(m.this.dU);
                    m.this.a(m.this.dW);
                }
            }).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.just.agentweb.m.9
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    m.this.a(m.this.dU);
                    if (m.this.dW != null) {
                        m.this.dW.confirm();
                    }
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.just.agentweb.m.8
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                    m.this.a(m.this.dW);
                }
            }).create();
        }
        this.dU.setMessage(str);
        this.dW = jsResult;
        this.dU.show();
    }

    private void a(String str, String str2, JsPromptResult jsPromptResult) {
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            jsPromptResult.cancel();
            return;
        }
        if (this.dX == null) {
            final EditText editText = new EditText(activity);
            editText.setText(str2);
            this.dX = new AlertDialog.Builder(activity).setView(editText).setTitle(str).setNegativeButton(17039360, new DialogInterface.OnClickListener() { // from class: com.just.agentweb.m.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    m.this.a(m.this.dX);
                    m.this.a(m.this.dV);
                }
            }).setPositiveButton(17039370, new DialogInterface.OnClickListener() { // from class: com.just.agentweb.m.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    m.this.a(m.this.dX);
                    if (m.this.dV != null) {
                        m.this.dV.confirm(editText.getText().toString());
                    }
                }
            }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.just.agentweb.m.2
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    dialogInterface.dismiss();
                    m.this.a(m.this.dV);
                }
            }).create();
        }
        this.dV = jsPromptResult;
        this.dX.show();
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, int i, String str, String str2) {
        String str3 = this.TAG;
        ag.c(str3, "mWebParentLayout onMainFrameError:" + this.cm);
        if (this.cm != null) {
            this.cm.at();
        }
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, String str, final Handler.Callback callback) {
        ag.c(this.TAG, "onOpenPagePrompt");
        if (this.dY == null) {
            this.dY = new AlertDialog.Builder(this.mActivity).setMessage(this.mResources.getString(al.d.agentweb_leave_app_and_go_other_page, h.j(this.mActivity))).setTitle(this.mResources.getString(al.d.agentweb_tips)).setNegativeButton(17039360, new DialogInterface.OnClickListener() { // from class: com.just.agentweb.m.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callback != null) {
                        callback.handleMessage(Message.obtain((Handler) null, -1));
                    }
                }
            }).setPositiveButton(this.mResources.getString(al.d.agentweb_leave), new DialogInterface.OnClickListener() { // from class: com.just.agentweb.m.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (callback != null) {
                        callback.handleMessage(Message.obtain((Handler) null, 1));
                    }
                }
            }).create();
        }
        this.dY.show();
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, String str, String str2) {
        h.a(webView.getContext().getApplicationContext(), str2);
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, String str, String str2, JsResult jsResult) {
        a(str2, jsResult);
    }

    @Override // com.just.agentweb.b
    public void a(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        a(str2, str3, jsPromptResult);
    }

    @Override // com.just.agentweb.b
    public void a(String str, Handler.Callback callback) {
        a(callback);
    }

    @Override // com.just.agentweb.b
    public void a(String str, String str2) {
        if (TextUtils.isEmpty(str2) || !str2.contains("performDownload")) {
            h.a(this.mActivity.getApplicationContext(), str);
        }
    }

    @Override // com.just.agentweb.b
    public void a(String[] strArr, String str, String str2) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.just.agentweb.b
    public void b(WebParentLayout webParentLayout, Activity activity) {
        this.mActivity = activity;
        this.cm = webParentLayout;
        this.mResources = this.mActivity.getResources();
    }

    @Override // com.just.agentweb.b
    public void q() {
        if (this.cm != null) {
            this.cm.av();
        }
    }
}
