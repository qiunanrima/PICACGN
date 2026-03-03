package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/LeaderboardPopularFragment_ViewBinding.class */
public class LeaderboardPopularFragment_ViewBinding implements Unbinder {
    private LeaderboardPopularFragment pO;

    @UiThread
    public LeaderboardPopularFragment_ViewBinding(LeaderboardPopularFragment leaderboardPopularFragment, View view) {
        this.pO = leaderboardPopularFragment;
        leaderboardPopularFragment.radioGroup_time = (RadioGroup) Utils.findRequiredViewAsType(view, 2131296746, "field 'radioGroup_time'", RadioGroup.class);
        leaderboardPopularFragment.radioButton_24hr = (RadioButton) Utils.findRequiredViewAsType(view, 2131296743, "field 'radioButton_24hr'", RadioButton.class);
        leaderboardPopularFragment.radioButton_7days = (RadioButton) Utils.findRequiredViewAsType(view, 2131296745, "field 'radioButton_7days'", RadioButton.class);
        leaderboardPopularFragment.radioButton_30days = (RadioButton) Utils.findRequiredViewAsType(view, 2131296744, "field 'radioButton_30days'", RadioButton.class);
        leaderboardPopularFragment.recyclerView_popular = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296766, "field 'recyclerView_popular'", RecyclerView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        LeaderboardPopularFragment leaderboardPopularFragment = this.pO;
        if (leaderboardPopularFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.pO = null;
        leaderboardPopularFragment.radioGroup_time = null;
        leaderboardPopularFragment.radioButton_24hr = null;
        leaderboardPopularFragment.radioButton_7days = null;
        leaderboardPopularFragment.radioButton_30days = null;
        leaderboardPopularFragment.recyclerView_popular = null;
    }
}
