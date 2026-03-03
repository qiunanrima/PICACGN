package com.picacomic.fregata.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import butterknife.BindView;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.picacomic.fregata.MyApplication;
import com.picacomic.fregata.a.j;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/PicaAppFragment.class */
public class PicaAppFragment extends BaseFragment implements j {
    public static final String TAG = "PicaAppFragment";
    @BindView(2131296702)
    LinearLayout linearLayoutWeb;
    AgentWeb mAgentWeb;
    String qw;
    String qx;
    String qy;
    @BindView(2131297037)
    Toolbar toolbar;

    public static PicaAppFragment n(String str, String str2) {
        PicaAppFragment picaAppFragment = new PicaAppFragment();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_PICA_APP_NAME", str);
        bundle.putString("KEY_PICA_APP_URL", str2);
        picaAppFragment.setArguments(bundle);
        return picaAppFragment;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, com.picacomic.fregata.a.i
    public void b(View view) {
        if (getParentFragment() != null) {
            if ((getParentFragment() instanceof ChatroomContainerFragment) || (getParentFragment() instanceof CustomPicaAppContainerFragment)) {
                AlertDialogCenter.showCustomAlertDialog(getActivity(), 2131230957, 2131689548, 2131689547, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.PicaAppFragment.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        PicaAppFragment.this.getParentFragment().getFragmentManager().popBackStack();
                        PicaAppFragment.this.getFragmentManager().popBackStack();
                    }
                }, (View.OnClickListener) null);
            }
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        this.toolbar.setVisibility(8);
        AgentWeb.e L = AgentWeb.a(this).a(this.linearLayoutWeb, new LinearLayout.LayoutParams(-1, -1)).K().a(DefaultWebClient.OpenOtherPageWays.ASK).a(new WebViewClient() { // from class: com.picacomic.fregata.fragments.PicaAppFragment.1
            @Override // android.webkit.WebViewClient
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                super.onReceivedError(webView, i, str, str2);
                webView.loadUrl("file:///android_res/raw/page_not_found.html");
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                if (Build.VERSION.SDK_INT >= 21) {
                    String str = PicaAppFragment.TAG;
                    f.F(str, "Version URL = " + webResourceRequest.getUrl().toString());
                    if (webResourceRequest.getUrl().toString().contains(PicaAppFragment.this.qy)) {
                        return false;
                    }
                    g.A(MyApplication.by(), webResourceRequest.getUrl().toString());
                    return true;
                }
                return false;
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                String str2 = PicaAppFragment.TAG;
                f.F(str2, "URL = " + str);
                if (str.contains(PicaAppFragment.this.qy)) {
                    return false;
                }
                g.A(MyApplication.by(), str);
                return true;
            }
        }).J().L();
        this.mAgentWeb = L.a(this.qx + "?token=" + e.z(getContext()) + "&secret=pb6XkQ94iBBny1WUAxY0dY5fksexw0dt");
        this.mAgentWeb.A().n().setJavaScriptEnabled(true);
        this.mAgentWeb.A().n().setJavaScriptCanOpenWindowsAutomatically(true);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.a.j
    public void backButton() {
        getFragmentManager().popBackStack();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
    }

    @Override // com.picacomic.fregata.a.j
    public void fullScreen() {
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        this.qy = e.an(MyApplication.by());
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() == null) {
            getFragmentManager().popBackStack();
            return;
        }
        this.qw = getArguments().getString("KEY_PICA_APP_NAME");
        this.qx = getArguments().getString("KEY_PICA_APP_URL");
        if (this.qx == null || this.qx.equalsIgnoreCase("")) {
            getFragmentManager().popBackStack();
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427424, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDestroyView() {
        this.mAgentWeb.x().onDestroy();
        super.onDestroyView();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        this.mAgentWeb.x().onPause();
        super.onPause();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        this.mAgentWeb.x().onResume();
        super.onResume();
    }

    @Override // com.picacomic.fregata.a.j
    public void rotation() {
    }
}
