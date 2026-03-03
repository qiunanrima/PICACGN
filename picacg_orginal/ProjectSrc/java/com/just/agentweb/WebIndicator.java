package com.just.agentweb;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
/* loaded from: picacg_1.jar:com/just/agentweb/WebIndicator.class */
public class WebIndicator extends BaseIndicatorView implements i {
    private static int eV = 8000;
    private static int eW = 450;
    public static int fa = 3;
    private Animator eT;
    private int eU;
    private int eX;
    private float eY;
    private float eZ;
    private ValueAnimator.AnimatorUpdateListener fb;
    private AnimatorListenerAdapter fc;
    private int mColor;
    private Paint mPaint;

    public WebIndicator(Context context) {
        this(context, null);
    }

    public WebIndicator(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WebIndicator(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.eU = 0;
        this.eX = 0;
        this.eY = 0.0f;
        this.eZ = 0.0f;
        this.fb = new ValueAnimator.AnimatorUpdateListener() { // from class: com.just.agentweb.WebIndicator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                WebIndicator.this.eZ = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                WebIndicator.this.invalidate();
            }
        };
        this.fc = new AnimatorListenerAdapter() { // from class: com.just.agentweb.WebIndicator.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                WebIndicator.this.as();
            }
        };
        a(context, attributeSet, i);
    }

    private void a(Context context, AttributeSet attributeSet, int i) {
        this.mPaint = new Paint();
        this.mColor = Color.parseColor("#1aad19");
        this.mPaint.setAntiAlias(true);
        this.mPaint.setColor(this.mColor);
        this.mPaint.setDither(true);
        this.mPaint.setStrokeCap(Paint.Cap.SQUARE);
        this.eU = context.getResources().getDisplayMetrics().widthPixels;
        fa = h.a(context, 3.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void as() {
        if (this.eX == 2 && this.eZ == 100.0f) {
            setVisibility(8);
            this.eZ = 0.0f;
            setAlpha(1.0f);
        }
        this.eX = 0;
    }

    private void c(boolean z) {
        AnimatorSet animatorSet;
        float f = z ? 100.0f : 95.0f;
        if (this.eT != null && this.eT.isStarted()) {
            this.eT.cancel();
        }
        this.eZ = this.eZ == 0.0f ? 1.0E-8f : this.eZ;
        ag.c("WebIndicator", "mCurrentProgress:" + this.eZ + " v:" + f + "  :" + (1.0f - this.eZ));
        if (z) {
            ValueAnimator valueAnimator = null;
            if (this.eZ < 95.0f) {
                valueAnimator = ValueAnimator.ofFloat(this.eZ, 95.0f);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.setDuration(((1.0f - (this.eZ / 100.0f)) - 0.05f) * eW);
                valueAnimator.setInterpolator(new DecelerateInterpolator());
                valueAnimator.addUpdateListener(this.fb);
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alpha", 1.0f, 0.0f);
            ofFloat.setDuration(600L);
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(95.0f, 100.0f);
            ofFloat2.setDuration(600L);
            ofFloat2.addUpdateListener(this.fb);
            AnimatorSet animatorSet2 = new AnimatorSet();
            animatorSet2.playTogether(ofFloat, ofFloat2);
            if (valueAnimator != null) {
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.play(animatorSet2).after(valueAnimator);
                animatorSet = animatorSet3;
            } else {
                animatorSet = animatorSet2;
            }
            animatorSet.addListener(this.fc);
            animatorSet.start();
            this.eT = animatorSet;
        } else {
            ValueAnimator ofFloat3 = ValueAnimator.ofFloat(this.eZ, f);
            ofFloat3.setInterpolator(new LinearInterpolator());
            ofFloat3.setDuration(((1.0f - (this.eZ / 100.0f)) - 0.05f) * eV);
            ofFloat3.addUpdateListener(this.fb);
            ofFloat3.start();
            this.eT = ofFloat3;
        }
        this.eX = 1;
        this.eY = f;
    }

    @Override // com.just.agentweb.BaseIndicatorView
    public FrameLayout.LayoutParams Y() {
        return new FrameLayout.LayoutParams(-1, fa);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (this.eZ / 100.0f) * Float.valueOf(getWidth()).floatValue(), getHeight(), this.mPaint);
    }

    @Override // com.just.agentweb.BaseIndicatorView, com.just.agentweb.i
    public void hide() {
        this.eX = 2;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.eT == null || !this.eT.isStarted()) {
            return;
        }
        this.eT.cancel();
        this.eT = null;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = size;
        if (mode == Integer.MIN_VALUE) {
            i3 = size <= getContext().getResources().getDisplayMetrics().widthPixels ? size : getContext().getResources().getDisplayMetrics().widthPixels;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = fa;
        }
        setMeasuredDimension(i3, size2);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.eU = getMeasuredWidth();
        int i5 = getContext().getResources().getDisplayMetrics().widthPixels;
        if (this.eU >= i5) {
            eW = 450;
            eV = 8000;
        } else {
            float floatValue = this.eU / Float.valueOf(i5).floatValue();
            eV = (int) (8000.0f * floatValue);
            eW = (int) (floatValue * 450.0f);
        }
        ag.c("WebProgress", "CURRENT_MAX_UNIFORM_SPEED_DURATION" + eV);
    }

    @Override // com.just.agentweb.BaseIndicatorView, com.just.agentweb.i
    public void reset() {
        this.eZ = 0.0f;
        if (this.eT == null || !this.eT.isStarted()) {
            return;
        }
        this.eT.cancel();
    }

    public void setColor(int i) {
        this.mColor = i;
        this.mPaint.setColor(i);
    }

    public void setColor(String str) {
        setColor(Color.parseColor(str));
    }

    public void setProgress(float f) {
        if (getVisibility() == 8) {
            setVisibility(0);
        }
        if (f >= 95.0f && this.eX != 2) {
            c(true);
        }
    }

    @Override // com.just.agentweb.BaseIndicatorView, com.just.agentweb.i
    public void setProgress(int i) {
        setProgress(Float.valueOf(i).floatValue());
    }

    @Override // com.just.agentweb.BaseIndicatorView, com.just.agentweb.i
    public void show() {
        if (getVisibility() == 8) {
            setVisibility(0);
            this.eZ = 0.0f;
            c(false);
        }
    }
}
