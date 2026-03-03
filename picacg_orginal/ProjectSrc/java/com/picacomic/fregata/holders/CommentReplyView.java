package com.picacomic.fregata.holders;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.e;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CommentReplyView.class */
public class CommentReplyView extends LinearLayout implements View.OnClickListener {
    public static final String TAG = "CommentReplyView";
    @BindView(2131296363)
    public Button button_hide;
    @BindView(2131296593)
    public ImageView imageView_likeIcon;
    @BindView(2131296589)
    public ImageView imageView_option;
    @BindView(2131296594)
    public CircleImageView imageView_userThumb;
    @BindView(2131296595)
    public ImageView imageView_userVerified;
    public int jB;
    e sI;
    public int sK;
    @BindView(2131296916)
    public TextView textView_content;
    @BindView(2131296917)
    public TextView textView_createdDate;
    @BindView(2131296918)
    public TextView textView_floor;
    @BindView(2131296919)
    public TextView textView_level;
    @BindView(2131296920)
    public TextView textView_likeCount;
    @BindView(2131296921)
    public TextView textView_title;
    @BindView(2131296922)
    public TextView textView_username;

    public CommentReplyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CommentReplyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CommentReplyView(Context context, e eVar, int i, int i2) {
        super(context);
        inflate(context, 2131427468, this);
        this.sI = eVar;
        this.sK = i;
        this.jB = i2;
        ButterKnife.bind(this);
        setOnClickListener(this);
        this.imageView_likeIcon.setOnClickListener(this);
        this.textView_likeCount.setOnClickListener(this);
        this.textView_username.setOnClickListener(this);
        this.button_hide.setOnClickListener(this);
        this.imageView_option.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == 2131296593 || view.getId() == 2131296920) {
            this.sI.g(this.sK, this.jB);
        } else if (view.getId() == 2131296922) {
            this.sI.h(this.sK, this.jB);
        } else if (view.getId() == 2131296363) {
            this.sI.i(this.sK, this.jB);
        } else if (view.getId() == 2131296589) {
            this.sI.j(this.sK, this.jB);
        } else {
            this.sI.f(this.sK, this.jB);
        }
    }
}
