package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.design.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import butterknife.BindView;
import com.picacomic.fregata.utils.a;
import com.picacomic.fregata.utils.g;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/SupportUsAdsGameFragment.class */
public class SupportUsAdsGameFragment extends BaseFragment {
    public static final String TAG = "SupportUsAdsGameFragment";
    @BindView(2131296630)
    ImageView imageView_game;
    @BindView(2131297063)
    WebView webView_ads;

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        g.k(this.webView_ads);
        this.webView_ads.loadUrl(a.dS());
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.imageView_game.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SupportUsAdsGameFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SupportUsAdsGameFragment.this.getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new GameFragment(), GameFragment.TAG).addToBackStack(GameFragment.TAG).commit();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427434, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
