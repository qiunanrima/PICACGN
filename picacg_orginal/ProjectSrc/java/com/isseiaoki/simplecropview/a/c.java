package com.isseiaoki.simplecropview.a;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.view.animation.Interpolator;
@TargetApi(14)
/* loaded from: picacg_1.jar:com/isseiaoki/simplecropview/a/c.class */
public class c implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener, a {
    private b bY = new b() { // from class: com.isseiaoki.simplecropview.a.c.1
        @Override // com.isseiaoki.simplecropview.a.b
        public void h(float f) {
        }

        @Override // com.isseiaoki.simplecropview.a.b
        public void j() {
        }

        @Override // com.isseiaoki.simplecropview.a.b
        public void k() {
        }
    };
    private ValueAnimator bX = ValueAnimator.ofFloat(0.0f, 1.0f);

    public c(Interpolator interpolator) {
        this.bX.addListener(this);
        this.bX.addUpdateListener(this);
        this.bX.setInterpolator(interpolator);
    }

    @Override // com.isseiaoki.simplecropview.a.a
    public void a(long j) {
        if (j >= 0) {
            this.bX.setDuration(j);
        } else {
            this.bX.setDuration(150L);
        }
        this.bX.start();
    }

    @Override // com.isseiaoki.simplecropview.a.a
    public void a(b bVar) {
        if (bVar != null) {
            this.bY = bVar;
        }
    }

    @Override // com.isseiaoki.simplecropview.a.a
    public void l() {
        this.bX.cancel();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        this.bY.k();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        this.bY.k();
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        this.bY.j();
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.bY.h(valueAnimator.getAnimatedFraction());
    }
}
