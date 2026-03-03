package com.picacomic.fregata.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.picacomic.fregata.utils.e;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProgressDialogFragment.class */
public class ProgressDialogFragment extends DialogFragment {
    public static final String TAG = "ProgressDialogFragment";
    public String rd;
    AnimationDrawable re;

    public static ProgressDialogFragment ai(String str) {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("PROGRESS_MESSAGE", str);
        progressDialogFragment.setArguments(bundle);
        return progressDialogFragment;
    }

    public static ProgressDialogFragment dH() {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        progressDialogFragment.setArguments(new Bundle());
        return progressDialogFragment;
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.rd = getArguments().getString("PROGRESS_MESSAGE", "");
        } else {
            this.rd = "";
        }
    }

    @Override // android.support.v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        onCreateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        onCreateDialog.setCancelable(false);
        onCreateDialog.setCanceledOnTouchOutside(true);
        onCreateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.picacomic.fregata.fragments.ProgressDialogFragment.1
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 || i == 84;
            }
        });
        return onCreateDialog;
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427429, (ViewGroup) null, false);
        ImageView imageView = (ImageView) inflate.findViewById(2131296616);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(2131296739);
        TextView textView = (TextView) inflate.findViewById(2131296986);
        if (getContext() != null && progressBar != null && imageView != null && textView != null) {
            if (e.x(getContext())) {
                progressBar.setVisibility(0);
                imageView.setVisibility(8);
            } else {
                progressBar.setVisibility(8);
                imageView.setVisibility(0);
                try {
                    Picasso.with(getContext()).load("TestingLink").placeholder(2131231006).into(imageView);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
            textView.setText(this.rd);
        }
        return inflate;
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onDetach() {
        System.gc();
        if (isAdded()) {
            dismiss();
            this.re.stop();
            this.re = null;
        }
        super.onDetach();
    }
}
