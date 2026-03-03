package com.picacomic.fregata.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.picacomic.fregata.fragments.AnonymousChatFragment;
import com.picacomic.fregata.fragments.ChatroomFragment;
import com.picacomic.fregata.fragments.PicaAppFragment;
import com.picacomic.fregata.objects.ChatroomListObject;
import com.picacomic.fregata.objects.PicaAppBaseObject;
import com.picacomic.fregata.objects.PicaAppObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/CustomPicaAppFragmentPagerAdapter.class */
public class CustomPicaAppFragmentPagerAdapter extends FragmentPagerAdapter {
    public static final String TAG = "CustomPicaAppFragmentPagerAdapter";
    ArrayList<PicaAppBaseObject> ja;

    public CustomPicaAppFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<PicaAppBaseObject> arrayList) {
        super(fragmentManager);
        this.ja = arrayList;
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        if (this.ja != null) {
            return this.ja.size();
        }
        return 0;
    }

    @Override // android.support.v4.app.FragmentPagerAdapter
    public Fragment getItem(int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return null;
        }
        if (this.ja.get(i) instanceof PicaAppObject) {
            return ((PicaAppObject) this.ja.get(i)).getTitle().equalsIgnoreCase("嗶咔萌約") ? new AnonymousChatFragment() : PicaAppFragment.n(((PicaAppObject) this.ja.get(i)).getTitle(), ((PicaAppObject) this.ja.get(i)).getUrl());
        } else if (this.ja.get(i) instanceof ChatroomListObject) {
            return ChatroomFragment.M(((ChatroomListObject) this.ja.get(i)).getUrl());
        } else {
            return null;
        }
    }
}
