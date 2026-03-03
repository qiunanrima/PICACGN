package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ComicListFragment_ViewBinding.class */
public class ComicListFragment_ViewBinding implements Unbinder {
    private ComicListFragment nU;

    @UiThread
    public ComicListFragment_ViewBinding(ComicListFragment comicListFragment, View view) {
        this.nU = comicListFragment;
        comicListFragment.frameLayout_noComics = (FrameLayout) Utils.findRequiredViewAsType(view, 2131296511, "field 'frameLayout_noComics'", FrameLayout.class);
        comicListFragment.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(view, 2131296430, "field 'coordinatorLayout'", CoordinatorLayout.class);
        comicListFragment.recyclerView_comicList = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296760, "field 'recyclerView_comicList'", RecyclerView.class);
        comicListFragment.imageView_empty = (ImageView) Utils.findRequiredViewAsType(view, 2131296583, "field 'imageView_empty'", ImageView.class);
        comicListFragment.textView_totalPage = (TextView) Utils.findRequiredViewAsType(view, 2131296897, "field 'textView_totalPage'", TextView.class);
        comicListFragment.editText_currentPage = (EditText) Utils.findRequiredViewAsType(view, 2131296456, "field 'editText_currentPage'", EditText.class);
        comicListFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        comicListFragment.buttons_filters = (Button[]) Utils.arrayOf((Button) Utils.findRequiredViewAsType(view, 2131296330, "field 'buttons_filters'", Button.class), (Button) Utils.findRequiredViewAsType(view, 2131296333, "field 'buttons_filters'", Button.class), (Button) Utils.findRequiredViewAsType(view, 2131296328, "field 'buttons_filters'", Button.class), (Button) Utils.findRequiredViewAsType(view, 2131296332, "field 'buttons_filters'", Button.class), (Button) Utils.findRequiredViewAsType(view, 2131296334, "field 'buttons_filters'", Button.class), (Button) Utils.findRequiredViewAsType(view, 2131296329, "field 'buttons_filters'", Button.class), (Button) Utils.findRequiredViewAsType(view, 2131296331, "field 'buttons_filters'", Button.class), (Button) Utils.findRequiredViewAsType(view, 2131296335, "field 'buttons_filters'", Button.class));
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComicListFragment comicListFragment = this.nU;
        if (comicListFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.nU = null;
        comicListFragment.frameLayout_noComics = null;
        comicListFragment.coordinatorLayout = null;
        comicListFragment.recyclerView_comicList = null;
        comicListFragment.imageView_empty = null;
        comicListFragment.textView_totalPage = null;
        comicListFragment.editText_currentPage = null;
        comicListFragment.toolbar = null;
        comicListFragment.buttons_filters = null;
    }
}
