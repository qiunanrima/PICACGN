package com.picacomic.fregata.holders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.ComicThumbImageView;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/SingleImageTextView.class */
public class SingleImageTextView extends LinearLayout {
    @BindView(2131296629)
    ComicThumbImageView imageView_image;
    int targetHeight;
    int targetWidth;
    @BindView(2131297009)
    TextView textView_title;

    public SingleImageTextView(Context context) {
        super(context);
        this.targetWidth = 80;
        this.targetHeight = 120;
    }

    public SingleImageTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.targetWidth = 80;
        this.targetHeight = 120;
    }

    public SingleImageTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.targetWidth = 80;
        this.targetHeight = 120;
    }

    public SingleImageTextView(Context context, ComicListObject comicListObject, View.OnClickListener onClickListener) {
        super(context);
        this.targetWidth = 80;
        this.targetHeight = 120;
        inflate(context, 2131427488, this);
        ButterKnife.bind(this);
        Picasso.with(context).load(g.b(comicListObject.getThumb())).resize(this.targetWidth, this.targetHeight).centerCrop().into(this.imageView_image);
        TextView textView = this.textView_title;
        textView.setText(comicListObject.getTitle() + "");
        setOnClickListener(onClickListener);
    }
}
