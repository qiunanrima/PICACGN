package com.picacomic.fregata.utils.views;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/PopupWebview_ViewBinding.class */
public class PopupWebview_ViewBinding implements Unbinder {
    private PopupWebview target;

    @UiThread
    public PopupWebview_ViewBinding(PopupWebview popupWebview) {
        this(popupWebview, popupWebview);
    }

    @UiThread
    public PopupWebview_ViewBinding(PopupWebview popupWebview, View view) {
        this.target = popupWebview;
        popupWebview.relativeLayout_container = (RelativeLayout) Utils.findRequiredViewAsType(view, 2131296777, "field 'relativeLayout_container'", RelativeLayout.class);
        popupWebview.webView_banner = (WebView) Utils.findRequiredViewAsType(view, 2131297067, "field 'webView_banner'", WebView.class);
        popupWebview.linearLayout_web = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296702, "field 'linearLayout_web'", LinearLayout.class);
        popupWebview.imageButton_close = (ImageButton) Utils.findRequiredViewAsType(view, 2131296568, "field 'imageButton_close'", ImageButton.class);
        popupWebview.textView_text = (TextView) Utils.findRequiredViewAsType(view, 2131296967, "field 'textView_text'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        PopupWebview popupWebview = this.target;
        if (popupWebview == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        popupWebview.relativeLayout_container = null;
        popupWebview.webView_banner = null;
        popupWebview.linearLayout_web = null;
        popupWebview.imageButton_close = null;
        popupWebview.textView_text = null;
    }
}
