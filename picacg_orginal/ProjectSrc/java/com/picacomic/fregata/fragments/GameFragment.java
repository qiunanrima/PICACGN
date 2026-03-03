package com.picacomic.fregata.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.R;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.picacomic.fregata.a.k;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.GameListRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.GameListObject;
import com.picacomic.fregata.objects.responses.DataClass.GameListResponse.GameListResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/GameFragment.class */
public class GameFragment extends BaseFragment implements k {
    public static final String TAG = "GameFragment";
    int page;
    boolean pg;
    boolean ph;
    Call<GeneralResponse<GameListResponse>> pi;
    GameListRecyclerViewAdapter pj;
    ArrayList<GameListObject> pk;
    @BindView(2131296763)
    RecyclerView recyclerView_games;
    @BindView(2131297037)
    Toolbar toolbar;

    /* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/GameFragment$ItemOffsetDecoration.class */
    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
        private int offset;

        public ItemOffsetDecoration(int i) {
            this.offset = i;
        }

        @Override // android.support.v7.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            rect.left = this.offset;
            rect.right = this.offset;
            rect.bottom = this.offset;
            if (recyclerView.getChildAdapterPosition(view) == 0 || recyclerView.getChildAdapterPosition(view) == 1) {
                rect.top = this.offset;
            }
        }
    }

    @Override // com.picacomic.fregata.a.k
    public void C(int i) {
        getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, GameDetailFragment.ad(this.pk.get(i).getGameId()), GameDetailFragment.TAG).addToBackStack(GameDetailFragment.TAG).commit();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        a(this.toolbar, 2131689976, true);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(0);
        }
        if (this.pg) {
            di();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        this.pj.notifyDataSetChanged();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        int i = (int) ((getContext().getResources().getDisplayMetrics().density * 4.0f) + 0.5f);
        this.recyclerView_games.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        this.recyclerView_games.setAdapter(this.pj);
        this.recyclerView_games.addItemDecoration(new ItemOffsetDecoration(i));
        this.recyclerView_games.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.picacomic.fregata.fragments.GameFragment.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (recyclerView.getLayoutManager() != null && (recyclerView.getLayoutManager() instanceof LinearLayoutManager) && ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() == ((LinearLayoutManager) recyclerView.getLayoutManager()).getItemCount() - 1) {
                    GameFragment.this.di();
                }
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
            }
        });
    }

    public void di() {
        if (this.ph) {
            C(getResources().getString(2131689826));
            f.aA("Show Progress");
            this.pi = new d(getContext()).dO().e(e.z(getActivity()), this.page);
            this.pi.enqueue(new Callback<GeneralResponse<GameListResponse>>() { // from class: com.picacomic.fregata.fragments.GameFragment.2
                @Override // retrofit2.Callback
                public void onFailure(Call<GeneralResponse<GameListResponse>> call, Throwable th) {
                    th.printStackTrace();
                    f.aA("dismiss progress");
                    GameFragment.this.bC();
                    new c(GameFragment.this.getActivity()).dN();
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<GeneralResponse<GameListResponse>> call, Response<GeneralResponse<GameListResponse>> response) {
                    if (response.code() != 200) {
                        try {
                            new c(GameFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (response.body() != null && response.body().data != null && response.body().data.getGames() != null) {
                        for (int i = 0; i < response.body().data.getGames().getDocs().size(); i++) {
                            GameFragment.this.pk.add(response.body().data.getGames().getDocs().get(i));
                        }
                        GameFragment.this.page++;
                        if (GameFragment.this.page > response.body().data.getGames().getPages()) {
                            GameFragment.this.ph = false;
                        }
                        if (GameFragment.this.getActivity() != null) {
                            GameFragment.this.bI();
                        }
                    }
                    f.aA("dismiss progress");
                    GameFragment.this.bC();
                }
            });
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        if (this.pk == null) {
            this.page = 1;
            this.ph = true;
            this.pk = new ArrayList<>();
            this.pg = true;
        } else {
            this.pg = false;
        }
        this.pj = new GameListRecyclerViewAdapter(getActivity(), this.pk, this);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427412, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.pi != null) {
            this.pi.cancel();
        }
        super.onDetach();
    }
}
