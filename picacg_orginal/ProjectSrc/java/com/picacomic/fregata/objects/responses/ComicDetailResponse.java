package com.picacomic.fregata.objects.responses;

import com.picacomic.fregata.objects.ComicDetailObject;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/ComicDetailResponse.class */
public class ComicDetailResponse {
    ComicDetailObject comic;

    public ComicDetailResponse(ComicDetailObject comicDetailObject) {
        this.comic = comicDetailObject;
    }

    public ComicDetailObject getComic() {
        return this.comic;
    }

    public void setComic(ComicDetailObject comicDetailObject) {
        this.comic = comicDetailObject;
    }

    public String toString() {
        return "ComicDetailResponse{comic=" + this.comic + '}';
    }
}
