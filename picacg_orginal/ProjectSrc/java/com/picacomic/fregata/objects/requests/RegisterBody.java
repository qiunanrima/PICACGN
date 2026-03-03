package com.picacomic.fregata.objects.requests;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/RegisterBody.class */
public class RegisterBody {
    String answer1;
    String answer2;
    String answer3;
    String birthday;
    String email;
    String gender;
    String name;
    String password;
    String question1;
    String question2;
    String question3;

    public RegisterBody() {
    }

    public RegisterBody(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.name = str;
        this.email = str2;
        this.password = str3;
        this.birthday = str4;
        this.gender = str5;
        this.question1 = str6;
        this.question2 = str7;
        this.question3 = str8;
        this.answer1 = str9;
        this.answer2 = str10;
        this.answer3 = str11;
    }

    public String getAnswer1() {
        return this.answer1;
    }

    public String getAnswer2() {
        return this.answer2;
    }

    public String getAnswer3() {
        return this.answer3;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public String getEmail() {
        return this.email;
    }

    public String getGender() {
        return this.gender;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getQuestion1() {
        return this.question1;
    }

    public String getQuestion2() {
        return this.question2;
    }

    public String getQuestion3() {
        return this.question3;
    }

    public void setAnswer1(String str) {
        this.answer1 = str;
    }

    public void setAnswer2(String str) {
        this.answer2 = str;
    }

    public void setAnswer3(String str) {
        this.answer3 = str;
    }

    public void setBirthday(String str) {
        this.birthday = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setQuestion1(String str) {
        this.question1 = str;
    }

    public void setQuestion2(String str) {
        this.question2 = str;
    }

    public void setQuestion3(String str) {
        this.question3 = str;
    }
}
