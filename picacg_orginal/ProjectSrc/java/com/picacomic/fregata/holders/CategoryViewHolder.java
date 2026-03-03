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
import com.picacomic.fregata.objects.CategoryObject;
import com.picacomic.fregata.objects.DefaultCategoryObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CategoryViewHolder.class */
public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "CategoryViewHolder";
    Context context;
    @BindView(2131296573)
    public ImageView imageView_image;
    k je;
    @BindView(2131296848)
    public TextView textView_title;

    public CategoryViewHolder(Context context, View view, k kVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.je = kVar;
        view.setOnClickListener(this);
    }

    public void a(CategoryObject categoryObject) {
        Picasso.with(this.context).load(g.b(categoryObject.getThumb())).placeholder(2131231027).into(this.imageView_image);
        this.textView_title.setText(categoryObject.getTitle());
    }

    public void a(DefaultCategoryObject defaultCategoryObject) {
        this.imageView_image.setImageResource(defaultCategoryObject.getThumbId());
        this.textView_title.setText(defaultCategoryObject.getTitle());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.je.C(getAdapterPosition());
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
