package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/SingleImageTextViewObject.class */
public class SingleImageTextViewObject {
    ThumbnailObject image;
    String title;

    public SingleImageTextViewObject() {
    }

    public SingleImageTextViewObject(ThumbnailObject thumbnailObject, String str) {
        this.image = thumbnailObject;
        this.title = str;
    }

    public ThumbnailObject getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setImage(ThumbnailObject thumbnailObject) {
        this.image = thumbnailObject;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "ProfileComicImageWithTextViewObject{image=" + this.image + ", title='" + this.title + "'}";
    }
}
