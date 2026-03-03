package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/PicaAppFragment_ViewBinding.class */
public class PicaAppFragment_ViewBinding implements Unbinder {
    private PicaAppFragment qA;

    @UiThread
    public PicaAppFragment_ViewBinding(PicaAppFragment picaAppFragment, View view) {
        this.qA = picaAppFragment;
        picaAppFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        picaAppFragment.linearLayoutWeb = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296702, "field 'linearLayoutWeb'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        PicaAppFragment picaAppFragment = this.qA;
        if (picaAppFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qA = null;
        picaAppFragment.toolbar = null;
        picaAppFragment.linearLayoutWeb = null;
    }
}
