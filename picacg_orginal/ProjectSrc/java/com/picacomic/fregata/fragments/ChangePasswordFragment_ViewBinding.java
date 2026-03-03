package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChangePasswordFragment_ViewBinding.class */
public class ChangePasswordFragment_ViewBinding implements Unbinder {
    private ChangePasswordFragment kP;

    @UiThread
    public ChangePasswordFragment_ViewBinding(ChangePasswordFragment changePasswordFragment, View view) {
        this.kP = changePasswordFragment;
        changePasswordFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        changePasswordFragment.editText_password = (EditText) Utils.findRequiredViewAsType(view, 2131296446, "field 'editText_password'", EditText.class);
        changePasswordFragment.editText_passwordConfirm = (EditText) Utils.findRequiredViewAsType(view, 2131296447, "field 'editText_passwordConfirm'", EditText.class);
        changePasswordFragment.checkBox_showPassword = (CheckBox) Utils.findRequiredViewAsType(view, 2131296416, "field 'checkBox_showPassword'", CheckBox.class);
        changePasswordFragment.button_changePassword = (Button) Utils.findRequiredViewAsType(view, 2131296308, "field 'button_changePassword'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ChangePasswordFragment changePasswordFragment = this.kP;
        if (changePasswordFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.kP = null;
        changePasswordFragment.toolbar = null;
        changePasswordFragment.editText_password = null;
        changePasswordFragment.editText_passwordConfirm = null;
        changePasswordFragment.checkBox_showPassword = null;
        changePasswordFragment.button_changePassword = null;
    }
}
