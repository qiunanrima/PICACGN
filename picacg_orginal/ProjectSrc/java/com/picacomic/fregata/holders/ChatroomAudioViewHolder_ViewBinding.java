package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomAudioViewHolder_ViewBinding.class */
public class ChatroomAudioViewHolder_ViewBinding implements Unbinder {
    private ChatroomAudioViewHolder sr;

    @UiThread
    public ChatroomAudioViewHolder_ViewBinding(ChatroomAudioViewHolder chatroomAudioViewHolder, View view) {
        this.sr = chatroomAudioViewHolder;
        chatroomAudioViewHolder.container = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296650, "field 'container'", LinearLayout.class);
        chatroomAudioViewHolder.imageView_avatar = (CircleImageView) Utils.findRequiredViewAsType(view, 2131296576, "field 'imageView_avatar'", CircleImageView.class);
        chatroomAudioViewHolder.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296864, "field 'textView_name'", TextView.class);
        chatroomAudioViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296869, "field 'textView_title'", TextView.class);
        chatroomAudioViewHolder.imageButton_audioAction = (ImageButton) Utils.findRequiredViewAsType(view, 2131296548, "field 'imageButton_audioAction'", ImageButton.class);
        chatroomAudioViewHolder.textView_timestamp = (TextView) Utils.findRequiredViewAsType(view, 2131296868, "field 'textView_timestamp'", TextView.class);
        chatroomAudioViewHolder.imageView_verified = (ImageView) Utils.findRequiredViewAsType(view, 2131296578, "field 'imageView_verified'", ImageView.class);
        chatroomAudioViewHolder.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296862, "field 'textView_level'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomAudioViewHolder chatroomAudioViewHolder = this.sr;
        if (chatroomAudioViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sr = null;
        chatroomAudioViewHolder.container = null;
        chatroomAudioViewHolder.imageView_avatar = null;
        chatroomAudioViewHolder.textView_name = null;
        chatroomAudioViewHolder.textView_title = null;
        chatroomAudioViewHolder.imageButton_audioAction = null;
        chatroomAudioViewHolder.textView_timestamp = null;
        chatroomAudioViewHolder.imageView_verified = null;
        chatroomAudioViewHolder.textView_level = null;
    }
}
