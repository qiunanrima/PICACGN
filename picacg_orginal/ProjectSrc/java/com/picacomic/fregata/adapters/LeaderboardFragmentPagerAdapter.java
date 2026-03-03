package com.picacomic.fregata.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.picacomic.fregata.fragments.LeaderboardKnightFragment;
import com.picacomic.fregata.fragments.LeaderboardPopularFragment;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/LeaderboardFragmentPagerAdapter.class */
public class LeaderboardFragmentPagerAdapter extends FragmentPagerAdapter {
    public LeaderboardFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return 2;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new LeaderboardPopularFragment();
            case 1:
                return new LeaderboardKnightFragment();
            default:
                return null;
        }
    }
}
