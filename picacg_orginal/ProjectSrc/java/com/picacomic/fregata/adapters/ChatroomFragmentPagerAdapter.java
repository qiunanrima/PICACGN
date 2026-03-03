package com.picacomic.fregata.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.picacomic.fregata.fragments.ChatroomFragment;
import com.picacomic.fregata.objects.ChatroomListObject;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/ChatroomFragmentPagerAdapter.class */
public class ChatroomFragmentPagerAdapter extends FragmentPagerAdapter {
    public static final String TAG = "ChatroomFragmentPagerAdapter";
    ArrayList<ChatroomListObject> ja;

    public ChatroomFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<ChatroomListObject> arrayList) {
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
        return ChatroomFragment.M(this.ja.get(i).getUrl());
    }
}
