package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProfilePopupFragment_ViewBinding.class */
public class ProfilePopupFragment_ViewBinding implements Unbinder {
    private ProfilePopupFragment rc;

    @UiThread
    public ProfilePopupFragment_ViewBinding(ProfilePopupFragment profilePopupFragment, View view) {
        this.rc = profilePopupFragment;
        profilePopupFragment.imageView_avatar = (ImageView) Utils.findRequiredViewAsType(view, 2131296625, "field 'imageView_avatar'", ImageView.class);
        profilePopupFragment.imageView_character = (ImageView) Utils.findRequiredViewAsType(view, 2131296626, "field 'imageView_character'", ImageView.class);
        profilePopupFragment.textView_levelTitle = (TextView) Utils.findRequiredViewAsType(view, 2131296977, "field 'textView_levelTitle'", TextView.class);
        profilePopupFragment.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296976, "field 'textView_level'", TextView.class);
        profilePopupFragment.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296978, "field 'textView_name'", TextView.class);
        profilePopupFragment.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296981, "field 'textView_title'", TextView.class);
        profilePopupFragment.textView_slogan = (TextView) Utils.findRequiredViewAsType(view, 2131296980, "field 'textView_slogan'", TextView.class);
        profilePopupFragment.textView_adminFunction = (TextView) Utils.findRequiredViewAsType(view, 2131296975, "field 'textView_adminFunction'", TextView.class);
        profilePopupFragment.textView_block = (TextView) Utils.findRequiredViewAsType(view, 2131296974, "field 'textView_block'", TextView.class);
        profilePopupFragment.textView_woo = (TextView) Utils.findRequiredViewAsType(view, 2131296982, "field 'textView_woo'", TextView.class);
        profilePopupFragment.textView_removeComment = (TextView) Utils.findRequiredViewAsType(view, 2131296979, "field 'textView_removeComment'", TextView.class);
        profilePopupFragment.linearLayout_adminFunction = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296697, "field 'linearLayout_adminFunction'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ProfilePopupFragment profilePopupFragment = this.rc;
        if (profilePopupFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.rc = null;
        profilePopupFragment.imageView_avatar = null;
        profilePopupFragment.imageView_character = null;
        profilePopupFragment.textView_levelTitle = null;
        profilePopupFragment.textView_level = null;
        profilePopupFragment.textView_name = null;
        profilePopupFragment.textView_title = null;
        profilePopupFragment.textView_slogan = null;
        profilePopupFragment.textView_adminFunction = null;
        profilePopupFragment.textView_block = null;
        profilePopupFragment.textView_woo = null;
        profilePopupFragment.textView_removeComment = null;
        profilePopupFragment.linearLayout_adminFunction = null;
    }
}
