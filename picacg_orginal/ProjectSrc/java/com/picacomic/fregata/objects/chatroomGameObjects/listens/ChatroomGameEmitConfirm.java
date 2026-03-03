package com.picacomic.fregata.objects.chatroomGameObjects.listens;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/chatroomGameObjects/listens/ChatroomGameEmitConfirm.class */
public class ChatroomGameEmitConfirm {
    String id;
    String options;

    public ChatroomGameEmitConfirm(String str, String str2) {
        this.id = str;
        this.options = str2;
    }

    public String getId() {
        return this.id;
    }

    public String getOptions() {
        return this.options;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOptions(String str) {
        this.options = str;
    }

    public String toString() {
        return "ChatroomGameEmitConfirm{id='" + this.id + "', options='" + this.options + "'}";
    }
}
