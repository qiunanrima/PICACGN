package android.support.v4.graphics;

import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
/* loaded from: picacg_1.jar:android/support/v4/graphics/PaintCompat.class */
public final class PaintCompat {
    private PaintCompat() {
    }

    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        return Build.VERSION.SDK_INT >= 23 ? paint.hasGlyph(str) : PaintCompatApi14.hasGlyph(paint, str);
    }
}
