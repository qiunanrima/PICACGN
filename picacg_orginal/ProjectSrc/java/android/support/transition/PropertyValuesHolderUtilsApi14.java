package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.support.annotation.RequiresApi;
import android.util.Property;
@RequiresApi(14)
/* loaded from: picacg_1.jar:android/support/transition/PropertyValuesHolderUtilsApi14.class */
class PropertyValuesHolderUtilsApi14 implements PropertyValuesHolderUtilsImpl {

    /* loaded from: picacg_1.jar:android/support/transition/PropertyValuesHolderUtilsApi14$PathEvaluator.class */
    private static class PathEvaluator implements TypeEvaluator<PointF> {
        private final float mPathLength;
        private final PathMeasure mPathMeasure;
        private final PointF mPointF = new PointF();
        private final float[] mPosition = new float[2];

        PathEvaluator(Path path) {
            this.mPathMeasure = new PathMeasure(path, false);
            this.mPathLength = this.mPathMeasure.getLength();
        }

        @Override // android.animation.TypeEvaluator
        public PointF evaluate(float f, PointF pointF, PointF pointF2) {
            this.mPathMeasure.getPosTan(this.mPathLength * f, this.mPosition, null);
            this.mPointF.set(this.mPosition[0], this.mPosition[1]);
            return this.mPointF;
        }
    }

    @Override // android.support.transition.PropertyValuesHolderUtilsImpl
    public PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        return PropertyValuesHolder.ofObject(property, new PathEvaluator(path), new PointF[0]);
    }
}
