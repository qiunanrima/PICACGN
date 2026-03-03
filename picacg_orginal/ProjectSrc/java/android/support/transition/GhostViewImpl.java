package android.support.transition;

import android.graphics.Matrix;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
@RequiresApi(14)
/* loaded from: picacg_1.jar:android/support/transition/GhostViewImpl.class */
interface GhostViewImpl {

    /* loaded from: picacg_1.jar:android/support/transition/GhostViewImpl$Creator.class */
    public interface Creator {
        GhostViewImpl addGhost(View view, ViewGroup viewGroup, Matrix matrix);

        void removeGhost(View view);
    }

    void reserveEndViewTransition(ViewGroup viewGroup, View view);

    void setVisibility(int i);
}
