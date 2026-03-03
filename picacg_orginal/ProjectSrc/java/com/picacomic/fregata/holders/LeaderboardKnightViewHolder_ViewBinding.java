package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/LeaderboardKnightViewHolder_ViewBinding.class */
public class LeaderboardKnightViewHolder_ViewBinding implements Unbinder {
    private LeaderboardKnightViewHolder sR;

    @UiThread
    public LeaderboardKnightViewHolder_ViewBinding(LeaderboardKnightViewHolder leaderboardKnightViewHolder, View view) {
        this.sR = leaderboardKnightViewHolder;
        leaderboardKnightViewHolder.textView_order = (TextView) Utils.findRequiredViewAsType(view, 2131296952, "field 'textView_order'", TextView.class);
        leaderboardKnightViewHolder.textView_name = (TextView) Utils.findRequiredViewAsType(view, 2131296951, "field 'textView_name'", TextView.class);
        leaderboardKnightViewHolder.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296950, "field 'textView_level'", TextView.class);
        leaderboardKnightViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296953, "field 'textView_title'", TextView.class);
        leaderboardKnightViewHolder.textView_comic = (TextView) Utils.findRequiredViewAsType(view, 2131296949, "field 'textView_comic'", TextView.class);
        leaderboardKnightViewHolder.imageView_characterIcon = (ImageView) Utils.findRequiredViewAsType(view, 2131296613, "field 'imageView_characterIcon'", ImageView.class);
        leaderboardKnightViewHolder.imageView_order = (ImageView) Utils.findRequiredViewAsType(view, 2131296612, "field 'imageView_order'", ImageView.class);
        leaderboardKnightViewHolder.imageView_avatar = (ImageView) Utils.findRequiredViewAsType(view, 2131296611, "field 'imageView_avatar'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        LeaderboardKnightViewHolder leaderboardKnightViewHolder = this.sR;
        if (leaderboardKnightViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sR = null;
        leaderboardKnightViewHolder.textView_order = null;
        leaderboardKnightViewHolder.textView_name = null;
        leaderboardKnightViewHolder.textView_level = null;
        leaderboardKnightViewHolder.textView_title = null;
        leaderboardKnightViewHolder.textView_comic = null;
        leaderboardKnightViewHolder.imageView_characterIcon = null;
        leaderboardKnightViewHolder.imageView_order = null;
        leaderboardKnightViewHolder.imageView_avatar = null;
    }
}
