package com.picacomic.fregata.objects.requests;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/AvatarBody.class */
public class AvatarBody {
    String avatar;

    public AvatarBody(String str) {
        this.avatar = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String toString() {
        return "AvatarBody{avatar='" + this.avatar + "'}";
    }
}
