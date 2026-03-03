package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/SupportUsAdsGameFragment_ViewBinding.class */
public class SupportUsAdsGameFragment_ViewBinding implements Unbinder {
    private SupportUsAdsGameFragment rN;

    @UiThread
    public SupportUsAdsGameFragment_ViewBinding(SupportUsAdsGameFragment supportUsAdsGameFragment, View view) {
        this.rN = supportUsAdsGameFragment;
        supportUsAdsGameFragment.imageView_game = (ImageView) Utils.findRequiredViewAsType(view, 2131296630, "field 'imageView_game'", ImageView.class);
        supportUsAdsGameFragment.webView_ads = (WebView) Utils.findRequiredViewAsType(view, 2131297063, "field 'webView_ads'", WebView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SupportUsAdsGameFragment supportUsAdsGameFragment = this.rN;
        if (supportUsAdsGameFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.rN = null;
        supportUsAdsGameFragment.imageView_game = null;
        supportUsAdsGameFragment.webView_ads = null;
    }
}
