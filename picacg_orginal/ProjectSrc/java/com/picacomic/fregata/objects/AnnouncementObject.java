package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/AnnouncementObject.class */
public class AnnouncementObject {
    @SerializedName("_id")
    String announcementId;
    String content;
    @SerializedName("created_at")
    String createdAt;
    ThumbnailObject thumb;
    String title;

    public AnnouncementObject(String str, String str2, String str3, String str4, ThumbnailObject thumbnailObject) {
        this.announcementId = str;
        this.title = str2;
        this.content = str3;
        this.createdAt = str4;
        this.thumb = thumbnailObject;
    }

    public String getAnnouncementId() {
        return this.announcementId;
    }

    public String getContent() {
        return this.content;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public ThumbnailObject getThumb() {
        return this.thumb;
    }

    public String getTitle() {
        return this.title;
    }

    public void setAnnouncementId(String str) {
        this.announcementId = str;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public void setThumb(ThumbnailObject thumbnailObject) {
        this.thumb = thumbnailObject;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
