package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.a;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomBroadcastAdsViewHolder.class */
public class ChatroomBroadcastAdsViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = ChatroomMessageViewHolder.class.getSimpleName();
    @BindView(2131296650)
    public LinearLayout container;
    @BindView(2131296576)
    public CircleImageView imageView_avatar;
    @BindView(2131296577)
    public ImageView imageView_image;
    @BindView(2131296578)
    public ImageView imageView_verified;
    a jn;
    @BindView(2131296862)
    public TextView textView_level;
    @BindView(2131296863)
    public TextView textView_message;
    @BindView(2131296864)
    public TextView textView_name;
    @BindView(2131296868)
    public TextView textView_timestamp;
    @BindView(2131296869)
    public TextView textView_title;

    public ChatroomBroadcastAdsViewHolder(View view, a aVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.jn = aVar;
        this.textView_name.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomBroadcastAdsViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomBroadcastAdsViewHolder.this.jn.G(ChatroomBroadcastAdsViewHolder.this.getAdapterPosition());
            }
        });
        this.textView_title.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomBroadcastAdsViewHolder.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomBroadcastAdsViewHolder.this.jn.G(ChatroomBroadcastAdsViewHolder.this.getAdapterPosition());
            }
        });
        this.imageView_avatar.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomBroadcastAdsViewHolder.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomBroadcastAdsViewHolder.this.jn.F(ChatroomBroadcastAdsViewHolder.this.getAdapterPosition());
            }
        });
        this.imageView_image.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomBroadcastAdsViewHolder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomBroadcastAdsViewHolder.this.jn.E(ChatroomBroadcastAdsViewHolder.this.getAdapterPosition());
            }
        });
        this.textView_message.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.holders.ChatroomBroadcastAdsViewHolder.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ChatroomBroadcastAdsViewHolder.this.jn.H(ChatroomBroadcastAdsViewHolder.this.getAdapterPosition());
            }
        });
    }
}
