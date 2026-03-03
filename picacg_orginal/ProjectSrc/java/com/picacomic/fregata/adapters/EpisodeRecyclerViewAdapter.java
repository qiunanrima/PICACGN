package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.holders.EpisodeViewHolder;
import com.picacomic.fregata.objects.ComicEpisodeObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/EpisodeRecyclerViewAdapter.class */
public class EpisodeRecyclerViewAdapter extends RecyclerView.Adapter<EpisodeViewHolder> {
    public static final String TAG = "EpisodeRecyclerViewAdapter";
    private final Context context;
    private ArrayList<ComicEpisodeObject> ja;
    private k jb;
    private final LayoutInflater mLayoutInflater;

    public EpisodeRecyclerViewAdapter(Context context, ArrayList<ComicEpisodeObject> arrayList, k kVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
        this.jb = kVar;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(EpisodeViewHolder episodeViewHolder, int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return;
        }
        episodeViewHolder.a(this.ja.get(i));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: f */
    public EpisodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i != 4) {
            switch (i) {
                case 0:
                    return new EpisodeViewHolder(this.context, this.mLayoutInflater.inflate(2131427472, viewGroup, false), this.jb);
                case 1:
                    return new EpisodeViewHolder(this.context, this.mLayoutInflater.inflate(2131427474, viewGroup, false), this.jb);
                case 2:
                    return new EpisodeViewHolder(this.context, this.mLayoutInflater.inflate(2131427473, viewGroup, false), this.jb);
                default:
                    return new EpisodeViewHolder(this.context, this.mLayoutInflater.inflate(2131427472, viewGroup, false), this.jb);
            }
        }
        return new EpisodeViewHolder(this.context, this.mLayoutInflater.inflate(2131427475, viewGroup, false), this.jb);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.ja == null ? 0 : this.ja.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.ja == null || this.ja.size() <= 0) {
            return 0;
        }
        if (this.ja.get(i).isSelected()) {
            return 4;
        }
        return this.ja.get(i).getStatus();
    }
}
