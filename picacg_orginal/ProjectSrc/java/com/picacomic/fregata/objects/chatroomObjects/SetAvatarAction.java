package com.picacomic.fregata.objects.chatroomObjects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/chatroomObjects/SetAvatarAction.class */
public class SetAvatarAction extends ChatroomSystemAction {
    String from;
    int no;
    String user;
    String user_id;

    public SetAvatarAction(String str, String str2) {
        super(str, str2);
    }

    public SetAvatarAction(String str, String str2, String str3, String str4, String str5, int i) {
        super(str, str2);
        this.user = str3;
        this.user_id = str4;
        this.from = str5;
        this.no = i;
    }

    public String getFrom() {
        return this.from;
    }

    public int getNo() {
        return this.no;
    }

    public String getUser() {
        return this.user;
    }

    public String getUser_id() {
        return this.user_id;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setNo(int i) {
        this.no = i;
    }

    public void setUser(String str) {
        this.user = str;
    }

    public void setUser_id(String str) {
        this.user_id = str;
    }

    @Override // com.picacomic.fregata.objects.chatroomObjects.ChatroomSystemAction
    public String toString() {
        return "HideAvatarAction{action='" + this.action + "', user='" + this.user + "', user_id='" + this.user_id + "', from='" + this.from + "', no='" + this.no + "'}";
    }
}
