package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.objects.BannerObject;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/BannerPagerAdapter.class */
public class BannerPagerAdapter extends PagerAdapter {
    public static final String TAG = "BannerPagerAdapter";
    Context context;
    ArrayList<BannerObject> ja;
    LayoutInflater jc;
    RelativeLayout.LayoutParams jd = new RelativeLayout.LayoutParams(-1, -1);
    k je;

    public BannerPagerAdapter(Context context, ArrayList<BannerObject> arrayList, k kVar) {
        this.context = context;
        this.jc = LayoutInflater.from(context);
        this.ja = arrayList;
        this.je = kVar;
    }

    @Override // android.support.v4.view.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.ja == null ? 0 : this.ja.size();
    }

    @Override // android.support.v4.view.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, final int i) {
        if (this.ja == null || this.ja.size() <= i) {
            return null;
        }
        if (this.ja.get(i).getType().equals("ads")) {
            WebView webView = new WebView(this.context);
            webView.setLayoutParams(this.jd);
            WebView webView2 = webView;
            g.k(webView2);
            webView2.loadUrl(this.ja.get(i).getLink());
            viewGroup.addView(webView);
            return webView;
        }
        ImageView imageView = new ImageView(this.context);
        imageView.setLayoutParams(this.jd);
        ImageView imageView2 = imageView;
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Picasso with = Picasso.with(this.context);
        with.load(this.ja.get(i).getThumb().getFileServer() + "/static/" + this.ja.get(i).getThumb().getPath()).into(imageView2);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.adapters.BannerPagerAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BannerPagerAdapter.this.je.C(i);
            }
        });
        viewGroup.addView(imageView);
        return imageView;
    }

    @Override // android.support.v4.view.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
