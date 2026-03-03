package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/AnnouncementListFragment_ViewBinding.class */
public class AnnouncementListFragment_ViewBinding implements Unbinder {
    private AnnouncementListFragment jT;

    @UiThread
    public AnnouncementListFragment_ViewBinding(AnnouncementListFragment announcementListFragment, View view) {
        this.jT = announcementListFragment;
        announcementListFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        announcementListFragment.recyclerView_apkVersions = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296751, "field 'recyclerView_apkVersions'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AnnouncementListFragment announcementListFragment = this.jT;
        if (announcementListFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.jT = null;
        announcementListFragment.toolbar = null;
        announcementListFragment.recyclerView_apkVersions = null;
    }
}
