package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AnonymousChatViewHolder_ViewBinding.class */
public class AnonymousChatViewHolder_ViewBinding implements Unbinder {
    private AnonymousChatViewHolder sk;

    @UiThread
    public AnonymousChatViewHolder_ViewBinding(AnonymousChatViewHolder anonymousChatViewHolder, View view) {
        this.sk = anonymousChatViewHolder;
        anonymousChatViewHolder.textView_message = (TextView) Utils.findRequiredViewAsType(view, 2131296839, "field 'textView_message'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AnonymousChatViewHolder anonymousChatViewHolder = this.sk;
        if (anonymousChatViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sk = null;
        anonymousChatViewHolder.textView_message = null;
    }
}
