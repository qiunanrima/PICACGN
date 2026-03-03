package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.support.annotation.RequiresApi;
import android.util.Property;
@RequiresApi(21)
/* loaded from: picacg_1.jar:android/support/transition/ObjectAnimatorUtilsApi21.class */
class ObjectAnimatorUtilsApi21 implements ObjectAnimatorUtilsImpl {
    @Override // android.support.transition.ObjectAnimatorUtilsImpl
    public <T> ObjectAnimator ofFloat(T t, Property<T, Float> property, Property<T, Float> property2, Path path) {
        return ObjectAnimator.ofFloat(t, property, property2, path);
    }

    @Override // android.support.transition.ObjectAnimatorUtilsImpl
    public <T> ObjectAnimator ofInt(T t, Property<T, Integer> property, Property<T, Integer> property2, Path path) {
        return ObjectAnimator.ofInt(t, property, property2, path);
    }

    @Override // android.support.transition.ObjectAnimatorUtilsImpl
    public <T> ObjectAnimator ofInt(T t, String str, String str2, Path path) {
        return ObjectAnimator.ofInt(t, str, str2, path);
    }
}
