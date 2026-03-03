package com.picacomic.fregata.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ImagePopupFragment.class */
public class ImagePopupFragment extends DialogFragment {
    public static final String TAG = "ImagePopupFragment";
    public String pA;

    public static ImagePopupFragment ae(String str) {
        ImagePopupFragment imagePopupFragment = new ImagePopupFragment();
        Bundle bundle = new Bundle();
        bundle.putString("IMAGE_URL", str);
        imagePopupFragment.setArguments(bundle);
        return imagePopupFragment;
    }

    @Override // android.support.v4.app.DialogFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.pA = getArguments().getString("IMAGE_URL", null);
        }
    }

    @Override // android.support.v4.app.DialogFragment
    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        onCreateDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        onCreateDialog.setCancelable(true);
        onCreateDialog.setCanceledOnTouchOutside(true);
        onCreateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.picacomic.fregata.fragments.ImagePopupFragment.3
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
        View inflate = layoutInflater.inflate(2131427415, viewGroup, false);
        ImageView imageView = (ImageView) inflate.findViewById(2131296610);
        try {
            if (this.pA != null && !this.pA.equalsIgnoreCase("")) {
                Picasso.with(getContext()).load(this.pA).placeholder(2131231027).into(imageView);
            }
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        ((ImageButton) inflate.findViewById(2131296567)).setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ImagePopupFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImagePopupFragment.this.dismiss();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ImagePopupFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ImagePopupFragment.this.dismiss();
            }
        });
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
}
