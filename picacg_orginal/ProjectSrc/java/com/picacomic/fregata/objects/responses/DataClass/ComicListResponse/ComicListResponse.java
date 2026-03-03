package com.picacomic.fregata.objects.responses.DataClass.ComicListResponse;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/ComicListResponse/ComicListResponse.class */
public class ComicListResponse {
    ComicListData comics;

    public ComicListResponse(ComicListData comicListData) {
        this.comics = comicListData;
    }

    public ComicListData getComics() {
        return this.comics;
    }

    public void setComics(ComicListData comicListData) {
        this.comics = comicListData;
    }

    public String toString() {
        return "ComicListResponse{, comics=" + this.comics + '}';
    }
}
