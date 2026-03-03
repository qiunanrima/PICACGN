package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/TitleEditPopupFragment_ViewBinding.class */
public class TitleEditPopupFragment_ViewBinding implements Unbinder {
    private TitleEditPopupFragment sa;

    @UiThread
    public TitleEditPopupFragment_ViewBinding(TitleEditPopupFragment titleEditPopupFragment, View view) {
        this.sa = titleEditPopupFragment;
        titleEditPopupFragment.textView_userId = (TextView) Utils.findRequiredViewAsType(view, 2131297018, "field 'textView_userId'", TextView.class);
        titleEditPopupFragment.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131297017, "field 'textView_title'", TextView.class);
        titleEditPopupFragment.editText_newTitle = (EditText) Utils.findRequiredViewAsType(view, 2131296486, "field 'editText_newTitle'", EditText.class);
        titleEditPopupFragment.button_cancel = (Button) Utils.findRequiredViewAsType(view, 2131296407, "field 'button_cancel'", Button.class);
        titleEditPopupFragment.button_confirm = (Button) Utils.findRequiredViewAsType(view, 2131296408, "field 'button_confirm'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        TitleEditPopupFragment titleEditPopupFragment = this.sa;
        if (titleEditPopupFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sa = null;
        titleEditPopupFragment.textView_userId = null;
        titleEditPopupFragment.textView_title = null;
        titleEditPopupFragment.editText_newTitle = null;
        titleEditPopupFragment.button_cancel = null;
        titleEditPopupFragment.button_confirm = null;
    }
}
