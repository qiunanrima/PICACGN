package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.PicaAppFragmentPagerAdapter;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/PicaAppContainerFragment.class */
public class PicaAppContainerFragment extends BaseFragment {
    public static final String TAG = "PicaAppContainerFragment";
    PicaAppFragmentPagerAdapter qu;
    @BindView(2131296830)
    TabLayout tabLayout;
    @BindView(2131297037)
    Toolbar toolbar;
    @BindView(2131297055)
    ViewPager viewPager_tags;

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, 2131689981, true);
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            return;
        }
        ((MainActivity) getActivity()).t(8);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        if (this.qu == null) {
            this.qu = new PicaAppFragmentPagerAdapter(getChildFragmentManager());
        }
        if (this.viewPager_tags != null && this.viewPager_tags.getAdapter() == null) {
            this.viewPager_tags.setAdapter(this.qu);
        }
        this.tabLayout.setupWithViewPager(this.viewPager_tags);
        TabLayout.Tab tabAt = this.tabLayout.getTabAt(0);
        this.tabLayout.getTabAt(0).setText(2131689971);
        tabAt.setText(2131689971);
        TabLayout.Tab tabAt2 = this.tabLayout.getTabAt(1);
        this.tabLayout.getTabAt(1).setText(2131689981);
        tabAt2.setText(2131689981);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427416, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
