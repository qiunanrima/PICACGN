package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomImageViewHolder_ViewBinding.class */
public class ChatroomImageViewHolder_ViewBinding implements Unbinder {
    private ChatroomImageViewHolder sx;

    @UiThread
    public ChatroomImageViewHolder_ViewBinding(ChatroomImageViewHolder chatroomImageViewHolder, View view) {
        this.sx = chatroomImageViewHolder;
        chatroomImageViewHolder.container = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296650, "field 'container'", LinearLayout.class);
        chatroomImageViewHolder.imageView_avatar = (CircleImageView) Utils.findRequiredViewAsType(view, 2131296576, "field 'imageView_avatar'", CircleImageView.class);
        chatroomImageViewHolder.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296864, "field 'textView_name'", TextView.class);
        chatroomImageViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296869, "field 'textView_title'", TextView.class);
        chatroomImageViewHolder.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296577, "field 'imageView_image'", ImageView.class);
        chatroomImageViewHolder.textView_timestamp = (TextView) Utils.findRequiredViewAsType(view, 2131296868, "field 'textView_timestamp'", TextView.class);
        chatroomImageViewHolder.imageView_verified = (ImageView) Utils.findRequiredViewAsType(view, 2131296578, "field 'imageView_verified'", ImageView.class);
        chatroomImageViewHolder.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296862, "field 'textView_level'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomImageViewHolder chatroomImageViewHolder = this.sx;
        if (chatroomImageViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sx = null;
        chatroomImageViewHolder.container = null;
        chatroomImageViewHolder.imageView_avatar = null;
        chatroomImageViewHolder.textView_name = null;
        chatroomImageViewHolder.textView_title = null;
        chatroomImageViewHolder.imageView_image = null;
        chatroomImageViewHolder.textView_timestamp = null;
        chatroomImageViewHolder.imageView_verified = null;
        chatroomImageViewHolder.textView_level = null;
    }
}
