package com.picacomic.fregata.objects.responses.DataClass.AnnouncementsResponse;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/AnnouncementsResponse/AnnouncementsResponse.class */
public class AnnouncementsResponse {
    AnnouncementsData announcements;

    public AnnouncementsResponse(AnnouncementsData announcementsData) {
        this.announcements = announcementsData;
    }

    public AnnouncementsData getAnnouncements() {
        return this.announcements;
    }

    public void setAnnouncements(AnnouncementsData announcementsData) {
        this.announcements = announcementsData;
    }
}
