package com.picacomic.fregata.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.R;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.just.agentweb.al;
import com.picacomic.fregata.a.d;
import com.picacomic.fregata.adapters.c;
import com.picacomic.fregata.fragments.ComicViewFragment;
import com.picacomic.fregata.fragments.ComicViewerListFragment;
import com.picacomic.fregata.objects.ComicEpisodeObject;
import com.picacomic.fregata.objects.ComicPageObject;
import com.picacomic.fregata.objects.databaseTable.DbComicViewRecordObject;
import com.picacomic.fregata.objects.databaseTable.DownloadComicEpisodeObject;
import com.picacomic.fregata.objects.databaseTable.DownloadComicPageObject;
import com.picacomic.fregata.objects.responses.DataClass.ComicEpisodeResponse.ComicEpisodeResponse;
import com.picacomic.fregata.objects.responses.DataClass.ComicPageResponse.ComicPagesResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.b;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.VerticalSeekBar;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/activities/ComicViewerActivity.class */
public class ComicViewerActivity extends BaseActivity implements d {
    public static final String TAG = "ComicViewerActivity";
    public static int hq = 40;
    @BindView(2131296336)
    Button button_autoPaging;
    @BindView(2131296337)
    Button button_comment;
    @BindView(2131296338)
    Button button_dialogAutoPagingStart;
    @BindView(2131296339)
    Button button_download;
    @BindView(2131296340)
    Button button_hide;
    @BindView(2131296341)
    Button button_hint;
    @BindView(2131296342)
    Button button_nextEpisode;
    @BindView(2131296343)
    Button button_nextPageBottom;
    @BindView(2131296344)
    Button button_nextPageRight;
    @BindView(2131296345)
    Button button_nightMode;
    @BindView(2131296346)
    Button button_panel;
    @BindView(2131296347)
    Button button_panelLeftCorner;
    @BindView(2131296348)
    Button button_previousEpisode;
    @BindView(2131296349)
    Button button_previousPage;
    @BindView(2131296350)
    Button button_screenOrientation;
    @BindView(2131296351)
    Button button_scrollOrientation;
    @BindView(2131296352)
    Button button_selectEpisode;
    @BindView(2131296353)
    Button button_setting;
    @BindView(2131296354)
    Button button_share;
    @BindView(2131296355)
    Button button_twoPageViewMode;
    @BindView(2131296417)
    CheckBox checkBox_brightnessSystem;
    public String comicId;
    int currentPage;
    public int episodeOrder;
    public int episodeTotal;
    @BindView(2131296516)
    FrameLayout frameLayout_gestureArea;
    @BindView(2131296517)
    FrameLayout frameLayout_nightModeMask;
    @BindView(2131296533)
    GridView gridView_episodeDialog;
    c hA;
    boolean hB;
    boolean hC;
    boolean hD;
    boolean hE;
    public int hF;
    public int hG;
    public int hH;
    public int hI;
    public int hJ;
    public ComicEpisodeObject hK;
    public String hL;
    int hM;
    int hN;
    int hO;
    int hP;
    boolean hQ;
    boolean hR;
    boolean hS;
    boolean hT;
    boolean hU;
    boolean hV;
    CountDownTimer hX;
    CountDownTimer hY;
    Call<GeneralResponse<ComicPagesResponse>> hZ;
    Animation hr;
    Animation hs;
    Animation ht;
    Animation hu;
    Animation hv;
    Animation hw;
    Animation hx;
    Animation hy;
    com.picacomic.fregata.a.c hz;
    Call<GeneralResponse<ComicEpisodeResponse>> ia;
    View.OnClickListener ib;
    View.OnClickListener ic;
    SeekBar.OnSeekBarChangeListener ie;

