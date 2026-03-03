package com.picacomic.fregata.objects.responses;

import com.picacomic.fregata.objects.BannerObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/BannersResponse.class */
public class BannersResponse {
    ArrayList<BannerObject> banners;

    public BannersResponse(ArrayList<BannerObject> arrayList) {
        this.banners = arrayList;
    }

    public ArrayList<BannerObject> getBanners() {
        return this.banners;
    }

    public void setBanners(ArrayList<BannerObject> arrayList) {
        this.banners = arrayList;
    }
}
