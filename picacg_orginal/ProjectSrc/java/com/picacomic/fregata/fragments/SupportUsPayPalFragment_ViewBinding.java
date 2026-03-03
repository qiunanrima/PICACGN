package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/SupportUsPayPalFragment_ViewBinding.class */
public class SupportUsPayPalFragment_ViewBinding implements Unbinder {
    private SupportUsPayPalFragment rW;

    @UiThread
    public SupportUsPayPalFragment_ViewBinding(SupportUsPayPalFragment supportUsPayPalFragment, View view) {
        this.rW = supportUsPayPalFragment;
        supportUsPayPalFragment.recyclerView_paypal = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296770, "field 'recyclerView_paypal'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SupportUsPayPalFragment supportUsPayPalFragment = this.rW;
        if (supportUsPayPalFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.rW = null;
        supportUsPayPalFragment.recyclerView_paypal = null;
    }
}
