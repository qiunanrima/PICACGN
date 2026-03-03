package com.picacomic.fregata.objects;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/AtMeObject.class */
public class AtMeObject {
    int index;
    String name;

    public AtMeObject(String str, int i) {
        this.name = str;
        this.index = i;
    }

    public void addIndex() {
        this.index++;
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void setName(String str) {
        this.name = str;
    }
}
