package com.picacomic.fregata.objects;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/GameDetailObject.class */
public class GameDetailObject {
    boolean adult;

    /* renamed from: android  reason: collision with root package name */
    boolean f7android;
    ArrayList<String> androidLinks;
    float androidSize;
    int commentsCount;
    String description;
    int downloadsCount;
    @SerializedName("_id")
    String gameId;
    ThumbnailObject icon;
    boolean ios;
    ArrayList<String> iosLinks;
    float iosSize;
    boolean isLiked;
    int likesCount;
    String publisher;
    ArrayList<ThumbnailObject> screenshots;
    boolean suggest;
    String title;
    String updateContent;
    String version;
    String videoLink;

    public GameDetailObject() {
    }

    public GameDetailObject(String str, String str2, String str3, String str4, ThumbnailObject thumbnailObject, String str5, String str6, String str7, int i, int i2, int i3, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, float f, float f2, ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<ThumbnailObject> arrayList3) {
        this.gameId = str;
        this.title = str2;
        this.version = str3;
        this.publisher = str4;
        this.icon = thumbnailObject;
        this.updateContent = str5;
        this.description = str6;
        this.videoLink = str7;
        this.downloadsCount = i;
        this.commentsCount = i2;
        this.likesCount = i3;
        this.isLiked = z;
        this.suggest = z2;
        this.adult = z3;
        this.ios = z4;
        this.f7android = z5;
        this.iosSize = f;
        this.androidSize = f2;
        this.iosLinks = arrayList;
        this.androidLinks = arrayList2;
        this.screenshots = arrayList3;
    }

    public ArrayList<String> getAndroidLinks() {
        return this.androidLinks;
    }

    public float getAndroidSize() {
        return this.androidSize;
    }

    public int getCommentsCount() {
        return this.commentsCount;
    }

    public String getDescription() {
        return this.description;
    }

    public int getDownloadsCount() {
        return this.downloadsCount;
    }

    public String getGameId() {
        return this.gameId;
    }

    public ThumbnailObject getIcon() {
        return this.icon;
    }

    public ArrayList<String> getIosLinks() {
        return this.iosLinks;
    }

    public float getIosSize() {
        return this.iosSize;
    }

    public int getLikesCount() {
        return this.likesCount;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public ArrayList<ThumbnailObject> getScreenshots() {
        return this.screenshots;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUpdateContent() {
        return this.updateContent;
    }

    public String getVersion() {
        return this.version;
    }

    public String getVideoLink() {
        return this.videoLink;
    }

    public boolean isAdult() {
        return this.adult;
    }

    public boolean isAndroid() {
        return this.f7android;
    }

    public boolean isIos() {
        return this.ios;
    }

    public boolean isLiked() {
        return this.isLiked;
    }

    public boolean isSuggest() {
        return this.suggest;
    }

    public void setAdult(boolean z) {
        this.adult = z;
    }

    public void setAndroid(boolean z) {
        this.f7android = z;
    }

    public void setAndroidLinks(ArrayList<String> arrayList) {
        this.androidLinks = arrayList;
    }

    public void setAndroidSize(float f) {
        this.androidSize = f;
    }

    public void setCommentsCount(int i) {
        this.commentsCount = i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDownloadsCount(int i) {
        this.downloadsCount = i;
    }

    public void setGameId(String str) {
        this.gameId = str;
    }

    public void setIcon(ThumbnailObject thumbnailObject) {
        this.icon = thumbnailObject;
    }

    public void setIos(boolean z) {
        this.ios = z;
    }

    public void setIosLinks(ArrayList<String> arrayList) {
        this.iosLinks = arrayList;
    }

    public void setIosSize(float f) {
        this.iosSize = f;
    }

    public void setLiked(boolean z) {
        this.isLiked = z;
    }

    public void setLikesCount(int i) {
        this.likesCount = i;
    }

    public void setPublisher(String str) {
        this.publisher = str;
    }

    public void setScreenshots(ArrayList<ThumbnailObject> arrayList) {
        this.screenshots = arrayList;
    }

    public void setSuggest(boolean z) {
        this.suggest = z;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUpdateContent(String str) {
        this.updateContent = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public void setVideoLink(String str) {
        this.videoLink = str;
    }

    public String toString() {
        return "GameDetailObject{gameId='" + this.gameId + "', title='" + this.title + "', version='" + this.version + "', publisher='" + this.publisher + "', icon=" + this.icon + ", updateContent='" + this.updateContent + "', description='" + this.description + "', videoLink='" + this.videoLink + "', downloadsCount=" + this.downloadsCount + ", commentsCount=" + this.commentsCount + ", likesCount=" + this.likesCount + ", isLiked=" + this.isLiked + ", suggest=" + this.suggest + ", adult=" + this.adult + ", ios=" + this.ios + ", android=" + this.f7android + ", iosSize=" + this.iosSize + ", androidSize=" + this.androidSize + ", iosLinks=" + this.iosLinks + ", androidLinks=" + this.androidLinks + ", screenshots=" + this.screenshots + '}';
    }
}
