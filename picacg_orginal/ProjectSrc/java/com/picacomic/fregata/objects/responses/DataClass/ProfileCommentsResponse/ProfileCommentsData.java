package com.picacomic.fregata.objects.responses.DataClass.ProfileCommentsResponse;

import com.picacomic.fregata.objects.ProfileCommentObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/ProfileCommentsResponse/ProfileCommentsData.class */
public class ProfileCommentsData {
    ArrayList<ProfileCommentObject> docs;
    int limit;
    int page;
    int pages;
    int total;

    public ProfileCommentsData(int i, int i2, int i3, int i4, ArrayList<ProfileCommentObject> arrayList) {
        this.total = i;
        this.limit = i2;
        this.page = i3;
        this.pages = i4;
        this.docs = arrayList;
    }

    public ArrayList<ProfileCommentObject> getDocs() {
        return this.docs;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getPage() {
        return this.page;
    }

    public int getPages() {
        return this.pages;
    }

    public int getTotal() {
        return this.total;
    }

    public void setDocs(ArrayList<ProfileCommentObject> arrayList) {
        this.docs = arrayList;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public void setPage(int i) {
        this.page = i;
    }

    public void setPages(int i) {
        this.pages = i;
    }

    public void setTotal(int i) {
        this.total = i;
    }
}
