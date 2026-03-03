package com.just.agentweb;

import android.app.Activity;
import android.os.Build;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import java.util.HashSet;
import java.util.Set;
/* loaded from: picacg_1.jar:com/just/agentweb/an.class */
public class an implements u {
    private static final String TAG = "an";
    private WebView db;
    private Set<Pair<Integer, Integer>> eO;
    private View eP = null;
    private ViewGroup eQ = null;
    private WebChromeClient.CustomViewCallback eR;
    private Activity mActivity;

    public an(Activity activity, WebView webView) {
        this.eO = null;
        this.mActivity = activity;
        this.db = webView;
        this.eO = new HashSet();
    }

    @Override // com.just.agentweb.u
    public void onHideCustomView() {
        if (this.eP == null) {
            return;
        }
        if (this.mActivity != null && this.mActivity.getRequestedOrientation() != 1) {
            this.mActivity.setRequestedOrientation(1);
        }
        if (!this.eO.isEmpty()) {
            for (Pair<Integer, Integer> pair : this.eO) {
                this.mActivity.getWindow().setFlags(pair.second.intValue(), pair.first.intValue());
            }
            this.eO.clear();
        }
        this.eP.setVisibility(8);
        if (this.eQ != null && this.eP != null) {
            this.eQ.removeView(this.eP);
        }
        if (this.eQ != null) {
            this.eQ.setVisibility(8);
        }
        if (this.eR != null) {
            this.eR.onCustomViewHidden();
        }
        this.eP = null;
        if (this.db != null) {
            this.db.setVisibility(0);
        }
    }

    @Override // com.just.agentweb.u
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing()) {
            return;
        }
        activity.setRequestedOrientation(0);
        Window window = activity.getWindow();
        if ((window.getAttributes().flags & 128) == 0) {
            Pair<Integer, Integer> pair = new Pair<>(128, 0);
            window.setFlags(128, 128);
            this.eO.add(pair);
        }
        if (Build.VERSION.SDK_INT >= 11 && (window.getAttributes().flags & 16777216) == 0) {
            Pair<Integer, Integer> pair2 = new Pair<>(16777216, 0);
            window.setFlags(16777216, 16777216);
            this.eO.add(pair2);
        }
        if (this.eP != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        if (this.db != null) {
            this.db.setVisibility(8);
        }
        if (this.eQ == null) {
            FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView();
            this.eQ = new FrameLayout(activity);
            this.eQ.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            frameLayout.addView(this.eQ);
        }
        this.eR = customViewCallback;
        ViewGroup viewGroup = this.eQ;
        this.eP = view;
        viewGroup.addView(view);
        this.eQ.setVisibility(0);
    }
}
