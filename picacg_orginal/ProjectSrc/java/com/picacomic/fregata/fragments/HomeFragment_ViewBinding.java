package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/HomeFragment_ViewBinding.class */
public class HomeFragment_ViewBinding implements Unbinder {
    private HomeFragment pz;

    @UiThread
    public HomeFragment_ViewBinding(HomeFragment homeFragment, View view) {
        this.pz = homeFragment;
        homeFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        homeFragment.viewPager_banner = (ViewPager) Utils.findRequiredViewAsType(view, 2131297054, "field 'viewPager_banner'", ViewPager.class);
        homeFragment.textView_bannerTitle = (TextView) Utils.findRequiredViewAsType(view, 2131296948, "field 'textView_bannerTitle'", TextView.class);
        homeFragment.linearLayout_pagerIndicators = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296683, "field 'linearLayout_pagerIndicators'", LinearLayout.class);
        homeFragment.linearLayout_announcements = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296682, "field 'linearLayout_announcements'", LinearLayout.class);
        homeFragment.linearLayout_collection_1 = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296684, "field 'linearLayout_collection_1'", LinearLayout.class);
        homeFragment.linearLayout_collection_2 = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296685, "field 'linearLayout_collection_2'", LinearLayout.class);
        homeFragment.linearLayout_collection_3 = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296686, "field 'linearLayout_collection_3'", LinearLayout.class);
        homeFragment.linearLayout_collection_4 = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296687, "field 'linearLayout_collection_4'", LinearLayout.class);
        homeFragment.linearLayout_collection_5 = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296688, "field 'linearLayout_collection_5'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        HomeFragment homeFragment = this.pz;
        if (homeFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.pz = null;
        homeFragment.toolbar = null;
        homeFragment.viewPager_banner = null;
        homeFragment.textView_bannerTitle = null;
        homeFragment.linearLayout_pagerIndicators = null;
        homeFragment.linearLayout_announcements = null;
        homeFragment.linearLayout_collection_1 = null;
        homeFragment.linearLayout_collection_2 = null;
        homeFragment.linearLayout_collection_3 = null;
        homeFragment.linearLayout_collection_4 = null;
        homeFragment.linearLayout_collection_5 = null;
    }
}
