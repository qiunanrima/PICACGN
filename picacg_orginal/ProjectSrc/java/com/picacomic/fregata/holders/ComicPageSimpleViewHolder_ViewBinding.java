package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicPageSimpleViewHolder_ViewBinding.class */
public class ComicPageSimpleViewHolder_ViewBinding implements Unbinder {
    private ComicPageSimpleViewHolder sF;

    @UiThread
    public ComicPageSimpleViewHolder_ViewBinding(ComicPageSimpleViewHolder comicPageSimpleViewHolder, View view) {
        this.sF = comicPageSimpleViewHolder;
        comicPageSimpleViewHolder.frameLayout_container = (RelativeLayout) Utils.findRequiredViewAsType(view, 2131296515, "field 'frameLayout_container'", RelativeLayout.class);
        comicPageSimpleViewHolder.textView_page = (TextView) Utils.findRequiredViewAsType(view, 2131296898, "field 'textView_page'", TextView.class);
        comicPageSimpleViewHolder.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296585, "field 'imageView_image'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComicPageSimpleViewHolder comicPageSimpleViewHolder = this.sF;
        if (comicPageSimpleViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sF = null;
        comicPageSimpleViewHolder.frameLayout_container = null;
        comicPageSimpleViewHolder.textView_page = null;
        comicPageSimpleViewHolder.imageView_image = null;
    }
}
