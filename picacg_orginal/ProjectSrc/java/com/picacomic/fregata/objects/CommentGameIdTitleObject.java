package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/CommentGameIdTitleObject.class */
public class CommentGameIdTitleObject {
    @SerializedName("_id")
    String gameId;
    @SerializedName("title")
    String title;

    public CommentGameIdTitleObject(String str, String str2) {
        this.gameId = str;
        this.title = str2;
    }

    public String getGameId() {
        return this.gameId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setGameId(String str) {
        this.gameId = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "CommentGameObject{gameId='" + this.gameId + "', title='" + this.title + "'}";
    }
}
