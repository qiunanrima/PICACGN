package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/PicaAppContainerFragment_ViewBinding.class */
public class PicaAppContainerFragment_ViewBinding implements Unbinder {
    private PicaAppContainerFragment qv;

    @UiThread
    public PicaAppContainerFragment_ViewBinding(PicaAppContainerFragment picaAppContainerFragment, View view) {
        this.qv = picaAppContainerFragment;
        picaAppContainerFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        picaAppContainerFragment.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, 2131296830, "field 'tabLayout'", TabLayout.class);
        picaAppContainerFragment.viewPager_tags = (ViewPager) Utils.findRequiredViewAsType(view, 2131297055, "field 'viewPager_tags'", ViewPager.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        PicaAppContainerFragment picaAppContainerFragment = this.qv;
        if (picaAppContainerFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qv = null;
        picaAppContainerFragment.toolbar = null;
        picaAppContainerFragment.tabLayout = null;
        picaAppContainerFragment.viewPager_tags = null;
    }
}
