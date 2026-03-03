package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/CreatorObject.class */
public class CreatorObject {
    ThumbnailObject avatar;
    @SerializedName("_id")
    String creatorId;
    String gender;
    String name;

    public CreatorObject() {
    }

    public CreatorObject(String str, String str2, String str3, ThumbnailObject thumbnailObject) {
        this.creatorId = str;
        this.name = str2;
        this.gender = str3;
        this.avatar = thumbnailObject;
    }

    public ThumbnailObject getAvatar() {
        return this.avatar;
    }

    public String getCreatorId() {
        return this.creatorId;
    }

    public String getGender() {
        return this.gender;
    }

    public String getName() {
        return this.name;
    }

    public void setAvatar(ThumbnailObject thumbnailObject) {
        this.avatar = thumbnailObject;
    }

    public void setCreatorId(String str) {
        this.creatorId = str;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
