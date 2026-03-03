package com.picacomic.fregata.fragments;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.design.R;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import butterknife.BindView;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.GameScreenShotRecyclerViewAdapter;
import com.picacomic.fregata.adapters.GameScreenShotViewPagerAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.GameDetailObject;
import com.picacomic.fregata.objects.ThumbnailObject;
import com.picacomic.fregata.objects.responses.ActionResponse;
import com.picacomic.fregata.objects.responses.DataClass.GameDetailResponse.GameDetailResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import com.picacomic.fregata.utils.views.SnapRecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/GameDetailFragment.class */
public class GameDetailFragment extends BaseFragment implements k {
    public static final String TAG = "GameDetailFragment";
    @BindView(2131296297)
    AppBarLayout appBarLayout;
    @BindView(2131296376)
    Button button_download;
    @BindView(2131296426)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(2131296527)
    FrameLayout frameLayout_banner;
    private String gameId;
    @BindView(2131296560)
    ImageButton imageButton_closePopup;
    @BindView(2131296561)
    ImageButton imageButton_comment;
    @BindView(2131296562)
    ImageButton imageButton_descriptionHeightControl;
    @BindView(2131296563)
    ImageButton imageButton_gift;
    @BindView(2131296564)
    ImageButton imageButton_like;
    @BindView(2131296565)
    ImageButton imageButton_playVideo;
    @BindView(2131296566)
    ImageButton imageButton_versionDescriptionHeightControl;
    @BindView(2131296600)
    ImageView imageView_adult;
    @BindView(2131296601)
    ImageView imageView_android;
    @BindView(2131296602)
    ImageView imageView_banner;
    @BindView(2131296603)
    ImageView imageView_icon;
    @BindView(2131296604)
    ImageView imageView_ios;
    @BindView(2131296605)
    ImageView imageView_recommend;
    TransitionDrawable mT;
    Animation mU;
    Animation mV;
    @BindView(2131296722)
    NestedScrollView nestedScrollView;
    Call<GeneralResponse<GameDetailResponse>> oS;
    Call<GeneralResponse<ActionResponse>> oT;
    GameScreenShotRecyclerViewAdapter oU;
    GameScreenShotViewPagerAdapter oV;
    MediaController oW;
    ArrayList<ThumbnailObject> oX;
    ThumbnailObject oY;
    private GameDetailObject oZ;
    @BindView(2131296764)
    SnapRecyclerView recyclerView_screenShots;
    @BindView(2131296775)
    RelativeLayout relativeLayout_popup;
    int screenWidth;
    @BindView(2131296937)
    TextView textView_commentCount;
    @BindView(2131296938)
    TextView textView_description;
    @BindView(2131296939)
    TextView textView_download;
    @BindView(2131296942)
    TextView textView_gameSize;
    @BindView(2131296940)
    TextView textView_likeCount;
    @BindView(2131296941)
    TextView textView_publisher;
    @BindView(2131296943)
    TextView textView_title;
    @BindView(2131296945)
    TextView textView_version;
    @BindView(2131296944)
    TextView textView_versionDescription;
    @BindView(2131297037)
    Toolbar toolbar;
    @BindView(2131297050)
    VideoView videoView;
    @BindView(2131297053)
    ViewPager viewPager_screenShots;
    boolean isLiked = false;
    boolean pa = false;
    boolean nh = false;
    boolean pb = false;

