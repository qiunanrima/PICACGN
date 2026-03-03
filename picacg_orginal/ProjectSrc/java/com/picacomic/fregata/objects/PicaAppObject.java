package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/PicaAppObject.class */
public class PicaAppObject extends PicaAppBaseObject {
    String description;
    String icon;
    String title;
    String url;

    public PicaAppObject(String str, String str2, String str3, String str4) {
        this.title = str;
        this.url = str2;
        this.icon = str3;
        this.description = str4;
    }

    public String getDescription() {
        return this.description;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String toString() {
        return "PicaAppObject{title='" + this.title + "', url='" + this.url + "', icon='" + this.icon + "', description='" + this.description + "'}";
    }
}
