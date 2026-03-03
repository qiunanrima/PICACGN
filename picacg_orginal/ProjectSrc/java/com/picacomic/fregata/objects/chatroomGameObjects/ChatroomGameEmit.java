package com.picacomic.fregata.objects.chatroomGameObjects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/chatroomGameObjects/ChatroomGameEmit.class */
public class ChatroomGameEmit<DataClass> {
    String action;
    DataClass data;

    public ChatroomGameEmit(String str) {
        this.action = str;
    }

    public ChatroomGameEmit(String str, DataClass dataclass) {
        this.action = str;
        this.data = dataclass;
    }

    public String getAction() {
        return this.action;
    }

    public DataClass getData() {
        return this.data;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public void setData(DataClass dataclass) {
        this.data = dataclass;
    }

    public String toString() {
        return "ChatroomGameEmit{action='" + this.action + "', data=" + this.data + '}';
    }
}
