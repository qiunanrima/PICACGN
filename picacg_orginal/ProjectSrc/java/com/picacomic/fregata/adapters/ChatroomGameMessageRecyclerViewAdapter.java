package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.picacomic.fregata.holders.ChatroomGameMessageViewHolder;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/ChatroomGameMessageRecyclerViewAdapter.class */
public class ChatroomGameMessageRecyclerViewAdapter extends RecyclerView.Adapter<ChatroomGameMessageViewHolder> {
    public static final String TAG = "ChatroomGameMessageRecyclerViewAdapter";
    private final Context context;
    private ArrayList<String> ja;
    private final LayoutInflater mLayoutInflater;

    public ChatroomGameMessageRecyclerViewAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: a */
    public void onBindViewHolder(ChatroomGameMessageViewHolder chatroomGameMessageViewHolder, int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return;
        }
        TextView textView = chatroomGameMessageViewHolder.textView_message;
        textView.setText("Game Message " + i);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    /* renamed from: c */
    public ChatroomGameMessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ChatroomGameMessageViewHolder(this.mLayoutInflater.inflate(2131427453, viewGroup, false));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.ja == null || this.ja.size() <= 0) {
            return 0;
        }
        return this.ja.size();
    }
}
