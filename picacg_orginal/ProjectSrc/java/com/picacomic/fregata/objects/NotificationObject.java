package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/NotificationObject.class */
public class NotificationObject {
    String content;
    ThumbnailObject cover;
    @SerializedName("created_at")
    String createdAt;
    String link;
    @SerializedName("_id")
    String notificationId;
    @SerializedName("_redirectId")
    String redirectId;
    String redirectType;
    @SerializedName("_sender")
    UserProfileObject sender;
    boolean system;
    String title;

    public NotificationObject(String str, String str2, String str3, String str4, String str5, String str6, boolean z, ThumbnailObject thumbnailObject, UserProfileObject userProfileObject, String str7) {
        this.notificationId = str;
        this.title = str2;
        this.content = str3;
        this.redirectId = str4;
        this.redirectType = str5;
        this.link = str6;
        this.system = z;
        this.cover = thumbnailObject;
        this.sender = userProfileObject;
        this.createdAt = str7;
    }

    public String getContent() {
        return this.content;
    }

    public ThumbnailObject getCover() {
        return this.cover;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public String getLink() {
        return this.link;
    }

    public String getNotificationId() {
        return this.notificationId;
    }

    public String getRedirectId() {
        return this.redirectId;
    }

    public String getRedirectType() {
        return this.redirectType;
    }

    public UserProfileObject getSender() {
        return this.sender;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isSystem() {
        return this.system;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public void setCover(ThumbnailObject thumbnailObject) {
        this.cover = thumbnailObject;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public void setNotificationId(String str) {
        this.notificationId = str;
    }

    public void setRedirectId(String str) {
        this.redirectId = str;
    }

    public void setRedirectType(String str) {
        this.redirectType = str;
    }

    public void setSender(UserProfileObject userProfileObject) {
        this.sender = userProfileObject;
    }

    public void setSystem(boolean z) {
        this.system = z;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "NotificationObject{notificationId='" + this.notificationId + "', title='" + this.title + "', content='" + this.content + "', redirectId='" + this.redirectId + "', redirectType='" + this.redirectType + "', link='" + this.link + "', system=" + this.system + ", cover=" + this.cover + ", sender=" + this.sender + ", createdAt='" + this.createdAt + "'}";
    }
}
