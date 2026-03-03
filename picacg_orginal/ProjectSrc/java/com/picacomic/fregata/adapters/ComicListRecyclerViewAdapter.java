package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.holders.AdvertisementListViewHolder;
import com.picacomic.fregata.holders.ComicListEmptyHolder;
import com.picacomic.fregata.holders.ComicListViewHolder;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.g;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/ComicListRecyclerViewAdapter.class */
public class ComicListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "ComicListRecyclerViewAdapter";
    private final Context context;
    final String defaultUrl;
    private ArrayList<ComicListObject> ja;
    private com.picacomic.fregata.a.b jq;
    int jr;
    boolean[] jt;
    private final LayoutInflater mLayoutInflater;
    final int VIEW_TYPE_NORMAL = 0;
    final int jo = 1;
    final int jp = 2;
    String[] js = new String[8];

    public ComicListRecyclerViewAdapter(Context context, ArrayList<ComicListObject> arrayList, com.picacomic.fregata.a.b bVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.ja = arrayList;
        this.jq = bVar;
        this.jr = (arrayList.size() / 20) + 1;
        this.js[0] = context.getResources().getString(2131689721);
        this.js[1] = context.getResources().getString(2131689724);
        this.js[2] = context.getResources().getString(2131689719);
        this.js[3] = context.getResources().getString(2131689723);
        this.js[4] = context.getResources().getString(2131689725);
        this.js[5] = context.getResources().getString(2131689720);
        this.js[6] = context.getResources().getString(2131689722);
        this.js[7] = context.getResources().getString(2131689726);
        this.jt = new boolean[8];
        for (int i = 0; i < 8; i++) {
            this.jt[i] = false;
        }
        this.defaultUrl = g.af(e.al(context));
    }

    public void a(int i, boolean z) {
        if (this.jt == null || this.jt.length <= i) {
            return;
        }
        this.jt[i] = z;
        notifyDataSetChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        this.jr = (this.ja.size() / 20) + 1;
        return this.ja == null ? 0 : this.ja.size() + this.jr;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int i2 = i - (i / 21);
        if ((i == 0 || (i + 1) % 21 != 0) && i != (this.ja.size() + this.jr) - 1) {
            if (this.ja == null || this.ja.size() <= i2) {
                return 0;
            }
            boolean[] zArr = new boolean[8];
            for (int i3 = 0; i3 < 8; i3++) {
                zArr[i3] = false;
            }
            if (this.ja.get(i2).getCategories() != null) {
                for (int i4 = 0; i4 < this.ja.get(i2).getCategories().size(); i4++) {
                    int i5 = 0;
                    while (true) {
                        if (i5 >= 8) {
                            break;
                        }
                        if (!zArr[i5] && this.ja.get(i2).getCategories().get(i4).equalsIgnoreCase(this.js[i5])) {
                            zArr[i5] = true;
                            break;
                        }
                        i5++;
                    }
                }
            }
            for (int i6 = 0; i6 < 8; i6++) {
                if (this.jt[i6] && zArr[i6]) {
                    return 1;
                }
            }
            return 0;
        }
        return 2;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2 = i - (i / 21);
        if (viewHolder instanceof AdvertisementListViewHolder) {
            ((AdvertisementListViewHolder) viewHolder).webView_ads.loadUrl(this.defaultUrl);
        } else if (!(viewHolder instanceof ComicListEmptyHolder) && (viewHolder instanceof ComicListViewHolder)) {
            ComicListViewHolder comicListViewHolder = (ComicListViewHolder) viewHolder;
            if (this.ja == null || this.ja.size() <= i2) {
                return;
            }
            comicListViewHolder.a(this.ja.get(i2), this.js);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 1:
                return new ComicListEmptyHolder(this.mLayoutInflater.inflate(2131427460, viewGroup, false));
            case 2:
                return new AdvertisementListViewHolder(this.mLayoutInflater.inflate(2131427440, viewGroup, false));
            default:
                return new ComicListViewHolder(this.context, this.mLayoutInflater.inflate(2131427461, viewGroup, false), this.jq);
        }
    }
}
