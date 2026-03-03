package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChatroomContainerFragment_ViewBinding.class */
public class ChatroomContainerFragment_ViewBinding implements Unbinder {
    private ChatroomContainerFragment kV;

    @UiThread
    public ChatroomContainerFragment_ViewBinding(ChatroomContainerFragment chatroomContainerFragment, View view) {
        this.kV = chatroomContainerFragment;
        chatroomContainerFragment.viewPager_tags = (ViewPager) Utils.findRequiredViewAsType(view, 2131297051, "field 'viewPager_tags'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChatroomContainerFragment chatroomContainerFragment = this.kV;
        if (chatroomContainerFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.kV = null;
        chatroomContainerFragment.viewPager_tags = null;
    }
}
