package com.picacomic.fregata.fragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/SupportUsQQAlipayFragment.class */
public class SupportUsQQAlipayFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "SupportUsQQAlipayFragment";
    @BindView(2131297010)
    TextView textView_alipay;
    @BindView(2131297011)
    TextView textView_alipayTitle;

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.textView_alipayTitle.setOnClickListener(this);
        this.textView_alipay.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == 2131297010 || view.getId() == 2131297011) {
            FragmentActivity activity = getActivity();
            getActivity();
            ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", getString(2131689939)));
            Toast.makeText(getContext(), 2131689557, 0).show();
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427438, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
