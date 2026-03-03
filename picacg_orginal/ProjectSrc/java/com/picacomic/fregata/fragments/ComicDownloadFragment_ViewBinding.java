package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ComicDownloadFragment_ViewBinding.class */
public class ComicDownloadFragment_ViewBinding implements Unbinder {
    private ComicDownloadFragment nx;

    @UiThread
    public ComicDownloadFragment_ViewBinding(ComicDownloadFragment comicDownloadFragment, View view) {
        this.nx = comicDownloadFragment;
        comicDownloadFragment.button_download = (Button) Utils.findRequiredViewAsType(view, 2131296326, "field 'button_download'", Button.class);
        comicDownloadFragment.button_manage = (Button) Utils.findRequiredViewAsType(view, 2131296327, "field 'button_manage'", Button.class);
        comicDownloadFragment.textView_testBroadcast = (TextView) Utils.findRequiredViewAsType(view, 2131296889, "field 'textView_testBroadcast'", TextView.class);
        comicDownloadFragment.recyclerView_episode = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296759, "field 'recyclerView_episode'", RecyclerView.class);
        comicDownloadFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ComicDownloadFragment comicDownloadFragment = this.nx;
        if (comicDownloadFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.nx = null;
        comicDownloadFragment.button_download = null;
        comicDownloadFragment.button_manage = null;
        comicDownloadFragment.textView_testBroadcast = null;
        comicDownloadFragment.recyclerView_episode = null;
        comicDownloadFragment.toolbar = null;
    }
}
