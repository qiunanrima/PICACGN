package com.picacomic.fregata.objects.responses.DataClass.PostCommentResponse;

import com.google.gson.annotations.SerializedName;
import com.picacomic.fregata.objects.UserBasicObject;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/PostCommentResponse/PostCommentData.class */
public class PostCommentData {
    String content;
    @SerializedName("_user")
    UserBasicObject user;

    public PostCommentData() {
    }

    public PostCommentData(String str, UserBasicObject userBasicObject) {
        this.content = str;
        this.user = userBasicObject;
    }

    public String getContent() {
        return this.content;
    }

    public UserBasicObject getUser() {
        return this.user;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setUser(UserBasicObject userBasicObject) {
        this.user = userBasicObject;
    }

    public String toString() {
        return "PostCommentData{content='" + this.content + "', user=" + this.user + '}';
    }
}
