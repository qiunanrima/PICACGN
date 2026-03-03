package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/NewsCellView_ViewBinding.class */
public class NewsCellView_ViewBinding implements Unbinder {
    private NewsCellView sT;

    @UiThread
    public NewsCellView_ViewBinding(NewsCellView newsCellView, View view) {
        this.sT = newsCellView;
        newsCellView.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296618, "field 'imageView_image'", ImageView.class);
        newsCellView.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296962, "field 'textView_title'", TextView.class);
        newsCellView.textView_description = (TextView) Utils.findRequiredViewAsType(view, 2131296961, "field 'textView_description'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        NewsCellView newsCellView = this.sT;
        if (newsCellView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sT = null;
        newsCellView.imageView_image = null;
        newsCellView.textView_title = null;
        newsCellView.textView_description = null;
    }
}
