package com.picacomic.fregata.objects.responses;

import com.picacomic.fregata.objects.CategoryObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/CategoryResponse.class */
public class CategoryResponse {
    public ArrayList<CategoryObject> categories;

    public CategoryResponse(ArrayList<CategoryObject> arrayList) {
        this.categories = arrayList;
    }

    public ArrayList<CategoryObject> getCategories() {
        return this.categories;
    }

    public void setCategories(ArrayList<CategoryObject> arrayList) {
        this.categories = arrayList;
    }

    public String toString() {
        return "CategoryResponse{categories=" + this.categories + '}';
    }
}
