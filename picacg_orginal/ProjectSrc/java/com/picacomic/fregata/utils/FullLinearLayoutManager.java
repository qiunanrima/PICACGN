package com.picacomic.fregata.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/FullLinearLayoutManager.class */
public class FullLinearLayoutManager extends LinearLayoutManager {
    public static final String TAG = "FullLinearLayoutManager";
    private int[] un;

    public FullLinearLayoutManager(Context context) {
        super(context);
        this.un = new int[2];
    }

    public FullLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
        this.un = new int[2];
    }

    public FullLinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.un = new int[2];
    }

    private void a(RecyclerView.Recycler recycler, int i, int i2, int i3, int[] iArr) {
        try {
            View viewForPosition = recycler.getViewForPosition(0);
            if (viewForPosition != null) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewForPosition.getLayoutParams();
                viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                iArr[0] = viewForPosition.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                iArr[1] = viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
                recycler.recycleView(viewForPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        String str = TAG;
        Log.i(str, "onMeasure called. \nwidthMode " + mode + " \nheightMode " + i2 + " \nwidthSize " + size + " \nheightSize " + size2 + " \ngetItemCount() " + getItemCount());
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < getItemCount(); i5++) {
            a(recycler, i5, View.MeasureSpec.makeMeasureSpec(i5, 0), View.MeasureSpec.makeMeasureSpec(i5, 0), this.un);
            if (getOrientation() == 0) {
                int i6 = i3 + this.un[0];
                i3 = i6;
                if (i5 == 0) {
                    i4 = this.un[1];
                    i3 = i6;
                }
            } else {
                int i7 = i4 + this.un[1];
                i4 = i7;
                if (i5 == 0) {
                    i3 = this.un[0];
                    i4 = i7;
                }
            }
        }
        int i8 = size;
        if (mode != 1073741824) {
            i8 = i3;
        }
        int i9 = size2;
        if (mode2 != 1073741824) {
            i9 = i4;
        }
        setMeasuredDimension(i8, i9);
    }
}
