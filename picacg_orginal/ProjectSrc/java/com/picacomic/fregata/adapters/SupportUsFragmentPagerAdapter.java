package com.picacomic.fregata.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.picacomic.fregata.fragments.SupportUsAdsGameFragment;
import com.picacomic.fregata.fragments.SupportUsOfficalGroupFragment;
import com.picacomic.fregata.fragments.SupportUsQQAlipayFragment;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/SupportUsFragmentPagerAdapter.class */
public class SupportUsFragmentPagerAdapter extends FragmentPagerAdapter {
    public SupportUsFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return 3;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new SupportUsQQAlipayFragment();
            case 1:
                return new SupportUsOfficalGroupFragment();
            case 2:
                return new SupportUsAdsGameFragment();
            default:
                return null;
        }
    }
}
