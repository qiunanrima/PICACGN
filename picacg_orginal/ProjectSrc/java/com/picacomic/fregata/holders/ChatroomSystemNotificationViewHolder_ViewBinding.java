package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomSystemNotificationViewHolder_ViewBinding.class */
public class ChatroomSystemNotificationViewHolder_ViewBinding implements Unbinder {
    private ChatroomSystemNotificationViewHolder sB;

    @UiThread
    public ChatroomSystemNotificationViewHolder_ViewBinding(ChatroomSystemNotificationViewHolder chatroomSystemNotificationViewHolder, View view) {
        this.sB = chatroomSystemNotificationViewHolder;
        chatroomSystemNotificationViewHolder.textView_notification = (TextView) Utils.findRequiredViewAsType(view, 2131296867, "field 'textView_notification'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomSystemNotificationViewHolder chatroomSystemNotificationViewHolder = this.sB;
        if (chatroomSystemNotificationViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sB = null;
        chatroomSystemNotificationViewHolder.textView_notification = null;
    }
}
