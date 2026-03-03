package com.picacomic.fregata.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.activities.BaseActivity;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.objects.requests.UserIdBody;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.UserProfileDirtyResponse;
import com.picacomic.fregata.objects.responses.UserProfileResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProfilePopupFragment.class */
public class ProfilePopupFragment extends DialogFragment {
    public static final String TAG = "ProfilePopupFragment";
    @BindView(2131296625)
    ImageView imageView_avatar;
    @BindView(2131296626)
    ImageView imageView_character;
    UserProfileObject jW;
    Call<GeneralResponse<UserProfileResponse>> jX;
    @BindView(2131296697)
    LinearLayout linearLayout_adminFunction;
    Call<GeneralResponse<UserProfileDirtyResponse>> oo;
    Call<GeneralResponse> qZ;
    Call<GeneralResponse> ra;
    @BindView(2131296975)
    TextView textView_adminFunction;
    @BindView(2131296974)
    TextView textView_block;
    @BindView(2131296976)
    TextView textView_level;
    @BindView(2131296977)
    TextView textView_levelTitle;
    @BindView(2131296978)
    TextView textView_name;
    @BindView(2131296979)
    TextView textView_removeComment;
    @BindView(2131296980)
    TextView textView_slogan;
    @BindView(2131296981)
    TextView textView_title;
    @BindView(2131296982)
    TextView textView_woo;
    public String userId;

    public static ProfilePopupFragment ah(String str) {
        ProfilePopupFragment profilePopupFragment = new ProfilePopupFragment();
        Bundle bundle = new Bundle();
        bundle.putString("USER_ID", str);
        profilePopupFragment.setArguments(bundle);
        return profilePopupFragment;
    }

    public static ProfilePopupFragment c(UserProfileObject userProfileObject) {
        ProfilePopupFragment profilePopupFragment = new ProfilePopupFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("USER_PROFILE_OBJECT", userProfileObject);
        profilePopupFragment.setArguments(bundle);
        return profilePopupFragment;
    }

    public void bI() {
        if (this.jW == null) {
            if (getContext() != null) {
                Toast.makeText(getContext(), 2131689572, 0).show();
            }
            dismiss();
            return;
        }
        String u = e.u(getContext());
        if (u != null && u.contains("@picacomic.com")) {
            this.textView_adminFunction.setVisibility(0);
        }
        if (this.imageView_avatar != null && this.jW.getAvatar() != null) {
            Picasso.with(getActivity()).load(g.b(this.jW.getAvatar())).into(this.imageView_avatar);
        }
        if (this.imageView_character != null) {
            if (this.jW.getCharacter() != null) {
                Picasso.with(getActivity()).load(this.jW.getCharacter()).into(this.imageView_character);
                this.imageView_character.setVisibility(0);
            } else {
                this.imageView_character.setVisibility(8);
            }
        }
        if (this.textView_level != null) {
            TextView textView = this.textView_level;
            textView.setText(this.jW.getLevel() + "");
        }
        if (this.textView_name != null && this.jW.getName() != null) {
            TextView textView2 = this.textView_name;
            textView2.setText(this.jW.getName() + "");
        }
        if (this.textView_title != null && this.jW.getTitle() != null) {
            TextView textView3 = this.textView_title;
            textView3.setText(this.jW.getTitle() + "");
        }
        if (this.textView_title != null && this.jW.getGender() != null) {
            TextView textView4 = this.textView_title;
            textView4.append(" (" + g.E(getContext(), this.jW.getGender()) + ")");
        }
        if (this.textView_slogan == null || this.jW.getSlogan() == null) {
            return;
        }
        TextView textView5 = this.textView_slogan;
        textView5.setText(this.jW.getSlogan() + "");
    }

