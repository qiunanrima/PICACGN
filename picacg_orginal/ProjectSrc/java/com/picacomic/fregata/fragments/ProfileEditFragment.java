package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.objects.requests.UpdateProfileBody;
import com.picacomic.fregata.objects.responses.RegisterResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProfileEditFragment.class */
public class ProfileEditFragment extends BaseImagePickFragment {
    public static final String TAG = "ProfileEditFragment";
    @BindView(2131296383)
    Button button_update;
    @BindView(2131296470)
    EditText editText_slogan;
    @BindView(2131296622)
    CircleImageView imageView_avatar;
    UserProfileObject jW;
    Call<RegisterResponse> qO;
    @BindView(2131296968)
    TextView textView_birth;
    @BindView(2131296969)
    TextView textView_email;
    @BindView(2131296972)
    TextView textView_name;
    @BindView(2131297037)
    Toolbar toolbar;

    public static ProfileEditFragment b(UserProfileObject userProfileObject) {
        ProfileEditFragment profileEditFragment = new ProfileEditFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("USER_PROFILE_OBJECT", userProfileObject);
        profileEditFragment.setArguments(bundle);
        return profileEditFragment;
    }

    @Override // com.picacomic.fregata.fragments.BaseImagePickFragment
    public void K(String str) {
        super.K(str);
        Picasso.with(getActivity()).load(str).into(this.imageView_avatar);
    }

    public void ag(String str) {
        C(getResources().getString(2131689826));
        this.qO = new d(getContext()).dO().a(e.z(getActivity()), new UpdateProfileBody(str));
        this.qO.enqueue(new Callback<RegisterResponse>() { // from class: com.picacomic.fregata.fragments.ProfileEditFragment.3
            @Override // retrofit2.Callback
            public void onFailure(Call<RegisterResponse> call, Throwable th) {
                th.printStackTrace();
                ProfileEditFragment.this.bC();
                new c(ProfileEditFragment.this.getActivity()).dN();
                ProfileEditFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() != 200) {
                    try {
                        new c(ProfileEditFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (ProfileEditFragment.this.getFragmentManager() != null) {
                    Toast.makeText(ProfileEditFragment.this.getContext(), 2131689866, 0).show();
                    ProfileEditFragment.this.getFragmentManager().popBackStack();
                }
                ProfileEditFragment.this.bC();
                ProfileEditFragment.this.bI();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, 2131689983, true);
        if (this.jW == null || this.jW.getAvatar() == null) {
            return;
        }
        if (this.jW.getAvatar() != null) {
            Picasso.with(getActivity()).load(g.b(this.jW.getAvatar())).into(this.imageView_avatar);
        }
        this.textView_name.setText(this.jW.getName());
        this.textView_birth.setText(this.jW.getBirthday().substring(0, this.jW.getBirthday().indexOf("T")));
        this.textView_email.setText(this.jW.getEmail());
        EditText editText = this.editText_slogan;
        editText.setText(this.jW.getSlogan() + "");
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.imageView_avatar.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfileEditFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfileEditFragment.this.cf();
            }
        });
        this.button_update.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ProfileEditFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ProfileEditFragment.this.editText_slogan == null || ProfileEditFragment.this.editText_slogan.getText() == null) {
                    return;
                }
                ProfileEditFragment.this.ag(ProfileEditFragment.this.editText_slogan.getText().toString());
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.kv = 1;
        if (getArguments() != null) {
            this.jW = (UserProfileObject) getArguments().getParcelable("USER_PROFILE_OBJECT");
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427427, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
