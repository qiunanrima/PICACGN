package android.support.v7.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: picacg_1.jar:android/support/v7/widget/AlertDialogLayout.class */
public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(@Nullable Context context) {
        super(context);
    }

    public AlertDialogLayout(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) childAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = childAt.getMeasuredHeight();
                    measureChildWithMargins(childAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    private static int resolveMinimumHeight(View view) {
        int minimumHeight = ViewCompat.getMinimumHeight(view);
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return resolveMinimumHeight(viewGroup.getChildAt(0));
            }
            return 0;
        }
        return 0;
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    private boolean tryOnMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int childCount = getChildCount();
        View view = null;
        View view2 = null;
        View view3 = null;
        for (int i8 = 0; i8 < childCount; i8++) {
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                int id = childAt.getId();
                if (id == R.id.topPanel) {
                    view = childAt;
                } else if (id == R.id.buttonPanel) {
                    view3 = childAt;
                } else if ((id != R.id.contentPanel && id != R.id.customPanel) || view2 != null) {
                    return false;
                } else {
                    view2 = childAt;
                }
            }
        }
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (view != null) {
            view.measure(i, 0);
            paddingTop += view.getMeasuredHeight();
            i3 = View.combineMeasuredStates(0, view.getMeasuredState());
        } else {
            i3 = 0;
        }
        if (view3 != null) {
            view3.measure(i, 0);
            i4 = resolveMinimumHeight(view3);
            i5 = view3.getMeasuredHeight() - i4;
            paddingTop += i4;
            i3 = View.combineMeasuredStates(i3, view3.getMeasuredState());
        } else {
            i4 = 0;
            i5 = 0;
        }
        if (view2 != null) {
            view2.measure(i, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingTop), mode));
            i6 = view2.getMeasuredHeight();
            paddingTop += i6;
            i3 = View.combineMeasuredStates(i3, view2.getMeasuredState());
        } else {
            i6 = 0;
        }
        int i9 = size - paddingTop;
        int i10 = i3;
        int i11 = i9;
        int i12 = paddingTop;
        if (view3 != null) {
            int min = Math.min(i9, i5);
            int i13 = i9;
            int i14 = i4;
            if (min > 0) {
                i13 = i9 - min;
                i14 = i4 + min;
            }
            view3.measure(i, View.MeasureSpec.makeMeasureSpec(i14, 1073741824));
            i12 = (paddingTop - i4) + view3.getMeasuredHeight();
            i11 = i13;
            i10 = View.combineMeasuredStates(i3, view3.getMeasuredState());
        }
        int i15 = i10;
        int i16 = i12;
        if (view2 != null) {
            i15 = i10;
            i16 = i12;
            if (i11 > 0) {
                view2.measure(i, View.MeasureSpec.makeMeasureSpec(i6 + i11, mode));
                i16 = (i12 - i6) + view2.getMeasuredHeight();
                i15 = View.combineMeasuredStates(i10, view2.getMeasuredState());
            }
        }
        int i17 = 0;
        int i18 = 0;
        while (true) {
            i7 = i18;
            if (i17 >= childCount) {
                break;
            }
            View childAt2 = getChildAt(i17);
            int i19 = i7;
            if (childAt2.getVisibility() != 8) {
                i19 = Math.max(i7, childAt2.getMeasuredWidth());
            }
            i17++;
            i18 = i19;
        }
        setMeasuredDimension(View.resolveSizeAndState(i7 + getPaddingLeft() + getPaddingRight(), i, i15), View.resolveSizeAndState(i16, i2, 0));
        if (mode2 != 1073741824) {
            forceUniformWidth(childCount, i2);
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int i5 = i3 - i;
        int paddingRight = getPaddingRight();
        int paddingRight2 = getPaddingRight();
        int measuredHeight = getMeasuredHeight();
        int childCount = getChildCount();
        int gravity = getGravity();
        int i6 = gravity & 112;
        int paddingTop = i6 != 16 ? i6 != 80 ? getPaddingTop() : ((getPaddingTop() + i4) - i2) - measuredHeight : (((i4 - i2) - measuredHeight) / 2) + getPaddingTop();
        Drawable dividerDrawable = getDividerDrawable();
        int intrinsicHeight = dividerDrawable == null ? 0 : dividerDrawable.getIntrinsicHeight();
        int i7 = 0;
        while (i7 < childCount) {
            View childAt = getChildAt(i7);
            int i8 = paddingTop;
            if (childAt != null) {
                i8 = paddingTop;
                if (childAt.getVisibility() != 8) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    int measuredHeight2 = childAt.getMeasuredHeight();
                    LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) childAt.getLayoutParams();
                    int i9 = layoutParams.gravity;
                    int i10 = i9;
                    if (i9 < 0) {
                        i10 = gravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
                    }
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(i10, ViewCompat.getLayoutDirection(this)) & 7;
                    int i11 = absoluteGravity != 1 ? absoluteGravity != 5 ? layoutParams.leftMargin + paddingLeft : ((i5 - paddingRight) - measuredWidth) - layoutParams.rightMargin : ((((((i5 - paddingLeft) - paddingRight2) - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                    int i12 = paddingTop;
                    if (hasDividerBeforeChildAt(i7)) {
                        i12 = paddingTop + intrinsicHeight;
                    }
                    int i13 = i12 + layoutParams.topMargin;
                    setChildFrame(childAt, i11, i13, measuredWidth, measuredHeight2);
                    i8 = i13 + measuredHeight2 + layoutParams.bottomMargin;
                }
            }
            i7++;
            paddingTop = i8;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.LinearLayoutCompat, android.view.View
    public void onMeasure(int i, int i2) {
        if (tryOnMeasure(i, i2)) {
            return;
        }
        super.onMeasure(i, i2);
    }
}
