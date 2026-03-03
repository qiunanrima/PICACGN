package com.picacomic.fregata.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/RoundedCornersTransformation.class */
public class RoundedCornersTransformation implements Transformation {
    private int mMargin;
    private int us;
    private int ut;
    private CornerType uu;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.picacomic.fregata.utils.RoundedCornersTransformation$1  reason: invalid class name */
    /* loaded from: picacg_1.jar:com/picacomic/fregata/utils/RoundedCornersTransformation$1.class */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] uv = new int[CornerType.values().length];

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00b9 -> B:79:0x0014). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:35:0x00bd -> B:91:0x001f). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00c1 -> B:87:0x002a). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x00c5 -> B:69:0x0035). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x00c9 -> B:65:0x0040). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00cd -> B:75:0x004c). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x00d1 -> B:71:0x0058). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00d5 -> B:81:0x0064). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x00d9 -> B:77:0x0070). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x00dd -> B:89:0x007c). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x00e1 -> B:85:0x0088). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:55:0x00e5 -> B:67:0x0094). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:57:0x00e9 -> B:63:0x00a0). Please submit an issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:59:0x00ed -> B:73:0x00ac). Please submit an issue!!! */
        static {
            try {
                uv[CornerType.ALL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                uv[CornerType.TOP_LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                uv[CornerType.TOP_RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                uv[CornerType.BOTTOM_LEFT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                uv[CornerType.BOTTOM_RIGHT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                uv[CornerType.TOP.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                uv[CornerType.BOTTOM.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                uv[CornerType.LEFT.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                uv[CornerType.RIGHT.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                uv[CornerType.OTHER_TOP_LEFT.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                uv[CornerType.OTHER_TOP_RIGHT.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                uv[CornerType.OTHER_BOTTOM_LEFT.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                uv[CornerType.OTHER_BOTTOM_RIGHT.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                uv[CornerType.DIAGONAL_FROM_TOP_LEFT.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                uv[CornerType.DIAGONAL_FROM_TOP_RIGHT.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
        }
    }

    /* loaded from: picacg_1.jar:com/picacomic/fregata/utils/RoundedCornersTransformation$CornerType.class */
    public enum CornerType {
        ALL,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        OTHER_TOP_LEFT,
        OTHER_TOP_RIGHT,
        OTHER_BOTTOM_LEFT,
        OTHER_BOTTOM_RIGHT,
        DIAGONAL_FROM_TOP_LEFT,
        DIAGONAL_FROM_TOP_RIGHT
    }

    public RoundedCornersTransformation(int i, int i2) {
        this(i, i2, CornerType.ALL);
    }

    public RoundedCornersTransformation(int i, int i2, CornerType cornerType) {
        this.us = i;
        this.ut = i * 2;
        this.mMargin = i2;
        this.uu = cornerType;
    }

    private void a(Canvas canvas, Paint paint, float f, float f2) {
        float f3 = f - this.mMargin;
        float f4 = f2 - this.mMargin;
        switch (AnonymousClass1.uv[this.uu.ordinal()]) {
            case 1:
                canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f3, f4), this.us, this.us, paint);
                return;
            case 2:
                b(canvas, paint, f3, f4);
                return;
            case 3:
                c(canvas, paint, f3, f4);
                return;
            case 4:
                d(canvas, paint, f3, f4);
                return;
            case 5:
                e(canvas, paint, f3, f4);
                return;
            case 6:
                f(canvas, paint, f3, f4);
                return;
            case 7:
                g(canvas, paint, f3, f4);
                return;
            case 8:
                h(canvas, paint, f3, f4);
                return;
            case 9:
                i(canvas, paint, f3, f4);
                return;
            case 10:
                j(canvas, paint, f3, f4);
                return;
            case 11:
                k(canvas, paint, f3, f4);
                return;
            case 12:
                l(canvas, paint, f3, f4);
                return;
            case 13:
                m(canvas, paint, f3, f4);
                return;
            case 14:
                n(canvas, paint, f3, f4);
                return;
            case 15:
                o(canvas, paint, f3, f4);
                return;
            default:
                canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f3, f4), this.us, this.us, paint);
                return;
        }
    }

    private void b(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.ut, this.mMargin + this.ut), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.us, this.mMargin + this.us, f2), paint);
        canvas.drawRect(new RectF(this.mMargin + this.us, this.mMargin, f, f2), paint);
    }

    private void c(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - this.ut, this.mMargin, f, this.mMargin + this.ut), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.us, f2), paint);
        canvas.drawRect(new RectF(f - this.us, this.mMargin + this.us, f, f2), paint);
    }

    private void d(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.ut, this.mMargin + this.ut, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.ut, f2 - this.us), paint);
        canvas.drawRect(new RectF(this.mMargin + this.us, this.mMargin, f, f2), paint);
    }

    private void e(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - this.ut, f2 - this.ut, f, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.us, f2), paint);
        canvas.drawRect(new RectF(f - this.us, this.mMargin, f, f2 - this.us), paint);
    }

    private void f(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f, this.mMargin + this.ut), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.us, f, f2), paint);
    }

    private void g(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.ut, f, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f, f2 - this.us), paint);
    }

    private void h(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.ut, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin + this.us, this.mMargin, f, f2), paint);
    }

    private void i(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - this.ut, this.mMargin, f, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.us, f2), paint);
    }

    private void j(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.ut, f, f2), this.us, this.us, paint);
        canvas.drawRoundRect(new RectF(f - this.ut, this.mMargin, f, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.us, f2 - this.us), paint);
    }

    private void k(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.ut, f2), this.us, this.us, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.ut, f, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin + this.us, this.mMargin, f, f2 - this.us), paint);
    }

    private void l(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f, this.mMargin + this.ut), this.us, this.us, paint);
        canvas.drawRoundRect(new RectF(f - this.ut, this.mMargin, f, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.us, f - this.us, f2), paint);
    }

    private void m(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, f, this.mMargin + this.ut), this.us, this.us, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.ut, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin + this.us, this.mMargin + this.us, f, f2), paint);
    }

    private void n(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(this.mMargin, this.mMargin, this.mMargin + this.ut, this.mMargin + this.ut), this.us, this.us, paint);
        canvas.drawRoundRect(new RectF(f - this.ut, f2 - this.ut, f, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin + this.us, f - this.ut, f2), paint);
        canvas.drawRect(new RectF(this.mMargin + this.ut, this.mMargin, f, f2 - this.us), paint);
    }

    private void o(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawRoundRect(new RectF(f - this.ut, this.mMargin, f, this.mMargin + this.ut), this.us, this.us, paint);
        canvas.drawRoundRect(new RectF(this.mMargin, f2 - this.ut, this.mMargin + this.ut, f2), this.us, this.us, paint);
        canvas.drawRect(new RectF(this.mMargin, this.mMargin, f - this.us, f2 - this.us), paint);
        canvas.drawRect(new RectF(this.mMargin + this.us, this.mMargin + this.us, f, f2), paint);
    }

    @Override // com.squareup.picasso.Transformation
    public String key() {
        return "RoundedTransformation(radius=" + this.us + ", margin=" + this.mMargin + ", diameter=" + this.ut + ", cornerType=" + this.uu.name() + ")";
    }

    @Override // com.squareup.picasso.Transformation
    public Bitmap transform(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        a(canvas, paint, width, height);
        bitmap.recycle();
        return createBitmap;
    }
}
