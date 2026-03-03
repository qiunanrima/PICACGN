package com.picacomic.fregata.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.objects.DefaultCategoryObject;
import com.picacomic.fregata.utils.a;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.SquareWebview;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CategoryWebViewHolder.class */
public class CategoryWebViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "CategoryWebViewHolder";
    Context context;
    @BindView(2131296848)
    public TextView textView_title;
    @BindView(2131297066)
    public SquareWebview webview;

    public CategoryWebViewHolder(Context context, View view) {
        super(view);
        this.context = context;
        ButterKnife.bind(this, view);
    }

    public void a(DefaultCategoryObject defaultCategoryObject) {
        this.textView_title.setText(defaultCategoryObject.getTitle());
        g.k(this.webview);
        this.webview.loadUrl(a.dU());
    }
}
