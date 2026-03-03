package com.picacomic.fregata.objects.responses.DataClass.ComicPageResponse;

import com.picacomic.fregata.objects.ComicEpisodeObject;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/ComicPageResponse/ComicPagesResponse.class */
public class ComicPagesResponse {
    ComicEpisodeObject ep;
    ComicPageData pages;

    public ComicPagesResponse(ComicPageData comicPageData, ComicEpisodeObject comicEpisodeObject) {
        this.pages = comicPageData;
        this.ep = comicEpisodeObject;
    }

    public ComicEpisodeObject getEp() {
        return this.ep;
    }

    public ComicPageData getPages() {
        return this.pages;
    }

    public void setEp(ComicEpisodeObject comicEpisodeObject) {
        this.ep = comicEpisodeObject;
    }

    public void setPages(ComicPageData comicPageData) {
        this.pages = comicPageData;
    }

    public String toString() {
        return "ComicPagesResponse{pages=" + this.pages + ", ep=" + this.ep + '}';
    }
}
