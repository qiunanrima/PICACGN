package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomAdsViewHolder_ViewBinding.class */
public class ChatroomAdsViewHolder_ViewBinding implements Unbinder {
    private ChatroomAdsViewHolder so;

    @UiThread
    public ChatroomAdsViewHolder_ViewBinding(ChatroomAdsViewHolder chatroomAdsViewHolder, View view) {
        this.so = chatroomAdsViewHolder;
        chatroomAdsViewHolder.imageView_avatar = (CircleImageView) Utils.findRequiredViewAsType(view, 2131296576, "field 'imageView_avatar'", CircleImageView.class);
        chatroomAdsViewHolder.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296864, "field 'textView_name'", TextView.class);
        chatroomAdsViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296869, "field 'textView_title'", TextView.class);
        chatroomAdsViewHolder.textView_timestamp = (TextView) Utils.findRequiredViewAsType(view, 2131296868, "field 'textView_timestamp'", TextView.class);
        chatroomAdsViewHolder.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296862, "field 'textView_level'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomAdsViewHolder chatroomAdsViewHolder = this.so;
        if (chatroomAdsViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.so = null;
        chatroomAdsViewHolder.imageView_avatar = null;
        chatroomAdsViewHolder.textView_name = null;
        chatroomAdsViewHolder.textView_title = null;
        chatroomAdsViewHolder.textView_timestamp = null;
        chatroomAdsViewHolder.textView_level = null;
    }
}
