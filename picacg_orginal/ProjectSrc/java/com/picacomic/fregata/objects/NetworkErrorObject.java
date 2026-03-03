package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/NetworkErrorObject.class */
public class NetworkErrorObject {
    int code;
    String detail;
    String error;
    String message;

    public NetworkErrorObject() {
    }

    public NetworkErrorObject(int i, String str, String str2, String str3) {
        this.code = i;
        this.error = str;
        this.message = str2;
        this.detail = str3;
    }

    public int getCode() {
        return this.code;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getError() {
        return this.error;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public void setDetail(String str) {
        this.detail = str;
    }

    public void setError(String str) {
        this.error = str;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String toString() {
        return "NetworkErrorObject{code=" + this.code + ", error='" + this.error + "', message='" + this.message + "', detail='" + this.detail + "'}";
    }
}
