package com.picacomic.fregata.objects.responses;

import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/WakaInitResponse.class */
public class WakaInitResponse {
    String adKeyword;
    ArrayList<String> addresses;
    String message;
    String status;
    String waka;

    public WakaInitResponse(String str, ArrayList<String> arrayList, String str2, String str3, String str4) {
        this.status = str;
        this.addresses = arrayList;
        this.waka = str2;
        this.adKeyword = str3;
        this.message = str4;
    }

    public String getAdKeyword() {
        return this.adKeyword;
    }

    public ArrayList<String> getAddresses() {
        return this.addresses;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public String getWaka() {
        return this.waka;
    }

    public void setAdKeyword(String str) {
        this.adKeyword = str;
    }

    public void setAddresses(ArrayList<String> arrayList) {
        this.addresses = arrayList;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setWaka(String str) {
        this.waka = str;
    }

    public String toString() {
        return "WakaInitResponse{status='" + this.status + "', addresses=" + this.addresses + ", waka='" + this.waka + ", adKeyword='" + this.adKeyword + ", message='" + this.message + "'}";
    }
}
