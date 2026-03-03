package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/RegisterFragment_ViewBinding.class */
public class RegisterFragment_ViewBinding implements Unbinder {
    private RegisterFragment rv;

    @UiThread
    public RegisterFragment_ViewBinding(RegisterFragment registerFragment, View view) {
        this.rv = registerFragment;
        registerFragment.frameLayout_backgroundWhite = (FrameLayout) Utils.findRequiredViewAsType(view, 2131296529, "field 'frameLayout_backgroundWhite'", FrameLayout.class);
        registerFragment.editText_username = (EditText) Utils.findRequiredViewAsType(view, 2131296480, "field 'editText_username'", EditText.class);
        registerFragment.editText_email = (EditText) Utils.findRequiredViewAsType(view, 2131296474, "field 'editText_email'", EditText.class);
        registerFragment.editText_password = (EditText) Utils.findRequiredViewAsType(view, 2131296475, "field 'editText_password'", EditText.class);
        registerFragment.editText_passwordConfirm = (EditText) Utils.findRequiredViewAsType(view, 2131296476, "field 'editText_passwordConfirm'", EditText.class);
        registerFragment.editText_question_1 = (EditText) Utils.findRequiredViewAsType(view, 2131296477, "field 'editText_question_1'", EditText.class);
        registerFragment.editText_question_2 = (EditText) Utils.findRequiredViewAsType(view, 2131296478, "field 'editText_question_2'", EditText.class);
        registerFragment.editText_question_3 = (EditText) Utils.findRequiredViewAsType(view, 2131296479, "field 'editText_question_3'", EditText.class);
        registerFragment.editText_answer_1 = (EditText) Utils.findRequiredViewAsType(view, 2131296471, "field 'editText_answer_1'", EditText.class);
        registerFragment.editText_answer_2 = (EditText) Utils.findRequiredViewAsType(view, 2131296472, "field 'editText_answer_2'", EditText.class);
        registerFragment.editText_answer_3 = (EditText) Utils.findRequiredViewAsType(view, 2131296473, "field 'editText_answer_3'", EditText.class);
        registerFragment.textView_birthday = (TextView) Utils.findRequiredViewAsType(view, 2131296988, "field 'textView_birthday'", TextView.class);
        registerFragment.button_register = (Button) Utils.findRequiredViewAsType(view, 2131296387, "field 'button_register'", Button.class);
        registerFragment.buttons_gender = (AppCompatButton[]) Utils.arrayOf((AppCompatButton) Utils.findRequiredViewAsType(view, 2131296386, "field 'buttons_gender'", AppCompatButton.class), (AppCompatButton) Utils.findRequiredViewAsType(view, 2131296385, "field 'buttons_gender'", AppCompatButton.class), (AppCompatButton) Utils.findRequiredViewAsType(view, 2131296384, "field 'buttons_gender'", AppCompatButton.class));
        registerFragment.genders = view.getContext().getResources().getStringArray(2130903048);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        RegisterFragment registerFragment = this.rv;
        if (registerFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.rv = null;
        registerFragment.frameLayout_backgroundWhite = null;
        registerFragment.editText_username = null;
        registerFragment.editText_email = null;
        registerFragment.editText_password = null;
        registerFragment.editText_passwordConfirm = null;
        registerFragment.editText_question_1 = null;
        registerFragment.editText_question_2 = null;
        registerFragment.editText_question_3 = null;
        registerFragment.editText_answer_1 = null;
        registerFragment.editText_answer_2 = null;
        registerFragment.editText_answer_3 = null;
        registerFragment.textView_birthday = null;
        registerFragment.button_register = null;
        registerFragment.buttons_gender = null;
    }
}
