package com.picacomic.fregata.objects;

import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/CollectionObject.class */
public class CollectionObject {
    ArrayList<ComicListObject> comics;
    String title;

    public CollectionObject(String str, ArrayList<ComicListObject> arrayList) {
        this.title = str;
        this.comics = arrayList;
    }

    public ArrayList<ComicListObject> getComics() {
        return this.comics;
    }

    public String getTitle() {
        return this.title;
    }

    public void setComics(ArrayList<ComicListObject> arrayList) {
        this.comics = arrayList;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return "CollectionObject{title='" + this.title + "', comics=" + this.comics + '}';
    }
}
