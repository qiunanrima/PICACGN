package com.picacomic.fregata.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.picacomic.fregata.fragments.ChatroomListFragment;
import com.picacomic.fregata.fragments.PicaAppListFragment;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/PicaAppFragmentPagerAdapter.class */
public class PicaAppFragmentPagerAdapter extends FragmentPagerAdapter {
    public PicaAppFragmentPagerAdapter(FragmentManager fragmentManager) {
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
                return new ChatroomListFragment();
            case 1:
                return new PicaAppListFragment();
            default:
                return null;
        }
    }
}
