package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.picacomic.fregata.utils.views.SquareWebview;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CategoryWebViewHolder_ViewBinding.class */
public class CategoryWebViewHolder_ViewBinding implements Unbinder {
    private CategoryWebViewHolder sn;

    @UiThread
    public CategoryWebViewHolder_ViewBinding(CategoryWebViewHolder categoryWebViewHolder, View view) {
        this.sn = categoryWebViewHolder;
        categoryWebViewHolder.webview = (SquareWebview) Utils.findRequiredViewAsType(view, 2131297066, "field 'webview'", SquareWebview.class);
        categoryWebViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296848, "field 'textView_title'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CategoryWebViewHolder categoryWebViewHolder = this.sn;
        if (categoryWebViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sn = null;
        categoryWebViewHolder.webview = null;
        categoryWebViewHolder.textView_title = null;
    }
}
