package com.picacomic.fregata.objects.requests;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/ForgotPasswordBody.class */
public class ForgotPasswordBody {
    String email;

    public ForgotPasswordBody(String str) {
        this.email = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String toString() {
        return "ForgotPasswordBody{email='" + this.email + "'}";
    }
}
