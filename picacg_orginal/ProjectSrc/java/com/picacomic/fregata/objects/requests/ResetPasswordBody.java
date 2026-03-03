package com.picacomic.fregata.objects.requests;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/ResetPasswordBody.class */
public class ResetPasswordBody {
    String answer;
    String email;
    int questionNo;

    public ResetPasswordBody(String str, int i, String str2) {
        this.email = str;
        this.questionNo = i;
        this.answer = str2;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String getEmail() {
        return this.email;
    }

    public int getQuestionNo() {
        return this.questionNo;
    }

    public void setAnswer(String str) {
        this.answer = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setQuestionNo(int i) {
        this.questionNo = i;
    }

    public String toString() {
        return "ResetPasswordBody{email='" + this.email + "', questionNo=" + this.questionNo + ", answer='" + this.answer + "'}";
    }
}
