package com.picacomic.fregata.objects.responses.DataClass.ProfileCommentsResponse;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/ProfileCommentsResponse/ProfileCommentsResponse.class */
public class ProfileCommentsResponse {
    ProfileCommentsData comments;

    public ProfileCommentsResponse(ProfileCommentsData profileCommentsData) {
        this.comments = profileCommentsData;
    }

    public ProfileCommentsData getComments() {
        return this.comments;
    }

    public void setComments(ProfileCommentsData profileCommentsData) {
        this.comments = profileCommentsData;
    }
}
