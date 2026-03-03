package com.picacomic.fregata.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.BindView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picacomic.fregata.adapters.CustomPicaAppFragmentPagerAdapter;
import com.picacomic.fregata.objects.ChatroomListObject;
import com.picacomic.fregata.objects.PicaAppBaseObject;
import com.picacomic.fregata.objects.PicaAppObject;
import com.picacomic.fregata.utils.e;
import java.util.ArrayList;
import java.util.List;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/CustomPicaAppContainerFragment.class */
public class CustomPicaAppContainerFragment extends BaseFragment {
    public static final String TAG = "CustomPicaAppContainerFragment";
    @BindView(2131296499)
    FloatingActionButton fab_add;
    Gson gson;
    RelativeLayout.LayoutParams iI;
    private int iJ;
    private int iK;
    long iL = 0;
    ArrayList<PicaAppBaseObject> ja;
    ArrayList<ChatroomListObject> oK;
    ArrayList<PicaAppObject> oL;
    CustomPicaAppFragmentPagerAdapter oM;
    String[] oN;
    String[] oO;
    String[] oP;
    @BindView(2131297052)
    ViewPager viewPage_picaApp;

    @Override // com.picacomic.fregata.fragments.BaseFragment, com.picacomic.fregata.a.i
    public void b(View view) {
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.ja = new ArrayList<>();
        this.oM = new CustomPicaAppFragmentPagerAdapter(getChildFragmentManager(), this.ja);
        this.viewPage_picaApp.setAdapter(this.oM);
        this.fab_add.setLayoutParams(this.iI);
        this.fab_add.setOnTouchListener(new View.OnTouchListener() { // from class: com.picacomic.fregata.fragments.CustomPicaAppContainerFragment.3
            int iR;
            int iS;
            boolean iT;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                switch (motionEvent.getAction()) {
                    case 0:
                        this.iR = rawX;
                        this.iS = rawY;
                        this.iT = true;
                        CustomPicaAppContainerFragment.this.iL = System.currentTimeMillis();
                        CustomPicaAppContainerFragment.this.iJ = rawX - CustomPicaAppContainerFragment.this.iI.leftMargin;
                        CustomPicaAppContainerFragment.this.iK = rawY - CustomPicaAppContainerFragment.this.iI.topMargin;
                        return true;
                    case 1:
                        CustomPicaAppContainerFragment.this.fab_add.setLayoutParams(CustomPicaAppContainerFragment.this.iI);
                        if (System.currentTimeMillis() - CustomPicaAppContainerFragment.this.iL >= 500 || !this.iT) {
                            return true;
                        }
                        CustomPicaAppContainerFragment.this.dd();
                        return true;
                    case 2:
                        if (Math.abs(this.iR - rawX) >= 10 || Math.abs(this.iS - rawY) >= 10) {
                            this.iT = false;
                        } else {
                            this.iT = true;
                        }
                        CustomPicaAppContainerFragment.this.iI.leftMargin = rawX - CustomPicaAppContainerFragment.this.iJ;
                        CustomPicaAppContainerFragment.this.iI.topMargin = rawY - CustomPicaAppContainerFragment.this.iK;
                        CustomPicaAppContainerFragment.this.fab_add.setLayoutParams(CustomPicaAppContainerFragment.this.iI);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    public void dd() {
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(2131689560).setSingleChoiceItems(this.oN, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.CustomPicaAppContainerFragment.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                switch (i) {
                    case 0:
                        CustomPicaAppContainerFragment.this.de();
                        return;
                    case 1:
                        CustomPicaAppContainerFragment.this.df();
                        return;
                    default:
                        return;
                }
            }
        }).show();
    }

    public void de() {
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(2131689971).setSingleChoiceItems(this.oO, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.CustomPicaAppContainerFragment.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                CustomPicaAppContainerFragment.this.ja.add(CustomPicaAppContainerFragment.this.oK.get(i));
                CustomPicaAppContainerFragment.this.oM.notifyDataSetChanged();
            }
        }).show();
    }

    public void df() {
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(2131689981).setSingleChoiceItems(this.oP, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.CustomPicaAppContainerFragment.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                CustomPicaAppContainerFragment.this.ja.add(CustomPicaAppContainerFragment.this.oL.get(i));
                CustomPicaAppContainerFragment.this.oM.notifyDataSetChanged();
            }
        }).show();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.oN = new String[]{getString(2131689971), getString(2131689981)};
        if (this.gson == null) {
            this.gson = new Gson();
        }
        String G = e.G(getContext());
        if (G != null && !G.equalsIgnoreCase("")) {
            this.oK = (ArrayList) this.gson.fromJson(e.G(getContext()), new TypeToken<List<ChatroomListObject>>() { // from class: com.picacomic.fregata.fragments.CustomPicaAppContainerFragment.1
            }.getType());
            if (this.oK != null && this.oK.size() > 0) {
                this.oO = new String[this.oK.size()];
                for (int i = 0; i < this.oK.size(); i++) {
                    this.oO[i] = this.oK.get(i).getTitle();
                }
            }
        }
        String H = e.H(getContext());
        if (H != null && !H.equalsIgnoreCase("")) {
            this.oL = (ArrayList) this.gson.fromJson(e.H(getContext()), new TypeToken<List<PicaAppObject>>() { // from class: com.picacomic.fregata.fragments.CustomPicaAppContainerFragment.2
            }.getType());
            if (this.oL != null && this.oL.size() > 0) {
                this.oP = new String[this.oL.size()];
                for (int i2 = 0; i2 < this.oL.size(); i2++) {
                    this.oP[i2] = this.oL.get(i2).getTitle();
                }
            }
        }
        this.iI = new RelativeLayout.LayoutParams(-2, -2);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427411, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
