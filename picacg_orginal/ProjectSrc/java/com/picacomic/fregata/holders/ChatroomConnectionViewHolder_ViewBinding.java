package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomConnectionViewHolder_ViewBinding.class */
public class ChatroomConnectionViewHolder_ViewBinding implements Unbinder {
    private ChatroomConnectionViewHolder su;

    @UiThread
    public ChatroomConnectionViewHolder_ViewBinding(ChatroomConnectionViewHolder chatroomConnectionViewHolder, View view) {
        this.su = chatroomConnectionViewHolder;
        chatroomConnectionViewHolder.textView_connection = (TextView) Utils.findRequiredViewAsType(view, 2131296861, "field 'textView_connection'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomConnectionViewHolder chatroomConnectionViewHolder = this.su;
        if (chatroomConnectionViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.su = null;
        chatroomConnectionViewHolder.textView_connection = null;
    }
}
