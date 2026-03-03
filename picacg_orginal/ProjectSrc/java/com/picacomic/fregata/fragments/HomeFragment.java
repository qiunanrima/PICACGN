package com.picacomic.fregata.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.R;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.BannerPagerAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.holders.AnnouncementContainerView;
import com.picacomic.fregata.holders.ComicCollectionView;
import com.picacomic.fregata.objects.AnnouncementObject;
import com.picacomic.fregata.objects.BannerObject;
import com.picacomic.fregata.objects.CollectionObject;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.objects.ThumbnailObject;
import com.picacomic.fregata.objects.responses.BannersResponse;
import com.picacomic.fregata.objects.responses.DataClass.AnnouncementsResponse.AnnouncementsResponse;
import com.picacomic.fregata.objects.responses.DataClass.CollectionsResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import com.picacomic.fregata.utils.views.PagerIndicator;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/HomeFragment.class */
public class HomeFragment extends BaseFragment implements View.OnClickListener, k {
    public static final String TAG = "HomeFragment";
    Call<GeneralResponse<AnnouncementsResponse>> jP;
    @BindView(2131296682)
    LinearLayout linearLayout_announcements;
    @BindView(2131296684)
    LinearLayout linearLayout_collection_1;
    @BindView(2131296685)
    LinearLayout linearLayout_collection_2;
    @BindView(2131296686)
    LinearLayout linearLayout_collection_3;
    @BindView(2131296687)
    LinearLayout linearLayout_collection_4;
    @BindView(2131296688)
    LinearLayout linearLayout_collection_5;
    @BindView(2131296683)
    LinearLayout linearLayout_pagerIndicators;
    Menu menu;
    int page;
    Call<GeneralResponse<BannersResponse>> pn;
    Call<GeneralResponse<CollectionsResponse>> po;
    PagerIndicator pp;
    BannerPagerAdapter pq;
    CountDownTimer pr;
    ArrayList<BannerObject> ps;
    ArrayList<AnnouncementObject> pt;
    ArrayList<CollectionObject> pu;
    AnnouncementContainerView pv;
    int pw;
    String px;
    @BindView(2131296948)
    TextView textView_bannerTitle;
    @BindView(2131297037)
    Toolbar toolbar;
    @BindView(2131297054)
    ViewPager viewPager_banner;

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        if (this.ps == null || this.ps.size() <= i) {
            return;
        }
        if (this.ps.get(i).getType().equals("game")) {
            if (this.ps.get(i).getGameId() != null) {
                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                if (getContext() != null && e.x(getContext())) {
                    beginTransaction.setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007);
                }
                beginTransaction.replace(R.id.container, GameDetailFragment.ad(this.ps.get(i).getGameId()), GameDetailFragment.TAG).addToBackStack(GameDetailFragment.TAG).commit();
            }
        } else if (!this.ps.get(i).getType().equals("comic")) {
            if (!this.ps.get(i).getType().equals("web") || this.ps.get(i).getLink() == null) {
                return;
            }
            g.A(getActivity(), this.ps.get(i).getLink());
        } else if (this.ps.get(i).getComicId() != null) {
            ComicListObject comicListObject = new ComicListObject(this.ps.get(i).getComicId());
            FragmentTransaction beginTransaction2 = getFragmentManager().beginTransaction();
            if (getContext() != null && e.x(getContext())) {
                beginTransaction2.setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007);
            }
            beginTransaction2.replace(R.id.container, ComicDetailFragment.a(comicListObject), ComicDetailFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            return;
        }
        ((MainActivity) getActivity()).t(0);
        if (this.pt != null && this.pt.size() > 0 && getActivity() != null && !e.I(getActivity()).equals(this.pt.get(0).getAnnouncementId())) {
            this.px = this.pt.get(0).getAnnouncementId();
            AlertDialogCenter.showAnnouncementAlertDialog(getContext(), g.b(this.pt.get(0).getThumb()), this.pt.get(0).getTitle(), this.pt.get(0).getContent(), this.pt.get(0).getCreatedAt(), new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.HomeFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    e.p(HomeFragment.this.getContext(), HomeFragment.this.pt.get(0).getAnnouncementId());
                }
            });
        }
        bI();
        dk();
        dj();
        dl();
        String F = e.F(getContext());
        if (F == null || F.equalsIgnoreCase("")) {
            dj();
            return;
        }
        try {
            List list = (List) new Gson().fromJson(F, new TypeToken<List<BannerObject>>() { // from class: com.picacomic.fregata.fragments.HomeFragment.6
            }.getType());
            if (list == null || list.size() <= 0) {
                dj();
            } else {
                this.ps.addAll(list);
                this.pp.setSize(this.ps.size());
                TextView textView = this.textView_bannerTitle;
                textView.setText(this.ps.get(0).getTitle() + " - " + this.ps.get(0).getShortDescription());
                this.pq.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dj();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (this.pt != null && this.pt.size() > 0 && this.pv != null) {
            this.pv.dL();
            this.pv.b(this.pt);
            if (getActivity() != null && !e.I(getActivity()).equals(this.pt.get(0).getAnnouncementId()) && (this.px == null || !this.px.equalsIgnoreCase(this.pt.get(0).getAnnouncementId()))) {
                AlertDialogCenter.showAnnouncementAlertDialog(getContext(), g.b(this.pt.get(0).getThumb()), this.pt.get(0).getTitle(), this.pt.get(0).getContent(), this.pt.get(0).getCreatedAt(), new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.HomeFragment.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        e.p(HomeFragment.this.getContext(), HomeFragment.this.pt.get(0).getAnnouncementId());
                    }
                });
            }
        }
        try {
            if (this.menu == null || !e.ak(getContext())) {
                return;
            }
            this.menu.getItem(0).setIcon(2131230964);
        } catch (Exception e) {
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.pq = new BannerPagerAdapter(getContext(), this.ps, this);
        this.viewPager_banner.setAdapter(this.pq);
        this.viewPager_banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.picacomic.fregata.fragments.HomeFragment.4
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                HomeFragment.this.pp.setCurrentIndex(i);
                TextView textView = HomeFragment.this.textView_bannerTitle;
                textView.setText(HomeFragment.this.ps.get(i).getTitle() + " - " + HomeFragment.this.ps.get(i).getShortDescription());
                HomeFragment.this.dm();
            }
        });
    }

    public void dj() {
        this.pn = new d(getContext()).dO().as(e.z(getActivity()));
        this.pn.enqueue(new Callback<GeneralResponse<BannersResponse>>() { // from class: com.picacomic.fregata.fragments.HomeFragment.8
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<BannersResponse>> call, Throwable th) {
                th.printStackTrace();
                new c(HomeFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<BannersResponse>> call, Response<GeneralResponse<BannersResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(HomeFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                f.aA(response.body().data.toString());
                if (response.body().data == null || response.body().data.getBanners() == null) {
                    return;
                }
                e.m(HomeFragment.this.getContext(), new Gson().toJson(response.body().data.getBanners()));
                if (HomeFragment.this.ps != null) {
                    HomeFragment.this.ps.clear();
                } else {
                    HomeFragment.this.ps = new ArrayList<>();
                }
                HomeFragment.this.ps.addAll(response.body().data.getBanners());
                if (HomeFragment.this.pq != null) {
                    HomeFragment.this.pq.notifyDataSetChanged();
                }
                if (HomeFragment.this.pp != null) {
                    HomeFragment.this.pp.setSize(HomeFragment.this.ps.size());
                }
                if (HomeFragment.this.textView_bannerTitle == null || HomeFragment.this.ps.size() <= 0) {
                    return;
                }
                TextView textView = HomeFragment.this.textView_bannerTitle;
                textView.setText(HomeFragment.this.ps.get(0).getTitle() + " - " + HomeFragment.this.ps.get(0).getShortDescription());
            }
        });
    }

    public void dk() {
        this.jP = new d(getContext()).dO().f(e.z(getActivity()), this.page);
        this.jP.enqueue(new Callback<GeneralResponse<AnnouncementsResponse>>() { // from class: com.picacomic.fregata.fragments.HomeFragment.9
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<AnnouncementsResponse>> call, Throwable th) {
                th.printStackTrace();
                HomeFragment.this.bC();
                new c(HomeFragment.this.getActivity()).dN();
                HomeFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<AnnouncementsResponse>> call, Response<GeneralResponse<AnnouncementsResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getAnnouncements() != null && response.body().data.getAnnouncements().getDocs() != null) {
                        HomeFragment.this.pt = response.body().data.getAnnouncements().getDocs();
                    }
                } else {
                    try {
                        new c(HomeFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                HomeFragment.this.bC();
                HomeFragment.this.bI();
            }
        });
    }

    public void dl() {
        this.po = new d(getContext()).dO().aq(e.z(getActivity()));
        this.po.enqueue(new Callback<GeneralResponse<CollectionsResponse>>() { // from class: com.picacomic.fregata.fragments.HomeFragment.10
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<CollectionsResponse>> call, Throwable th) {
                th.printStackTrace();
                HomeFragment.this.bC();
                new c(HomeFragment.this.getActivity()).dN();
                HomeFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<CollectionsResponse>> call, Response<GeneralResponse<CollectionsResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getCollections() != null) {
                        HomeFragment.this.pu = response.body().data.getCollections();
                    }
                    HomeFragment.this.dn();
                } else {
                    try {
                        new c(HomeFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                HomeFragment.this.bC();
                HomeFragment.this.bI();
            }
        });
    }

    public void dm() {
        if (this.pr != null) {
            this.pr.cancel();
        }
        this.pr = new CountDownTimer(4000L, 1000L) { // from class: com.picacomic.fregata.fragments.HomeFragment.2
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (HomeFragment.this.pp != null) {
                    try {
                        if (HomeFragment.this.pp.hasNext()) {
                            HomeFragment.this.viewPager_banner.setCurrentItem(HomeFragment.this.pp.getCurrentIndex() + 1);
                        } else {
                            HomeFragment.this.viewPager_banner.setCurrentItem(0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                HomeFragment.this.dm();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }
        };
        this.pr.start();
    }

    public void dn() {
        if (this.pu != null) {
            for (int i = 0; i < this.pu.size(); i++) {
                try {
                    ComicCollectionView comicCollectionView = new ComicCollectionView(getActivity(), this.pu.get(i).getComics(), (i * 10) + 10000, this, null);
                    comicCollectionView.getTextView_title().setText(this.pu.get(i).getTitle() + "");
                    if (i == 0 && this.linearLayout_collection_1 != null) {
                        this.linearLayout_collection_1.addView(comicCollectionView);
                    } else if (i == 1 && this.linearLayout_collection_1 != null) {
                        this.linearLayout_collection_2.addView(comicCollectionView);
                    } else if (i == 2 && this.linearLayout_collection_1 != null) {
                        this.linearLayout_collection_3.addView(comicCollectionView);
                    } else if (i == 3 && this.linearLayout_collection_1 != null) {
                        this.linearLayout_collection_4.addView(comicCollectionView);
                    } else if (i == 4 && this.linearLayout_collection_1 != null) {
                        this.linearLayout_collection_5.addView(comicCollectionView);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        a(this.toolbar, 2131689977, false);
        if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        }
        this.toolbar.setTitle(2131689977);
        this.pw = 0;
        this.page = 1;
        this.viewPager_banner.getLayoutParams().height = (g.as(getActivity()) * 9) / 16;
        this.pp = new PagerIndicator(getActivity(), this.linearLayout_pagerIndicators, 5);
        ThumbnailObject[] thumbnailObjectArr = new ThumbnailObject[4];
        for (int i = 0; i < thumbnailObjectArr.length; i++) {
            thumbnailObjectArr[i] = new ThumbnailObject("https://storage1.picacomic.com", new String[]{"dd3a46f9-1fe0-45e4-8fe9-c84950b73083.jpg", "184ad860-e20f-4517-a07b-d81c17d9620a.jpg", "b2afb775-5e89-4d1f-a8c8-9cc56873af61.jpg", "d75914f5-e0ca-4914-9ed3-afc272b3067c.jpg"}[i], new String[]{"cover.jpg", "cover.jpg", "cover.jpg", "cover.jpg"}[i]);
        }
        new BannerObject("banner1", "嗶咔2.0公測正式開放！", "web", "", null, null, "https://picacomic.com", null);
        new BannerObject("banner2", "玩機動戰隊送多麗絲／教皇抱枕", "game", "", "58296dee1cc00b5d50b1b5fe", null, null, null);
        new BannerObject("banner3", "一點沒露卻色氣滿滿・奈奈與薫的SM日記", "comic", "", null, "5822a5bcad7ede6546963762", null, thumbnailObjectArr[0]);
        new BannerObject("banner4", "拯救嗶咔・點擊廣告", "ads", "", null, null, null, null);
        this.ps = new ArrayList<>();
        this.pv = new AnnouncementContainerView(getActivity(), null, 1000, this, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.HomeFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FragmentTransaction beginTransaction = HomeFragment.this.getFragmentManager().beginTransaction();
                if (HomeFragment.this.getContext() != null && e.x(HomeFragment.this.getContext())) {
                    beginTransaction.setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007);
                }
                beginTransaction.replace(R.id.container, new AnnouncementListFragment(), AnnouncementListFragment.TAG).addToBackStack(AnnouncementListFragment.TAG).commit();
            }
        });
        this.pv.getTextView_title().setText(2131689968);
        this.linearLayout_announcements.addView(this.pv);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        dm();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (((Integer) view.getTag()).intValue() / 1000 == 1) {
            AnnouncementObject announcementObject = this.pt.get(((Integer) view.getTag()).intValue() % 1000);
            AlertDialogCenter.showAnnouncementAlertDialog(view.getContext(), g.b(announcementObject.getThumb()), announcementObject.getTitle(), announcementObject.getContent(), announcementObject.getCreatedAt(), null);
        }
        if (((Integer) view.getTag()).intValue() / 10000 == 1) {
            int intValue = ((Integer) view.getTag()).intValue() - 10000;
            getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicDetailFragment.a(new ComicListObject(this.pu.get(intValue / 10).getComics().get(intValue % 10).getComicId() + "")), ComicDetailFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131492871, menu);
        this.menu = menu;
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427414, viewGroup, false);
        if (e.E(getContext()) != null && !e.E(getContext()).equalsIgnoreCase("") && (this.pt == null || (this.pt != null && this.pt.size() == 0))) {
            this.pt = (ArrayList) new Gson().fromJson(e.E(getContext()), new TypeToken<List<AnnouncementObject>>() { // from class: com.picacomic.fregata.fragments.HomeFragment.1
            }.getType());
        }
        setHasOptionsMenu(true);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.pr != null) {
            this.pr.cancel();
            this.pr = null;
        }
        if (this.jP != null) {
            this.jP.cancel();
        }
        if (this.pn != null) {
            this.pn.cancel();
        }
        super.onDetach();
    }

    @Override // android.support.v4.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131296284) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            if (getContext() != null && e.x(getContext())) {
                beginTransaction.setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007);
            }
            beginTransaction.replace(R.id.container, new NotificationFragment(), NotificationFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.pt == null || this.pt.size() <= 0 || getContext() == null) {
            return;
        }
        e.l(getContext(), new Gson().toJson(this.pt));
    }
}
