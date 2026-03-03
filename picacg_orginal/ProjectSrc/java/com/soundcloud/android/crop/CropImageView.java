package com.soundcloud.android.crop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import com.soundcloud.android.crop.HighlightView;
import com.soundcloud.android.crop.ImageViewTouchBase;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: picacg_1.jar:com/soundcloud/android/crop/CropImageView.class */
public class CropImageView extends ImageViewTouchBase {
    Context context;
    ArrayList<HighlightView> vt;
    HighlightView vu;
    private float vv;
    private float vw;
    private int vx;
    private int vy;

    public CropImageView(Context context) {
        super(context);
        this.vt = new ArrayList<>();
    }

    public CropImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.vt = new ArrayList<>();
    }

    public CropImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.vt = new ArrayList<>();
    }

    private void a(HighlightView highlightView) {
        Rect rect = highlightView.vF;
        int max = Math.max(0, getLeft() - rect.left);
        int min = Math.min(0, getRight() - rect.right);
        int max2 = Math.max(0, getTop() - rect.top);
        int min2 = Math.min(0, getBottom() - rect.bottom);
        if (max == 0) {
            max = min;
        }
        if (max2 != 0) {
            min2 = max2;
        }
        if (max == 0 && min2 == 0) {
            return;
        }
        p(max, min2);
    }

    private void b(HighlightView highlightView) {
        Rect rect = highlightView.vF;
        float max = Math.max(1.0f, Math.min((getWidth() / rect.width()) * 0.6f, (getHeight() / rect.height()) * 0.6f) * getScale());
        if (Math.abs(max - getScale()) / max > 0.1d) {
            float[] fArr = {highlightView.vE.centerX(), highlightView.vE.centerY()};
            getUnrotatedMatrix().mapPoints(fArr);
            d(max, fArr[0], fArr[1], 300.0f);
        }
        a(highlightView);
    }

    @Override // com.soundcloud.android.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ void a(Bitmap bitmap, boolean z) {
        super.a(bitmap, z);
    }

    @Override // com.soundcloud.android.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ void a(e eVar, boolean z) {
        super.a(eVar, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.soundcloud.android.crop.ImageViewTouchBase
    public void c(float f, float f2, float f3) {
        super.c(f, f2, f3);
        Iterator<HighlightView> it = this.vt.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.matrix.set(getUnrotatedMatrix());
            next.invalidate();
        }
    }

    public void c(HighlightView highlightView) {
        this.vt.add(highlightView);
        invalidate();
    }

    @Override // com.soundcloud.android.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.soundcloud.android.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ Matrix getUnrotatedMatrix() {
        return super.getUnrotatedMatrix();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.soundcloud.android.crop.ImageViewTouchBase
    public void l(float f, float f2) {
        super.l(f, f2);
        Iterator<HighlightView> it = this.vt.iterator();
        while (it.hasNext()) {
            HighlightView next = it.next();
            next.matrix.postTranslate(f, f2);
            next.invalidate();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Iterator<HighlightView> it = this.vt.iterator();
        while (it.hasNext()) {
            it.next().draw(canvas);
        }
    }

    @Override // com.soundcloud.android.crop.ImageViewTouchBase, android.view.View, android.view.KeyEvent.Callback
    public /* bridge */ /* synthetic */ boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.soundcloud.android.crop.ImageViewTouchBase, android.view.View, android.view.KeyEvent.Callback
    public /* bridge */ /* synthetic */ boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.soundcloud.android.crop.ImageViewTouchBase, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.wg.getBitmap() != null) {
            Iterator<HighlightView> it = this.vt.iterator();
            while (it.hasNext()) {
                HighlightView next = it.next();
                next.matrix.set(getUnrotatedMatrix());
                next.invalidate();
                if (next.hasFocus()) {
                    b(next);
                }
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (((CropImageActivity) this.context).el()) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                Iterator<HighlightView> it = this.vt.iterator();
                while (it.hasNext()) {
                    HighlightView next = it.next();
                    int m = next.m(motionEvent.getX(), motionEvent.getY());
                    if (m != 1) {
                        this.vx = m;
                        this.vu = next;
                        this.vv = motionEvent.getX();
                        this.vw = motionEvent.getY();
                        this.vy = motionEvent.getPointerId(motionEvent.getActionIndex());
                        this.vu.a(m == 32 ? HighlightView.ModifyMode.Move : HighlightView.ModifyMode.Grow);
                        return true;
                    }
                }
                return true;
            case 1:
                if (this.vu != null) {
                    b(this.vu);
                    this.vu.a(HighlightView.ModifyMode.None);
                }
                this.vu = null;
                ep();
                return true;
            case 2:
                if (this.vu != null && motionEvent.getPointerId(motionEvent.getActionIndex()) == this.vy) {
                    this.vu.a(this.vx, motionEvent.getX() - this.vv, motionEvent.getY() - this.vw);
                    this.vv = motionEvent.getX();
                    this.vw = motionEvent.getY();
                }
                if (getScale() == 1.0f) {
                    ep();
                    return true;
                }
                return true;
            default:
                return true;
        }
    }

    @Override // com.soundcloud.android.crop.ImageViewTouchBase, android.widget.ImageView
    public /* bridge */ /* synthetic */ void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
    }

    @Override // com.soundcloud.android.crop.ImageViewTouchBase
    public /* bridge */ /* synthetic */ void setRecycler(ImageViewTouchBase.a aVar) {
        super.setRecycler(aVar);
    }
}
