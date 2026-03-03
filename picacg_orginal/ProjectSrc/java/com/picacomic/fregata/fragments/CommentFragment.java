package com.picacomic.fregata.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.R;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.google.gson.Gson;
import com.picacomic.fregata.a.e;
import com.picacomic.fregata.adapters.CommentRecyclerViewAdapter;
import com.picacomic.fregata.b.a;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.b.d;
import com.picacomic.fregata.objects.ComicListObject;
import com.picacomic.fregata.objects.CommentObject;
import com.picacomic.fregata.objects.CommentWithReplyObject;
import com.picacomic.fregata.objects.UserBasicObject;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.objects.requests.CommentBody;
import com.picacomic.fregata.objects.responses.ActionResponse;
import com.picacomic.fregata.objects.responses.CommentPostToTopResponse;
import com.picacomic.fregata.objects.responses.DataClass.CommentsResponse.CommentsResponse;
import com.picacomic.fregata.objects.responses.DataClass.PostCommentResponse.PostCommentResponse;
import com.picacomic.fregata.objects.responses.DataClass.ProfileCommentsResponse.ProfileCommentsResponse;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.MessageResponse;
import com.picacomic.fregata.objects.responses.UserProfileDirtyResponse;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/CommentFragment.class */
public class CommentFragment extends BaseFragment implements e {
    public static final String TAG = "CommentFragment";
    @BindView(2131296357)
    Button button_postComment;
    @BindView(2131296356)
    Button button_replyCancel;
    String comicId;
    @BindView(2131296457)
    EditText editText_currentPage;
    @BindView(2131296458)
    EditText editText_inputField;
    String gameId;
    @BindView(2131296587)
    ImageView imageView_empty;
    boolean jG;
    UserBasicObject jH;
    ArrayList<CommentWithReplyObject> jz;
    boolean kE;
    @BindView(2131296673)
    LinearLayout linearLayout_commentPage;
    @BindView(2131296671)
    LinearLayout linearLayout_inputBar;
    @BindView(2131296672)
    LinearLayout linearLayout_noComment;
    Call<GeneralResponse<ActionResponse>> mW;
    Call<GeneralResponse<ProfileCommentsResponse>> og;
    Call<GeneralResponse<CommentsResponse>> oh;
    Call<GeneralResponse<PostCommentResponse>> oi;
    Call<GeneralResponse<CommentsResponse>> oj;
    Call<GeneralResponse<PostCommentResponse>> ok;
    Call<GeneralResponse<MessageResponse>> ol;
    Call<GeneralResponse<MessageResponse>> om;
    Call<GeneralResponse<CommentPostToTopResponse>> on;
    Call<GeneralResponse<UserProfileDirtyResponse>> oo;
    CommentRecyclerViewAdapter op;
    boolean oq;
    boolean or;
    int os;
    String ot;
    String ou = "";
    int ov;
    int page;
    @BindView(2131296762)
    RecyclerView recyclerView_comments;
    @BindView(2131296923)
    TextView textView_totalPage;
    @BindView(2131297037)
    Toolbar toolbar;
    int totalPage;
    String userId;

    public static CommentFragment Z(String str) {
        CommentFragment commentFragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("GAME_ID", str);
        bundle.putInt("FRAGMENT_TYPE", 2);
        commentFragment.setArguments(bundle);
        return commentFragment;
    }

    public static CommentFragment a(String str, UserBasicObject userBasicObject) {
        CommentFragment commentFragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("USER_ID", str);
        bundle.putInt("FRAGMENT_TYPE", 1);
        bundle.putParcelable("USER_BASIC_OBJECT", userBasicObject);
        commentFragment.setArguments(bundle);
        return commentFragment;
    }

    public static CommentFragment l(String str, String str2) {
        CommentFragment commentFragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("COMIC_ID", str2);
        bundle.putInt("FRAGMENT_TYPE", 0);
        bundle.putString("KNIGHT_USER_ID", str);
        commentFragment.setArguments(bundle);
        return commentFragment;
    }

    @Override // com.picacomic.fregata.a.e
    public void A(int i) {
        this.op.A(i);
    }

    @Override // com.picacomic.fregata.a.e
    public void C(int i) {
        if (this.ou.equalsIgnoreCase(this.jz.get(i).getCommentId())) {
            D(false);
            this.ou = "";
            this.op.a(-1, null, false);
            return;
        }
        D(true);
        this.ou = this.jz.get(i).getCommentId();
        this.ov = i;
        if (this.jz.get(i).getChildsCount() <= 0) {
            this.op.a(i, null, false);
        } else if (this.jz.get(i).getArrayList() == null || this.jz.get(i).getArrayList().size() <= 0) {
            a(this.jz.get(i).getCommentId(), i, false);
        } else if (this.jz.get(i).getCurrentPage() < this.jz.get(i).getTotalPage()) {
            this.op.a(i, this.jz.get(i).getArrayList(), true);
        } else {
            this.op.a(i, this.jz.get(i).getArrayList(), false);
        }
    }

