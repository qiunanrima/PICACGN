package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/AnonymousChatDataObject.class */
public class AnonymousChatDataObject {
    String id;
    String message;
    String name;
    String roomId;
    String userId;

    public AnonymousChatDataObject(String str, String str2, String str3, String str4, String str5) {
        this.id = str;
        this.userId = str2;
        this.name = str3;
        this.message = str4;
        this.roomId = str5;
    }

    public String getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public String getName() {
        return this.name;
    }

    public String getRoomId() {
        return this.roomId;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRoomId(String str) {
        this.roomId = str;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String toString() {
        return "AnonymousChatDataObject{id='" + this.id + "', userId='" + this.userId + "', name='" + this.name + "', message='" + this.message + "', roomId='" + this.roomId + "'}";
    }
}
