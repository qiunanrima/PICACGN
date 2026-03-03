package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
@RequiresApi(14)
/* loaded from: picacg_1.jar:android/support/transition/ViewUtilsImpl.class */
interface ViewUtilsImpl {
    void clearNonTransitionAlpha(@NonNull View view);

    ViewOverlayImpl getOverlay(@NonNull View view);

    float getTransitionAlpha(@NonNull View view);

    WindowIdImpl getWindowId(@NonNull View view);

    void saveNonTransitionAlpha(@NonNull View view);

    void setAnimationMatrix(@NonNull View view, Matrix matrix);

    void setTransitionAlpha(@NonNull View view, float f);

    void transformMatrixToGlobal(@NonNull View view, @NonNull Matrix matrix);

    void transformMatrixToLocal(@NonNull View view, @NonNull Matrix matrix);
}
