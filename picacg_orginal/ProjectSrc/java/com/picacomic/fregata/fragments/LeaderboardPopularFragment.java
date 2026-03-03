package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.design.R;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.BindView;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.adapters.LeaderboardPopularRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.objects.LeaderboardComicListObject;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.LeaderboardResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/LeaderboardPopularFragment.class */
public class LeaderboardPopularFragment extends BaseFragment implements k {
    public static final String TAG = "LeaderboardPopularFragment";
    ArrayList<LeaderboardComicListObject> arrayList;
    String jM;
    LinearLayoutManager jQ;
    LeaderboardPopularRecyclerViewAdapter pI;
    Call<GeneralResponse<LeaderboardResponse>> pJ;
    RadioGroup.OnCheckedChangeListener pK;
    String pL;
    boolean pM;
    @BindView(2131296743)
    RadioButton radioButton_24hr;
    @BindView(2131296744)
    RadioButton radioButton_30days;
    @BindView(2131296745)
    RadioButton radioButton_7days;
    @BindView(2131296746)
    RadioGroup radioGroup_time;
    @BindView(2131296766)
    RecyclerView recyclerView_popular;

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        if (this.arrayList == null || this.arrayList.size() <= i) {
            return;
        }
        String str = TAG;
        f.D(str, "Array Item = " + this.arrayList.get(i).toString());
        getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicDetailFragment.a(new ComicListObject(this.arrayList.get(i).getComicId())), ComicDetailFragment.TAG).addToBackStack(ComicDetailFragment.TAG).commit();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (this.arrayList == null || this.arrayList.size() != 0) {
            return;
        }
        String str = TAG;
        f.D(str, "CALL in initUI " + this.pM);
        dp();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        this.pI.notifyDataSetChanged();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        if (this.pK == null) {
            this.pK = new RadioGroup.OnCheckedChangeListener() { // from class: com.picacomic.fregata.fragments.LeaderboardPopularFragment.1
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case 2131296743:
                            LeaderboardPopularFragment.this.jM = "H24";
                            String str = LeaderboardPopularFragment.TAG;
                            f.D(str, "CALL in 24HRS " + LeaderboardPopularFragment.this.pM);
                            if (!LeaderboardPopularFragment.this.pM) {
                                LeaderboardPopularFragment.this.dp();
                            }
                            LeaderboardPopularFragment.this.pI.H(LeaderboardPopularFragment.this.jM);
                            return;
                        case 2131296744:
                            LeaderboardPopularFragment.this.jM = "D30";
                            String str2 = LeaderboardPopularFragment.TAG;
                            f.D(str2, "CALL in 30DAYS " + LeaderboardPopularFragment.this.pM);
                            if (!LeaderboardPopularFragment.this.pM) {
                                LeaderboardPopularFragment.this.dp();
                            }
                            LeaderboardPopularFragment.this.pI.H(LeaderboardPopularFragment.this.jM);
                            return;
                        case 2131296745:
                            LeaderboardPopularFragment.this.jM = "D7";
                            String str3 = LeaderboardPopularFragment.TAG;
                            f.D(str3, "CALL in 7DAYS " + LeaderboardPopularFragment.this.pM);
                            if (!LeaderboardPopularFragment.this.pM) {
                                LeaderboardPopularFragment.this.dp();
                            }
                            LeaderboardPopularFragment.this.pI.H(LeaderboardPopularFragment.this.jM);
                            return;
                        default:
                            return;
                    }
                }
            };
        }
        this.radioGroup_time.setOnCheckedChangeListener(this.pK);
    }

    public void dp() {
        C(getResources().getString(2131689826));
        this.pJ = new d(getContext()).dO().a(e.z(getActivity()), this.jM, this.pL);
        this.pJ.enqueue(new Callback<GeneralResponse<LeaderboardResponse>>() { // from class: com.picacomic.fregata.fragments.LeaderboardPopularFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<LeaderboardResponse>> call, Throwable th) {
                th.printStackTrace();
                LeaderboardPopularFragment.this.bC();
                new c(LeaderboardPopularFragment.this.getActivity()).dN();
                LeaderboardPopularFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<LeaderboardResponse>> call, Response<GeneralResponse<LeaderboardResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getComics() != null) {
                        if (LeaderboardPopularFragment.this.arrayList != null) {
                            LeaderboardPopularFragment.this.arrayList.clear();
                        }
                        LeaderboardPopularFragment.this.arrayList.addAll(response.body().data.getComics());
                    }
                } else {
                    try {
                        new c(LeaderboardPopularFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                LeaderboardPopularFragment.this.bC();
                LeaderboardPopularFragment.this.bI();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.pM = true;
        if (this.arrayList == null) {
            f.D(TAG, "INIT ARRAY");
            this.arrayList = new ArrayList<>();
            this.jM = "H24";
            this.pL = "VC";
            this.radioButton_24hr.setChecked(true);
        }
        String str = TAG;
        f.D(str, "IS INIT = " + this.pM + " timeType = " + this.jM);
        this.jQ = new LinearLayoutManager(getActivity(), 1, false);
        this.pI = new LeaderboardPopularRecyclerViewAdapter(getActivity(), this.arrayList, this);
        this.recyclerView_popular.setLayoutManager(this.jQ);
        this.recyclerView_popular.setAdapter(this.pI);
        f.D(TAG, "FLOW 1");
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        f.D(TAG, "FLOW 2");
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427418, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.pJ != null) {
            this.pJ.cancel();
        }
        super.onDetach();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        f.D(TAG, "FLOW 4");
        this.pM = false;
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        f.D(TAG, "FLOW 3");
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        f.D(TAG, "FLOW 5");
    }

    @Override // android.support.v4.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        f.D(TAG, "FLOW 6");
    }
}
