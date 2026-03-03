package com.picacomic.fregata.objects.responses.DataClass.ApplicationsResponse;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/ApplicationsResponse/ApplicationsResponse.class */
public class ApplicationsResponse {
    ApplicationsData applications;

    public ApplicationsResponse(ApplicationsData applicationsData) {
        this.applications = applicationsData;
    }

    public ApplicationsData getApplications() {
        return this.applications;
    }

    public void setApplications(ApplicationsData applicationsData) {
        this.applications = applicationsData;
    }

    public String toString() {
        return "ApplicationsResponse{applications=" + this.applications + '}';
    }
}
