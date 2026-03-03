package com.picacomic.fregata.utils.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ListView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/ZoomableListView.class */
public class ZoomableListView extends ListView {
    private static final int INVALID_POINTER_ID = -1;
    static float gx;
    static float gy;
    private float centerX;
    private float centerY;
    private boolean isPagerEnabled;
    private int mActivePointerId;
    private float mLastTouchX;
    private float mLastTouchY;
    private float mPosX;
    private float mPosY;
    private ScaleGestureDetector mScaleDetector;
    private float mScaleFactor;
    Matrix matrix;
    private float totalMovementX;

    /* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/ZoomableListView$ScaleListener.class */
    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private ScaleListener() {
        }

        @Override // android.view.ScaleGestureDetector.SimpleOnScaleGestureListener, android.view.ScaleGestureDetector.OnScaleGestureListener
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            ZoomableListView.this.mScaleFactor *= scaleGestureDetector.getScaleFactor();
            ZoomableListView.this.centerX = scaleGestureDetector.getFocusX();
            ZoomableListView.this.centerY = scaleGestureDetector.getFocusY();
            ZoomableListView.this.mScaleFactor = Math.max(1.0f, Math.min(ZoomableListView.this.mScaleFactor, 3.0f));
            ZoomableListView.this.invalidate();
            return true;
        }
    }

    public ZoomableListView(Context context) {
        super(context);
        this.mScaleFactor = 1.0f;
        this.mActivePointerId = -1;
        this.matrix = new Matrix();
        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    public ZoomableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScaleFactor = 1.0f;
        this.mActivePointerId = -1;
        this.matrix = new Matrix();
        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleListener());
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.save(1);
        if (this.mScaleFactor == 1.0f) {
            this.isPagerEnabled = true;
        } else {
            this.isPagerEnabled = false;
        }
        if (this.mScaleFactor == 1.0f) {
            this.mPosX = 0.0f;
            this.mPosY = 0.0f;
        }
        canvas.translate(this.mPosX, this.mPosY);
        canvas.scale(this.mScaleFactor, this.mScaleFactor);
        super.dispatchDraw(canvas);
        canvas.restore();
        invalidate();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save(1);
        canvas.translate(this.mPosX, this.mPosY);
        canvas.scale(this.mScaleFactor, this.mScaleFactor);
        canvas.restore();
    }

    @Override // android.widget.AbsListView, android.view.View
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
                this.mPosX += (x2 - f) * this.mScaleFactor;
                if (this.mPosX > 0.0f) {
                    this.mPosX = 0.0f;
                }
                if ((-this.mPosX) / (this.mScaleFactor - 1.0f) > getWidth()) {
                    this.mPosX = (-getWidth()) * (this.mScaleFactor - 1.0f);
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
}
