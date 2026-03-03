package com.soundcloud.android.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.ImageView;
/* loaded from: picacg_1.jar:com/soundcloud/android/crop/ImageViewTouchBase.class */
abstract class ImageViewTouchBase extends ImageView {
    protected Handler handler;
    protected Matrix wc;
    protected Matrix wd;
    private final Matrix we;
    private final float[] wf;
    protected final e wg;
    int wh;
    int wi;
    float wj;
    private Runnable wk;
    private a wl;

    /* loaded from: picacg_1.jar:com/soundcloud/android/crop/ImageViewTouchBase$a.class */
    public interface a {
        void i(Bitmap bitmap);
    }

    public ImageViewTouchBase(Context context) {
        super(context);
        this.wc = new Matrix();
        this.wd = new Matrix();
        this.we = new Matrix();
        this.wf = new float[9];
        this.wg = new e(null, 0);
        this.wh = -1;
        this.wi = -1;
        this.handler = new Handler();
        init();
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.wc = new Matrix();
        this.wd = new Matrix();
        this.we = new Matrix();
        this.wf = new float[9];
        this.wg = new e(null, 0);
        this.wh = -1;
        this.wi = -1;
        this.handler = new Handler();
        init();
    }

    public ImageViewTouchBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.wc = new Matrix();
        this.wd = new Matrix();
        this.we = new Matrix();
        this.wf = new float[9];
        this.wg = new e(null, 0);
        this.wh = -1;
        this.wi = -1;
        this.handler = new Handler();
        init();
    }

    private float a(RectF rectF, float f, float f2) {
        float height = getHeight();
        if (f < height) {
            f2 = ((height - f) / 2.0f) - rectF.top;
        } else if (rectF.top > 0.0f) {
            f2 = -rectF.top;
        } else if (rectF.bottom < height) {
            f2 = getHeight() - rectF.bottom;
        }
        return f2;
    }

    private void a(Bitmap bitmap, int i) {
        super.setImageBitmap(bitmap);
        Drawable drawable = getDrawable();
        if (drawable != null) {
            drawable.setDither(true);
        }
        Bitmap bitmap2 = this.wg.getBitmap();
        this.wg.setBitmap(bitmap);
        this.wg.setRotation(i);
        if (bitmap2 == null || bitmap2 == bitmap || this.wl == null) {
            return;
        }
        this.wl.i(bitmap2);
    }

    private void a(e eVar, Matrix matrix, boolean z) {
        float width = getWidth();
        float height = getHeight();
        float width2 = eVar.getWidth();
        float height2 = eVar.getHeight();
        matrix.reset();
        float min = Math.min(Math.min(width / width2, 3.0f), Math.min(height / height2, 3.0f));
        if (z) {
            matrix.postConcat(eVar.er());
        }
        matrix.postScale(min, min);
        matrix.postTranslate((width - (width2 * min)) / 2.0f, (height - (height2 * min)) / 2.0f);
    }

    private float b(RectF rectF, float f, float f2) {
        float width = getWidth();
        if (f < width) {
            f2 = ((width - f) / 2.0f) - rectF.left;
        } else if (rectF.left > 0.0f) {
            f2 = -rectF.left;
        } else if (rectF.right < width) {
            f2 = width - rectF.right;
        }
        return f2;
    }

    private void init() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    protected float a(Matrix matrix) {
        return a(matrix, 0);
    }

    protected float a(Matrix matrix, int i) {
        matrix.getValues(this.wf);
        return this.wf[i];
    }

    public void a(Bitmap bitmap, boolean z) {
        a(new e(bitmap, 0), z);
    }

    public void a(final e eVar, final boolean z) {
        if (getWidth() <= 0) {
            this.wk = new Runnable() { // from class: com.soundcloud.android.crop.ImageViewTouchBase.1
                @Override // java.lang.Runnable
                public void run() {
                    ImageViewTouchBase.this.a(eVar, z);
                }
            };
            return;
        }
        if (eVar.getBitmap() != null) {
            a(eVar, this.wc, true);
            a(eVar.getBitmap(), eVar.getRotation());
        } else {
            this.wc.reset();
            setImageBitmap(null);
        }
        if (z) {
            this.wd.reset();
        }
        setImageMatrix(getImageViewMatrix());
        this.wj = eq();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void c(float f, float f2, float f3) {
        float f4 = f;
        if (f > this.wj) {
            f4 = this.wj;
        }
        float scale = f4 / getScale();
        this.wd.postScale(scale, scale, f2, f3);
        setImageMatrix(getImageViewMatrix());
        ep();
    }

    public void clear() {
        a((Bitmap) null, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d(float f, final float f2, final float f3, final float f4) {
        final float scale = (f - getScale()) / f4;
        final float scale2 = getScale();
        final long currentTimeMillis = System.currentTimeMillis();
        this.handler.post(new Runnable() { // from class: com.soundcloud.android.crop.ImageViewTouchBase.2
            @Override // java.lang.Runnable
            public void run() {
                float min = Math.min(f4, (float) (System.currentTimeMillis() - currentTimeMillis));
                ImageViewTouchBase.this.c(scale2 + (scale * min), f2, f3);
                if (min < f4) {
                    ImageViewTouchBase.this.handler.post(this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ep() {
        Bitmap bitmap = this.wg.getBitmap();
        if (bitmap == null) {
            return;
        }
        Matrix imageViewMatrix = getImageViewMatrix();
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        imageViewMatrix.mapRect(rectF);
        float height = rectF.height();
        float width = rectF.width();
        l(b(rectF, width, 0.0f), a(rectF, height, 0.0f));
        setImageMatrix(getImageViewMatrix());
    }

    protected float eq() {
        if (this.wg.getBitmap() == null) {
            return 1.0f;
        }
        return Math.max(this.wg.getWidth() / this.wh, this.wg.getHeight() / this.wi) * 4.0f;
    }

    protected Matrix getImageViewMatrix() {
        this.we.set(this.wc);
        this.we.postConcat(this.wd);
        return this.we;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public float getScale() {
        return a(this.wd);
    }

    public Matrix getUnrotatedMatrix() {
        Matrix matrix = new Matrix();
        a(this.wg, matrix, false);
        matrix.postConcat(this.wd);
        return matrix;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void l(float f, float f2) {
        this.wd.postTranslate(f, f2);
    }

    protected void n(float f) {
        c(f, getWidth() / 2.0f, getHeight() / 2.0f);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            keyEvent.startTracking();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4 || !keyEvent.isTracking() || keyEvent.isCanceled() || getScale() <= 1.0f) {
            return super.onKeyUp(i, keyEvent);
        }
        n(1.0f);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.wh = i3 - i;
        this.wi = i4 - i2;
        Runnable runnable = this.wk;
        if (runnable != null) {
            this.wk = null;
            runnable.run();
        }
        if (this.wg.getBitmap() != null) {
            a(this.wg, this.wc, true);
            setImageMatrix(getImageViewMatrix());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void p(float f, float f2) {
        l(f, f2);
        setImageMatrix(getImageViewMatrix());
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        a(bitmap, 0);
    }

    public void setRecycler(a aVar) {
        this.wl = aVar;
    }
}