    public static GameDetailFragment ad(String str) {
        GameDetailFragment gameDetailFragment = new GameDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_GAME_ID", str);
        gameDetailFragment.setArguments(bundle);
        return gameDetailFragment;
    }

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        c(0, false);
        this.viewPager_screenShots.setCurrentItem(i);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, 2131689975, true);
        if (this.oZ == null) {
            dg();
        } else {
            bI();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (this.oZ != null) {
            if (this.oZ.isAdult()) {
                this.imageView_adult.setVisibility(0);
            } else {
                this.imageView_adult.setVisibility(8);
            }
            if (this.oZ.isSuggest()) {
                this.imageView_recommend.setVisibility(0);
            } else {
                this.imageView_recommend.setVisibility(8);
            }
            if (this.oZ.isAndroid()) {
                this.imageView_android.setVisibility(0);
            } else {
                this.imageView_android.setVisibility(8);
            }
            if (this.oZ.isIos()) {
                this.imageView_ios.setVisibility(0);
            } else {
                this.imageView_ios.setVisibility(8);
            }
            if (this.isLiked != this.oZ.isLiked()) {
                this.isLiked = this.oZ.isLiked();
                z(this.isLiked);
            }
            if (this.oZ.getVideoLink() == null || this.oZ.getVideoLink().equalsIgnoreCase("")) {
                this.pa = false;
                this.imageButton_playVideo.setVisibility(8);
            } else {
                try {
                    VideoView videoView = this.videoView;
                    videoView.setVideoPath(this.oZ.getVideoLink() + "");
                    this.oW = new MediaController(getActivity());
                    this.oW.setAnchorView(this.videoView);
                    this.videoView.setMediaController(this.oW);
                    this.pa = true;
                    this.imageButton_playVideo.setVisibility(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    this.pa = false;
                    this.imageButton_playVideo.setVisibility(8);
                }
            }
            TextView textView = this.textView_title;
            textView.setText(this.oZ.getTitle() + "");
            TextView textView2 = this.textView_publisher;
            textView2.setText(this.oZ.getPublisher() + "");
            TextView textView3 = this.textView_gameSize;
            textView3.setText(this.oZ.getAndroidSize() + "");
            TextView textView4 = this.textView_commentCount;
            textView4.setText(this.oZ.getCommentsCount() + "");
            TextView textView5 = this.textView_likeCount;
            textView5.setText(this.oZ.getLikesCount() + "");
            TextView textView6 = this.textView_download;
            textView6.setText(this.oZ.getDownloadsCount() + "");
            TextView textView7 = this.textView_version;
            textView7.setText(this.oZ.getVersion() + "");
            TextView textView8 = this.textView_versionDescription;
            textView8.setText(this.oZ.getUpdateContent() + "");
            TextView textView9 = this.textView_description;
            textView9.setText(this.oZ.getDescription() + "");
            Picasso.with(getActivity()).load(g.b(this.oZ.getIcon())).into(this.imageView_icon);
            if (this.oY != null) {
                Picasso.with(getActivity()).load(g.b(this.oY)).into(this.imageView_banner);
            }
            this.oU.notifyDataSetChanged();
            this.oV.notifyDataSetChanged();
        }
    }

    public void c(int i, boolean z) {
        if (i == 8) {
            if (this.videoView.isPlaying()) {
                this.videoView.pause();
            }
            this.viewPager_screenShots.setVisibility(8);
            this.videoView.setVisibility(8);
            this.relativeLayout_popup.setVisibility(8);
            this.collapsingToolbarLayout.setVisibility(0);
        } else if (i == 0) {
            if (!z) {
                this.viewPager_screenShots.setVisibility(0);
                this.videoView.setVisibility(8);
            } else if (this.pa) {
                this.videoView.setVisibility(0);
                this.videoView.start();
            }
            this.collapsingToolbarLayout.setVisibility(8);
            this.relativeLayout_popup.setVisibility(0);
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.1
            boolean pc = false;
            int pd = -1;

            @Override // android.support.design.widget.AppBarLayout.OnOffsetChangedListener
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (this.pd == -1) {
                    this.pd = appBarLayout.getTotalScrollRange();
                }
                if (this.pd + i == 0) {
                    GameDetailFragment.this.collapsingToolbarLayout.setTitle("Title");
                    this.pc = true;
                } else if (this.pc) {
                    GameDetailFragment.this.collapsingToolbarLayout.setTitle("");
                    this.pc = false;
                }
            }
        });
        this.button_download.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (GameDetailFragment.this.oZ == null || GameDetailFragment.this.oZ.getAndroidLinks() == null || GameDetailFragment.this.oZ.getAndroidLinks().size() <= 0) {
                    return;
                }
                g.A(GameDetailFragment.this.getActivity(), GameDetailFragment.this.oZ.getAndroidLinks().get(0));
            }
        });
        this.imageButton_gift.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertDialogCenter.giftNotReady(GameDetailFragment.this.getContext());
            }
        });
        this.imageButton_playVideo.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameDetailFragment.this.c(0, true);
            }
        });
        this.imageButton_closePopup.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameDetailFragment.this.c(8, true);
            }
        });
        this.imageButton_like.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameDetailFragment.this.dh();
            }
        });
        this.imageButton_comment.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameDetailFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, CommentFragment.Z(GameDetailFragment.this.gameId), CommentFragment.TAG).addToBackStack(CommentFragment.TAG).commit();
            }
        });
        this.imageButton_descriptionHeightControl.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (GameDetailFragment.this.nh) {
                    GameDetailFragment.this.textView_description.setSingleLine(false);
                    GameDetailFragment.this.nh = false;
                    GameDetailFragment.this.d(GameDetailFragment.this.imageButton_descriptionHeightControl);
                    return;
                }
                GameDetailFragment.this.textView_description.setSingleLine();
                GameDetailFragment.this.nh = true;
                GameDetailFragment.this.e(GameDetailFragment.this.imageButton_descriptionHeightControl);
            }
        });
        this.imageButton_versionDescriptionHeightControl.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (GameDetailFragment.this.pb) {
                    GameDetailFragment.this.textView_versionDescription.setSingleLine(false);
                    GameDetailFragment.this.pb = false;
                    GameDetailFragment.this.d(GameDetailFragment.this.imageButton_versionDescriptionHeightControl);
                    return;
                }
                GameDetailFragment.this.textView_versionDescription.setSingleLine();
                GameDetailFragment.this.pb = true;
                GameDetailFragment.this.e(GameDetailFragment.this.imageButton_versionDescriptionHeightControl);
            }
        });
        this.recyclerView_screenShots.setLayoutManager(new LinearLayoutManager(getActivity(), 0, false));
        this.oU = new GameScreenShotRecyclerViewAdapter(getActivity(), this.oX, this);
        this.recyclerView_screenShots.setAdapter(this.oU);
        this.oV = new GameScreenShotViewPagerAdapter(getActivity(), this.oX);
        this.viewPager_screenShots.setAdapter(this.oV);
    }

    public void d(View view) {
        view.setAnimation(null);
        if (view instanceof ImageButton) {
            ((ImageButton) view).setImageResource(2131230951);
        }
        this.mU = AnimationUtils.loadAnimation(getActivity(), 2130771979);
        view.startAnimation(this.mU);
    }

    public void dg() {
        C(getResources().getString(2131689826));
        f.aA("Show Progress");
        this.oS = new d(getContext()).dO().z(e.z(getActivity()), this.gameId);
        this.oS.enqueue(new Callback<GeneralResponse<GameDetailResponse>>() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<GameDetailResponse>> call, Throwable th) {
                th.printStackTrace();
                f.aA("dismiss progress");
                GameDetailFragment.this.bC();
                new c(GameDetailFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<GameDetailResponse>> call, Response<GeneralResponse<GameDetailResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(GameDetailFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.getGame() != null) {
                    f.aA(response.body().data.getGame().toString());
                    GameDetailFragment.this.oZ = response.body().data.getGame();
                    for (int i = 0; i < response.body().data.getGame().getScreenshots().size(); i++) {
                        if (i == 0) {
                            GameDetailFragment.this.oY = response.body().data.getGame().getScreenshots().get(i);
                        } else {
                            GameDetailFragment.this.oX.add(response.body().data.getGame().getScreenshots().get(i));
                        }
                    }
                    GameDetailFragment.this.bI();
                }
                f.aA("dismiss progress");
                GameDetailFragment.this.bC();
            }
        });
    }

    public void dh() {
        bA();
        this.oT = new d(getContext()).dO().A(e.z(getActivity()), this.gameId);
        this.oT.enqueue(new Callback<GeneralResponse<ActionResponse>>() { // from class: com.picacomic.fregata.fragments.GameDetailFragment.3
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ActionResponse>> call, Throwable th) {
                th.printStackTrace();
                GameDetailFragment.this.bC();
                new c(GameDetailFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ActionResponse>> call, Response<GeneralResponse<ActionResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getAction() != null && GameDetailFragment.this.oZ != null) {
                        if (response.body().data.getAction().equalsIgnoreCase("like")) {
                            GameDetailFragment.this.isLiked = true;
                            GameDetailFragment.this.z(true);
                            TextView textView = GameDetailFragment.this.textView_likeCount;
                            textView.setText((GameDetailFragment.this.oZ.getLikesCount() + 1) + "");
                        } else if (response.body().data.getAction().equalsIgnoreCase("unlike")) {
                            GameDetailFragment.this.isLiked = false;
                            GameDetailFragment.this.z(false);
                            TextView textView2 = GameDetailFragment.this.textView_likeCount;
                            textView2.setText((GameDetailFragment.this.oZ.getLikesCount() - 1) + "");
                        }
                    }
                } else {
                    try {
                        new c(GameDetailFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                GameDetailFragment.this.bC();
            }
        });
    }

    public void e(View view) {
        view.setAnimation(null);
        if (view instanceof ImageButton) {
            ((ImageButton) view).setImageResource(2131230936);
        }
        this.mV = AnimationUtils.loadAnimation(getActivity(), 2130771978);
        view.startAnimation(this.mV);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.mU = AnimationUtils.loadAnimation(getActivity(), 2130771979);
        this.mV = AnimationUtils.loadAnimation(getActivity(), 2130771978);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        this.screenWidth = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay().getWidth();
        this.recyclerView_screenShots.getLayoutParams().height = (this.screenWidth * 9) / 16;
        this.frameLayout_banner.getLayoutParams().height = (this.screenWidth * 9) / 16;
        this.mT = new TransitionDrawable(new Drawable[]{ResourcesCompat.getDrawable(getResources(), 2131230934, getActivity().getTheme()), ResourcesCompat.getDrawable(getResources(), 2131230935, getActivity().getTheme())});
        this.imageButton_like.setImageDrawable(this.mT);
        if (this.oX == null) {
            this.oX = new ArrayList<>();
        }
        this.oU = new GameScreenShotRecyclerViewAdapter(getActivity(), this.oX, this);
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.gameId = getArguments().getString("KEY_GAME_ID", "");
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427413, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.oS != null) {
            this.oS.cancel();
        }
        if (this.oT != null) {
            this.oT.cancel();
        }
        super.onDetach();
    }

    public void z(boolean z) {
        if (z) {
            this.mT.startTransition(getResources().getInteger(2131361794));
        } else {
            this.mT.reverseTransition(getResources().getInteger(2131361794));
        }
    }
}
