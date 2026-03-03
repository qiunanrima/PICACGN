package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicCollectionView_ViewBinding.class */
public class ComicCollectionView_ViewBinding implements Unbinder {
    private ComicCollectionView sC;

    @UiThread
    public ComicCollectionView_ViewBinding(ComicCollectionView comicCollectionView, View view) {
        this.sC = comicCollectionView;
        comicCollectionView.imageView_icon = (ImageView) Utils.findRequiredViewAsType(view, 2131296579, "field 'imageView_icon'", ImageView.class);
        comicCollectionView.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296877, "field 'textView_title'", TextView.class);
        comicCollectionView.textView_count = (TextView) Utils.findRequiredViewAsType(view, 2131296876, "field 'textView_count'", TextView.class);
        comicCollectionView.linearLayout_content = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296656, "field 'linearLayout_content'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComicCollectionView comicCollectionView = this.sC;
        if (comicCollectionView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sC = null;
        comicCollectionView.imageView_icon = null;
        comicCollectionView.textView_title = null;
        comicCollectionView.textView_count = null;
        comicCollectionView.linearLayout_content = null;
    }
}
