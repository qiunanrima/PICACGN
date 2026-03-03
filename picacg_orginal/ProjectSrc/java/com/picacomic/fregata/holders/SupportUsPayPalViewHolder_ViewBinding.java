package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/SupportUsPayPalViewHolder_ViewBinding.class */
public class SupportUsPayPalViewHolder_ViewBinding implements Unbinder {
    private SupportUsPayPalViewHolder sY;

    @UiThread
    public SupportUsPayPalViewHolder_ViewBinding(SupportUsPayPalViewHolder supportUsPayPalViewHolder, View view) {
        this.sY = supportUsPayPalViewHolder;
        supportUsPayPalViewHolder.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296631, "field 'imageView_image'", ImageView.class);
        supportUsPayPalViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131297016, "field 'textView_title'", TextView.class);
        supportUsPayPalViewHolder.textView_priceUnit = (TextView) Utils.findRequiredViewAsType(view, 2131297015, "field 'textView_priceUnit'", TextView.class);
        supportUsPayPalViewHolder.textView_price = (TextView) Utils.findRequiredViewAsType(view, 2131297014, "field 'textView_price'", TextView.class);
        supportUsPayPalViewHolder.textView_description = (TextView) Utils.findRequiredViewAsType(view, 2131297013, "field 'textView_description'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SupportUsPayPalViewHolder supportUsPayPalViewHolder = this.sY;
        if (supportUsPayPalViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sY = null;
        supportUsPayPalViewHolder.imageView_image = null;
        supportUsPayPalViewHolder.textView_title = null;
        supportUsPayPalViewHolder.textView_priceUnit = null;
        supportUsPayPalViewHolder.textView_price = null;
        supportUsPayPalViewHolder.textView_description = null;
    }
}
