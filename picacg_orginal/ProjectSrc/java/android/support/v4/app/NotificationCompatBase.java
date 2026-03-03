package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.v4.app.RemoteInputCompatBase;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v4/app/NotificationCompatBase.class */
public class NotificationCompatBase {

    /* loaded from: picacg_1.jar:android/support/v4/app/NotificationCompatBase$Action.class */
    public static abstract class Action {

        /* loaded from: picacg_1.jar:android/support/v4/app/NotificationCompatBase$Action$Factory.class */
        public interface Factory {
            Action build(int i, CharSequence charSequence, PendingIntent pendingIntent, Bundle bundle, RemoteInputCompatBase.RemoteInput[] remoteInputArr, RemoteInputCompatBase.RemoteInput[] remoteInputArr2, boolean z);

            Action[] newArray(int i);
        }

        public abstract PendingIntent getActionIntent();

        public abstract boolean getAllowGeneratedReplies();

        public abstract RemoteInputCompatBase.RemoteInput[] getDataOnlyRemoteInputs();

        public abstract Bundle getExtras();

        public abstract int getIcon();

        public abstract RemoteInputCompatBase.RemoteInput[] getRemoteInputs();

        public abstract CharSequence getTitle();
    }

    /* loaded from: picacg_1.jar:android/support/v4/app/NotificationCompatBase$UnreadConversation.class */
    public static abstract class UnreadConversation {

        /* loaded from: picacg_1.jar:android/support/v4/app/NotificationCompatBase$UnreadConversation$Factory.class */
        public interface Factory {
            UnreadConversation build(String[] strArr, RemoteInputCompatBase.RemoteInput remoteInput, PendingIntent pendingIntent, PendingIntent pendingIntent2, String[] strArr2, long j);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long getLatestTimestamp();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String[] getMessages();

        abstract String getParticipant();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract String[] getParticipants();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract PendingIntent getReadPendingIntent();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract RemoteInputCompatBase.RemoteInput getRemoteInput();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract PendingIntent getReplyPendingIntent();
    }
}
