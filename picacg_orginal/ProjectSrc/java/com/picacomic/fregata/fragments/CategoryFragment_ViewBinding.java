package com.picacomic.fregata.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/CategoryFragment_ViewBinding.class */
public class CategoryFragment_ViewBinding implements Unbinder {
    private CategoryFragment kJ;

    @UiThread
    public CategoryFragment_ViewBinding(CategoryFragment categoryFragment, View view) {
        this.kJ = categoryFragment;
        categoryFragment.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(view, 2131296430, "field 'coordinatorLayout'", CoordinatorLayout.class);
        categoryFragment.linearLayout_keywords = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296644, "field 'linearLayout_keywords'", LinearLayout.class);
        categoryFragment.linearLayout_tags = (LinearLayout) Utils.findRequiredViewAsType(view, 2131296645, "field 'linearLayout_tags'", LinearLayout.class);
        categoryFragment.scrollView = (NestedScrollView) Utils.findRequiredViewAsType(view, 2131296790, "field 'scrollView'", NestedScrollView.class);
        categoryFragment.recyclerView_category = (RecyclerView) Utils.findRequiredViewAsType(view, 2131296754, "field 'recyclerView_category'", RecyclerView.class);
        categoryFragment.toolbar = (Toolbar) Utils.findRequiredViewAsType(view, 2131297037, "field 'toolbar'", Toolbar.class);
        categoryFragment.searchView = (SearchView) Utils.findRequiredViewAsType(view, 2131296793, "field 'searchView'", SearchView.class);
    }

    @Override // butterknife.Unbinder
    @CallSuper
    public void unbind() {
        CategoryFragment categoryFragment = this.kJ;
        if (categoryFragment == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.kJ = null;
        categoryFragment.coordinatorLayout = null;
        categoryFragment.linearLayout_keywords = null;
        categoryFragment.linearLayout_tags = null;
        categoryFragment.scrollView = null;
        categoryFragment.recyclerView_category = null;
        categoryFragment.toolbar = null;
        categoryFragment.searchView = null;
    }
}
