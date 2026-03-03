package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/AnonymousChatActionDataObject.class */
public class AnonymousChatActionDataObject {
    @SerializedName("actionType")
    String actionType;
    AnonymousChatDataObject data;
    String responseType;

    public AnonymousChatActionDataObject(String str, String str2, AnonymousChatDataObject anonymousChatDataObject) {
        this.actionType = str;
        this.responseType = str2;
        this.data = anonymousChatDataObject;
    }

    public String getActionType() {
        return this.actionType;
    }

    public AnonymousChatDataObject getData() {
        return this.data;
    }

    public String getResponseType() {
        return this.responseType;
    }

    public void setActionType(String str) {
        this.actionType = str;
    }

    public void setData(AnonymousChatDataObject anonymousChatDataObject) {
        this.data = anonymousChatDataObject;
    }

    public void setResponseType(String str) {
        this.responseType = str;
    }

    public String toString() {
        return "AnonymousChatActionDataObject{actionType='" + this.actionType + "', responseType='" + this.responseType + "', data=" + this.data + '}';
    }
}
