package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.fragments.SupportUsPayPalFragment;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/SupportUsPayPalViewHolder.class */
public class SupportUsPayPalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = SupportUsPayPalFragment.class.getSimpleName();
    @BindView(2131296631)
    public ImageView imageView_image;
    public k je;
    @BindView(2131297013)
    public TextView textView_description;
    @BindView(2131297014)
    public TextView textView_price;
    @BindView(2131297015)
    public TextView textView_priceUnit;
    @BindView(2131297016)
    public TextView textView_title;

    public SupportUsPayPalViewHolder(View view, k kVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.je = kVar;
        view.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.je.C(getAdapterPosition());
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
