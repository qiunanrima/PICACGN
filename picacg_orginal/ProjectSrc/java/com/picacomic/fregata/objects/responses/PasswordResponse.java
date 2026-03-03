package com.picacomic.fregata.objects.responses;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/PasswordResponse.class */
public class PasswordResponse {
    String password;

    public PasswordResponse(String str) {
        this.password = str;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public String toString() {
        return "PasswordResponse{password='" + this.password + "'}";
    }
}
