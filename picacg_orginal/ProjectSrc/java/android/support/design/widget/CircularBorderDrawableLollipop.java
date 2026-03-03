package android.support.design.widget;

import android.graphics.Outline;
import android.support.annotation.RequiresApi;
@RequiresApi(21)
/* loaded from: picacg_1.jar:android/support/design/widget/CircularBorderDrawableLollipop.class */
class CircularBorderDrawableLollipop extends CircularBorderDrawable {
    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        copyBounds(this.mRect);
        outline.setOval(this.mRect);
    }
}
