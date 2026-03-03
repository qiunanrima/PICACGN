package com.picacomic.fregata.utils.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/SnapRecyclerView.class */
public class SnapRecyclerView extends RecyclerView {
    int screenWidth;

    public SnapRecyclerView(Context context) {
        super(context);
        setScreenWidth(context);
    }

    public SnapRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        setScreenWidth(context);
    }

    public SnapRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setScreenWidth(context);
    }

    private void setScreenWidth(Context context) {
        this.screenWidth = ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    @Override // android.support.v7.widget.RecyclerView
    public boolean fling(int i, int i2) {
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
}
