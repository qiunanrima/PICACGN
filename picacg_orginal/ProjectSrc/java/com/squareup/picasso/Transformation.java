package com.squareup.picasso;

import android.graphics.Bitmap;
/* loaded from: picacg_1.jar:com/squareup/picasso/Transformation.class */
public interface Transformation {
    String key();

    Bitmap transform(Bitmap bitmap);
}
