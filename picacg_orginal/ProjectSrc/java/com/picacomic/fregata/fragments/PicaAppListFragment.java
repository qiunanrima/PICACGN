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
import com.picacomic.fregata.adapters.PicaAppListRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.PicaAppObject;
import com.picacomic.fregata.objects.responses.DataClass.PicaAppsResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/PicaAppListFragment.class */
public class PicaAppListFragment extends BaseFragment implements k {
    public static final String TAG = "PicaAppListFragment";
    ArrayList<PicaAppObject> arrayList;
    Gson gson;
    LinearLayoutManager jQ;
    boolean jR;
    PicaAppListRecyclerViewAdapter qB;
    Call<GeneralResponse<PicaAppsResponse>> qC;
    @BindView(2131296757)
    RecyclerView recyclerView_list;
    @BindView(2131297037)
    Toolbar toolbar;

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        if (this.arrayList == null || this.arrayList.size() <= i) {
            return;
        }
        if (this.arrayList.get(i).getTitle().equalsIgnoreCase("嗶咔萌約")) {
            getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new AnonymousChatFragment(), AnonymousChatFragment.TAG).addToBackStack(AnonymousChatFragment.TAG).commit();
        } else {
            getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, PicaAppFragment.n(this.arrayList.get(i).getTitle(), this.arrayList.get(i).getUrl()), PicaAppFragment.TAG).addToBackStack(PicaAppFragment.TAG).commit();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        this.toolbar.setVisibility(8);
        du();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (this.arrayList != null) {
            this.qB.notifyDataSetChanged();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.jQ = new LinearLayoutManager(getContext(), 1, false);
        this.qB = new PicaAppListRecyclerViewAdapter(getContext(), this.arrayList, this);
        this.recyclerView_list.setLayoutManager(this.jQ);
        this.recyclerView_list.setAdapter(this.qB);
    }

    public void du() {
        if (this.jR) {
            return;
        }
        this.jR = true;
        C(getResources().getString(2131689826));
        this.qC = new d(getContext()).dO().au(e.z(getActivity()));
        this.qC.enqueue(new Callback<GeneralResponse<PicaAppsResponse>>() { // from class: com.picacomic.fregata.fragments.PicaAppListFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<PicaAppsResponse>> call, Throwable th) {
                th.printStackTrace();
                PicaAppListFragment.this.bC();
                new c(PicaAppListFragment.this.getActivity()).dN();
                PicaAppListFragment.this.bI();
                PicaAppListFragment.this.jR = false;
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<PicaAppsResponse>> call, Response<GeneralResponse<PicaAppsResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response != null && response.body() != null && response.body().data != null && response.body().data.getApps() != null) {
                        PicaAppListFragment.this.arrayList.clear();
                        PicaAppListFragment.this.arrayList.addAll(response.body().data.getApps());
                        PicaAppListFragment.this.arrayList.add(new PicaAppObject("嗶咔萌約", null, null, "可能是最有用的同性交友應用"));
                        if (PicaAppListFragment.this.gson == null) {
                            PicaAppListFragment.this.gson = new Gson();
                        }
                        e.o(PicaAppListFragment.this.getContext(), PicaAppListFragment.this.gson.toJson(PicaAppListFragment.this.arrayList));
                        if (response.body().data != null && response.body().data.getApps() != null) {
                            PicaAppListFragment.this.bI();
                        }
                    }
                } else {
                    try {
                        new c(PicaAppListFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                PicaAppListFragment.this.bC();
                PicaAppListFragment.this.bI();
                PicaAppListFragment.this.jR = false;
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
            String H = e.H(getContext());
            if (H == null || H.equalsIgnoreCase("")) {
                return;
            }
            this.arrayList = (ArrayList) this.gson.fromJson(e.H(getContext()), new TypeToken<List<PicaAppObject>>() { // from class: com.picacomic.fregata.fragments.PicaAppListFragment.1
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
        if (this.qC != null) {
            this.qC.cancel();
        }
        super.onDetach();
    }
}
