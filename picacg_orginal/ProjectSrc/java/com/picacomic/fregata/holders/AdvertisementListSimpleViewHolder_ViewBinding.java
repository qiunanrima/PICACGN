package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AdvertisementListSimpleViewHolder_ViewBinding.class */
public class AdvertisementListSimpleViewHolder_ViewBinding implements Unbinder {
    private AdvertisementListSimpleViewHolder sc;

    @UiThread
    public AdvertisementListSimpleViewHolder_ViewBinding(AdvertisementListSimpleViewHolder advertisementListSimpleViewHolder, View view) {
        this.sc = advertisementListSimpleViewHolder;
        advertisementListSimpleViewHolder.webView_ads = (WebView) Utils.findRequiredViewAsType(view, 2131297060, "field 'webView_ads'", WebView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AdvertisementListSimpleViewHolder advertisementListSimpleViewHolder = this.sc;
        if (advertisementListSimpleViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sc = null;
        advertisementListSimpleViewHolder.webView_ads = null;
    }
}
