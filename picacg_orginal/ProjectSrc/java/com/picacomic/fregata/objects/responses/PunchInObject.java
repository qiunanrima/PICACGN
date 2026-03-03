package com.picacomic.fregata.objects.responses;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/PunchInObject.class */
public class PunchInObject {
    String punchInLastDay;
    String status;

    public PunchInObject(String str, String str2) {
        this.status = str;
        this.punchInLastDay = str2;
    }

    public String getPunchInLastDay() {
        return this.punchInLastDay;
    }

    public String getStatus() {
        return this.status;
    }

    public void setPunchInLastDay(String str) {
        this.punchInLastDay = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        return "PunchInObject{status='" + this.status + "', punchInLastDay='" + this.punchInLastDay + "'}";
    }
}
