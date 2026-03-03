package com.picacomic.fregata.fragments;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.R;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.NetworkErrorObject;
import com.picacomic.fregata.objects.requests.ForgotPasswordBody;
import com.picacomic.fregata.objects.requests.ResetPasswordBody;
import com.picacomic.fregata.objects.requests.SignInBody;
import com.picacomic.fregata.objects.responses.ForgotPasswordResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.PasswordResponse;
import com.picacomic.fregata.objects.responses.SignInResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import com.squareup.picasso.Picasso;
import java.util.Random;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/LoginFragment.class */
public class LoginFragment extends BaseFragment {
    public static final String TAG = "LoginFragment";
    @BindView(2131296378)
    Button button_forgotPassword;
    @BindView(2131296379)
    Button button_login;
    @BindView(2131296380)
    Button button_register;
    @BindView(2131296381)
    Button button_resendActivation;
    @BindView(2131296468)
    EditText editText_email;
    @BindView(2131296469)
    EditText editText_password;
    @BindView(2131296617)
    ImageView imageView_logo;
    @BindView(2131296690)
    LinearLayout linearLayout_loginForm;
    EditText pT;
    Call<GeneralResponse<SignInResponse>> pU;
    Call<GeneralResponse<ForgotPasswordResponse>> pV;
    Call<GeneralResponse<PasswordResponse>> pW;
    Animation pX;
    Animation pY;
    boolean pZ = false;
    String qa;
    String qb;

    public void E(boolean z) {
        if (this.pT != null && this.pT.getText() != null && g.A(this.pT.getText().toString())) {
            this.pT.setError(null);
            this.pZ = true;
        } else if (z) {
            this.pT.setError(getString(2131689567));
            this.pZ = false;
        }
    }

