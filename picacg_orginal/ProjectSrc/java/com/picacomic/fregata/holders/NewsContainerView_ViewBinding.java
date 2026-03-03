package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/NewsContainerView_ViewBinding.class */
public class NewsContainerView_ViewBinding implements Unbinder {
    private NewsContainerView sU;

    @UiThread
    public NewsContainerView_ViewBinding(NewsContainerView newsContainerView, View view) {
        this.sU = newsContainerView;
        newsContainerView.imageView_icon = (ImageView) Utils.findRequiredViewAsType(view, 2131296619, "field 'imageView_icon'", ImageView.class);
        newsContainerView.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296963, "field 'textView_title'", TextView.class);
        newsContainerView.imageView_viewMore = (ImageView) Utils.findRequiredViewAsType(view, 2131296620, "field 'imageView_viewMore'", ImageView.class);
        newsContainerView.textView_count = (TextView) Utils.findRequiredViewAsType(view, 2131296964, "field 'textView_count'", TextView.class);
        newsContainerView.linearLayout_content = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296692, "field 'linearLayout_content'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        NewsContainerView newsContainerView = this.sU;
        if (newsContainerView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sU = null;
        newsContainerView.imageView_icon = null;
        newsContainerView.textView_title = null;
        newsContainerView.imageView_viewMore = null;
        newsContainerView.textView_count = null;
        newsContainerView.linearLayout_content = null;
    }
}
