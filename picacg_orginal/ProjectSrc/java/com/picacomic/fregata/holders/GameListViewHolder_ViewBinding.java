package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/GameListViewHolder_ViewBinding.class */
public class GameListViewHolder_ViewBinding implements Unbinder {
    private GameListViewHolder sP;

    @UiThread
    public GameListViewHolder_ViewBinding(GameListViewHolder gameListViewHolder, View view) {
        this.sP = gameListViewHolder;
        gameListViewHolder.imageView_banner = (ImageView) Utils.findRequiredViewAsType(view, 2131296608, "field 'imageView_banner'", ImageView.class);
        gameListViewHolder.imageView_adult = (ImageView) Utils.findRequiredViewAsType(view, 2131296607, "field 'imageView_adult'", ImageView.class);
        gameListViewHolder.imageView_recommend = (ImageView) Utils.findRequiredViewAsType(view, 2131296609, "field 'imageView_recommend'", ImageView.class);
        gameListViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296947, "field 'textView_title'", TextView.class);
        gameListViewHolder.textView_publisher = (TextView) Utils.findRequiredViewAsType(view, 2131296946, "field 'textView_publisher'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        GameListViewHolder gameListViewHolder = this.sP;
        if (gameListViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sP = null;
        gameListViewHolder.imageView_banner = null;
        gameListViewHolder.imageView_adult = null;
        gameListViewHolder.imageView_recommend = null;
        gameListViewHolder.textView_title = null;
        gameListViewHolder.textView_publisher = null;
    }
}
