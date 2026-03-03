package com.picacomic.fregata.utils.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.just.agentweb.AgentWeb;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.g;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/PopupWebview.class */
public class PopupWebview extends RelativeLayout {
    CountDownTimer countDownTimer;
    String defaultUrl;
    @BindView(2131296568)
    ImageButton imageButton_close;
    @BindView(2131296702)
    LinearLayout linearLayout_web;
    AgentWeb mAgentWeb;
    Context mContext;
    @BindView(2131296777)
    RelativeLayout relativeLayout_container;
    @BindView(2131296967)
    TextView textView_text;
    @BindView(2131297067)
    WebView webView_banner;

    public PopupWebview(Context context) {
        super(context);
        this.defaultUrl = g.af(e.al(context));
        init(context);
    }

    public PopupWebview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultUrl = g.af(e.al(context));
        init(context);
    }

    public PopupWebview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.defaultUrl = g.af(e.al(context));
        init(context);
    }

    public void hide() {
        if (this.relativeLayout_container != null) {
            this.relativeLayout_container.setVisibility(8);
            if (this.webView_banner != null) {
                this.webView_banner.stopLoading();
            }
            if (this.mContext != null) {
                e.b(this.mContext, System.currentTimeMillis());
            }
        }
    }

    public void init(Context context) {
        this.mContext = context;
        inflate(context, 2131427503, this);
        ButterKnife.bind(this);
        g.k(this.webView_banner);
        this.webView_banner.loadUrl(this.defaultUrl);
        this.imageButton_close.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.PopupWebview.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PopupWebview.this.hide();
            }
        });
    }

    public void setBannerUrl(String str) {
        if (this.webView_banner == null || str == null) {
            return;
        }
        if (str.startsWith("http://") || str.startsWith("https://")) {
            this.webView_banner.loadUrl(str);
        }
    }

    public void show() {
        if (this.relativeLayout_container != null) {
            this.relativeLayout_container.setVisibility(0);
            setBannerUrl(this.defaultUrl);
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.picacomic.fregata.utils.views.PopupWebview$2] */
    public void show(String str) {
        if (this.relativeLayout_container != null) {
            this.relativeLayout_container.setVisibility(0);
            this.imageButton_close.setVisibility(8);
            setBannerUrl(str);
            if (this.textView_text != null) {
                this.textView_text.setVisibility(0);
            }
            if (this.countDownTimer == null) {
                this.countDownTimer = new CountDownTimer(5000L, 1000L) { // from class: com.picacomic.fregata.utils.views.PopupWebview.2
                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        if (PopupWebview.this.textView_text != null) {
                            PopupWebview.this.textView_text.setVisibility(8);
                        }
                        if (PopupWebview.this.imageButton_close != null) {
                            PopupWebview.this.imageButton_close.setVisibility(0);
                        }
                    }

                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        if (PopupWebview.this.textView_text != null) {
                            TextView textView = PopupWebview.this.textView_text;
                            textView.setText("廣告可於 " + (j / 1000) + " 秒後關閉");
                            PopupWebview.this.textView_text.setVisibility(0);
                        }
                    }
                }.start();
            } else {
                this.countDownTimer.start();
            }
        }
    }
}
