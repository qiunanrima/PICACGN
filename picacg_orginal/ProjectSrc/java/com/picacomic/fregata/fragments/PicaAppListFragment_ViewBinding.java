package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/PicaAppListFragment_ViewBinding.class */
public class PicaAppListFragment_ViewBinding implements Unbinder {
    private PicaAppListFragment qE;

    @UiThread
    public PicaAppListFragment_ViewBinding(PicaAppListFragment picaAppListFragment, View view) {
        this.qE = picaAppListFragment;
        picaAppListFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        picaAppListFragment.recyclerView_list = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296757, "field 'recyclerView_list'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        PicaAppListFragment picaAppListFragment = this.qE;
        if (picaAppListFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qE = null;
        picaAppListFragment.toolbar = null;
        picaAppListFragment.recyclerView_list = null;
    }
}
