package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AnnouncementCellView_ViewBinding.class */
public class AnnouncementCellView_ViewBinding implements Unbinder {
    private AnnouncementCellView se;

    @UiThread
    public AnnouncementCellView_ViewBinding(AnnouncementCellView announcementCellView, View view) {
        this.se = announcementCellView;
        announcementCellView.imageView_image = (ImageView) Utils.findRequiredViewAsType(view, 2131296571, "field 'imageView_image'", ImageView.class);
        announcementCellView.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296836, "field 'textView_title'", TextView.class);
        announcementCellView.textView_description = (TextView) Utils.findRequiredViewAsType(view, 2131296835, "field 'textView_description'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AnnouncementCellView announcementCellView = this.se;
        if (announcementCellView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.se = null;
        announcementCellView.imageView_image = null;
        announcementCellView.textView_title = null;
        announcementCellView.textView_description = null;
    }
}
