package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/AnonymousChatFragment_ViewBinding.class */
public class AnonymousChatFragment_ViewBinding implements Unbinder {
    private AnonymousChatFragment kl;

    @UiThread
    public AnonymousChatFragment_ViewBinding(AnonymousChatFragment anonymousChatFragment, View view) {
        this.kl = anonymousChatFragment;
        anonymousChatFragment.linearLayout_name = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296642, "field 'linearLayout_name'", LinearLayout.class);
        anonymousChatFragment.linearLayout_typeSpace = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296643, "field 'linearLayout_typeSpace'", LinearLayout.class);
        anonymousChatFragment.linearLayout_message = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296641, "field 'linearLayout_message'", LinearLayout.class);
        anonymousChatFragment.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296752, "field 'recyclerView'", RecyclerView.class);
        anonymousChatFragment.editText_name = (EditText) Utils.findRequiredViewAsType(view, 2131296444, "field 'editText_name'", EditText.class);
        anonymousChatFragment.editText_typeSpace = (EditText) Utils.findRequiredViewAsType(view, 2131296445, "field 'editText_typeSpace'", EditText.class);
        anonymousChatFragment.textView_message = (TextView) Utils.findRequiredViewAsType(view, 2131296841, "field 'textView_message'", TextView.class);
        anonymousChatFragment.textView_matcherName = (TextView) Utils.findRequiredViewAsType(view, 2131296840, "field 'textView_matcherName'", TextView.class);
        anonymousChatFragment.imageButton_leave = (ImageButton) Utils.findRequiredViewAsType(view, 2131296540, "field 'imageButton_leave'", ImageButton.class);
        anonymousChatFragment.button_match = (Button) Utils.findRequiredViewAsType(view, 2131296305, "field 'button_match'", Button.class);
        anonymousChatFragment.button_send = (Button) Utils.findRequiredViewAsType(view, 2131296306, "field 'button_send'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AnonymousChatFragment anonymousChatFragment = this.kl;
        if (anonymousChatFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.kl = null;
        anonymousChatFragment.linearLayout_name = null;
        anonymousChatFragment.linearLayout_typeSpace = null;
        anonymousChatFragment.linearLayout_message = null;
        anonymousChatFragment.recyclerView = null;
        anonymousChatFragment.editText_name = null;
        anonymousChatFragment.editText_typeSpace = null;
        anonymousChatFragment.textView_message = null;
        anonymousChatFragment.textView_matcherName = null;
        anonymousChatFragment.imageButton_leave = null;
        anonymousChatFragment.button_match = null;
        anonymousChatFragment.button_send = null;
    }
}
