package com.picacomic.fregata.objects.responses;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/PunchInResponse.class */
public class PunchInResponse {
    PunchInObject res;

    public PunchInResponse(PunchInObject punchInObject) {
        this.res = punchInObject;
    }

    public PunchInObject getRes() {
        return this.res;
    }

    public void setRes(PunchInObject punchInObject) {
        this.res = punchInObject;
    }

    public String toString() {
        return "PunchInResponse{res=" + this.res + '}';
    }
}
