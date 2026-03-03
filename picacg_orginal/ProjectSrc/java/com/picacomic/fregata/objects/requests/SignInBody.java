package com.picacomic.fregata.objects.requests;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/SignInBody.class */
public class SignInBody {
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public SignInBody() {
    }

    public SignInBody(String str, String str2) {
        this.email = str;
        this.password = str2;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }
}