    public void cd() {
        this.jX = new d(getContext()).dO().q(e.z(getActivity()), this.userId);
        this.jX.enqueue(new Callback<GeneralResponse<UserProfileResponse>>() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.6
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<UserProfileResponse>> call, Throwable th) {
                th.printStackTrace();
                new c(ProfilePopupFragment.this.getActivity()).dN();
                ProfilePopupFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<UserProfileResponse>> call, Response<GeneralResponse<UserProfileResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(ProfilePopupFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.getUser() != null) {
                    ProfilePopupFragment.this.jW = response.body().data.getUser();
                }
                ProfilePopupFragment.this.bI();
            }
        });
    }

    public void dC() {
        AlertDialogCenter.showCustomAlertDialog(getContext(), 2131230947, 2131689539, 2131689537, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfilePopupFragment.this.dD();
            }
        }, (View.OnClickListener) null);
    }

    public void dD() {
        this.ra = new d(getContext()).dO().b(e.z(getActivity()), new UserIdBody(this.userId != null ? this.userId : this.jW.getUserId()));
        this.ra.enqueue(new Callback<GeneralResponse>() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse> call, Throwable th) {
                th.printStackTrace();
                new c(ProfilePopupFragment.this.getActivity()).dN();
                ProfilePopupFragment.this.bI();
                Toast.makeText(ProfilePopupFragment.this.getContext(), 2131689987, 0).show();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(ProfilePopupFragment.this.getContext(), 2131689988, 0).show();
                } else {
                    Toast.makeText(ProfilePopupFragment.this.getContext(), 2131689987, 0).show();
                    try {
                        new c(ProfilePopupFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ProfilePopupFragment.this.bI();
            }
        });
    }

    public void dE() {
        AlertDialogCenter.showCustomAlertDialog(getContext(), 2131230947, 2131689599, 2131689597, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfilePopupFragment.this.dF();
            }
        }, (View.OnClickListener) null);
    }

    public void dF() {
        this.qZ = new d(getContext()).dO().a(e.z(getActivity()), new UserIdBody(this.userId != null ? this.userId : this.jW.getUserId()));
        this.qZ.enqueue(new Callback<GeneralResponse>() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.4
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse> call, Throwable th) {
                th.printStackTrace();
                new c(ProfilePopupFragment.this.getActivity()).dN();
                ProfilePopupFragment.this.bI();
                Toast.makeText(ProfilePopupFragment.this.getContext(), 2131689994, 0).show();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(ProfilePopupFragment.this.getContext(), 2131689995, 0).show();
                } else {
                    Toast.makeText(ProfilePopupFragment.this.getContext(), 2131689994, 0).show();
                    try {
                        new c(ProfilePopupFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                ProfilePopupFragment.this.bI();
            }
        });
    }

    public void dG() {
        this.oo = new d(getContext()).dO().p(e.z(getActivity()), this.userId != null ? this.userId : this.jW.getUserId());
        this.oo.enqueue(new Callback<GeneralResponse<UserProfileDirtyResponse>>() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.5
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<UserProfileDirtyResponse>> call, Throwable th) {
                th.printStackTrace();
                new c(ProfilePopupFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<UserProfileDirtyResponse>> call, Response<GeneralResponse<UserProfileDirtyResponse>> response) {
                if (response.code() == 200) {
                    Toast.makeText(ProfilePopupFragment.this.getContext(), "修改污頭像成功！", 0).show();
                    return;
                }
                try {
                    String str = ProfilePopupFragment.TAG;
                    f.D(str, response.code() + ": " + response.errorBody().string());
                    new c(ProfilePopupFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.userId = getArguments().getString("USER_ID", null);
            this.jW = (UserProfileObject) getArguments().getParcelable("USER_PROFILE_OBJECT");
        }
    }

    @Override // android.support.v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        onCreateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        onCreateDialog.setCancelable(true);
        onCreateDialog.setCanceledOnTouchOutside(true);
        onCreateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.13
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 || i == 84;
            }
        });
        return onCreateDialog;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427428, viewGroup, false);
        ButterKnife.bind(this, inflate);
        this.textView_adminFunction.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ProfilePopupFragment.this.linearLayout_adminFunction.getVisibility() == 0) {
                    ProfilePopupFragment.this.linearLayout_adminFunction.setVisibility(8);
                } else {
                    ProfilePopupFragment.this.linearLayout_adminFunction.setVisibility(0);
                }
            }
        });
        this.textView_block.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfilePopupFragment.this.dC();
            }
        });
        this.textView_woo.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfilePopupFragment.this.dG();
            }
        });
        this.textView_removeComment.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfilePopupFragment.this.dE();
            }
        });
        this.imageView_avatar.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ProfilePopupFragment.this.getActivity() == null || !(ProfilePopupFragment.this.getActivity() instanceof BaseActivity) || ProfilePopupFragment.this.jW == null || ProfilePopupFragment.this.jW.getAvatar() == null) {
                    return;
                }
                ((BaseActivity) ProfilePopupFragment.this.getActivity()).D(g.b(ProfilePopupFragment.this.jW.getAvatar()));
                ProfilePopupFragment.this.dismiss();
            }
        });
        this.textView_title.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (g.ax(ProfilePopupFragment.this.getContext()) == 0 || ProfilePopupFragment.this.getActivity() == null || !(ProfilePopupFragment.this.getActivity() instanceof BaseActivity) || ProfilePopupFragment.this.jW == null || ProfilePopupFragment.this.jW.getUserId() == null) {
                    return;
                }
                ((BaseActivity) ProfilePopupFragment.this.getActivity()).h(ProfilePopupFragment.this.jW.getUserId(), ProfilePopupFragment.this.jW.getTitle());
                ProfilePopupFragment.this.dismiss();
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfilePopupFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (g.ax(ProfilePopupFragment.this.getContext()) == 0 || ProfilePopupFragment.this.jW == null || ProfilePopupFragment.this.jW.getName() == null || ProfilePopupFragment.this.jW.getUserId() == null) {
                    return;
                }
                ProfilePopupFragment.this.dismiss();
                ((MainActivity) ProfilePopupFragment.this.getActivity()).i(ProfilePopupFragment.this.jW.getName(), ProfilePopupFragment.this.jW.getUserId());
            }
        };
        this.textView_levelTitle.setOnClickListener(onClickListener);
        this.textView_level.setOnClickListener(onClickListener);
        if (this.jW != null) {
            bI();
        } else if (this.userId != null) {
            cd();
        }
        return inflate;
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onDetach() {
        System.gc();
        if (isAdded()) {
            dismiss();
        }
        super.onDetach();
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.jX != null) {
            this.jX.cancel();
        }
        if (this.ra != null) {
            this.ra.cancel();
        }
        if (this.qZ != null) {
            this.qZ.cancel();
        }
    }
}
