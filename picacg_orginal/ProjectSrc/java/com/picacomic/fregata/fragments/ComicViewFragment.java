package com.picacomic.fregata.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import butterknife.BindView;
import com.picacomic.fregata.a.c;
import com.picacomic.fregata.a.d;
import com.picacomic.fregata.activities.ComicViewerActivity;
import com.picacomic.fregata.adapters.ComicPageRecyclerViewAdapter;
import com.picacomic.fregata.objects.ComicPageObject;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.views.ZoomableRecyclerView;
import java.util.ArrayList;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ComicViewFragment.class */
public class ComicViewFragment extends BaseFragment implements c {
    public static final String TAG = "ComicViewFragment";
    boolean hC;

    /* renamed from: if  reason: not valid java name */
    ArrayList<ComicPageObject> f3if;
    LinearLayoutManager jQ;
    d nV;
    ComicPageRecyclerViewAdapter nW;
    @BindView(2131296761)
    ZoomableRecyclerView recyclerView_comic_viewer;
    int nX = 0;
    boolean nY = false;
    int currentPage = 0;
    int hP = 0;
    @Deprecated
    int hF = 1;

    @Override // com.picacomic.fregata.a.c
    public void B(boolean z) {
        cY();
        if (z) {
            this.jQ.setOrientation(1);
            this.recyclerView_comic_viewer.setVertical(true);
        } else {
            this.jQ.setOrientation(0);
            this.recyclerView_comic_viewer.setVertical(false);
        }
        this.hC = z;
        this.nW.r(z);
        this.nW.notifyDataSetChanged();
        this.recyclerView_comic_viewer.invalidateItemDecorations();
    }

