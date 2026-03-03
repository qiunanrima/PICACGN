package com.picacomic.fregata.objects.responses.DataClass.ComicListResponse;

import com.picacomic.fregata.objects.ComicListObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/ComicListResponse/ComicListData.class */
public class ComicListData {
    ArrayList<ComicListObject> docs;
    int limit;
    int page;
    int pages;
    int total;

    public ComicListData(int i, int i2, int i3, int i4, ArrayList<ComicListObject> arrayList) {
        this.total = i;
        this.limit = i2;
        this.page = i3;
        this.pages = i4;
        this.docs = arrayList;
    }

    public ArrayList<ComicListObject> getDocs() {
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

    public void setDocs(ArrayList<ComicListObject> arrayList) {
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

    public String toString() {
        return "ComicListData{total=" + this.total + ", limit=" + this.limit + ", page=" + this.page + ", pages=" + this.pages + ", docs=" + this.docs + '}';
    }
}
