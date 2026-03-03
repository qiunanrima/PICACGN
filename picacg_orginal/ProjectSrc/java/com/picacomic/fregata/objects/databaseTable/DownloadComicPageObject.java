package com.picacomic.fregata.objects.databaseTable;

import com.orm.d;
import com.picacomic.fregata.objects.ComicPageObject;
import com.picacomic.fregata.objects.ThumbnailObject;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/databaseTable/DownloadComicPageObject.class */
public class DownloadComicPageObject extends d {
    String comicId;
    String comicPageId;
    String episodeId;
    String mediaFileServer;
    String mediaOriginalName;
    String mediaPath;
    String storageFolder;

    public DownloadComicPageObject() {
    }

    public DownloadComicPageObject(String str, String str2, String str3, ComicPageObject comicPageObject) {
        this.comicId = str;
        this.episodeId = str2;
        this.comicPageId = comicPageObject.getComicPageId();
        this.mediaFileServer = comicPageObject.getMedia().getFileServer();
        this.mediaOriginalName = comicPageObject.getMedia().getOriginalName();
        this.mediaPath = comicPageObject.getMedia().getPath();
        this.storageFolder = str3;
    }

    public String getComicId() {
        return this.comicId;
    }

    public String getComicPageId() {
        return this.comicPageId;
    }

    public ComicPageObject getComicPageObject() {
        return new ComicPageObject(this.comicPageId, new ThumbnailObject(this.mediaFileServer, this.mediaPath, this.mediaOriginalName));
    }

    public String getEpisodeId() {
        return this.episodeId;
    }

    public ThumbnailObject getMedia() {
        return new ThumbnailObject(this.mediaFileServer, this.mediaPath, this.mediaOriginalName);
    }

    public String getMediaFileServer() {
        return this.mediaFileServer;
    }

    public String getMediaOriginalName() {
        return this.mediaOriginalName;
    }

    public String getMediaPath() {
        return this.mediaPath;
    }

    public String getStorageFolder() {
        return this.storageFolder;
    }

    public void setComicId(String str) {
        this.comicId = str;
    }

    public void setComicPageId(String str) {
        this.comicPageId = str;
    }

    public void setEpisodeId(String str) {
        this.episodeId = str;
    }

    public void setMedia(ThumbnailObject thumbnailObject) {
        if (thumbnailObject != null) {
            this.mediaFileServer = thumbnailObject.getFileServer();
            this.mediaOriginalName = thumbnailObject.getOriginalName();
            this.mediaPath = thumbnailObject.getPath();
        }
    }

    public void setMediaFileServer(String str) {
        this.mediaFileServer = str;
    }

    public void setMediaOriginalName(String str) {
        this.mediaOriginalName = str;
    }

    public void setMediaPath(String str) {
        this.mediaPath = str;
    }

    public void setStorageFolder(String str) {
        this.storageFolder = str;
    }

    public String toString() {
        return "DownloadComicPageObject{comicId='" + this.comicId + "', episodeId='" + this.episodeId + "', comicPageId='" + this.comicPageId + "', mediaFileServer='" + this.mediaFileServer + "', mediaOriginalName='" + this.mediaOriginalName + "', mediaPath='" + this.mediaPath + "', storageFolder='" + this.storageFolder + "'}";
    }

    public void updateWithDownloadComicPageObject(DownloadComicPageObject downloadComicPageObject) {
        this.comicId = downloadComicPageObject.getComicId();
        this.episodeId = downloadComicPageObject.getEpisodeId();
        this.comicPageId = downloadComicPageObject.getComicPageId();
        this.mediaFileServer = downloadComicPageObject.getMediaFileServer();
        this.mediaOriginalName = downloadComicPageObject.getMediaOriginalName();
        this.mediaPath = downloadComicPageObject.getMediaPath();
        this.storageFolder = downloadComicPageObject.getStorageFolder();
    }
}
