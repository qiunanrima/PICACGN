package com.just.agentweb;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.just.agentweb.DefaultWebClient;
import java.lang.ref.WeakReference;
import java.util.Map;
/* loaded from: picacg_1.jar:com/just/agentweb/AgentWeb.class */
public final class AgentWeb {
    private static final String TAG = "AgentWeb";
    private WebViewClient cA;
    private boolean cB;
    private s cC;
    private ArrayMap<String, Object> cD;
    private int cE;
    private ar cF;
    private at<as> cG;
    private as cH;
    private WebChromeClient cI;
    private SecurityType cJ;
    private com.just.agentweb.d cK;
    private z cL;
    private t cM;
    private aq cN;
    private u cO;
    private boolean cP;
    private aj cQ;
    private boolean cR;
    private int cS;
    private ai cT;
    private ah cU;
    private ae cV;
    private ap cw;
    private r cx;
    private x cy;
    private WebChromeClient cz;
    private Activity mActivity;
    private AgentWeb mAgentWeb;
    private ViewGroup mViewGroup;

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWeb$SecurityType.class */
    public enum SecurityType {
        DEFAULT_CHECK,
        STRICT_CHECK
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWeb$a.class */
    public static final class a {
        private WebViewClient cA;
        private s cC;
        private Fragment cW;
        private BaseIndicatorView cX;
        private ap cw;
        private r cx;
        private WebChromeClient cz;
        private ArrayMap<String, Object> da;
        private WebView db;
        private com.just.agentweb.b dd;
        private ai df;
        private View di;
        private int dj;
        private int dk;
        private Activity mActivity;
        private int mTag;
        private ViewGroup mViewGroup;
        private int mIndex = -1;
        private x cy = null;
        private boolean cB = true;
        private ViewGroup.LayoutParams cY = null;
        private int mIndicatorColor = -1;
        private q cZ = null;
        private int mHeight = -1;
        private SecurityType cJ = SecurityType.DEFAULT_CHECK;
        private boolean cP = true;
        private w dc = null;
        private aj cQ = null;

        /* renamed from: de  reason: collision with root package name */
        private DefaultWebClient.OpenOtherPageWays f5de = null;
        private boolean cR = false;
        private ah dg = null;
        private ah dh = null;

