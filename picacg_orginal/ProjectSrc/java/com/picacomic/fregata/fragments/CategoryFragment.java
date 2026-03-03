package com.picacomic.fregata.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.CategoryRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.CategoryObject;
import com.picacomic.fregata.objects.DefaultCategoryObject;
import com.picacomic.fregata.objects.responses.CategoryResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.KeywordsResponse;
import com.picacomic.fregata.objects.responses.TagListResponse;
import com.picacomic.fregata.utils.FullGridLayoutManager;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/CategoryFragment.class */
public class CategoryFragment extends BaseFragment implements k {
    public static final String TAG = "CategoryFragment";
    @BindView(2131296430)
    CoordinatorLayout coordinatorLayout;
    ArrayList<String> kA;
    ArrayList<String> kB;
    ArrayList<CategoryObject> kC;
    ArrayList<DefaultCategoryObject> kD;
    boolean kE;
    Call<GeneralResponse<CategoryResponse>> kF;
    Call<GeneralResponse<TagListResponse>> kG;
    Call<GeneralResponse<KeywordsResponse>> kH;
    CategoryRecyclerViewAdapter kx;
    Button[] ky;
    Button[] kz;
    @BindView(2131296644)
    LinearLayout linearLayout_keywords;
    @BindView(2131296645)
    LinearLayout linearLayout_tags;
    @BindView(2131296754)
    RecyclerView recyclerView_category;
    @BindView(2131296790)
    NestedScrollView scrollView;
    @BindView(2131296793)
    SearchView searchView;
    @BindView(2131297037)
    Toolbar toolbar;

