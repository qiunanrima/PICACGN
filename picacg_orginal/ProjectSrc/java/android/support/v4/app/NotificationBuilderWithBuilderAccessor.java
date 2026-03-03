package android.support.v4.app;

import android.app.Notification;
import android.support.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v4/app/NotificationBuilderWithBuilderAccessor.class */
public interface NotificationBuilderWithBuilderAccessor {
    Notification build();

    Notification.Builder getBuilder();
}
