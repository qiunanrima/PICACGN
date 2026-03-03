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
import com.picacomic.fregata.objects.LeaderboardKnightObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/LeaderboardKnightViewHolder.class */
public class LeaderboardKnightViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "LeaderboardKnightViewHolder";
    Context context;
    @BindView(2131296611)
    public ImageView imageView_avatar;
    @BindView(2131296613)
    public ImageView imageView_characterIcon;
    @BindView(2131296612)
    public ImageView imageView_order;
    k je;
    @BindView(2131296949)
    public TextView textView_comic;
    @BindView(2131296950)
    public TextView textView_level;
    @BindView(2131296951)
    public TextView textView_name;
    @BindView(2131296952)
    public TextView textView_order;
    @BindView(2131296953)
    public TextView textView_title;

    public LeaderboardKnightViewHolder(Context context, View view, k kVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.context = context;
        this.je = kVar;
        view.setOnClickListener(this);
    }

    public void a(LeaderboardKnightObject leaderboardKnightObject, int i) {
        TextView textView = this.textView_order;
        textView.setText((i + 1) + "");
        TextView textView2 = this.textView_level;
        textView2.setText(leaderboardKnightObject.getLevel() + "");
        TextView textView3 = this.textView_name;
        textView3.setText(leaderboardKnightObject.getName() + "");
        TextView textView4 = this.textView_comic;
        textView4.setText(leaderboardKnightObject.getComicsUploaded() + "");
        if (leaderboardKnightObject.getCharacter() == null || leaderboardKnightObject.getCharacter().equalsIgnoreCase("")) {
            this.imageView_characterIcon.setVisibility(8);
        } else {
            Picasso.with(this.context).load(leaderboardKnightObject.getCharacter()).into(this.imageView_characterIcon);
            this.imageView_characterIcon.setVisibility(0);
        }
        Picasso.with(this.context).load(g.b(leaderboardKnightObject.getAvatar())).placeholder(2131231027).into(this.imageView_avatar);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.je.C(getAdapterPosition());
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
