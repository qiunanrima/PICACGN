package com.just.agentweb;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.just.agentweb.al;
/* loaded from: picacg_1.jar:com/just/agentweb/WebParentLayout.class */
public class WebParentLayout extends FrameLayout {
    private static final String TAG = "WebParentLayout";
    private WebView db;
    private b dd;
    private View di;
    @LayoutRes
    private int fe;
    @IdRes
    private int ff;
    private FrameLayout fg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebParentLayout(@NonNull Context context) {
        this(context, null);
        ag.c(TAG, TAG);
    }

    WebParentLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    WebParentLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.dd = null;
        this.ff = -1;
        this.fg = null;
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("WebParentLayout context must be activity or activity sub class .");
        }
        this.fe = al.c.agentweb_error_page;
    }

    private void au() {
        final FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setBackgroundColor(-1);
        frameLayout.setId(al.b.mainframe_error_container_id);
        if (this.di == null) {
            LayoutInflater from = LayoutInflater.from(getContext());
            String str = TAG;
            ag.c(str, "mErrorLayoutRes:" + this.fe);
            from.inflate(this.fe, (ViewGroup) frameLayout, true);
        } else {
            frameLayout.addView(this.di);
        }
        View view = (ViewStub) findViewById(al.b.mainframe_error_viewsub_id);
        int indexOfChild = indexOfChild(view);
        removeViewInLayout(view);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            this.fg = frameLayout;
            addView(frameLayout, indexOfChild, layoutParams);
        } else {
            this.fg = frameLayout;
            addView(frameLayout, indexOfChild);
        }
        frameLayout.setVisibility(0);
        if (this.ff != -1) {
            final View findViewById = frameLayout.findViewById(this.ff);
            if (findViewById != null) {
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.just.agentweb.WebParentLayout.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        if (WebParentLayout.this.getWebView() != null) {
                            findViewById.setClickable(false);
                            WebParentLayout.this.getWebView().reload();
                        }
                    }
                });
                return;
            } else if (ag.ap()) {
                ag.d(TAG, "ClickView is null , cannot bind accurate view to refresh or reload .");
            }
        }
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.just.agentweb.WebParentLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (WebParentLayout.this.getWebView() != null) {
                    frameLayout.setClickable(false);
                    WebParentLayout.this.getWebView().reload();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(b bVar) {
        this.dd = bVar;
        this.dd.a(this, (Activity) getContext());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void at() {
        View findViewById;
        FrameLayout frameLayout = this.fg;
        if (frameLayout != null) {
            frameLayout.setVisibility(0);
        } else {
            au();
            frameLayout = this.fg;
        }
        if (this.ff == -1 || (findViewById = frameLayout.findViewById(this.ff)) == null) {
            frameLayout.setClickable(true);
        } else {
            findViewById.setClickable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void av() {
        View findViewById = findViewById(al.b.mainframe_error_container_id);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
    }

    public b aw() {
        return this.dd;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void e(@LayoutRes int i, @IdRes int i2) {
        this.ff = i2;
        if (this.ff <= 0) {
            this.ff = -1;
        }
        this.fe = i;
        if (this.fe <= 0) {
            this.fe = al.c.agentweb_error_page;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebView getWebView() {
        return this.db;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void i(WebView webView) {
        if (this.db == null) {
            this.db = webView;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setErrorView(@NonNull View view) {
        this.di = view;
    }
}
