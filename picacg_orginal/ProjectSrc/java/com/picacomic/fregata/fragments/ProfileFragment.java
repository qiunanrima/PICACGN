package com.picacomic.fregata.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.R;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.google.gson.Gson;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.ProfileFragmentPagerAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.UserBasicObject;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.PunchInResponse;
import com.picacomic.fregata.objects.responses.UserProfileResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import com.picacomic.fregata.utils.views.ExpCircleView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProfileFragment.class */
public class ProfileFragment extends BaseImagePickFragment {
    public static final String TAG = "ProfileFragment";
    @BindView(2131296382)
    Button button_edit;
    @BindView(2131296496)
    ExpCircleView expCircleView;
    @BindView(2131296622)
    CircleImageView imageView_avatar;
    @BindView(2131296623)
    ImageView imageView_avatarBlur;
    @BindView(2131296624)
    ImageView imageView_character;
    @BindView(2131296627)
    ImageView imageView_verified;
    UserProfileObject jW;
    Call<GeneralResponse<UserProfileResponse>> jX;
    ProfileFragmentPagerAdapter qR;
    Call<GeneralResponse<PunchInResponse>> qS;
    CountDownTimer qT;
    @BindView(2131296830)
    TabLayout tabLayout;
    @BindView(2131296970)
    TextView textView_honor;
    @BindView(2131296971)
    TextView textView_level;
    @BindView(2131296972)
    TextView textView_name;
    @BindView(2131296983)
    TextView textView_punchIn;
    @BindView(2131296984)
    TextView textView_slogan;
    @BindView(2131297056)
    ViewPager viewPager_tags;
    float qU = 180.0f;
    int gridSize = 1;
    Target qV = new Target() { // from class: com.picacomic.fregata.fragments.ProfileFragment.1
        @Override // com.squareup.picasso.Target
        public void onBitmapFailed(Drawable drawable) {
        }

        @Override // com.squareup.picasso.Target
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
            if (bitmap == null || ProfileFragment.this.imageView_avatar == null || ProfileFragment.this.imageView_avatarBlur == null || ProfileFragment.this.imageView_avatarBlur == null) {
                return;
            }
            ProfileFragment.this.imageView_avatarBlur.setImageBitmap(g.a(bitmap, 0.5f, 5));
        }

