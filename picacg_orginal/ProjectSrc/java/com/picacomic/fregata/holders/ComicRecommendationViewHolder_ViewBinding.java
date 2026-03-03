package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ComicRecommendationViewHolder_ViewBinding.class */
public class ComicRecommendationViewHolder_ViewBinding implements Unbinder {
    private ComicRecommendationViewHolder sH;

    @UiThread
    public ComicRecommendationViewHolder_ViewBinding(ComicRecommendationViewHolder comicRecommendationViewHolder, View view) {
        this.sH = comicRecommendationViewHolder;
        comicRecommendationViewHolder.imageView = (ImageView) Utils.findRequiredViewAsType(view, 2131296628, "field 'imageView'", ImageView.class);
        comicRecommendationViewHolder.textView = (TextView) Utils.findRequiredViewAsType(view, 2131296987, "field 'textView'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComicRecommendationViewHolder comicRecommendationViewHolder = this.sH;
        if (comicRecommendationViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sH = null;
        comicRecommendationViewHolder.imageView = null;
        comicRecommendationViewHolder.textView = null;
    }
}
