package com.picacomic.fregata.objects.responses;

import com.picacomic.fregata.objects.ChatroomListObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/ChatroomListResponse.class */
public class ChatroomListResponse {
    ArrayList<ChatroomListObject> chatList;

    public ChatroomListResponse(ArrayList<ChatroomListObject> arrayList) {
        this.chatList = arrayList;
    }

    public ArrayList<ChatroomListObject> getChatList() {
        return this.chatList;
    }

    public void setChatList(ArrayList<ChatroomListObject> arrayList) {
        this.chatList = arrayList;
    }
}
