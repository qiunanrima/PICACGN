package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.holders.ComicRecommendationViewHolder;
import com.picacomic.fregata.objects.ComicListObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/ComicRecommendationRecyclerViewAdapter.class */
public class ComicRecommendationRecyclerViewAdapter extends RecyclerView.Adapter<ComicRecommendationViewHolder> {
    public static final String TAG = "ComicRecommendationRecyclerViewAdapter";
    private final Context context;
    private ArrayList<ComicListObject> ja;
    private com.picacomic.fregata.a.b jq;
    private final LayoutInflater mLayoutInflater;
    int targetWidth = 80;
    int targetHeight = 120;

    public ComicRecommendationRecyclerViewAdapter(Context context, ArrayList<ComicListObject> arrayList, com.picacomic.fregata.a.b bVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
        this.jq = bVar;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(ComicRecommendationViewHolder comicRecommendationViewHolder, int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return;
        }
        comicRecommendationViewHolder.b(this.ja.get(i));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: e */
    public ComicRecommendationViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ComicRecommendationViewHolder(this.context, this.mLayoutInflater.inflate(2131427464, viewGroup, false), this.jq);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.ja != null) {
            return this.ja.size();
        }
        return 0;
    }
}
