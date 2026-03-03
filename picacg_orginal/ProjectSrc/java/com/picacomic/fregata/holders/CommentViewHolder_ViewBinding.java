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
/* loaded from: picacg_1.jar:com/picacomic/fregata/holders/CommentViewHolder_ViewBinding.class */
public class CommentViewHolder_ViewBinding implements Unbinder {
    private CommentViewHolder sN;

    @UiThread
    public CommentViewHolder_ViewBinding(CommentViewHolder commentViewHolder, View view) {
        this.sN = commentViewHolder;
        commentViewHolder.textView_content = (TextView) Utils.findRequiredViewAsType(view, 2131296906, "field 'textView_content'", TextView.class);
        commentViewHolder.textView_likeCount = (TextView) Utils.findRequiredViewAsType(view, 2131296910, "field 'textView_likeCount'", TextView.class);
        commentViewHolder.textView_replyCount = (TextView) Utils.findRequiredViewAsType(view, 2131296912, "field 'textView_replyCount'", TextView.class);
        commentViewHolder.textView_createdDate = (TextView) Utils.findRequiredViewAsType(view, 2131296907, "field 'textView_createdDate'", TextView.class);
        commentViewHolder.textView_username = (TextView) Utils.findRequiredViewAsType(view, 2131296914, "field 'textView_username'", TextView.class);
        commentViewHolder.textView_floor = (TextView) Utils.findRequiredViewAsType(view, 2131296908, "field 'textView_floor'", TextView.class);
        commentViewHolder.textView_level = (TextView) Utils.findRequiredViewAsType(view, 2131296909, "field 'textView_level'", TextView.class);
        commentViewHolder.textView_title = (TextView) Utils.findRequiredViewAsType(view, 2131296913, "field 'textView_title'", TextView.class);
        commentViewHolder.imageView_likeIcon = (ImageView) Utils.findRequiredViewAsType(view, 2131296588, "field 'imageView_likeIcon'", ImageView.class);
        commentViewHolder.imageView_userThumb = (ImageView) Utils.findRequiredViewAsType(view, 2131296591, "field 'imageView_userThumb'", ImageView.class);
        commentViewHolder.imageView_userVerified = (ImageView) Utils.findRequiredViewAsType(view, 2131296592, "field 'imageView_userVerified'", ImageView.class);
        commentViewHolder.linearLayout_reply = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296674, "field 'linearLayout_reply'", LinearLayout.class);
        commentViewHolder.button_viewMore = (Button) Utils.findRequiredViewAsType(view, 2131296362, "field 'button_viewMore'", Button.class);
        commentViewHolder.textView_noReply = (TextView) Utils.findRequiredViewAsType(view, 2131296911, "field 'textView_noReply'", TextView.class);
        commentViewHolder.linearLayout_tools = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296675, "field 'linearLayout_tools'", LinearLayout.class);
        commentViewHolder.button_tools = (Button) Utils.findRequiredViewAsType(view, 2131296360, "field 'button_tools'", Button.class);
        commentViewHolder.button_dirty = (Button) Utils.findRequiredViewAsType(view, 2131296358, "field 'button_dirty'", Button.class);
        commentViewHolder.button_top = (Button) Utils.findRequiredViewAsType(view, 2131296361, "field 'button_top'", Button.class);
        commentViewHolder.button_hide = (Button) Utils.findRequiredViewAsType(view, 2131296359, "field 'button_hide'", Button.class);
        commentViewHolder.imageView_option = (ImageView) Utils.findRequiredViewAsType(view, 2131296589, "field 'imageView_option'", ImageView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CommentViewHolder commentViewHolder = this.sN;
        if (commentViewHolder == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.sN = null;
        commentViewHolder.textView_content = null;
        commentViewHolder.textView_likeCount = null;
        commentViewHolder.textView_replyCount = null;
        commentViewHolder.textView_createdDate = null;
        commentViewHolder.textView_username = null;
        commentViewHolder.textView_floor = null;
        commentViewHolder.textView_level = null;
        commentViewHolder.textView_title = null;
        commentViewHolder.imageView_likeIcon = null;
        commentViewHolder.imageView_userThumb = null;
        commentViewHolder.imageView_userVerified = null;
        commentViewHolder.linearLayout_reply = null;
        commentViewHolder.button_viewMore = null;
        commentViewHolder.textView_noReply = null;
        commentViewHolder.linearLayout_tools = null;
        commentViewHolder.button_tools = null;
        commentViewHolder.button_dirty = null;
        commentViewHolder.button_top = null;
        commentViewHolder.button_hide = null;
        commentViewHolder.imageView_option = null;
    }
}
