package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CommentTopReplacementViewHolder_ViewBinding.class */
public class CommentTopReplacementViewHolder_ViewBinding implements Unbinder {
    private CommentTopReplacementViewHolder sM;

    @UiThread
    public CommentTopReplacementViewHolder_ViewBinding(CommentTopReplacementViewHolder commentTopReplacementViewHolder, View view) {
        this.sM = commentTopReplacementViewHolder;
        commentTopReplacementViewHolder.textView_floor = (TextView) Utils.findRequiredViewAsType(view, 2131296908, "field 'textView_floor'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CommentTopReplacementViewHolder commentTopReplacementViewHolder = this.sM;
        if (commentTopReplacementViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sM = null;
        commentTopReplacementViewHolder.textView_floor = null;
    }
}
