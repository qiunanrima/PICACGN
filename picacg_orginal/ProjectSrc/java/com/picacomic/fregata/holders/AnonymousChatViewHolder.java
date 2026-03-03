package com.picacomic.fregata.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.objects.AnonymousChatDataObject;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AnonymousChatViewHolder.class */
public class AnonymousChatViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = "AnonymousChatViewHolder";
    @BindView(2131296839)
    TextView textView_message;

    public AnonymousChatViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void a(AnonymousChatDataObject anonymousChatDataObject) {
        if (anonymousChatDataObject == null || anonymousChatDataObject.getMessage() == null) {
            return;
        }
        TextView textView = this.textView_message;
        textView.setText(anonymousChatDataObject.getMessage() + "");
    }
}
