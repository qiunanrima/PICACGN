package com.picacomic.fregata.objects.databaseTable;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/databaseTable/DbComicEpisodeIndexObject.class */
public class DbComicEpisodeIndexObject {
    String comicId;
    String episodeId;
    int limit;
    int page;
    int pages;
    int total;

    public DbComicEpisodeIndexObject() {
    }

    public DbComicEpisodeIndexObject(DbComicEpisodeIndexObject dbComicEpisodeIndexObject) {
        this.comicId = dbComicEpisodeIndexObject.getComicId();
        this.episodeId = dbComicEpisodeIndexObject.getEpisodeId();
        this.total = dbComicEpisodeIndexObject.getTotal();
        this.limit = dbComicEpisodeIndexObject.getLimit();
        this.page = dbComicEpisodeIndexObject.getPage();
        this.pages = dbComicEpisodeIndexObject.getPages();
    }

    public DbComicEpisodeIndexObject(String str, String str2, int i, int i2, int i3, int i4) {
        this.comicId = str;
        this.episodeId = str2;
        this.total = i;
        this.limit = i2;
        this.page = i3;
        this.pages = i4;
    }

    public String getComicId() {
        return this.comicId;
    }

    public String getEpisodeId() {
        return this.episodeId;
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

    public void setComicId(String str) {
        this.comicId = str;
    }

    public void setEpisodeId(String str) {
        this.episodeId = str;
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
        return "DbComicEpisodeIndexObject{comicId='" + this.comicId + "', episodeId='" + this.episodeId + "', total=" + this.total + ", limit=" + this.limit + ", page=" + this.page + ", pages=" + this.pages + '}';
    }

    public void updateDbComicEpisodeIndexObject(DbComicEpisodeIndexObject dbComicEpisodeIndexObject) {
        this.comicId = dbComicEpisodeIndexObject.getComicId();
        this.episodeId = dbComicEpisodeIndexObject.getEpisodeId();
        this.total = dbComicEpisodeIndexObject.getTotal();
        this.limit = dbComicEpisodeIndexObject.getLimit();
        this.page = dbComicEpisodeIndexObject.getPage();
        this.pages = dbComicEpisodeIndexObject.getPages();
    }
}
