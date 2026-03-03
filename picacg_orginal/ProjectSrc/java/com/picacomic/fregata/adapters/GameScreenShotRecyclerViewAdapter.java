package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.holders.GameScreenShotViewHolder;
import com.picacomic.fregata.objects.ThumbnailObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/GameScreenShotRecyclerViewAdapter.class */
public class GameScreenShotRecyclerViewAdapter extends RecyclerView.Adapter<GameScreenShotViewHolder> {
    private final Context context;
    ArrayList<ThumbnailObject> ja;
    private k jb;
    private final LayoutInflater mLayoutInflater;

    public GameScreenShotRecyclerViewAdapter(Context context, ArrayList<ThumbnailObject> arrayList, k kVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
        this.jb = kVar;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(GameScreenShotViewHolder gameScreenShotViewHolder, int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return;
        }
        gameScreenShotViewHolder.a(this.ja.get(i));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.ja == null ? 0 : this.ja.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: h */
    public GameScreenShotViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new GameScreenShotViewHolder(this.context, this.mLayoutInflater.inflate(2131427476, viewGroup, false), this.jb);
    }
}
