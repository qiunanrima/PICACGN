package com.picacomic.fregata.holders;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/NewsContainerView.class */
public class NewsContainerView extends LinearLayout {
    @BindView(2131296619)
    ImageView imageView_icon;
    @BindView(2131296620)
    ImageView imageView_viewMore;
    @BindView(2131296692)
    LinearLayout linearLayout_content;
    @BindView(2131296964)
    TextView textView_count;
    @BindView(2131296963)
    TextView textView_title;

    public NewsContainerView(Context context) {
        super(context);
        inflate(context, 2131427502, this);
        ButterKnife.bind(this);
    }

    public NewsContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inflate(context, 2131427502, this);
        ButterKnife.bind(this);
    }

    public NewsContainerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflate(context, 2131427502, this);
        ButterKnife.bind(this);
    }

    public TextView getTextView_count() {
        return this.textView_count;
    }

    public TextView getTextView_title() {
        return this.textView_title;
    }

    public void setTextView_count(TextView textView) {
        this.textView_count = textView;
    }

    public void setTextView_title(TextView textView) {
        this.textView_title = textView;
    }
}
