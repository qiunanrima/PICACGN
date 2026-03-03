package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/AnnouncementContainerView_ViewBinding.class */
public class AnnouncementContainerView_ViewBinding implements Unbinder {
    private AnnouncementContainerView si;

    @UiThread
    public AnnouncementContainerView_ViewBinding(AnnouncementContainerView announcementContainerView, View view) {
        this.si = announcementContainerView;
        announcementContainerView.imageView_icon = (ImageView) Utils.findRequiredViewAsType(view, 2131296572, "field 'imageView_icon'", ImageView.class);
        announcementContainerView.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296837, "field 'textView_title'", TextView.class);
        announcementContainerView.textView_count = (TextView) Utils.findRequiredViewAsType(view, 2131296838, "field 'textView_count'", TextView.class);
        announcementContainerView.linearLayout_content = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296640, "field 'linearLayout_content'", LinearLayout.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        AnnouncementContainerView announcementContainerView = this.si;
        if (announcementContainerView == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.si = null;
        announcementContainerView.imageView_icon = null;
        announcementContainerView.textView_title = null;
        announcementContainerView.textView_count = null;
        announcementContainerView.linearLayout_content = null;
    }
}
