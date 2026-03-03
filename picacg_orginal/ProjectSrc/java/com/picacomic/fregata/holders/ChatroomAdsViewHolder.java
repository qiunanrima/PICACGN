package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.a;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomAdsViewHolder.class */
public class ChatroomAdsViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "ChatroomAdsViewHolder";
    @BindView(2131296576)
    public CircleImageView imageView_avatar;
    @BindView(2131296862)
    public TextView textView_level;
    @BindView(2131296864)
    public TextView textView_name;
    @BindView(2131296868)
    public TextView textView_timestamp;
    @BindView(2131296869)
    public TextView textView_title;

    public ChatroomAdsViewHolder(View view, a aVar) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