    public void C(final boolean z) {
        if (this.or) {
            return;
        }
        this.or = true;
        C(getResources().getString(2131689826));
        d dVar = new d(getContext());
        switch (this.os) {
            case 1:
                this.og = dVar.dO().c(com.picacomic.fregata.utils.e.z(getActivity()), this.page);
                break;
            case 2:
                this.oh = dVar.dO().f(com.picacomic.fregata.utils.e.z(getActivity()), this.gameId, this.page);
                break;
            default:
                this.oh = dVar.dO().c(com.picacomic.fregata.utils.e.z(getActivity()), this.comicId, this.page);
                break;
        }
        if (this.os == 1) {
            this.og.enqueue(new Callback<GeneralResponse<ProfileCommentsResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.14
                @Override // retrofit2.Callback
                public void onFailure(Call<GeneralResponse<ProfileCommentsResponse>> call, Throwable th) {
                    CommentFragment.this.or = false;
                    th.printStackTrace();
                    CommentFragment.this.bC();
                    new c(CommentFragment.this.getActivity()).dN();
                    CommentFragment.this.bI();
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<GeneralResponse<ProfileCommentsResponse>> call, Response<GeneralResponse<ProfileCommentsResponse>> response) {
                    if (response.code() == 200) {
                        f.aA(response.body().data.toString());
                        if (response.body().data != null && response.body().data.getComments() != null && response.body().data.getComments().getDocs() != null) {
                            if (z) {
                                if (CommentFragment.this.jz != null) {
                                    CommentFragment.this.jz.clear();
                                } else {
                                    CommentFragment.this.jz = new ArrayList<>();
                                }
                                CommentFragment.this.op.z(response.body().data.getComments().getTotal() - (response.body().data.getComments().getLimit() * (CommentFragment.this.page - 1)));
                            }
                            for (int i = 0; i < response.body().data.getComments().getDocs().size(); i++) {
                                CommentFragment.this.jz.add(new CommentWithReplyObject(response.body().data.getComments().getDocs().get(i), null));
                            }
                            CommentFragment.this.totalPage = response.body().data.getComments().getPages();
                        }
                    } else {
                        try {
                            new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    CommentFragment.this.or = false;
                    CommentFragment.this.bC();
                    CommentFragment.this.bI();
                }
            });
        } else {
            this.oh.enqueue(new Callback<GeneralResponse<CommentsResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.15
                @Override // retrofit2.Callback
                public void onFailure(Call<GeneralResponse<CommentsResponse>> call, Throwable th) {
                    CommentFragment.this.or = false;
                    th.printStackTrace();
                    CommentFragment.this.bC();
                    new c(CommentFragment.this.getActivity()).dN();
                    CommentFragment.this.bI();
                }

                @Override // retrofit2.Callback
                public void onResponse(Call<GeneralResponse<CommentsResponse>> call, Response<GeneralResponse<CommentsResponse>> response) {
                    if (response.code() == 200) {
                        f.aA(response.body().data.toString());
                        if (response.body().data != null && response.body().data.getComments() != null && response.body().data.getComments().getDocs() != null) {
                            if (z) {
                                if (CommentFragment.this.jz != null) {
                                    CommentFragment.this.jz.clear();
                                } else {
                                    CommentFragment.this.jz = new ArrayList<>();
                                }
                                if (response.body().data.getTopComments() == null || response.body().data.getTopComments().size() <= 0 || CommentFragment.this.page != 1) {
                                    int limit = response.body().data.getComments().getLimit();
                                    int i = CommentFragment.this.page;
                                    CommentFragment.this.op.B(0);
                                    CommentFragment.this.op.z(response.body().data.getComments().getTotal() - (limit * (i - 1)));
                                } else {
                                    CommentFragment.this.op.B(response.body().data.getTopComments().size());
                                    CommentFragment.this.op.z(response.body().data.getComments().getTotal() + response.body().data.getTopComments().size());
                                    for (int i2 = 0; i2 < response.body().data.getTopComments().size(); i2++) {
                                        CommentFragment.this.jz.add(0, new CommentWithReplyObject(response.body().data.getTopComments().get(i2)));
                                    }
                                }
                            }
                            for (int i3 = 0; i3 < response.body().data.getComments().getDocs().size(); i3++) {
                                CommentFragment.this.jz.add(new CommentWithReplyObject(response.body().data.getComments().getDocs().get(i3)));
                            }
                            CommentFragment.this.totalPage = response.body().data.getComments().getPages();
                        }
                    } else {
                        try {
                            new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    CommentFragment.this.or = false;
                    CommentFragment.this.bC();
                    CommentFragment.this.bI();
                }
            });
        }
    }

    public void D(boolean z) {
        if (z) {
            this.oq = true;
            this.button_postComment.setBackgroundResource(2131230847);
            this.button_postComment.setText(2131689768);
            this.editText_inputField.setHint(2131689770);
            this.button_replyCancel.getLayoutParams().width = -2;
            this.button_replyCancel.setPadding(10, 0, 0, 0);
            return;
        }
        this.oq = false;
        this.button_postComment.setBackgroundResource(2131230846);
        this.button_postComment.setText(2131689771);
        this.editText_inputField.setHint(2131689759);
        this.button_replyCancel.getLayoutParams().width = 0;
        this.button_replyCancel.setPadding(0, 0, 0, 0);
    }

    @Override // com.picacomic.fregata.a.e
    public void N(int i) {
        if (this.jz.get(i).getCurrentPage() < this.jz.get(i).getTotalPage()) {
            a(this.jz.get(i).getCommentId(), i, false);
        }
    }

    @Override // com.picacomic.fregata.a.e
    public void O(int i) {
        if (this.jz == null || this.jz.size() <= i) {
            return;
        }
        if (this.jz.get(i).getComicId() != null) {
            if (getParentFragment() != null) {
                getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, ComicDetailFragment.a(new ComicListObject(this.jz.get(i).getComicId().getComicId() + "")), ComicDetailFragment.TAG).addToBackStack(ComicListFragment.TAG).commit();
            }
        } else if (this.jz.get(i).getGameId() == null || getParentFragment() == null) {
        } else {
            FragmentTransaction customAnimations = getParentFragment().getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007);
            customAnimations.replace(R.id.container, GameDetailFragment.ad(this.jz.get(i).getGameId().getGameId() + ""), GameDetailFragment.TAG).addToBackStack(GameDetailFragment.TAG).commit();
        }
    }

