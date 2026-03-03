package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/ApkVersionListViewHolder_ViewBinding.class */
public class ApkVersionListViewHolder_ViewBinding implements Unbinder {
    private ApkVersionListViewHolder sl;

    @UiThread
    public ApkVersionListViewHolder_ViewBinding(ApkVersionListViewHolder apkVersionListViewHolder, View view) {
        this.sl = apkVersionListViewHolder;
        apkVersionListViewHolder.textView_version = (TextView) Utils.findRequiredViewAsType(view, 2131296845, "field 'textView_version'", TextView.class);
        apkVersionListViewHolder.textView_content = (TextView) Utils.findRequiredViewAsType(view, 2131296842, "field 'textView_content'", TextView.class);
        apkVersionListViewHolder.textView_timestamp = (TextView) Utils.findRequiredViewAsType(view, 2131296843, "field 'textView_timestamp'", TextView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        ApkVersionListViewHolder apkVersionListViewHolder = this.sl;
        if (apkVersionListViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sl = null;
        apkVersionListViewHolder.textView_version = null;
        apkVersionListViewHolder.textView_content = null;
        apkVersionListViewHolder.textView_timestamp = null;
    }
}
