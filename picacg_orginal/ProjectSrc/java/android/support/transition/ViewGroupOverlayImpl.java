package android.support.transition;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
@RequiresApi(14)
/* loaded from: picacg_1.jar:android/support/transition/ViewGroupOverlayImpl.class */
interface ViewGroupOverlayImpl extends ViewOverlayImpl {
    void add(@NonNull View view);

    void remove(@NonNull View view);
}
