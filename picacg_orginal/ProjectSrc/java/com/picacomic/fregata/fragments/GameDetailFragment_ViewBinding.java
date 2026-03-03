package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.picacomic.fregata.utils.views.SnapRecyclerView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/GameDetailFragment_ViewBinding.class */
public class GameDetailFragment_ViewBinding implements Unbinder {
    private GameDetailFragment pf;

    @UiThread
    public GameDetailFragment_ViewBinding(GameDetailFragment gameDetailFragment, View view) {
        this.pf = gameDetailFragment;
        gameDetailFragment.appBarLayout = (AppBarLayout) Utils.findRequiredViewAsType(view, 2131296297, "field 'appBarLayout'", AppBarLayout.class);
        gameDetailFragment.collapsingToolbarLayout = (CollapsingToolbarLayout) Utils.findRequiredViewAsType(view, 2131296426, "field 'collapsingToolbarLayout'", CollapsingToolbarLayout.class);
        gameDetailFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        gameDetailFragment.nestedScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, 2131296722, "field 'nestedScrollView'", NestedScrollView.class);
        gameDetailFragment.frameLayout_banner = (FrameLayout) Utils.findRequiredViewAsType(view, 2131296527, "field 'frameLayout_banner'", FrameLayout.class);
        gameDetailFragment.relativeLayout_popup = (RelativeLayout) Utils.findRequiredViewAsType(view, 2131296775, "field 'relativeLayout_popup'", RelativeLayout.class);
        gameDetailFragment.videoView = (VideoView) Utils.findRequiredViewAsType(view, 2131297050, "field 'videoView'", VideoView.class);
        gameDetailFragment.viewPager_screenShots = (ViewPager) Utils.findRequiredViewAsType(view, 2131297053, "field 'viewPager_screenShots'", ViewPager.class);
        gameDetailFragment.imageView_banner = (ImageView) Utils.findRequiredViewAsType(view, 2131296602, "field 'imageView_banner'", ImageView.class);
        gameDetailFragment.imageView_icon = (ImageView) Utils.findRequiredViewAsType(view, 2131296603, "field 'imageView_icon'", ImageView.class);
        gameDetailFragment.imageView_adult = (ImageView) Utils.findRequiredViewAsType(view, 2131296600, "field 'imageView_adult'", ImageView.class);
        gameDetailFragment.imageView_recommend = (ImageView) Utils.findRequiredViewAsType(view, 2131296605, "field 'imageView_recommend'", ImageView.class);
        gameDetailFragment.imageView_android = (ImageView) Utils.findRequiredViewAsType(view, 2131296601, "field 'imageView_android'", ImageView.class);
        gameDetailFragment.imageView_ios = (ImageView) Utils.findRequiredViewAsType(view, 2131296604, "field 'imageView_ios'", ImageView.class);
        gameDetailFragment.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296943, "field 'textView_title'", TextView.class);
        gameDetailFragment.textView_publisher = (TextView) Utils.findRequiredViewAsType(view, 2131296941, "field 'textView_publisher'", TextView.class);
        gameDetailFragment.textView_gameSize = (TextView) Utils.findRequiredViewAsType(view, 2131296942, "field 'textView_gameSize'", TextView.class);
        gameDetailFragment.textView_version = (TextView) Utils.findRequiredViewAsType(view, 2131296945, "field 'textView_version'", TextView.class);
        gameDetailFragment.textView_versionDescription = (TextView) Utils.findRequiredViewAsType(view, 2131296944, "field 'textView_versionDescription'", TextView.class);
        gameDetailFragment.textView_description = (TextView) Utils.findRequiredViewAsType(view, 2131296938, "field 'textView_description'", TextView.class);
        gameDetailFragment.textView_commentCount = (TextView) Utils.findRequiredViewAsType(view, 2131296937, "field 'textView_commentCount'", TextView.class);
        gameDetailFragment.textView_likeCount = (TextView) Utils.findRequiredViewAsType(view, 2131296940, "field 'textView_likeCount'", TextView.class);
        gameDetailFragment.textView_download = (TextView) Utils.findRequiredViewAsType(view, 2131296939, "field 'textView_download'", TextView.class);
        gameDetailFragment.button_download = (Button) Utils.findRequiredViewAsType(view, 2131296376, "field 'button_download'", Button.class);
        gameDetailFragment.imageButton_gift = (ImageButton) Utils.findRequiredViewAsType(view, 2131296563, "field 'imageButton_gift'", ImageButton.class);
        gameDetailFragment.imageButton_like = (ImageButton) Utils.findRequiredViewAsType(view, 2131296564, "field 'imageButton_like'", ImageButton.class);
        gameDetailFragment.imageButton_comment = (ImageButton) Utils.findRequiredViewAsType(view, 2131296561, "field 'imageButton_comment'", ImageButton.class);
        gameDetailFragment.imageButton_playVideo = (ImageButton) Utils.findRequiredViewAsType(view, 2131296565, "field 'imageButton_playVideo'", ImageButton.class);
        gameDetailFragment.imageButton_closePopup = (ImageButton) Utils.findRequiredViewAsType(view, 2131296560, "field 'imageButton_closePopup'", ImageButton.class);
        gameDetailFragment.imageButton_descriptionHeightControl = (ImageButton) Utils.findRequiredViewAsType(view, 2131296562, "field 'imageButton_descriptionHeightControl'", ImageButton.class);
        gameDetailFragment.imageButton_versionDescriptionHeightControl = (ImageButton) Utils.findRequiredViewAsType(view, 2131296566, "field 'imageButton_versionDescriptionHeightControl'", ImageButton.class);
        gameDetailFragment.recyclerView_screenShots = (SnapRecyclerView) Utils.findRequiredViewAsType(view, 2131296764, "field 'recyclerView_screenShots'", SnapRecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        GameDetailFragment gameDetailFragment = this.pf;
        if (gameDetailFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.pf = null;
        gameDetailFragment.appBarLayout = null;
        gameDetailFragment.collapsingToolbarLayout = null;
        gameDetailFragment.toolbar = null;
        gameDetailFragment.nestedScrollView = null;
        gameDetailFragment.frameLayout_banner = null;
        gameDetailFragment.relativeLayout_popup = null;
        gameDetailFragment.videoView = null;
        gameDetailFragment.viewPager_screenShots = null;
        gameDetailFragment.imageView_banner = null;
        gameDetailFragment.imageView_icon = null;
        gameDetailFragment.imageView_adult = null;
        gameDetailFragment.imageView_recommend = null;
        gameDetailFragment.imageView_android = null;
        gameDetailFragment.imageView_ios = null;
        gameDetailFragment.textView_title = null;
        gameDetailFragment.textView_publisher = null;
        gameDetailFragment.textView_gameSize = null;
        gameDetailFragment.textView_version = null;
        gameDetailFragment.textView_versionDescription = null;
        gameDetailFragment.textView_description = null;
        gameDetailFragment.textView_commentCount = null;
        gameDetailFragment.textView_likeCount = null;
        gameDetailFragment.textView_download = null;
        gameDetailFragment.button_download = null;
        gameDetailFragment.imageButton_gift = null;
        gameDetailFragment.imageButton_like = null;
        gameDetailFragment.imageButton_comment = null;
        gameDetailFragment.imageButton_playVideo = null;
        gameDetailFragment.imageButton_closePopup = null;
        gameDetailFragment.imageButton_descriptionHeightControl = null;
        gameDetailFragment.imageButton_versionDescriptionHeightControl = null;
        gameDetailFragment.recyclerView_screenShots = null;
    }
}
