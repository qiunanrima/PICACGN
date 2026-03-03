package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.holders.AnonymousChatViewHolder;
import com.picacomic.fregata.objects.AnonymousChatDataObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/AnonymousChatRecyclerViewAdapter.class */
public class AnonymousChatRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "AnonymousChatRecyclerViewAdapter";
    private final Context context;
    private ArrayList<AnonymousChatDataObject> ja;
    private final LayoutInflater mLayoutInflater;
    String userId;

    public AnonymousChatRecyclerViewAdapter(Context context, String str, ArrayList<AnonymousChatDataObject> arrayList) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
        this.userId = str;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.ja == null) {
            return 0;
        }
        return this.ja.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return (this.ja == null || this.ja.size() <= i || this.ja.get(i).getId() == null) ? super.getItemViewType(i) : this.ja.get(i).getId().equalsIgnoreCase(this.userId) ? 0 : 1;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder == null || this.ja == null || this.ja.size() <= i) {
            return;
        }
        ((AnonymousChatViewHolder) viewHolder).a(this.ja.get(i));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new AnonymousChatViewHolder(this.mLayoutInflater.inflate(2131427443, viewGroup, false));
        }
        if (i == 1) {
            return new AnonymousChatViewHolder(this.mLayoutInflater.inflate(2131427442, viewGroup, false));
        }
        return null;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
