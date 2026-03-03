package com.picacomic.fregata.objects.responses.DataClass.ComicPageResponse;

import com.picacomic.fregata.objects.ComicPageObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/ComicPageResponse/ComicPageData.class */
public class ComicPageData {
    ArrayList<ComicPageObject> docs;
    int limit;
    int page;
    int pages;
    int total;

    public ComicPageData() {
    }

    public ComicPageData(int i, int i2, int i3, int i4, ArrayList<ComicPageObject> arrayList) {
        this.total = i;
        this.limit = i2;
        this.page = i3;
        this.pages = i4;
        this.docs = arrayList;
    }

    public ArrayList<ComicPageObject> getDocs() {
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

    public void setDocs(ArrayList<ComicPageObject> arrayList) {
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
        return "ComicPageData{total=" + this.total + ", limit=" + this.limit + ", page=" + this.page + ", pages=" + this.pages + ", docs=" + this.docs + '}';
    }
}
