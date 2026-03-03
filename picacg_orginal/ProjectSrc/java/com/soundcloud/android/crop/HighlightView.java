package com.soundcloud.android.crop;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import android.view.View;
import com.soundcloud.android.crop.d;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: picacg_1.jar:com/soundcloud/android/crop/HighlightView.class */
public class HighlightView {
    private int highlightColor;
    Matrix matrix;
    RectF vE;
    Rect vF;
    private RectF vG;
    private View vK;
    private boolean vL;
    private boolean vM;
    private boolean vP;
    private float vQ;
    private float vR;
    private float vS;
    private boolean vT;
    private final Paint vH = new Paint();
    private final Paint vI = new Paint();
    private final Paint vJ = new Paint();
    private ModifyMode vN = ModifyMode.None;
    private HandleMode vO = HandleMode.Changing;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: picacg_1.jar:com/soundcloud/android/crop/HighlightView$HandleMode.class */
    public enum HandleMode {
        Changing,
        Always,
        Never
    }

    /* loaded from: picacg_1.jar:com/soundcloud/android/crop/HighlightView$ModifyMode.class */
    enum ModifyMode {
        None,
        Move,
        Grow
    }

    public HighlightView(View view) {
        this.vK = view;
        az(view.getContext());
    }

    private void az(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(d.a.cropImageStyle, typedValue, true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(typedValue.resourceId, d.e.CropImageView);
        try {
            this.vL = obtainStyledAttributes.getBoolean(d.e.CropImageView_showThirds, false);
            this.vM = obtainStyledAttributes.getBoolean(d.e.CropImageView_showCircle, false);
            this.highlightColor = obtainStyledAttributes.getColor(d.e.CropImageView_highlightColor, -13388315);
            this.vO = HandleMode.values()[obtainStyledAttributes.getInt(d.e.CropImageView_showHandles, 0)];
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private Rect eo() {
        RectF rectF = new RectF(this.vE.left, this.vE.top, this.vE.right, this.vE.bottom);
        this.matrix.mapRect(rectF);
        return new Rect(Math.round(rectF.left), Math.round(rectF.top), Math.round(rectF.right), Math.round(rectF.bottom));
    }

    private void f(Canvas canvas) {
        int i = this.vF.left;
        int i2 = (this.vF.right - this.vF.left) / 2;
        float f = this.vF.top + ((this.vF.bottom - this.vF.top) / 2);
        canvas.drawCircle(this.vF.left, f, this.vR, this.vJ);
        float f2 = i + i2;
        canvas.drawCircle(f2, this.vF.top, this.vR, this.vJ);
        canvas.drawCircle(this.vF.right, f, this.vR, this.vJ);
        canvas.drawCircle(f2, this.vF.bottom, this.vR, this.vJ);
    }

    private void k(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, canvas.getWidth(), this.vF.top, this.vH);
        canvas.drawRect(0.0f, this.vF.bottom, canvas.getWidth(), canvas.getHeight(), this.vH);
        canvas.drawRect(0.0f, this.vF.top, this.vF.left, this.vF.bottom, this.vH);
        canvas.drawRect(this.vF.right, this.vF.top, canvas.getWidth(), this.vF.bottom, this.vH);
    }

    private float l(float f) {
        return f * this.vK.getResources().getDisplayMetrics().density;
    }

    @SuppressLint({"NewApi"})
    private boolean l(Canvas canvas) {
        if (Build.VERSION.SDK_INT == 17) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 14 || Build.VERSION.SDK_INT > 15) {
            return true;
        }
        return !canvas.isHardwareAccelerated();
    }

    private void m(Canvas canvas) {
        this.vI.setStrokeWidth(1.0f);
        float f = (this.vF.right - this.vF.left) / 3;
        float f2 = (this.vF.bottom - this.vF.top) / 3;
        canvas.drawLine(this.vF.left + f, this.vF.top, this.vF.left + f, this.vF.bottom, this.vI);
        float f3 = f * 2.0f;
        canvas.drawLine(this.vF.left + f3, this.vF.top, this.vF.left + f3, this.vF.bottom, this.vI);
        canvas.drawLine(this.vF.left, this.vF.top + f2, this.vF.right, this.vF.top + f2, this.vI);
        float f4 = f2 * 2.0f;
        canvas.drawLine(this.vF.left, this.vF.top + f4, this.vF.right, this.vF.top + f4, this.vI);
    }

    private void n(Canvas canvas) {
        this.vI.setStrokeWidth(1.0f);
        canvas.drawOval(new RectF(this.vF), this.vI);
    }

    public void G(boolean z) {
        this.vT = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(int i, float f, float f2) {
        Rect eo = eo();
        if (i == 32) {
            n(f * (this.vE.width() / eo.width()), f2 * (this.vE.height() / eo.height()));
            return;
        }
        if ((i & 6) == 0) {
            f = 0.0f;
        }
        if ((i & 24) == 0) {
            f2 = 0.0f;
        }
        float width = this.vE.width() / eo.width();
        float height = this.vE.height() / eo.height();
        float f3 = (i & 2) != 0 ? -1 : 1;
        int i2 = 1;
        if ((i & 8) != 0) {
            i2 = -1;
        }
        o(f3 * f * width, i2 * f2 * height);
    }

    public void a(Matrix matrix, Rect rect, RectF rectF, boolean z) {
        this.matrix = new Matrix(matrix);
        this.vE = rectF;
        this.vG = new RectF(rect);
        this.vP = z;
        this.vQ = this.vE.width() / this.vE.height();
        this.vF = eo();
        this.vH.setARGB(125, 50, 50, 50);
        this.vI.setStyle(Paint.Style.STROKE);
        this.vI.setAntiAlias(true);
        this.vS = l(2.0f);
        this.vJ.setColor(this.highlightColor);
        this.vJ.setStyle(Paint.Style.FILL);
        this.vJ.setAntiAlias(true);
        this.vR = l(12.0f);
        this.vN = ModifyMode.None;
    }

    public void a(ModifyMode modifyMode) {
        if (modifyMode != this.vN) {
            this.vN = modifyMode;
            this.vK.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void draw(Canvas canvas) {
        canvas.save();
        Path path = new Path();
        this.vI.setStrokeWidth(this.vS);
        if (!hasFocus()) {
            this.vI.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawRect(this.vF, this.vI);
            return;
        }
        Rect rect = new Rect();
        this.vK.getDrawingRect(rect);
        path.addRect(new RectF(this.vF), Path.Direction.CW);
        this.vI.setColor(this.highlightColor);
        if (l(canvas)) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            canvas.drawRect(rect, this.vH);
        } else {
            k(canvas);
        }
        canvas.restore();
        canvas.drawPath(path, this.vI);
        if (this.vL) {
            m(canvas);
        }
        if (this.vM) {
            n(canvas);
        }
        if (this.vO == HandleMode.Always || (this.vO == HandleMode.Changing && this.vN == ModifyMode.Grow)) {
            f(canvas);
        }
    }

    public boolean hasFocus() {
        return this.vT;
    }

    public void invalidate() {
        this.vF = eo();
    }

    public int m(float f, float f2) {
        Rect eo = eo();
        boolean z = f2 >= ((float) eo.top) - 20.0f && f2 < ((float) eo.bottom) + 20.0f;
        boolean z2 = false;
        if (f >= eo.left - 20.0f) {
            z2 = false;
            if (f < eo.right + 20.0f) {
                z2 = true;
            }
        }
        int i = (Math.abs(((float) eo.left) - f) >= 20.0f || !z) ? 1 : 3;
        int i2 = i;
        if (Math.abs(eo.right - f) < 20.0f) {
            i2 = i;
            if (z) {
                i2 = i | 4;
            }
        }
        int i3 = i2;
        if (Math.abs(eo.top - f2) < 20.0f) {
            i3 = i2;
            if (z2) {
                i3 = i2 | 8;
            }
        }
        int i4 = i3;
        if (Math.abs(eo.bottom - f2) < 20.0f) {
            i4 = i3;
            if (z2) {
                i4 = i3 | 16;
            }
        }
        int i5 = i4;
        if (i4 == 1) {
            i5 = i4;
            if (eo.contains((int) f, (int) f2)) {
                i5 = 32;
            }
        }
        return i5;
    }

    public Rect m(float f) {
        return new Rect((int) (this.vE.left * f), (int) (this.vE.top * f), (int) (this.vE.right * f), (int) (this.vE.bottom * f));
    }

    void n(float f, float f2) {
        Rect rect = new Rect(this.vF);
        this.vE.offset(f, f2);
        this.vE.offset(Math.max(0.0f, this.vG.left - this.vE.left), Math.max(0.0f, this.vG.top - this.vE.top));
        this.vE.offset(Math.min(0.0f, this.vG.right - this.vE.right), Math.min(0.0f, this.vG.bottom - this.vE.bottom));
        this.vF = eo();
        rect.union(this.vF);
        rect.inset(-((int) this.vR), -((int) this.vR));
        this.vK.invalidate(rect);
    }

    void o(float f, float f2) {
        float f3 = f;
        float f4 = f2;
        if (this.vP) {
            if (f != 0.0f) {
                f4 = f / this.vQ;
                f3 = f;
            } else {
                f3 = f;
                f4 = f2;
                if (f2 != 0.0f) {
                    f3 = this.vQ * f2;
                    f4 = f2;
                }
            }
        }
        RectF rectF = new RectF(this.vE);
        float f5 = f3;
        float f6 = f4;
        if (f3 > 0.0f) {
            f5 = f3;
            f6 = f4;
            if (rectF.width() + (f3 * 2.0f) > this.vG.width()) {
                float width = (this.vG.width() - rectF.width()) / 2.0f;
                f5 = width;
                f6 = f4;
                if (this.vP) {
                    f6 = width / this.vQ;
                    f5 = width;
                }
            }
        }
        float f7 = f5;
        float f8 = f6;
        if (f6 > 0.0f) {
            f7 = f5;
            f8 = f6;
            if (rectF.height() + (f6 * 2.0f) > this.vG.height()) {
                float height = (this.vG.height() - rectF.height()) / 2.0f;
                f7 = f5;
                f8 = height;
                if (this.vP) {
                    f7 = this.vQ * height;
                    f8 = height;
                }
            }
        }
        rectF.inset(-f7, -f8);
        float f9 = 25.0f;
        if (rectF.width() < 25.0f) {
            rectF.inset((-(25.0f - rectF.width())) / 2.0f, 0.0f);
        }
        if (this.vP) {
            f9 = 25.0f / this.vQ;
        }
        if (rectF.height() < f9) {
            rectF.inset(0.0f, (-(f9 - rectF.height())) / 2.0f);
        }
        if (rectF.left < this.vG.left) {
            rectF.offset(this.vG.left - rectF.left, 0.0f);
        } else if (rectF.right > this.vG.right) {
            rectF.offset(-(rectF.right - this.vG.right), 0.0f);
        }
        if (rectF.top < this.vG.top) {
            rectF.offset(0.0f, this.vG.top - rectF.top);
        } else if (rectF.bottom > this.vG.bottom) {
            rectF.offset(0.0f, -(rectF.bottom - this.vG.bottom));
        }
        this.vE.set(rectF);
        this.vF = eo();
        this.vK.invalidate();
    }
}
