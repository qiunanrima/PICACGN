package com.picacomic.fregata.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.R;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.picacomic.fregata.a.f;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.fragments.CropImageFragment;
import com.picacomic.fregata.objects.requests.AvatarBody;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.PutAvatarResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.g;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/activities/ImageCropActivity.class */
public class ImageCropActivity extends BaseActivity implements f {
    public static final String TAG = "ImageCropActivity";
    Call<GeneralResponse<PutAvatarResponse>> ir;
    String is;
    public int type;

    @Override // com.picacomic.fregata.a.f
    public void b(Uri uri) {
        Intent intent = new Intent();
        intent.putExtra("CROP_IMAGE_RESULT_URI", uri.toString());
        setResult(-1, intent);
        switch (this.type) {
            case 1:
                c(uri);
                return;
            case 2:
                this.is = null;
                finish();
                return;
            default:
                this.is = null;
                finish();
                return;
        }
    }

    public void c(Uri uri) {
        C(getResources().getString(2131689830));
        com.picacomic.fregata.utils.f.aA("Show Progress");
        d dVar = new d(this);
        try {
            this.ir = dVar.dO().a(e.z(this), new AvatarBody(g.f(g.c(this, uri, ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION))));
            this.ir.enqueue(new Callback<GeneralResponse<PutAvatarResponse>>() { // from class: com.picacomic.fregata.activities.ImageCropActivity.1
                @Override // retrofit2.Callback
                public void onFailure(Call<GeneralResponse<PutAvatarResponse>> call, Throwable th) {
                    th.printStackTrace();
                    com.picacomic.fregata.utils.f.aA("dismiss progress");
                    ImageCropActivity.this.bC();
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<GeneralResponse<PutAvatarResponse>> call, Response<GeneralResponse<PutAvatarResponse>> response) {
                    if (response.code() != 200) {
                        try {
                            new c(ImageCropActivity.this, response.code(), response.errorBody().string()).dN();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.body() != null && response.body().data != null && response.body().data.getAvatar() != null) {
                        ImageCropActivity.this.is = null;
                        ImageCropActivity.this.finish();
                    }
                    com.picacomic.fregata.utils.f.aA("dismiss progress");
                    ImageCropActivity.this.bC();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.picacomic.fregata.activities.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131427358);
        Intent intent = getIntent();
        if (intent == null) {
            setResult(0);
            finish();
            return;
        }
        this.is = intent.getStringExtra("KEY_IMAGE_URI_STRING");
        this.type = intent.getIntExtra("KEY_ACTION_TYPE", 2);
        getSupportFragmentManager().beginTransaction().add(R.id.container, CropImageFragment.a(this.is, this.type), CropImageFragment.TAG).commit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        if (this.ir != null) {
            this.ir.cancel();
        }
        super.onDestroy();
    }
}
