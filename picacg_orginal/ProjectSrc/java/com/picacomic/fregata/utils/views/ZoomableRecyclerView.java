package com.picacomic.fregata.utils.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/ZoomableRecyclerView.class */
public class ZoomableRecyclerView extends RecyclerView {
    private static final int INVALID_POINTER_ID = -1;
    private static final String TAG = "ZoomableRecyclerView";
    static float gx;
    static float gy;
    private float centerX;
    private float centerY;
    private float defaultScaleFactor;
    private boolean isPagerEnabled;
    private boolean isVertical;
    private int mActivePointerId;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor;
    Matrix matrix;
    private int screenWidth;
    private float totalMovementX;
    private float totalMovementY;

    /* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/ZoomableRecyclerView$ScaleListener.class */
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            ZoomableRecyclerView.this.mScaleFactor *= scaleGestureDetector.getScaleFactor();
            ZoomableRecyclerView.this.centerX = scaleGestureDetector.getFocusX();
            ZoomableRecyclerView.this.centerY = scaleGestureDetector.getFocusY();
            ZoomableRecyclerView.this.mScaleFactor = Math.max(ZoomableRecyclerView.this.defaultScaleFactor, Math.min(ZoomableRecyclerView.this.mScaleFactor, 3.0f));
            ZoomableRecyclerView.this.invalidate();
            return true;
        }
    }

    public ZoomableRecyclerView(Context context) {
        super(context);
        this.mScaleFactor = 1.0f;
        this.mActivePointerId = -1;
        this.screenWidth = 1080;
        this.defaultScaleFactor = 1.0f;
        this.matrix = new Matrix();
        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    public ZoomableRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScaleFactor = 1.0f;
        this.mActivePointerId = -1;
        this.screenWidth = 1080;
        this.defaultScaleFactor = 1.0f;
        this.matrix = new Matrix();
        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    public ZoomableRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mScaleFactor = 1.0f;
        this.mActivePointerId = -1;
        this.screenWidth = 1080;
        this.defaultScaleFactor = 1.0f;
        this.matrix = new Matrix();
        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.save(1);
        if (this.mScaleFactor <= this.defaultScaleFactor) {
            this.isPagerEnabled = true;
        } else {
            this.isPagerEnabled = false;
        }
        if (this.mScaleFactor == this.defaultScaleFactor) {
            this.mPosX = 0.0f;
            this.mPosY = 0.0f;
        }
        canvas.translate(this.mPosX, this.mPosY);
        canvas.scale(this.mScaleFactor, this.mScaleFactor);
        super.dispatchDraw(canvas);
        canvas.restore();
        invalidate();
    }

    @Override // android.support.v7.widget.RecyclerView
    public boolean fling(int i, int i2) {
        if (!this.isPagerEnabled || this.isVertical) {
            return super.fling(i, i2);
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) getLayoutManager();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        View findViewByPosition = linearLayoutManager.findViewByPosition(linearLayoutManager.findFirstVisibleItemPosition());
        View findViewByPosition2 = linearLayoutManager.findViewByPosition(findLastVisibleItemPosition);
        if (findViewByPosition2 == null || findViewByPosition == null) {
            return super.fling(i, i2);
        }
        int width = (this.screenWidth - findViewByPosition2.getWidth()) / 2;
        int width2 = (this.screenWidth - findViewByPosition.getWidth()) / 2;
        int width3 = findViewByPosition.getWidth();
        int left = findViewByPosition2.getLeft();
        int right = findViewByPosition.getRight();
        if (i > 0) {
            smoothScrollBy(left - width, 0);
            return true;
        }
        smoothScrollBy(-((width2 + width3) - right), 0);
        return true;
    }

    public int getScreenWidth() {
        return this.screenWidth;
    }

    public boolean isVertical() {
        return this.isVertical;
    }

    @Override // android.support.v7.widget.RecyclerView, android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save(1);
        canvas.translate(this.mPosX, this.mPosY);
        canvas.scale(this.mScaleFactor, this.mScaleFactor, this.centerX, this.centerY);
        canvas.restore();
    }

    @Override // android.support.v7.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        this.mScaleDetector.onTouchEvent(motionEvent);
        int i = action & 255;
        int i2 = 0;
        if (i == 6) {
            int i3 = (action & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
            if (motionEvent.getPointerId(i3) == this.mActivePointerId) {
                if (i3 == 0) {
                    i2 = 1;
                }
                this.mLastTouchX = motionEvent.getX(i2);
                this.mLastTouchY = motionEvent.getY(i2);
                this.mActivePointerId = motionEvent.getPointerId(i2);
                return true;
            }
            return true;
        }
        switch (i) {
            case 0:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.mLastTouchX = x;
                this.mLastTouchY = y;
                this.mActivePointerId = motionEvent.getPointerId(0);
                return true;
            case 1:
                this.mActivePointerId = -1;
                return true;
            case 2:
                int findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                if (findPointerIndex == -1) {
                    return true;
                }
                float x2 = motionEvent.getX(findPointerIndex);
                float y2 = motionEvent.getY(findPointerIndex);
                float f = this.mLastTouchX;
                float f2 = this.mLastTouchY;
                if (this.isVertical) {
                    this.mPosX += (x2 - f) * this.mScaleFactor;
                    if (this.mPosX > 0.0f) {
                        this.mPosX = 0.0f;
                    }
                    if ((-this.mPosX) / (this.mScaleFactor - 1.0f) > getWidth()) {
                        this.mPosX = (-getWidth()) * (this.mScaleFactor - 1.0f);
                    }
                } else {
                    this.mPosY += (y2 - f2) * this.mScaleFactor;
                    if (this.mPosY > 0.0f) {
                        this.mPosY = 0.0f;
                    }
                    if ((-this.mPosY) / (this.mScaleFactor - 1.0f) > getHeight()) {
                        this.mPosY = (-getHeight()) * (this.mScaleFactor - 1.0f);
                    }
                }
                this.mLastTouchX = x2;
                this.mLastTouchY = y2;
                invalidate();
                return true;
            case 3:
                this.mActivePointerId = -1;
                return true;
            default:
                return true;
        }
    }

    public void setScreenWidth(int i) {
        this.screenWidth = i;
    }

    public void setVertical(boolean z) {
        this.isVertical = z;
        this.defaultScaleFactor = 1.0f;
        this.mScaleFactor = 1.0f;
    }
}
