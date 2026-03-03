package android.support.design.widget;

import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
/* loaded from: picacg_1.jar:android/support/design/widget/SwipeDismissBehavior.class */
public class SwipeDismissBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    private boolean mInterceptingEvents;
    OnDismissListener mListener;
    private boolean mSensitivitySet;
    ViewDragHelper mViewDragHelper;
    private float mSensitivity = 0.0f;
    int mSwipeDirection = 2;
    float mDragDismissThreshold = 0.5f;
    float mAlphaStartSwipeDistance = 0.0f;
    float mAlphaEndSwipeDistance = 0.5f;
    private final ViewDragHelper.Callback mDragCallback = new ViewDragHelper.Callback() { // from class: android.support.design.widget.SwipeDismissBehavior.1
        private static final int INVALID_POINTER_ID = -1;
        private int mActivePointerId = -1;
        private int mOriginalCapturedViewLeft;

        /* JADX WARN: Code restructure failed: missing block: B:18:0x0041, code lost:
            r8 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0069, code lost:
            if (r5 > 0.0f) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x006c, code lost:
            r8 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0078, code lost:
            if (r5 < 0.0f) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0080, code lost:
            return r8;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private boolean shouldDismiss(android.view.View r4, float r5) {
            /*
                r3 = this;
                r0 = 0
                r6 = r0
                r0 = 0
                r7 = r0
                r0 = 0
                r8 = r0
                r0 = r5
                r1 = 0
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 == 0) goto L83
                r0 = r4
                int r0 = android.support.v4.view.ViewCompat.getLayoutDirection(r0)
                r1 = 1
                if (r0 != r1) goto L1c
                r0 = 1
                r9 = r0
                goto L1f
            L1c:
                r0 = 0
                r9 = r0
            L1f:
                r0 = r3
                android.support.design.widget.SwipeDismissBehavior r0 = android.support.design.widget.SwipeDismissBehavior.this
                int r0 = r0.mSwipeDirection
                r1 = 2
                if (r0 != r1) goto L2c
                r0 = 1
                return r0
            L2c:
                r0 = r3
                android.support.design.widget.SwipeDismissBehavior r0 = android.support.design.widget.SwipeDismissBehavior.this
                int r0 = r0.mSwipeDirection
                if (r0 != 0) goto L53
                r0 = r9
                if (r0 == 0) goto L47
                r0 = r5
                r1 = 0
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 >= 0) goto L50
            L41:
                r0 = 1
                r8 = r0
                goto L50
            L47:
                r0 = r5
                r1 = 0
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 <= 0) goto L50
                goto L41
            L50:
                r0 = r8
                return r0
            L53:
                r0 = r3
                android.support.design.widget.SwipeDismissBehavior r0 = android.support.design.widget.SwipeDismissBehavior.this
                int r0 = r0.mSwipeDirection
                r1 = 1
                if (r0 != r1) goto L81
                r0 = r9
                if (r0 == 0) goto L72
                r0 = r6
                r8 = r0
                r0 = r5
                r1 = 0
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 <= 0) goto L7e
            L6c:
                r0 = 1
                r8 = r0
                goto L7e
            L72:
                r0 = r6
                r8 = r0
                r0 = r5
                r1 = 0
                int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
                if (r0 >= 0) goto L7e
                goto L6c
            L7e:
                r0 = r8
                return r0
            L81:
                r0 = 0
                return r0
            L83:
                r0 = r4
                int r0 = r0.getLeft()
                r9 = r0
                r0 = r3
                int r0 = r0.mOriginalCapturedViewLeft
                r10 = r0
                r0 = r4
                int r0 = r0.getWidth()
                float r0 = (float) r0
                r1 = r3
                android.support.design.widget.SwipeDismissBehavior r1 = android.support.design.widget.SwipeDismissBehavior.this
                float r1 = r1.mDragDismissThreshold
                float r0 = r0 * r1
                int r0 = java.lang.Math.round(r0)
                r11 = r0
                r0 = r7
                r8 = r0
                r0 = r9
                r1 = r10
                int r0 = r0 - r1
                int r0 = java.lang.Math.abs(r0)
                r1 = r11
                if (r0 < r1) goto Lb5
                r0 = 1
                r8 = r0
            Lb5:
                r0 = r8
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.SwipeDismissBehavior.AnonymousClass1.shouldDismiss(android.view.View, float):boolean");
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int clampViewPositionHorizontal(View view, int i, int i2) {
            int width;
            int width2;
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            if (SwipeDismissBehavior.this.mSwipeDirection == 0) {
                if (z) {
                    width = this.mOriginalCapturedViewLeft - view.getWidth();
                    width2 = this.mOriginalCapturedViewLeft;
                } else {
                    width = this.mOriginalCapturedViewLeft;
                    width2 = view.getWidth() + this.mOriginalCapturedViewLeft;
                }
            } else if (SwipeDismissBehavior.this.mSwipeDirection != 1) {
                width = this.mOriginalCapturedViewLeft - view.getWidth();
                width2 = view.getWidth() + this.mOriginalCapturedViewLeft;
            } else if (z) {
                width = this.mOriginalCapturedViewLeft;
                width2 = view.getWidth() + this.mOriginalCapturedViewLeft;
            } else {
                width = this.mOriginalCapturedViewLeft - view.getWidth();
                width2 = this.mOriginalCapturedViewLeft;
            }
            return SwipeDismissBehavior.clamp(width, i, width2);
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int clampViewPositionVertical(View view, int i, int i2) {
            return view.getTop();
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewCaptured(View view, int i) {
            this.mActivePointerId = i;
            this.mOriginalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewDragStateChanged(int i) {
            if (SwipeDismissBehavior.this.mListener != null) {
                SwipeDismissBehavior.this.mListener.onDragStateChanged(i);
            }
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            float width = this.mOriginalCapturedViewLeft + (view.getWidth() * SwipeDismissBehavior.this.mAlphaStartSwipeDistance);
            float width2 = this.mOriginalCapturedViewLeft + (view.getWidth() * SwipeDismissBehavior.this.mAlphaEndSwipeDistance);
            float f = i;
            if (f <= width) {
                view.setAlpha(1.0f);
            } else if (f >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(width, width2, f), 1.0f));
            }
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public void onViewReleased(View view, float f, float f2) {
            int i;
            boolean z;
            this.mActivePointerId = -1;
            int width = view.getWidth();
            if (shouldDismiss(view, f)) {
                i = view.getLeft() < this.mOriginalCapturedViewLeft ? this.mOriginalCapturedViewLeft - width : this.mOriginalCapturedViewLeft + width;
                z = true;
            } else {
                i = this.mOriginalCapturedViewLeft;
                z = false;
            }
            if (SwipeDismissBehavior.this.mViewDragHelper.settleCapturedViewAt(i, view.getTop())) {
                ViewCompat.postOnAnimation(view, new SettleRunnable(view, z));
            } else if (!z || SwipeDismissBehavior.this.mListener == null) {
            } else {
                SwipeDismissBehavior.this.mListener.onDismiss(view);
            }
        }

        @Override // android.support.v4.widget.ViewDragHelper.Callback
        public boolean tryCaptureView(View view, int i) {
            return this.mActivePointerId == -1 && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }
    };

    /* loaded from: picacg_1.jar:android/support/design/widget/SwipeDismissBehavior$OnDismissListener.class */
    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i);
    }

    /* loaded from: picacg_1.jar:android/support/design/widget/SwipeDismissBehavior$SettleRunnable.class */
    private class SettleRunnable implements Runnable {
        private final boolean mDismiss;
        private final View mView;

        SettleRunnable(View view, boolean z) {
            this.mView = view;
            this.mDismiss = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SwipeDismissBehavior.this.mViewDragHelper != null && SwipeDismissBehavior.this.mViewDragHelper.continueSettling(true)) {
                ViewCompat.postOnAnimation(this.mView, this);
            } else if (!this.mDismiss || SwipeDismissBehavior.this.mListener == null) {
            } else {
                SwipeDismissBehavior.this.mListener.onDismiss(this.mView);
            }
        }
    }

    static float clamp(float f, float f2, float f3) {
        return Math.min(Math.max(f, f2), f3);
    }

    static int clamp(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        if (this.mViewDragHelper == null) {
            this.mViewDragHelper = this.mSensitivitySet ? ViewDragHelper.create(viewGroup, this.mSensitivity, this.mDragCallback) : ViewDragHelper.create(viewGroup, this.mDragCallback);
        }
    }

    static float fraction(float f, float f2, float f3) {
        return (f3 - f) / (f2 - f);
    }

    public boolean canSwipeDismissView(@NonNull View view) {
        return true;
    }

    public int getDragState() {
        return this.mViewDragHelper != null ? this.mViewDragHelper.getViewDragState() : 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0063 A[RETURN] */
    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.support.design.widget.CoordinatorLayout r7, V r8, android.view.MotionEvent r9) {
        /*
            r6 = this;
            r0 = r6
            boolean r0 = r0.mInterceptingEvents
            r10 = r0
            r0 = r9
            int r0 = r0.getActionMasked()
            r11 = r0
            r0 = r11
            r1 = 3
            if (r0 == r1) goto L4b
            r0 = r11
            switch(r0) {
                case 0: goto L2f;
                case 1: goto L4b;
                default: goto L2c;
            }
        L2c:
            goto L50
        L2f:
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            float r3 = r3.getX()
            int r3 = (int) r3
            r4 = r9
            float r4 = r4.getY()
            int r4 = (int) r4
            boolean r1 = r1.isPointInChildBounds(r2, r3, r4)
            r0.mInterceptingEvents = r1
            r0 = r6
            boolean r0 = r0.mInterceptingEvents
            r10 = r0
            goto L50
        L4b:
            r0 = r6
            r1 = 0
            r0.mInterceptingEvents = r1
        L50:
            r0 = r10
            if (r0 == 0) goto L63
            r0 = r6
            r1 = r7
            r0.ensureViewDragHelper(r1)
            r0 = r6
            android.support.v4.widget.ViewDragHelper r0 = r0.mViewDragHelper
            r1 = r9
            boolean r0 = r0.shouldInterceptTouchEvent(r1)
            return r0
        L63:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.widget.SwipeDismissBehavior.onInterceptTouchEvent(android.support.design.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    @Override // android.support.design.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v, MotionEvent motionEvent) {
        if (this.mViewDragHelper != null) {
            this.mViewDragHelper.processTouchEvent(motionEvent);
            return true;
        }
        return false;
    }

    public void setDragDismissDistance(float f) {
        this.mDragDismissThreshold = clamp(0.0f, f, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f) {
        this.mAlphaEndSwipeDistance = clamp(0.0f, f, 1.0f);
    }

    public void setListener(OnDismissListener onDismissListener) {
        this.mListener = onDismissListener;
    }

    public void setSensitivity(float f) {
        this.mSensitivity = f;
        this.mSensitivitySet = true;
    }

    public void setStartAlphaSwipeDistance(float f) {
        this.mAlphaStartSwipeDistance = clamp(0.0f, f, 1.0f);
    }

    public void setSwipeDirection(int i) {
        this.mSwipeDirection = i;
    }
}
