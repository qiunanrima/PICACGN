package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/ApkObject.class */
public class ApkObject {
    String fileServer;
    String originalName;
    String path;

    public ApkObject(String str, String str2, String str3) {
        this.originalName = str;
        this.path = str2;
        this.fileServer = str3;
    }

    public String getFileServer() {
        return this.fileServer;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public String getPath() {
        return this.path;
    }

    public void setFileServer(String str) {
        this.fileServer = str;
    }

    public void setOriginalName(String str) {
        this.originalName = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public String toString() {
        return "ApkObject{originalName='" + this.originalName + "', path='" + this.path + "', fileServer='" + this.fileServer + "'}";
    }
}
