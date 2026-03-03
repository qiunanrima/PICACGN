package com.picacomic.fregata.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.R;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.BindViews;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picacomic.fregata.a.b;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.ComicListRecyclerViewAdapter;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.c.c;
import com.picacomic.fregata.objects.CategoryObject;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.objects.databaseTable.DbComicDetailObject;
import com.picacomic.fregata.objects.databaseTable.DbComicViewRecordObject;
import com.picacomic.fregata.objects.requests.SortingBody;
import com.picacomic.fregata.objects.responses.ComicRandomListResponse;
import com.picacomic.fregata.objects.responses.DataClass.ComicListResponse.ComicListResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ComicListFragment.class */
public class ComicListFragment extends BaseFragment implements View.OnClickListener, b {
    public static final String TAG = "ComicListFragment";
    private static int ny = 40;
    public static final int[] nz = {2131230831, 2131230834, 2131230829, 2131230833, 2131230835, 2131230830, 2131230832, 2131230837};
    private String author;
    @BindViews({2131296330, 2131296333, 2131296328, 2131296332, 2131296334, 2131296329, 2131296331, 2131296335})
    public Button[] buttons_filters;
    @BindView(2131296430)
    CoordinatorLayout coordinatorLayout;
    private String creatorId;
    private String creatorName;
    @BindView(2131296456)
    EditText editText_currentPage;
    @BindView(2131296511)
    FrameLayout frameLayout_noComics;
    int hP;
    @BindView(2131296583)
    ImageView imageView_empty;
    boolean kE;
    Call<GeneralResponse<ComicListResponse>> nB;
    Call<GeneralResponse<ComicRandomListResponse>> nC;
    ComicListRecyclerViewAdapter nD;
    ArrayList<ComicListObject> nE;
    ArrayList<String> nF;
    String[] nG;
    boolean[] nH;
    private String nI;
    private String nJ;
    private String nK;
    private String nL;
    private String nM;
    int nN;
    int nO;
    boolean nP;
    boolean nQ;
    int page;
    @BindView(2131296760)
    RecyclerView recyclerView_comicList;
    private String tags;
    @BindView(2131296897)
    TextView textView_totalPage;
    @BindView(2131297037)
    Toolbar toolbar;
    int totalPage;
    public boolean[] nA = {false, false, false, false, false, false, false, false};
    int nR = 0;
    int nS = 0;

