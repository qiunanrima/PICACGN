package com.picacomic.fregata.activities;

import android.os.Bundle;
import android.support.design.R;
import com.picacomic.fregata.fragments.CommentFragment;
import com.picacomic.fregata.fragments.SettingFragment;
/* loaded from: picacg_1.jar:com/picacomic/fregata/activities/PopupActivity.class */
public class PopupActivity extends BaseActivity {
    public static final String TAG = "PopupActivity";

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131427356);
        String stringExtra = getIntent().getStringExtra("EXTRA_KEY_TYPE");
        if (stringExtra == null) {
            finish();
        }
        if (bundle == null) {
            if (!stringExtra.equalsIgnoreCase("TYPE_KEY_COMMENT")) {
                if (stringExtra.equalsIgnoreCase("TYPE_KEY_SETTING")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new SettingFragment(), SettingFragment.TAG).commit();
                    return;
                }
                return;
            }
            String stringExtra2 = getIntent().getStringExtra("EXTRA_KEY_COMIC_ID");
            if (stringExtra2 == null) {
                finish();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, CommentFragment.l(null, stringExtra2), CommentFragment.TAG).commit();
            }
        }
    }
}
