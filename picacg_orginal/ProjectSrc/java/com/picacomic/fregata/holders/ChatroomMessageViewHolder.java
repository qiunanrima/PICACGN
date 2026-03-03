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
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomMessageViewHolder.class */
public class ChatroomMessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "ChatroomMessageViewHolder";
    @BindView(2131296650)
    public LinearLayout container;
    @BindView(2131296576)
    public CircleImageView imageView_avatar;
    @BindView(2131296578)
    public ImageView imageView_verified;
    a jn;
    @BindView(2131296653)
    public LinearLayout linearLayout_replyContainer;
    @BindView(2131296860)
    public TextView textView_at;
    @BindView(2131296862)
    public TextView textView_level;
    @BindView(2131296863)
    public TextView textView_message;
    @BindView(2131296864)
    public TextView textView_name;
    @BindView(2131296865)
    public TextView textView_replyMessage;
    @BindView(2131296866)
    public TextView textView_replyName;
    @BindView(2131296868)
    public TextView textView_timestamp;
    @BindView(2131296869)
    public TextView textView_title;

    public ChatroomMessageViewHolder(View view, a aVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.jn = aVar;
        this.textView_message.setOnClickListener(this);
        this.textView_title.setOnClickListener(this);
        this.textView_name.setOnClickListener(this);
        this.imageView_avatar.setOnClickListener(this);
        view.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == 2131296864 || view.getId() == 2131296869) {
            this.jn.G(getAdapterPosition());
        } else if (view.getId() == 2131296576) {
            this.jn.F(getAdapterPosition());
        } else {
            this.jn.H(getAdapterPosition());
        }
    }
}
