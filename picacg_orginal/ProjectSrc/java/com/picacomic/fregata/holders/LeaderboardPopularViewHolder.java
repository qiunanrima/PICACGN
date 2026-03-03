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
import com.picacomic.fregata.objects.LeaderboardComicListObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/LeaderboardPopularViewHolder.class */
public class LeaderboardPopularViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "LeaderboardPopularViewHolder";
    Context context;
    @BindView(2131296614)
    public ImageView imageView_image;
    @BindView(2131296615)
    public ImageView imageView_order;
    k je;
    @BindView(2131296954)
    public TextView textView_author;
    @BindView(2131296955)
    public TextView textView_category;
    @BindView(2131296956)
    public TextView textView_name;
    @BindView(2131296957)
    public TextView textView_order;
    @BindView(2131296958)
    public TextView textView_viewCount;
    @BindView(2131296959)
    public TextView textView_viewCountTitle;

    public LeaderboardPopularViewHolder(Context context, View view, k kVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.context = context;
        this.je = kVar;
        view.setOnClickListener(this);
    }

    public void a(LeaderboardComicListObject leaderboardComicListObject, int i, String str) {
        TextView textView = this.textView_order;
        textView.setText((i + 1) + "");
        Picasso.with(this.context).load(g.b(leaderboardComicListObject.getThumb())).into(this.imageView_image);
        TextView textView2 = this.textView_name;
        textView2.setText(leaderboardComicListObject.getTitle() + "");
        TextView textView3 = this.textView_author;
        textView3.setText(leaderboardComicListObject.getAuthor() + "");
        this.textView_category.setText(leaderboardComicListObject.getCategories().toString());
        if (str != null && str.equals("D7")) {
            this.textView_viewCountTitle.setText(2131689812);
        } else if (str == null || !str.equals("D30")) {
            this.textView_viewCountTitle.setText(2131689810);
        } else {
            this.textView_viewCountTitle.setText(2131689811);
        }
        TextView textView4 = this.textView_viewCount;
        textView4.setText(leaderboardComicListObject.getLeaderboardCount() + "");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.je.C(getAdapterPosition());
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
