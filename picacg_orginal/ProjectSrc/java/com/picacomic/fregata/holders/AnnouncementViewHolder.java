package com.picacomic.fregata.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.objects.AnnouncementObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AnnouncementViewHolder.class */
public class AnnouncementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "AnnouncementViewHolder";
    Context context;
    @BindView(2131296571)
    public ImageView imageView_image;
    k je;
    @BindView(2131296835)
    public TextView textView_description;
    @BindView(2131296836)
    public TextView textView_title;

    public AnnouncementViewHolder(Context context, View view, k kVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.je = kVar;
        this.context = context;
        view.setOnClickListener(this);
    }

    public void a(AnnouncementObject announcementObject) {
        if (announcementObject != null) {
            TextView textView = this.textView_title;
            textView.setText(announcementObject.getTitle() + "");
            TextView textView2 = this.textView_description;
            textView2.setText(announcementObject.getContent() + "");
            Picasso.with(this.context).load(g.b(announcementObject.getThumb())).into(this.imageView_image);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.je.C(getAdapterPosition());
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
