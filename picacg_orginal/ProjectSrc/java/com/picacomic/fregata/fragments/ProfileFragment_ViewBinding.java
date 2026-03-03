package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.picacomic.fregata.utils.views.ExpCircleView;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProfileFragment_ViewBinding.class */
public class ProfileFragment_ViewBinding implements Unbinder {
    private ProfileFragment qY;

    @UiThread
    public ProfileFragment_ViewBinding(ProfileFragment profileFragment, View view) {
        this.qY = profileFragment;
        profileFragment.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, 2131296830, "field 'tabLayout'", TabLayout.class);
        profileFragment.expCircleView = (ExpCircleView) Utils.findRequiredViewAsType(view, 2131296496, "field 'expCircleView'", ExpCircleView.class);
        profileFragment.imageView_avatarBlur = (ImageView) Utils.findRequiredViewAsType(view, 2131296623, "field 'imageView_avatarBlur'", ImageView.class);
        profileFragment.imageView_verified = (ImageView) Utils.findRequiredViewAsType(view, 2131296627, "field 'imageView_verified'", ImageView.class);
        profileFragment.imageView_character = (ImageView) Utils.findRequiredViewAsType(view, 2131296624, "field 'imageView_character'", ImageView.class);
        profileFragment.imageView_avatar = (CircleImageView) Utils.findRequiredViewAsType(view, 2131296622, "field 'imageView_avatar'", CircleImageView.class);
        profileFragment.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296971, "field 'textView_level'", TextView.class);
        profileFragment.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296972, "field 'textView_name'", TextView.class);
        profileFragment.textView_honor = (TextView) Utils.findRequiredViewAsType(view, 2131296970, "field 'textView_honor'", TextView.class);
        profileFragment.textView_slogan = (TextView) Utils.findRequiredViewAsType(view, 2131296984, "field 'textView_slogan'", TextView.class);
        profileFragment.textView_punchIn = (TextView) Utils.findRequiredViewAsType(view, 2131296983, "field 'textView_punchIn'", TextView.class);
        profileFragment.button_edit = (Button) Utils.findRequiredViewAsType(view, 2131296382, "field 'button_edit'", Button.class);
        profileFragment.viewPager_tags = (ViewPager) Utils.findRequiredViewAsType(view, 2131297056, "field 'viewPager_tags'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ProfileFragment profileFragment = this.qY;
        if (profileFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qY = null;
        profileFragment.tabLayout = null;
        profileFragment.expCircleView = null;
        profileFragment.imageView_avatarBlur = null;
        profileFragment.imageView_verified = null;
        profileFragment.imageView_character = null;
        profileFragment.imageView_avatar = null;
        profileFragment.textView_level = null;
        profileFragment.textView_name = null;
        profileFragment.textView_honor = null;
        profileFragment.textView_slogan = null;
        profileFragment.textView_punchIn = null;
        profileFragment.button_edit = null;
        profileFragment.viewPager_tags = null;
    }
}