    public void a(final String str, final int i, String str2) {
        View inflate = ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(2131427388, (ViewGroup) getView().getParent(), false);
        TextView textView = (TextView) inflate.findViewById(2131296931);
        this.pT = (EditText) inflate.findViewById(2131296460);
        textView.setText(str2 + "");
        EditText editText = this.pT;
        editText.setHint("Answer " + i);
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(2131689834).setView(inflate).setCancelable(false).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (LoginFragment.this.pT == null || LoginFragment.this.pT.getText().length() <= 0) {
                    return;
                }
                LoginFragment.this.b(str, i, LoginFragment.this.pT.getText().toString());
            }
        }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    public void af(final String str) {
        C(getResources().getString(2131689825));
        this.pV = new d(getContext()).dO().a(new ForgotPasswordBody(str));
        this.pV.enqueue(new Callback<GeneralResponse<ForgotPasswordResponse>>() { // from class: com.picacomic.fregata.fragments.LoginFragment.8
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ForgotPasswordResponse>> call, Throwable th) {
                th.printStackTrace();
                LoginFragment.this.bI();
                LoginFragment.this.bC();
                new c(LoginFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ForgotPasswordResponse>> call, Response<GeneralResponse<ForgotPasswordResponse>> response) {
                String question2;
                if (response.code() == 200) {
                    int i = 3;
                    switch (new Random(System.currentTimeMillis()).nextInt(3)) {
                        case 1:
                            i = 2;
                            question2 = response.body().data.getQuestion2();
                            break;
                        case 2:
                            question2 = response.body().data.getQuestion3();
                            break;
                        default:
                            i = 1;
                            question2 = response.body().data.getQuestion1();
                            break;
                    }
                    LoginFragment.this.a(str, i, question2);
                } else {
                    try {
                        new c(LoginFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    LoginFragment.this.bI();
                }
                LoginFragment.this.bC();
            }
        });
    }

    public void b(String str, int i, String str2) {
        C(getResources().getString(2131689825));
        this.pW = new d(getContext()).dO().a(new ResetPasswordBody(str, i, str2));
        this.pW.enqueue(new Callback<GeneralResponse<PasswordResponse>>() { // from class: com.picacomic.fregata.fragments.LoginFragment.9
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<PasswordResponse>> call, Throwable th) {
                th.printStackTrace();
                LoginFragment.this.bI();
                LoginFragment.this.bC();
                new c(LoginFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<PasswordResponse>> call, Response<GeneralResponse<PasswordResponse>> response) {
                if (response.code() == 200) {
                    LoginFragment.this.qb = response.body().data.getPassword();
                    LoginFragment.this.editText_password.setText(LoginFragment.this.qb);
                    LoginFragment.this.editText_password.setInputType(1);
                    try {
                        ((ClipboardManager) LoginFragment.this.getActivity().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text", LoginFragment.this.qb));
                        Toast.makeText(LoginFragment.this.getContext(), "新密碼已複製", 1).show();
                        Toast.makeText(LoginFragment.this.getContext(), "登入後請到「設定」修改密碼", 1).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        new c(LoginFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    LoginFragment.this.bI();
                }
                LoginFragment.this.bC();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        this.linearLayout_loginForm.setVisibility(4);
        Picasso.with(getContext()).load(2131231039).into(this.imageView_logo);
        if (getContext() == null || !e.x(getContext())) {
            this.imageView_logo.startAnimation(this.pY);
        }
        this.editText_email.setText(e.u(getActivity()));
        this.editText_password.setText(e.v(getActivity()));
        if (e.z(getActivity()) == null || e.z(getActivity()).isEmpty()) {
            bI();
        } else {
            dq();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (getActivity() != null) {
            f.D(TAG, "When will you run");
            this.linearLayout_loginForm.setVisibility(0);
            try {
                this.linearLayout_loginForm.startAnimation(this.pX);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getContext(), "Start Animation Error", 0).show();
            }
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.button_login.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LoginFragment.this.editText_password.length() < 8) {
                    AlertDialogCenter.passwordLength(LoginFragment.this.getActivity());
                } else {
                    LoginFragment.this.dr();
                }
            }
        });
        this.button_register.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    LoginFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new RegisterFragment(), RegisterFragment.TAG).addToBackStack(RegisterFragment.TAG).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(LoginFragment.this.getContext(), "Cannot Run Fragment Animation", 0).show();
                    LoginFragment.this.getFragmentManager().beginTransaction().replace(R.id.container, new RegisterFragment(), RegisterFragment.TAG).addToBackStack(RegisterFragment.TAG).commit();
                }
            }
        });
        this.button_forgotPassword.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View inflate = ((LayoutInflater) LoginFragment.this.getActivity().getSystemService("layout_inflater")).inflate(2131427388, (ViewGroup) view.getParent(), false);
                LoginFragment.this.pT = (EditText) inflate.findViewById(2131296460);
                LoginFragment.this.pT.addTextChangedListener(new TextWatcher() { // from class: com.picacomic.fregata.fragments.LoginFragment.3.1
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                        if (editable.length() < 2) {
                            LoginFragment.this.pT.setError(LoginFragment.this.getString(2131689857));
                            LoginFragment.this.pZ = false;
                        } else if (editable.toString().matches("^[0-9a-z_](\\.?[0-9a-z_]){1,29}$")) {
                            LoginFragment.this.pT.setError(null);
                            LoginFragment.this.pZ = true;
                        } else {
                            LoginFragment.this.pT.setError(LoginFragment.this.getString(2131689858));
                            LoginFragment.this.pZ = false;
                        }
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }
                });
                new AlertDialog.Builder(LoginFragment.this.getActivity(), 2131755210).setTitle(2131689834).setView(inflate).setCancelable(false).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.3.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (LoginFragment.this.pZ) {
                            LoginFragment.this.af(LoginFragment.this.pT.getText().toString());
                            return;
                        }
                        Toast.makeText(LoginFragment.this.getActivity(), 2131689588, 0).show();
                        if (LoginFragment.this.pT == null || LoginFragment.this.pT.getText() == null || LoginFragment.this.pT.getText().toString().equalsIgnoreCase("")) {
                            return;
                        }
                        LoginFragment.this.qa = LoginFragment.this.pT.getText().toString();
                    }
                }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.3.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });
        this.button_resendActivation.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View inflate = ((LayoutInflater) LoginFragment.this.getActivity().getSystemService("layout_inflater")).inflate(2131427388, (ViewGroup) view.getParent(), false);
                LoginFragment.this.pT = (EditText) inflate.findViewById(2131296460);
                LoginFragment.this.pT.addTextChangedListener(new TextWatcher() { // from class: com.picacomic.fregata.fragments.LoginFragment.4.1
                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                        LoginFragment.this.E(true);
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    }
                });
                new AlertDialog.Builder(LoginFragment.this.getActivity(), 2131755210).setTitle(2131689839).setView(inflate).setCancelable(false).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.4.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginFragment.this.E(true);
                        if (LoginFragment.this.pZ) {
                            return;
                        }
                        Toast.makeText(LoginFragment.this.getActivity(), 2131689567, 0).show();
                        if (LoginFragment.this.pT == null || LoginFragment.this.pT.getText() == null || LoginFragment.this.pT.getText().toString().equalsIgnoreCase("")) {
                            return;
                        }
                        LoginFragment.this.qa = LoginFragment.this.pT.getText().toString();
                    }
                }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.4.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });
    }

    public void dq() {
        getActivity().startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }

    public void dr() {
        C(getResources().getString(2131689831));
        this.pU = new d(getContext()).dO().a(new SignInBody(this.editText_email.getText().toString(), this.editText_password.getText().toString()));
        this.pU.enqueue(new Callback<GeneralResponse<SignInResponse>>() { // from class: com.picacomic.fregata.fragments.LoginFragment.7
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<SignInResponse>> call, Throwable th) {
                th.printStackTrace();
                LoginFragment.this.bI();
                LoginFragment.this.bC();
                new c(LoginFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<SignInResponse>> call, Response<GeneralResponse<SignInResponse>> response) {
                if (response.code() == 200) {
                    if (LoginFragment.this.getActivity() != null) {
                        e.e(LoginFragment.this.getActivity(), LoginFragment.this.editText_email.getText().toString());
                        e.f(LoginFragment.this.getActivity(), LoginFragment.this.editText_password.getText().toString());
                        e.h(LoginFragment.this.getActivity(), response.body().data.getToken());
                    }
                    LoginFragment.this.dq();
                } else {
                    try {
                        new c(LoginFragment.this.getActivity(), response.code(), response.errorBody().string(), new com.picacomic.fregata.a.g() { // from class: com.picacomic.fregata.fragments.LoginFragment.7.1
                            @Override // com.picacomic.fregata.a.g
                            public void a(int i, NetworkErrorObject networkErrorObject) {
                                AlertDialog.Builder title = new AlertDialog.Builder(LoginFragment.this.getActivity()).setTitle(networkErrorObject.getError());
                                title.setMessage(networkErrorObject.getMessage() + "\n" + networkErrorObject.getDetail()).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.LoginFragment.7.1.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int i2) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();
                            }
                        }).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    LoginFragment.this.bI();
                }
                LoginFragment.this.bC();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.pX = AnimationUtils.loadAnimation(getActivity(), 2130771997);
        this.pY = AnimationUtils.loadAnimation(getActivity(), 2130771998);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427420, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.pU != null) {
            this.pU.cancel();
        }
        if (this.pV != null) {
            this.pV.cancel();
        }
        if (this.pW != null) {
            this.pW.cancel();
        }
        super.onDetach();
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }
}
