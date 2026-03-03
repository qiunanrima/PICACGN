package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.util.Property;
/* loaded from: picacg_1.jar:android/support/transition/ObjectAnimatorUtils.class */
class ObjectAnimatorUtils {
    private static final ObjectAnimatorUtilsImpl IMPL;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            IMPL = new ObjectAnimatorUtilsApi21();
        } else {
            IMPL = new ObjectAnimatorUtilsApi14();
        }
    }

    ObjectAnimatorUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> ObjectAnimator ofFloat(T t, Property<T, Float> property, Property<T, Float> property2, Path path) {
        return IMPL.ofFloat(t, property, property2, path);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> ObjectAnimator ofInt(T t, Property<T, Integer> property, Property<T, Integer> property2, Path path) {
        return IMPL.ofInt((ObjectAnimatorUtilsImpl) t, (Property<ObjectAnimatorUtilsImpl, Integer>) property, (Property<ObjectAnimatorUtilsImpl, Integer>) property2, path);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> ObjectAnimator ofInt(T t, String str, String str2, Path path) {
        return IMPL.ofInt((ObjectAnimatorUtilsImpl) t, str, str2, path);
    }
}
