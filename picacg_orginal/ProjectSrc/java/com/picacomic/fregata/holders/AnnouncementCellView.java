package com.picacomic.fregata.holders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.objects.AnnouncementObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AnnouncementCellView.class */
public class AnnouncementCellView extends LinearLayout {
    @BindView(2131296571)
    ImageView imageView_image;
    @BindView(2131296835)
    TextView textView_description;
    @BindView(2131296836)
    TextView textView_title;

    public AnnouncementCellView(Context context) {
        super(context);
    }

    public AnnouncementCellView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AnnouncementCellView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public AnnouncementCellView(Context context, AnnouncementObject announcementObject, View.OnClickListener onClickListener) {
        super(context);
        inflate(context, 2131427441, this);
        ButterKnife.bind(this);
        Picasso.with(context).load(g.b(announcementObject.getThumb())).into(this.imageView_image);
        TextView textView = this.textView_title;
        textView.setText(announcementObject.getTitle() + "");
        TextView textView2 = this.textView_description;
        textView2.setText(announcementObject.getContent() + "");
        setOnClickListener(onClickListener);
    }
}
