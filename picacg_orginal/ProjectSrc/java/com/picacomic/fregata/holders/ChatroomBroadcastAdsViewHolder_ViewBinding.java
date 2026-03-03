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
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomBroadcastAdsViewHolder_ViewBinding.class */
public class ChatroomBroadcastAdsViewHolder_ViewBinding implements Unbinder {
    private ChatroomBroadcastAdsViewHolder st;

    @UiThread
    public ChatroomBroadcastAdsViewHolder_ViewBinding(ChatroomBroadcastAdsViewHolder chatroomBroadcastAdsViewHolder, View view) {
        this.st = chatroomBroadcastAdsViewHolder;
        chatroomBroadcastAdsViewHolder.container = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296650, "field 'container'", LinearLayout.class);
        chatroomBroadcastAdsViewHolder.imageView_avatar = (CircleImageView) Utils.findRequiredViewAsType(view, 2131296576, "field 'imageView_avatar'", CircleImageView.class);
        chatroomBroadcastAdsViewHolder.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296864, "field 'textView_name'", TextView.class);
        chatroomBroadcastAdsViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296869, "field 'textView_title'", TextView.class);
        chatroomBroadcastAdsViewHolder.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296577, "field 'imageView_image'", ImageView.class);
        chatroomBroadcastAdsViewHolder.textView_message = (TextView) Utils.findRequiredViewAsType(view, 2131296863, "field 'textView_message'", TextView.class);
        chatroomBroadcastAdsViewHolder.textView_timestamp = (TextView) Utils.findRequiredViewAsType(view, 2131296868, "field 'textView_timestamp'", TextView.class);
        chatroomBroadcastAdsViewHolder.imageView_verified = (ImageView) Utils.findRequiredViewAsType(view, 2131296578, "field 'imageView_verified'", ImageView.class);
        chatroomBroadcastAdsViewHolder.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296862, "field 'textView_level'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomBroadcastAdsViewHolder chatroomBroadcastAdsViewHolder = this.st;
        if (chatroomBroadcastAdsViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.st = null;
        chatroomBroadcastAdsViewHolder.container = null;
        chatroomBroadcastAdsViewHolder.imageView_avatar = null;
        chatroomBroadcastAdsViewHolder.textView_name = null;
        chatroomBroadcastAdsViewHolder.textView_title = null;
        chatroomBroadcastAdsViewHolder.imageView_image = null;
        chatroomBroadcastAdsViewHolder.textView_message = null;
        chatroomBroadcastAdsViewHolder.textView_timestamp = null;
        chatroomBroadcastAdsViewHolder.imageView_verified = null;
        chatroomBroadcastAdsViewHolder.textView_level = null;
    }
}
