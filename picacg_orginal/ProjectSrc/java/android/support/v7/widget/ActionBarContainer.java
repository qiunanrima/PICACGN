package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v7.appcompat.R;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v7/widget/ActionBarContainer.class */
public class ActionBarContainer extends FrameLayout {
    private View mActionBarView;
    Drawable mBackground;
    private View mContextView;
    private int mHeight;
    boolean mIsSplit;
    boolean mIsStacked;
    private boolean mIsTransitioning;
    Drawable mSplitBackground;
    Drawable mStackedBackground;
    private View mTabContainer;

    public ActionBarContainer(Context context) {
        this(context, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0087, code lost:
        if (r5.mSplitBackground == null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x008a, code lost:
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x00a3, code lost:
        if (r5.mStackedBackground == null) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ActionBarContainer(android.content.Context r6, android.util.AttributeSet r7) {
        /*
            r5 = this;
            r0 = r5
            r1 = r6
            r2 = r7
            r0.<init>(r1, r2)
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 21
            if (r0 < r1) goto L1a
            android.support.v7.widget.ActionBarBackgroundDrawableV21 r0 = new android.support.v7.widget.ActionBarBackgroundDrawableV21
            r1 = r0
            r2 = r5
            r1.<init>(r2)
            r8 = r0
            goto L23
        L1a:
            android.support.v7.widget.ActionBarBackgroundDrawable r0 = new android.support.v7.widget.ActionBarBackgroundDrawable
            r1 = r0
            r2 = r5
            r1.<init>(r2)
            r8 = r0
        L23:
            r0 = r5
            r1 = r8
            android.support.v4.view.ViewCompat.setBackground(r0, r1)
            r0 = r6
            r1 = r7
            int[] r2 = android.support.v7.appcompat.R.styleable.ActionBar
            android.content.res.TypedArray r0 = r0.obtainStyledAttributes(r1, r2)
            r6 = r0
            r0 = r5
            r1 = r6
            int r2 = android.support.v7.appcompat.R.styleable.ActionBar_background
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r2)
            r0.mBackground = r1
            r0 = r5
            r1 = r6
            int r2 = android.support.v7.appcompat.R.styleable.ActionBar_backgroundStacked
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r2)
            r0.mStackedBackground = r1
            r0 = r5
            r1 = r6
            int r2 = android.support.v7.appcompat.R.styleable.ActionBar_height
            r3 = -1
            int r1 = r1.getDimensionPixelSize(r2, r3)
            r0.mHeight = r1
            r0 = r5
            int r0 = r0.getId()
            int r1 = android.support.v7.appcompat.R.id.split_action_bar
            if (r0 != r1) goto L6d
            r0 = r5
            r1 = 1
            r0.mIsSplit = r1
            r0 = r5
            r1 = r6
            int r2 = android.support.v7.appcompat.R.styleable.ActionBar_backgroundSplit
            android.graphics.drawable.Drawable r1 = r1.getDrawable(r2)
            r0.mSplitBackground = r1
        L6d:
            r0 = r6
            r0.recycle()
            r0 = r5
            boolean r0 = r0.mIsSplit
            r9 = r0
            r0 = 0
            r10 = r0
            r0 = r9
            if (r0 == 0) goto L90
            r0 = r10
            r9 = r0
            r0 = r5
            android.graphics.drawable.Drawable r0 = r0.mSplitBackground
            if (r0 != 0) goto La9
        L8a:
            r0 = 1
            r9 = r0
            goto La9
        L90:
            r0 = r10
            r9 = r0
            r0 = r5
            android.graphics.drawable.Drawable r0 = r0.mBackground
            if (r0 != 0) goto La9
            r0 = r10
            r9 = r0
            r0 = r5
            android.graphics.drawable.Drawable r0 = r0.mStackedBackground
            if (r0 != 0) goto La9
            goto L8a
        La9:
            r0 = r5
            r1 = r9
            r0.setWillNotDraw(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContainer.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private int getMeasuredHeightWithMargins(View view) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        return view.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private boolean isCollapsed(View view) {
        return view == null || view.getVisibility() == 8 || view.getMeasuredHeight() == 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackground != null && this.mBackground.isStateful()) {
            this.mBackground.setState(getDrawableState());
        }
        if (this.mStackedBackground != null && this.mStackedBackground.isStateful()) {
            this.mStackedBackground.setState(getDrawableState());
        }
        if (this.mSplitBackground == null || !this.mSplitBackground.isStateful()) {
            return;
        }
        this.mSplitBackground.setState(getDrawableState());
    }

    public View getTabContainer() {
        return this.mTabContainer;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void jumpDrawablesToCurrentState() {
        if (Build.VERSION.SDK_INT >= 11) {
            super.jumpDrawablesToCurrentState();
            if (this.mBackground != null) {
                this.mBackground.jumpToCurrentState();
            }
            if (this.mStackedBackground != null) {
                this.mStackedBackground.jumpToCurrentState();
            }
            if (this.mSplitBackground != null) {
                this.mSplitBackground.jumpToCurrentState();
            }
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mActionBarView = findViewById(R.id.action_bar);
        this.mContextView = findViewById(R.id.action_context_bar);
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mIsTransitioning || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean z2;
        super.onLayout(z, i, i2, i3, i4);
        View view = this.mTabContainer;
        boolean z3 = (view == null || view.getVisibility() == 8) ? false : true;
        if (view != null && view.getVisibility() != 8) {
            int measuredHeight = getMeasuredHeight();
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
            view.layout(i, (measuredHeight - view.getMeasuredHeight()) - layoutParams.bottomMargin, i3, measuredHeight - layoutParams.bottomMargin);
        }
        if (!this.mIsSplit) {
            z2 = false;
            if (this.mBackground != null) {
                if (this.mActionBarView.getVisibility() == 0) {
                    this.mBackground.setBounds(this.mActionBarView.getLeft(), this.mActionBarView.getTop(), this.mActionBarView.getRight(), this.mActionBarView.getBottom());
                } else if (this.mContextView == null || this.mContextView.getVisibility() != 0) {
                    this.mBackground.setBounds(0, 0, 0, 0);
                } else {
                    this.mBackground.setBounds(this.mContextView.getLeft(), this.mContextView.getTop(), this.mContextView.getRight(), this.mContextView.getBottom());
                }
                z2 = true;
            }
            this.mIsStacked = z3;
            if (z3 && this.mStackedBackground != null) {
                this.mStackedBackground.setBounds(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
                z2 = true;
            }
        } else if (this.mSplitBackground != null) {
            this.mSplitBackground.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        int i3 = i2;
        if (this.mActionBarView == null) {
            i3 = i2;
            if (View.MeasureSpec.getMode(i2) == Integer.MIN_VALUE) {
                i3 = i2;
                if (this.mHeight >= 0) {
                    i3 = View.MeasureSpec.makeMeasureSpec(Math.min(this.mHeight, View.MeasureSpec.getSize(i2)), Integer.MIN_VALUE);
                }
            }
        }
        super.onMeasure(i, i3);
        if (this.mActionBarView == null) {
            return;
        }
        int mode = View.MeasureSpec.getMode(i3);
        if (this.mTabContainer == null || this.mTabContainer.getVisibility() == 8 || mode == 1073741824) {
            return;
        }
        setMeasuredDimension(getMeasuredWidth(), Math.min((!isCollapsed(this.mActionBarView) ? getMeasuredHeightWithMargins(this.mActionBarView) : !isCollapsed(this.mContextView) ? getMeasuredHeightWithMargins(this.mContextView) : 0) + getMeasuredHeightWithMargins(this.mTabContainer), mode == Integer.MIN_VALUE ? View.MeasureSpec.getSize(i3) : Integer.MAX_VALUE));
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0060, code lost:
        if (r6.mSplitBackground == null) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0063, code lost:
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0077, code lost:
        if (r6.mStackedBackground == null) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setPrimaryBackground(android.graphics.drawable.Drawable r7) {
        /*
            r6 = this;
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mBackground
            if (r0 == 0) goto L17
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mBackground
            r1 = 0
            r0.setCallback(r1)
            r0 = r6
            r1 = r6
            android.graphics.drawable.Drawable r1 = r1.mBackground
            r0.unscheduleDrawable(r1)
        L17:
            r0 = r6
            r1 = r7
            r0.mBackground = r1
            r0 = r7
            if (r0 == 0) goto L4f
            r0 = r7
            r1 = r6
            r0.setCallback(r1)
            r0 = r6
            android.view.View r0 = r0.mActionBarView
            if (r0 == 0) goto L4f
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mBackground
            r1 = r6
            android.view.View r1 = r1.mActionBarView
            int r1 = r1.getLeft()
            r2 = r6
            android.view.View r2 = r2.mActionBarView
            int r2 = r2.getTop()
            r3 = r6
            android.view.View r3 = r3.mActionBarView
            int r3 = r3.getRight()
            r4 = r6
            android.view.View r4 = r4.mActionBarView
            int r4 = r4.getBottom()
            r0.setBounds(r1, r2, r3, r4)
        L4f:
            r0 = r6
            boolean r0 = r0.mIsSplit
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r8
            if (r0 == 0) goto L68
            r0 = r9
            r8 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mSplitBackground
            if (r0 != 0) goto L7d
        L63:
            r0 = 1
            r8 = r0
            goto L7d
        L68:
            r0 = r9
            r8 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mBackground
            if (r0 != 0) goto L7d
            r0 = r9
            r8 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mStackedBackground
            if (r0 != 0) goto L7d
            goto L63
        L7d:
            r0 = r6
            r1 = r8
            r0.setWillNotDraw(r1)
            r0 = r6
            r0.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContainer.setPrimaryBackground(android.graphics.drawable.Drawable):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0053, code lost:
        if (r6.mSplitBackground == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0056, code lost:
        r9 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x006a, code lost:
        if (r6.mStackedBackground == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setSplitBackground(android.graphics.drawable.Drawable r7) {
        /*
            r6 = this;
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mSplitBackground
            if (r0 == 0) goto L17
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mSplitBackground
            r1 = 0
            r0.setCallback(r1)
            r0 = r6
            r1 = r6
            android.graphics.drawable.Drawable r1 = r1.mSplitBackground
            r0.unscheduleDrawable(r1)
        L17:
            r0 = r6
            r1 = r7
            r0.mSplitBackground = r1
            r0 = 0
            r8 = r0
            r0 = r7
            if (r0 == 0) goto L46
            r0 = r7
            r1 = r6
            r0.setCallback(r1)
            r0 = r6
            boolean r0 = r0.mIsSplit
            if (r0 == 0) goto L46
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mSplitBackground
            if (r0 == 0) goto L46
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mSplitBackground
            r1 = 0
            r2 = 0
            r3 = r6
            int r3 = r3.getMeasuredWidth()
            r4 = r6
            int r4 = r4.getMeasuredHeight()
            r0.setBounds(r1, r2, r3, r4)
        L46:
            r0 = r6
            boolean r0 = r0.mIsSplit
            if (r0 == 0) goto L5b
            r0 = r8
            r9 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mSplitBackground
            if (r0 != 0) goto L70
        L56:
            r0 = 1
            r9 = r0
            goto L70
        L5b:
            r0 = r8
            r9 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mBackground
            if (r0 != 0) goto L70
            r0 = r8
            r9 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mStackedBackground
            if (r0 != 0) goto L70
            goto L56
        L70:
            r0 = r6
            r1 = r9
            r0.setWillNotDraw(r1)
            r0 = r6
            r0.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContainer.setSplitBackground(android.graphics.drawable.Drawable):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0067, code lost:
        if (r6.mSplitBackground == null) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
        r8 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007e, code lost:
        if (r6.mStackedBackground == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setStackedBackground(android.graphics.drawable.Drawable r7) {
        /*
            r6 = this;
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mStackedBackground
            if (r0 == 0) goto L17
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mStackedBackground
            r1 = 0
            r0.setCallback(r1)
            r0 = r6
            r1 = r6
            android.graphics.drawable.Drawable r1 = r1.mStackedBackground
            r0.unscheduleDrawable(r1)
        L17:
            r0 = r6
            r1 = r7
            r0.mStackedBackground = r1
            r0 = r7
            if (r0 == 0) goto L56
            r0 = r7
            r1 = r6
            r0.setCallback(r1)
            r0 = r6
            boolean r0 = r0.mIsStacked
            if (r0 == 0) goto L56
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mStackedBackground
            if (r0 == 0) goto L56
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mStackedBackground
            r1 = r6
            android.view.View r1 = r1.mTabContainer
            int r1 = r1.getLeft()
            r2 = r6
            android.view.View r2 = r2.mTabContainer
            int r2 = r2.getTop()
            r3 = r6
            android.view.View r3 = r3.mTabContainer
            int r3 = r3.getRight()
            r4 = r6
            android.view.View r4 = r4.mTabContainer
            int r4 = r4.getBottom()
            r0.setBounds(r1, r2, r3, r4)
        L56:
            r0 = r6
            boolean r0 = r0.mIsSplit
            r8 = r0
            r0 = 0
            r9 = r0
            r0 = r8
            if (r0 == 0) goto L6f
            r0 = r9
            r8 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mSplitBackground
            if (r0 != 0) goto L84
        L6a:
            r0 = 1
            r8 = r0
            goto L84
        L6f:
            r0 = r9
            r8 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mBackground
            if (r0 != 0) goto L84
            r0 = r9
            r8 = r0
            r0 = r6
            android.graphics.drawable.Drawable r0 = r0.mStackedBackground
            if (r0 != 0) goto L84
            goto L6a
        L84:
            r0 = r6
            r1 = r8
            r0.setWillNotDraw(r1)
            r0 = r6
            r0.invalidate()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.ActionBarContainer.setStackedBackground(android.graphics.drawable.Drawable):void");
    }

    public void setTabContainer(ScrollingTabContainerView scrollingTabContainerView) {
        if (this.mTabContainer != null) {
            removeView(this.mTabContainer);
        }
        this.mTabContainer = scrollingTabContainerView;
        if (scrollingTabContainerView != null) {
            addView(scrollingTabContainerView);
            ViewGroup.LayoutParams layoutParams = scrollingTabContainerView.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            scrollingTabContainerView.setAllowCollapse(false);
        }
    }

    public void setTransitioning(boolean z) {
        this.mIsTransitioning = z;
        setDescendantFocusability(z ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        super.setVisibility(i);
        boolean z = i == 0;
        if (this.mBackground != null) {
            this.mBackground.setVisible(z, false);
        }
        if (this.mStackedBackground != null) {
            this.mStackedBackground.setVisible(z, false);
        }
        if (this.mSplitBackground != null) {
            this.mSplitBackground.setVisible(z, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i) {
        if (i != 0) {
            return super.startActionModeForChild(view, callback, i);
        }
        return null;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return (drawable == this.mBackground && !this.mIsSplit) || (drawable == this.mStackedBackground && this.mIsStacked) || ((drawable == this.mSplitBackground && this.mIsSplit) || super.verifyDrawable(drawable));
    }
}
