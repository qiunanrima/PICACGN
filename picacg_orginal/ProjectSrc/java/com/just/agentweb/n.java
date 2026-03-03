package com.just.agentweb;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.just.agentweb.al;
/* loaded from: picacg_1.jar:com/just/agentweb/n.class */
public class n implements ap {
    private static final String TAG = "n";
    private ViewGroup.LayoutParams cY;
    private WebView db;
    private boolean et;
    private BaseIndicatorView eu;
    private boolean ev;
    private w ew;
    private i ex;
    private FrameLayout ey;
    private Activity mActivity;
    private int mColor;
    private int mHeight;
    private int mIndex;
    private ViewGroup mViewGroup;

    /* JADX INFO: Access modifiers changed from: protected */
    public n(@NonNull Activity activity, @Nullable ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams, int i, int i2, int i3, WebView webView, w wVar) {
        this.cY = null;
        this.mColor = -1;
        this.ev = false;
        this.db = null;
        this.ey = null;
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        this.et = true;
        this.mIndex = i;
        this.mColor = i2;
        this.cY = layoutParams;
        this.mHeight = i3;
        this.db = webView;
        this.ew = wVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public n(@NonNull Activity activity, @Nullable ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams, int i, @Nullable WebView webView, w wVar) {
        this.cY = null;
        this.mColor = -1;
        this.ev = false;
        this.db = null;
        this.ey = null;
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        this.et = false;
        this.mIndex = i;
        this.cY = layoutParams;
        this.db = webView;
        this.ew = wVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public n(@NonNull Activity activity, @Nullable ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams, int i, BaseIndicatorView baseIndicatorView, WebView webView, w wVar) {
        this.cY = null;
        this.mColor = -1;
        this.ev = false;
        this.db = null;
        this.ey = null;
        this.mActivity = activity;
        this.mViewGroup = viewGroup;
        this.et = false;
        this.mIndex = i;
        this.cY = layoutParams;
        this.eu = baseIndicatorView;
        this.db = webView;
        this.ew = wVar;
    }

    private ViewGroup ad() {
        WebView ae;
        Activity activity = this.mActivity;
        WebParentLayout webParentLayout = new WebParentLayout(activity);
        webParentLayout.setId(al.b.web_parent_layout_id);
        webParentLayout.setBackgroundColor(-1);
        if (this.ew == null) {
            ae = af();
            this.db = ae;
        } else {
            ae = ae();
        }
        webParentLayout.addView(ae, new FrameLayout.LayoutParams(-1, -1));
        webParentLayout.i(this.db);
        String str = TAG;
        ag.c(str, "  instanceof  AgentWebView:" + (this.db instanceof AgentWebView));
        if (this.db instanceof AgentWebView) {
            c.dv = 2;
        }
        ViewStub viewStub = new ViewStub(activity);
        viewStub.setId(al.b.mainframe_error_viewsub_id);
        webParentLayout.addView(viewStub, new FrameLayout.LayoutParams(-1, -1));
        if (this.et) {
            WebIndicator webIndicator = new WebIndicator(activity);
            FrameLayout.LayoutParams layoutParams = this.mHeight > 0 ? new FrameLayout.LayoutParams(-2, h.a(activity, this.mHeight)) : webIndicator.Y();
            if (this.mColor != -1) {
                webIndicator.setColor(this.mColor);
            }
            layoutParams.gravity = 48;
            this.ex = webIndicator;
            webParentLayout.addView(webIndicator, layoutParams);
            webIndicator.setVisibility(8);
        } else if (!this.et && this.eu != null) {
            BaseIndicatorView baseIndicatorView = this.eu;
            this.ex = baseIndicatorView;
            webParentLayout.addView(baseIndicatorView, this.eu.Y());
            this.eu.setVisibility(8);
        }
        return webParentLayout;
    }

    private View ae() {
        WebView webView = this.ew.getWebView();
        if (webView == null) {
            webView = af();
            this.ew.aj().addView(webView, -1, -1);
            ag.c(TAG, "add webview");
        } else {
            c.dv = 3;
        }
        this.db = webView;
        return this.ew.aj();
    }

    private WebView af() {
        AgentWebView webView;
        if (this.db != null) {
            webView = this.db;
            c.dv = 3;
        } else if (c.du) {
            webView = new AgentWebView(this.mActivity);
            c.dv = 2;
        } else {
            webView = new WebView(this.mActivity);
            c.dv = 1;
        }
        return webView;
    }

    @Override // com.just.agentweb.ap
    /* renamed from: ab */
    public n ah() {
        if (this.ev) {
            return this;
        }
        this.ev = true;
        ViewGroup viewGroup = this.mViewGroup;
        if (viewGroup == null) {
            FrameLayout frameLayout = (FrameLayout) ad();
            this.ey = frameLayout;
            this.mActivity.setContentView(frameLayout);
        } else if (this.mIndex == -1) {
            FrameLayout frameLayout2 = (FrameLayout) ad();
            this.ey = frameLayout2;
            viewGroup.addView(frameLayout2, this.cY);
        } else {
            FrameLayout frameLayout3 = (FrameLayout) ad();
            this.ey = frameLayout3;
            viewGroup.addView(frameLayout3, this.mIndex, this.cY);
        }
        return this;
    }

    @Override // com.just.agentweb.ap
    public FrameLayout ac() {
        return this.ey;
    }

    @Override // com.just.agentweb.v
    public i ag() {
        return this.ex;
    }

    @Override // com.just.agentweb.ap
    public WebView getWebView() {
        return this.db;
    }
}
