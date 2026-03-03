package com.picacomic.fregata.holders;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/NewsCellView.class */
public class NewsCellView extends LinearLayout {
    @BindView(2131296618)
    ImageView imageView_image;
    @BindView(2131296961)
    TextView textView_description;
    @BindView(2131296962)
    TextView textView_title;

    public NewsCellView(Context context) {
        super(context);
    }

    public NewsCellView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public NewsCellView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }
}
