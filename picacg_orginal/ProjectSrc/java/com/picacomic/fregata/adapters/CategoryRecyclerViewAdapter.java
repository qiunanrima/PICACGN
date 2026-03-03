package com.picacomic.fregata.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.holders.CategoryViewHolder;
import com.picacomic.fregata.holders.CategoryWebViewHolder;
import com.picacomic.fregata.objects.CategoryObject;
import com.picacomic.fregata.objects.DefaultCategoryObject;
import com.picacomic.fregata.utils.RoundedCornersTransformation;
import com.squareup.picasso.Transformation;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/adapters/CategoryRecyclerViewAdapter.class */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = "CategoryRecyclerViewAdapter";
    private final Context context;
    private k jb;
    ArrayList<DefaultCategoryObject> jg;
    ArrayList<CategoryObject> jh;
    private final LayoutInflater mLayoutInflater;
    final int radius = 40;
    final int ji = 0;
    final Transformation jj = new RoundedCornersTransformation(40, 0);

    public CategoryRecyclerViewAdapter(Context context, ArrayList<DefaultCategoryObject> arrayList, ArrayList<CategoryObject> arrayList2, k kVar) {
        this.context = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.jh = arrayList2;
        this.jg = arrayList;
        this.jb = kVar;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = 0;
        if (this.jh != null) {
            i = 0 + this.jh.size();
        }
        int i2 = i;
        if (this.jg != null) {
            i2 = i + this.jg.size();
        }
        return i2;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (this.jg != null) {
            if (i == 3) {
                return 2;
            }
            return i < this.jg.size() ? 0 : 1;
        }
        return 1;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2;
        if (viewHolder instanceof CategoryWebViewHolder) {
            ((CategoryWebViewHolder) viewHolder).a(this.jg.get(i));
        } else if (getItemViewType(i) == 0) {
            if (this.jg == null || this.jg.size() <= i) {
                return;
            }
            ((CategoryViewHolder) viewHolder).a(this.jg.get(i));
        } else {
            int i3 = 0;
            if (this.jg != null) {
                i3 = 0 + this.jg.size();
            }
            if (this.jh == null || this.jh.size() <= (i2 = i - i3)) {
                return;
            }
            ((CategoryViewHolder) viewHolder).a(this.jh.get(i2));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 2 ? new CategoryWebViewHolder(this.context, this.mLayoutInflater.inflate(2131427446, viewGroup, false)) : new CategoryViewHolder(this.context, this.mLayoutInflater.inflate(2131427445, viewGroup, false), this.jb);
    }
}
