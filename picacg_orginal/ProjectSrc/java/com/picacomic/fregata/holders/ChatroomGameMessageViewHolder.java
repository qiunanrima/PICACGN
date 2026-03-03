package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomGameMessageViewHolder.class */
public class ChatroomGameMessageViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "ChatroomGameMessageViewHolder";
    @BindView(2131296857)
    public TextView textView_message;

    public ChatroomGameMessageViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
