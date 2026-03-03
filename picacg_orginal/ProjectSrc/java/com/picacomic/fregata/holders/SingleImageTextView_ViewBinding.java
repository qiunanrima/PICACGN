package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.picacomic.fregata.utils.views.ComicThumbImageView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/SingleImageTextView_ViewBinding.class */
public class SingleImageTextView_ViewBinding implements Unbinder {
    private SingleImageTextView sX;

    @UiThread
    public SingleImageTextView_ViewBinding(SingleImageTextView singleImageTextView, View view) {
        this.sX = singleImageTextView;
        singleImageTextView.imageView_image = (ComicThumbImageView) Utils.findRequiredViewAsType(view, 2131296629, "field 'imageView_image'", ComicThumbImageView.class);
        singleImageTextView.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131297009, "field 'textView_title'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SingleImageTextView singleImageTextView = this.sX;
        if (singleImageTextView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sX = null;
        singleImageTextView.imageView_image = null;
        singleImageTextView.textView_title = null;
    }
}
