package com.picacomic.fregata.utils.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/BannerWebview_ViewBinding.class */
public class BannerWebview_ViewBinding implements Unbinder {
    private BannerWebview target;

    @UiThread
    public BannerWebview_ViewBinding(BannerWebview bannerWebview) {
        this(bannerWebview, bannerWebview);
    }

    @UiThread
    public BannerWebview_ViewBinding(BannerWebview bannerWebview, View view) {
        this.target = bannerWebview;
        bannerWebview.relativeLayout_container = (RelativeLayout) Utils.findRequiredViewAsType(view, 2131296771, "field 'relativeLayout_container'", RelativeLayout.class);
        bannerWebview.webView_banner = (WebView) Utils.findRequiredViewAsType(view, 2131297065, "field 'webView_banner'", WebView.class);
        bannerWebview.imageButton_close = (ImageButton) Utils.findRequiredViewAsType(view, 2131296541, "field 'imageButton_close'", ImageButton.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        BannerWebview bannerWebview = this.target;
        if (bannerWebview == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bannerWebview.relativeLayout_container = null;
        bannerWebview.webView_banner = null;
        bannerWebview.imageButton_close = null;
    }
}
