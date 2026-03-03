package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.e;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CommentViewHolder.class */
public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "CommentViewHolder";
    @BindView(2131296358)
    public Button button_dirty;
    @BindView(2131296359)
    public Button button_hide;
    @BindView(2131296360)
    public Button button_tools;
    @BindView(2131296361)
    public Button button_top;
    @BindView(2131296362)
    public Button button_viewMore;
    @BindView(2131296588)
    public ImageView imageView_likeIcon;
    @BindView(2131296589)
    public ImageView imageView_option;
    @BindView(2131296591)
    public ImageView imageView_userThumb;
    @BindView(2131296592)
    public ImageView imageView_userVerified;
    @BindView(2131296674)
    public LinearLayout linearLayout_reply;
    @BindView(2131296675)
    public LinearLayout linearLayout_tools;
    e sI;
    @BindView(2131296906)
    public TextView textView_content;
    @BindView(2131296907)
    public TextView textView_createdDate;
    @BindView(2131296908)
    public TextView textView_floor;
    @BindView(2131296909)
    public TextView textView_level;
    @BindView(2131296910)
    public TextView textView_likeCount;
    @BindView(2131296911)
    public TextView textView_noReply;
    @BindView(2131296912)
    public TextView textView_replyCount;
    @BindView(2131296913)
    public TextView textView_title;
    @BindView(2131296914)
    public TextView textView_username;

    public CommentViewHolder(View view, e eVar) {
        super(view);
        ButterKnife.bind(this, view);
        this.sI = eVar;
        view.setOnClickListener(this);
        this.imageView_userThumb.setOnClickListener(this);
        this.button_viewMore.setOnClickListener(this);
        this.imageView_likeIcon.setOnClickListener(this);
        this.textView_likeCount.setOnClickListener(this);
        this.textView_username.setOnClickListener(this);
        this.button_hide.setOnClickListener(this);
        this.button_dirty.setOnClickListener(this);
        this.button_tools.setOnClickListener(this);
        this.button_top.setOnClickListener(this);
        this.imageView_option.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == 2131296588 || view.getId() == 2131296910) {
            this.sI.Q(getAdapterPosition());
        } else if (view.getId() == 2131296362) {
            this.sI.N(getAdapterPosition());
        } else if (view.getId() == 2131296591) {
            this.sI.P(getAdapterPosition());
        } else if (view.getId() == 2131296915) {
            this.sI.O(getAdapterPosition());
        } else if (view.getId() == 2131296914) {
            this.sI.R(getAdapterPosition());
        } else if (view.getId() == 2131296359) {
            this.sI.S(getAdapterPosition());
        } else if (view.getId() == 2131296360) {
            this.sI.A(getAdapterPosition());
        } else if (view.getId() == 2131296361) {
            this.sI.T(getAdapterPosition());
        } else if (view.getId() == 2131296358) {
            this.sI.U(getAdapterPosition());
        } else if (view.getId() == 2131296589) {
            this.sI.V(getAdapterPosition());
        } else {
            this.sI.C(getAdapterPosition());
        }
        String str = TAG;
        Log.d(str, "onClick--> position = " + getAdapterPosition());
    }
}
