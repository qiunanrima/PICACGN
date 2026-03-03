package android.support.v7.app;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.LongSparseArray;
import java.lang.reflect.Field;
import java.util.Map;
/* loaded from: picacg_1.jar:android/support/v7/app/ResourcesFlusher.class */
class ResourcesFlusher {
    private static final String TAG = "ResourcesFlusher";
    private static Field sDrawableCacheField;
    private static boolean sDrawableCacheFieldFetched;
    private static Field sResourcesImplField;
    private static boolean sResourcesImplFieldFetched;
    private static Class sThemedResourceCacheClazz;
    private static boolean sThemedResourceCacheClazzFetched;
    private static Field sThemedResourceCache_mUnthemedEntriesField;
    private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;

    ResourcesFlusher() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean flush(@NonNull Resources resources) {
        if (Build.VERSION.SDK_INT >= 24) {
            return flushNougats(resources);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return flushMarshmallows(resources);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            return flushLollipops(resources);
        }
        return false;
    }

    @RequiresApi(21)
    private static boolean flushLollipops(@NonNull Resources resources) {
        Map map;
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(TAG, "Could not retrieve Resources#mDrawableCache field", e);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField != null) {
            try {
                map = (Map) sDrawableCacheField.get(resources);
            } catch (IllegalAccessException e2) {
                Log.e(TAG, "Could not retrieve value from Resources#mDrawableCache", e2);
                map = null;
            }
            if (map != null) {
                map.clear();
                return true;
            }
            return false;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004b A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004d  */
    @android.support.annotation.RequiresApi(23)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean flushMarshmallows(@android.support.annotation.NonNull android.content.res.Resources r4) {
        /*
            boolean r0 = android.support.v7.app.ResourcesFlusher.sDrawableCacheFieldFetched
            if (r0 != 0) goto L28
            java.lang.Class<android.content.res.Resources> r0 = android.content.res.Resources.class
            java.lang.String r1 = "mDrawableCache"
            java.lang.reflect.Field r0 = r0.getDeclaredField(r1)     // Catch: java.lang.NoSuchFieldException -> L1a
            android.support.v7.app.ResourcesFlusher.sDrawableCacheField = r0     // Catch: java.lang.NoSuchFieldException -> L1a
            java.lang.reflect.Field r0 = android.support.v7.app.ResourcesFlusher.sDrawableCacheField     // Catch: java.lang.NoSuchFieldException -> L1a
            r1 = 1
            r0.setAccessible(r1)     // Catch: java.lang.NoSuchFieldException -> L1a
            goto L24
        L1a:
            r5 = move-exception
            java.lang.String r0 = "ResourcesFlusher"
            java.lang.String r1 = "Could not retrieve Resources#mDrawableCache field"
            r2 = r5
            int r0 = android.util.Log.e(r0, r1, r2)
        L24:
            r0 = 1
            android.support.v7.app.ResourcesFlusher.sDrawableCacheFieldFetched = r0
        L28:
            java.lang.reflect.Field r0 = android.support.v7.app.ResourcesFlusher.sDrawableCacheField
            if (r0 == 0) goto L43
            java.lang.reflect.Field r0 = android.support.v7.app.ResourcesFlusher.sDrawableCacheField     // Catch: java.lang.IllegalAccessException -> L39
            r1 = r4
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.IllegalAccessException -> L39
            r4 = r0
            goto L45
        L39:
            r4 = move-exception
            java.lang.String r0 = "ResourcesFlusher"
            java.lang.String r1 = "Could not retrieve value from Resources#mDrawableCache"
            r2 = r4
            int r0 = android.util.Log.e(r0, r1, r2)
        L43:
            r0 = 0
            r4 = r0
        L45:
            r0 = 0
            r6 = r0
            r0 = r4
            if (r0 != 0) goto L4d
            r0 = 0
            return r0
        L4d:
            r0 = r6
            r7 = r0
            r0 = r4
            if (r0 == 0) goto L5e
            r0 = r6
            r7 = r0
            r0 = r4
            boolean r0 = flushThemedResourcesCache(r0)
            if (r0 == 0) goto L5e
            r0 = 1
            r7 = r0
        L5e:
            r0 = r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.app.ResourcesFlusher.flushMarshmallows(android.content.res.Resources):boolean");
    }

    @RequiresApi(24)
    private static boolean flushNougats(@NonNull Resources resources) {
        Object obj;
        Object obj2;
        boolean z = true;
        if (!sResourcesImplFieldFetched) {
            try {
                sResourcesImplField = Resources.class.getDeclaredField("mResourcesImpl");
                sResourcesImplField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                Log.e(TAG, "Could not retrieve Resources#mResourcesImpl field", e);
            }
            sResourcesImplFieldFetched = true;
        }
        if (sResourcesImplField == null) {
            return false;
        }
        try {
            obj = sResourcesImplField.get(resources);
        } catch (IllegalAccessException e2) {
            Log.e(TAG, "Could not retrieve value from Resources#mResourcesImpl", e2);
            obj = null;
        }
        if (obj == null) {
            return false;
        }
        if (!sDrawableCacheFieldFetched) {
            try {
                sDrawableCacheField = obj.getClass().getDeclaredField("mDrawableCache");
                sDrawableCacheField.setAccessible(true);
            } catch (NoSuchFieldException e3) {
                Log.e(TAG, "Could not retrieve ResourcesImpl#mDrawableCache field", e3);
            }
            sDrawableCacheFieldFetched = true;
        }
        if (sDrawableCacheField != null) {
            try {
                obj2 = sDrawableCacheField.get(obj);
            } catch (IllegalAccessException e4) {
                Log.e(TAG, "Could not retrieve value from ResourcesImpl#mDrawableCache", e4);
            }
            if (obj2 != null || !flushThemedResourcesCache(obj2)) {
                z = false;
            }
            return z;
        }
        obj2 = null;
        if (obj2 != null) {
        }
        z = false;
        return z;
    }

    @RequiresApi(16)
    private static boolean flushThemedResourcesCache(@NonNull Object obj) {
        LongSparseArray longSparseArray;
        if (!sThemedResourceCacheClazzFetched) {
            try {
                sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
            } catch (ClassNotFoundException e) {
                Log.e(TAG, "Could not find ThemedResourceCache class", e);
            }
            sThemedResourceCacheClazzFetched = true;
        }
        if (sThemedResourceCacheClazz == null) {
            return false;
        }
        if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
            try {
                sThemedResourceCache_mUnthemedEntriesField = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
                sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                Log.e(TAG, "Could not retrieve ThemedResourceCache#mUnthemedEntries field", e2);
            }
            sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
        }
        if (sThemedResourceCache_mUnthemedEntriesField == null) {
            return false;
        }
        try {
            longSparseArray = (LongSparseArray) sThemedResourceCache_mUnthemedEntriesField.get(obj);
        } catch (IllegalAccessException e3) {
            Log.e(TAG, "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", e3);
            longSparseArray = null;
        }
        if (longSparseArray != null) {
            longSparseArray.clear();
            return true;
        }
        return false;
    }
}
