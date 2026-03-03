package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.e;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CommentTopReplacementViewHolder.class */
public class CommentTopReplacementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "CommentTopReplacementViewHolder";
    e sI;
    @BindView(2131296908)
    public TextView textView_floor;

    public CommentTopReplacementViewHolder(View view, e eVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.sI = eVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
