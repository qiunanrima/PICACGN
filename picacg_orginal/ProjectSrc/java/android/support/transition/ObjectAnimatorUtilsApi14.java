package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.RequiresApi;
import android.util.Property;
@RequiresApi(14)
/* loaded from: picacg_1.jar:android/support/transition/ObjectAnimatorUtilsApi14.class */
class ObjectAnimatorUtilsApi14 implements ObjectAnimatorUtilsImpl {

    /* loaded from: picacg_1.jar:android/support/transition/ObjectAnimatorUtilsApi14$CastIntegerProperty.class */
    private static class CastIntegerProperty<T> extends Property<T, Float> {
        private final Property<T, Integer> mProperty;

        CastIntegerProperty(Property<T, Integer> property) {
            super(Float.class, property.getName());
            this.mProperty = property;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Property
        public Float get(T t) {
            return Float.valueOf(this.mProperty.get(t).intValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object obj) {
            return get((CastIntegerProperty<T>) obj);
        }

        /* renamed from: set  reason: avoid collision after fix types in other method */
        public void set2(T t, Float f) {
            this.mProperty.set(t, Integer.valueOf(Math.round(f.floatValue())));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(Object obj, Float f) {
            set2((CastIntegerProperty<T>) obj, f);
        }
    }

    /* loaded from: picacg_1.jar:android/support/transition/ObjectAnimatorUtilsApi14$PathProperty.class */
    private static class PathProperty<T> extends Property<T, Float> {
        private float mCurrentFraction;
        private final float mPathLength;
        private final PathMeasure mPathMeasure;
        private final float[] mPosition;
        private final Property<T, Float> mXProperty;
        private final Property<T, Float> mYProperty;

        PathProperty(Property<T, Float> property, Property<T, Float> property2, Path path) {
            super(Float.class, property.getName() + "/" + property2.getName());
            this.mPosition = new float[2];
            this.mXProperty = property;
            this.mYProperty = property2;
            this.mPathMeasure = new PathMeasure(path, false);
            this.mPathLength = this.mPathMeasure.getLength();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.util.Property
        public Float get(T t) {
            return Float.valueOf(this.mCurrentFraction);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object obj) {
            return get((PathProperty<T>) obj);
        }

        /* renamed from: set  reason: avoid collision after fix types in other method */
        public void set2(T t, Float f) {
            this.mCurrentFraction = f.floatValue();
            this.mPathMeasure.getPosTan(this.mPathLength * f.floatValue(), this.mPosition, null);
            this.mXProperty.set(t, Float.valueOf(this.mPosition[0]));
            this.mYProperty.set(t, Float.valueOf(this.mPosition[1]));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(Object obj, Float f) {
            set2((PathProperty<T>) obj, f);
        }
    }

    @Override // android.support.transition.ObjectAnimatorUtilsImpl
    public <T> ObjectAnimator ofFloat(T t, Property<T, Float> property, Property<T, Float> property2, Path path) {
        return ObjectAnimator.ofFloat(t, new PathProperty(property, property2, path), new float[0]);
    }

    @Override // android.support.transition.ObjectAnimatorUtilsImpl
    public <T> ObjectAnimator ofInt(T t, Property<T, Integer> property, Property<T, Integer> property2, Path path) {
        return ObjectAnimator.ofFloat(t, new PathProperty(new CastIntegerProperty(property), new CastIntegerProperty(property2), path), 0.0f, 1.0f);
    }

    @Override // android.support.transition.ObjectAnimatorUtilsImpl
    public <T> ObjectAnimator ofInt(T t, String str, String str2, Path path) {
        Class<?> cls = t.getClass();
        return ObjectAnimator.ofFloat(t, new PathProperty(new CastIntegerProperty(Property.of(cls, Integer.class, str)), new CastIntegerProperty(Property.of(cls, Integer.class, str2)), path), 0.0f, 1.0f);
    }
}
