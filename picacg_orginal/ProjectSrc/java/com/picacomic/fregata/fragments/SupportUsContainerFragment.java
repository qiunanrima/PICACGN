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
import com.picacomic.fregata.adapters.SupportUsFragmentPagerAdapter;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/SupportUsContainerFragment.class */
public class SupportUsContainerFragment extends BaseFragment {
    public static final String TAG = "SupportUsContainerFragment";
    SupportUsFragmentPagerAdapter rO;
    @BindView(2131296830)
    TabLayout tabLayout;
    @BindView(2131297037)
    Toolbar toolbar;
    @BindView(2131297057)
    ViewPager viewPager_tags;

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, 2131689986, true);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        AlertDialogCenter.showCustomAlertDialog(getActivity(), 2131230975, 2131689607, 2131689606);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.rO = new SupportUsFragmentPagerAdapter(getChildFragmentManager());
        this.viewPager_tags.setAdapter(this.rO);
        this.tabLayout.setupWithViewPager(this.viewPager_tags);
        TabLayout.Tab tabAt = this.tabLayout.getTabAt(0);
        this.tabLayout.getTabAt(0).setText(2131689958);
        tabAt.setText(2131689958);
        TabLayout.Tab tabAt2 = this.tabLayout.getTabAt(1);
        this.tabLayout.getTabAt(1).setText(2131689956);
        tabAt2.setText(2131689956);
        TabLayout.Tab tabAt3 = this.tabLayout.getTabAt(2);
        this.tabLayout.getTabAt(2).setText(2131689955);
        tabAt3.setText(2131689955);
        this.tabLayout.setTabMode(0);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427435, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
