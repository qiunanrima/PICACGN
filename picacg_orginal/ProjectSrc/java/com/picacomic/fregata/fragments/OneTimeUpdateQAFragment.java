package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.design.R;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.requests.UpdateQandABody;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/OneTimeUpdateQAFragment.class */
public class OneTimeUpdateQAFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "OneTimeUpdateQAFragment";
    @BindView(2131296409)
    Button button_update;
    @BindView(2131296471)
    EditText editText_answer_1;
    @BindView(2131296472)
    EditText editText_answer_2;
    @BindView(2131296473)
    EditText editText_answer_3;
    @BindView(2131296477)
    EditText editText_question_1;
    @BindView(2131296478)
    EditText editText_question_2;
    @BindView(2131296479)
    EditText editText_question_3;
    Call<GeneralResponse> qr;
    @BindView(2131297037)
    Toolbar toolbar;

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        C(getResources().getString(2131689826));
        this.qr = new d(getContext()).dO().a(e.z(getActivity()), new UpdateQandABody(str, str2, str3, str4, str5, str6));
        this.qr.enqueue(new Callback<GeneralResponse>() { // from class: com.picacomic.fregata.fragments.OneTimeUpdateQAFragment.1
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse> call, Throwable th) {
                th.printStackTrace();
                OneTimeUpdateQAFragment.this.bC();
                new c(OneTimeUpdateQAFragment.this.getActivity()).dN();
                OneTimeUpdateQAFragment.this.bI();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                if (response.code() == 200) {
                    OneTimeUpdateQAFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new OneTimeIdUpdateFragment(), OneTimeIdUpdateFragment.TAG).addToBackStack(OneTimeIdUpdateFragment.TAG).commit();
                } else {
                    try {
                        new c(OneTimeUpdateQAFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                OneTimeUpdateQAFragment.this.bC();
                OneTimeUpdateQAFragment.this.bI();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        }
        a(this.toolbar, 2131689980, true);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if ((this.editText_question_1 == null || this.editText_question_1.getText().length() >= 1) && ((this.editText_question_2 == null || this.editText_question_2.getText().length() >= 1) && ((this.editText_question_3 == null || this.editText_question_3.getText().length() >= 1) && ((this.editText_answer_1 == null || this.editText_answer_1.getText().length() >= 1) && ((this.editText_answer_2 == null || this.editText_answer_2.getText().length() >= 1) && (this.editText_answer_3 == null || this.editText_answer_3.getText().length() >= 1)))))) {
            return;
        }
        AlertDialogCenter.showCustomAlertDialog(getContext(), 2131230947, 2131689565);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.button_update.setOnClickListener(this);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != this.button_update || this.editText_question_1 == null || this.editText_question_1.getText().length() <= 0 || this.editText_question_2 == null || this.editText_question_2.getText().length() <= 0 || this.editText_question_3 == null || this.editText_question_3.getText().length() <= 0 || this.editText_answer_1 == null || this.editText_answer_1.getText().length() <= 0 || this.editText_answer_2 == null || this.editText_answer_2.getText().length() <= 0 || this.editText_answer_3 == null || this.editText_answer_3.getText().length() <= 0) {
            return;
        }
        a(this.editText_question_1.getText().toString(), this.editText_question_2.getText().toString(), this.editText_question_3.getText().toString(), this.editText_answer_1.getText().toString(), this.editText_answer_2.getText().toString(), this.editText_answer_3.getText().toString());
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427423, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
