package com.picacomic.fregata.services;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/services/ChatroomService_ViewBinding.class */
public class ChatroomService_ViewBinding implements Unbinder {
    private ChatroomService tM;

    @UiThread
    public ChatroomService_ViewBinding(ChatroomService chatroomService, View view) {
        this.tM = chatroomService;
        chatroomService.recyclerView_chat = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296769, "field 'recyclerView_chat'", RecyclerView.class);
        chatroomService.relativeLayout_input = (RelativeLayout) Utils.findRequiredViewAsType(view, 2131296778, "field 'relativeLayout_input'", RelativeLayout.class);
        chatroomService.button_messageMode = (Button) Utils.findRequiredViewAsType(view, 2131296389, "field 'button_messageMode'", Button.class);
        chatroomService.button_close = (Button) Utils.findRequiredViewAsType(view, 2131296388, "field 'button_close'", Button.class);
        chatroomService.button_send = (Button) Utils.findRequiredViewAsType(view, 2131296391, "field 'button_send'", Button.class);
        chatroomService.button_move = (Button) Utils.findRequiredViewAsType(view, 2131296390, "field 'button_move'", Button.class);
        chatroomService.button_sizeController = (Button) Utils.findRequiredViewAsType(view, 2131296392, "field 'button_sizeController'", Button.class);
        chatroomService.editText_input = (EditText) Utils.findRequiredViewAsType(view, 2131296481, "field 'editText_input'", EditText.class);
        chatroomService.textView_totalUserCount = (TextView) Utils.findRequiredViewAsType(view, 2131296989, "field 'textView_totalUserCount'", TextView.class);
        chatroomService.textView_atList = (TextView) Utils.findRequiredViewAsType(view, 2131296849, "field 'textView_atList'", TextView.class);
        chatroomService.textView_reply = (TextView) Utils.findRequiredViewAsType(view, 2131296853, "field 'textView_reply'", TextView.class);
        chatroomService.textView_toastMsg = (TextView) Utils.findRequiredViewAsType(view, 2131296855, "field 'textView_toastMsg'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomService chatroomService = this.tM;
        if (chatroomService == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.tM = null;
        chatroomService.recyclerView_chat = null;
        chatroomService.relativeLayout_input = null;
        chatroomService.button_messageMode = null;
        chatroomService.button_close = null;
        chatroomService.button_send = null;
        chatroomService.button_move = null;
        chatroomService.button_sizeController = null;
        chatroomService.editText_input = null;
        chatroomService.textView_totalUserCount = null;
        chatroomService.textView_atList = null;
        chatroomService.textView_reply = null;
        chatroomService.textView_toastMsg = null;
    }
}
