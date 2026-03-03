package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/LeaderboardPopularViewHolder_ViewBinding.class */
public class LeaderboardPopularViewHolder_ViewBinding implements Unbinder {
    private LeaderboardPopularViewHolder sS;

    @UiThread
    public LeaderboardPopularViewHolder_ViewBinding(LeaderboardPopularViewHolder leaderboardPopularViewHolder, View view) {
        this.sS = leaderboardPopularViewHolder;
        leaderboardPopularViewHolder.textView_order = (TextView) Utils.findRequiredViewAsType(view, 2131296957, "field 'textView_order'", TextView.class);
        leaderboardPopularViewHolder.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296956, "field 'textView_name'", TextView.class);
        leaderboardPopularViewHolder.textView_author = (TextView) Utils.findRequiredViewAsType(view, 2131296954, "field 'textView_author'", TextView.class);
        leaderboardPopularViewHolder.textView_category = (TextView) Utils.findRequiredViewAsType(view, 2131296955, "field 'textView_category'", TextView.class);
        leaderboardPopularViewHolder.textView_viewCount = (TextView) Utils.findRequiredViewAsType(view, 2131296958, "field 'textView_viewCount'", TextView.class);
        leaderboardPopularViewHolder.textView_viewCountTitle = (TextView) Utils.findRequiredViewAsType(view, 2131296959, "field 'textView_viewCountTitle'", TextView.class);
        leaderboardPopularViewHolder.imageView_order = (ImageView) Utils.findRequiredViewAsType(view, 2131296615, "field 'imageView_order'", ImageView.class);
        leaderboardPopularViewHolder.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296614, "field 'imageView_image'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        LeaderboardPopularViewHolder leaderboardPopularViewHolder = this.sS;
        if (leaderboardPopularViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sS = null;
        leaderboardPopularViewHolder.textView_order = null;
        leaderboardPopularViewHolder.textView_name = null;
        leaderboardPopularViewHolder.textView_author = null;
        leaderboardPopularViewHolder.textView_category = null;
        leaderboardPopularViewHolder.textView_viewCount = null;
        leaderboardPopularViewHolder.textView_viewCountTitle = null;
        leaderboardPopularViewHolder.imageView_order = null;
        leaderboardPopularViewHolder.imageView_image = null;
    }
}