        public a(@NonNull Activity activity, @NonNull Fragment fragment) {
            this.mTag = -1;
            this.mActivity = activity;
            this.cW = fragment;
            this.mTag = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public e I() {
            if (this.mTag == 1 && this.mViewGroup == null) {
                throw new NullPointerException("ViewGroup is null,Please check your parameters .");
            }
            return new e(p.a(new AgentWeb(this), this));
        }

        public c a(@NonNull ViewGroup viewGroup, @NonNull ViewGroup.LayoutParams layoutParams) {
            this.mViewGroup = viewGroup;
            this.cY = layoutParams;
            return new c(this);
        }
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWeb$b.class */
    public static class b {
        private a dl;

        public b(a aVar) {
            this.dl = aVar;
        }

        public e J() {
            return this.dl.I();
        }

        public b a(@Nullable WebViewClient webViewClient) {
            this.dl.cA = webViewClient;
            return this;
        }

        public b a(@Nullable DefaultWebClient.OpenOtherPageWays openOtherPageWays) {
            this.dl.f5de = openOtherPageWays;
            return this;
        }
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWeb$c.class */
    public static class c {
        private a dl;

        public c(a aVar) {
            this.dl = null;
            this.dl = aVar;
        }

        public b K() {
            this.dl.cB = true;
            return new b(this.dl);
        }
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWeb$d.class */
    private static final class d implements aj {
        private WeakReference<aj> dm;

        private d(aj ajVar) {
            this.dm = new WeakReference<>(ajVar);
        }

        @Override // com.just.agentweb.aj
        public boolean a(String str, String[] strArr, String str2) {
            if (this.dm.get() == null) {
                return false;
            }
            return this.dm.get().a(str, strArr, str2);
        }
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/AgentWeb$e.class */
    public static class e {

        /* renamed from: do  reason: not valid java name */
        private boolean f0do = false;
        private AgentWeb mAgentWeb;

        e(AgentWeb agentWeb) {
            this.mAgentWeb = agentWeb;
        }

        public e L() {
            if (!this.f0do) {
                this.mAgentWeb.G();
                this.f0do = true;
            }
            return this;
        }

        public AgentWeb a(@Nullable String str) {
            if (!this.f0do) {
                L();
            }
            return this.mAgentWeb.a(str);
        }
    }

    private AgentWeb(a aVar) {
        this.mAgentWeb = null;
        this.cD = new ArrayMap<>();
        this.cE = 0;
        this.cG = null;
        this.cH = null;
        this.cJ = SecurityType.DEFAULT_CHECK;
        this.cK = null;
        this.cL = null;
        this.cM = null;
        this.cO = null;
        this.cP = true;
        this.cR = false;
        this.cS = -1;
        this.cV = null;
        this.cE = aVar.mTag;
        this.mActivity = aVar.mActivity;
        this.mViewGroup = aVar.mViewGroup;
        this.cC = aVar.cC;
        this.cB = aVar.cB;
        this.cw = aVar.cw == null ? a(aVar.cX, aVar.mIndex, aVar.cY, aVar.mIndicatorColor, aVar.mHeight, aVar.db, aVar.dc) : aVar.cw;
        this.cy = aVar.cy;
        this.cz = aVar.cz;
        this.cA = aVar.cA;
        this.mAgentWeb = this;
        this.cx = aVar.cx;
        if (aVar.da != null && !aVar.da.isEmpty()) {
            this.cD.putAll((Map<? extends String, ? extends Object>) aVar.da);
            String str = TAG;
            ag.c(str, "mJavaObject size:" + this.cD.size());
        }
        this.cQ = aVar.cQ == null ? null : new d(aVar.cQ);
        this.cJ = aVar.cJ;
        this.cM = new am(this.cw.ah().getWebView(), aVar.cZ);
        if (this.cw.ac() instanceof WebParentLayout) {
            WebParentLayout webParentLayout = (WebParentLayout) this.cw.ac();
            webParentLayout.a(aVar.dd == null ? g.M() : aVar.dd);
            webParentLayout.e(aVar.dj, aVar.dk);
            webParentLayout.setErrorView(aVar.di);
        }
        this.cN = new o(this.cw.getWebView());
        this.cG = new au(this.cw.getWebView(), this.mAgentWeb.cD, this.cJ);
        this.cP = aVar.cP;
        this.cR = aVar.cR;
        if (aVar.f5de != null) {
            this.cS = aVar.f5de.code;
        }
        this.cT = aVar.df;
        this.cU = aVar.dg;
        init();
    }

    private void D() {
        as asVar = this.cH;
        av avVar = asVar;
        if (asVar == null) {
            avVar = av.ax();
            this.cH = avVar;
        }
        this.cG.b(avVar);
    }

    private void E() {
        ArrayMap<String, Object> arrayMap = this.cD;
        com.just.agentweb.d dVar = new com.just.agentweb.d(this, this.mActivity);
        this.cK = dVar;
        arrayMap.put("agentWeb", dVar);
    }

    private u F() {
        return this.cO == null ? new an(this.mActivity, this.cw.getWebView()) : this.cO;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AgentWeb G() {
        com.just.agentweb.c.b(this.mActivity.getApplicationContext());
        r rVar = this.cx;
        com.just.agentweb.a aVar = rVar;
        if (rVar == null) {
            aVar = f.m();
            this.cx = aVar;
        }
        boolean z = aVar instanceof com.just.agentweb.a;
        if (z) {
            ((com.just.agentweb.a) aVar).a(this);
        }
        if (this.cF == null && z) {
            this.cF = (ar) aVar;
        }
        aVar.a(this.cw.getWebView());
        if (this.cV == null) {
            this.cV = af.a(this.cw.getWebView(), this.cJ);
        }
        String str = TAG;
        ag.c(str, "mJavaObjects:" + this.cD.size());
        if (this.cD != null && !this.cD.isEmpty()) {
            this.cV.a(this.cD);
        }
        if (this.cF != null) {
            this.cF.a(this.cw.getWebView(), (DownloadListener) null);
            this.cF.a(this.cw.getWebView(), H());
            this.cF.a(this.cw.getWebView(), getWebViewClient());
        }
        return this;
    }

    private WebChromeClient H() {
        y a2 = this.cy == null ? y.am().a(this.cw.ag()) : this.cy;
        Activity activity = this.mActivity;
        this.cy = a2;
        WebChromeClient webChromeClient = this.cz;
        u F = F();
        this.cO = F;
        k kVar = new k(activity, a2, webChromeClient, F, this.cQ, this.cw.getWebView());
        ag.c(TAG, "WebChromeClient:" + this.cz);
        ah ahVar = this.cU;
        if (ahVar == null) {
            this.cI = kVar;
            return kVar;
        }
        ah ahVar2 = ahVar;
        int i = 1;
        while (ahVar2.aq() != null) {
            ahVar2 = ahVar2.aq();
            i++;
        }
        ag.c(TAG, "MiddlewareWebClientBase middleware count:" + i);
        ahVar2.a(kVar);
        this.cI = ahVar;
        return ahVar;
    }

    public static a a(@NonNull Fragment fragment) {
        FragmentActivity activity = fragment.getActivity();
        if (activity != null) {
            return new a(activity, fragment);
        }
        throw new NullPointerException("activity can not be null .");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AgentWeb a(String str) {
        x B;
        C().loadUrl(str);
        if (!TextUtils.isEmpty(str) && (B = B()) != null && B.ak() != null) {
            B().ak().show();
        }
        return this;
    }

    private ap a(BaseIndicatorView baseIndicatorView, int i, ViewGroup.LayoutParams layoutParams, int i2, int i3, WebView webView, w wVar) {
        if (baseIndicatorView == null || !this.cB) {
            return this.cB ? new n(this.mActivity, this.mViewGroup, layoutParams, i, i2, i3, webView, wVar) : new n(this.mActivity, this.mViewGroup, layoutParams, i, webView, wVar);
        }
        return new n(this.mActivity, this.mViewGroup, layoutParams, i, baseIndicatorView, webView, wVar);
    }

    private WebViewClient getWebViewClient() {
        ag.c(TAG, "getDelegate:" + this.cT);
        DefaultWebClient aa = DefaultWebClient.Z().a(this.mActivity).b(this.cA).a(this.cP).a(this.cQ).g(this.cw.getWebView()).b(this.cR).e(this.cS).aa();
        ai aiVar = this.cT;
        if (aiVar != null) {
            ai aiVar2 = aiVar;
            int i = 1;
            while (aiVar2.ar() != null) {
                aiVar2 = aiVar2.ar();
                i++;
            }
            ag.c(TAG, "MiddlewareWebClientBase middleware count:" + i);
            aiVar2.c(aa);
            return aiVar;
        }
        return aa;
    }

    private void init() {
        E();
        D();
    }

    public r A() {
        return this.cx;
    }

    public x B() {
        return this.cy;
    }

    public t C() {
        return this.cM;
    }

    public aj w() {
        return this.cQ;
    }

    public aq x() {
        return this.cN;
    }

    public z y() {
        z zVar = this.cL;
        aa aaVar = zVar;
        if (zVar == null) {
            aaVar = aa.h(this.cw.getWebView());
            this.cL = aaVar;
        }
        return aaVar;
    }

    public ap z() {
        return this.cw;
    }
}
