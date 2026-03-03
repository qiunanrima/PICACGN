package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProfileEditFragment_ViewBinding.class */
public class ProfileEditFragment_ViewBinding implements Unbinder {
    private ProfileEditFragment qQ;

    @UiThread
    public ProfileEditFragment_ViewBinding(ProfileEditFragment profileEditFragment, View view) {
        this.qQ = profileEditFragment;
        profileEditFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        profileEditFragment.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296972, "field 'textView_name'", TextView.class);
        profileEditFragment.textView_email = (TextView) Utils.findRequiredViewAsType(view, 2131296969, "field 'textView_email'", TextView.class);
        profileEditFragment.textView_birth = (TextView) Utils.findRequiredViewAsType(view, 2131296968, "field 'textView_birth'", TextView.class);
        profileEditFragment.editText_slogan = (EditText) Utils.findRequiredViewAsType(view, 2131296470, "field 'editText_slogan'", EditText.class);
        profileEditFragment.button_update = (Button) Utils.findRequiredViewAsType(view, 2131296383, "field 'button_update'", Button.class);
        profileEditFragment.imageView_avatar = (CircleImageView) Utils.findRequiredViewAsType(view, 2131296622, "field 'imageView_avatar'", CircleImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ProfileEditFragment profileEditFragment = this.qQ;
        if (profileEditFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qQ = null;
        profileEditFragment.toolbar = null;
        profileEditFragment.textView_name = null;
        profileEditFragment.textView_email = null;
        profileEditFragment.textView_birth = null;
        profileEditFragment.editText_slogan = null;
        profileEditFragment.button_update = null;
        profileEditFragment.imageView_avatar = null;
    }
}
