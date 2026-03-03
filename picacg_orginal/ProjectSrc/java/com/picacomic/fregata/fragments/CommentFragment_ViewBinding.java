package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/CommentFragment_ViewBinding.class */
public class CommentFragment_ViewBinding implements Unbinder {
    private CommentFragment oC;

    @UiThread
    public CommentFragment_ViewBinding(CommentFragment commentFragment, View view) {
        this.oC = commentFragment;
        commentFragment.linearLayout_noComment = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296672, "field 'linearLayout_noComment'", LinearLayout.class);
        commentFragment.linearLayout_inputBar = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296671, "field 'linearLayout_inputBar'", LinearLayout.class);
        commentFragment.linearLayout_commentPage = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296673, "field 'linearLayout_commentPage'", LinearLayout.class);
        commentFragment.recyclerView_comments = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296762, "field 'recyclerView_comments'", RecyclerView.class);
        commentFragment.imageView_empty = (ImageView) Utils.findRequiredViewAsType(view, 2131296587, "field 'imageView_empty'", ImageView.class);
        commentFragment.textView_totalPage = (TextView) Utils.findRequiredViewAsType(view, 2131296923, "field 'textView_totalPage'", TextView.class);
        commentFragment.editText_currentPage = (EditText) Utils.findRequiredViewAsType(view, 2131296457, "field 'editText_currentPage'", EditText.class);
        commentFragment.editText_inputField = (EditText) Utils.findRequiredViewAsType(view, 2131296458, "field 'editText_inputField'", EditText.class);
        commentFragment.button_postComment = (Button) Utils.findRequiredViewAsType(view, 2131296357, "field 'button_postComment'", Button.class);
        commentFragment.button_replyCancel = (Button) Utils.findRequiredViewAsType(view, 2131296356, "field 'button_replyCancel'", Button.class);
        commentFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CommentFragment commentFragment = this.oC;
        if (commentFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.oC = null;
        commentFragment.linearLayout_noComment = null;
        commentFragment.linearLayout_inputBar = null;
        commentFragment.linearLayout_commentPage = null;
        commentFragment.recyclerView_comments = null;
        commentFragment.imageView_empty = null;
        commentFragment.textView_totalPage = null;
        commentFragment.editText_currentPage = null;
        commentFragment.editText_inputField = null;
        commentFragment.button_postComment = null;
        commentFragment.button_replyCancel = null;
        commentFragment.toolbar = null;
    }
}
