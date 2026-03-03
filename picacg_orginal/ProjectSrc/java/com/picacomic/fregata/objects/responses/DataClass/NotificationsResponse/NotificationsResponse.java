package com.picacomic.fregata.objects.responses.DataClass.NotificationsResponse;
/* loaded from: picacg_1.jar:com/picacomic/fregata/objects/responses/DataClass/NotificationsResponse/NotificationsResponse.class */
public class NotificationsResponse {
    NotificationsData notifications;

    public NotificationsResponse(NotificationsData notificationsData) {
        this.notifications = notificationsData;
    }

    public NotificationsData getNotifications() {
        return this.notifications;
    }

    public void setNotifications(NotificationsData notificationsData) {
        this.notifications = notificationsData;
    }

    public String toString() {
        return "NotificationsResponse{notifications=" + this.notifications + '}';
    }
}
