package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicPageViewHolder_ViewBinding.class */
public class ComicPageViewHolder_ViewBinding implements Unbinder {
    private ComicPageViewHolder sG;

    @UiThread
    public ComicPageViewHolder_ViewBinding(ComicPageViewHolder comicPageViewHolder, View view) {
        this.sG = comicPageViewHolder;
        comicPageViewHolder.frameLayout_container = (FrameLayout) Utils.findRequiredViewAsType(view, 2131296515, "field 'frameLayout_container'", FrameLayout.class);
        comicPageViewHolder.textView_page = (TextView) Utils.findRequiredViewAsType(view, 2131296898, "field 'textView_page'", TextView.class);
        comicPageViewHolder.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296585, "field 'imageView_image'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComicPageViewHolder comicPageViewHolder = this.sG;
        if (comicPageViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sG = null;
        comicPageViewHolder.frameLayout_container = null;
        comicPageViewHolder.textView_page = null;
        comicPageViewHolder.imageView_image = null;
    }
}
