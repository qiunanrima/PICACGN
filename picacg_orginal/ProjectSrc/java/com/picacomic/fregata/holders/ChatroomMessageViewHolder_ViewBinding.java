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
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomMessageViewHolder_ViewBinding.class */
public class ChatroomMessageViewHolder_ViewBinding implements Unbinder {
    private ChatroomMessageViewHolder sA;

    @UiThread
    public ChatroomMessageViewHolder_ViewBinding(ChatroomMessageViewHolder chatroomMessageViewHolder, View view) {
        this.sA = chatroomMessageViewHolder;
        chatroomMessageViewHolder.container = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296650, "field 'container'", LinearLayout.class);
        chatroomMessageViewHolder.imageView_avatar = (CircleImageView) Utils.findRequiredViewAsType(view, 2131296576, "field 'imageView_avatar'", CircleImageView.class);
        chatroomMessageViewHolder.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296864, "field 'textView_name'", TextView.class);
        chatroomMessageViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296869, "field 'textView_title'", TextView.class);
        chatroomMessageViewHolder.textView_message = (TextView) Utils.findRequiredViewAsType(view, 2131296863, "field 'textView_message'", TextView.class);
        chatroomMessageViewHolder.textView_timestamp = (TextView) Utils.findRequiredViewAsType(view, 2131296868, "field 'textView_timestamp'", TextView.class);
        chatroomMessageViewHolder.imageView_verified = (ImageView) Utils.findRequiredViewAsType(view, 2131296578, "field 'imageView_verified'", ImageView.class);
        chatroomMessageViewHolder.textView_replyName = (TextView) Utils.findRequiredViewAsType(view, 2131296866, "field 'textView_replyName'", TextView.class);
        chatroomMessageViewHolder.textView_replyMessage = (TextView) Utils.findRequiredViewAsType(view, 2131296865, "field 'textView_replyMessage'", TextView.class);
        chatroomMessageViewHolder.textView_at = (TextView) Utils.findRequiredViewAsType(view, 2131296860, "field 'textView_at'", TextView.class);
        chatroomMessageViewHolder.linearLayout_replyContainer = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296653, "field 'linearLayout_replyContainer'", LinearLayout.class);
        chatroomMessageViewHolder.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296862, "field 'textView_level'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomMessageViewHolder chatroomMessageViewHolder = this.sA;
        if (chatroomMessageViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sA = null;
        chatroomMessageViewHolder.container = null;
        chatroomMessageViewHolder.imageView_avatar = null;
        chatroomMessageViewHolder.textView_name = null;
        chatroomMessageViewHolder.textView_title = null;
        chatroomMessageViewHolder.textView_message = null;
        chatroomMessageViewHolder.textView_timestamp = null;
        chatroomMessageViewHolder.imageView_verified = null;
        chatroomMessageViewHolder.textView_replyName = null;
        chatroomMessageViewHolder.textView_replyMessage = null;
        chatroomMessageViewHolder.textView_at = null;
        chatroomMessageViewHolder.linearLayout_replyContainer = null;
        chatroomMessageViewHolder.textView_level = null;
    }
}
