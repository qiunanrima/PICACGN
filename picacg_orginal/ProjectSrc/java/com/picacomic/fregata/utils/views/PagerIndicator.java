package com.picacomic.fregata.utils.views;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/PagerIndicator.class */
public class PagerIndicator {
    private Context context;
    private int currentIndex;
    ArrayList<ImageView> imageViews = new ArrayList<>();
    LinearLayout linearLayout_indicator_container;

    public PagerIndicator(Context context, LinearLayout linearLayout, int i) {
        this.context = context;
        this.linearLayout_indicator_container = linearLayout;
        init(i);
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public ImageView getCurrentView() {
        if (this.imageViews == null || this.imageViews.size() <= this.currentIndex) {
            return null;
        }
        return this.imageViews.get(this.currentIndex);
    }

    public boolean hasNext() {
        return this.imageViews != null && this.imageViews.size() - 1 > this.currentIndex;
    }

    public void init(int i) {
        if (this.imageViews != null) {
            this.imageViews.clear();
        } else {
            this.imageViews = new ArrayList<>();
        }
        for (int i2 = 0; i2 < i; i2++) {
            ImageView imageView = new ImageView(this.context);
            imageView.setImageResource(2131230966);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setAdjustViewBounds(true);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(10, 10, 10, 10);
            imageView.setLayoutParams(layoutParams);
            this.imageViews.add(imageView);
            this.linearLayout_indicator_container.addView(imageView);
        }
        if (i <= 0) {
            this.currentIndex = -1;
            return;
        }
        this.imageViews.get(0).setImageResource(2131230967);
        this.currentIndex = 0;
    }

    public int next() {
        if (this.imageViews != null) {
            if (this.imageViews.size() - 1 > this.currentIndex) {
                this.currentIndex++;
                setCurrentIndex(this.currentIndex);
                return this.currentIndex;
            }
            this.currentIndex = 0;
            setCurrentIndex(this.currentIndex);
            return 0;
        }
        return -1;
    }

    public int previous() {
        if (this.imageViews != null) {
            if (this.currentIndex > 0) {
                setCurrentIndex(this.currentIndex - 1);
                return this.currentIndex;
            }
            setCurrentIndex(this.imageViews.size() - 1);
            return 0;
        }
        return -1;
    }

    public boolean setCurrentIndex(int i) {
        if (this.imageViews == null || this.imageViews.size() <= i) {
            return false;
        }
        for (int i2 = 0; i2 < this.imageViews.size(); i2++) {
            this.imageViews.get(i2).setImageResource(2131230966);
        }
        this.imageViews.get(i).setImageResource(2131230967);
        this.currentIndex = i;
        return true;
    }

    public void setSize(int i) {
        if (this.linearLayout_indicator_container != null) {
            this.linearLayout_indicator_container.removeAllViews();
            init(i);
        }
    }
}
