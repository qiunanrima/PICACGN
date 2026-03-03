package com.picacomic.fregata.objects.responses;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/MessageResponse.class */
public class MessageResponse {
    String message;

    public MessageResponse(String str) {
        this.message = str;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "MessageResponse{message='" + this.message + "'}";
    }
}
