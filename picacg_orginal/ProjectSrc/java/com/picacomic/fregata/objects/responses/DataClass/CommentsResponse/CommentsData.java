package com.picacomic.fregata.objects.responses.DataClass.CommentsResponse;

import com.picacomic.fregata.objects.CommentObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/CommentsResponse/CommentsData.class */
public class CommentsData {
    ArrayList<CommentObject> docs;
    int limit;
    int page;
    int pages;
    int total;

    public CommentsData(int i, int i2, int i3, int i4, ArrayList<CommentObject> arrayList) {
        this.total = i;
        this.limit = i2;
        this.page = i3;
        this.pages = i4;
        this.docs = arrayList;
    }

    public ArrayList<CommentObject> getDocs() {
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

    public void setDocs(ArrayList<CommentObject> arrayList) {
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
        return "CommentsData{total=" + this.total + ", limit=" + this.limit + ", page=" + this.page + ", pages=" + this.pages + ", docs=" + this.docs + '}';
    }
}
