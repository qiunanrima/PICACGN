package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.ApkVersionListRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.LatestApplicationObject;
import com.picacomic.fregata.objects.responses.DataClass.ApplicationsResponse.ApplicationsResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ApkVersionListFragment.class */
public class ApkVersionListFragment extends BaseFragment implements k {
    public static final String TAG = "ApkVersionListFragment";
    ArrayList<LatestApplicationObject> arrayList;
    LinearLayoutManager jQ;
    ApkVersionListRecyclerViewAdapter km;
    Call<GeneralResponse<ApplicationsResponse>> kn;
    int page;
    @BindView(2131296753)
    RecyclerView recyclerView_apkVersions;
    @BindView(2131297037)
    Toolbar toolbar;
    int totalPage;

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        if (this.arrayList == null || this.arrayList.size() <= i) {
            return;
        }
        AlertDialogCenter.showUpdateApkAlertDialog(getContext(), this.arrayList.get(i), false);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, 2131689977, true);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        ce();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (this.arrayList != null) {
            this.km.notifyDataSetChanged();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.arrayList = new ArrayList<>();
        this.jQ = new LinearLayoutManager(getContext(), 1, false);
        this.km = new ApkVersionListRecyclerViewAdapter(getActivity(), this.arrayList, this);
        this.recyclerView_apkVersions.setLayoutManager(this.jQ);
        this.recyclerView_apkVersions.setAdapter(this.km);
    }

    public void ce() {
        if (this.page >= this.totalPage) {
            return;
        }
        C(getResources().getString(2131689826));
        f.aA("Show Progress");
        this.kn = new d(getContext()).dO().b(e.z(getActivity()), this.page + 1);
        this.kn.enqueue(new Callback<GeneralResponse<ApplicationsResponse>>() { // from class: com.picacomic.fregata.fragments.ApkVersionListFragment.1
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ApplicationsResponse>> call, Throwable th) {
                th.printStackTrace();
                f.aA("dismiss progress");
                ApkVersionListFragment.this.bC();
                new c(ApkVersionListFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ApplicationsResponse>> call, Response<GeneralResponse<ApplicationsResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(ApkVersionListFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.getApplications() != null) {
                    ApkVersionListFragment.this.page = response.body().data.getApplications().getPage();
                    ApkVersionListFragment.this.totalPage = response.body().data.getApplications().getPages();
                    if (response.body().data.getApplications().getDocs() != null) {
                        ApkVersionListFragment.this.arrayList.addAll(response.body().data.getApplications().getDocs());
                    }
                    if (ApkVersionListFragment.this.getActivity() != null) {
                        ApkVersionListFragment.this.bI();
                    }
                }
                f.aA("dismiss progress");
                ApkVersionListFragment.this.bC();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.page = 0;
        this.totalPage = 1;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427395, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.kn != null) {
            this.kn.cancel();
        }
        super.onDetach();
    }
}
