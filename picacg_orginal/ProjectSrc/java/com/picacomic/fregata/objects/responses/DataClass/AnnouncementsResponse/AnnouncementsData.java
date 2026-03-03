package com.picacomic.fregata.objects.responses.DataClass.AnnouncementsResponse;

import com.picacomic.fregata.objects.AnnouncementObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/AnnouncementsResponse/AnnouncementsData.class */
public class AnnouncementsData {
    ArrayList<AnnouncementObject> docs;
    int limit;
    int page;
    int pages;
    int total;

    public AnnouncementsData(int i, int i2, int i3, int i4, ArrayList<AnnouncementObject> arrayList) {
        this.total = i;
        this.limit = i2;
        this.page = i3;
        this.pages = i4;
        this.docs = arrayList;
    }

    public ArrayList<AnnouncementObject> getDocs() {
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

    public void setDocs(ArrayList<AnnouncementObject> arrayList) {
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
