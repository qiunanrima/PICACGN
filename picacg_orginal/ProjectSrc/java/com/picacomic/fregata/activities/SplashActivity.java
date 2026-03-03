package com.picacomic.fregata.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.MyApplication;
import com.picacomic.fregata.objects.responses.WakaInitResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import java.util.ArrayList;
import java.util.HashSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/activities/SplashActivity.class */
public class SplashActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "SplashActivity";
    public static boolean iV = false;
    @BindView(2131296394)
    Button button_error;
    @BindView(2131296395)
    Button button_server1;
    @BindView(2131296396)
    Button button_server2;
    @BindView(2131296397)
    Button button_server3;
    Call<WakaInitResponse> iW;
    @BindView(2131296699)
    LinearLayout linearLayout_error;
    @BindView(2131296700)
    LinearLayout linearLayout_options;

    public void bZ() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.button_server1) {
            e.p((Context) this, false);
            e.i(this, 1);
            bZ();
        }
        if (view == this.button_server2) {
            e.p((Context) this, true);
            e.i(this, 2);
            try {
                MyApplication.bw();
                bZ();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(MyApplication.by(), 2131689891, 1).show();
                finishAffinity();
                finish();
                System.exit(0);
            }
        }
        if (view == this.button_server3) {
            e.p((Context) this, true);
            e.i(this, 3);
            try {
                bZ();
            } catch (Exception e2) {
                e2.printStackTrace();
                Toast.makeText(MyApplication.by(), 2131689891, 1).show();
                finishAffinity();
                finish();
                System.exit(0);
            }
        }
        if (view != this.button_error || this.linearLayout_error == null) {
            return;
        }
        this.linearLayout_error.setVisibility(8);
        w(3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131427361);
        ButterKnife.bind(this);
        w(3);
        this.button_server1.setOnClickListener(this);
        this.button_server2.setOnClickListener(this);
        this.button_server3.setOnClickListener(this);
        this.button_error.setOnClickListener(this);
        if (e.am(this) == null || e.am(this).equalsIgnoreCase("") || e.ao(this) == null) {
            return;
        }
        this.linearLayout_options.setVisibility(0);
    }

    public void w(final int i) {
        this.iW = new com.picacomic.fregata.b.e(this).dO().dM();
        this.iW.enqueue(new Callback<WakaInitResponse>() { // from class: com.picacomic.fregata.activities.SplashActivity.1
            @Override // retrofit2.Callback
            public void onFailure(Call<WakaInitResponse> call, Throwable th) {
                th.printStackTrace();
                int i2 = i - 1;
                if (i2 < 0) {
                    if (SplashActivity.this.linearLayout_error != null) {
                        SplashActivity.this.linearLayout_error.setVisibility(0);
                    }
                } else if (SplashActivity.this.linearLayout_error != null) {
                    SplashActivity.iV = true;
                    SplashActivity.this.w(i2);
                }
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<WakaInitResponse> call, Response<WakaInitResponse> response) {
                if (response == null || response.body() == null || !response.body().getStatus().equalsIgnoreCase("OK")) {
                    int i2 = i - 1;
                    if (i2 < 0) {
                        if (SplashActivity.this.linearLayout_options != null) {
                            SplashActivity.this.linearLayout_error.setVisibility(0);
                            return;
                        }
                        return;
                    } else if (SplashActivity.this.linearLayout_options != null) {
                        SplashActivity.this.w(i2);
                        return;
                    } else {
                        return;
                    }
                }
                if (response.body().getWaka() != null) {
                    f.aA("ADS BASE LINK = " + response.body().getWaka());
                    e.y(MyApplication.by(), response.body().getWaka());
                }
                if (response.body().getAdKeyword() != null) {
                    e.z(MyApplication.by(), response.body().getAdKeyword());
                }
                if (response.body().getAddresses() != null && response.body().getAddresses().size() > 0) {
                    ArrayList<String> addresses = response.body().getAddresses();
                    String str = SplashActivity.TAG;
                    f.F(str, "ADDRESS IP = " + response.body().getAddresses().toString());
                    e.a(MyApplication.by(), new HashSet(addresses));
                }
                if (SplashActivity.this.linearLayout_options != null) {
                    SplashActivity.this.linearLayout_options.setVisibility(0);
                }
                if (response.body().getMessage() == null || response.body().getMessage().length() <= 1 || SplashActivity.this == null) {
                    return;
                }
                AlertDialogCenter.showAnnouncementAlertDialog(SplashActivity.this, null, null, response.body().getMessage(), null, null);
            }
        });
    }
}
