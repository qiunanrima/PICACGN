package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ComicDetailFragment_ViewBinding.class */
public class ComicDetailFragment_ViewBinding implements Unbinder {
    private ComicDetailFragment ns;

    @UiThread
    public ComicDetailFragment_ViewBinding(ComicDetailFragment comicDetailFragment, View view) {
        this.ns = comicDetailFragment;
        comicDetailFragment.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(view, 2131296430, "field 'coordinatorLayout'", CoordinatorLayout.class);
        comicDetailFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        comicDetailFragment.nestedScrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, 2131296790, "field 'nestedScrollView'", NestedScrollView.class);
        comicDetailFragment.linearLayout_tags = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296659, "field 'linearLayout_tags'", LinearLayout.class);
        comicDetailFragment.recyclerView_episode = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296758, "field 'recyclerView_episode'", RecyclerView.class);
        comicDetailFragment.recyclerView_recommendation = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296768, "field 'recyclerView_recommendation'", RecyclerView.class);
        comicDetailFragment.imageView_cover = (ImageView) Utils.findRequiredViewAsType(view, 2131296580, "field 'imageView_cover'", ImageView.class);
        comicDetailFragment.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296886, "field 'textView_title'", TextView.class);
        comicDetailFragment.textView_author = (TextView) Utils.findRequiredViewAsType(view, 2131296878, "field 'textView_author'", TextView.class);
        comicDetailFragment.textView_translate = (TextView) Utils.findRequiredViewAsType(view, 2131296887, "field 'textView_translate'", TextView.class);
        comicDetailFragment.textView_likeCount = (TextView) Utils.findRequiredViewAsType(view, 2131296883, "field 'textView_likeCount'", TextView.class);
        comicDetailFragment.textView_viewCount = (TextView) Utils.findRequiredViewAsType(view, 2131296888, "field 'textView_viewCount'", TextView.class);
        comicDetailFragment.textView_categories = (TextView) Utils.findRequiredViewAsType(view, 2131296879, "field 'textView_categories'", TextView.class);
        comicDetailFragment.textView_description = (TextView) Utils.findRequiredViewAsType(view, 2131296881, "field 'textView_description'", TextView.class);
        comicDetailFragment.textView_commentCount = (TextView) Utils.findRequiredViewAsType(view, 2131296880, "field 'textView_commentCount'", TextView.class);
        comicDetailFragment.textView_timestamp = (TextView) Utils.findRequiredViewAsType(view, 2131296884, "field 'textView_timestamp'", TextView.class);
        comicDetailFragment.textView_knight = (TextView) Utils.findRequiredViewAsType(view, 2131296882, "field 'textView_knight'", TextView.class);
        comicDetailFragment.imageView_knightAvatar = (ImageView) Utils.findRequiredViewAsType(view, 2131296581, "field 'imageView_knightAvatar'", ImageView.class);
        comicDetailFragment.imageView_knightVerified = (ImageView) Utils.findRequiredViewAsType(view, 2131296582, "field 'imageView_knightVerified'", ImageView.class);
        comicDetailFragment.button_startRead = (Button) Utils.findRequiredViewAsType(view, 2131296325, "field 'button_startRead'", Button.class);
        comicDetailFragment.button_moreEpisode = (Button) Utils.findRequiredViewAsType(view, 2131296324, "field 'button_moreEpisode'", Button.class);
        comicDetailFragment.imageButton_bookmark = (ImageButton) Utils.findRequiredViewAsType(view, 2131296549, "field 'imageButton_bookmark'", ImageButton.class);
        comicDetailFragment.imageButton_descriptionHeightControl = (ImageButton) Utils.findRequiredViewAsType(view, 2131296551, "field 'imageButton_descriptionHeightControl'", ImageButton.class);
        comicDetailFragment.imageButton_tagHeightControl = (ImageButton) Utils.findRequiredViewAsType(view, 2131296553, "field 'imageButton_tagHeightControl'", ImageButton.class);
        comicDetailFragment.imageButton_comment = (ImageButton) Utils.findRequiredViewAsType(view, 2131296550, "field 'imageButton_comment'", ImageButton.class);
        comicDetailFragment.imageButton_like = (ImageButton) Utils.findRequiredViewAsType(view, 2131296552, "field 'imageButton_like'", ImageButton.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComicDetailFragment comicDetailFragment = this.ns;
        if (comicDetailFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.ns = null;
        comicDetailFragment.coordinatorLayout = null;
        comicDetailFragment.toolbar = null;
        comicDetailFragment.nestedScrollView = null;
        comicDetailFragment.linearLayout_tags = null;
        comicDetailFragment.recyclerView_episode = null;
        comicDetailFragment.recyclerView_recommendation = null;
        comicDetailFragment.imageView_cover = null;
        comicDetailFragment.textView_title = null;
        comicDetailFragment.textView_author = null;
        comicDetailFragment.textView_translate = null;
        comicDetailFragment.textView_likeCount = null;
        comicDetailFragment.textView_viewCount = null;
        comicDetailFragment.textView_categories = null;
        comicDetailFragment.textView_description = null;
        comicDetailFragment.textView_commentCount = null;
        comicDetailFragment.textView_timestamp = null;
        comicDetailFragment.textView_knight = null;
        comicDetailFragment.imageView_knightAvatar = null;
        comicDetailFragment.imageView_knightVerified = null;
        comicDetailFragment.button_startRead = null;
        comicDetailFragment.button_moreEpisode = null;
        comicDetailFragment.imageButton_bookmark = null;
        comicDetailFragment.imageButton_descriptionHeightControl = null;
        comicDetailFragment.imageButton_tagHeightControl = null;
        comicDetailFragment.imageButton_comment = null;
        comicDetailFragment.imageButton_like = null;
    }
}
