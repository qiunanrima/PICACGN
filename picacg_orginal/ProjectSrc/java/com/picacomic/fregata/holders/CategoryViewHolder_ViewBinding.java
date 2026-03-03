package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CategoryViewHolder_ViewBinding.class */
public class CategoryViewHolder_ViewBinding implements Unbinder {
    private CategoryViewHolder sm;

    @UiThread
    public CategoryViewHolder_ViewBinding(CategoryViewHolder categoryViewHolder, View view) {
        this.sm = categoryViewHolder;
        categoryViewHolder.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296573, "field 'imageView_image'", ImageView.class);
        categoryViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296848, "field 'textView_title'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CategoryViewHolder categoryViewHolder = this.sm;
        if (categoryViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sm = null;
        categoryViewHolder.imageView_image = null;
        categoryViewHolder.textView_title = null;
    }
}