    private void c(View view) {
        if (view != null) {
            if (view instanceof TextView) {
                ((TextView) view).setTextColor(ViewCompat.MEASURED_STATE_MASK);
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    c(viewGroup.getChildAt(i));
                }
            }
        }
    }

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        int i2;
        if (this.kD != null) {
            int size = this.kD.size();
            switch (i) {
                case 0:
                    if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
                        return;
                    }
                    ((MainActivity) getActivity()).bW();
                    return;
                case 1:
                    getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new LeaderboardContainerFragment(), LeaderboardContainerFragment.TAG).addToBackStack(LeaderboardContainerFragment.TAG).commit();
                    return;
                case 2:
                    getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new GameFragment(), GameFragment.TAG).addToBackStack(GameFragment.TAG).commit();
                    return;
                case 3:
                    return;
                case 4:
                    if (getActivity() != null) {
                        getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new PicaAppContainerFragment(), PicaAppContainerFragment.TAG).addToBackStack(PicaAppContainerFragment.TAG).commit();
                        return;
                    }
                    return;
                case 5:
                    getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, CommentFragment.l(null, "5822a6e3ad7ede654696e482"), CommentFragment.TAG).addToBackStack(CommentFragment.TAG).commit();
                    return;
                case 6:
                    getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicListFragment.a(null, null, null, null, null, "dd", null, null, null), ComicListFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
                    return;
                case 7:
                    getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicListFragment.a("CATEGORY_RANDOM", null, null, null, null, null, null, null, null), ComicListFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
                    return;
                default:
                    if (this.kC == null || this.kC.size() <= (i2 = i - (size + 0))) {
                        return;
                    }
                    if (!this.kC.get(i2).isWeb() || this.kC.get(i2).getLink() == null) {
                        getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicListFragment.a(this.kC.get(i2).getTitle(), null, null, null, null, "dd", null, null, null), ComicListFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
                        return;
                    } else {
                        getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, PicaAppFragment.n(this.kC.get(i2).getTitle(), this.kC.get(i2).getLink()), PicaAppFragment.TAG).addToBackStack(PicaAppFragment.TAG).commit();
                        return;
                    }
            }
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        this.toolbar.setTitle(2131689970);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(0);
            if (e.ai(getActivity())) {
                AlertDialogCenter.showFaqAlertDialog(getContext(), "https://www.picacomic.com/faq", null);
                e.n((Context) getActivity(), false);
            }
        }
        if (this.kE) {
            cl();
        } else {
            bI();
        }
        if (this.kA != null && !this.kA.isEmpty()) {
            bI();
        }
        if (this.kB == null || (this.kB != null && this.kB.size() == 0)) {
            ck();
        }
        c(this.searchView);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        this.kx.notifyDataSetChanged();
        if (this.kA == null || this.kA.size() <= 0) {
            return;
        }
        this.ky = new Button[this.kA.size()];
        for (int i = 0; i < this.kA.size(); i++) {
            this.ky[i] = new Button(getActivity(), null, 2131755245);
            this.ky[i].setTextColor(ResourcesCompat.getColor(getResources(), 2131099775, getActivity().getTheme()));
            this.ky[i].setBackground(ResourcesCompat.getDrawable(getResources(), 2131230854, getActivity().getTheme()));
            this.ky[i].setText(this.kA.get(i) + "");
            this.ky[i].setTag(this.kA.get(i));
            this.ky[i].setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CategoryFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CategoryFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicListFragment.a(null, null, (String) view.getTag(), null, null, null, null, null, null), ComicListFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
                }
            });
        }
        g.a(this.linearLayout_tags, this.ky, getActivity(), (View) null);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() { // from class: com.picacomic.fregata.fragments.CategoryFragment.2
            @Override // android.support.v7.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                return false;
            }

            @Override // android.support.v7.widget.SearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                if (str == null || str.equalsIgnoreCase("")) {
                    return false;
                }
                CategoryFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicListFragment.a(null, str, null, null, null, null, null, null, null), ComicListFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
                return false;
            }
        });
        this.recyclerView_category.setLayoutManager(new FullGridLayoutManager(getActivity(), 3));
        this.kx = new CategoryRecyclerViewAdapter(getActivity(), this.kD, this.kC, this);
        this.recyclerView_category.setAdapter(this.kx);
        this.recyclerView_category.setNestedScrollingEnabled(false);
    }

    public void ci() {
        if (this.kD != null) {
            for (int i = 0; i < 8; i++) {
                DefaultCategoryObject defaultCategoryObject = null;
                switch (i) {
                    case 0:
                        defaultCategoryObject = new DefaultCategoryObject("", getString(2131689644), getString(2131689644), 2131230865);
                        break;
                    case 1:
                        defaultCategoryObject = new DefaultCategoryObject("", getString(2131689638), getString(2131689638), 2131230860);
                        break;
                    case 2:
                        defaultCategoryObject = new DefaultCategoryObject("", getString(2131689636), getString(2131689636), 2131230858);
                        break;
                    case 3:
                        defaultCategoryObject = new DefaultCategoryObject("", getString(2131689634), getString(2131689634), 2131230862);
                        break;
                    case 4:
                        defaultCategoryObject = new DefaultCategoryObject("", getString(2131689640), getString(2131689640), 2131230862);
                        break;
                    case 5:
                        defaultCategoryObject = new DefaultCategoryObject("", getString(2131689642), getString(2131689642), 2131230857);
                        break;
                    case 6:
                        defaultCategoryObject = new DefaultCategoryObject("", getString(2131689637), getString(2131689637), 2131230859);
                        break;
                    case 7:
                        defaultCategoryObject = new DefaultCategoryObject("", getString(2131689643), getString(2131689643), 2131230864);
                        break;
                }
                this.kD.add(defaultCategoryObject);
            }
        }
    }

    public void cj() {
        if (this.kB == null || this.kB.size() <= 0) {
            return;
        }
        this.kz = new Button[this.kB.size()];
        for (int i = 0; i < this.kB.size(); i++) {
            this.kz[i] = new Button(getActivity(), null, 2131755209);
            this.kz[i].setTextColor(ResourcesCompat.getColor(getResources(), 2131099769, getActivity().getTheme()));
            this.kz[i].setBackground(ResourcesCompat.getDrawable(getResources(), 2131230839, getActivity().getTheme()));
            this.kz[i].setText(this.kB.get(i) + "");
            this.kz[i].setTag(this.kB.get(i));
            this.kz[i].setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CategoryFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CategoryFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicListFragment.a(null, (String) view.getTag(), null, null, null, null, null, null, null), ComicListFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
                }
            });
        }
        g.a(this.linearLayout_keywords, this.kz, getActivity(), (View) null);
    }

    public void ck() {
        bA();
        this.kH = new d(getContext()).dO().ar(e.z(getActivity()));
        this.kH.enqueue(new Callback<GeneralResponse<KeywordsResponse>>() { // from class: com.picacomic.fregata.fragments.CategoryFragment.5
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<KeywordsResponse>> call, Throwable th) {
                th.printStackTrace();
                new c(CategoryFragment.this.getActivity()).dN();
                CategoryFragment.this.bC();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<KeywordsResponse>> call, Response<GeneralResponse<KeywordsResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(CategoryFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.getKeywords() != null && response.body().data.getKeywords().size() > 0) {
                    f.D(CategoryFragment.TAG, "KEYWORD = " + response.body().data.getKeywords().toString());
                    if (CategoryFragment.this.kB == null) {
                        CategoryFragment.this.kB = new ArrayList<>();
                    }
                    for (int i = 0; i < response.body().data.getKeywords().size(); i++) {
                        CategoryFragment.this.kB.add(response.body().data.getKeywords().get(i) + "");
                    }
                    if (CategoryFragment.this.getActivity() != null) {
                        CategoryFragment.this.cj();
                    }
                }
                CategoryFragment.this.bC();
            }
        });
    }

    public void cl() {
        C(getResources().getString(2131689827));
        this.kF = new d(getContext()).dO().al(e.z(getActivity()));
        this.kF.enqueue(new Callback<GeneralResponse<CategoryResponse>>() { // from class: com.picacomic.fregata.fragments.CategoryFragment.6
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<CategoryResponse>> call, Throwable th) {
                th.printStackTrace();
                new c(CategoryFragment.this.getActivity()).dN();
                CategoryFragment.this.bC();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<CategoryResponse>> call, Response<GeneralResponse<CategoryResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(CategoryFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.categories != null) {
                    for (int i = 0; i < response.body().data.getCategories().size(); i++) {
                        CategoryFragment.this.kC.add(response.body().data.getCategories().get(i));
                    }
                    e.j(CategoryFragment.this.getActivity(), new Gson().toJson(CategoryFragment.this.kC));
                    f.D(CategoryFragment.TAG, CategoryFragment.this.kC.toString());
                    CategoryFragment.this.bI();
                }
                CategoryFragment.this.bC();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        String str = TAG;
        f.D(str, "Token = " + e.z(getActivity()));
        this.kE = false;
        if (this.kD == null) {
            this.kD = new ArrayList<>();
            ci();
        }
        if (this.kC == null) {
            this.kC = new ArrayList<>();
            this.kE = true;
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        View inflate = layoutInflater.inflate(2131427396, viewGroup, false);
        if (e.C(getContext()) != null && !e.C(getContext()).equalsIgnoreCase("") && (this.kC == null || (this.kC != null && this.kC.size() == 0))) {
            this.kC = (ArrayList) new Gson().fromJson(e.C(getContext()), new TypeToken<List<CategoryObject>>() { // from class: com.picacomic.fregata.fragments.CategoryFragment.1
            }.getType());
        }
        if (getActivity() != null) {
            a(inflate);
        }
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.kG != null) {
            this.kG.cancel();
        }
        if (this.kH != null) {
            this.kH.cancel();
        }
        if (this.kF != null) {
            this.kF.cancel();
        }
        super.onDetach();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.kC == null || this.kC.size() <= 0 || getContext() == null) {
            return;
        }
        e.j(getContext(), new Gson().toJson(this.kC));
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // android.support.v4.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        cj();
    }
}
