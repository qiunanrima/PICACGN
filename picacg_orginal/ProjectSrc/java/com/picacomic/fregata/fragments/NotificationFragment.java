package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.design.R;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.picacomic.fregata.a.h;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.NotificationRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.objects.NotificationObject;
import com.picacomic.fregata.objects.responses.DataClass.NotificationsResponse.NotificationsResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/NotificationFragment.class */
public class NotificationFragment extends BaseFragment implements h {
    public static final String TAG = "NotificationFragment";
    ArrayList<NotificationObject> ja;
    boolean or;
    int page;
    NotificationRecyclerViewAdapter qj;
    Call<GeneralResponse<NotificationsResponse>> qk;
    @BindView(2131296767)
    RecyclerView recyclerView;
    @BindView(2131297037)
    Toolbar toolbar;
    int totalPage;

    @Override // com.picacomic.fregata.a.h
    public void W(int i) {
        if (this.ja == null || this.ja.size() <= i || this.ja.get(i).getRedirectType() == null) {
            return;
        }
        if (this.ja.get(i).getRedirectType().equalsIgnoreCase("comic") && this.ja.get(i).getRedirectId() != null) {
            getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicDetailFragment.a(new ComicListObject(this.ja.get(i).getRedirectId())), ComicDetailFragment.TAG).addToBackStack(ComicDetailFragment.TAG).commit();
        } else if (this.ja.get(i).getRedirectType().equalsIgnoreCase("game") && this.ja.get(i).getRedirectId() != null) {
            getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, GameDetailFragment.ad(this.ja.get(i).getRedirectId()), GameDetailFragment.TAG).addToBackStack(GameDetailFragment.TAG).commit();
        } else if (this.ja.get(i).getRedirectType().equalsIgnoreCase("comment") && this.ja.get(i).getRedirectId() != null) {
            getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, CommentFragment.l(null, this.ja.get(i).getRedirectId()), CommentFragment.TAG).addToBackStack(CommentFragment.TAG).commit();
        } else if (this.ja.get(i).getRedirectType().equalsIgnoreCase("app")) {
            FragmentTransaction customAnimations = getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007);
            customAnimations.replace(R.id.container, PicaAppFragment.n(this.ja.get(i).getTitle() + "", this.ja.get(i).getLink()), PicaAppFragment.TAG).addToBackStack(PicaAppFragment.TAG).commit();
        } else if (this.ja.get(i).getRedirectType().equalsIgnoreCase("web")) {
            FragmentTransaction customAnimations2 = getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007);
            customAnimations2.replace(R.id.container, PicaAppFragment.n(this.ja.get(i).getTitle() + "", this.ja.get(i).getLink()), PicaAppFragment.TAG).addToBackStack(PicaAppFragment.TAG).commit();
        }
    }

    @Override // com.picacomic.fregata.a.h
    public void X(int i) {
        if (this.ja == null || this.ja.size() <= i || this.ja.get(i).getSender() == null) {
            return;
        }
        a(this.ja.get(i).getSender());
    }

    @Override // com.picacomic.fregata.a.h
    public void Y(int i) {
        if (this.ja == null || this.ja.size() <= i || this.ja.get(i).getSender() == null) {
            return;
        }
        D(g.b(this.ja.get(i).getCover()));
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        this.qj.notifyDataSetChanged();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.qj = new NotificationRecyclerViewAdapter(getContext(), this.ja, this);
        this.recyclerView.setAdapter(this.qj);
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.picacomic.fregata.fragments.NotificationFragment.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (recyclerView.getLayoutManager() != null && (recyclerView.getLayoutManager() instanceof LinearLayoutManager) && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == ((LinearLayoutManager) recyclerView.getLayoutManager()).getItemCount() - 1) {
                    NotificationFragment.this.ds();
                }
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
    }

    public void ds() {
        if (this.totalPage < this.page || this.or) {
            return;
        }
        this.or = true;
        C(getResources().getString(2131689824));
        this.qk = new d(getContext()).dO().d(e.z(getActivity()), this.page);
        this.qk.enqueue(new Callback<GeneralResponse<NotificationsResponse>>() { // from class: com.picacomic.fregata.fragments.NotificationFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<NotificationsResponse>> call, Throwable th) {
                th.printStackTrace();
                NotificationFragment.this.bC();
                new c(NotificationFragment.this.getActivity()).dN();
                NotificationFragment.this.bI();
                NotificationFragment.this.or = false;
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<NotificationsResponse>> call, Response<GeneralResponse<NotificationsResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getNotifications() != null && response.body().data.getNotifications().getDocs() != null) {
                        for (int i = 0; i < response.body().data.getNotifications().getDocs().size(); i++) {
                            NotificationFragment.this.ja.add(response.body().data.getNotifications().getDocs().get(i));
                        }
                        NotificationFragment.this.totalPage = response.body().data.getNotifications().getPages();
                        NotificationFragment.this.page++;
                    }
                } else {
                    try {
                        new c(NotificationFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                NotificationFragment.this.bC();
                NotificationFragment.this.bI();
                NotificationFragment.this.or = false;
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        a(this.toolbar, 2131689979, true);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        if (this.ja == null) {
            this.ja = new ArrayList<>();
            this.page = 1;
            this.totalPage = 1;
            this.or = false;
        }
        ds();
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427421, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.qk != null) {
            this.qk.cancel();
        }
        super.onDetach();
    }
}
