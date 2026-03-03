package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/ChatSystemObject.class */
public class ChatSystemObject extends ChatBaseObject {
    String message;

    public ChatSystemObject(String str) {
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "ChatSystemObject{message='" + this.message + "'}";
    }
}
