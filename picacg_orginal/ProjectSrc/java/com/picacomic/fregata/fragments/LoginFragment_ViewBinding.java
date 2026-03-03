package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/LoginFragment_ViewBinding.class */
public class LoginFragment_ViewBinding implements Unbinder {
    private LoginFragment qi;

    @UiThread
    public LoginFragment_ViewBinding(LoginFragment loginFragment, View view) {
        this.qi = loginFragment;
        loginFragment.linearLayout_loginForm = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296690, "field 'linearLayout_loginForm'", LinearLayout.class);
        loginFragment.imageView_logo = (ImageView) Utils.findRequiredViewAsType(view, 2131296617, "field 'imageView_logo'", ImageView.class);
        loginFragment.button_register = (Button) Utils.findRequiredViewAsType(view, 2131296380, "field 'button_register'", Button.class);
        loginFragment.button_login = (Button) Utils.findRequiredViewAsType(view, 2131296379, "field 'button_login'", Button.class);
        loginFragment.editText_email = (EditText) Utils.findRequiredViewAsType(view, 2131296468, "field 'editText_email'", EditText.class);
        loginFragment.editText_password = (EditText) Utils.findRequiredViewAsType(view, 2131296469, "field 'editText_password'", EditText.class);
        loginFragment.button_forgotPassword = (Button) Utils.findRequiredViewAsType(view, 2131296378, "field 'button_forgotPassword'", Button.class);
        loginFragment.button_resendActivation = (Button) Utils.findRequiredViewAsType(view, 2131296381, "field 'button_resendActivation'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        LoginFragment loginFragment = this.qi;
        if (loginFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qi = null;
        loginFragment.linearLayout_loginForm = null;
        loginFragment.imageView_logo = null;
        loginFragment.button_register = null;
        loginFragment.button_login = null;
        loginFragment.editText_email = null;
        loginFragment.editText_password = null;
        loginFragment.button_forgotPassword = null;
        loginFragment.button_resendActivation = null;
    }
}
