package com.picacomic.fregata.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.activities.BaseActivity;
import com.picacomic.fregata.adapters.EpisodeRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.ComicEpisodeObject;
import com.picacomic.fregata.objects.ComicPageObject;
import com.picacomic.fregata.objects.databaseTable.DbComicDetailObject;
import com.picacomic.fregata.objects.databaseTable.DownloadComicEpisodeObject;
import com.picacomic.fregata.objects.databaseTable.DownloadComicPageObject;
import com.picacomic.fregata.objects.responses.DataClass.ComicEpisodeResponse.ComicEpisodeResponse;
import com.picacomic.fregata.objects.responses.DataClass.ComicPageResponse.ComicPagesResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.services.DownloadService;
import com.picacomic.fregata.utils.b;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ComicDownloadFragment.class */
public class ComicDownloadFragment extends BaseFragment implements k {
    public static final String TAG = "ComicDownloadFragment";
    @BindView(2131296326)
    Button button_download;
    @BindView(2131296327)
    Button button_manage;
    String comicId;
    int episodeTotal;
    String hL;
    Call<GeneralResponse<ComicPagesResponse>> hZ;

    /* renamed from: if  reason: not valid java name */
    public ArrayList<ComicPageObject> f2if;
    ArrayList<ComicEpisodeObject> ig;
    Call<GeneralResponse<ComicEpisodeResponse>> nb;
    EpisodeRecyclerViewAdapter nd;
    int nm;
    private boolean nt;
    @BindView(2131296759)
    RecyclerView recyclerView_episode;
    @BindView(2131296889)
    TextView textView_testBroadcast;
    @BindView(2131297037)
    Toolbar toolbar;
    boolean nk = true;
    boolean nu = false;
    boolean hR = false;
    private final BroadcastReceiver nv = new BroadcastReceiver() { // from class: com.picacomic.fregata.fragments.ComicDownloadFragment.6
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(DownloadService.tN)) {
                intent.getStringExtra("COMIC_ID");
                String stringExtra = intent.getStringExtra("COMIC_NAME");
                String stringExtra2 = intent.getStringExtra("EPISODE_ID");
                String stringExtra3 = intent.getStringExtra("EPISODE_TITLE");
                int intExtra = intent.getIntExtra("PROGRESS_CURRENT", 0);
                int intExtra2 = intent.getIntExtra("PROGRESS_TOTAL", 1);
                if (ComicDownloadFragment.this.ig != null) {
                    int size = ComicDownloadFragment.this.ig.size();
                    int i = 0;
                    while (true) {
                        if (i >= size) {
                            break;
                        }
                        if (ComicDownloadFragment.this.ig != null && ComicDownloadFragment.this.ig.size() > i && ComicDownloadFragment.this.ig.get(i).getEpisodeId().equalsIgnoreCase(stringExtra2)) {
                            if (intExtra != intExtra2) {
                                if (ComicDownloadFragment.this.nd != null && ComicDownloadFragment.this.ig != null && ComicDownloadFragment.this.ig.size() > i) {
                                    ComicDownloadFragment.this.ig.get(i).setTitle(intExtra + "/" + intExtra2);
                                    ComicDownloadFragment.this.nd.notifyItemChanged(i);
                                    break;
                                }
                            } else if (ComicDownloadFragment.this.nd != null && ComicDownloadFragment.this.ig != null && ComicDownloadFragment.this.ig.size() > i) {
                                ComicDownloadFragment.this.ig.get(i).setStatus(2);
                                ComicDownloadFragment.this.ig.get(i).setTitle(stringExtra3);
                                ComicDownloadFragment.this.nd.notifyItemChanged(i);
                                break;
                            }
                        }
                        i++;
                    }
                }
                if (ComicDownloadFragment.this.textView_testBroadcast != null) {
                    ComicDownloadFragment.this.textView_testBroadcast.setVisibility(0);
                    ComicDownloadFragment.this.textView_testBroadcast.setText(stringExtra + "\n" + stringExtra3 + "\n" + intExtra + " / " + intExtra2);
                }
            }
        }
    };

    private void cP() {
        cQ();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadService.tN);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.nv, intentFilter);
        this.nt = true;
    }

    private void cQ() {
        if (this.nt) {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.nv);
            this.nt = false;
        }
    }

    public static ComicDownloadFragment k(String str, String str2) {
        ComicDownloadFragment comicDownloadFragment = new ComicDownloadFragment();
        Bundle bundle = new Bundle();
        bundle.putString("COMIC_ID", str);
        bundle.putString("COMIC_TITLE", str2);
        comicDownloadFragment.setArguments(bundle);
        return comicDownloadFragment;
    }

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        if (this.ig == null || this.ig.size() <= i) {
            return;
        }
        if (this.ig.get(i).isSelected()) {
            this.ig.get(i).setSelected(false);
        } else {
            this.ig.get(i).setSelected(true);
        }
        this.nd.notifyDataSetChanged();
    }

    public void J(int i) {
        String episodeId = this.ig.get(i).getEpisodeId();
        if (b.ay(episodeId) == null) {
            new DownloadComicEpisodeObject(this.comicId, this.ig.get(i), 1).save();
        }
        f.E(TAG, "Start Download Service");
        Intent intent = new Intent(getContext(), DownloadService.class);
        intent.putExtra("COMIC_ID", this.comicId);
        intent.putExtra("EPISODE_ID", episodeId);
        getContext().startService(intent);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        }
        String string = getString(2131689785);
        Toolbar toolbar = this.toolbar;
        a(toolbar, string + " " + this.hL, true);
        bN();
        for (DownloadComicEpisodeObject downloadComicEpisodeObject : DownloadComicEpisodeObject.listAll(DownloadComicEpisodeObject.class)) {
            f.D(TAG, downloadComicEpisodeObject.toString());
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    public void bN() {
        if (!this.nu && this.nk) {
            this.nu = true;
            bA();
            this.nb = new d(getContext()).dO().b(e.z(getActivity()), this.comicId, this.nm);
            this.nb.enqueue(new Callback<GeneralResponse<ComicEpisodeResponse>>() { // from class: com.picacomic.fregata.fragments.ComicDownloadFragment.5
                @Override // retrofit2.Callback
                public void onFailure(Call<GeneralResponse<ComicEpisodeResponse>> call, Throwable th) {
                    th.printStackTrace();
                    ComicDownloadFragment.this.bC();
                    new c(ComicDownloadFragment.this.getActivity()).dN();
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<GeneralResponse<ComicEpisodeResponse>> call, Response<GeneralResponse<ComicEpisodeResponse>> response) {
                    if (response.code() == 200) {
                        if (response.body().data != null && response.body().data.getEps() != null && response.body().data.getEps().getDocs() != null && response.body().data.getEps().getDocs().size() > 0) {
                            ComicDownloadFragment.this.episodeTotal = response.body().data.getEps().getTotal();
                            for (int i = 0; i < response.body().data.getEps().getDocs().size(); i++) {
                                ComicEpisodeObject comicEpisodeObject = response.body().data.getEps().getDocs().get(i);
                                DownloadComicEpisodeObject ay = b.ay(response.body().data.getEps().getDocs().get(i).getEpisodeId());
                                if (ay != null) {
                                    if (ay.getStatus() == 1 || ay.getStatus() == 2 || ay.getStatus() == 3) {
                                        comicEpisodeObject.setStatus(1);
                                    } else if (ay.getStatus() == 4) {
                                        comicEpisodeObject.setStatus(2);
                                    } else {
                                        comicEpisodeObject.setStatus(0);
                                    }
                                }
                                comicEpisodeObject.setSelected(false);
                                ComicDownloadFragment.this.ig.add(comicEpisodeObject);
                            }
                            if (response.body().data.getEps().getTotal() == ComicDownloadFragment.this.ig.size()) {
                                ComicDownloadFragment.this.nk = false;
                            }
                        }
                        if (ComicDownloadFragment.this.nd != null && ComicDownloadFragment.this.recyclerView_episode != null) {
                            ComicDownloadFragment.this.nd.notifyDataSetChanged();
                        }
                        ComicDownloadFragment.this.nm++;
                    } else {
                        try {
                            new c(ComicDownloadFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    ComicDownloadFragment.this.nu = false;
                    ComicDownloadFragment.this.bC();
                }
            });
        }
    }

    public void cO() {
        DbComicDetailObject aw;
        if (this.comicId == null || (aw = b.aw(this.comicId)) == null) {
            return;
        }
        aw.setDownloadStatus(0);
        aw.setDownloadedAt(0L);
        aw.save();
        List find = DownloadComicEpisodeObject.find(DownloadComicEpisodeObject.class, "comic_id = ?", this.comicId);
        if (find != null) {
            for (int i = 0; i < find.size(); i++) {
                g.g(new File(com.picacomic.fregata.utils.c.ec(), ((DownloadComicEpisodeObject) find.get(i)).getEpisodeId()));
                ((DownloadComicEpisodeObject) find.get(i)).delete();
            }
        }
        DownloadComicPageObject.deleteAll(DownloadComicPageObject.class, "comic_id = ?", this.comicId);
        if (this.nd == null || this.ig == null) {
            return;
        }
        for (int i2 = 0; i2 < this.ig.size(); i2++) {
            this.ig.get(i2).setSelected(false);
            this.ig.get(i2).setStatus(0);
        }
        this.nd.notifyDataSetChanged();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.recyclerView_episode.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        this.nd = new EpisodeRecyclerViewAdapter(getActivity(), this.ig, this);
        this.recyclerView_episode.setAdapter(this.nd);
        this.recyclerView_episode.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.picacomic.fregata.fragments.ComicDownloadFragment.2
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (recyclerView == null || recyclerView.getLayoutManager() == null || !(recyclerView.getLayoutManager() instanceof GridLayoutManager) || ComicDownloadFragment.this.ig == null || ComicDownloadFragment.this.ig.size() - 1 != ((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition()) {
                    return;
                }
                ComicDownloadFragment.this.bN();
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
        this.button_download.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ComicDownloadFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i;
                if (ContextCompat.checkSelfPermission(ComicDownloadFragment.this.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
                    if (ComicDownloadFragment.this.getActivity() == null || !(ComicDownloadFragment.this.getActivity() instanceof BaseActivity)) {
                        return;
                    }
                    ((BaseActivity) ComicDownloadFragment.this.getActivity()).requestPermission();
                } else if (ComicDownloadFragment.this.ig != null) {
                    int i2 = 0;
                    int i3 = 0;
                    while (true) {
                        i = i3;
                        if (i2 >= ComicDownloadFragment.this.ig.size()) {
                            break;
                        }
                        int i4 = i;
                        if (ComicDownloadFragment.this.ig.get(i2).isSelected()) {
                            ComicDownloadFragment.this.J(i2);
                            ComicDownloadFragment.this.ig.get(i2).setStatus(1);
                            ComicDownloadFragment.this.ig.get(i2).setSelected(false);
                            i4 = i + 1;
                        }
                        i2++;
                        i3 = i4;
                    }
                    if (i > 0) {
                        DbComicDetailObject aw = b.aw(ComicDownloadFragment.this.comicId);
                        if (aw != null) {
                            aw.setDownloadStatus(4);
                            aw.setDownloadedAt(System.currentTimeMillis());
                            aw.save();
                        } else {
                            f.D(ComicDownloadFragment.TAG, "Some ERROR must occur as DbComicDetailObject must NOT be null!");
                        }
                    }
                    ComicDownloadFragment.this.nd.notifyDataSetChanged();
                }
            }
        });
        this.button_manage.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ComicDownloadFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(ComicDownloadFragment.this.getContext(), "功能暫未開放", 0).show();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        cP();
        this.nm = 1;
        this.ig = new ArrayList<>();
        this.f2if = new ArrayList<>();
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.comicId = getArguments().getString("COMIC_ID", null);
            this.hL = getArguments().getString("COMIC_TITLE", null);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131492865, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427405, viewGroup, false);
        if (this.comicId == null) {
            getFragmentManager().popBackStack();
        }
        setHasOptionsMenu(true);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.nb != null) {
            this.nb.cancel();
        }
        if (this.hZ != null) {
            this.hZ.cancel();
        }
        cQ();
        super.onDetach();
    }

    @Override // android.support.v4.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131296275) {
            AlertDialogCenter.showCustomAlertDialog(getContext(), 2131230947, 2131689559, 2131689558, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ComicDownloadFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ComicDownloadFragment.this.cO();
                }
            }, (View.OnClickListener) null);
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
