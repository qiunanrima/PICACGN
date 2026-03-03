package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/OneTimeIdUpdateFragment_ViewBinding.class */
public class OneTimeIdUpdateFragment_ViewBinding implements Unbinder {
    private OneTimeIdUpdateFragment qq;

    @UiThread
    public OneTimeIdUpdateFragment_ViewBinding(OneTimeIdUpdateFragment oneTimeIdUpdateFragment, View view) {
        this.qq = oneTimeIdUpdateFragment;
        oneTimeIdUpdateFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        oneTimeIdUpdateFragment.editText_username = (EditText) Utils.findRequiredViewAsType(view, 2131296463, "field 'editText_username'", EditText.class);
        oneTimeIdUpdateFragment.editText_email = (EditText) Utils.findRequiredViewAsType(view, 2131296462, "field 'editText_email'", EditText.class);
        oneTimeIdUpdateFragment.button_update = (Button) Utils.findRequiredViewAsType(view, 2131296377, "field 'button_update'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        OneTimeIdUpdateFragment oneTimeIdUpdateFragment = this.qq;
        if (oneTimeIdUpdateFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.qq = null;
        oneTimeIdUpdateFragment.toolbar = null;
        oneTimeIdUpdateFragment.editText_username = null;
        oneTimeIdUpdateFragment.editText_email = null;
        oneTimeIdUpdateFragment.button_update = null;
    }
}
