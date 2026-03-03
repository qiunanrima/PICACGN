package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.utils.g;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AdvertisementListViewHolder.class */
public class AdvertisementListViewHolder extends RecyclerView.ViewHolder {
    @BindView(2131297060)
    public WebView webView_ads;

    public AdvertisementListViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        g.k(this.webView_ads);
    }
}
