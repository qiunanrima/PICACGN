package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/EpisodeViewHolder_ViewBinding.class */
public class EpisodeViewHolder_ViewBinding implements Unbinder {
    private EpisodeViewHolder sO;

    @UiThread
    public EpisodeViewHolder_ViewBinding(EpisodeViewHolder episodeViewHolder, View view) {
        this.sO = episodeViewHolder;
        episodeViewHolder.button_epsiodeTitle = (Button) Utils.findRequiredViewAsType(view, 2131296375, "field 'button_epsiodeTitle'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        EpisodeViewHolder episodeViewHolder = this.sO;
        if (episodeViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sO = null;
        episodeViewHolder.button_epsiodeTitle = null;
    }
}
