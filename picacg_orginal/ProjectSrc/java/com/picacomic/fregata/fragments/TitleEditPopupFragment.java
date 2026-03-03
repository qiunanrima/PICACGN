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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.requests.UpdateUserTitleBody;
import com.picacomic.fregata.objects.responses.RegisterResponse;
import com.picacomic.fregata.utils.e;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/TitleEditPopupFragment.class */
public class TitleEditPopupFragment extends DialogFragment {
    public static final String TAG = "TitleEditPopupFragment";
    @BindView(2131296407)
    Button button_cancel;
    @BindView(2131296408)
    Button button_confirm;
    @BindView(2131296486)
    EditText editText_newTitle;
    Call<RegisterResponse> lg;
    public String rY;
    @BindView(2131297017)
    TextView textView_title;
    @BindView(2131297018)
    TextView textView_userId;
    public String userId;

    public static TitleEditPopupFragment o(String str, String str2) {
        TitleEditPopupFragment titleEditPopupFragment = new TitleEditPopupFragment();
        Bundle bundle = new Bundle();
        bundle.putString("USER_ID", str);
        bundle.putString("USER_TITLE", str2);
        titleEditPopupFragment.setArguments(bundle);
        return titleEditPopupFragment;
    }

    public void aj(String str) {
        this.lg = new d(getContext()).dO().a(e.z(getActivity()), this.userId, new UpdateUserTitleBody(str));
        this.lg.enqueue(new Callback<RegisterResponse>() { // from class: com.picacomic.fregata.fragments.TitleEditPopupFragment.4
            @Override // retrofit2.Callback
            public void onFailure(Call<RegisterResponse> call, Throwable th) {
                th.printStackTrace();
                new c(TitleEditPopupFragment.this.getActivity()).dN();
                Toast.makeText(TitleEditPopupFragment.this.getContext(), "Update Title Failed", 0).show();
                TitleEditPopupFragment.this.dismiss();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(TitleEditPopupFragment.this.getContext(), "Update Title Success", 0).show();
                    TitleEditPopupFragment.this.dismiss();
                    return;
                }
                try {
                    new c(TitleEditPopupFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(TitleEditPopupFragment.this.getContext(), "Update Title Failed", 0).show();
                TitleEditPopupFragment.this.dismiss();
            }
        });
    }

    public void bF() {
        this.button_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.TitleEditPopupFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TitleEditPopupFragment.this.dismiss();
            }
        });
        this.button_confirm.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.TitleEditPopupFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TitleEditPopupFragment.this.editText_newTitle == null || TitleEditPopupFragment.this.editText_newTitle.getText() == null || TitleEditPopupFragment.this.editText_newTitle.getText().toString() == null) {
                    return;
                }
                TitleEditPopupFragment.this.aj(TitleEditPopupFragment.this.editText_newTitle.getText().toString());
            }
        });
    }

    public void bI() {
        if (this.userId == null) {
            if (getContext() != null) {
                dismiss();
                return;
            }
            return;
        }
        if (this.textView_userId != null) {
            TextView textView = this.textView_userId;
            textView.setText(this.userId + "");
        }
        if (this.textView_title == null || this.rY == null) {
            return;
        }
        TextView textView2 = this.textView_title;
        textView2.setText(this.rY + "");
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.userId = getArguments().getString("USER_ID", null);
            this.rY = getArguments().getString("USER_TITLE", null);
        }
    }

    @Override // android.support.v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        onCreateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        onCreateDialog.setCancelable(true);
        onCreateDialog.setCanceledOnTouchOutside(true);
        onCreateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.picacomic.fregata.fragments.TitleEditPopupFragment.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 || i == 84;
            }
        });
        return onCreateDialog;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427439, viewGroup, false);
        ButterKnife.bind(this, inflate);
        bF();
        bI();
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
        if (this.lg != null) {
            this.lg.cancel();
        }
    }
}
