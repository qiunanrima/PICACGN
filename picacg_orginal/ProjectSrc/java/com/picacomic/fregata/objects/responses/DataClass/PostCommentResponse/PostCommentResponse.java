package com.picacomic.fregata.objects.responses.DataClass.PostCommentResponse;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/PostCommentResponse/PostCommentResponse.class */
public class PostCommentResponse {
    @SerializedName("childsCount")
    int childsCount;
    @SerializedName("_comic")
    String comicId;
    PostCommentData comment;
    @SerializedName("_id")
    String commentId;
    @SerializedName("created_at")
    String createdAt;
    @SerializedName("likesCount")
    int likesCount;
    @SerializedName("_parent")
    String parentId;

    public PostCommentResponse() {
    }

    public PostCommentResponse(PostCommentData postCommentData, String str, String str2, String str3, String str4, int i, int i2) {
        this.comment = postCommentData;
        this.parentId = str;
        this.comicId = str2;
        this.commentId = str3;
        this.createdAt = str4;
        this.likesCount = i;
        this.childsCount = i2;
    }

    public int getChildsCount() {
        return this.childsCount;
    }

    public String getComicId() {
        return this.comicId;
    }

    public PostCommentData getComment() {
        return this.comment;
    }

    public String getCommentId() {
        return this.commentId;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public int getLikesCount() {
        return this.likesCount;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setChildsCount(int i) {
        this.childsCount = i;
    }

    public void setComicId(String str) {
        this.comicId = str;
    }

    public void setComment(PostCommentData postCommentData) {
        this.comment = postCommentData;
    }

    public void setCommentId(String str) {
        this.commentId = str;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public void setLikesCount(int i) {
        this.likesCount = i;
    }

    public void setParentId(String str) {
        this.parentId = str;
    }

    public String toString() {
        return "PostCommentResponse{comment=" + this.comment + ", parentId='" + this.parentId + "', comicId='" + this.comicId + "', commentId='" + this.commentId + "', createdAt='" + this.createdAt + "', likesCount=" + this.likesCount + ", childsCount=" + this.childsCount + '}';
    }
}
