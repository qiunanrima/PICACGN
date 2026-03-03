package com.picacomic.fregata.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.objects.LatestApplicationObject;
import com.picacomic.fregata.utils.g;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ApkVersionListViewHolder.class */
public class ApkVersionListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "ApkVersionListViewHolder";
    Context context;
    k je;
    @BindView(2131296842)
    public TextView textView_content;
    @BindView(2131296843)
    public TextView textView_timestamp;
    @BindView(2131296845)
    public TextView textView_version;

    public ApkVersionListViewHolder(Context context, View view, k kVar) {
        super(view);
        this.context = context;
        ButterKnife.bind(this, view);
        this.je = kVar;
        view.setOnClickListener(this);
    }

    public void a(LatestApplicationObject latestApplicationObject) {
        TextView textView = this.textView_version;
        textView.setText(latestApplicationObject.getVersion() + "");
        TextView textView2 = this.textView_content;
        textView2.setText(latestApplicationObject.getUpdateContent() + "");
        this.textView_timestamp.setText(g.B(this.context, latestApplicationObject.getCreatedAt()));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.je.C(getAdapterPosition());
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
