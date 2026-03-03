package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/ChatroomListObject.class */
public class ChatroomListObject extends PicaAppBaseObject {
    String avatar;
    String description;
    String title;
    String url;

    public ChatroomListObject(String str, String str2, String str3, String str4) {
        this.avatar = str;
        this.title = str2;
        this.description = str3;
        this.url = str4;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "ChatroomListObject{avatar='" + this.avatar + "', title='" + this.title + "', description='" + this.description + "', url='" + this.url + "'}";
    }
}
