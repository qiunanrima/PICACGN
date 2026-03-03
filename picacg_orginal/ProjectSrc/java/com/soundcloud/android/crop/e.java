package com.soundcloud.android.crop;

import android.graphics.Bitmap;
import android.graphics.Matrix;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: picacg_1.jar:com/soundcloud/android/crop/e.class */
public class e {
    private Bitmap bitmap;
    private int rotation;

    public e(Bitmap bitmap, int i) {
        this.bitmap = bitmap;
        this.rotation = i % 360;
    }

    public Matrix er() {
        Matrix matrix = new Matrix();
        if (this.bitmap != null && this.rotation != 0) {
            matrix.preTranslate(-(this.bitmap.getWidth() / 2), -(this.bitmap.getHeight() / 2));
            matrix.postRotate(this.rotation);
            matrix.postTranslate(getWidth() / 2, getHeight() / 2);
        }
        return matrix;
    }

    public boolean es() {
        return (this.rotation / 90) % 2 != 0;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public int getHeight() {
        if (this.bitmap == null) {
            return 0;
        }
        return es() ? this.bitmap.getWidth() : this.bitmap.getHeight();
    }

    public int getRotation() {
        return this.rotation;
    }

    public int getWidth() {
        if (this.bitmap == null) {
            return 0;
        }
        return es() ? this.bitmap.getHeight() : this.bitmap.getWidth();
    }

    public void recycle() {
        if (this.bitmap != null) {
            this.bitmap.recycle();
            this.bitmap = null;
        }
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setRotation(int i) {
        this.rotation = i;
    }
}
