package com.picacomic.fregata.fragments;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.picacomic.fregata.utils.e;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ProgressLoadingFragment.class */
public class ProgressLoadingFragment extends DialogFragment {
    public static final String TAG = "ProgressLoadingFragment";
    AnimationDrawable re;

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(3, 0);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        getDialog().getWindow().setGravity(49);
        View inflate = layoutInflater.inflate(2131427430, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(2131296616);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(2131296739);
        if (getContext() != null && imageView != null && progressBar != null) {
            if (e.x(getContext())) {
                imageView.setVisibility(8);
                progressBar.setVisibility(0);
            } else {
                imageView.setVisibility(0);
                progressBar.setVisibility(8);
                try {
                    Picasso.with(getContext()).load("TestingLink").placeholder(2131231005).into(imageView);
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
        }
        return inflate;
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (isAdded()) {
            dismiss();
            this.re.stop();
            this.re = null;
        }
        super.onDetach();
    }
}
