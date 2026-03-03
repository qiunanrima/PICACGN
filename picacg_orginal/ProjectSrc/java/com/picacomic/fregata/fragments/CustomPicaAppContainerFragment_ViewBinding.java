package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/CustomPicaAppContainerFragment_ViewBinding.class */
public class CustomPicaAppContainerFragment_ViewBinding implements Unbinder {
    private CustomPicaAppContainerFragment oR;

    @UiThread
    public CustomPicaAppContainerFragment_ViewBinding(CustomPicaAppContainerFragment customPicaAppContainerFragment, View view) {
        this.oR = customPicaAppContainerFragment;
        customPicaAppContainerFragment.viewPage_picaApp = (ViewPager) Utils.findRequiredViewAsType(view, 2131297052, "field 'viewPage_picaApp'", ViewPager.class);
        customPicaAppContainerFragment.fab_add = (FloatingActionButton) Utils.findRequiredViewAsType(view, 2131296499, "field 'fab_add'", FloatingActionButton.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CustomPicaAppContainerFragment customPicaAppContainerFragment = this.oR;
        if (customPicaAppContainerFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.oR = null;
        customPicaAppContainerFragment.viewPage_picaApp = null;
        customPicaAppContainerFragment.fab_add = null;
    }
}
