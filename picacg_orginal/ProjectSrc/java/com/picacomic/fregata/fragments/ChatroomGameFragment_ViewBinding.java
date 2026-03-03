package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.picacomic.fregata.utils.ChatroomGame.ChatroomGameView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChatroomGameFragment_ViewBinding.class */
public class ChatroomGameFragment_ViewBinding implements Unbinder {
    private ChatroomGameFragment mO;

    @UiThread
    public ChatroomGameFragment_ViewBinding(ChatroomGameFragment chatroomGameFragment, View view) {
        this.mO = chatroomGameFragment;
        chatroomGameFragment.recyclerView_gameMessage = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296756, "field 'recyclerView_gameMessage'", RecyclerView.class);
        chatroomGameFragment.gameView = (ChatroomGameView) Utils.findRequiredViewAsType(view, 2131296415, "field 'gameView'", ChatroomGameView.class);
        chatroomGameFragment.button_t1 = (Button) Utils.findRequiredViewAsType(view, 2131296403, "field 'button_t1'", Button.class);
        chatroomGameFragment.button_t2 = (Button) Utils.findRequiredViewAsType(view, 2131296404, "field 'button_t2'", Button.class);
        chatroomGameFragment.button_t3 = (Button) Utils.findRequiredViewAsType(view, 2131296405, "field 'button_t3'", Button.class);
        chatroomGameFragment.button_t4 = (Button) Utils.findRequiredViewAsType(view, 2131296406, "field 'button_t4'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomGameFragment chatroomGameFragment = this.mO;
        if (chatroomGameFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.mO = null;
        chatroomGameFragment.recyclerView_gameMessage = null;
        chatroomGameFragment.gameView = null;
        chatroomGameFragment.button_t1 = null;
        chatroomGameFragment.button_t2 = null;
        chatroomGameFragment.button_t3 = null;
        chatroomGameFragment.button_t4 = null;
    }
}
