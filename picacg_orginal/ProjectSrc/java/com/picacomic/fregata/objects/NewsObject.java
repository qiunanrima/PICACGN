package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/NewsObject.class */
public class NewsObject {
    String description;
    ThumbnailObject image;
    String title;

    public NewsObject(ThumbnailObject thumbnailObject, String str, String str2) {
        this.image = thumbnailObject;
        this.title = str;
        this.description = str2;
    }

    public String getDescription() {
        return this.description;
    }

    public ThumbnailObject getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setImage(ThumbnailObject thumbnailObject) {
        this.image = thumbnailObject;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "NewsObject{image=" + this.image + ", title='" + this.title + "', description='" + this.description + "'}";
    }
}
