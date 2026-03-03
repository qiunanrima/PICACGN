package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChangePinFragment_ViewBinding.class */
public class ChangePinFragment_ViewBinding implements Unbinder {
    private ChangePinFragment kR;

    @UiThread
    public ChangePinFragment_ViewBinding(ChangePinFragment changePinFragment, View view) {
        this.kR = changePinFragment;
        changePinFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        changePinFragment.editText_pin = (EditText) Utils.findRequiredViewAsType(view, 2131296448, "field 'editText_pin'", EditText.class);
        changePinFragment.editText_pinConfirm = (EditText) Utils.findRequiredViewAsType(view, 2131296449, "field 'editText_pinConfirm'", EditText.class);
        changePinFragment.button_change = (Button) Utils.findRequiredViewAsType(view, 2131296309, "field 'button_change'", Button.class);
        changePinFragment.button_cancel = (Button) Utils.findRequiredViewAsType(view, 2131296310, "field 'button_cancel'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChangePinFragment changePinFragment = this.kR;
        if (changePinFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.kR = null;
        changePinFragment.toolbar = null;
        changePinFragment.editText_pin = null;
        changePinFragment.editText_pinConfirm = null;
        changePinFragment.button_change = null;
        changePinFragment.button_cancel = null;
    }
}
