package com.picacomic.fregata.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.picacomic.fregata.a.i;
import com.picacomic.fregata.activities.BaseActivity;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.utils.a;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/BaseFragment.class */
public class BaseFragment extends Fragment implements i {
    public static final String TAG = "BaseFragment";
    private Unbinder kq;

    public void C(String str) {
        if (getActivity() == null || !(getActivity() instanceof BaseActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).C(str);
    }

    public void D(String str) {
        if (getActivity() == null || !(getActivity() instanceof BaseActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).D(str);
    }

    public void E(String str) {
        if (getActivity() == null || !(getActivity() instanceof BaseActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).E(str);
    }

    public void a(Toolbar toolbar, int i, boolean z) {
        toolbar.setTitle(i);
        if (z) {
            toolbar.setNavigationIcon(2131230924);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.BaseFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (BaseFragment.this.getActivity() == null || !(BaseFragment.this.getActivity() instanceof BaseActivity)) {
                        return;
                    }
                    ((BaseActivity) BaseFragment.this.getActivity()).onBackPressed();
                }
            });
        }
    }

    public void a(Toolbar toolbar, String str, boolean z) {
        toolbar.setTitle(str);
        if (z) {
            toolbar.setNavigationIcon(2131230924);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.BaseFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (BaseFragment.this.getActivity() == null || !(BaseFragment.this.getActivity() instanceof BaseActivity)) {
                        return;
                    }
                    ((BaseActivity) BaseFragment.this.getActivity()).onBackPressed();
                }
            });
        }
    }

    public void a(View view) {
        this.kq = ButterKnife.bind(this, view);
        init();
        ca();
        bH();
    }

    public void a(UserProfileObject userProfileObject) {
        if (getActivity() == null || !(getActivity() instanceof BaseActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).a(userProfileObject);
    }

    @Override // com.picacomic.fregata.a.i
    public void b(View view) {
        if (getActivity() == null || !(getActivity() instanceof BaseActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).onBackPressed();
    }

    public void bA() {
        if (getActivity() == null || !(getActivity() instanceof BaseActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).bA();
    }

    public void bB() {
        if (getActivity() == null || !(getActivity() instanceof BaseActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).bB();
    }

    public void bC() {
        if (getActivity() == null || !(getActivity() instanceof BaseActivity)) {
            return;
        }
        ((BaseActivity) getActivity()).bC();
    }

    public void bH() {
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            long P = e.P(getActivity());
            String str = TAG;
            f.D(str, "Last Time = " + P + " Current Time = " + System.currentTimeMillis() + " Diff = " + (System.currentTimeMillis() - P));
            if (System.currentTimeMillis() - P > 7200000) {
                ((MainActivity) getActivity()).G(a.dS());
            }
        }
        if ((this instanceof ComicDetailFragment) || (this instanceof GameDetailFragment)) {
            if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
                return;
            }
            ((MainActivity) getActivity()).F(a.getDetail());
        } else if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
        } else {
            ((MainActivity) getActivity()).bV();
        }
    }

    public void bI() {
    }

    public void ca() {
    }

    public void init() {
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            ((BaseActivity) getActivity()).a(this);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        if (this.kq != null) {
            this.kq.unbind();
        } else {
            f.D(TAG, "unbinder = null");
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDetach() {
        if (getActivity() != null && (getActivity() instanceof BaseActivity)) {
            ((BaseActivity) getActivity()).a((i) null);
        }
        bC();
        super.onDetach();
    }
}
