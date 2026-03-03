package com.picacomic.fregata.fragments;

import android.os.Bundle;
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
import com.picacomic.fregata.activities.BaseActivity;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.utils.e;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChangePinFragment.class */
public class ChangePinFragment extends BaseFragment {
    public static final String TAG = "ChangePinFragment";
    @BindView(2131296310)
    Button button_cancel;
    @BindView(2131296309)
    Button button_change;
    @BindView(2131296448)
    EditText editText_pin;
    @BindView(2131296449)
    EditText editText_pinConfirm;
    boolean kK;
    boolean kL;
    @BindView(2131297037)
    Toolbar toolbar;

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, 2131689918, true);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        String y = e.y(getContext());
        if (y == null || y.length() <= 0 || getActivity() == null || !(getActivity() instanceof MainActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).bD();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.editText_pin.addTextChangedListener(new TextWatcher() { // from class: com.picacomic.fregata.fragments.ChangePinFragment.1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (ChangePinFragment.this.editText_pin.getText().toString().length() < 4) {
                    ChangePinFragment.this.editText_pin.setError(ChangePinFragment.this.getString(2131689589));
                    ChangePinFragment.this.kK = false;
                    return;
                }
                ChangePinFragment.this.editText_pin.setError(null);
                ChangePinFragment.this.kK = true;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.editText_pinConfirm.addTextChangedListener(new TextWatcher() { // from class: com.picacomic.fregata.fragments.ChangePinFragment.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (ChangePinFragment.this.editText_pin.getText().toString().equals(ChangePinFragment.this.editText_pinConfirm.getText().toString())) {
                    ChangePinFragment.this.editText_pinConfirm.setError(null);
                    ChangePinFragment.this.kL = true;
                    return;
                }
                ChangePinFragment.this.editText_pinConfirm.setError(ChangePinFragment.this.getString(2131689586));
                ChangePinFragment.this.kL = false;
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.button_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChangePinFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChangePinFragment.this.getActivity() == null || ChangePinFragment.this.getFragmentManager() == null) {
                    return;
                }
                Toast.makeText(ChangePinFragment.this.getActivity(), 2131689652, 0).show();
                e.g(ChangePinFragment.this.getContext(), "");
                ChangePinFragment.this.getFragmentManager().popBackStack();
            }
        });
        this.button_change.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChangePinFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChangePinFragment.this.getActivity() == null || ChangePinFragment.this.getFragmentManager() == null || !ChangePinFragment.this.kK || !ChangePinFragment.this.kL) {
                    return;
                }
                Toast.makeText(ChangePinFragment.this.getActivity(), 2131689653, 0).show();
                e.g(ChangePinFragment.this.getContext(), ChangePinFragment.this.editText_pinConfirm.getText().toString());
                ChangePinFragment.this.getFragmentManager().popBackStack();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.kK = false;
        this.kL = false;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427398, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
