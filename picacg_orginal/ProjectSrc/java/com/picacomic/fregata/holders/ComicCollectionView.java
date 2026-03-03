package com.picacomic.fregata.holders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.objects.ComicListObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicCollectionView.class */
public class ComicCollectionView extends LinearLayout {
    @BindView(2131296579)
    ImageView imageView_icon;
    @BindView(2131296656)
    LinearLayout linearLayout_content;
    @BindView(2131296876)
    TextView textView_count;
    @BindView(2131296877)
    TextView textView_title;

    public ComicCollectionView(Context context) {
        super(context);
        inflate(context, 2131427495, this);
        ButterKnife.bind(this);
    }

    public ComicCollectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inflate(context, 2131427495, this);
        ButterKnife.bind(this);
    }

    public ComicCollectionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflate(context, 2131427495, this);
        ButterKnife.bind(this);
    }

    public ComicCollectionView(Context context, ArrayList<ComicListObject> arrayList, int i, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context);
        inflate(context, 2131427495, this);
        ButterKnife.bind(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        int size = arrayList.size() > 4 ? 4 : arrayList.size();
        if (onClickListener2 != null) {
            this.textView_count.setOnClickListener(onClickListener2);
        } else {
            this.textView_count.setVisibility(8);
        }
        for (int i2 = 0; i2 < size; i2++) {
            SingleImageTextView singleImageTextView = new SingleImageTextView(context, arrayList.get(i2), onClickListener);
            singleImageTextView.setLayoutParams(layoutParams);
            singleImageTextView.setTag(Integer.valueOf(i2 + i));
            this.linearLayout_content.addView(singleImageTextView);
        }
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
