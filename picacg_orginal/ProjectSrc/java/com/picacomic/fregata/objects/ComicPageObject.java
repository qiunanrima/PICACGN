package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/ComicPageObject.class */
public class ComicPageObject {
    @SerializedName("_id")
    String comicPageId;
    ThumbnailObject media;

    public ComicPageObject() {
    }

    public ComicPageObject(String str, ThumbnailObject thumbnailObject) {
        this.comicPageId = str;
        this.media = thumbnailObject;
    }

    public String getComicPageId() {
        return this.comicPageId;
    }

    public ThumbnailObject getMedia() {
        return this.media;
    }

    public void setComicPageId(String str) {
        this.comicPageId = str;
    }

    public void setMedia(ThumbnailObject thumbnailObject) {
        this.media = thumbnailObject;
    }

    public String toString() {
        return "ComicPageObject{comicPageId='" + this.comicPageId + "', media=" + this.media + '}';
    }
}
