package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/CategoryObject.class */
public class CategoryObject {
    @SerializedName("_id")
    String categoryId;
    String description;
    boolean isWeb;
    String link;
    ThumbnailObject thumb;
    String title;

    public CategoryObject() {
    }

    public CategoryObject(String str, String str2, String str3, ThumbnailObject thumbnailObject, boolean z, String str4) {
        this.categoryId = str;
        this.title = str2;
        this.description = str3;
        this.thumb = thumbnailObject;
        this.isWeb = z;
        this.link = str4;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLink() {
        return this.link;
    }

    public ThumbnailObject getThumb() {
        return this.thumb;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isWeb() {
        return this.isWeb;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public void setThumb(ThumbnailObject thumbnailObject) {
        this.thumb = thumbnailObject;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setWeb(boolean z) {
        this.isWeb = z;
    }

    public String toString() {
        return "CategoryObject{categoryId='" + this.categoryId + "', title='" + this.title + "', description='" + this.description + "', thumb=" + this.thumb + ", isWeb=" + this.isWeb + ", link='" + this.link + "'}";
    }
}