        @Override // com.squareup.picasso.Target
        public void onPrepareLoad(Drawable drawable) {
        }
    };

    @Override // com.picacomic.fregata.fragments.BaseImagePickFragment
    public void K(String str) {
        super.K(str);
        Picasso.with(getActivity()).load(str).into(this.imageView_avatar);
        this.imageView_avatarBlur.setImageBitmap(g.a(g.b(getActivity(), Uri.parse(str)), 0.5f, 5));
        if (getActivity() != null) {
            cd();
        }
    }

    public int Z(int i) {
        int i2 = (i * 2) - 1;
        return ((i2 * i2) - 1) * 25;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        this.expCircleView.setGridSize(20);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(0);
        }
        cd();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (this.jW == null) {
            this.qR = new ProfileFragmentPagerAdapter(getChildFragmentManager(), null);
            this.viewPager_tags.setAdapter(this.qR);
            this.tabLayout.setupWithViewPager(this.viewPager_tags);
            TabLayout.Tab tabAt = this.tabLayout.getTabAt(0);
            this.tabLayout.getTabAt(0).setText(2131689700);
            tabAt.setText(2131689700);
            return;
        }
        try {
            e.i(getContext(), new Gson().toJson(this.jW));
            if (this.jW.getAvatar() != null) {
                Picasso.with(getActivity()).load(g.b(this.jW.getAvatar())).into(this.qV);
                Picasso.with(getActivity()).load(g.b(this.jW.getAvatar())).into(this.imageView_avatar);
            }
            if (this.jW.getCharacter() != null) {
                Picasso.with(getContext()).load(this.jW.getCharacter()).into(this.imageView_character);
                this.imageView_character.setVisibility(0);
            } else {
                this.imageView_character.setVisibility(8);
            }
            int A = e.A(getActivity());
            if (A != -1 && A != this.jW.getLevel()) {
                AlertDialogCenter.levelUp(getActivity());
            }
            e.a((Context) getActivity(), this.jW.getLevel());
            TextView textView = this.textView_level;
            textView.setText(this.jW.getLevel() + " (" + this.jW.getExp() + "/" + Z(this.jW.getLevel() + 1) + ")");
            TextView textView2 = this.textView_name;
            StringBuilder sb = new StringBuilder();
            sb.append(this.jW.getName());
            sb.append("");
            textView2.setText(sb.toString());
            TextView textView3 = this.textView_honor;
            textView3.setText(this.jW.getTitle() + "");
            TextView textView4 = this.textView_slogan;
            textView4.setText(this.jW.getSlogan() + "");
            if (this.jW.isVerified()) {
                this.imageView_verified.setVisibility(0);
            } else {
                this.imageView_verified.setVisibility(8);
            }
            this.qU = (this.jW.getExp() * 360.0f) / Z(this.jW.getLevel() + 1);
            String str = TAG;
            f.D(str, "Angle = " + this.qU + " next = " + Z(this.jW.getLevel()) + 1);
            j(this.qU);
            this.qR = new ProfileFragmentPagerAdapter(getChildFragmentManager(), new UserBasicObject(this.jW));
            this.viewPager_tags.setAdapter(this.qR);
            this.tabLayout.setupWithViewPager(this.viewPager_tags);
            TabLayout.Tab tabAt2 = this.tabLayout.getTabAt(0);
            this.tabLayout.getTabAt(0).setText(2131689700);
            tabAt2.setText(2131689700);
            TabLayout.Tab tabAt3 = this.tabLayout.getTabAt(1);
            this.tabLayout.getTabAt(1).setText(2131689758);
            tabAt3.setText(2131689758);
            if (this.jW.isPunched()) {
                this.textView_punchIn.setVisibility(8);
            } else {
                this.textView_punchIn.setVisibility(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.button_edit.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfileFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ProfileFragment.this.jW != null) {
                    ProfileFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ProfileEditFragment.b(ProfileFragment.this.jW), ProfileEditFragment.TAG).addToBackStack(ProfileEditFragment.TAG).commit();
                }
            }
        });
        this.textView_punchIn.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfileFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfileFragment.this.dB();
            }
        });
        this.imageView_avatar.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfileFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfileFragment.this.cf();
            }
        });
        this.textView_name.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfileFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.textView_slogan.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfileFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
    }

    public void cd() {
        C(getResources().getString(2131689826));
        f.aA("Show Progress");
        this.jX = new d(getContext()).dO().am(e.z(getActivity()));
        this.jX.enqueue(new Callback<GeneralResponse<UserProfileResponse>>() { // from class: com.picacomic.fregata.fragments.ProfileFragment.9
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<UserProfileResponse>> call, Throwable th) {
                th.printStackTrace();
                f.aA("dismiss progress");
                ProfileFragment.this.bC();
                new c(ProfileFragment.this.getActivity()).dN();
                ProfileFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<UserProfileResponse>> call, Response<GeneralResponse<UserProfileResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(ProfileFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.getUser() != null) {
                    ProfileFragment.this.jW = response.body().data.getUser();
                    if (ProfileFragment.this.getActivity() == null || ProfileFragment.this == null) {
                        f.D(ProfileFragment.TAG, "ProfileFragment Null Error");
                    }
                }
                ProfileFragment.this.bI();
                f.aA("dismiss progress");
                ProfileFragment.this.bC();
            }
        });
    }

    public void dA() {
        if (getActivity() == null || this.textView_punchIn == null) {
            return;
        }
        this.textView_punchIn.setVisibility(8);
        AlertDialogCenter.punchedIn(getContext());
    }

    public void dB() {
        C(getResources().getString(2131689826));
        f.aA("Show Progress");
        this.qS = new d(getContext()).dO().an(e.z(getActivity()));
        this.qS.enqueue(new Callback<GeneralResponse<PunchInResponse>>() { // from class: com.picacomic.fregata.fragments.ProfileFragment.8
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<PunchInResponse>> call, Throwable th) {
                th.printStackTrace();
                f.aA("dismiss progress");
                ProfileFragment.this.bC();
                new c(ProfileFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<PunchInResponse>> call, Response<GeneralResponse<PunchInResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(ProfileFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null) {
                    ProfileFragment.this.dA();
                }
                f.aA("dismiss progress");
                ProfileFragment.this.bC();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        for (int i = 2; i < 101; i++) {
            f.D(TAG, "LEVEL = " + i + " EXP = " + Z(i));
        }
    }

    public void j(final float f) {
        this.qT = new CountDownTimer(1000L, 10L) { // from class: com.picacomic.fregata.fragments.ProfileFragment.7
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (ProfileFragment.this.expCircleView != null) {
                    ProfileFragment.this.expCircleView.setAngle(f);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (ProfileFragment.this.expCircleView != null) {
                    ProfileFragment.this.expCircleView.setAngle((((float) (1000 - j)) * f) / 1000.0f);
                }
            }
        };
        this.qT.start();
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.kv = 1;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427425, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.qT != null) {
            this.qT.cancel();
        }
        if (this.jX != null) {
            this.jX.cancel();
        }
        if (this.qS != null) {
            this.qS.cancel();
        }
        super.onDetach();
    }
}
