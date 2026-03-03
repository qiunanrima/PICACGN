package com.picacomic.fregata.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.objects.requests.UpdatePicaIdBody;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.UserProfileResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/OneTimeIdUpdateFragment.class */
public class OneTimeIdUpdateFragment extends BaseFragment {
    public static final String TAG = "OneTimeIdUpdateFragment";
    @BindView(2131296377)
    Button button_update;
    @BindView(2131296462)
    EditText editText_email;
    @BindView(2131296463)
    EditText editText_username;
    UserProfileObject jW;
    Call<GeneralResponse<UserProfileResponse>> jX;
    Call<GeneralResponse> qn;
    boolean qo;
    @BindView(2131297037)
    Toolbar toolbar;

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        }
        a(this.toolbar, 2131689980, true);
        cd();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.editText_email.addTextChangedListener(new TextWatcher() { // from class: com.picacomic.fregata.fragments.OneTimeIdUpdateFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() < 2) {
                    OneTimeIdUpdateFragment.this.editText_email.setError(OneTimeIdUpdateFragment.this.getString(2131689857));
                    OneTimeIdUpdateFragment.this.qo = false;
                } else if (charSequence.toString().matches("^[0-9a-z_](\\.?[0-9a-z_]){1,29}$")) {
                    OneTimeIdUpdateFragment.this.editText_email.setError(null);
                    OneTimeIdUpdateFragment.this.qo = true;
                } else {
                    OneTimeIdUpdateFragment.this.editText_email.setError(OneTimeIdUpdateFragment.this.getString(2131689858));
                    OneTimeIdUpdateFragment.this.qo = false;
                }
            }
        });
        this.button_update.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.OneTimeIdUpdateFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OneTimeIdUpdateFragment.this.dt();
            }
        });
    }

    public void cd() {
        C(getResources().getString(2131689826));
        f.aA("Show Progress");
        this.jX = new d(getContext()).dO().am(e.z(getActivity()));
        this.jX.enqueue(new Callback<GeneralResponse<UserProfileResponse>>() { // from class: com.picacomic.fregata.fragments.OneTimeIdUpdateFragment.3
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<UserProfileResponse>> call, Throwable th) {
                th.printStackTrace();
                f.aA("dismiss progress");
                OneTimeIdUpdateFragment.this.bC();
                new c(OneTimeIdUpdateFragment.this.getActivity()).dN();
                OneTimeIdUpdateFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<UserProfileResponse>> call, Response<GeneralResponse<UserProfileResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(OneTimeIdUpdateFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.getUser() != null) {
                    OneTimeIdUpdateFragment.this.jW = response.body().data.getUser();
                    if (OneTimeIdUpdateFragment.this.editText_username != null && OneTimeIdUpdateFragment.this.editText_username.getText().length() == 0) {
                        EditText editText = OneTimeIdUpdateFragment.this.editText_username;
                        editText.setText(OneTimeIdUpdateFragment.this.jW.getName() + "");
                    }
                }
                OneTimeIdUpdateFragment.this.bI();
                f.aA("dismiss progress");
                OneTimeIdUpdateFragment.this.bC();
            }
        });
    }

    public void dt() {
        if (!this.qo || this.editText_email == null || this.editText_username == null) {
            return;
        }
        m(this.editText_email.getText().toString(), this.editText_username.getText().toString());
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            return;
        }
        ((MainActivity) getActivity()).t(8);
    }

    public void m(String str, String str2) {
        C(getResources().getString(2131689826));
        f.aA("Show Progress");
        this.qn = new d(getContext()).dO().a(e.z(getActivity()), new UpdatePicaIdBody(str, str2));
        this.qn.enqueue(new Callback<GeneralResponse>() { // from class: com.picacomic.fregata.fragments.OneTimeIdUpdateFragment.4
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse> call, Throwable th) {
                th.printStackTrace();
                f.aA("dismiss progress");
                OneTimeIdUpdateFragment.this.bC();
                new c(OneTimeIdUpdateFragment.this.getActivity()).dN();
                OneTimeIdUpdateFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(OneTimeIdUpdateFragment.this.getContext(), "更新成功！", 0).show();
                    OneTimeIdUpdateFragment.this.getActivity().startActivity(new Intent(OneTimeIdUpdateFragment.this.getActivity(), MainActivity.class));
                    OneTimeIdUpdateFragment.this.getActivity().finish();
                } else {
                    try {
                        new c(OneTimeIdUpdateFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                OneTimeIdUpdateFragment.this.bI();
                f.aA("dismiss progress");
                OneTimeIdUpdateFragment.this.bC();
            }
        });
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427422, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.jX != null) {
            this.jX.cancel();
        }
        if (this.qn != null) {
            this.qn.cancel();
        }
        super.onDetach();
    }
}
