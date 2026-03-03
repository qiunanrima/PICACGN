package com.picacomic.fregata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.picacomic.fregata.objects.ComicEpisodeObject;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/c.class */
public class c extends BaseAdapter {
    WeakReference<Context> jJ;
    ArrayList<ComicEpisodeObject> jL;
    LayoutInflater jc;

    public c(Context context, ArrayList<ComicEpisodeObject> arrayList) {
        this.jJ = new WeakReference<>(context);
        this.jc = LayoutInflater.from(this.jJ.get());
        this.jL = arrayList;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.jL == null) {
            return 0;
        }
        return this.jL.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.jL != null) {
            if (this.jL == null || this.jL.size() != 0) {
                return this.jL.get(i);
            }
            return null;
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        if (this.jL == null) {
            return 0L;
        }
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {
            View inflate = this.jc.inflate(2131427465, viewGroup, false);
            textView = (TextView) inflate;
            inflate.setTag(textView);
        } else {
            textView = (TextView) view.getTag();
        }
        if (this.jL != null && this.jL.size() > i) {
            textView.setText(this.jL.get(i).getTitle() + "");
        }
        return textView;
    }
}
