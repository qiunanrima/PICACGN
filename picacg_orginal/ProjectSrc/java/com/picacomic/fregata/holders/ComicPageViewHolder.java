package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicPageViewHolder.class */
public class ComicPageViewHolder extends RecyclerView.ViewHolder {
    @BindView(2131296515)
    public FrameLayout frameLayout_container;
    @BindView(2131296585)
    public ImageView imageView_image;
    @BindView(2131296898)
    public TextView textView_page;

    public ComicPageViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
