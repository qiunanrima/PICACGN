package com.just.agentweb;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
/* loaded from: picacg_1.jar:com/just/agentweb/NestedScrollAgentWebView.class */
public class NestedScrollAgentWebView extends AgentWebView implements NestedScrollingChild {
    private NestedScrollingChildHelper mChildHelper;
    private int mLastMotionY;
    private int mNestedYOffset;
    private final int[] mScrollConsumed;
    private final int[] mScrollOffset;

    public NestedScrollAgentWebView(Context context) {
        super(context);
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        init();
    }

    public NestedScrollAgentWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScrollOffset = new int[2];
        this.mScrollConsumed = new int[2];
        init();
    }

    private void init() {
        this.mChildHelper = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.mChildHelper.dispatchNestedFling(f, f2, z);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.mChildHelper.dispatchNestedPreFling(f, f2);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.mChildHelper.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.mChildHelper.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean hasNestedScrollingParent() {
        return this.mChildHelper.hasNestedScrollingParent();
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean isNestedScrollingEnabled() {
        return this.mChildHelper.isNestedScrollingEnabled();
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        boolean z = false;
        if (actionMasked == 0) {
            this.mNestedYOffset = 0;
        }
        int y = (int) motionEvent.getY();
        motionEvent.offsetLocation(0.0f, this.mNestedYOffset);
        if (actionMasked != 5) {
            switch (actionMasked) {
                case 0:
                    this.mLastMotionY = y;
                    startNestedScroll(2);
                    z = super.onTouchEvent(motionEvent);
                    break;
                case 2:
                    int i = this.mLastMotionY - y;
                    int i2 = i;
                    if (dispatchNestedPreScroll(0, i, this.mScrollConsumed, this.mScrollOffset)) {
                        i2 = i - this.mScrollConsumed[1];
                        obtain.offsetLocation(0.0f, this.mScrollOffset[1]);
                        this.mNestedYOffset += this.mScrollOffset[1];
                    }
                    this.mLastMotionY = y - this.mScrollOffset[1];
                    int scrollY = getScrollY();
                    int max = Math.max(0, scrollY + i2) - scrollY;
                    if (dispatchNestedScroll(0, max, 0, i2 - max, this.mScrollOffset)) {
                        this.mLastMotionY -= this.mScrollOffset[1];
                        obtain.offsetLocation(0.0f, this.mScrollOffset[1]);
                        this.mNestedYOffset += this.mScrollOffset[1];
                    }
                    z = super.onTouchEvent(obtain);
                    obtain.recycle();
                    break;
            }
            return z;
        }
        stopNestedScroll();
        z = super.onTouchEvent(motionEvent);
        return z;
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        this.mChildHelper.setNestedScrollingEnabled(z);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public boolean startNestedScroll(int i) {
        return this.mChildHelper.startNestedScroll(i);
    }

    @Override // android.view.View, android.support.v4.view.NestedScrollingChild
    public void stopNestedScroll() {
        this.mChildHelper.stopNestedScroll();
    }
}
