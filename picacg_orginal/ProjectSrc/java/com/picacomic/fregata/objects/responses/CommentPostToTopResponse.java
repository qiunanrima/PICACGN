package com.picacomic.fregata.objects.responses;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/CommentPostToTopResponse.class */
public class CommentPostToTopResponse {
    boolean isTop;

    public CommentPostToTopResponse(boolean z) {
        this.isTop = z;
    }

    public boolean isTop() {
        return this.isTop;
    }

    public void setTop(boolean z) {
        this.isTop = z;
    }

    public String toString() {
        return "TopCommentResponse{isTop=" + this.isTop + '}';
    }
}
