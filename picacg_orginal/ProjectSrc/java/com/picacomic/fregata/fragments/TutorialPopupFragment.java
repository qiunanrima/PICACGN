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
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.picacomic.fregata.utils.g;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/TutorialPopupFragment.class */
public class TutorialPopupFragment extends DialogFragment {
    public static final String TAG = "TutorialPopupFragment";
    public String pA;

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
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(onCreateDialog.getWindow().getAttributes());
        layoutParams.width = g.as(getContext());
        layoutParams.height = g.at(getContext());
        onCreateDialog.getWindow().setAttributes(layoutParams);
        onCreateDialog.setCancelable(false);
        onCreateDialog.setCanceledOnTouchOutside(false);
        onCreateDialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.picacomic.fregata.fragments.TutorialPopupFragment.2
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
            imageView.setImageResource(2131231059);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        ((ImageButton) inflate.findViewById(2131296567)).setVisibility(8);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.TutorialPopupFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TutorialPopupFragment.this.dismiss();
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
