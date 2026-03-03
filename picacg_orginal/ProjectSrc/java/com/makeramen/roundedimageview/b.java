package com.makeramen.roundedimageview;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.widget.ImageView;
import java.util.HashSet;
/* loaded from: picacg_1.jar:com/makeramen/roundedimageview/b.class */
public class b extends Drawable {
    private final Bitmap mBitmap;
    private final int mBitmapHeight;
    private final Paint mBitmapPaint;
    private final int mBitmapWidth;
    private final Paint mBorderPaint;
    private final RectF gt = new RectF();
    private final RectF mDrawableRect = new RectF();
    private final RectF gu = new RectF();
    private final RectF mBorderRect = new RectF();
    private final Matrix mShaderMatrix = new Matrix();
    private final RectF gv = new RectF();
    private Shader.TileMode gw = Shader.TileMode.CLAMP;
    private Shader.TileMode gz = Shader.TileMode.CLAMP;
    private boolean gA = true;
    private float mCornerRadius = 0.0f;
    private final boolean[] gB = {true, true, true, true};
    private boolean gC = false;
    private float mBorderWidth = 0.0f;
    private ColorStateList gD = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
    private ImageView.ScaleType gE = ImageView.ScaleType.FIT_CENTER;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.makeramen.roundedimageview.b$1  reason: invalid class name */
    /* loaded from: picacg_1.jar:com/makeramen/roundedimageview/b$1.class */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ImageView.ScaleType.values().length];

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0059 -> B:33:0x0014). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x005d -> B:43:0x001f). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0061 -> B:39:0x002a). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x0065 -> B:35:0x0035). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:25:0x0069 -> B:31:0x0040). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x006d -> B:41:0x004c). Please submit an issue!!! */
        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.CENTER_INSIDE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public b(Bitmap bitmap) {
        this.mBitmap = bitmap;
        this.mBitmapWidth = bitmap.getWidth();
        this.mBitmapHeight = bitmap.getHeight();
        this.gu.set(0.0f, 0.0f, this.mBitmapWidth, this.mBitmapHeight);
        this.mBitmapPaint = new Paint();
        this.mBitmapPaint.setStyle(Paint.Style.FILL);
        this.mBitmapPaint.setAntiAlias(true);
        this.mBorderPaint = new Paint();
        this.mBorderPaint.setStyle(Paint.Style.STROKE);
        this.mBorderPaint.setAntiAlias(true);
        this.mBorderPaint.setColor(this.gD.getColorForState(getState(), ViewCompat.MEASURED_STATE_MASK));
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
    }

    public static Drawable a(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof b) {
                return drawable;
            }
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    layerDrawable.setDrawableByLayerId(layerDrawable.getId(i), a(layerDrawable.getDrawable(i)));
                }
                return layerDrawable;
            }
            Bitmap b = b(drawable);
            if (b != null) {
                return new b(b);
            }
        }
        return drawable;
    }

    private static boolean a(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    public static Bitmap b(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(Math.max(drawable.getIntrinsicWidth(), 2), Math.max(drawable.getIntrinsicHeight(), 2), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            bitmap = createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("RoundedDrawable", "Failed to create bitmap from drawable!");
            bitmap = null;
        }
        return bitmap;
    }

    private static boolean b(boolean[] zArr) {
        for (boolean z : zArr) {
            if (z) {
                return false;
            }
        }
        return true;
    }

    public static b e(Bitmap bitmap) {
        if (bitmap != null) {
            return new b(bitmap);
        }
        return null;
    }

    private void h(Canvas canvas) {
        if (b(this.gB) || this.mCornerRadius == 0.0f) {
            return;
        }
        float f = this.mDrawableRect.left;
        float f2 = this.mDrawableRect.top;
        float width = this.mDrawableRect.width() + f;
        float height = this.mDrawableRect.height() + f2;
        float f3 = this.mCornerRadius;
        if (!this.gB[0]) {
            this.gv.set(f, f2, f + f3, f2 + f3);
            canvas.drawRect(this.gv, this.mBitmapPaint);
        }
        if (!this.gB[1]) {
            this.gv.set(width - f3, f2, width, f3);
            canvas.drawRect(this.gv, this.mBitmapPaint);
        }
        if (!this.gB[2]) {
            this.gv.set(width - f3, height - f3, width, height);
            canvas.drawRect(this.gv, this.mBitmapPaint);
        }
        if (this.gB[3]) {
            return;
        }
        this.gv.set(f, height - f3, f3 + f, height);
        canvas.drawRect(this.gv, this.mBitmapPaint);
    }

    private void i(Canvas canvas) {
        if (b(this.gB) || this.mCornerRadius == 0.0f) {
            return;
        }
        float f = this.mDrawableRect.left;
        float f2 = this.mDrawableRect.top;
        float width = f + this.mDrawableRect.width();
        float height = f2 + this.mDrawableRect.height();
        float f3 = this.mCornerRadius;
        float f4 = this.mBorderWidth / 2.0f;
        if (!this.gB[0]) {
            canvas.drawLine(f - f4, f2, f + f3, f2, this.mBorderPaint);
            canvas.drawLine(f, f2 - f4, f, f2 + f3, this.mBorderPaint);
        }
        if (!this.gB[1]) {
            canvas.drawLine((width - f3) - f4, f2, width, f2, this.mBorderPaint);
            canvas.drawLine(width, f2 - f4, width, f2 + f3, this.mBorderPaint);
        }
        if (!this.gB[2]) {
            canvas.drawLine((width - f3) - f4, height, width + f4, height, this.mBorderPaint);
            canvas.drawLine(width, height - f3, width, height, this.mBorderPaint);
        }
        if (this.gB[3]) {
            return;
        }
        canvas.drawLine(f - f4, height, f + f3, height, this.mBorderPaint);
        canvas.drawLine(f, height - f3, f, height, this.mBorderPaint);
    }

    private void updateShaderMatrix() {
        float width;
        float f;
        switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[this.gE.ordinal()]) {
            case 1:
                this.mBorderRect.set(this.gt);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.reset();
                this.mShaderMatrix.setTranslate((int) (((this.mBorderRect.width() - this.mBitmapWidth) * 0.5f) + 0.5f), (int) (((this.mBorderRect.height() - this.mBitmapHeight) * 0.5f) + 0.5f));
                break;
            case 2:
                this.mBorderRect.set(this.gt);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.reset();
                float f2 = 0.0f;
                if (this.mBitmapWidth * this.mBorderRect.height() > this.mBorderRect.width() * this.mBitmapHeight) {
                    width = this.mBorderRect.height() / this.mBitmapHeight;
                    f = (this.mBorderRect.width() - (this.mBitmapWidth * width)) * 0.5f;
                } else {
                    width = this.mBorderRect.width() / this.mBitmapWidth;
                    f2 = (this.mBorderRect.height() - (this.mBitmapHeight * width)) * 0.5f;
                    f = 0.0f;
                }
                this.mShaderMatrix.setScale(width, width);
                this.mShaderMatrix.postTranslate(((int) (f + 0.5f)) + (this.mBorderWidth / 2.0f), ((int) (f2 + 0.5f)) + (this.mBorderWidth / 2.0f));
                break;
            case 3:
                this.mShaderMatrix.reset();
                float min = (((float) this.mBitmapWidth) > this.gt.width() || ((float) this.mBitmapHeight) > this.gt.height()) ? Math.min(this.gt.width() / this.mBitmapWidth, this.gt.height() / this.mBitmapHeight) : 1.0f;
                float width2 = (int) (((this.gt.width() - (this.mBitmapWidth * min)) * 0.5f) + 0.5f);
                float height = (int) (((this.gt.height() - (this.mBitmapHeight * min)) * 0.5f) + 0.5f);
                this.mShaderMatrix.setScale(min, min);
                this.mShaderMatrix.postTranslate(width2, height);
                this.mBorderRect.set(this.gu);
                this.mShaderMatrix.mapRect(this.mBorderRect);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.setRectToRect(this.gu, this.mBorderRect, Matrix.ScaleToFit.FILL);
                break;
            case 4:
            default:
                this.mBorderRect.set(this.gu);
                this.mShaderMatrix.setRectToRect(this.gu, this.gt, Matrix.ScaleToFit.CENTER);
                this.mShaderMatrix.mapRect(this.mBorderRect);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.setRectToRect(this.gu, this.mBorderRect, Matrix.ScaleToFit.FILL);
                break;
            case 5:
                this.mBorderRect.set(this.gu);
                this.mShaderMatrix.setRectToRect(this.gu, this.gt, Matrix.ScaleToFit.END);
                this.mShaderMatrix.mapRect(this.mBorderRect);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.setRectToRect(this.gu, this.mBorderRect, Matrix.ScaleToFit.FILL);
                break;
            case 6:
                this.mBorderRect.set(this.gu);
                this.mShaderMatrix.setRectToRect(this.gu, this.gt, Matrix.ScaleToFit.START);
                this.mShaderMatrix.mapRect(this.mBorderRect);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.setRectToRect(this.gu, this.mBorderRect, Matrix.ScaleToFit.FILL);
                break;
            case 7:
                this.mBorderRect.set(this.gt);
                this.mBorderRect.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
                this.mShaderMatrix.reset();
                this.mShaderMatrix.setRectToRect(this.gu, this.mBorderRect, Matrix.ScaleToFit.FILL);
                break;
        }
        this.mDrawableRect.set(this.mBorderRect);
    }

    public b a(ColorStateList colorStateList) {
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(0);
        }
        this.gD = colorStateList;
        this.mBorderPaint.setColor(this.gD.getColorForState(getState(), ViewCompat.MEASURED_STATE_MASK));
        return this;
    }

    public b a(Shader.TileMode tileMode) {
        if (this.gw != tileMode) {
            this.gw = tileMode;
            this.gA = true;
            invalidateSelf();
        }
        return this;
    }

    public b a(ImageView.ScaleType scaleType) {
        ImageView.ScaleType scaleType2 = scaleType;
        if (scaleType == null) {
            scaleType2 = ImageView.ScaleType.FIT_CENTER;
        }
        if (this.gE != scaleType2) {
            this.gE = scaleType2;
            updateShaderMatrix();
        }
        return this;
    }

    public b b(float f, float f2, float f3, float f4) {
        HashSet hashSet = new HashSet(4);
        hashSet.add(Float.valueOf(f));
        hashSet.add(Float.valueOf(f2));
        hashSet.add(Float.valueOf(f3));
        hashSet.add(Float.valueOf(f4));
        hashSet.remove(Float.valueOf(0.0f));
        if (hashSet.size() <= 1) {
            if (hashSet.isEmpty()) {
                this.mCornerRadius = 0.0f;
            } else {
                float floatValue = ((Float) hashSet.iterator().next()).floatValue();
                if (Float.isInfinite(floatValue) || Float.isNaN(floatValue) || floatValue < 0.0f) {
                    throw new IllegalArgumentException("Invalid radius value: " + floatValue);
                }
                this.mCornerRadius = floatValue;
            }
            this.gB[0] = f > 0.0f;
            this.gB[1] = f2 > 0.0f;
            this.gB[2] = f3 > 0.0f;
            boolean[] zArr = this.gB;
            boolean z = false;
            if (f4 > 0.0f) {
                z = true;
            }
            zArr[3] = z;
            return this;
        }
        throw new IllegalArgumentException("Multiple nonzero corner radii not yet supported.");
    }

    public b b(Shader.TileMode tileMode) {
        if (this.gz != tileMode) {
            this.gz = tileMode;
            this.gA = true;
            invalidateSelf();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        if (this.gA) {
            BitmapShader bitmapShader = new BitmapShader(this.mBitmap, this.gw, this.gz);
            if (this.gw == Shader.TileMode.CLAMP && this.gz == Shader.TileMode.CLAMP) {
                bitmapShader.setLocalMatrix(this.mShaderMatrix);
            }
            this.mBitmapPaint.setShader(bitmapShader);
            this.gA = false;
        }
        if (this.gC) {
            if (this.mBorderWidth <= 0.0f) {
                canvas.drawOval(this.mDrawableRect, this.mBitmapPaint);
                return;
            }
            canvas.drawOval(this.mDrawableRect, this.mBitmapPaint);
            canvas.drawOval(this.mBorderRect, this.mBorderPaint);
        } else if (!a(this.gB)) {
            canvas.drawRect(this.mDrawableRect, this.mBitmapPaint);
            if (this.mBorderWidth > 0.0f) {
                canvas.drawRect(this.mBorderRect, this.mBorderPaint);
            }
        } else {
            float f = this.mCornerRadius;
            if (this.mBorderWidth <= 0.0f) {
                canvas.drawRoundRect(this.mDrawableRect, f, f, this.mBitmapPaint);
                h(canvas);
                return;
            }
            canvas.drawRoundRect(this.mDrawableRect, f, f, this.mBitmapPaint);
            canvas.drawRoundRect(this.mBorderRect, f, f, this.mBorderPaint);
            h(canvas);
            i(canvas);
        }
    }

    public b e(boolean z) {
        this.gC = z;
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.mBitmapPaint.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.mBitmapPaint.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public b i(float f) {
        this.mBorderWidth = f;
        this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.gD.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(@NonNull Rect rect) {
        super.onBoundsChange(rect);
        this.gt.set(rect);
        updateShaderMatrix();
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int colorForState = this.gD.getColorForState(iArr, 0);
        if (this.mBorderPaint.getColor() != colorForState) {
            this.mBorderPaint.setColor(colorForState);
            return true;
        }
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mBitmapPaint.setAlpha(i);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mBitmapPaint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        this.mBitmapPaint.setDither(z);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        this.mBitmapPaint.setFilterBitmap(z);
        invalidateSelf();
    }
}
