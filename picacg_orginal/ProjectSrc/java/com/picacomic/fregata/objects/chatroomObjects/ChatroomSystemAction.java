package com.picacomic.fregata.objects.chatroomObjects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/chatroomObjects/ChatroomSystemAction.class */
public class ChatroomSystemAction {
    public String action;
    public String email;

    public ChatroomSystemAction(String str, String str2) {
        this.action = str;
        this.email = str2;
    }

    public String getAction() {
        return this.action;
    }

    public String getEmail() {
        return this.email;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String toString() {
        return "ChatroomSystemAction{action='" + this.action + "', email='" + this.email + "'}";
    }
}
