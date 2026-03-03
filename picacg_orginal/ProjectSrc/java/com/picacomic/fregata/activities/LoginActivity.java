package com.picacomic.fregata.activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.R;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.picacomic.fregata.fragments.LoginFragment;
import com.picacomic.fregata.utils.e;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/activities/LoginActivity.class */
public class LoginActivity extends BaseActivity {
    CountDownTimer countDownTimer;
    FrameLayout frameLayout_backgroundWhite;
    ImageView iu;
    ImageView iv;
    Animation iw;
    Animation ix;
    Animation iy;

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Type inference failed for: r1v24, types: [com.picacomic.fregata.activities.LoginActivity$1] */
    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131427359);
        this.iu = (ImageView) findViewById(2131296569);
        this.iv = (ImageView) findViewById(2131296570);
        this.frameLayout_backgroundWhite = (FrameLayout) findViewById(2131296506);
        Picasso.with(this).load(2131231036).into(this.iu);
        Picasso.with(this).load(2131231037).into(this.iv);
        this.iw = AnimationUtils.loadAnimation(this, 2130771994);
        this.iy = AnimationUtils.loadAnimation(this, 2130771995);
        this.ix = AnimationUtils.loadAnimation(this, 2130771996);
        if (bundle == null) {
            try {
                if (e.x(this)) {
                    this.iv.setVisibility(8);
                    this.frameLayout_backgroundWhite.setVisibility(8);
                } else {
                    this.iu.startAnimation(this.iy);
                    this.iv.startAnimation(this.iw);
                    this.frameLayout_backgroundWhite.startAnimation(this.ix);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Start Animation Error", 0).show();
            }
            this.countDownTimer = new CountDownTimer(getResources().getInteger(2131361797), getResources().getInteger(2131361796)) { // from class: com.picacomic.fregata.activities.LoginActivity.1
                @Override // android.os.CountDownTimer
                public void onFinish() {
                    if (LoginActivity.this.getApplicationContext() != null && e.x(LoginActivity.this.getApplicationContext())) {
                        LoginActivity.this.iv.setVisibility(0);
                        LoginActivity.this.frameLayout_backgroundWhite.setVisibility(0);
                    }
                    try {
                        LoginActivity.this.getSupportFragmentManager().beginTransaction().add(R.id.container, new LoginFragment(), LoginFragment.TAG).commit();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j) {
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
        }
        super.onDestroy();
    }
}