    @Override // com.picacomic.fregata.a.e
    public void P(int i) {
        if (this.jz == null || this.jz.size() <= i || this.jz.get(i).getUser() == null) {
            return;
        }
        a(this.jz.get(i).getUser());
    }

    @Override // com.picacomic.fregata.a.e
    public void Q(int i) {
        if (this.jz == null || this.jz.size() <= i) {
            return;
        }
        aa(this.jz.get(i).getCommentId());
        if (this.jz.get(i).isLiked()) {
            this.jz.get(i).setLikesCount(this.jz.get(i).getLikesCount() - 1);
            this.jz.get(i).setLiked(false);
            Toast.makeText(getActivity(), 2131689580, 0).show();
        } else {
            this.jz.get(i).setLikesCount(this.jz.get(i).getLikesCount() + 1);
            this.jz.get(i).setLiked(true);
            Toast.makeText(getActivity(), 2131689581, 0).show();
        }
        this.op.notifyItemChanged(i);
    }

    @Override // com.picacomic.fregata.a.e
    public void R(int i) {
        if (this.jz == null || this.jz.size() <= i || this.jz.get(i).getUser() == null) {
            return;
        }
        a(this.jz.get(i).getUser());
    }

    @Override // com.picacomic.fregata.a.e
    public void S(final int i) {
        AlertDialogCenter.hideComment(getContext(), new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommentFragment.this.a(CommentFragment.this.jz.get(i).getCommentId(), i, -1);
            }
        });
    }

    @Override // com.picacomic.fregata.a.e
    public void T(int i) {
        if (this.jz == null || this.jz.size() <= i || this.jz.get(i).getCommentId() == null) {
            return;
        }
        ac(this.jz.get(i).getCommentId());
    }

    @Override // com.picacomic.fregata.a.e
    public void U(int i) {
        if (this.jz == null || this.jz.size() <= i || this.jz.get(i).getUser() == null || this.jz.get(i).getUser().getUserId() == null) {
            return;
        }
        ab(this.jz.get(i).getUser().getUserId());
    }

    @Override // com.picacomic.fregata.a.e
    public void V(int i) {
        k(i, -1);
    }

    public void a(String str, final int i, final int i2) {
        bA();
        this.ol = new d(getContext()).dO().w(com.picacomic.fregata.utils.e.z(getActivity()), str);
        this.ol.enqueue(new Callback<GeneralResponse<MessageResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.3
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<MessageResponse>> call, Throwable th) {
                th.printStackTrace();
                CommentFragment.this.bC();
                new c(CommentFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<MessageResponse>> call, Response<GeneralResponse<MessageResponse>> response) {
                if (response.code() == 200) {
                    Toast.makeText(CommentFragment.this.getContext(), "隱藏留言成功！", 0).show();
                    if (i2 < 0) {
                        if (CommentFragment.this.jz != null && CommentFragment.this.jz.size() > i && CommentFragment.this.op != null) {
                            CommentFragment.this.jz.get(i).setHide(true);
                            if (response.body().data != null && response.body().data.getMessage() != null) {
                                CommentFragment.this.jz.get(i).setContent(response.body().data.getMessage());
                            }
                            CommentFragment.this.op.notifyItemChanged(i);
                        }
                    } else if (CommentFragment.this.jz != null && CommentFragment.this.jz.size() > i && CommentFragment.this.jz.get(i).getArrayList() != null && CommentFragment.this.jz.get(i).getArrayList().size() > i2) {
                        CommentFragment.this.jz.get(i).getArrayList().get(i2).setHide(true);
                        if (response.body().data != null && response.body().data.getMessage() != null) {
                            CommentFragment.this.jz.get(i).getArrayList().get(i2).setContent(response.body().data.getMessage());
                        }
                        CommentFragment.this.op.notifyItemChanged(i);
                    }
                } else {
                    try {
                        new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CommentFragment.this.bC();
            }
        });
    }

    public void a(String str, final int i, final boolean z) {
        if (this.or) {
            return;
        }
        this.or = true;
        C(getResources().getString(2131689826));
        d dVar = new d(getContext());
        if (z) {
            this.oj = dVar.dO().d(com.picacomic.fregata.utils.e.z(getActivity()), str, 1);
        } else {
            this.oj = dVar.dO().d(com.picacomic.fregata.utils.e.z(getActivity()), str, this.jz.get(i).getCurrentPage() + 1);
        }
        this.oj.enqueue(new Callback<GeneralResponse<CommentsResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.17
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<CommentsResponse>> call, Throwable th) {
                CommentFragment.this.or = false;
                th.printStackTrace();
                CommentFragment.this.bC();
                new c(CommentFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<CommentsResponse>> call, Response<GeneralResponse<CommentsResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null && response.body().data.getComments() != null && response.body().data.getComments().getDocs() != null) {
                        if (z) {
                            CommentFragment.this.jz.get(i).setCurrentPage(1);
                            CommentFragment.this.jz.get(i).setArrayList(null);
                        }
                        ArrayList<CommentObject> arrayList = CommentFragment.this.jz.get(i).getArrayList();
                        ArrayList<CommentObject> arrayList2 = arrayList;
                        if (arrayList == null) {
                            arrayList2 = new ArrayList<>();
                        }
                        for (int i2 = 0; i2 < response.body().data.getComments().getDocs().size(); i2++) {
                            arrayList2.add(response.body().data.getComments().getDocs().get(i2));
                        }
                        CommentFragment.this.jz.get(i).setArrayList(arrayList2);
                        CommentFragment.this.jz.get(i).setCurrentPage(CommentFragment.this.jz.get(i).getCurrentPage() + 1);
                        CommentFragment.this.jz.get(i).setTotalPage(response.body().data.getComments().getPages());
                        CommentFragment.this.jz.get(i).setChildsCount(response.body().data.getComments().getTotal());
                        if (CommentFragment.this.jz.get(i).getCurrentPage() < CommentFragment.this.jz.get(i).getTotalPage()) {
                            CommentFragment.this.op.a(i, CommentFragment.this.jz.get(i).getArrayList(), true);
                        } else {
                            CommentFragment.this.op.a(i, CommentFragment.this.jz.get(i).getArrayList(), false);
                        }
                    }
                } else {
                    try {
                        new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CommentFragment.this.or = false;
                CommentFragment.this.bC();
            }
        });
    }

    public void a(String str, boolean z) {
        if (this.jz == null || this.jz.size() <= 0 || this.op == null) {
            return;
        }
        for (int i = 0; i < this.jz.size(); i++) {
            if (this.jz.get(i).getUser() != null && this.jz.get(i).getUser().getUserId() != null && this.jz.get(i).getUser().getUserId().equals(str)) {
                if (z) {
                    this.jz.get(i).getUser().setCharacter("https://www.picacomic.com/special/frame-dirty.png?r=2");
                } else {
                    this.jz.get(i).getUser().setCharacter(null);
                }
                this.op.notifyItemChanged(i);
            }
            if (this.jz.get(i).getArrayList() != null) {
                ArrayList<CommentObject> arrayList = this.jz.get(i).getArrayList();
                for (int i2 = 0; i2 < arrayList.size(); i2++) {
                    if (arrayList.get(i2) != null && arrayList.get(i2).getUser() != null && arrayList.get(i2).getUser().getUserId() != null && arrayList.get(i2).getUser().getUserId().equals(str)) {
                        if (z) {
                            arrayList.get(i2).getUser().setCharacter("https://www.picacomic.com/special/frame-dirty.png?r=2");
                        } else {
                            arrayList.get(i2).getUser().setCharacter(null);
                        }
                    }
                }
                this.op.notifyItemChanged(i);
            }
        }
    }

    public void aa(String str) {
        bA();
        this.mW = new d(getContext()).dO().v(com.picacomic.fregata.utils.e.z(getActivity()), str);
        this.mW.enqueue(new Callback<GeneralResponse<ActionResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.2
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<ActionResponse>> call, Throwable th) {
                th.printStackTrace();
                CommentFragment.this.bC();
                new c(CommentFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<ActionResponse>> call, Response<GeneralResponse<ActionResponse>> response) {
                if (response.code() == 200) {
                    f.aA(response.body().data.toString());
                    if (response.body().data != null) {
                        response.body().data.getAction();
                    }
                } else {
                    try {
                        new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CommentFragment.this.bC();
            }
        });
    }

    public void ab(final String str) {
        bA();
        this.oo = new d(getContext()).dO().p(com.picacomic.fregata.utils.e.z(getActivity()), str);
        this.oo.enqueue(new Callback<GeneralResponse<UserProfileDirtyResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.5
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<UserProfileDirtyResponse>> call, Throwable th) {
                th.printStackTrace();
                CommentFragment.this.bC();
                new c(CommentFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<UserProfileDirtyResponse>> call, Response<GeneralResponse<UserProfileDirtyResponse>> response) {
                if (response.code() == 200) {
                    if (CommentFragment.this.op != null && response.body() != null && response.body().data != null) {
                        CommentFragment.this.a(str, response.body().data.isDirty());
                    }
                    Toast.makeText(CommentFragment.this.getContext(), "修改污頭像成功！", 0).show();
                } else {
                    try {
                        String str2 = CommentFragment.TAG;
                        f.D(str2, response.code() + ": " + response.errorBody().string());
                        new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CommentFragment.this.bC();
            }
        });
    }

    public void ac(final String str) {
        bA();
        this.on = new d(getContext()).dO().y(com.picacomic.fregata.utils.e.z(getActivity()), str);
        this.on.enqueue(new Callback<GeneralResponse<CommentPostToTopResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.6
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<CommentPostToTopResponse>> call, Throwable th) {
                th.printStackTrace();
                CommentFragment.this.bC();
                new c(CommentFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<CommentPostToTopResponse>> call, Response<GeneralResponse<CommentPostToTopResponse>> response) {
                if (response.code() == 200) {
                    if (CommentFragment.this.op != null && response.body() != null && response.body().data != null) {
                        CommentFragment.this.b(str, response.body().data.isTop());
                    }
                    Toast.makeText(CommentFragment.this.getContext(), "修改置頂成功！\n更新介面需重新進入。", 0).show();
                } else {
                    try {
                        new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CommentFragment.this.bC();
            }
        });
    }

    public void b(String str, final int i, final int i2) {
        bA();
        this.om = new d(getContext()).dO().x(com.picacomic.fregata.utils.e.z(getActivity()), str);
        this.om.enqueue(new Callback<GeneralResponse<MessageResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.4
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<MessageResponse>> call, Throwable th) {
                th.printStackTrace();
                CommentFragment.this.bC();
                new c(CommentFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<MessageResponse>> call, Response<GeneralResponse<MessageResponse>> response) {
                if (response.code() == 200) {
                    Toast.makeText(CommentFragment.this.getContext(), "舉報留言成功！", 0).show();
                    if (i2 < 0) {
                        if (CommentFragment.this.jz != null && CommentFragment.this.jz.size() > i && CommentFragment.this.op != null) {
                            CommentFragment.this.jz.get(i).setHide(true);
                            if (response.body().data != null && response.body().data.getMessage() != null) {
                                CommentFragment.this.jz.get(i).setContent(response.body().data.getMessage());
                            }
                            CommentFragment.this.op.notifyItemChanged(i);
                        }
                    } else if (CommentFragment.this.jz != null && CommentFragment.this.jz.size() > i && CommentFragment.this.jz.get(i).getArrayList() != null && CommentFragment.this.jz.get(i).getArrayList().size() > i2) {
                        CommentFragment.this.jz.get(i).getArrayList().get(i2).setHide(true);
                        if (response.body().data != null && response.body().data.getMessage() != null) {
                            CommentFragment.this.jz.get(i).getArrayList().get(i2).setContent(response.body().data.getMessage());
                        }
                        CommentFragment.this.op.notifyItemChanged(i);
                    }
                } else {
                    try {
                        new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CommentFragment.this.bC();
            }
        });
    }

    public void b(String str, boolean z) {
        if (this.jz == null || this.jz.size() <= 0 || this.op == null) {
            return;
        }
        for (int i = 0; i < this.jz.size(); i++) {
            if (this.jz.get(i).getCommentId().equals(str)) {
                this.jz.get(i).setTop(z);
                this.op.notifyItemChanged(i);
            }
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (getActivity() != null && (getActivity() instanceof AppCompatActivity)) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        }
        a(this.toolbar, 2131689973, true);
        D(this.oq);
        if (this.kE) {
            C(true);
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        try {
            this.op.notifyDataSetChanged();
            if (isAdded()) {
                if (this.jz != null && (this.jz == null || this.jz.size() != 0)) {
                    this.linearLayout_noComment.setVisibility(8);
                    EditText editText = this.editText_currentPage;
                    editText.setText(this.page + "");
                    TextView textView = this.textView_totalPage;
                    textView.setText(this.totalPage + "");
                }
                Picasso.with(getActivity()).load(2131230962).into(this.imageView_empty);
                this.linearLayout_noComment.setVisibility(0);
                EditText editText2 = this.editText_currentPage;
                editText2.setText(this.page + "");
                TextView textView2 = this.textView_totalPage;
                textView2.setText(this.totalPage + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Update UI error", 0).show();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.recyclerView_comments.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        this.op = new CommentRecyclerViewAdapter(getActivity(), this.jH, this.ot, this.jz, this);
        this.recyclerView_comments.setAdapter(this.op);
        this.recyclerView_comments.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition() != CommentFragment.this.jz.size() - 1 || CommentFragment.this.page >= CommentFragment.this.totalPage) {
                    return;
                }
                CommentFragment.this.page++;
                CommentFragment.this.C(false);
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
        this.editText_currentPage.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.11
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == 6) {
                    int i2 = CommentFragment.this.page;
                    try {
                        CommentFragment.this.page = Integer.parseInt(textView.getText().toString());
                    } catch (Exception e) {
                        CommentFragment.this.page = i2;
                    }
                    CommentFragment.this.C(true);
                    return true;
                }
                return false;
            }
        });
        this.button_postComment.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CommentFragment.this.editText_inputField != null && CommentFragment.this.editText_inputField.getText() != null && CommentFragment.this.editText_inputField.getText().length() < 2) {
                    AlertDialogCenter.emptyComment(CommentFragment.this.getContext());
                } else if (!CommentFragment.this.oq || CommentFragment.this.ou == null || CommentFragment.this.ou.equalsIgnoreCase("")) {
                    CommentFragment.this.db();
                } else {
                    CommentFragment.this.dc();
                }
            }
        });
        this.button_replyCancel.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommentFragment.this.D(false);
            }
        });
    }

    public void db() {
        C(getResources().getString(2131689829));
        d dVar = new d(getContext());
        if (this.os != 2) {
            a dO = dVar.dO();
            String z = com.picacomic.fregata.utils.e.z(getActivity());
            String str = this.comicId;
            this.oi = dO.a(z, str, new CommentBody(this.editText_inputField.getText().toString() + ""));
        } else {
            a dO2 = dVar.dO();
            String z2 = com.picacomic.fregata.utils.e.z(getActivity());
            String str2 = this.gameId;
            this.oi = dO2.c(z2, str2, new CommentBody(this.editText_inputField.getText().toString() + ""));
        }
        this.oi.enqueue(new Callback<GeneralResponse<PostCommentResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.16
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<PostCommentResponse>> call, Throwable th) {
                th.printStackTrace();
                CommentFragment.this.bC();
                new c(CommentFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<PostCommentResponse>> call, Response<GeneralResponse<PostCommentResponse>> response) {
                if (response.code() == 200) {
                    CommentFragment.this.editText_inputField.setText("");
                    CommentFragment.this.page = 1;
                    CommentFragment.this.C(true);
                } else {
                    try {
                        new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CommentFragment.this.bC();
            }
        });
    }

    public void dc() {
        C(getResources().getString(2131689829));
        a dO = new d(getContext()).dO();
        String z = com.picacomic.fregata.utils.e.z(getActivity());
        String str = this.ou;
        this.ok = dO.b(z, str, new CommentBody(this.editText_inputField.getText().toString() + ""));
        this.ok.enqueue(new Callback<GeneralResponse<PostCommentResponse>>() { // from class: com.picacomic.fregata.fragments.CommentFragment.18
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<PostCommentResponse>> call, Throwable th) {
                th.printStackTrace();
                CommentFragment.this.bC();
                new c(CommentFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<PostCommentResponse>> call, Response<GeneralResponse<PostCommentResponse>> response) {
                if (response.code() == 200) {
                    CommentFragment.this.editText_inputField.setText("");
                    CommentFragment.this.a(CommentFragment.this.ou, CommentFragment.this.ov, true);
                } else {
                    try {
                        new c(CommentFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                CommentFragment.this.bC();
            }
        });
    }

    @Override // com.picacomic.fregata.a.e
    public void f(int i, int i2) {
        if (this.jz == null || this.jz.size() <= i || this.jz.get(i) == null || this.jz.get(i).getArrayList() == null || this.jz.get(i).getArrayList().size() <= i2 || this.jz.get(i).getArrayList().get(i2).getUser() == null) {
            return;
        }
        a(this.jz.get(i).getArrayList().get(i2).getUser());
    }

    @Override // com.picacomic.fregata.a.e
    public void g(int i, int i2) {
        if (this.jz == null || this.jz.size() <= i || this.jz.get(i).getArrayList() == null || this.jz.get(i).getArrayList().size() <= i2) {
            return;
        }
        aa(this.jz.get(i).getArrayList().get(i2).getCommentId());
        if (this.jz.get(i).getArrayList().get(i2).isLiked()) {
            this.jz.get(i).getArrayList().get(i2).setLikesCount(this.jz.get(i).getArrayList().get(i2).getLikesCount() - 1);
            this.jz.get(i).getArrayList().get(i2).setLiked(false);
            Toast.makeText(getActivity(), 2131689580, 0).show();
        } else {
            this.jz.get(i).getArrayList().get(i2).setLikesCount(this.jz.get(i).getArrayList().get(i2).getLikesCount() + 1);
            this.jz.get(i).getArrayList().get(i2).setLiked(true);
            Toast.makeText(getActivity(), 2131689581, 0).show();
        }
        this.op.notifyItemChanged(i);
    }

    @Override // com.picacomic.fregata.a.e
    public void h(int i, int i2) {
        if (this.jz == null || this.jz.size() <= i || this.jz.get(i) == null || this.jz.get(i).getArrayList() == null || this.jz.get(i).getArrayList().size() <= i2 || this.jz.get(i).getArrayList().get(i2).getUser() == null) {
            return;
        }
        a(this.jz.get(i).getArrayList().get(i2).getUser());
    }

    @Override // com.picacomic.fregata.a.e
    public void i(final int i, final int i2) {
        AlertDialogCenter.hideComment(getContext(), new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CommentFragment.this.a(CommentFragment.this.jz.get(i).getArrayList().get(i2).getCommentId(), i, i2);
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.oq = false;
        this.or = false;
        this.ou = "";
        if (this.os == 1) {
            this.toolbar.setVisibility(8);
            this.linearLayout_inputBar.setVisibility(8);
        } else {
            this.toolbar.setVisibility(0);
            this.linearLayout_inputBar.setVisibility(0);
        }
        if (this.jz == null) {
            this.jz = new ArrayList<>();
            this.kE = true;
            this.page = 1;
        } else {
            this.kE = false;
        }
        String B = com.picacomic.fregata.utils.e.B(getContext());
        if (B == null || B.equalsIgnoreCase("")) {
            return;
        }
        UserProfileObject userProfileObject = (UserProfileObject) new Gson().fromJson(B, (Class<Object>) UserProfileObject.class);
        if (userProfileObject != null && userProfileObject.getUserId() != null && this.ot != null && this.ot.equals(userProfileObject.getUserId())) {
            this.jG = true;
        }
        if (userProfileObject == null || userProfileObject.getEmail() == null || !userProfileObject.getEmail().endsWith("@picacomic.com")) {
            return;
        }
        this.jG = true;
    }

    @Override // com.picacomic.fregata.a.e
    public void j(int i, int i2) {
        k(i, i2);
    }

    public void k(final int i, final int i2) {
        if (this.jG) {
            AlertDialogCenter.commentOptionsAdvance(getContext(), new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.10
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i3) {
                    switch (i3) {
                        case 0:
                            AlertDialogCenter.reportComment(CommentFragment.this.getContext(), new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.10.1
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    if (i2 >= 0) {
                                        CommentFragment.this.b(CommentFragment.this.jz.get(i).getArrayList().get(i2).getCommentId(), i, i2);
                                    } else {
                                        CommentFragment.this.b(CommentFragment.this.jz.get(i).getCommentId(), i, -1);
                                    }
                                }
                            });
                            break;
                        case 1:
                            if (i2 < 0) {
                                if (CommentFragment.this.jz != null && CommentFragment.this.jz.size() > i && CommentFragment.this.jz.get(i).getUser() != null && CommentFragment.this.jz.get(i).getUser().getUserId() != null) {
                                    CommentFragment.this.ab(CommentFragment.this.jz.get(i).getUser().getUserId());
                                    break;
                                }
                            } else if (CommentFragment.this.jz != null && CommentFragment.this.jz.size() > i && CommentFragment.this.jz.get(i).getUser() != null && CommentFragment.this.jz.get(i).getUser().getUserId() != null && CommentFragment.this.jz.get(i).getArrayList() != null && CommentFragment.this.jz.get(i).getArrayList().size() > i2 && CommentFragment.this.jz.get(i).getArrayList().get(i2) != null && CommentFragment.this.jz.get(i).getArrayList().get(i2).getUser() != null && CommentFragment.this.jz.get(i).getArrayList().get(i2).getUser().getUserId() != null) {
                                CommentFragment.this.ab(CommentFragment.this.jz.get(i).getArrayList().get(i2).getUser().getUserId());
                                break;
                            }
                            break;
                        case 2:
                            if (i2 < 0) {
                                if (CommentFragment.this.jz != null && CommentFragment.this.jz.size() > i && CommentFragment.this.jz.get(i).getCommentId() != null) {
                                    CommentFragment.this.ac(CommentFragment.this.jz.get(i).getCommentId());
                                    break;
                                }
                            } else {
                                Toast.makeText(CommentFragment.this.getContext(), 2131689993, 0).show();
                                break;
                            }
                            break;
                        case 3:
                            AlertDialogCenter.hideComment(CommentFragment.this.getContext(), new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.10.2
                                @Override // android.view.View.OnClickListener
                                public void onClick(View view) {
                                    if (i2 >= 0) {
                                        CommentFragment.this.a(CommentFragment.this.jz.get(i).getArrayList().get(i2).getCommentId(), i, i2);
                                    } else {
                                        CommentFragment.this.a(CommentFragment.this.jz.get(i).getCommentId(), i, -1);
                                    }
                                }
                            });
                            break;
                    }
                    dialogInterface.dismiss();
                }
            });
        } else {
            AlertDialogCenter.commentOptions(getContext(), new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.CommentFragment.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (i2 >= 0) {
                        CommentFragment.this.b(CommentFragment.this.jz.get(i).getArrayList().get(i2).getCommentId(), i, i2);
                    } else {
                        CommentFragment.this.b(CommentFragment.this.jz.get(i).getCommentId(), i, -1);
                    }
                }
            });
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.comicId = getArguments().getString("COMIC_ID", "");
            this.gameId = getArguments().getString("GAME_ID", "");
            this.userId = getArguments().getString("USER_ID", "");
            this.ot = getArguments().getString("KNIGHT_USER_ID", "");
            this.os = getArguments().getInt("FRAGMENT_TYPE", 0);
            if (getArguments().getParcelable("USER_BASIC_OBJECT") != null) {
                this.jH = (UserBasicObject) getArguments().getParcelable("USER_BASIC_OBJECT");
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(2131492870, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427409, viewGroup, false);
        setHasOptionsMenu(true);
        if (getActivity() != null) {
            a(inflate);
        }
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        if (this.mW != null) {
            this.mW.cancel();
        }
        if (this.ol != null) {
            this.ol.cancel();
        }
        if (this.om != null) {
            this.om.cancel();
        }
        if (this.og != null) {
            this.og.cancel();
        }
        if (this.oh != null) {
            this.oh.cancel();
        }
        if (this.oj != null) {
            this.oj.cancel();
        }
        if (this.oi != null) {
            this.oi.cancel();
        }
        if (this.ok != null) {
            this.ok.cancel();
        }
        super.onDetach();
    }

    @Override // android.support.v4.app.Fragment
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 2131296272) {
            if (this.linearLayout_commentPage != null && this.linearLayout_commentPage.getVisibility() == 8) {
                this.linearLayout_commentPage.setVisibility(0);
            } else if (this.linearLayout_commentPage != null && this.linearLayout_commentPage.getVisibility() == 0) {
                this.linearLayout_commentPage.setVisibility(8);
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
