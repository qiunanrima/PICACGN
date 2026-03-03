package com.picacomic.fregata.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicPageSimpleViewHolder.class */
public class ComicPageSimpleViewHolder {
    @BindView(2131296515)
    public RelativeLayout frameLayout_container;
    @BindView(2131296585)
    public ImageView imageView_image;
    @BindView(2131296898)
    public TextView textView_page;

    public ComicPageSimpleViewHolder(View view) {
        ButterKnife.bind(this, view);
    }
}
