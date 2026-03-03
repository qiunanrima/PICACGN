package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.design.R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.adapters.ChatroomListRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.ChatroomListObject;
import com.picacomic.fregata.objects.responses.ChatroomListResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChatroomListFragment.class */
public class ChatroomListFragment extends BaseFragment implements k {
    public static final String TAG = "ChatroomListFragment";
    ArrayList<ChatroomListObject> arrayList;
    Gson gson;
    LinearLayoutManager jQ;
    boolean jR;
    Call<GeneralResponse<ChatroomListResponse>> kS;
    ChatroomListRecyclerViewAdapter mP;
    @BindView(2131296757)
    RecyclerView recyclerView_list;
    @BindView(2131297037)
    Toolbar toolbar;

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        if (this.arrayList == null || this.arrayList.size() <= i) {
            return;
        }
        if (this.arrayList != null && this.arrayList.get(i).getUrl().equalsIgnoreCase("allchatroom")) {
            getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new ChatroomContainerFragment(), ChatroomContainerFragment.TAG).addToBackStack(ChatroomContainerFragment.TAG).commit();
        } else if (this.arrayList == null || !this.arrayList.get(i).getUrl().equalsIgnoreCase("custompicaapp")) {
            getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ChatroomFragment.M(this.arrayList.get(i).getUrl()), ChatroomFragment.TAG).addToBackStack(ChatroomFragment.TAG).commit();
        } else {
            getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new CustomPicaAppContainerFragment(), CustomPicaAppContainerFragment.TAG).addToBackStack(CustomPicaAppContainerFragment.TAG).commit();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        this.toolbar.setVisibility(8);
        cm();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (this.arrayList != null) {
            this.mP.notifyDataSetChanged();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.jQ = new LinearLayoutManager(getContext(), 1, false);
        this.mP = new ChatroomListRecyclerViewAdapter(getContext(), this.arrayList, this);
        this.recyclerView_list.setLayoutManager(this.jQ);
        this.recyclerView_list.setAdapter(this.mP);
    }

    public void cm() {
        if (this.jR) {
            return;
        }
        this.jR = true;
        C(getResources().getString(2131689826));
        this.kS = new d(getContext()).dO().at(e.z(getActivity()));
        this.kS.enqueue(new Callback<GeneralResponse<ChatroomListResponse>>() { // from class: com.picacomic.fregata.fragments.ChatroomListFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ChatroomListResponse>> call, Throwable th) {
                th.printStackTrace();
                ChatroomListFragment.this.bC();
                new c(ChatroomListFragment.this.getActivity()).dN();
                ChatroomListFragment.this.bI();
                ChatroomListFragment.this.jR = false;
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ChatroomListResponse>> call, Response<GeneralResponse<ChatroomListResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response != null && response.body() != null && response.body().data != null && response.body().data.getChatList() != null) {
                        ChatroomListFragment.this.arrayList.clear();
                        ChatroomListFragment.this.arrayList.addAll(response.body().data.getChatList());
                        if (ChatroomListFragment.this.gson == null) {
                            ChatroomListFragment.this.gson = new Gson();
                        }
                        e.n(ChatroomListFragment.this.getContext(), ChatroomListFragment.this.gson.toJson(ChatroomListFragment.this.arrayList));
                        ChatroomListFragment.this.arrayList.add(new ChatroomListObject(null, "自定小程式", "自定小程式", "custompicaapp"));
                        if (response.body().data != null && response.body().data.getChatList() != null) {
                            ChatroomListFragment.this.bI();
                        }
                    }
                } else {
                    try {
                        new c(ChatroomListFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ChatroomListFragment.this.bC();
                ChatroomListFragment.this.bI();
                ChatroomListFragment.this.jR = false;
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.jR = false;
        if (this.arrayList == null) {
            this.arrayList = new ArrayList<>();
            if (this.gson == null) {
                this.gson = new Gson();
            }
            String G = e.G(getContext());
            if (G == null || G.equalsIgnoreCase("")) {
                return;
            }
            this.arrayList = (ArrayList) this.gson.fromJson(e.G(getContext()), new TypeToken<List<ChatroomListObject>>() { // from class: com.picacomic.fregata.fragments.ChatroomListFragment.1
            }.getType());
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427402, viewGroup, false);
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
