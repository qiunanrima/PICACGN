package com.picacomic.fregata.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.a.h;
import com.picacomic.fregata.objects.NotificationObject;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/NotificationViewHolder.class */
public class NotificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String TAG = "NotificationViewHolder";
    Context context;
    @BindView(2131296576)
    ImageView imageView_avatar;
    @BindView(2131296578)
    ImageView imageView_character;
    @BindView(2131296621)
    ImageView imageView_image;
    h jN;
    NotificationObject sV;
    @BindView(2131296965)
    TextView textView_content;
    @BindView(2131296966)
    TextView textView_timestamp;

    public NotificationViewHolder(Context context, View view, h hVar) {
        super(view);
        this.context = context;
        ButterKnife.bind(this, view);
        this.jN = hVar;
        this.imageView_avatar.setOnClickListener(this);
        this.imageView_image.setOnClickListener(this);
        this.imageView_character.setOnClickListener(this);
        view.setOnClickListener(this);
    }

    public void a(NotificationObject notificationObject) {
        this.sV = notificationObject;
        if (this.sV != null) {
            if (this.sV.getSender() != null) {
                if (this.sV.getSender().getCharacter() == null || this.sV.getSender().getCharacter().equalsIgnoreCase("")) {
                    this.imageView_character.setVisibility(8);
                } else {
                    Picasso.with(this.context).load(this.sV.getSender().getCharacter()).into(this.imageView_character);
                    this.imageView_character.setVisibility(0);
                }
                Picasso.with(this.context).load(g.b(this.sV.getSender().getAvatar())).placeholder(2131231027).into(this.imageView_avatar);
            }
            if (this.sV.getCover() != null) {
                Picasso.with(this.context).load(g.b(this.sV.getCover())).into(this.imageView_image);
                f.F(TAG, g.b(this.sV.getCover()));
                this.imageView_image.setVisibility(0);
            } else {
                this.imageView_image.setVisibility(8);
            }
            TextView textView = this.textView_content;
            textView.setText(this.sV.getContent() + "");
            this.textView_timestamp.setText(g.B(this.context, this.sV.getCreatedAt()));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.jN != null) {
            if (view == this.imageView_image) {
                this.jN.Y(getAdapterPosition());
            } else if (view == this.imageView_avatar || view == this.imageView_character) {
                this.jN.X(getAdapterPosition());
            } else {
                this.jN.W(getAdapterPosition());
            }
        }
    }
}