    /* renamed from: if  reason: not valid java name */
    public ArrayList<ComicPageObject> f1if;
    ArrayList<ComicEpisodeObject> ig;
    @BindView(2131296554)
    ImageButton imageButton_back;
    @BindView(2131296665)
    LinearLayout linearLayout_bottomPanel;
    @BindView(2131296666)
    LinearLayout linearLayout_dialogAutoPaging;
    @BindView(2131296667)
    LinearLayout linearLayout_horizontalPagingScrollbar;
    @BindView(2131296668)
    LinearLayout linearLayout_rightPanel;
    @BindView(2131296670)
    LinearLayout linearLayout_verticalPagingScrollbar;
    @BindView(2131296773)
    RelativeLayout relativeLayout_leftPanel;
    @BindView(2131296774)
    RelativeLayout relativeLayout_toolbar;
    @BindView(2131296804)
    SeekBar seekBar_dialogAutoPaging;
    @BindView(2131296805)
    SeekBar seekBar_horizontalPaging;
    @BindView(2131296806)
    SeekBar seekBar_verticalPaging;
    @BindView(2131296901)
    TextView textView_dialogAutoPagingTitle;
    @BindView(2131296902)
    TextView textView_horizontalPage;
    @BindView(2131296903)
    TextView textView_page;
    @BindView(2131296904)
    TextView textView_title;
    @BindView(2131296905)
    TextView textView_verticalPage;
    @BindView(2131297049)
    VerticalSeekBar verticalSeekBar_brightness;
    String hW = "不明";
    private BroadcastReceiver ih = new BroadcastReceiver() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                int intExtra = intent.getIntExtra("level", 0);
                ComicViewerActivity comicViewerActivity = ComicViewerActivity.this;
                comicViewerActivity.hW = String.valueOf(intExtra) + "%";
            } catch (Exception e) {
                e.printStackTrace();
                if (ComicViewerActivity.this != null) {
                    Toast.makeText(ComicViewerActivity.this, "cannot parse battery level.", 0).show();
                }
            }
        }
    };
    private MediaPlayer ii = null;

    public void a(com.picacomic.fregata.a.c cVar) {
        this.hz = cVar;
    }

    public void b(int i, int i2, boolean z) {
        if (j(i)) {
            c(i, i2, z);
        } else {
            d(i, i2, z);
        }
    }

    public void bF() {
        if (this.ig == null) {
            this.ig = new ArrayList<>();
        }
        this.hA = new c(this, this.ig);
        this.gridView_episodeDialog.setAdapter((ListAdapter) this.hA);
        this.gridView_episodeDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.12
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (ComicViewerActivity.this.ig == null || ComicViewerActivity.this.ig.size() <= i) {
                    return;
                }
                ComicViewerActivity.this.episodeOrder = ComicViewerActivity.this.ig.get(i).getOrder();
                ComicViewerActivity.this.b(ComicViewerActivity.this.episodeOrder, 0, true);
            }
        });
        this.gridView_episodeDialog.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.23
            int im;

            /* renamed from: io  reason: collision with root package name */
            int f6io;
            int ip;

            private void s(int i) {
                if (this.im < this.ip - this.f6io || i != 0) {
                    return;
                }
                ComicViewerActivity.this.bN();
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                this.im = i;
                this.f6io = i2;
                this.ip = i3;
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
                s(i);
            }
        });
        this.imageButton_back.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.26
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ComicViewerActivity.this.onBackPressed();
            }
        });
        this.button_hint.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ComicViewerActivity.this.k(8);
                ComicViewerActivity.this.l(0);
                e.c((Context) ComicViewerActivity.this, true);
            }
        });
        this.button_share.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(ComicViewerActivity.this, "Download and Share Image", 0).show();
            }
        });
        this.button_download.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.29
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Toast.makeText(ComicViewerActivity.this, "Download Current Image", 0).show();
            }
        });
        this.button_screenOrientation.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.30
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComicViewerActivity.this.hB) {
                    ComicViewerActivity.this.hB = false;
                    ComicViewerActivity.this.h(false);
                } else {
                    ComicViewerActivity.this.hB = true;
                    ComicViewerActivity.this.h(true);
                }
                e.e(ComicViewerActivity.this, ComicViewerActivity.this.hB);
            }
        });
        this.button_scrollOrientation.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComicViewerActivity.this.hC) {
                    ComicViewerActivity.this.hC = false;
                    ComicViewerActivity.this.i(false);
                } else {
                    ComicViewerActivity.this.hC = true;
                    ComicViewerActivity.this.i(true);
                }
                e.f(ComicViewerActivity.this, ComicViewerActivity.this.hC);
            }
        });
        this.button_autoPaging.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComicViewerActivity.this.linearLayout_dialogAutoPaging.getVisibility() == 0) {
                    ComicViewerActivity.this.linearLayout_dialogAutoPaging.setVisibility(8);
                    return;
                }
                if (ComicViewerActivity.this.gridView_episodeDialog.getVisibility() == 0) {
                    ComicViewerActivity.this.gridView_episodeDialog.setVisibility(8);
                }
                ComicViewerActivity.this.linearLayout_dialogAutoPaging.setVisibility(0);
            }
        });
        this.button_dialogAutoPagingStart.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ComicViewerActivity.this.k(8);
                ComicViewerActivity.this.bO();
            }
        });
        this.button_setting.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(ComicViewerActivity.this, PopupActivity.class);
                intent.putExtra("EXTRA_KEY_TYPE", "TYPE_KEY_SETTING");
                ComicViewerActivity.this.startActivity(intent);
            }
        });
        this.button_hide.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ComicViewerActivity.this.k(8);
            }
        });
        this.button_selectEpisode.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComicViewerActivity.this.gridView_episodeDialog.getVisibility() == 0) {
                    ComicViewerActivity.this.gridView_episodeDialog.setVisibility(8);
                    return;
                }
                if (ComicViewerActivity.this.linearLayout_dialogAutoPaging.getVisibility() == 0) {
                    ComicViewerActivity.this.linearLayout_dialogAutoPaging.setVisibility(8);
                }
                ComicViewerActivity.this.gridView_episodeDialog.setVisibility(0);
                ComicViewerActivity.this.bN();
            }
        });
        this.button_nightMode.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComicViewerActivity.this.hE) {
                    ComicViewerActivity.this.k(false);
                } else {
                    ComicViewerActivity.this.k(true);
                }
            }
        });
        this.button_comment.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComicViewerActivity.this.comicId != null) {
                    Intent intent = new Intent(ComicViewerActivity.this, PopupActivity.class);
                    intent.putExtra("EXTRA_KEY_COMIC_ID", ComicViewerActivity.this.comicId);
                    intent.putExtra("EXTRA_KEY_TYPE", "TYPE_KEY_COMMENT");
                    ComicViewerActivity.this.startActivity(intent);
                }
            }
        });
        this.checkBox_brightnessSystem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ComicViewerActivity.this.j(z);
            }
        });
        this.ib = new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ComicViewerActivity.this.k(0);
                ComicViewerActivity.this.bP();
            }
        };
        this.button_panel.setOnClickListener(this.ib);
        this.button_panelLeftCorner.setOnClickListener(this.ib);
        this.verticalSeekBar_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.11
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                ComicViewerActivity.this.m(i);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.ie = new SeekBar.OnSeekBarChangeListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.13
            int ik;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                this.ik = i;
                if (z) {
                    ComicViewerActivity.this.currentPage = this.ik;
                    ComicViewerActivity.this.hz.b(this.ik, false);
                    ComicViewerActivity.this.r(ComicViewerActivity.this.currentPage);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        };
        this.seekBar_horizontalPaging.setOnSeekBarChangeListener(this.ie);
        this.seekBar_verticalPaging.setOnSeekBarChangeListener(this.ie);
        this.seekBar_dialogAutoPaging.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.14
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                ComicViewerActivity.this.hM = (i * 100) + 1000;
                e.b((Context) ComicViewerActivity.this, ComicViewerActivity.this.hM);
                float f = ComicViewerActivity.this.hM / 1000.0f;
                TextView textView = ComicViewerActivity.this.textView_dialogAutoPagingTitle;
                textView.setText(ComicViewerActivity.this.getResources().getString(2131689733) + " 【 " + String.format("%.1f", Float.valueOf(f)) + ComicViewerActivity.this.getResources().getString(2131689894) + " 】");
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        this.ic = new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ComicViewerActivity.this.bR();
            }
        };
        this.button_nextPageBottom.setOnClickListener(this.ic);
        this.button_nextPageRight.setOnClickListener(this.ic);
        this.button_nextEpisode.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComicViewerActivity.this.episodeOrder >= ComicViewerActivity.this.episodeTotal) {
                    ComicViewerActivity.this.p(8);
                    Toast.makeText(ComicViewerActivity.this, 2131689732, 0).show();
                    return;
                }
                ComicViewerActivity.this.episodeOrder++;
                ComicViewerActivity.this.b(ComicViewerActivity.this.episodeOrder, 0, true);
            }
        });
        this.button_previousEpisode.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ComicViewerActivity.this.episodeOrder <= 1) {
                    ComicViewerActivity.this.q(8);
                    Toast.makeText(ComicViewerActivity.this, 2131689731, 0).show();
                    return;
                }
                ComicViewerActivity.this.episodeOrder--;
                ComicViewerActivity.this.b(ComicViewerActivity.this.episodeOrder, 0, true);
            }
        });
        this.button_previousPage.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ComicViewerActivity.this.bS();
            }
        });
        this.frameLayout_gestureArea.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ComicViewerActivity.this.l(4);
                e.c((Context) ComicViewerActivity.this, false);
            }
        });
    }

    public void bG() {
        this.currentPage = 0;
        this.hF = 0;
        this.hG = 1;
        this.hP = 0;
        this.hS = false;
        this.hQ = false;
        this.hU = false;
    }

    public void bH() {
        k(8);
        h(this.hB);
        i(this.hC);
        k(this.hE);
        if (this.hM - 1000 < 0) {
            this.seekBar_dialogAutoPaging.setProgress(0);
        } else {
            this.seekBar_dialogAutoPaging.setProgress((this.hM - 1000) / 100);
        }
        if (e.K(this)) {
            k(8);
            l(0);
            return;
        }
        k(8);
        l(4);
    }

    public void bI() {
        p(8);
        q(8);
        n(this.currentPage);
        if (this.hK != null) {
            TextView textView = this.textView_title;
            textView.setText("〖" + this.hK.getTitle() + "〗 ");
            SpannableString spannableString = new SpannableString(this.hL);
            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(al.a.white)), 0, spannableString.length(), 33);
            this.textView_title.append(spannableString);
        }
    }

    public void bJ() {
        if (Build.VERSION.SDK_INT < 21 || ContextCompat.checkSelfPermission(this, "android.permission.WRITE_SETTINGS") == 0) {
            return;
        }
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_SETTINGS"}, 2001);
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_SETTINGS")) {
            Toast.makeText(this, "To ", 0).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_SETTINGS"}, 2001);
        }
    }

    public void bK() {
        this.hr = AnimationUtils.loadAnimation(this, 2130771984);
        this.hs = AnimationUtils.loadAnimation(this, 2130771985);
        this.ht = AnimationUtils.loadAnimation(this, 2130771986);
        this.hu = AnimationUtils.loadAnimation(this, 2130771987);
        this.hv = AnimationUtils.loadAnimation(this, 2130771988);
        this.hw = AnimationUtils.loadAnimation(this, 2130771989);
        this.hx = AnimationUtils.loadAnimation(this, 2130771982);
        this.hy = AnimationUtils.loadAnimation(this, 2130771983);
    }

    public void bL() {
        if (j(this.episodeOrder)) {
            c(this.episodeOrder, this.hF, false);
        } else {
            d(this.episodeOrder, this.hF, false);
        }
    }

    public void bM() {
        f.D(TAG, "Call Comic Page ?");
        if (bT() <= 0 || this.hR) {
            return;
        }
        this.hR = true;
        C(getResources().getString(2131689824));
        this.hZ = new com.picacomic.fregata.b.d(this).dO().a(e.z(this), this.comicId, this.episodeOrder, bT());
        this.hZ.enqueue(new Callback<GeneralResponse<ComicPagesResponse>>() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.21
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ComicPagesResponse>> call, Throwable th) {
                ComicViewerActivity.this.hR = false;
                th.printStackTrace();
                ComicViewerActivity.this.bC();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ComicPagesResponse>> call, Response<GeneralResponse<ComicPagesResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.getPages().toString());
                    if (response.body().data != null && response.body().data.getPages().getDocs() != null) {
                        ComicViewerActivity.hq = response.body().data.getPages().getLimit();
                        ComicViewerActivity.this.hH = response.body().data.getPages().getTotal();
                        ComicViewerActivity.this.f1if.addAll(0, response.body().data.getPages().getDocs());
                        ComicViewerActivity.this.hP -= ComicViewerActivity.hq;
                        f.D(ComicViewerActivity.TAG, "current Page = " + ComicViewerActivity.this.currentPage);
                        f.D(ComicViewerActivity.TAG, "Comic Paging Page = " + ComicViewerActivity.this.hF);
                        f.D(ComicViewerActivity.TAG, "Comic Paging Page Total = " + ComicViewerActivity.this.hG);
                        f.D(ComicViewerActivity.TAG, "jumpingPage = " + ComicViewerActivity.this.hP);
                        f.D(ComicViewerActivity.TAG, "episodeOrder = " + ComicViewerActivity.this.episodeOrder);
                        f.D(ComicViewerActivity.TAG, "episodeTotal = " + ComicViewerActivity.this.episodeTotal);
                        f.D(ComicViewerActivity.TAG, "episodePagingPage = " + ComicViewerActivity.this.hI);
                        f.D(ComicViewerActivity.TAG, "episodePagingPageTotal = " + ComicViewerActivity.this.hJ);
                        ComicViewerActivity.this.hz.a(response.body().data.getPages().getDocs(), ComicViewerActivity.this.hP, false, false);
                        ComicViewerActivity.this.o(ComicViewerActivity.this.f1if.size());
                        ComicViewerActivity.this.bI();
                    }
                } else {
                    try {
                        new com.picacomic.fregata.b.c(ComicViewerActivity.this, response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ComicViewerActivity.this.hR = false;
                ComicViewerActivity.this.bC();
            }
        });
    }

    public void bN() {
        if (this.hI < this.hJ) {
            bA();
            this.ia = new com.picacomic.fregata.b.d(this).dO().b(e.z(this), this.comicId, this.hI + 1);
            this.ia.enqueue(new Callback<GeneralResponse<ComicEpisodeResponse>>() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.22
                @Override // retrofit2.Callback
                public void onFailure(Call<GeneralResponse<ComicEpisodeResponse>> call, Throwable th) {
                    th.printStackTrace();
                    ComicViewerActivity.this.bC();
                    new com.picacomic.fregata.b.c(ComicViewerActivity.this).dN();
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<GeneralResponse<ComicEpisodeResponse>> call, Response<GeneralResponse<ComicEpisodeResponse>> response) {
                    if (response.code() != 200) {
                        try {
                            new com.picacomic.fregata.b.c(ComicViewerActivity.this, response.code(), response.errorBody().string()).dN();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.body().data != null && response.body().data.getEps() != null && response.body().data.getEps().getDocs() != null && response.body().data.getEps().getDocs().size() > 0) {
                        ComicViewerActivity.this.episodeTotal = response.body().data.getEps().getTotal();
                        ComicViewerActivity.this.hI = response.body().data.getEps().getPage();
                        ComicViewerActivity.this.hJ = response.body().data.getEps().getPages();
                        for (int i = 0; i < response.body().data.getEps().getDocs().size(); i++) {
                            ComicViewerActivity.this.ig.add(response.body().data.getEps().getDocs().get(i));
                        }
                        ComicViewerActivity.this.hA.notifyDataSetChanged();
                        f.D(ComicViewerActivity.TAG, ComicViewerActivity.this.ig.size() + "");
                    }
                    ComicViewerActivity.this.bC();
                }
            });
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.picacomic.fregata.activities.ComicViewerActivity$24] */
    public void bO() {
        if (this.hM > 0) {
            this.hX = new CountDownTimer(this.hM, this.hM) { // from class: com.picacomic.fregata.activities.ComicViewerActivity.24
                @Override // android.os.CountDownTimer
                public void onFinish() {
                    if (ComicViewerActivity.this.currentPage < g.ad(ComicViewerActivity.this.f1if.size())) {
                        ComicViewerActivity.this.currentPage++;
                        ComicViewerActivity.this.hz.b(ComicViewerActivity.this.currentPage, false);
                        ComicViewerActivity.this.r(ComicViewerActivity.this.currentPage);
                        ComicViewerActivity.this.bO();
                    }
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }
            }.start();
        }
    }

    public void bP() {
        if (this.hX != null) {
            this.hX.cancel();
            this.hX = null;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.picacomic.fregata.activities.ComicViewerActivity$25] */
    public void bQ() {
        if (this.hY != null) {
            this.hY.cancel();
        }
        this.hY = new CountDownTimer(2000L, 2000L) { // from class: com.picacomic.fregata.activities.ComicViewerActivity.25
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (ComicViewerActivity.this.button_nextEpisode != null) {
                    ComicViewerActivity.this.button_nextEpisode.setAlpha(0.0f);
                }
                if (ComicViewerActivity.this.button_previousEpisode != null) {
                    ComicViewerActivity.this.button_previousEpisode.setAlpha(0.0f);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }
        }.start();
    }

    public void bR() {
        if (this.currentPage < g.ad(this.f1if.size())) {
            this.currentPage++;
            this.hz.b(this.currentPage, false);
            r(this.currentPage);
        }
    }

    public void bS() {
        if (this.currentPage > 0) {
            this.currentPage--;
            this.hz.b(this.currentPage, false);
            r(this.currentPage);
        }
    }

    public int bT() {
        return this.hP / hq;
    }

    public int bU() {
        return (this.hP / hq) * hq;
    }

    public void c(int i, int i2, boolean z) {
        synchronized (this) {
            if (z) {
                bG();
                this.f1if.clear();
            }
            List find = DownloadComicEpisodeObject.find(DownloadComicEpisodeObject.class, "comic_id = ? and episode_order = ?", this.comicId, i + "");
            if (find == null || find.size() <= 0) {
                f.D(TAG, "Load DownloadComicEpisodeObject DB FAILED");
            } else {
                this.hK = ((DownloadComicEpisodeObject) find.get(0)).getComicEpisodeObject();
                this.hH = ((DownloadComicEpisodeObject) find.get(0)).getTotal();
                if (this.hH < hq) {
                    this.hG = 1;
                } else if (this.hH % hq == 0) {
                    this.hG = this.hH / hq;
                } else {
                    this.hG = (this.hH / hq) + 1;
                }
                List list = null;
                if (this.hG > i2) {
                    list = DownloadComicPageObject.findWithQuery(DownloadComicPageObject.class, "SELECT * FROM download_comic_page_object WHERE episode_id = ? LIMIT ? OFFSET ?", this.hK.getEpisodeId(), hq + "", (hq * i2) + "");
                    f.D(TAG, "SIZE = " + list.size() + "LIMIT = " + hq + " OFFSET = " + (hq * i2));
                    this.hF = i2 + 1;
                }
                if (this.f1if == null) {
                    this.f1if = new ArrayList<>();
                }
                if (list == null || list.size() <= 0) {
                    f.D(TAG, "Load DownloadComicPageObjectList DB FAILED");
                } else {
                    ArrayList<ComicPageObject> arrayList = new ArrayList<>();
                    for (int i3 = 0; i3 < list.size(); i3++) {
                        ComicPageObject comicPageObject = ((DownloadComicPageObject) list.get(i3)).getComicPageObject();
                        this.f1if.add(comicPageObject);
                        arrayList.add(comicPageObject);
                    }
                    this.hz.a(arrayList, this.hP, this.hU, z);
                    if (this.hU) {
                        this.hU = false;
                    }
                    o(this.f1if.size());
                    bI();
                }
            }
            f.D(TAG, "current Page = " + this.currentPage);
            f.D(TAG, "Comic Paging Page = " + this.hF);
            f.D(TAG, "Comic Paging Page Total = " + this.hG);
            f.D(TAG, "jumpingPage = " + this.hP);
            f.D(TAG, "episodeOrder = " + this.episodeOrder);
            f.D(TAG, "episodeTotal = " + this.episodeTotal);
            f.D(TAG, "episodePagingPage = " + this.hI);
            f.D(TAG, "episodePagingPageTotal = " + this.hJ);
        }
    }

    public void d(int i, int i2, final boolean z) {
        f.D(TAG, "Call Comic Page ?");
        if (i2 >= this.hG || this.hR) {
            return;
        }
        this.hR = true;
        C(getResources().getString(2131689824));
        com.picacomic.fregata.b.d dVar = new com.picacomic.fregata.b.d(this);
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Call Page api = ");
        sb.append(e.z(this));
        sb.append("  ");
        sb.append(this.comicId);
        sb.append("  ");
        sb.append(i);
        sb.append("  ");
        int i3 = i2 + 1;
        sb.append(i3);
        f.D(str, sb.toString());
        this.hZ = dVar.dO().a(e.z(this), this.comicId, i, i3);
        this.hZ.enqueue(new Callback<GeneralResponse<ComicPagesResponse>>() { // from class: com.picacomic.fregata.activities.ComicViewerActivity.20
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ComicPagesResponse>> call, Throwable th) {
                ComicViewerActivity.this.hR = false;
                th.printStackTrace();
                ComicViewerActivity.this.bC();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ComicPagesResponse>> call, Response<GeneralResponse<ComicPagesResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.getPages().toString());
                    if (response.body().data != null && response.body().data.getPages().getDocs() != null) {
                        ComicViewerActivity.hq = response.body().data.getPages().getLimit();
                        if (z) {
                            ComicViewerActivity.this.bG();
                            ComicViewerActivity.this.f1if.clear();
                        }
                        ComicViewerActivity.this.hH = response.body().data.getPages().getTotal();
                        ComicViewerActivity.this.hF = response.body().data.getPages().getPage();
                        ComicViewerActivity.this.hG = response.body().data.getPages().getPages();
                        ComicViewerActivity.this.hK = response.body().data.getEp();
                        if (ComicViewerActivity.this.f1if == null) {
                            ComicViewerActivity.this.f1if = new ArrayList<>();
                        }
                        for (int i4 = 0; i4 < response.body().data.getPages().getDocs().size(); i4++) {
                            ComicViewerActivity.this.f1if.add(response.body().data.getPages().getDocs().get(i4));
                        }
                        f.D(ComicViewerActivity.TAG, "current Page = " + ComicViewerActivity.this.currentPage);
                        f.D(ComicViewerActivity.TAG, "Comic Paging Page = " + ComicViewerActivity.this.hF);
                        f.D(ComicViewerActivity.TAG, "Comic Paging Page Total = " + ComicViewerActivity.this.hG);
                        f.D(ComicViewerActivity.TAG, "jumpingPage = " + ComicViewerActivity.this.hP);
                        f.D(ComicViewerActivity.TAG, "episodeOrder = " + ComicViewerActivity.this.episodeOrder);
                        f.D(ComicViewerActivity.TAG, "episodeTotal = " + ComicViewerActivity.this.episodeTotal);
                        f.D(ComicViewerActivity.TAG, "episodePagingPage = " + ComicViewerActivity.this.hI);
                        f.D(ComicViewerActivity.TAG, "episodePagingPageTotal = " + ComicViewerActivity.this.hJ);
                        ComicViewerActivity.this.hz.a(response.body().data.getPages().getDocs(), ComicViewerActivity.this.hP, ComicViewerActivity.this.hU, z);
                        if (ComicViewerActivity.this.hU) {
                            ComicViewerActivity.this.hU = false;
                        }
                        ComicViewerActivity.this.o(ComicViewerActivity.this.f1if.size());
                        ComicViewerActivity.this.bI();
                    }
                } else {
                    try {
                        new com.picacomic.fregata.b.c(ComicViewerActivity.this, response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ComicViewerActivity.this.hR = false;
                ComicViewerActivity.this.bC();
            }
        });
    }

    public void h(boolean z) {
        if (this.hz != null) {
            if (z) {
                setRequestedOrientation(7);
                if (this.hz != null) {
                    this.hz.M(7);
                    return;
                }
                return;
            }
            setRequestedOrientation(6);
            if (this.hz != null) {
                this.hz.M(6);
            }
        }
    }

    public void i(boolean z) {
        if (this.hz != null) {
            this.hz.B(z);
            if (z) {
                this.button_scrollOrientation.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(getApplicationContext(), 2131230913), (Drawable) null, (Drawable) null);
                return;
            }
            this.button_scrollOrientation.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(getApplicationContext(), 2131230914), (Drawable) null, (Drawable) null);
        }
    }

    public void init() {
        this.hT = e.Q(this);
        this.hB = e.M(this);
        this.hC = e.N(this);
        this.hE = e.L(this);
        this.hM = e.O(this);
        this.hQ = false;
        this.hR = false;
        this.hS = false;
        this.hF = 0;
        this.hG = 1;
        this.hI = 0;
        this.hJ = 1;
        this.hP = 0;
        this.hU = false;
        this.hV = true;
        this.comicId = getIntent().getStringExtra("EXTRA_KEY_COMIC_ID");
        this.hL = getIntent().getStringExtra("EXTRA_KEY_COMIC_TITLE");
        this.episodeOrder = getIntent().getIntExtra("EXTRA_KEY_LAST_VIEW_EPISODE_ORDER", 1);
        this.episodeTotal = getIntent().getIntExtra("EXTRA_KEY_EPISODE_TOTAL", 1);
        this.currentPage = getIntent().getIntExtra("EXTRA_KEY_LAST_VIEW_PAGE", 0);
        boolean booleanExtra = getIntent().getBooleanExtra("EXTRA_KEY_VIEW_FROM_RECORD", false);
        this.hN = 8;
        DbComicViewRecordObject ax = b.ax(this.comicId);
        if (ax != null && booleanExtra) {
            this.hP = ax.getPage();
            this.episodeOrder = ax.getEpisodeOrder();
            this.episodeTotal = ax.getEpisodeTotal();
            this.hF = bT();
            this.hG = this.hF + 1;
            this.hU = true;
        }
        bK();
        bJ();
    }

    public void j(boolean z) {
        try {
            if (z) {
                Settings.System.putInt(getContentResolver(), "screen_brightness_mode", 1);
                this.checkBox_brightnessSystem.setText(getResources().getString(2131689735));
            } else {
                Settings.System.putInt(getContentResolver(), "screen_brightness_mode", 0);
                this.checkBox_brightnessSystem.setText(getResources().getString(2131689736));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.hD = z;
    }

    public boolean j(int i) {
        String str = this.comicId;
        List find = DownloadComicEpisodeObject.find(DownloadComicEpisodeObject.class, "comic_id = ? and episode_order = ?", str, i + "");
        if (find == null || find.size() <= 0) {
            f.D(TAG, "NO DOWNLOAD EP");
            return false;
        }
        f.D(TAG, "HAVE DOWNLOAD EP");
        return true;
    }

    public void k(int i) {
        bK();
        this.relativeLayout_leftPanel.setVisibility(i);
        this.linearLayout_rightPanel.setVisibility(i);
        this.relativeLayout_toolbar.setVisibility(i);
        this.linearLayout_bottomPanel.setVisibility(i);
        if (i == 0) {
            if (!e.x(this)) {
                this.relativeLayout_leftPanel.startAnimation(this.hr);
                this.linearLayout_rightPanel.startAnimation(this.ht);
                this.relativeLayout_toolbar.startAnimation(this.hv);
                this.linearLayout_bottomPanel.startAnimation(this.hx);
            }
            l(8);
            return;
        }
        if (!e.x(this)) {
            this.relativeLayout_leftPanel.startAnimation(this.hs);
            this.linearLayout_rightPanel.startAnimation(this.hu);
            this.relativeLayout_toolbar.startAnimation(this.hw);
            this.linearLayout_bottomPanel.startAnimation(this.hy);
        }
        this.gridView_episodeDialog.setVisibility(8);
        this.linearLayout_dialogAutoPaging.setVisibility(8);
        l(4);
    }

    public void k(boolean z) {
        if (z) {
            this.frameLayout_nightModeMask.setVisibility(0);
            this.button_nightMode.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(getApplicationContext(), 2131230938), (Drawable) null, (Drawable) null);
            this.button_nightMode.setTextColor(ContextCompat.getColor(this, 2131099704));
            Toast.makeText(this, 2131689757, 0).show();
        } else {
            this.frameLayout_nightModeMask.setVisibility(8);
            this.button_nightMode.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(getApplicationContext(), 2131230917), (Drawable) null, (Drawable) null);
            this.button_nightMode.setTextColor(ContextCompat.getColor(this, al.a.white));
        }
        this.hE = z;
        e.d(this, this.hE);
    }

    public void l(int i) {
        if (i == 4) {
            this.button_nextPageRight.setVisibility(0);
            this.button_nextPageBottom.setVisibility(0);
            this.button_previousPage.setVisibility(0);
            this.button_panel.setVisibility(0);
            this.button_panelLeftCorner.setVisibility(0);
            this.button_nextPageRight.setBackgroundColor(getResources().getColor(2131099806));
            this.button_nextPageBottom.setBackgroundColor(getResources().getColor(2131099806));
            this.button_previousPage.setBackgroundColor(getResources().getColor(2131099806));
            this.button_panel.setBackgroundColor(getResources().getColor(2131099806));
            this.button_panelLeftCorner.setBackgroundColor(getResources().getColor(2131099806));
            this.button_nextPageRight.setText("");
            this.button_nextPageBottom.setText("");
            this.button_previousPage.setText("");
            this.button_panel.setText("");
            this.button_panelLeftCorner.setText("");
            this.frameLayout_gestureArea.setVisibility(8);
        } else if (i == 8) {
            this.button_nextPageRight.setVisibility(8);
            this.button_nextPageBottom.setVisibility(8);
            this.button_previousPage.setVisibility(8);
            this.button_panel.setVisibility(8);
            this.button_panelLeftCorner.setVisibility(8);
            this.button_panel.setText("");
            this.button_panelLeftCorner.setText("");
            this.button_panel.setBackgroundColor(getResources().getColor(2131099806));
            this.button_panelLeftCorner.setBackgroundColor(getResources().getColor(2131099806));
            this.frameLayout_gestureArea.setVisibility(8);
        } else if (i == 0) {
            this.button_nextPageRight.setVisibility(0);
            this.button_nextPageBottom.setVisibility(0);
            this.button_previousPage.setVisibility(0);
            this.button_panel.setVisibility(0);
            this.button_panelLeftCorner.setVisibility(0);
            this.button_nextPageRight.setText(getResources().getString(2131689745));
            this.button_nextPageBottom.setText(getResources().getString(2131689744));
            this.button_previousPage.setText(getResources().getString(2131689748));
            this.button_panel.setText(getResources().getString(2131689753));
            this.button_panelLeftCorner.setText(getResources().getString(2131689753));
            this.button_nextPageRight.setBackgroundColor(getResources().getColor(2131099705));
            this.button_nextPageBottom.setBackgroundColor(getResources().getColor(2131099705));
            this.button_previousPage.setBackgroundColor(getResources().getColor(2131099705));
            this.button_panel.setBackgroundColor(getResources().getColor(2131099747));
            this.button_panelLeftCorner.setBackgroundColor(getResources().getColor(2131099747));
            this.frameLayout_gestureArea.setVisibility(0);
        }
    }

    public void m(int i) {
        try {
            if (Settings.System.getInt(getContentResolver(), "screen_brightness_mode") == 1) {
                this.checkBox_brightnessSystem.setChecked(false);
            }
            Settings.System.putInt(getContentResolver(), "screen_brightness", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.hO = i;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:56:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void n(int r5) {
        /*
            Method dump skipped, instructions count: 607
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.picacomic.fregata.activities.ComicViewerActivity.n(int):void");
    }

    public void o(int i) {
        int ad = g.ad(i);
        this.seekBar_verticalPaging.setMax(ad);
        this.seekBar_horizontalPaging.setMax(ad);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            this.linearLayout_horizontalPagingScrollbar.setVisibility(0);
            this.linearLayout_verticalPagingScrollbar.setVisibility(8);
        } else if (configuration.orientation == 1) {
            this.linearLayout_horizontalPagingScrollbar.setVisibility(8);
            this.linearLayout_verticalPagingScrollbar.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131427355);
        ButterKnife.bind(this);
        setSupportActionBar((Toolbar) findViewById(2131297037));
        if (bundle != null) {
            finish();
            return;
        }
        if (e.w(this)) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ComicViewerListFragment(), ComicViewerListFragment.TAG).commit();
        } else {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new ComicViewFragment(), ComicViewFragment.TAG).commit();
        }
        init();
        bF();
        bH();
    }

    @Override // android.support.v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.hT) {
            if (i == 25) {
                bR();
                return true;
            } else if (i == 24) {
                bS();
                return true;
            }
        }
        if (i == 4) {
            onBackPressed();
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        if (this.hK != null && this.hK.getTitle() != null) {
            String title = this.hK.getTitle();
            b.a(new DbComicViewRecordObject(this.comicId, bU() + this.currentPage, title, this.episodeOrder, this.episodeTotal, System.currentTimeMillis()));
        }
        try {
            if (this.ih != null) {
                unregisterReceiver(this.ih);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onPause();
    }

    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i == 2001 && iArr.length > 0) {
            int i2 = iArr[0];
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        bK();
        try {
            if (this.ih != null) {
                registerReceiver(this.ih, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        String str = "";
        if (this.hK != null) {
            str = "";
            if (this.hK.getTitle() != null) {
                str = this.hK.getTitle();
            }
        }
        b.a(new DbComicViewRecordObject(this.comicId, bU() + this.currentPage, str, this.episodeOrder, this.episodeTotal, System.currentTimeMillis()));
        f.D(TAG, "Save View Record: ");
        super.onSaveInstanceState(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        bP();
        if (this.hZ != null) {
            this.hZ.cancel();
        }
        if (this.ia != null) {
            this.ia.cancel();
        }
        if (this.hx != null) {
            this.hx.cancel();
        }
        if (this.hy != null) {
            this.hy.cancel();
        }
        if (this.hr != null) {
            this.hr.cancel();
        }
        if (this.hs != null) {
            this.hs.cancel();
        }
        if (this.ht != null) {
            this.ht.cancel();
        }
        if (this.hu != null) {
            this.hu.cancel();
        }
        if (this.hv != null) {
            this.hv.cancel();
        }
        if (this.hw != null) {
            this.hw.cancel();
        }
        if (this.hY != null) {
            this.hY.cancel();
        }
        super.onStop();
    }

    public void p(int i) {
        this.button_nextEpisode.setVisibility(i);
        this.button_nextEpisode.setAlpha(1.0f);
        bQ();
    }

    public void q(int i) {
        this.button_previousEpisode.setVisibility(i);
        this.button_previousEpisode.setAlpha(1.0f);
        bQ();
    }

    @Override // com.picacomic.fregata.a.d
    public void r(int i) {
        String str = TAG;
        f.D(str, "Current Page = " + this.currentPage + " pageNumber = " + i);
        if (this.f1if != null) {
            if (this.hB) {
                this.seekBar_verticalPaging.setProgress(i);
            } else {
                this.seekBar_horizontalPaging.setProgress(i);
            }
            n(i);
            this.currentPage = i;
            if (!this.hQ && i != 0) {
                this.hQ = true;
            }
            if (this.currentPage == g.ad(this.f1if.size())) {
                bL();
                q(8);
                if (this.hF == this.hG) {
                    p(0);
                }
            } else if (!this.hQ || i != 0) {
                q(8);
                p(8);
            } else if (bT() > 0) {
                bM();
            } else {
                q(0);
                p(8);
            }
        }
    }
}
