package com.picacomic.fregata.holders;

import android.view.View;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.utils.g;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AdvertisementListSimpleViewHolder.class */
public class AdvertisementListSimpleViewHolder {
    @BindView(2131297060)
    public WebView webView_ads;

    public AdvertisementListSimpleViewHolder(View view) {
        ButterKnife.bind(this, view);
        g.k(this.webView_ads);
    }
}
