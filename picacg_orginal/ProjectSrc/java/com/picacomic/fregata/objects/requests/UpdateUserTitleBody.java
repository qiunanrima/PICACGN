package com.picacomic.fregata.objects.requests;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/UpdateUserTitleBody.class */
public class UpdateUserTitleBody {
    String title;

    public UpdateUserTitleBody(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "UpdateUserTitleBody{title='" + this.title + "'}";
    }
}
