package com.picacomic.fregata.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.b;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicRecommendationViewHolder.class */
public class ComicRecommendationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "ComicRecommendationViewHolder";
    Context context;
    @BindView(2131296628)
    ImageView imageView;
    b jq;
    ComicListObject nn;
    @BindView(2131296987)
    TextView textView;

    public ComicRecommendationViewHolder(Context context, View view, b bVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.context = context;
        view.setOnClickListener(this);
        this.jq = bVar;
    }

    public void b(ComicListObject comicListObject) {
        this.nn = comicListObject;
        if (this.nn != null) {
            Picasso.with(this.context).load(g.b(this.nn.getThumb())).placeholder(2131231027).into(this.imageView);
            g.a(this.context, this.textView, this.nn.getTitle(), this.nn.getPagesCount(), this.nn.isFinished());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.jq != null) {
            this.jq.C(getAdapterPosition());
        }
    }
}
