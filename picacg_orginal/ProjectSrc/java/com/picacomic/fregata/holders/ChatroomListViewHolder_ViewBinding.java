package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ChatroomListViewHolder_ViewBinding.class */
public class ChatroomListViewHolder_ViewBinding implements Unbinder {
    private ChatroomListViewHolder sz;

    @UiThread
    public ChatroomListViewHolder_ViewBinding(ChatroomListViewHolder chatroomListViewHolder, View view) {
        this.sz = chatroomListViewHolder;
        chatroomListViewHolder.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296575, "field 'imageView_image'", ImageView.class);
        chatroomListViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296859, "field 'textView_title'", TextView.class);
        chatroomListViewHolder.textView_description = (TextView) Utils.findRequiredViewAsType(view, 2131296858, "field 'textView_description'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomListViewHolder chatroomListViewHolder = this.sz;
        if (chatroomListViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sz = null;
        chatroomListViewHolder.imageView_image = null;
        chatroomListViewHolder.textView_title = null;
        chatroomListViewHolder.textView_description = null;
    }
}
