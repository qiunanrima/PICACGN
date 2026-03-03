package com.makeramen.roundedimageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import com.makeramen.roundedimageview.a;
/* loaded from: picacg_1.jar:com/makeramen/roundedimageview/RoundedImageView.class */
public class RoundedImageView extends ImageView {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final Shader.TileMode gF = Shader.TileMode.CLAMP;
    private static final ImageView.ScaleType[] gG = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private ColorStateList gD;
    private ImageView.ScaleType gE;
    private final float[] gH;
    private Drawable gI;
    private boolean gJ;
    private boolean gK;
    private boolean gL;
    private boolean gM;
    private int gN;
    private Shader.TileMode gw;
    private Shader.TileMode gz;
    private float mBorderWidth;
    private ColorFilter mColorFilter;
    private Drawable mDrawable;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.makeramen.roundedimageview.RoundedImageView$1  reason: invalid class name */
    /* loaded from: picacg_1.jar:com/makeramen/roundedimageview/RoundedImageView$1.class */
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
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_START.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_END.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ImageView.ScaleType.FIT_XY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public RoundedImageView(Context context) {
        super(context);
        this.gH = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.gD = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        this.mBorderWidth = 0.0f;
        this.mColorFilter = null;
        this.gJ = false;
        this.gK = false;
        this.gL = false;
        this.gM = false;
        this.gE = ImageView.ScaleType.FIT_CENTER;
        this.gw = gF;
        this.gz = gF;
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.gH = new float[]{0.0f, 0.0f, 0.0f, 0.0f};
        this.gD = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        this.mBorderWidth = 0.0f;
        this.mColorFilter = null;
        this.gJ = false;
        this.gK = false;
        this.gL = false;
        this.gM = false;
        this.gE = ImageView.ScaleType.FIT_CENTER;
        this.gw = gF;
        this.gz = gF;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, a.C0005a.RoundedImageView, i, 0);
        int i2 = obtainStyledAttributes.getInt(a.C0005a.RoundedImageView_android_scaleType, -1);
        if (i2 >= 0) {
            setScaleType(gG[i2]);
        } else {
            setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        float dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(a.C0005a.RoundedImageView_riv_corner_radius, -1);
        this.gH[0] = obtainStyledAttributes.getDimensionPixelSize(a.C0005a.RoundedImageView_riv_corner_radius_top_left, -1);
        this.gH[1] = obtainStyledAttributes.getDimensionPixelSize(a.C0005a.RoundedImageView_riv_corner_radius_top_right, -1);
        this.gH[2] = obtainStyledAttributes.getDimensionPixelSize(a.C0005a.RoundedImageView_riv_corner_radius_bottom_right, -1);
        this.gH[3] = obtainStyledAttributes.getDimensionPixelSize(a.C0005a.RoundedImageView_riv_corner_radius_bottom_left, -1);
        int length = this.gH.length;
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            if (this.gH[i3] < 0.0f) {
                this.gH[i3] = 0.0f;
            } else {
                z = true;
            }
        }
        if (!z) {
            float f = dimensionPixelSize < 0.0f ? 0.0f : dimensionPixelSize;
            int length2 = this.gH.length;
            for (int i4 = 0; i4 < length2; i4++) {
                this.gH[i4] = f;
            }
        }
        this.mBorderWidth = obtainStyledAttributes.getDimensionPixelSize(a.C0005a.RoundedImageView_riv_border_width, -1);
        if (this.mBorderWidth < 0.0f) {
            this.mBorderWidth = 0.0f;
        }
        this.gD = obtainStyledAttributes.getColorStateList(a.C0005a.RoundedImageView_riv_border_color);
        if (this.gD == null) {
            this.gD = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        }
        this.gM = obtainStyledAttributes.getBoolean(a.C0005a.RoundedImageView_riv_mutate_background, false);
        this.gL = obtainStyledAttributes.getBoolean(a.C0005a.RoundedImageView_riv_oval, false);
        int i5 = obtainStyledAttributes.getInt(a.C0005a.RoundedImageView_riv_tile_mode, -2);
        if (i5 != -2) {
            setTileModeX(h(i5));
            setTileModeY(h(i5));
        }
        int i6 = obtainStyledAttributes.getInt(a.C0005a.RoundedImageView_riv_tile_mode_x, -2);
        if (i6 != -2) {
            setTileModeX(h(i6));
        }
        int i7 = obtainStyledAttributes.getInt(a.C0005a.RoundedImageView_riv_tile_mode_y, -2);
        if (i7 != -2) {
            setTileModeY(h(i7));
        }
        bh();
        f(true);
        obtainStyledAttributes.recycle();
    }

    private Drawable bg() {
        Drawable drawable;
        Resources resources = getResources();
        if (resources == null) {
            return null;
        }
        if (this.gN != 0) {
            try {
                drawable = resources.getDrawable(this.gN);
            } catch (Exception e) {
                Log.w("RoundedImageView", "Unable to find resource: " + this.gN, e);
                this.gN = 0;
            }
            return b.a(drawable);
        }
        drawable = null;
        return b.a(drawable);
    }

    private void bh() {
        c(this.mDrawable);
    }

    private void bi() {
        if (this.mDrawable == null || !this.gJ) {
            return;
        }
        this.mDrawable = this.mDrawable.mutate();
        if (this.gK) {
            this.mDrawable.setColorFilter(this.mColorFilter);
        }
    }

    private void c(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (drawable instanceof b) {
            b bVar = (b) drawable;
            bVar.a(this.gE).i(this.mBorderWidth).a(this.gD).e(this.gL).a(this.gw).b(this.gz);
            if (this.gH != null) {
                bVar.b(this.gH[0], this.gH[1], this.gH[2], this.gH[3]);
            }
            bi();
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            for (int i = 0; i < numberOfLayers; i++) {
                c(layerDrawable.getDrawable(i));
            }
        }
    }

    private void f(boolean z) {
        if (this.gM) {
            if (z) {
                this.gI = b.a(this.gI);
            }
            c(this.gI);
        }
    }

    private static Shader.TileMode h(int i) {
        switch (i) {
            case 0:
                return Shader.TileMode.CLAMP;
            case 1:
                return Shader.TileMode.REPEAT;
            case 2:
                return Shader.TileMode.MIRROR;
            default:
                return null;
        }
    }

    public void c(float f, float f2, float f3, float f4) {
        if (this.gH[0] == f && this.gH[1] == f2 && this.gH[2] == f4 && this.gH[3] == f3) {
            return;
        }
        this.gH[0] = f;
        this.gH[1] = f2;
        this.gH[3] = f3;
        this.gH[2] = f4;
        bh();
        f(false);
        invalidate();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @ColorInt
    public int getBorderColor() {
        return this.gD.getDefaultColor();
    }

    public ColorStateList getBorderColors() {
        return this.gD;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public float getCornerRadius() {
        return getMaxCornerRadius();
    }

    public float getMaxCornerRadius() {
        float f = 0.0f;
        for (float f2 : this.gH) {
            f = Math.max(f2, f);
        }
        return f;
    }

    @Override // android.widget.ImageView
    public ImageView.ScaleType getScaleType() {
        return this.gE;
    }

    public Shader.TileMode getTileModeX() {
        return this.gw;
    }

    public Shader.TileMode getTileModeY() {
        return this.gz;
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        this.gI = drawable;
        f(true);
        super.setBackgroundDrawable(this.gI);
    }

    public void setBorderColor(@ColorInt int i) {
        setBorderColor(ColorStateList.valueOf(i));
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (this.gD.equals(colorStateList)) {
            return;
        }
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(ViewCompat.MEASURED_STATE_MASK);
        }
        this.gD = colorStateList;
        bh();
        f(false);
        if (this.mBorderWidth > 0.0f) {
            invalidate();
        }
    }

    public void setBorderWidth(float f) {
        if (this.mBorderWidth == f) {
            return;
        }
        this.mBorderWidth = f;
        bh();
        f(false);
        invalidate();
    }

    public void setBorderWidth(@DimenRes int i) {
        setBorderWidth(getResources().getDimension(i));
    }

    @Override // android.widget.ImageView
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mColorFilter != colorFilter) {
            this.mColorFilter = colorFilter;
            this.gK = true;
            this.gJ = true;
            bi();
            invalidate();
        }
    }

    public void setCornerRadius(float f) {
        c(f, f, f, f);
    }

    public void setCornerRadiusDimen(@DimenRes int i) {
        float dimension = getResources().getDimension(i);
        c(dimension, dimension, dimension, dimension);
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        this.gN = 0;
        this.mDrawable = b.e(bitmap);
        bh();
        super.setImageDrawable(this.mDrawable);
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        this.gN = 0;
        this.mDrawable = b.a(drawable);
        bh();
        super.setImageDrawable(this.mDrawable);
    }

    @Override // android.widget.ImageView
    public void setImageResource(@DrawableRes int i) {
        if (this.gN != i) {
            this.gN = i;
            this.mDrawable = bg();
            bh();
            super.setImageDrawable(this.mDrawable);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    public void setOval(boolean z) {
        this.gL = z;
        bh();
        f(false);
        invalidate();
    }

    @Override // android.widget.ImageView
    public void setScaleType(ImageView.ScaleType scaleType) {
        if (this.gE != scaleType) {
            this.gE = scaleType;
            switch (AnonymousClass1.$SwitchMap$android$widget$ImageView$ScaleType[scaleType.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    super.setScaleType(ImageView.ScaleType.FIT_XY);
                    break;
                default:
                    super.setScaleType(scaleType);
                    break;
            }
            bh();
            f(false);
            invalidate();
        }
    }

    public void setTileModeX(Shader.TileMode tileMode) {
        if (this.gw == tileMode) {
            return;
        }
        this.gw = tileMode;
        bh();
        f(false);
        invalidate();
    }

    public void setTileModeY(Shader.TileMode tileMode) {
        if (this.gz == tileMode) {
            return;
        }
        this.gz = tileMode;
        bh();
        f(false);
        invalidate();
    }
}
