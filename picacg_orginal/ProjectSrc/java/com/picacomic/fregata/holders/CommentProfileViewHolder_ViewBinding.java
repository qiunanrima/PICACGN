package com.picacomic.fregata.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CommentProfileViewHolder_ViewBinding.class */
public class CommentProfileViewHolder_ViewBinding implements Unbinder {
    private CommentProfileViewHolder sJ;

    @UiThread
    public CommentProfileViewHolder_ViewBinding(CommentProfileViewHolder commentProfileViewHolder, View view) {
        this.sJ = commentProfileViewHolder;
        commentProfileViewHolder.textView_content = (TextView) Utils.findRequiredViewAsType(view, 2131296906, "field 'textView_content'", TextView.class);
        commentProfileViewHolder.textView_likeCount = (TextView) Utils.findRequiredViewAsType(view, 2131296910, "field 'textView_likeCount'", TextView.class);
        commentProfileViewHolder.textView_replyCount = (TextView) Utils.findRequiredViewAsType(view, 2131296912, "field 'textView_replyCount'", TextView.class);
        commentProfileViewHolder.textView_createdDate = (TextView) Utils.findRequiredViewAsType(view, 2131296907, "field 'textView_createdDate'", TextView.class);
        commentProfileViewHolder.textView_username = (TextView) Utils.findRequiredViewAsType(view, 2131296914, "field 'textView_username'", TextView.class);
        commentProfileViewHolder.textView_floor = (TextView) Utils.findRequiredViewAsType(view, 2131296908, "field 'textView_floor'", TextView.class);
        commentProfileViewHolder.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296909, "field 'textView_level'", TextView.class);
        commentProfileViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296913, "field 'textView_title'", TextView.class);
        commentProfileViewHolder.imageView_likeIcon = (ImageView) Utils.findRequiredViewAsType(view, 2131296588, "field 'imageView_likeIcon'", ImageView.class);
        commentProfileViewHolder.imageView_userThumb = (ImageView) Utils.findRequiredViewAsType(view, 2131296591, "field 'imageView_userThumb'", ImageView.class);
        commentProfileViewHolder.imageView_userVerified = (ImageView) Utils.findRequiredViewAsType(view, 2131296592, "field 'imageView_userVerified'", ImageView.class);
        commentProfileViewHolder.linearLayout_reply = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296674, "field 'linearLayout_reply'", LinearLayout.class);
        commentProfileViewHolder.button_viewMore = (Button) Utils.findRequiredViewAsType(view, 2131296362, "field 'button_viewMore'", Button.class);
        commentProfileViewHolder.textView_noReply = (TextView) Utils.findRequiredViewAsType(view, 2131296911, "field 'textView_noReply'", TextView.class);
        commentProfileViewHolder.textView_viewContentPage = (TextView) Utils.findRequiredViewAsType(view, 2131296915, "field 'textView_viewContentPage'", TextView.class);
        commentProfileViewHolder.button_hide = (Button) Utils.findRequiredViewAsType(view, 2131296359, "field 'button_hide'", Button.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CommentProfileViewHolder commentProfileViewHolder = this.sJ;
        if (commentProfileViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sJ = null;
        commentProfileViewHolder.textView_content = null;
        commentProfileViewHolder.textView_likeCount = null;
        commentProfileViewHolder.textView_replyCount = null;
        commentProfileViewHolder.textView_createdDate = null;
        commentProfileViewHolder.textView_username = null;
        commentProfileViewHolder.textView_floor = null;
        commentProfileViewHolder.textView_level = null;
        commentProfileViewHolder.textView_title = null;
        commentProfileViewHolder.imageView_likeIcon = null;
        commentProfileViewHolder.imageView_userThumb = null;
        commentProfileViewHolder.imageView_userVerified = null;
        commentProfileViewHolder.linearLayout_reply = null;
        commentProfileViewHolder.button_viewMore = null;
        commentProfileViewHolder.textView_noReply = null;
        commentProfileViewHolder.textView_viewContentPage = null;
        commentProfileViewHolder.button_hide = null;
    }
}
