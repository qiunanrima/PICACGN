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
import com.picacomic.fregata.objects.GameListObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/GameListViewHolder.class */
public class GameListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "GameListViewHolder";
    Context context;
    @BindView(2131296607)
    public ImageView imageView_adult;
    @BindView(2131296608)
    public ImageView imageView_banner;
    @BindView(2131296609)
    public ImageView imageView_recommend;
    public k je;
    @BindView(2131296946)
    public TextView textView_publisher;
    @BindView(2131296947)
    public TextView textView_title;

    public GameListViewHolder(Context context, View view, k kVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.context = context;
        this.je = kVar;
        view.setOnClickListener(this);
    }

    public void a(GameListObject gameListObject) {
        Picasso.with(this.context).load(g.b(gameListObject.getIcon())).placeholder(2131231027).into(this.imageView_banner);
        TextView textView = this.textView_title;
        textView.setText(gameListObject.getTitle() + "");
        TextView textView2 = this.textView_publisher;
        textView2.setText(gameListObject.getPublisher() + "");
        if (gameListObject.isAdult()) {
            this.imageView_adult.setVisibility(0);
        } else {
            this.imageView_adult.setVisibility(8);
        }
        if (gameListObject.isSuggest()) {
            this.imageView_recommend.setVisibility(0);
        } else {
            this.imageView_recommend.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.je.C(getAdapterPosition());
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
