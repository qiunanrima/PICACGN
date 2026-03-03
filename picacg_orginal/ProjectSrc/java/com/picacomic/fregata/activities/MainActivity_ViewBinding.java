package com.picacomic.fregata.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.AppCompatImageButton;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.picacomic.fregata.utils.views.BannerWebview;
import com.picacomic.fregata.utils.views.PopupWebview;
/* loaded from: picacg_1.jar:com/picacomic/fregata/activities/MainActivity_ViewBinding.class */
public class MainActivity_ViewBinding implements Unbinder {
    private MainActivity iU;

    @UiThread
    public MainActivity_ViewBinding(MainActivity mainActivity, View view) {
        this.iU = mainActivity;
        mainActivity.button_home = (AppCompatImageButton) Utils.findRequiredViewAsType(view, 2131296400, "field 'button_home'", AppCompatImageButton.class);
        mainActivity.linearLayout_tabbar = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296701, "field 'linearLayout_tabbar'", LinearLayout.class);
        mainActivity.bannerWebview = (BannerWebview) Utils.findRequiredViewAsType(view, 2131296299, "field 'bannerWebview'", BannerWebview.class);
        mainActivity.popupWebview = (PopupWebview) Utils.findRequiredViewAsType(view, 2131296736, "field 'popupWebview'", PopupWebview.class);
        mainActivity.button_controlExp = (ImageButton) Utils.findRequiredViewAsType(view, 2131296556, "field 'button_controlExp'", ImageButton.class);
        mainActivity.button_controlBlock = (ImageButton) Utils.findRequiredViewAsType(view, 2131296555, "field 'button_controlBlock'", ImageButton.class);
        mainActivity.buttons_tabbar = (AppCompatImageButton[]) Utils.arrayOf((AppCompatImageButton) Utils.findRequiredViewAsType(view, 2131296400, "field 'buttons_tabbar'", AppCompatImageButton.class), (AppCompatImageButton) Utils.findRequiredViewAsType(view, 2131296398, "field 'buttons_tabbar'", AppCompatImageButton.class), (AppCompatImageButton) Utils.findRequiredViewAsType(view, 2131296399, "field 'buttons_tabbar'", AppCompatImageButton.class), (AppCompatImageButton) Utils.findRequiredViewAsType(view, 2131296401, "field 'buttons_tabbar'", AppCompatImageButton.class), (AppCompatImageButton) Utils.findRequiredViewAsType(view, 2131296402, "field 'buttons_tabbar'", AppCompatImageButton.class));
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        MainActivity mainActivity = this.iU;
        if (mainActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.iU = null;
        mainActivity.button_home = null;
        mainActivity.linearLayout_tabbar = null;
        mainActivity.bannerWebview = null;
        mainActivity.popupWebview = null;
        mainActivity.button_controlExp = null;
        mainActivity.button_controlBlock = null;
        mainActivity.buttons_tabbar = null;
    }
}
