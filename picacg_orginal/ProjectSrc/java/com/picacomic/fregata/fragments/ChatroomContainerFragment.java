package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.ChatroomFragmentPagerAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.ChatroomListObject;
import com.picacomic.fregata.objects.responses.ChatroomListResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChatroomContainerFragment.class */
public class ChatroomContainerFragment extends BaseFragment {
    public static final String TAG = "ChatroomContainerFragment";
    ArrayList<ChatroomListObject> arrayList;
    Gson gson;
    boolean jR;
    Call<GeneralResponse<ChatroomListResponse>> kS;
    ChatroomFragmentPagerAdapter kT;
    @BindView(2131297051)
    ViewPager viewPager_tags;

    @Override // com.picacomic.fregata.fragments.BaseFragment, com.picacomic.fregata.a.i
    public void b(View view) {
        f.D(TAG, "TESTING BACK");
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        if (getActivity() != null) {
            AlertDialogCenter.chatroomRules(getActivity());
        }
        cm();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        this.kT.notifyDataSetChanged();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        if (this.kT == null) {
            this.kT = new ChatroomFragmentPagerAdapter(getChildFragmentManager(), this.arrayList);
        }
        if (this.viewPager_tags == null || this.viewPager_tags.getAdapter() != null) {
            return;
        }
        this.viewPager_tags.setAdapter(this.kT);
    }

    public void cm() {
        if (this.jR) {
            return;
        }
        this.jR = true;
        C(getResources().getString(2131689826));
        this.kS = new d(getContext()).dO().at(e.z(getActivity()));
        this.kS.enqueue(new Callback<GeneralResponse<ChatroomListResponse>>() { // from class: com.picacomic.fregata.fragments.ChatroomContainerFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ChatroomListResponse>> call, Throwable th) {
                th.printStackTrace();
                ChatroomContainerFragment.this.bC();
                new c(ChatroomContainerFragment.this.getActivity()).dN();
                ChatroomContainerFragment.this.bI();
                ChatroomContainerFragment.this.jR = false;
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ChatroomListResponse>> call, Response<GeneralResponse<ChatroomListResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response != null && response.body() != null && response.body().data != null && response.body().data.getChatList() != null) {
                        ChatroomContainerFragment.this.arrayList.clear();
                        ChatroomContainerFragment.this.arrayList.addAll(response.body().data.getChatList());
                        if (ChatroomContainerFragment.this.gson == null) {
                            ChatroomContainerFragment.this.gson = new Gson();
                        }
                        e.n(ChatroomContainerFragment.this.getContext(), ChatroomContainerFragment.this.gson.toJson(ChatroomContainerFragment.this.arrayList));
                        if (response.body().data != null && response.body().data.getChatList() != null) {
                            ChatroomContainerFragment.this.bI();
                        }
                    }
                } else {
                    try {
                        new c(ChatroomContainerFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ChatroomContainerFragment.this.bC();
                ChatroomContainerFragment.this.bI();
                ChatroomContainerFragment.this.jR = false;
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        if (this.arrayList == null) {
            this.arrayList = new ArrayList<>();
            if (this.gson == null) {
                this.gson = new Gson();
            }
            String G = e.G(getContext());
            if (G == null || G.equalsIgnoreCase("")) {
                return;
            }
            this.arrayList = (ArrayList) this.gson.fromJson(e.G(getContext()), new TypeToken<List<ChatroomListObject>>() { // from class: com.picacomic.fregata.fragments.ChatroomContainerFragment.1
            }.getType());
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427400, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.kS != null) {
            this.kS.cancel();
        }
        super.onDetach();
    }
}
