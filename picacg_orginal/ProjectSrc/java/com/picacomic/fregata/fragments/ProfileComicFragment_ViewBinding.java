package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProfileComicFragment_ViewBinding.class */
public class ProfileComicFragment_ViewBinding implements Unbinder {
    private ProfileComicFragment qN;

    @UiThread
    public ProfileComicFragment_ViewBinding(ProfileComicFragment profileComicFragment, View view) {
        this.qN = profileComicFragment;
        profileComicFragment.linearLayout_downloaded = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296694, "field 'linearLayout_downloaded'", LinearLayout.class);
        profileComicFragment.linearLayout_recentView = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296695, "field 'linearLayout_recentView'", LinearLayout.class);
        profileComicFragment.linearLayout_bookmarked = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296693, "field 'linearLayout_bookmarked'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ProfileComicFragment profileComicFragment = this.qN;
        if (profileComicFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qN = null;
        profileComicFragment.linearLayout_downloaded = null;
        profileComicFragment.linearLayout_recentView = null;
        profileComicFragment.linearLayout_bookmarked = null;
    }
}
