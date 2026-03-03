package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.a.e;
import com.picacomic.fregata.holders.CommentProfileViewHolder;
import com.picacomic.fregata.holders.CommentReplyView;
import com.picacomic.fregata.holders.CommentTopReplacementViewHolder;
import com.picacomic.fregata.holders.CommentViewHolder;
import com.picacomic.fregata.objects.CommentObject;
import com.picacomic.fregata.objects.CommentWithReplyObject;
import com.picacomic.fregata.objects.UserBasicObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/CommentRecyclerViewAdapter.class */
public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "CommentRecyclerViewAdapter";
    private final Context context;
    private e jA;
    private int jD;
    private UserBasicObject jH;
    private ArrayList<CommentWithReplyObject> jz;
    private final LayoutInflater mLayoutInflater;
    private int jB = -1;
    private int jF = -1;
    private boolean jC = true;
    private boolean jG = false;
    private int jE = 0;

    public CommentRecyclerViewAdapter(Context context, UserBasicObject userBasicObject, String str, ArrayList<CommentWithReplyObject> arrayList, e eVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.jH = userBasicObject;
        this.jz = arrayList;
        this.jA = eVar;
        if (arrayList != null) {
            this.jD = arrayList.size();
        } else {
            this.jD = 1;
        }
    }

    public void A(int i) {
        if (this.jz == null || this.jz.size() <= i) {
            return;
        }
        this.jF = i;
        notifyItemChanged(i);
    }

    public void B(int i) {
        this.jE = i;
    }

    public void a(int i, ArrayList<CommentObject> arrayList, boolean z) {
        this.jC = z;
        int i2 = this.jB;
        this.jB = i;
        if (i2 != -1) {
            notifyItemChanged(i2);
        }
        notifyItemChanged(this.jB);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.jz == null ? 0 : this.jz.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.jH == null) {
            if (this.jE > i) {
                return 2;
            }
            return (this.jz == null || this.jz.size() <= i || !this.jz.get(i).isTop()) ? 0 : 3;
        }
        return 1;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof CommentProfileViewHolder) {
            CommentProfileViewHolder commentProfileViewHolder = (CommentProfileViewHolder) viewHolder;
            if (this.jz == null || this.jz.size() <= i) {
                return;
            }
            commentProfileViewHolder.textView_content.setText(this.jz.get(i).getContent() + "");
            commentProfileViewHolder.textView_likeCount.setText(this.jz.get(i).getLikesCount() + "");
            commentProfileViewHolder.textView_replyCount.setText(this.jz.get(i).getChildsCount() + "");
            commentProfileViewHolder.textView_createdDate.setText(g.B(this.context, this.jz.get(i).getCreatedAt()));
            if (this.jH != null) {
                if (this.jH.getCharacter() == null || this.jH.getCharacter().equalsIgnoreCase("")) {
                    commentProfileViewHolder.imageView_userVerified.setVisibility(8);
                } else {
                    commentProfileViewHolder.imageView_userVerified.setVisibility(0);
                    Picasso.with(this.context).load(this.jH.getCharacter()).into(commentProfileViewHolder.imageView_userVerified);
                }
                commentProfileViewHolder.textView_username.setText(this.jH.getName() + "");
                commentProfileViewHolder.textView_title.setText("");
                commentProfileViewHolder.textView_level.setText(this.jH.getLevel() + "");
                if (this.jz.get(i).getComicId() != null) {
                    commentProfileViewHolder.textView_viewContentPage.setText(this.context.getResources().getString(2131689766) + this.jz.get(i).getComicId().getTitle() + this.context.getResources().getString(2131689767) + "");
                    commentProfileViewHolder.textView_noReply.setText(2131689764);
                } else if (this.jz.get(i).getGameId() != null) {
                    commentProfileViewHolder.textView_viewContentPage.setText(this.context.getResources().getString(2131689766) + this.jz.get(i).getGameId().getTitle() + this.context.getResources().getString(2131689767) + "");
                    commentProfileViewHolder.textView_noReply.setText(2131689765);
                }
                Picasso.with(this.context).load(g.b(this.jH.getAvatar())).into(commentProfileViewHolder.imageView_userThumb);
            }
            if (this.jz.get(i).isLiked()) {
                commentProfileViewHolder.imageView_likeIcon.setImageResource(2131230941);
            } else {
                commentProfileViewHolder.imageView_likeIcon.setImageResource(2131230940);
            }
            commentProfileViewHolder.textView_floor.setText((this.jD - i) + "");
            commentProfileViewHolder.linearLayout_reply.removeAllViews();
            commentProfileViewHolder.button_viewMore.setVisibility(8);
            commentProfileViewHolder.textView_noReply.setVisibility(8);
            if (!this.jG || this.jz.get(i).isHide()) {
                commentProfileViewHolder.button_hide.setVisibility(8);
            } else {
                commentProfileViewHolder.button_hide.setVisibility(0);
            }
            if (i == this.jB) {
                if (this.jz.get(i).getChildsCount() == 0) {
                    commentProfileViewHolder.textView_noReply.setVisibility(0);
                }
                if (this.jC) {
                    commentProfileViewHolder.button_viewMore.setVisibility(0);
                }
                if (this.jz.get(i).getArrayList() != null) {
                    for (int i2 = 0; i2 < this.jz.get(i).getArrayList().size(); i2++) {
                        CommentReplyView commentReplyView = new CommentReplyView(this.context, this.jA, i, i2);
                        commentReplyView.textView_username.setText(this.jz.get(i).getArrayList().get(i2).getUser().getName() + "");
                        commentReplyView.textView_title.setText(this.jz.get(i).getArrayList().get(i2).getUser().getTitle() + "");
                        commentReplyView.textView_content.setText(this.jz.get(i).getArrayList().get(i2).getContent() + "");
                        commentReplyView.textView_floor.setText((this.jz.get(i).getChildsCount() - i2) + "");
                        commentReplyView.textView_level.setText(this.jz.get(i).getArrayList().get(i2).getUser().getLevel() + "");
                        commentReplyView.textView_likeCount.setText(this.jz.get(i).getArrayList().get(i2).getLikesCount() + "");
                        commentReplyView.textView_createdDate.setText(g.B(this.context, this.jz.get(i).getArrayList().get(i2).getCreatedAt()));
                        if (this.jz.get(i).getArrayList().get(i2).getUser().getCharacter() == null || this.jz.get(i).getArrayList().get(i2).getUser().getCharacter().equalsIgnoreCase("")) {
                            commentReplyView.imageView_userVerified.setVisibility(8);
                        } else {
                            commentReplyView.imageView_userVerified.setVisibility(0);
                            Picasso.with(this.context).load(this.jz.get(i).getArrayList().get(i2).getUser().getCharacter()).into(commentReplyView.imageView_userVerified);
                        }
                        if (this.jz.get(i).getArrayList().get(i2).isLiked()) {
                            commentReplyView.imageView_likeIcon.setImageResource(2131230941);
                        } else {
                            commentReplyView.imageView_likeIcon.setImageResource(2131230940);
                        }
                        if (!this.jG || this.jz.get(i).getArrayList().get(i2).isHide()) {
                            commentReplyView.button_hide.setVisibility(8);
                        } else {
                            commentReplyView.button_hide.setVisibility(0);
                        }
                        Picasso.with(this.context).load(g.b(this.jz.get(i).getArrayList().get(i2).getUser().getAvatar())).into(commentReplyView.imageView_userThumb);
                        commentProfileViewHolder.linearLayout_reply.addView(commentReplyView);
                    }
                }
            }
        } else if (!(viewHolder instanceof CommentViewHolder)) {
            CommentTopReplacementViewHolder commentTopReplacementViewHolder = (CommentTopReplacementViewHolder) viewHolder;
            if (this.jz == null || this.jz.size() <= i) {
                return;
            }
            commentTopReplacementViewHolder.textView_floor.setText((this.jD - i) + "");
        } else {
            CommentViewHolder commentViewHolder = (CommentViewHolder) viewHolder;
            if (this.jz == null || this.jz.size() <= i) {
                return;
            }
            commentViewHolder.textView_content.setText(this.jz.get(i).getContent() + "");
            commentViewHolder.textView_likeCount.setText(this.jz.get(i).getLikesCount() + "");
            commentViewHolder.textView_replyCount.setText(this.jz.get(i).getChildsCount() + "");
            commentViewHolder.textView_createdDate.setText(g.B(this.context, this.jz.get(i).getCreatedAt()));
            if (this.jz.get(i).getUser() != null) {
                if (this.jz.get(i).getUser().getCharacter() == null || this.jz.get(i).getUser().getCharacter().equalsIgnoreCase("")) {
                    commentViewHolder.imageView_userVerified.setVisibility(8);
                } else {
                    commentViewHolder.imageView_userVerified.setVisibility(0);
                    Picasso.with(this.context).load(this.jz.get(i).getUser().getCharacter()).into(commentViewHolder.imageView_userVerified);
                }
                commentViewHolder.textView_username.setText(this.jz.get(i).getUser().getName() + "");
                commentViewHolder.textView_title.setText(this.jz.get(i).getUser().getTitle() + "");
                commentViewHolder.textView_level.setText(this.jz.get(i).getUser().getLevel() + "");
                Picasso.with(this.context).load(g.b(this.jz.get(i).getUser().getAvatar())).into(commentViewHolder.imageView_userThumb);
            }
            if (this.jz.get(i).isLiked()) {
                commentViewHolder.imageView_likeIcon.setImageResource(2131230941);
            } else {
                commentViewHolder.imageView_likeIcon.setImageResource(2131230940);
            }
            commentViewHolder.textView_floor.setText((this.jD - i) + "");
            commentViewHolder.linearLayout_reply.removeAllViews();
            commentViewHolder.button_viewMore.setVisibility(8);
            commentViewHolder.textView_noReply.setVisibility(8);
            if (this.jG) {
                commentViewHolder.button_tools.setVisibility(0);
                if (i == this.jF) {
                    commentViewHolder.linearLayout_tools.setVisibility(0);
                    if (!this.jG || this.jz.get(i).isHide()) {
                        commentViewHolder.button_hide.setVisibility(4);
                    } else {
                        commentViewHolder.button_hide.setVisibility(0);
                    }
                } else {
                    commentViewHolder.linearLayout_tools.setVisibility(8);
                }
            }
            if (i == this.jB) {
                if (this.jz.get(i).getChildsCount() == 0) {
                    commentViewHolder.textView_noReply.setVisibility(0);
                }
                if (this.jC) {
                    commentViewHolder.button_viewMore.setVisibility(0);
                }
                if (this.jz.get(i).getArrayList() != null) {
                    for (int i3 = 0; i3 < this.jz.get(i).getArrayList().size(); i3++) {
                        CommentReplyView commentReplyView2 = new CommentReplyView(this.context, this.jA, i, i3);
                        commentReplyView2.textView_username.setText(this.jz.get(i).getArrayList().get(i3).getUser().getName() + "");
                        commentReplyView2.textView_title.setText(this.jz.get(i).getArrayList().get(i3).getUser().getTitle() + "");
                        commentReplyView2.textView_content.setText(this.jz.get(i).getArrayList().get(i3).getContent() + "");
                        commentReplyView2.textView_floor.setText((this.jz.get(i).getChildsCount() - i3) + "");
                        commentReplyView2.textView_level.setText(this.jz.get(i).getArrayList().get(i3).getUser().getLevel() + "");
                        commentReplyView2.textView_likeCount.setText(this.jz.get(i).getArrayList().get(i3).getLikesCount() + "");
                        commentReplyView2.textView_createdDate.setText(g.B(this.context, this.jz.get(i).getArrayList().get(i3).getCreatedAt()));
                        if (this.jz.get(i).getArrayList().get(i3).getUser().getCharacter() == null || this.jz.get(i).getArrayList().get(i3).getUser().getCharacter().equalsIgnoreCase("")) {
                            commentReplyView2.imageView_userVerified.setVisibility(8);
                        } else {
                            commentReplyView2.imageView_userVerified.setVisibility(0);
                            Picasso.with(this.context).load(this.jz.get(i).getArrayList().get(i3).getUser().getCharacter()).into(commentReplyView2.imageView_userVerified);
                        }
                        if (this.jz.get(i).getArrayList().get(i3).isLiked()) {
                            commentReplyView2.imageView_likeIcon.setImageResource(2131230941);
                        } else {
                            commentReplyView2.imageView_likeIcon.setImageResource(2131230940);
                        }
                        if (!this.jG || this.jz.get(i).getArrayList().get(i3).isHide()) {
                            commentReplyView2.button_hide.setVisibility(8);
                        } else {
                            commentReplyView2.button_hide.setVisibility(0);
                        }
                        Picasso.with(this.context).load(g.b(this.jz.get(i).getArrayList().get(i3).getUser().getAvatar())).into(commentReplyView2.imageView_userThumb);
                        commentViewHolder.linearLayout_reply.addView(commentReplyView2);
                    }
                }
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (viewGroup != null) {
            viewGroup.setClipChildren(false);
        }
        switch (i) {
            case 1:
                return new CommentProfileViewHolder(this.mLayoutInflater.inflate(2131427466, viewGroup, false), this.jA);
            case 2:
                return new CommentViewHolder(this.mLayoutInflater.inflate(2131427469, viewGroup, false), this.jA);
            case 3:
                return new CommentTopReplacementViewHolder(this.mLayoutInflater.inflate(2131427470, viewGroup, false), this.jA);
            default:
                return new CommentViewHolder(this.mLayoutInflater.inflate(2131427467, viewGroup, false), this.jA);
        }
    }

    public void z(int i) {
        this.jD = i;
    }
}
