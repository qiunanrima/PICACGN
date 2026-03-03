package com.picacomic.fregata.objects.databaseTable;

import com.orm.d;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/databaseTable/DbComicViewRecordObject.class */
public class DbComicViewRecordObject extends d {
    String comicId;
    int episodeOrder;
    String episodeTitle;
    int episodeTotal;
    long lastViewTimestamp;
    int page;

    public DbComicViewRecordObject() {
    }

    public DbComicViewRecordObject(String str, int i, String str2, int i2, int i3, long j) {
        this.comicId = str;
        this.page = i;
        this.episodeTitle = str2;
        this.episodeOrder = i2;
        this.episodeTotal = i3;
        this.lastViewTimestamp = j;
    }

    public String getComicId() {
        return this.comicId;
    }

    public int getEpisodeOrder() {
        return this.episodeOrder;
    }

    public String getEpisodeTitle() {
        return this.episodeTitle;
    }

    public int getEpisodeTotal() {
        return this.episodeTotal;
    }

    public long getLastViewTimestamp() {
        return this.lastViewTimestamp;
    }

    public int getPage() {
        return this.page;
    }

    public void setComicId(String str) {
        this.comicId = str;
    }

    public void setEpisodeOrder(int i) {
        this.episodeOrder = i;
    }

    public void setEpisodeTitle(String str) {
        this.episodeTitle = str;
    }

    public void setEpisodeTotal(int i) {
        this.episodeTotal = i;
    }

    public void setLastViewTimestamp(long j) {
        this.lastViewTimestamp = j;
    }

    public void setPage(int i) {
        this.page = i;
    }

    public String toString() {
        return "DbComicViewRecordObject{comicId='" + this.comicId + "', page=" + this.page + ", episodeTitle=" + this.episodeTitle + ", episodeOrder=" + this.episodeOrder + ", episodeTotal=" + this.episodeTotal + ", lastViewTimestamp=" + this.lastViewTimestamp + '}';
    }

    public void updateDbComicViewRecordObject(DbComicViewRecordObject dbComicViewRecordObject) {
        this.comicId = dbComicViewRecordObject.getComicId();
        this.page = dbComicViewRecordObject.getPage();
        this.episodeTitle = dbComicViewRecordObject.getEpisodeTitle();
        this.episodeOrder = dbComicViewRecordObject.getEpisodeOrder();
        this.episodeTotal = dbComicViewRecordObject.getEpisodeTotal();
        this.lastViewTimestamp = dbComicViewRecordObject.getLastViewTimestamp();
    }
}
