package com.picacomic.fregata.objects.requests;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/AdjustExpBody.class */
public class AdjustExpBody {
    int exp;
    String userId;

    public AdjustExpBody() {
    }

    public AdjustExpBody(int i, String str) {
        this.exp = i;
        this.userId = str;
    }

    public int getExp() {
        return this.exp;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setExp(int i) {
        this.exp = i;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String toString() {
        return "AdjustExpBody{exp=" + this.exp + ", userId='" + this.userId + "'}";
    }
}
