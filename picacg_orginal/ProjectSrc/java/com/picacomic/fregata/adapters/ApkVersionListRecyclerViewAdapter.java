package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.holders.ApkVersionListViewHolder;
import com.picacomic.fregata.objects.LatestApplicationObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/ApkVersionListRecyclerViewAdapter.class */
public class ApkVersionListRecyclerViewAdapter extends RecyclerView.Adapter<ApkVersionListViewHolder> {
    public static final String TAG = "ApkVersionListRecyclerViewAdapter";
    private final Context context;
    private ArrayList<LatestApplicationObject> ja;
    private k jb;
    private final LayoutInflater mLayoutInflater;

    public ApkVersionListRecyclerViewAdapter(Context context, ArrayList<LatestApplicationObject> arrayList, k kVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.jb = kVar;
        this.ja = arrayList;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(ApkVersionListViewHolder apkVersionListViewHolder, int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return;
        }
        apkVersionListViewHolder.a(this.ja.get(i));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: b */
    public ApkVersionListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ApkVersionListViewHolder(this.context, this.mLayoutInflater.inflate(2131427444, viewGroup, false), this.jb);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.ja == null ? 0 : this.ja.size();
    }
}
