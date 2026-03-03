package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/LeaderboardKnightFragment_ViewBinding.class */
public class LeaderboardKnightFragment_ViewBinding implements Unbinder {
    private LeaderboardKnightFragment pH;

    @UiThread
    public LeaderboardKnightFragment_ViewBinding(LeaderboardKnightFragment leaderboardKnightFragment, View view) {
        this.pH = leaderboardKnightFragment;
        leaderboardKnightFragment.recyclerView_knight = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296765, "field 'recyclerView_knight'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        LeaderboardKnightFragment leaderboardKnightFragment = this.pH;
        if (leaderboardKnightFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.pH = null;
        leaderboardKnightFragment.recyclerView_knight = null;
    }
}
