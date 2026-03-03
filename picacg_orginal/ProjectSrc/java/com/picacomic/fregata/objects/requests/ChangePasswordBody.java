package com.picacomic.fregata.objects.requests;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/ChangePasswordBody.class */
public class ChangePasswordBody {
    @SerializedName("new_password")
    String newPassword;
    @SerializedName("old_password")
    String oldPassword;

    public ChangePasswordBody(String str, String str2) {
        this.oldPassword = str;
        this.newPassword = str2;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public String getOldPassword() {
        return this.oldPassword;
    }

    public void setNewPassword(String str) {
        this.newPassword = str;
    }

    public void setOldPassword(String str) {
        this.oldPassword = str;
    }

    public String toString() {
        return "ChangePasswordBody{oldPassword='" + this.oldPassword + "', newPassword='" + this.newPassword + "'}";
    }
}
