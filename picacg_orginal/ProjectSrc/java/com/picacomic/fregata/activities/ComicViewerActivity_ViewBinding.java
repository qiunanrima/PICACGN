package com.picacomic.fregata.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.picacomic.fregata.utils.views.VerticalSeekBar;
/* loaded from: picacg_1.jar:com/picacomic/fregata/activities/ComicViewerActivity_ViewBinding.class */
public class ComicViewerActivity_ViewBinding implements Unbinder {
    private ComicViewerActivity iq;

    @UiThread
    public ComicViewerActivity_ViewBinding(ComicViewerActivity comicViewerActivity, View view) {
        this.iq = comicViewerActivity;
        comicViewerActivity.relativeLayout_toolbar = (RelativeLayout) Utils.findRequiredViewAsType(view, 2131296774, "field 'relativeLayout_toolbar'", RelativeLayout.class);
        comicViewerActivity.relativeLayout_leftPanel = (RelativeLayout) Utils.findRequiredViewAsType(view, 2131296773, "field 'relativeLayout_leftPanel'", RelativeLayout.class);
        comicViewerActivity.linearLayout_rightPanel = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296668, "field 'linearLayout_rightPanel'", LinearLayout.class);
        comicViewerActivity.linearLayout_bottomPanel = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296665, "field 'linearLayout_bottomPanel'", LinearLayout.class);
        comicViewerActivity.frameLayout_nightModeMask = (FrameLayout) Utils.findRequiredViewAsType(view, 2131296517, "field 'frameLayout_nightModeMask'", FrameLayout.class);
        comicViewerActivity.linearLayout_verticalPagingScrollbar = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296670, "field 'linearLayout_verticalPagingScrollbar'", LinearLayout.class);
        comicViewerActivity.linearLayout_horizontalPagingScrollbar = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296667, "field 'linearLayout_horizontalPagingScrollbar'", LinearLayout.class);
        comicViewerActivity.gridView_episodeDialog = (GridView) Utils.findRequiredViewAsType(view, 2131296533, "field 'gridView_episodeDialog'", GridView.class);
        comicViewerActivity.linearLayout_dialogAutoPaging = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296666, "field 'linearLayout_dialogAutoPaging'", LinearLayout.class);
        comicViewerActivity.button_nextPageBottom = (Button) Utils.findRequiredViewAsType(view, 2131296343, "field 'button_nextPageBottom'", Button.class);
        comicViewerActivity.button_nextPageRight = (Button) Utils.findRequiredViewAsType(view, 2131296344, "field 'button_nextPageRight'", Button.class);
        comicViewerActivity.button_previousPage = (Button) Utils.findRequiredViewAsType(view, 2131296349, "field 'button_previousPage'", Button.class);
        comicViewerActivity.button_panel = (Button) Utils.findRequiredViewAsType(view, 2131296346, "field 'button_panel'", Button.class);
        comicViewerActivity.button_panelLeftCorner = (Button) Utils.findRequiredViewAsType(view, 2131296347, "field 'button_panelLeftCorner'", Button.class);
        comicViewerActivity.frameLayout_gestureArea = (FrameLayout) Utils.findRequiredViewAsType(view, 2131296516, "field 'frameLayout_gestureArea'", FrameLayout.class);
        comicViewerActivity.button_nextEpisode = (Button) Utils.findRequiredViewAsType(view, 2131296342, "field 'button_nextEpisode'", Button.class);
        comicViewerActivity.button_previousEpisode = (Button) Utils.findRequiredViewAsType(view, 2131296348, "field 'button_previousEpisode'", Button.class);
        comicViewerActivity.imageButton_back = (ImageButton) Utils.findRequiredViewAsType(view, 2131296554, "field 'imageButton_back'", ImageButton.class);
        comicViewerActivity.button_hint = (Button) Utils.findRequiredViewAsType(view, 2131296341, "field 'button_hint'", Button.class);
        comicViewerActivity.button_share = (Button) Utils.findRequiredViewAsType(view, 2131296354, "field 'button_share'", Button.class);
        comicViewerActivity.button_download = (Button) Utils.findRequiredViewAsType(view, 2131296339, "field 'button_download'", Button.class);
        comicViewerActivity.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296904, "field 'textView_title'", TextView.class);
        comicViewerActivity.textView_page = (TextView) Utils.findRequiredViewAsType(view, 2131296903, "field 'textView_page'", TextView.class);
        comicViewerActivity.checkBox_brightnessSystem = (CheckBox) Utils.findRequiredViewAsType(view, 2131296417, "field 'checkBox_brightnessSystem'", CheckBox.class);
        comicViewerActivity.verticalSeekBar_brightness = (VerticalSeekBar) Utils.findRequiredViewAsType(view, 2131297049, "field 'verticalSeekBar_brightness'", VerticalSeekBar.class);
        comicViewerActivity.button_selectEpisode = (Button) Utils.findRequiredViewAsType(view, 2131296352, "field 'button_selectEpisode'", Button.class);
        comicViewerActivity.button_nightMode = (Button) Utils.findRequiredViewAsType(view, 2131296345, "field 'button_nightMode'", Button.class);
        comicViewerActivity.button_comment = (Button) Utils.findRequiredViewAsType(view, 2131296337, "field 'button_comment'", Button.class);
        comicViewerActivity.textView_horizontalPage = (TextView) Utils.findRequiredViewAsType(view, 2131296902, "field 'textView_horizontalPage'", TextView.class);
        comicViewerActivity.textView_verticalPage = (TextView) Utils.findRequiredViewAsType(view, 2131296905, "field 'textView_verticalPage'", TextView.class);
        comicViewerActivity.button_screenOrientation = (Button) Utils.findRequiredViewAsType(view, 2131296350, "field 'button_screenOrientation'", Button.class);
        comicViewerActivity.button_scrollOrientation = (Button) Utils.findRequiredViewAsType(view, 2131296351, "field 'button_scrollOrientation'", Button.class);
        comicViewerActivity.button_autoPaging = (Button) Utils.findRequiredViewAsType(view, 2131296336, "field 'button_autoPaging'", Button.class);
        comicViewerActivity.button_twoPageViewMode = (Button) Utils.findRequiredViewAsType(view, 2131296355, "field 'button_twoPageViewMode'", Button.class);
        comicViewerActivity.button_setting = (Button) Utils.findRequiredViewAsType(view, 2131296353, "field 'button_setting'", Button.class);
        comicViewerActivity.button_hide = (Button) Utils.findRequiredViewAsType(view, 2131296340, "field 'button_hide'", Button.class);
        comicViewerActivity.seekBar_horizontalPaging = (SeekBar) Utils.findRequiredViewAsType(view, 2131296805, "field 'seekBar_horizontalPaging'", SeekBar.class);
        comicViewerActivity.seekBar_verticalPaging = (SeekBar) Utils.findRequiredViewAsType(view, 2131296806, "field 'seekBar_verticalPaging'", SeekBar.class);
        comicViewerActivity.textView_dialogAutoPagingTitle = (TextView) Utils.findRequiredViewAsType(view, 2131296901, "field 'textView_dialogAutoPagingTitle'", TextView.class);
        comicViewerActivity.seekBar_dialogAutoPaging = (SeekBar) Utils.findRequiredViewAsType(view, 2131296804, "field 'seekBar_dialogAutoPaging'", SeekBar.class);
        comicViewerActivity.button_dialogAutoPagingStart = (Button) Utils.findRequiredViewAsType(view, 2131296338, "field 'button_dialogAutoPagingStart'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComicViewerActivity comicViewerActivity = this.iq;
        if (comicViewerActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.iq = null;
        comicViewerActivity.relativeLayout_toolbar = null;
        comicViewerActivity.relativeLayout_leftPanel = null;
        comicViewerActivity.linearLayout_rightPanel = null;
        comicViewerActivity.linearLayout_bottomPanel = null;
        comicViewerActivity.frameLayout_nightModeMask = null;
        comicViewerActivity.linearLayout_verticalPagingScrollbar = null;
        comicViewerActivity.linearLayout_horizontalPagingScrollbar = null;
        comicViewerActivity.gridView_episodeDialog = null;
        comicViewerActivity.linearLayout_dialogAutoPaging = null;
        comicViewerActivity.button_nextPageBottom = null;
        comicViewerActivity.button_nextPageRight = null;
        comicViewerActivity.button_previousPage = null;
        comicViewerActivity.button_panel = null;
        comicViewerActivity.button_panelLeftCorner = null;
        comicViewerActivity.frameLayout_gestureArea = null;
        comicViewerActivity.button_nextEpisode = null;
        comicViewerActivity.button_previousEpisode = null;
        comicViewerActivity.imageButton_back = null;
        comicViewerActivity.button_hint = null;
        comicViewerActivity.button_share = null;
        comicViewerActivity.button_download = null;
        comicViewerActivity.textView_title = null;
        comicViewerActivity.textView_page = null;
        comicViewerActivity.checkBox_brightnessSystem = null;
        comicViewerActivity.verticalSeekBar_brightness = null;
        comicViewerActivity.button_selectEpisode = null;
        comicViewerActivity.button_nightMode = null;
        comicViewerActivity.button_comment = null;
        comicViewerActivity.textView_horizontalPage = null;
        comicViewerActivity.textView_verticalPage = null;
        comicViewerActivity.button_screenOrientation = null;
        comicViewerActivity.button_scrollOrientation = null;
        comicViewerActivity.button_autoPaging = null;
        comicViewerActivity.button_twoPageViewMode = null;
        comicViewerActivity.button_setting = null;
        comicViewerActivity.button_hide = null;
        comicViewerActivity.seekBar_horizontalPaging = null;
        comicViewerActivity.seekBar_verticalPaging = null;
        comicViewerActivity.textView_dialogAutoPagingTitle = null;
        comicViewerActivity.seekBar_dialogAutoPaging = null;
        comicViewerActivity.button_dialogAutoPagingStart = null;
    }
}
