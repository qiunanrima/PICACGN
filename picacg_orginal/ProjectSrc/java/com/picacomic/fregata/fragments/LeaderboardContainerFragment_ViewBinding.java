package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/LeaderboardContainerFragment_ViewBinding.class */
public class LeaderboardContainerFragment_ViewBinding implements Unbinder {
    private LeaderboardContainerFragment pD;

    @UiThread
    public LeaderboardContainerFragment_ViewBinding(LeaderboardContainerFragment leaderboardContainerFragment, View view) {
        this.pD = leaderboardContainerFragment;
        leaderboardContainerFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        leaderboardContainerFragment.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, 2131296830, "field 'tabLayout'", TabLayout.class);
        leaderboardContainerFragment.viewPager_tags = (ViewPager) Utils.findRequiredViewAsType(view, 2131297055, "field 'viewPager_tags'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        LeaderboardContainerFragment leaderboardContainerFragment = this.pD;
        if (leaderboardContainerFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.pD = null;
        leaderboardContainerFragment.toolbar = null;
        leaderboardContainerFragment.tabLayout = null;
        leaderboardContainerFragment.viewPager_tags = null;
    }
}
