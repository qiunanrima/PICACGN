package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v4/media/MediaBrowserCompatUtils.class */
public class MediaBrowserCompatUtils {
    public static boolean areSameOptions(Bundle bundle, Bundle bundle2) {
        boolean z = true;
        if (bundle == bundle2) {
            return true;
        }
        if (bundle != null) {
            if (bundle2 == null) {
                return bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == -1 && bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == -1;
            }
            return bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) == bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) && bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) == bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        }
        if (bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1) != -1 || bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1) != -1) {
            z = false;
        }
        return z;
    }

    public static boolean hasDuplicatedItems(Bundle bundle, Bundle bundle2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = bundle == null ? -1 : bundle.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i6 = bundle2 == null ? -1 : bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE, -1);
        int i7 = bundle == null ? -1 : bundle.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        int i8 = bundle2 == null ? -1 : bundle2.getInt(MediaBrowserCompat.EXTRA_PAGE_SIZE, -1);
        if (i5 == -1 || i7 == -1) {
            i = Integer.MAX_VALUE;
            i2 = 0;
        } else {
            i2 = i5 * i7;
            i = (i7 + i2) - 1;
        }
        if (i6 == -1 || i8 == -1) {
            i3 = 0;
            i4 = Integer.MAX_VALUE;
        } else {
            i3 = i8 * i6;
            i4 = (i8 + i3) - 1;
        }
        if (i2 > i3 || i3 > i) {
            return i2 <= i4 && i4 <= i;
        }
        return true;
    }
}
