package com.picacomic.fregata.utils.views;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.g;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/BannerWebview.class */
public class BannerWebview extends RelativeLayout {
    CountDownTimer countDownTimer;
    String defaultUrl;
    @BindView(2131296541)
    ImageButton imageButton_close;
    @BindView(2131296771)
    RelativeLayout relativeLayout_container;
    @BindView(2131297065)
    WebView webView_banner;

    public BannerWebview(Context context) {
        super(context);
        this.defaultUrl = g.ag(e.al(context));
        init(context);
    }

    public BannerWebview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultUrl = g.ag(e.al(context));
        init(context);
    }

    public BannerWebview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.defaultUrl = g.ag(e.al(context));
        init(context);
    }

    public void hide() {
        if (this.relativeLayout_container != null) {
            this.relativeLayout_container.setVisibility(8);
            if (this.webView_banner != null) {
                this.webView_banner.stopLoading();
            }
        }
    }

    public void init(Context context) {
        inflate(context, 2131427492, this);
        ButterKnife.bind(this);
        g.k(this.webView_banner);
        this.webView_banner.loadUrl(this.defaultUrl);
        this.imageButton_close.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.BannerWebview.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BannerWebview.this.relativeLayout_container.setVisibility(8);
            }
        });
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        setMeasuredDimension(measuredWidth, measuredWidth / 3);
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

    public void show(String str) {
        if (this.relativeLayout_container != null) {
            this.relativeLayout_container.setVisibility(0);
            setBannerUrl(str);
        }
    }
}
