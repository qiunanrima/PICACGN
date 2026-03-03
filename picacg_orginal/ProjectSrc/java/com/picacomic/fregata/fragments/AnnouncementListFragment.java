package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.AnnouncementListRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.AnnouncementObject;
import com.picacomic.fregata.objects.responses.DataClass.AnnouncementsResponse.AnnouncementsResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/AnnouncementListFragment.class */
public class AnnouncementListFragment extends BaseFragment implements k {
    public static final String TAG = "AnnouncementListFragment";
    ArrayList<AnnouncementObject> arrayList;
    AnnouncementListRecyclerViewAdapter jO;
    Call<GeneralResponse<AnnouncementsResponse>> jP;
    LinearLayoutManager jQ;
    boolean jR;
    int page;
    @BindView(2131296751)
    RecyclerView recyclerView_apkVersions;
    @BindView(2131297037)
    Toolbar toolbar;
    int totalPage;

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        if (this.arrayList == null || this.arrayList.size() <= i) {
            return;
        }
        AlertDialogCenter.showAnnouncementAlertDialog(getContext(), g.b(this.arrayList.get(i).getThumb()), this.arrayList.get(i).getTitle(), this.arrayList.get(i).getContent(), this.arrayList.get(i).getCreatedAt(), null);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, 2131689968, true);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        cb();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (this.arrayList != null) {
            this.jO.notifyDataSetChanged();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.arrayList = new ArrayList<>();
        this.jQ = new LinearLayoutManager(getContext(), 1, false);
        this.jO = new AnnouncementListRecyclerViewAdapter(getActivity(), this.arrayList, this);
        this.recyclerView_apkVersions.setLayoutManager(this.jQ);
        this.recyclerView_apkVersions.setAdapter(this.jO);
        this.recyclerView_apkVersions.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.picacomic.fregata.fragments.AnnouncementListFragment.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (recyclerView.getLayoutManager() != null && (recyclerView.getLayoutManager() instanceof LinearLayoutManager) && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == ((LinearLayoutManager) recyclerView.getLayoutManager()).getItemCount() - 1) {
                    AnnouncementListFragment.this.cb();
                }
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
    }

    public void cb() {
        if (!this.jR && this.page < this.totalPage) {
            this.jR = true;
            C(getResources().getString(2131689826));
            this.jP = new d(getContext()).dO().f(e.z(getActivity()), this.page + 1);
            this.jP.enqueue(new Callback<GeneralResponse<AnnouncementsResponse>>() { // from class: com.picacomic.fregata.fragments.AnnouncementListFragment.2
                @Override // retrofit2.Callback
                public void onFailure(Call<GeneralResponse<AnnouncementsResponse>> call, Throwable th) {
                    th.printStackTrace();
                    AnnouncementListFragment.this.bC();
                    new c(AnnouncementListFragment.this.getActivity()).dN();
                    AnnouncementListFragment.this.bI();
                    AnnouncementListFragment.this.jR = false;
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<GeneralResponse<AnnouncementsResponse>> call, Response<GeneralResponse<AnnouncementsResponse>> response) {
                    if (response.code() == 200) {
                        f.aA(response.body().data.toString());
                        if (response.body().data != null && response.body().data.getAnnouncements() != null && response.body().data.getAnnouncements().getDocs() != null) {
                            AnnouncementListFragment.this.page = response.body().data.getAnnouncements().getPage();
                            AnnouncementListFragment.this.totalPage = response.body().data.getAnnouncements().getPages();
                            AnnouncementListFragment.this.arrayList.addAll(response.body().data.getAnnouncements().getDocs());
                        }
                    } else {
                        try {
                            new c(AnnouncementListFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    AnnouncementListFragment.this.bC();
                    AnnouncementListFragment.this.bI();
                    AnnouncementListFragment.this.jR = false;
                }
            });
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.page = 0;
        this.totalPage = 1;
        this.jR = false;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427393, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.jP != null) {
            this.jP.cancel();
        }
        super.onDetach();
    }
}
