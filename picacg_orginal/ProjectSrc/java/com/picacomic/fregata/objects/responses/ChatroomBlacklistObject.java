package com.picacomic.fregata.objects.responses;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/ChatroomBlacklistObject.class */
public class ChatroomBlacklistObject {
    String userId;
    String username;

    public ChatroomBlacklistObject(String str, String str2) {
        this.username = str;
        this.userId = str2;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String toString() {
        return "ChatroomBlacklistObject{username='" + this.username + "', userId='" + this.userId + "'}";
    }
}
