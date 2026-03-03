package com.picacomic.fregata.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.objects.ThumbnailObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/GameScreenShotViewHolder.class */
public class GameScreenShotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "GameScreenShotViewHolder";
    Context context;
    @BindView(2131296606)
    public ImageView imageView_screenShot;
    k je;

    public GameScreenShotViewHolder(Context context, View view, k kVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.context = context;
        this.je = kVar;
        view.setOnClickListener(this);
    }

    public void a(ThumbnailObject thumbnailObject) {
        Picasso.with(this.context).load(g.b(thumbnailObject)).into(this.imageView_screenShot);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.je.C(getAdapterPosition());
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
