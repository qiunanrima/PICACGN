package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.util.Property;
/* loaded from: picacg_1.jar:android/support/transition/ObjectAnimatorUtilsImpl.class */
interface ObjectAnimatorUtilsImpl {
    <T> ObjectAnimator ofFloat(T t, Property<T, Float> property, Property<T, Float> property2, Path path);

    <T> ObjectAnimator ofInt(T t, Property<T, Integer> property, Property<T, Integer> property2, Path path);

    <T> ObjectAnimator ofInt(T t, String str, String str2, Path path);
}
