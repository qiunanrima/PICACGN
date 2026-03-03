package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/BaseCommentObject.class */
public class BaseCommentObject {
    @SerializedName("commentsCount")
    int childsCount;
    @SerializedName("_id")
    String commentId;
    String content;
    @SerializedName("created_at")
    String createdAt;
    boolean isLiked;
    int likesCount;
    @SerializedName("_user")
    UserBasicObject user;

    public int getChildsCount() {
        return this.childsCount;
    }

    public String getCommentId() {
        return this.commentId;
    }

    public String getContent() {
        return this.content;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public int getLikesCount() {
        return this.likesCount;
    }

    public UserBasicObject getUser() {
        return this.user;
    }

    public boolean isLiked() {
        return this.isLiked;
    }

    public void setChildsCount(int i) {
        this.childsCount = i;
    }

    public void setCommentId(String str) {
        this.commentId = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public void setLiked(boolean z) {
        this.isLiked = z;
    }

    public void setLikesCount(int i) {
        this.likesCount = i;
    }

    public void setUser(UserBasicObject userBasicObject) {
        this.user = userBasicObject;
    }
}
