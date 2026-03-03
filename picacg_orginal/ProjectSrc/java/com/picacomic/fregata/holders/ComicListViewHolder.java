package com.picacomic.fregata.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.b;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicListViewHolder.class */
public class ComicListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "ComicListViewHolder";
    Context context;
    @BindView(2131296512)
    public FrameLayout frameLayout_bl;
    @BindView(2131296513)
    public FrameLayout frameLayout_forbidden;
    @BindView(2131296514)
    public FrameLayout frameLayout_nonChinese;
    @BindView(2131296584)
    public ImageView imageView_image;
    @BindView(2131296661)
    public LinearLayout linearLayout_container;
    @BindView(2131296662)
    public LinearLayout linearLayout_content;
    @BindView(2131296663)
    public LinearLayout linearLayout_filter;
    b sD;
    int targetHeight;
    int targetWidth;
    @BindView(2131296890)
    public TextView textView_author;
    @BindView(2131296892)
    public TextView textView_bl;
    @BindView(2131296891)
    public TextView textView_category;
    @BindView(2131296893)
    public TextView textView_forbidden;
    @BindView(2131296895)
    public TextView textView_likeCount;
    @BindView(2131296896)
    public TextView textView_name;
    @BindView(2131296894)
    public TextView textView_nonChinese;

    public ComicListViewHolder(Context context, View view, b bVar) {
        super(view);
        this.targetWidth = 80;
        this.targetHeight = 120;
        this.context = context;
        ButterKnife.bind(this, view);
        this.sD = bVar;
        this.imageView_image.setOnClickListener(this);
        view.setOnClickListener(this);
        try {
            this.targetWidth = (int) context.getResources().getDimension(2131165386);
            this.targetHeight = (int) context.getResources().getDimension(2131165388);
        } catch (Exception e) {
            this.targetWidth = 80;
            this.targetHeight = 120;
        }
    }

    public void a(ComicListObject comicListObject, String[] strArr) {
        boolean[] zArr = new boolean[8];
        for (int i = 0; i < 8; i++) {
            zArr[i] = false;
        }
        if (comicListObject.getCategories() != null) {
            for (int i2 = 0; i2 < comicListObject.getCategories().size(); i2++) {
                int i3 = 0;
                while (true) {
                    if (i3 >= 8) {
                        break;
                    }
                    if (!zArr[i3] && comicListObject.getCategories().get(i2).equalsIgnoreCase(strArr[i3])) {
                        zArr[i3] = true;
                        break;
                    }
                    i3++;
                }
            }
        }
        Picasso.with(this.context).load(g.b(comicListObject.getThumb())).placeholder(2131231027).resize(this.targetWidth, this.targetHeight).centerCrop().into(this.imageView_image);
        g.a(this.context, this.textView_name, comicListObject.getTitle(), comicListObject.getPagesCount(), comicListObject.isFinished());
        String str = "";
        for (int i4 = 0; i4 < comicListObject.getCategories().size(); i4++) {
            str = str.concat(comicListObject.getCategories().get(i4) + " ");
        }
        this.textView_author.setText(comicListObject.getAuthor() + "");
        this.textView_category.setText(str + "");
        this.textView_likeCount.setText(comicListObject.getLikesCount() + "");
        this.linearLayout_filter.setVisibility(0);
        if (zArr[0]) {
            this.frameLayout_forbidden.setVisibility(0);
        } else {
            this.frameLayout_forbidden.setVisibility(8);
        }
        if (zArr[1]) {
            this.frameLayout_nonChinese.setVisibility(0);
        } else {
            this.frameLayout_nonChinese.setVisibility(8);
        }
        if (zArr[2]) {
            this.frameLayout_bl.setVisibility(0);
        } else {
            this.frameLayout_bl.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == 2131296584) {
            this.sD.I(getAdapterPosition());
        } else {
            this.sD.C(getAdapterPosition());
        }
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
