package com.picacomic.fregata.objects.chatroomObjects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/chatroomObjects/TimeAction.class */
public class TimeAction extends ChatroomSystemAction {
    String from;
    boolean toggle;

    public TimeAction(String str, String str2) {
        super(str, str2);
    }

    public TimeAction(String str, String str2, boolean z, String str3) {
        super(str, str2);
        this.toggle = z;
        this.from = str3;
    }

    public String getFrom() {
        return this.from;
    }

    public boolean isToggle() {
        return this.toggle;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public void setToggle(boolean z) {
        this.toggle = z;
    }

    @Override // com.picacomic.fregata.objects.chatroomObjects.ChatroomSystemAction
    public String toString() {
        return "TimeAction{action=" + this.action + ", toggle=" + this.toggle + ", from='" + this.from + "'}";
    }
}