    @Override // com.picacomic.fregata.a.c
    public void M(int i) {
        cY();
        if (i == 2) {
            this.nW.q(false);
        } else if (i == 1) {
            this.nW.q(true);
        }
        this.recyclerView_comic_viewer.setScreenWidth(((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay().getWidth());
        this.nW.notifyDataSetChanged();
        this.recyclerView_comic_viewer.invalidateItemDecorations();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x008a A[LOOP:0: B:13:0x0081->B:15:0x008a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    @Override // com.picacomic.fregata.a.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.util.ArrayList<com.picacomic.fregata.objects.ComicPageObject> r6, int r7, boolean r8, boolean r9) {
        /*
            r5 = this;
            r0 = r5
            r0.cY()
            r0 = 0
            r10 = r0
            r0 = r9
            if (r0 == 0) goto L32
            r0 = r5
            r1 = 0
            r0.nX = r1
            r0 = r5
            r1 = 0
            r0.nY = r1
            r0 = r5
            com.picacomic.fregata.utils.views.ZoomableRecyclerView r0 = r0.recyclerView_comic_viewer
            r1 = 0
            r0.scrollToPosition(r1)
            r0 = r5
            java.util.ArrayList<com.picacomic.fregata.objects.ComicPageObject> r0 = r0.f3if
            r0.clear()
            r0 = r5
            java.util.ArrayList<com.picacomic.fregata.objects.ComicPageObject> r0 = r0.f3if
            r1 = 0
            r2 = r6
            boolean r0 = r0.addAll(r1, r2)
            goto L62
        L32:
            r0 = r5
            int r0 = r0.hP
            r1 = r7
            if (r0 == r1) goto L4e
            r0 = r8
            if (r0 != 0) goto L4e
            r0 = r5
            java.util.ArrayList<com.picacomic.fregata.objects.ComicPageObject> r0 = r0.f3if
            r1 = 0
            r2 = r6
            boolean r0 = r0.addAll(r1, r2)
            r0 = 1
            r11 = r0
            goto L65
        L4e:
            r0 = r5
            java.util.ArrayList<com.picacomic.fregata.objects.ComicPageObject> r0 = r0.f3if
            int r0 = r0.size()
            r11 = r0
            r0 = r5
            java.util.ArrayList<com.picacomic.fregata.objects.ComicPageObject> r0 = r0.f3if
            r1 = r11
            r2 = r6
            boolean r0 = r0.addAll(r1, r2)
        L62:
            r0 = 0
            r11 = r0
        L65:
            r0 = r5
            r1 = r7
            r0.hP = r1
            r0 = r5
            com.picacomic.fregata.adapters.ComicPageRecyclerViewAdapter r0 = r0.nW
            r1 = r7
            int r2 = com.picacomic.fregata.activities.ComicViewerActivity.hq
            int r1 = r1 / r2
            int r2 = com.picacomic.fregata.activities.ComicViewerActivity.hq
            int r1 = r1 * r2
            r0.y(r1)
            r0 = r5
            com.picacomic.fregata.adapters.ComicPageRecyclerViewAdapter r0 = r0.nW
            r0.notifyDataSetChanged()
        L81:
            r0 = r10
            r1 = r6
            int r1 = r1.size()
            if (r0 >= r1) goto Lac
            r0 = r5
            android.content.Context r0 = r0.getContext()
            com.squareup.picasso.Picasso r0 = com.squareup.picasso.Picasso.with(r0)
            r1 = r6
            r2 = r10
            java.lang.Object r1 = r1.get(r2)
            com.picacomic.fregata.objects.ComicPageObject r1 = (com.picacomic.fregata.objects.ComicPageObject) r1
            com.picacomic.fregata.objects.ThumbnailObject r1 = r1.getMedia()
            java.lang.String r1 = com.picacomic.fregata.utils.g.b(r1)
            com.squareup.picasso.RequestCreator r0 = r0.load(r1)
            r0.fetch()
            int r10 = r10 + 1
            goto L81
        Lac:
            r0 = r8
            if (r0 == 0) goto Lc2
            r0 = r5
            com.picacomic.fregata.utils.views.ZoomableRecyclerView r0 = r0.recyclerView_comic_viewer
            r1 = r7
            r2 = r7
            int r3 = com.picacomic.fregata.activities.ComicViewerActivity.hq
            int r2 = r2 / r3
            int r3 = com.picacomic.fregata.activities.ComicViewerActivity.hq
            int r2 = r2 * r3
            int r1 = r1 - r2
            r0.scrollToPosition(r1)
        Lc2:
            r0 = r11
            if (r0 == 0) goto Ld1
            r0 = r5
            com.picacomic.fregata.utils.views.ZoomableRecyclerView r0 = r0.recyclerView_comic_viewer
            int r1 = com.picacomic.fregata.activities.ComicViewerActivity.hq
            r0.scrollToPosition(r1)
        Ld1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.picacomic.fregata.fragments.ComicViewFragment.a(java.util.ArrayList, int, boolean, boolean):void");
    }

    @Override // com.picacomic.fregata.a.c
    public void b(int i, boolean z) {
        cY();
        this.recyclerView_comic_viewer.scrollToPosition(i);
        this.currentPage = i;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (isAdded() && (getActivity() instanceof ComicViewerActivity)) {
            cX().bL();
            cX().bH();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        this.nW.notifyDataSetChanged();
    }

    public ComicViewerActivity cX() {
        if (isAdded() && (getActivity() instanceof ComicViewerActivity)) {
            return (ComicViewerActivity) getActivity();
        }
        return null;
    }

    public void cY() {
        if (this.recyclerView_comic_viewer == null) {
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.recyclerView_comic_viewer.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.picacomic.fregata.fragments.ComicViewFragment.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (recyclerView.getLayoutManager() == null || !(recyclerView.getLayoutManager() instanceof LinearLayoutManager)) {
                    return;
                }
                ComicViewFragment.this.currentPage = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                if (!ComicViewFragment.this.nY && ComicViewFragment.this.currentPage != 0) {
                    ComicViewFragment.this.nY = true;
                }
                if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() < 0) {
                    ComicViewFragment.this.currentPage = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                } else {
                    ComicViewFragment.this.currentPage = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                }
                ComicViewFragment.this.nV.r(ComicViewFragment.this.currentPage);
                String str = ComicViewFragment.TAG;
                f.D(str, "Current Page = " + ComicViewFragment.this.currentPage);
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        Display defaultDisplay = ((WindowManager) getActivity().getSystemService("window")).getDefaultDisplay();
        this.currentPage = 0;
        this.f3if = new ArrayList<>();
        this.nW = new ComicPageRecyclerViewAdapter(getActivity(), this.f3if);
        this.jQ = new LinearLayoutManager(getActivity());
        this.recyclerView_comic_viewer.setLayoutManager(this.jQ);
        this.recyclerView_comic_viewer.setAdapter(this.nW);
        this.recyclerView_comic_viewer.setScreenWidth(defaultDisplay.getWidth());
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ComicViewerActivity) {
            ((ComicViewerActivity) context).a((c) this);
        }
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427407, viewGroup, false);
        if (getActivity() instanceof ComicViewerActivity) {
            this.nV = cX();
            a(inflate);
        }
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (getActivity() instanceof ComicViewerActivity) {
            ((ComicViewerActivity) getActivity()).a((c) null);
        }
        super.onDetach();
    }
}
