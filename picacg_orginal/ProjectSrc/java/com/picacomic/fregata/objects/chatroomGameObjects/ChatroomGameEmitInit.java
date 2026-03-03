package com.picacomic.fregata.objects.chatroomGameObjects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/chatroomGameObjects/ChatroomGameEmitInit.class */
public class ChatroomGameEmitInit {
    String userId;

    public ChatroomGameEmitInit(String str) {
        this.userId = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String toString() {
        return "ChatroomGameEmitInit{userId='" + this.userId + "'}";
    }
}
