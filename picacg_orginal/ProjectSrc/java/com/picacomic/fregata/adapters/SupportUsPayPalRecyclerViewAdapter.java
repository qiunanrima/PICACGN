package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.holders.SupportUsPayPalViewHolder;
import com.picacomic.fregata.objects.SupportUsPayPalObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/SupportUsPayPalRecyclerViewAdapter.class */
public class SupportUsPayPalRecyclerViewAdapter extends RecyclerView.Adapter<SupportUsPayPalViewHolder> {
    public static final String TAG = "SupportUsPayPalRecyclerViewAdapter";
    private final Context context;
    private ArrayList<SupportUsPayPalObject> ja;
    private k jb;
    private final LayoutInflater mLayoutInflater;

    public SupportUsPayPalRecyclerViewAdapter(Context context, ArrayList<SupportUsPayPalObject> arrayList, k kVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
        this.jb = kVar;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(SupportUsPayPalViewHolder supportUsPayPalViewHolder, int i) {
        SupportUsPayPalObject supportUsPayPalObject;
        if (this.ja == null || this.ja.size() <= i || (supportUsPayPalObject = this.ja.get(i)) == null) {
            return;
        }
        if (supportUsPayPalObject.imageId != -1) {
            supportUsPayPalViewHolder.imageView_image.setImageResource(supportUsPayPalObject.imageId);
        }
        if (supportUsPayPalObject.title != null) {
            TextView textView = supportUsPayPalViewHolder.textView_title;
            textView.setText(supportUsPayPalObject.title + "");
        }
        if (supportUsPayPalObject.priceUnit != null) {
            TextView textView2 = supportUsPayPalViewHolder.textView_priceUnit;
            textView2.setText(supportUsPayPalObject.priceUnit + "");
        }
        if (supportUsPayPalObject.price != null) {
            TextView textView3 = supportUsPayPalViewHolder.textView_price;
            textView3.setText(supportUsPayPalObject.price + "");
        }
        if (supportUsPayPalObject.description != null) {
            TextView textView4 = supportUsPayPalViewHolder.textView_description;
            textView4.setText(supportUsPayPalObject.description + "");
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.ja == null ? 0 : this.ja.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: l */
    public SupportUsPayPalViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SupportUsPayPalViewHolder(this.mLayoutInflater.inflate(2131427489, viewGroup, false), this.jb);
    }
}
