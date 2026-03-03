package android.support.v7.widget.helper;

import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v7.recyclerview.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
/* loaded from: picacg_1.jar:android/support/v7/widget/helper/ItemTouchUIUtilImpl.class */
class ItemTouchUIUtilImpl {

    /* loaded from: picacg_1.jar:android/support/v7/widget/helper/ItemTouchUIUtilImpl$Api21Impl.class */
    static class Api21Impl extends BaseImpl {
        private float findMaxElevation(RecyclerView recyclerView, View view) {
            float f;
            int childCount = recyclerView.getChildCount();
            float f2 = 0.0f;
            int i = 0;
            while (i < childCount) {
                View childAt = recyclerView.getChildAt(i);
                if (childAt == view) {
                    f = f2;
                } else {
                    float elevation = ViewCompat.getElevation(childAt);
                    f = f2;
                    if (elevation > f2) {
                        f = elevation;
                    }
                }
                i++;
                f2 = f;
            }
            return f2;
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtilImpl.BaseImpl, android.support.v7.widget.helper.ItemTouchUIUtil
        public void clearView(View view) {
            Object tag = view.getTag(R.id.item_touch_helper_previous_elevation);
            if (tag != null && (tag instanceof Float)) {
                ViewCompat.setElevation(view, ((Float) tag).floatValue());
            }
            view.setTag(R.id.item_touch_helper_previous_elevation, null);
            super.clearView(view);
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtilImpl.BaseImpl, android.support.v7.widget.helper.ItemTouchUIUtil
        public void onDraw(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
            if (z && view.getTag(R.id.item_touch_helper_previous_elevation) == null) {
                float elevation = ViewCompat.getElevation(view);
                ViewCompat.setElevation(view, findMaxElevation(recyclerView, view) + 1.0f);
                view.setTag(R.id.item_touch_helper_previous_elevation, Float.valueOf(elevation));
            }
            super.onDraw(canvas, recyclerView, view, f, f2, i, z);
        }
    }

    /* loaded from: picacg_1.jar:android/support/v7/widget/helper/ItemTouchUIUtilImpl$BaseImpl.class */
    static class BaseImpl implements ItemTouchUIUtil {
        @Override // android.support.v7.widget.helper.ItemTouchUIUtil
        public void clearView(View view) {
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtil
        public void onDraw(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
            view.setTranslationX(f);
            view.setTranslationY(f2);
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtil
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, View view, float f, float f2, int i, boolean z) {
        }

        @Override // android.support.v7.widget.helper.ItemTouchUIUtil
        public void onSelected(View view) {
        }
    }

    ItemTouchUIUtilImpl() {
    }
}
