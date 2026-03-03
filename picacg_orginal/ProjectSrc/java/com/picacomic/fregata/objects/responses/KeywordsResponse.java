package com.picacomic.fregata.objects.responses;

import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/KeywordsResponse.class */
public class KeywordsResponse {
    ArrayList<String> keywords;

    public KeywordsResponse(ArrayList<String> arrayList) {
        this.keywords = arrayList;
    }

    public ArrayList<String> getKeywords() {
        return this.keywords;
    }

    public void setKeywords(ArrayList<String> arrayList) {
        this.keywords = arrayList;
    }

    public String toString() {
        return "KeywordsResponse{keywords=" + this.keywords + '}';
    }
}
