package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.holders.ChatroomListViewHolder;
import com.picacomic.fregata.objects.ChatroomListObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/ChatroomListRecyclerViewAdapter.class */
public class ChatroomListRecyclerViewAdapter extends RecyclerView.Adapter<ChatroomListViewHolder> {
    public static final String TAG = "ChatroomListRecyclerViewAdapter";
    private final Context context;
    ArrayList<ChatroomListObject> ja;
    private k jb;
    private final LayoutInflater mLayoutInflater;

    public ChatroomListRecyclerViewAdapter(Context context, ArrayList<ChatroomListObject> arrayList, k kVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
        this.jb = kVar;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(ChatroomListViewHolder chatroomListViewHolder, int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return;
        }
        chatroomListViewHolder.a(this.ja.get(i));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: d */
    public ChatroomListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ChatroomListViewHolder(this.context, this.mLayoutInflater.inflate(2131427456, viewGroup, false), this.jb);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.ja == null ? 0 : this.ja.size();
    }
}
