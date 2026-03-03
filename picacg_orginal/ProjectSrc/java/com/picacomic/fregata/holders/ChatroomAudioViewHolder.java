package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.a;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomAudioViewHolder.class */
public class ChatroomAudioViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "ChatroomAudioViewHolder";
    @BindView(2131296650)
    public LinearLayout container;
    @BindView(2131296548)
    public ImageButton imageButton_audioAction;
    @BindView(2131296576)
    public CircleImageView imageView_avatar;
    @BindView(2131296578)
    public ImageView imageView_verified;
    a jn;
    public boolean sp;
    @BindView(2131296862)
    public TextView textView_level;
    @BindView(2131296864)
    public TextView textView_name;
    @BindView(2131296868)
    public TextView textView_timestamp;
    @BindView(2131296869)
    public TextView textView_title;

    public ChatroomAudioViewHolder(View view, a aVar) {
        super(view);
        this.sp = false;
        ButterKnife.bind(this, view);
        this.jn = aVar;
        this.textView_name.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomAudioViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomAudioViewHolder.this.jn.G(ChatroomAudioViewHolder.this.getAdapterPosition());
            }
        });
        this.textView_title.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomAudioViewHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomAudioViewHolder.this.jn.G(ChatroomAudioViewHolder.this.getAdapterPosition());
            }
        });
        this.imageView_avatar.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomAudioViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomAudioViewHolder.this.jn.F(ChatroomAudioViewHolder.this.getAdapterPosition());
            }
        });
        this.imageButton_audioAction.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomAudioViewHolder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomAudioViewHolder.this.jn.D(ChatroomAudioViewHolder.this.getAdapterPosition());
            }
        });
    }
}
