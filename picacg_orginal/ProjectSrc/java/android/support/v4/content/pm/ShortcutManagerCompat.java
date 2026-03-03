package android.support.v4.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ShortcutManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.os.BuildCompat;
/* loaded from: picacg_1.jar:android/support/v4/content/pm/ShortcutManagerCompat.class */
public class ShortcutManagerCompat {
    @VisibleForTesting
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    @VisibleForTesting
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

    private ShortcutManagerCompat() {
    }

    @NonNull
    public static Intent createShortcutResultIntent(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat) {
        Intent createShortcutResultIntent = BuildCompat.isAtLeastO() ? ((ShortcutManager) context.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcutInfoCompat.toShortcutInfo()) : null;
        Intent intent = createShortcutResultIntent;
        if (createShortcutResultIntent == null) {
            intent = new Intent();
        }
        return shortcutInfoCompat.addToIntent(intent);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isRequestPinShortcutSupported(@android.support.annotation.NonNull android.content.Context r5) {
        /*
            boolean r0 = android.support.v4.os.BuildCompat.isAtLeastO()
            if (r0 == 0) goto L13
            r0 = r5
            java.lang.Class<android.content.pm.ShortcutManager> r1 = android.content.pm.ShortcutManager.class
            java.lang.Object r0 = r0.getSystemService(r1)
            android.content.pm.ShortcutManager r0 = (android.content.pm.ShortcutManager) r0
            boolean r0 = r0.isRequestPinShortcutSupported()
            return r0
        L13:
            r0 = r5
            java.lang.String r1 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            int r0 = android.support.v4.content.ContextCompat.checkSelfPermission(r0, r1)
            if (r0 == 0) goto L1e
            r0 = 0
            return r0
        L1e:
            r0 = r5
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            android.content.Intent r1 = new android.content.Intent
            r2 = r1
            java.lang.String r3 = "com.android.launcher.action.INSTALL_SHORTCUT"
            r2.<init>(r3)
            r2 = 0
            java.util.List r0 = r0.queryBroadcastReceivers(r1, r2)
            java.util.Iterator r0 = r0.iterator()
            r5 = r0
        L35:
            r0 = r5
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L60
            r0 = r5
            java.lang.Object r0 = r0.next()
            android.content.pm.ResolveInfo r0 = (android.content.pm.ResolveInfo) r0
            android.content.pm.ActivityInfo r0 = r0.activityInfo
            java.lang.String r0 = r0.permission
            r6 = r0
            r0 = r6
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L5e
            java.lang.String r0 = "com.android.launcher.permission.INSTALL_SHORTCUT"
            r1 = r6
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L35
        L5e:
            r0 = 1
            return r0
        L60:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.content.pm.ShortcutManagerCompat.isRequestPinShortcutSupported(android.content.Context):boolean");
    }

    public static boolean requestPinShortcut(@NonNull Context context, @NonNull ShortcutInfoCompat shortcutInfoCompat, @Nullable final IntentSender intentSender) {
        if (BuildCompat.isAtLeastO()) {
            return ((ShortcutManager) context.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcutInfoCompat.toShortcutInfo(), intentSender);
        }
        if (isRequestPinShortcutSupported(context)) {
            Intent addToIntent = shortcutInfoCompat.addToIntent(new Intent(ACTION_INSTALL_SHORTCUT));
            if (intentSender == null) {
                context.sendBroadcast(addToIntent);
                return true;
            }
            context.sendOrderedBroadcast(addToIntent, null, new BroadcastReceiver() { // from class: android.support.v4.content.pm.ShortcutManagerCompat.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    try {
                        intentSender.sendIntent(context2, 0, null, null, null);
                    } catch (IntentSender.SendIntentException e) {
                    }
                }
            }, null, -1, null, null);
            return true;
        }
        return false;
    }
}
