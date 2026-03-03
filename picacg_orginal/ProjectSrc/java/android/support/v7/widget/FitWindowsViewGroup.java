package android.support.v7.widget;

import android.graphics.Rect;
import android.support.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v7/widget/FitWindowsViewGroup.class */
public interface FitWindowsViewGroup {

    /* loaded from: picacg_1.jar:android/support/v7/widget/FitWindowsViewGroup$OnFitSystemWindowsListener.class */
    public interface OnFitSystemWindowsListener {
        void onFitSystemWindows(Rect rect);
    }

    void setOnFitSystemWindowsListener(OnFitSystemWindowsListener onFitSystemWindowsListener);
}
