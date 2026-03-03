package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/SettingFragment_ViewBinding.class */
public class SettingFragment_ViewBinding implements Unbinder {
    private SettingFragment rL;

    @UiThread
    public SettingFragment_ViewBinding(SettingFragment settingFragment, View view) {
        this.rL = settingFragment;
        settingFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        settingFragment.textView_screenOrientation = (TextView) Utils.findRequiredViewAsType(view, 2131297006, "field 'textView_screenOrientation'", TextView.class);
        settingFragment.textView_scrollDirection = (TextView) Utils.findRequiredViewAsType(view, 2131297007, "field 'textView_scrollDirection'", TextView.class);
        settingFragment.textView_autoPagingInterval = (TextView) Utils.findRequiredViewAsType(view, 2131296994, "field 'textView_autoPagingInterval'", TextView.class);
        settingFragment.textView_apkVersionTitle = (TextView) Utils.findRequiredViewAsType(view, 2131296993, "field 'textView_apkVersionTitle'", TextView.class);
        settingFragment.textView_apkVersion = (TextView) Utils.findRequiredViewAsType(view, 2131296992, "field 'textView_apkVersion'", TextView.class);
        settingFragment.textView_cacheTitle = (TextView) Utils.findRequiredViewAsType(view, 2131296996, "field 'textView_cacheTitle'", TextView.class);
        settingFragment.textView_cache = (TextView) Utils.findRequiredViewAsType(view, 2131296995, "field 'textView_cache'", TextView.class);
        settingFragment.textView_continueDownloadComic = (TextView) Utils.findRequiredViewAsType(view, 2131296998, "field 'textView_continueDownloadComic'", TextView.class);
        settingFragment.textView_pinTitle = (TextView) Utils.findRequiredViewAsType(view, 2131297005, "field 'textView_pinTitle'", TextView.class);
        settingFragment.textView_pin = (TextView) Utils.findRequiredViewAsType(view, 2131297004, "field 'textView_pin'", TextView.class);
        settingFragment.textView_password = (TextView) Utils.findRequiredViewAsType(view, 2131297002, "field 'textView_password'", TextView.class);
        settingFragment.textView_faq = (TextView) Utils.findRequiredViewAsType(view, 2131297000, "field 'textView_faq'", TextView.class);
        settingFragment.textView_chatroomService = (TextView) Utils.findRequiredViewAsType(view, 2131296997, "field 'textView_chatroomService'", TextView.class);
        settingFragment.textView_imageQuality = (TextView) Utils.findRequiredViewAsType(view, 2131297001, "field 'textView_imageQuality'", TextView.class);
        settingFragment.textView_themeColor = (TextView) Utils.findRequiredViewAsType(view, 2131297008, "field 'textView_themeColor'", TextView.class);
        settingFragment.button_logout = (Button) Utils.findRequiredViewAsType(view, 2131296393, "field 'button_logout'", Button.class);
        settingFragment.toggleButton_nightMode = (ToggleButton) Utils.findRequiredViewAsType(view, 2131297034, "field 'toggleButton_nightMode'", ToggleButton.class);
        settingFragment.toggleButton_volumePagingControl = (ToggleButton) Utils.findRequiredViewAsType(view, 2131297036, "field 'toggleButton_volumePagingControl'", ToggleButton.class);
        settingFragment.toggleButton_testingVersion = (ToggleButton) Utils.findRequiredViewAsType(view, 2131297033, "field 'toggleButton_testingVersion'", ToggleButton.class);
        settingFragment.toggleButton_performanceEnhancement = (ToggleButton) Utils.findRequiredViewAsType(view, 2131297035, "field 'toggleButton_performanceEnhancement'", ToggleButton.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        SettingFragment settingFragment = this.rL;
        if (settingFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.rL = null;
        settingFragment.toolbar = null;
        settingFragment.textView_screenOrientation = null;
        settingFragment.textView_scrollDirection = null;
        settingFragment.textView_autoPagingInterval = null;
        settingFragment.textView_apkVersionTitle = null;
        settingFragment.textView_apkVersion = null;
        settingFragment.textView_cacheTitle = null;
        settingFragment.textView_cache = null;
        settingFragment.textView_continueDownloadComic = null;
        settingFragment.textView_pinTitle = null;
        settingFragment.textView_pin = null;
        settingFragment.textView_password = null;
        settingFragment.textView_faq = null;
        settingFragment.textView_chatroomService = null;
        settingFragment.textView_imageQuality = null;
        settingFragment.textView_themeColor = null;
        settingFragment.button_logout = null;
        settingFragment.toggleButton_nightMode = null;
        settingFragment.toggleButton_volumePagingControl = null;
        settingFragment.toggleButton_testingVersion = null;
        settingFragment.toggleButton_performanceEnhancement = null;
    }
}