    public static ComicListFragment a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        ComicListFragment comicListFragment = new ComicListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("CATEGORY_TITLE", str);
        bundle.putString("KEYWORDS", str2);
        bundle.putString("TAGS", str3);
        bundle.putString("AUTHOR", str4);
        bundle.putString("FINISHED", str5);
        bundle.putString("SORTING", str6);
        bundle.putString("TRANSLATE", str7);
        bundle.putString("CREATOR_ID", str8);
        bundle.putString("CREATOR_NAME", str9);
        comicListFragment.setArguments(bundle);
        return comicListFragment;
    }

    @Override // com.picacomic.fregata.a.b
    public void C(int i) {
        getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicDetailFragment.a(this.nE.get(i - (i / 21))), ComicDetailFragment.TAG).addToBackStack(ComicDetailFragment.TAG).commit();
    }

    @Override // com.picacomic.fregata.a.b
    public void I(int i) {
        int i2 = i - (i / 21);
        if (this.nE == null || this.nE.size() <= i2 || this.nE.get(i2) == null || this.nE.get(i2).getThumb() == null) {
            return;
        }
        D(g.b(this.nE.get(i2).getThumb()));
    }

    public boolean K(int i) {
        if (this.nA[i]) {
            this.buttons_filters[i].setBackgroundResource(nz[i]);
            this.nA[i] = false;
            e.a(getActivity(), i, false);
            Context context = getContext();
            Toast.makeText(context, getString(2131689991) + this.buttons_filters[i].getText().toString() + getString(2131689992), 0).show();
            return false;
        }
        this.buttons_filters[i].setBackgroundResource(2131230836);
        this.nA[i] = true;
        e.a(getActivity(), i, true);
        Context context2 = getContext();
        Toast.makeText(context2, getString(2131689991) + this.buttons_filters[i].getText().toString() + getString(2131689990), 0).show();
        return true;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, this.nI, true);
        String string = getString(2131689984);
        if (this.nJ != null) {
            if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
                ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
            }
            a(this.toolbar, this.nI, true);
            this.toolbar.setTitle(string + this.nJ);
            this.nL = c.uR[this.nS];
        } else if (this.tags != null) {
            this.toolbar.setTitle(string + this.tags);
        } else if (this.author != null) {
            this.toolbar.setTitle(string + this.author);
        } else if (this.nM != null) {
            this.toolbar.setTitle(string + this.nM);
        } else if (this.creatorId != null) {
            this.toolbar.setTitle(string + this.creatorName);
        } else if (this.nI != null && this.nI.equals("CATEGORY_RANDOM")) {
            this.toolbar.setTitle(2131689643);
        } else if (this.nI != null && this.nI.equals("CATEGORY_USER_FAVOURITE")) {
            if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
                ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
            }
            a(this.toolbar, this.nI, true);
            this.toolbar.setTitle(2131689628);
        } else if (this.nI != null && this.nI.equals("CATEGORY_RECENT_VIEW")) {
            if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
                ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
            }
            a(this.toolbar, this.nI, true);
            this.toolbar.setTitle(2131689874);
        } else if (this.nI != null && this.nI.equals("CATEGORY_DOWNLOADED")) {
            this.toolbar.setTitle(2131689786);
        } else if (this.nI != null) {
            if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
                ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
            }
            a(this.toolbar, this.nI, true);
            this.toolbar.setTitle(this.nI + "");
        } else if (this.nL != null && this.nL.equalsIgnoreCase("dd")) {
            this.toolbar.setTitle(2131689637);
        }
        for (int i = 0; i < 8; i++) {
            this.nD.a(i, this.nA[i]);
            if (this.nA[i]) {
                this.buttons_filters[i].setBackgroundResource(2131230836);
            } else {
                this.buttons_filters[i].setBackgroundResource(nz[i]);
            }
        }
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        if (this.kE) {
            if (this.nI != null && this.nI.equals("CATEGORY_RANDOM")) {
                cR();
            } else if (this.nI != null && this.nI.equals("CATEGORY_USER_FAVOURITE")) {
                cV();
            } else if (this.nI != null && this.nI.equals("CATEGORY_RECENT_VIEW")) {
                cS();
            } else if (this.nI == null || !this.nI.equals("CATEGORY_DOWNLOADED")) {
                cW();
            } else {
                cT();
            }
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (isAdded()) {
            if (this.nE == null || (this.nE != null && this.nE.size() == 0)) {
                Picasso.with(getActivity()).load(2131230946).into(this.imageView_empty);
                this.frameLayout_noComics.setVisibility(0);
            } else {
                this.frameLayout_noComics.setVisibility(8);
            }
            if (this.nI != null && this.nI.equals("CATEGORY_RECENT_VIEW")) {
                long count = DbComicViewRecordObject.count(DbComicViewRecordObject.class);
                long j = count / ny;
                long j2 = j;
                if (count % ny != 0) {
                    j2 = j + 1;
                }
                TextView textView = this.textView_totalPage;
                textView.setText(j2 + "");
            } else if (this.nI == null || !this.nI.equals("CATEGORY_DOWNLOADED")) {
                TextView textView2 = this.textView_totalPage;
                textView2.setText(this.totalPage + "");
            } else {
                long count2 = DbComicDetailObject.count(DbComicDetailObject.class, "download_status > 0", null);
                long j3 = count2 / ny;
                long j4 = j3;
                if (count2 % ny != 0) {
                    j4 = j3 + 1;
                }
                TextView textView3 = this.textView_totalPage;
                textView3.setText(j4 + "");
            }
            this.nD.notifyDataSetChanged();
        }
    }

    public void cR() {
        C(getResources().getString(2131689824));
        this.nC = new d(getContext()).dO().ao(e.z(getActivity()));
        this.nC.enqueue(new Callback<GeneralResponse<ComicRandomListResponse>>() { // from class: com.picacomic.fregata.fragments.ComicListFragment.11
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ComicRandomListResponse>> call, Throwable th) {
                th.printStackTrace();
                ComicListFragment.this.bC();
                new com.picacomic.fregata.b.c(ComicListFragment.this.getActivity()).dN();
                ComicListFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ComicRandomListResponse>> call, Response<GeneralResponse<ComicRandomListResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getComics() != null) {
                        for (int i = 0; i < response.body().data.getComics().size(); i++) {
                            ComicListFragment.this.nE.add(response.body().data.getComics().get(i));
                        }
                    }
                } else {
                    try {
                        new com.picacomic.fregata.b.c(ComicListFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ComicListFragment.this.bC();
                ComicListFragment.this.bI();
            }
        });
    }

    public void cS() {
        List findWithQuery = DbComicViewRecordObject.findWithQuery(DbComicViewRecordObject.class, "SELECT * FROM db_comic_view_record_object WHERE last_view_timestamp > 0 ORDER BY last_view_timestamp DESC LIMIT ? OFFSET ?", ny + "", this.nN + "");
        if (findWithQuery == null || this.nE == null) {
            return;
        }
        for (int i = 0; i < findWithQuery.size(); i++) {
            List find = DbComicDetailObject.find(DbComicDetailObject.class, "comic_id = ?", ((DbComicViewRecordObject) findWithQuery.get(i)).getComicId());
            if (find != null && find.size() > 0) {
                this.nE.add(new ComicListObject((DbComicDetailObject) find.get(0)));
                f.D(TAG, "recent View " + i + " = " + ((DbComicViewRecordObject) findWithQuery.get(i)).toString());
            }
        }
        this.nN = this.nE.size();
        bI();
    }

    public void cT() {
        List findWithQuery = DbComicDetailObject.findWithQuery(DbComicDetailObject.class, "SELECT * FROM db_comic_detail_object WHERE download_status > 0 ORDER BY downloaded_at DESC LIMIT ? OFFSET ?", ny + "", this.nO + "");
        if (findWithQuery == null || this.nE == null) {
            return;
        }
        for (int i = 0; i < findWithQuery.size(); i++) {
            this.nE.add(new ComicListObject((DbComicDetailObject) findWithQuery.get(i)));
            f.D(TAG, "recent View " + i + " = " + ((DbComicDetailObject) findWithQuery.get(i)).toString());
        }
        this.nO = this.nE.size();
        bI();
    }

    public void cU() {
        DbComicViewRecordObject.deleteAll(DbComicViewRecordObject.class);
        if (this.nE != null) {
            this.nE.clear();
            this.nD.notifyDataSetChanged();
            Toast.makeText(getContext(), 2131689550, 0).show();
        }
    }

    public void cV() {
        if (this.totalPage < this.page || this.nP) {
            return;
        }
        this.nP = true;
        C(getResources().getString(2131689824));
        this.nB = new d(getContext()).dO().a(e.z(getActivity()), c.uQ[this.nR], this.page);
        this.nB.enqueue(new Callback<GeneralResponse<ComicListResponse>>() { // from class: com.picacomic.fregata.fragments.ComicListFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ComicListResponse>> call, Throwable th) {
                th.printStackTrace();
                ComicListFragment.this.bC();
                new com.picacomic.fregata.b.c(ComicListFragment.this.getActivity()).dN();
                ComicListFragment.this.bI();
                ComicListFragment.this.nP = false;
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ComicListResponse>> call, Response<GeneralResponse<ComicListResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getComics() != null && response.body().data.getComics().getDocs() != null) {
                        int unused = ComicListFragment.ny = response.body().data.getComics().getLimit();
                        if (ComicListFragment.this.nQ) {
                            if (ComicListFragment.this.nE != null) {
                                ComicListFragment.this.nE.clear();
                            } else {
                                ComicListFragment.this.nE = new ArrayList<>();
                            }
                            ComicListFragment.this.nQ = false;
                        }
                        for (int i = 0; i < response.body().data.getComics().getDocs().size(); i++) {
                            ComicListFragment.this.nE.add(response.body().data.getComics().getDocs().get(i));
                        }
                        ComicListFragment.this.totalPage = response.body().data.getComics().getPages();
                        ComicListFragment.this.page++;
                    }
                } else {
                    try {
                        new com.picacomic.fregata.b.c(ComicListFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ComicListFragment.this.bC();
                ComicListFragment.this.bI();
                ComicListFragment.this.nP = false;
            }
        });
    }

    public void cW() {
        if (this.totalPage < this.page || this.nP) {
            return;
        }
        this.nP = true;
        C(getResources().getString(2131689824));
        d dVar = new d(getContext());
        if (this.nJ != null) {
            this.nB = dVar.dO().a(e.z(getActivity()), this.page, new SortingBody(this.nJ, this.nL, this.nF));
        } else {
            this.nB = dVar.dO().a(e.z(getActivity()), this.page, this.nI, this.tags, this.author, this.nK, this.nL, this.nM, this.creatorId);
        }
        this.nB.enqueue(new Callback<GeneralResponse<ComicListResponse>>() { // from class: com.picacomic.fregata.fragments.ComicListFragment.3
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ComicListResponse>> call, Throwable th) {
                th.printStackTrace();
                ComicListFragment.this.bC();
                new com.picacomic.fregata.b.c(ComicListFragment.this.getActivity()).dN();
                ComicListFragment.this.bI();
                ComicListFragment.this.nP = false;
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ComicListResponse>> call, Response<GeneralResponse<ComicListResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getComics() != null && response.body().data.getComics().getDocs() != null) {
                        int unused = ComicListFragment.ny = response.body().data.getComics().getLimit();
                        if (ComicListFragment.this.nQ) {
                            if (ComicListFragment.this.nE != null) {
                                ComicListFragment.this.nE.clear();
                            } else {
                                ComicListFragment.this.nE = new ArrayList<>();
                            }
                            ComicListFragment.this.nQ = false;
                        }
                        for (int i = 0; i < response.body().data.getComics().getDocs().size(); i++) {
                            ComicListFragment.this.nE.add(response.body().data.getComics().getDocs().get(i));
                        }
                        ComicListFragment.this.totalPage = response.body().data.getComics().getPages();
                        ComicListFragment.this.page++;
                    }
                } else {
                    try {
                        new com.picacomic.fregata.b.c(ComicListFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ComicListFragment.this.bC();
                ComicListFragment.this.bI();
                ComicListFragment.this.nP = false;
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.recyclerView_comicList.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.nD = new ComicListRecyclerViewAdapter(getActivity(), this.nE, this);
        this.recyclerView_comicList.setAdapter(this.nD);
        if (this.buttons_filters != null) {
            for (int i = 0; i < this.buttons_filters.length; i++) {
                this.buttons_filters[i].setOnClickListener(this);
            }
        } else {
            Toast.makeText(getActivity(), "NULL!!!", 0).show();
        }
        this.recyclerView_comicList.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.picacomic.fregata.fragments.ComicListFragment.9
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (recyclerView.getLayoutManager() == null || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
                    return;
                }
                if (ComicListFragment.this.editText_currentPage != null) {
                    int findFirstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() / 21;
                    EditText editText = ComicListFragment.this.editText_currentPage;
                    editText.setHint((ComicListFragment.this.hP + findFirstVisibleItemPosition) + "");
                    ComicListFragment.this.editText_currentPage.setText("");
                }
                if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == ((LinearLayoutManager) recyclerView.getLayoutManager()).getItemCount() - 1) {
                    if (ComicListFragment.this.nI != null && ComicListFragment.this.nI.equals("CATEGORY_USER_FAVOURITE")) {
                        ComicListFragment.this.cV();
                    } else if (ComicListFragment.this.nI != null && ComicListFragment.this.nI.equals("CATEGORY_RECENT_VIEW")) {
                        ComicListFragment.this.cS();
                    } else if (ComicListFragment.this.nI == null || !ComicListFragment.this.nI.equals("CATEGORY_DOWNLOADED")) {
                        ComicListFragment.this.cW();
                    } else {
                        ComicListFragment.this.cT();
                    }
                }
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
            }
        });
        this.editText_currentPage.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.picacomic.fregata.fragments.ComicListFragment.10
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i2, KeyEvent keyEvent) {
                if (i2 == 6) {
                    try {
                        ComicListFragment.this.hP = Integer.parseInt(textView.getText().toString());
                        ComicListFragment.this.page = ComicListFragment.this.hP;
                    } catch (Exception e) {
                        ComicListFragment.this.hP = ComicListFragment.this.page;
                    }
                    ComicListFragment.this.nQ = true;
                    if (ComicListFragment.this.nI == null || !ComicListFragment.this.nI.equals("CATEGORY_USER_FAVOURITE")) {
                        ComicListFragment.this.cW();
                        return true;
                    }
                    ComicListFragment.this.cV();
                    return true;
                }
                return false;
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        if (getActivity() != null) {
            for (int i = 0; i < 8; i++) {
                this.nA[i] = e.d(getActivity(), i);
            }
        }
        this.kE = false;
        if (this.nE == null) {
            this.nQ = false;
            this.page = 1;
            this.totalPage = 1;
            this.hP = this.page;
            this.nP = false;
            this.nE = new ArrayList<>();
            this.kE = true;
            this.nN = 0;
            this.nO = 0;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = arrayList;
        if (e.D(getContext()) != null) {
            arrayList2 = arrayList;
            if (!e.D(getContext()).equalsIgnoreCase("")) {
                arrayList2 = (ArrayList) new Gson().fromJson(e.D(getContext()), new TypeToken<List<CategoryObject>>() { // from class: com.picacomic.fregata.fragments.ComicListFragment.8
                }.getType());
            }
        }
        if (arrayList2 != null) {
            this.nG = new String[arrayList2.size()];
            this.nH = new boolean[arrayList2.size()];
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                this.nG[i2] = ((CategoryObject) arrayList2.get(i2)).getTitle();
                this.nH[i2] = false;
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case 2131296328:
                K(2);
                this.nD.a(2, this.nA[2]);
                return;
            case 2131296329:
                K(5);
                this.nD.a(5, this.nA[5]);
                return;
            case 2131296330:
                K(0);
                this.nD.a(0, this.nA[0]);
                return;
            case 2131296331:
                K(6);
                this.nD.a(6, this.nA[6]);
                return;
            case 2131296332:
                K(3);
                this.nD.a(3, this.nA[3]);
                return;
            case 2131296333:
                K(1);
                this.nD.a(1, this.nA[1]);
                return;
            case 2131296334:
                K(4);
                this.nD.a(4, this.nA[4]);
                return;
            case 2131296335:
                K(7);
                this.nD.a(7, this.nA[7]);
                return;
            default:
                return;
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.nI = getArguments().getString("CATEGORY_TITLE", null);
            this.nJ = getArguments().getString("KEYWORDS", null);
            this.tags = getArguments().getString("TAGS", null);
            this.author = getArguments().getString("AUTHOR", null);
            this.nK = getArguments().getString("FINISHED", null);
            this.nL = getArguments().getString("SORTING", null);
            this.nM = getArguments().getString("TRANSLATE", null);
            this.creatorId = getArguments().getString("CREATOR_ID", null);
            this.creatorName = getArguments().getString("CREATOR_NAME", null);
            String str = TAG;
            f.D(str, "CAT=" + this.nI + "KEY=" + this.nJ + "TAG=" + this.tags + "AUT=" + this.author + "FIN=" + this.nK + "SOR=" + this.nL);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.nJ != null) {
            menuInflater.inflate(2131492867, menu);
            menu.getItem(0).setShowAsAction(2);
            menu.getItem(1).setShowAsAction(2);
        } else if (this.nI != null && this.nI.equals("CATEGORY_RECENT_VIEW")) {
            menuInflater.inflate(2131492866, menu);
        } else if ((this.nI != null && this.nI.equals("CATEGORY_USER_FAVOURITE")) || (this.nI != null && this.nL != null && this.nL.equalsIgnoreCase("dd"))) {
            menuInflater.inflate(2131492868, menu);
            menu.getItem(0).setShowAsAction(2);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427406, viewGroup, false);
        setHasOptionsMenu(true);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.nB != null) {
            this.nB.cancel();
        }
        if (this.nC != null) {
            this.nC.cancel();
        }
        super.onDetach();
    }

    @Override // android.support.v4.app.Fragment
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        if (menuItem.getItemId() == 2131296271) {
            AlertDialogCenter.showCustomAlertDialog(getContext(), 2131230947, 2131689551, 2131689549, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ComicListFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ComicListFragment.this.cU();
                }
            }, (View.OnClickListener) null);
        }
        if (menuItem.getItemId() == 2131296287) {
            if (this.nI == null || !this.nI.equals("CATEGORY_USER_FAVOURITE")) {
                AlertDialogCenter.sortingAdvancedOptions(getContext(), this.nS, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ComicListFragment.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ComicListFragment.this.nS = i;
                        ComicListFragment.this.page = 1;
                        ComicListFragment.this.totalPage = 1;
                        String[] stringArray = ComicListFragment.this.getResources().getStringArray(2130903054);
                        ComicListFragment.this.nL = c.uR[ComicListFragment.this.nS];
                        menuItem.setTitle(stringArray[i]);
                        ComicListFragment.this.nE.clear();
                        ComicListFragment.this.cW();
                        dialogInterface.dismiss();
                    }
                });
            } else {
                AlertDialogCenter.sortingFavouriteOptions(getContext(), this.nR, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ComicListFragment.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ComicListFragment.this.nR = i;
                        ComicListFragment.this.page = 1;
                        ComicListFragment.this.totalPage = 1;
                        menuItem.setTitle(ComicListFragment.this.getResources().getStringArray(2130903055)[i]);
                        ComicListFragment.this.nE.clear();
                        ComicListFragment.this.cV();
                        dialogInterface.dismiss();
                    }
                });
            }
        }
        if (menuItem.getItemId() == 2131296270) {
            AlertDialogCenter.sortingAdvancedCategoriesOptions(getContext(), this.nG, this.nH, new DialogInterface.OnMultiChoiceClickListener() { // from class: com.picacomic.fregata.fragments.ComicListFragment.6
                @Override // android.content.DialogInterface.OnMultiChoiceClickListener
                public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                    int i2;
                    ComicListFragment.this.nH[i] = z;
                    if (z) {
                        if (ComicListFragment.this.nF == null) {
                            ComicListFragment.this.nF = new ArrayList<>();
                        }
                        ComicListFragment.this.nF.add(ComicListFragment.this.nG[i]);
                    } else if (ComicListFragment.this.nF == null || ComicListFragment.this.nF.size() <= 0) {
                    } else {
                        int i3 = 0;
                        int i4 = -1;
                        while (true) {
                            int i5 = i4;
                            if (i3 >= ComicListFragment.this.nF.size()) {
                                ComicListFragment.this.nF.remove(i5);
                                return;
                            }
                            int i6 = 0;
                            while (true) {
                                i2 = i5;
                                if (i6 >= ComicListFragment.this.nG.length) {
                                    break;
                                } else if (ComicListFragment.this.nF.get(i3).equalsIgnoreCase(ComicListFragment.this.nG[i6])) {
                                    i2 = i3;
                                    break;
                                } else {
                                    i6++;
                                }
                            }
                            i3++;
                            i4 = i2;
                        }
                    }
                }
            }, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ComicListFragment.7
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    ComicListFragment.this.page = 1;
                    ComicListFragment.this.totalPage = 1;
                    ComicListFragment.this.nE.clear();
                    ComicListFragment.this.cW();
                    dialogInterface.dismiss();
                }
            });
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
