package com.picacomic.fregata.objects.requests;

import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/requests/SortingBody.class */
public class SortingBody {
    ArrayList<String> categories;
    String keyword;
    String sort;

    public SortingBody(String str, String str2, ArrayList<String> arrayList) {
        this.keyword = str;
        this.sort = str2;
        this.categories = arrayList;
    }

    public ArrayList<String> getCategories() {
        return this.categories;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public String getSort() {
        return this.sort;
    }

    public void setCategories(ArrayList<String> arrayList) {
        this.categories = arrayList;
    }

    public void setKeyword(String str) {
        this.keyword = str;
    }

    public void setSort(String str) {
        this.sort = str;
    }

    public String toString() {
        return "SortingBody{keyword='" + this.keyword + "', sort='" + this.sort + "', categories=" + this.categories + '}';
    }
}
