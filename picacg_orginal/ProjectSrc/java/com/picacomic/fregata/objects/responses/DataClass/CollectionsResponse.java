package com.picacomic.fregata.objects.responses.DataClass;

import com.picacomic.fregata.objects.CollectionObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/CollectionsResponse.class */
public class CollectionsResponse {
    ArrayList<CollectionObject> collections;

    public CollectionsResponse(ArrayList<CollectionObject> arrayList) {
        this.collections = arrayList;
    }

    public ArrayList<CollectionObject> getCollections() {
        return this.collections;
    }

    public void setCollections(ArrayList<CollectionObject> arrayList) {
        this.collections = arrayList;
    }

    public String toString() {
        return "CollectionsResponse{collections=" + this.collections + '}';
    }
}
