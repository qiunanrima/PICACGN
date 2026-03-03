package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/GameFragment_ViewBinding.class */
public class GameFragment_ViewBinding implements Unbinder {
    private GameFragment pm;

    @UiThread
    public GameFragment_ViewBinding(GameFragment gameFragment, View view) {
        this.pm = gameFragment;
        gameFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        gameFragment.recyclerView_games = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296763, "field 'recyclerView_games'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        GameFragment gameFragment = this.pm;
        if (gameFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.pm = null;
        gameFragment.toolbar = null;
        gameFragment.recyclerView_games = null;
    }
}
