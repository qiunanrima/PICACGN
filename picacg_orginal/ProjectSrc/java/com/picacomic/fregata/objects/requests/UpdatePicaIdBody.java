package com.picacomic.fregata.objects.requests;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/UpdatePicaIdBody.class */
public class UpdatePicaIdBody {
    String email;
    String name;

    public UpdatePicaIdBody(String str, String str2) {
        this.email = str;
        this.name = str2;
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return "UpdatePicaIdBody{email='" + this.email + "', name='" + this.name + "'}";
    }
}
