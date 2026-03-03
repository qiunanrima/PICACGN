package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.google.gson.Gson;
import com.picacomic.fregata.adapters.AnonymousChatRecyclerViewAdapter;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.objects.AnonymousChatActionDataObject;
import com.picacomic.fregata.objects.AnonymousChatDataObject;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.UserProfileResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import io.socket.b.a;
import io.socket.client.b;
import io.socket.client.d;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/AnonymousChatFragment.class */
public class AnonymousChatFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "AnonymousChatFragment";
    @BindView(2131296305)
    Button button_match;
    @BindView(2131296306)
    Button button_send;
    @BindView(2131296444)
    EditText editText_name;
    @BindView(2131296445)
    EditText editText_typeSpace;
    Gson gson;
    @BindView(2131296540)
    ImageButton imageButton_leave;
    LinearLayoutManager jQ;
    UserProfileObject jW;
    Call<GeneralResponse<UserProfileResponse>> jX;
    AnonymousChatRecyclerViewAdapter jY;
    private d jZ;
    ArrayList<AnonymousChatDataObject> ja;
    String ka;
    String kb;
    String kc;
    @BindView(2131296641)
    LinearLayout linearLayout_message;
    @BindView(2131296642)
    LinearLayout linearLayout_name;
    @BindView(2131296643)
    LinearLayout linearLayout_typeSpace;
    @BindView(2131296752)
    RecyclerView recyclerView;
    @BindView(2131296840)
    TextView textView_matcherName;
    @BindView(2131296841)
    TextView textView_message;
    public final String jU = "action";
    public final String jV = "response";
    private a.InterfaceC0017a kd = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.AnonymousChatFragment.1
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(Object... objArr) {
            AnonymousChatFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.AnonymousChatFragment.1.1
                @Override // java.lang.Runnable
                public void run() {
                    f.D(AnonymousChatFragment.TAG, "Testing INIT");
                }
            });
        }
    };
    private a.InterfaceC0017a ke = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.AnonymousChatFragment.3
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            AnonymousChatFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.AnonymousChatFragment.3.1
                @Override // java.lang.Runnable
                public void run() {
                    AnonymousChatActionDataObject b = AnonymousChatFragment.this.b((JSONObject) objArr[0]);
                    if (b == null) {
                        return;
                    }
                    String str = AnonymousChatFragment.TAG;
                    f.F(str, "ACTION: " + b.toString());
                    b.getActionType();
                }
            });
        }
    };
    private a.InterfaceC0017a kf = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.AnonymousChatFragment.4
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            AnonymousChatFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.AnonymousChatFragment.4.1
                @Override // java.lang.Runnable
                public void run() {
                    AnonymousChatActionDataObject b = AnonymousChatFragment.this.b((JSONObject) objArr[0]);
                    if (b == null) {
                        return;
                    }
                    String str = AnonymousChatFragment.TAG;
                    f.F(str, "RESPONSE: " + b.toString());
                    if (b.getResponseType() != null) {
                        AnonymousChatFragment.this.a(b.getResponseType(), b.getData());
                    }
                }
            });
        }
    };

    public void I(String str) {
        TextView textView = this.textView_message;
        textView.setText(str + " joined\n");
        TextView textView2 = this.textView_matcherName;
        textView2.setText(str + "");
        this.linearLayout_typeSpace.setVisibility(0);
        this.linearLayout_message.setVisibility(0);
        this.linearLayout_name.setVisibility(8);
    }

    public void J(String str) {
        e.v(getContext(), null);
        e.w(getContext(), null);
        this.textView_message.setText(str);
        this.linearLayout_typeSpace.setVisibility(8);
        this.linearLayout_message.setVisibility(8);
        this.linearLayout_name.setVisibility(0);
    }

    public void a(String str, AnonymousChatDataObject anonymousChatDataObject) {
        String str2 = TAG;
        f.F(str2, str + ": " + anonymousChatDataObject);
        if (str.equalsIgnoreCase("FOUND_MATCHER")) {
            this.ka = anonymousChatDataObject.getName();
            this.kb = anonymousChatDataObject.getRoomId();
            e.v(getContext(), this.kb);
            e.w(getContext(), this.ka);
            I(this.ka);
            f.F(TAG, "MATCHED");
        } else if (str.equalsIgnoreCase("NO_MATCHER")) {
            J("Not Matched");
            AlertDialogCenter.showCustomAlertDialog(getContext(), 2131689532);
            f.F(TAG, "NOT MATCHED");
        } else if (str.equalsIgnoreCase("GOT_MESSAGE")) {
            if (anonymousChatDataObject.getUserId() == null || this.jW == null || this.jW.getUserId() == null || !anonymousChatDataObject.getUserId().equalsIgnoreCase(this.jW.getUserId())) {
                this.ja.add(0, anonymousChatDataObject);
                this.jY.notifyDataSetChanged();
            }
            f.F(TAG, "GOT MESSAGE");
        }
    }

    public AnonymousChatActionDataObject b(JSONObject jSONObject) {
        JSONObject jSONObject2;
        AnonymousChatActionDataObject anonymousChatActionDataObject = new AnonymousChatActionDataObject(null, null, new AnonymousChatDataObject(null, null, null, null, null));
        try {
            if (jSONObject.has("actionType")) {
                anonymousChatActionDataObject.setActionType(jSONObject.getString("actionType"));
            }
            if (jSONObject.has("responseType")) {
                anonymousChatActionDataObject.setResponseType(jSONObject.getString("responseType"));
            }
            if (jSONObject.has("data") && (jSONObject2 = jSONObject.getJSONObject("data")) != null) {
                if (jSONObject2.has("id")) {
                    anonymousChatActionDataObject.getData().setId(jSONObject2.getString("id"));
                }
                if (jSONObject2.has("userId")) {
                    anonymousChatActionDataObject.getData().setUserId(jSONObject2.getString("userId"));
                }
                if (jSONObject2.has("name")) {
                    anonymousChatActionDataObject.getData().setName(jSONObject2.getString("name"));
                }
                if (jSONObject2.has("message")) {
                    anonymousChatActionDataObject.getData().setMessage(jSONObject2.getString("message"));
                }
                if (jSONObject2.has("roomId")) {
                    anonymousChatActionDataObject.getData().setRoomId(jSONObject2.getString("roomId"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return anonymousChatActionDataObject;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (this.kb == null) {
            J("Welcome");
            return;
        }
        this.ka = e.ah(getContext());
        I(this.ka + "");
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.button_match.setOnClickListener(this);
        this.button_send.setOnClickListener(this);
        this.imageButton_leave.setOnClickListener(this);
        this.jQ = new LinearLayoutManager(getContext(), 1, true);
        this.jY = new AnonymousChatRecyclerViewAdapter(getContext(), this.kc, this.ja);
        this.recyclerView.setLayoutManager(this.jQ);
        this.recyclerView.setAdapter(this.jY);
    }

    public void cc() {
        if (this.jW == null || this.jW.getUserId() == null) {
            return;
        }
        String userId = this.jW.getUserId();
        String obj = this.editText_name != null ? this.editText_name.getText().toString() : "";
        try {
            String json = this.gson.toJson(new AnonymousChatActionDataObject("MATCHING", null, new AnonymousChatDataObject(null, userId, obj + "", null, null)), AnonymousChatActionDataObject.class);
            String str = TAG;
            f.F(str, "GSON: " + json);
            if (this.jZ == null) {
                f.F(TAG, "SOCKET NULL");
            } else {
                String str2 = TAG;
                f.F(str2, "NEW MATCH: " + json);
                d dVar = this.jZ;
                dVar.b("action", json + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cd() {
        C(getResources().getString(2131689823));
        f.aA("Show Progress");
        this.jX = new com.picacomic.fregata.b.d(getContext()).dO().am(e.z(getActivity()));
        this.jX.enqueue(new Callback<GeneralResponse<UserProfileResponse>>() { // from class: com.picacomic.fregata.fragments.AnonymousChatFragment.5
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<UserProfileResponse>> call, Throwable th) {
                th.printStackTrace();
                f.aA("dismiss progress");
                AnonymousChatFragment.this.bC();
                new c(AnonymousChatFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<UserProfileResponse>> call, Response<GeneralResponse<UserProfileResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(AnonymousChatFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.getUser() != null) {
                    AnonymousChatFragment.this.jW = response.body().data.getUser();
                    AnonymousChatFragment.this.kc = AnonymousChatFragment.this.jW.getUserId();
                    if (AnonymousChatFragment.this.jY != null) {
                        AnonymousChatFragment.this.jY.setUserId(AnonymousChatFragment.this.kc);
                    }
                    if (AnonymousChatFragment.this.getActivity() != null) {
                        if (AnonymousChatFragment.this.gson == null) {
                            AnonymousChatFragment.this.gson = new Gson();
                        }
                        e.i(AnonymousChatFragment.this.getContext(), AnonymousChatFragment.this.gson.toJson(AnonymousChatFragment.this.jW));
                        AnonymousChatFragment.this.bI();
                    }
                }
                f.aA("dismiss progress");
                AnonymousChatFragment.this.bC();
            }
        });
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.gson = new Gson();
        String B = e.B(getContext());
        this.kc = "";
        if (B == null || B.equalsIgnoreCase("")) {
            cd();
        } else {
            this.jW = (UserProfileObject) this.gson.fromJson(B, (Class<Object>) UserProfileObject.class);
            this.kc = this.jW.getUserId();
        }
        this.kb = e.ag(getContext());
        this.ka = e.ah(getContext());
        this.ja = new ArrayList<>();
    }

    public void j(String str, String str2) {
        if (this.jW == null || this.jW.getUserId() == null) {
            return;
        }
        String userId = this.jW.getUserId();
        String obj = this.editText_name != null ? this.editText_name.getText().toString() : "";
        AnonymousChatActionDataObject anonymousChatActionDataObject = new AnonymousChatActionDataObject("SEND_MESSAGE", null, new AnonymousChatDataObject(null, userId, obj + "", str2, str));
        this.jZ.b("action", this.gson.toJson(anonymousChatActionDataObject, AnonymousChatActionDataObject.class));
        this.ja.add(0, anonymousChatActionDataObject.getData());
        this.jY.notifyDataSetChanged();
        this.editText_typeSpace.setText("");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.button_send && this.editText_typeSpace != null && this.editText_typeSpace.getText() != null && this.editText_typeSpace.getText().toString().length() > 0) {
            j(this.kb, this.editText_typeSpace.getText().toString() + "");
        }
        if (view == this.button_match) {
            cc();
        }
        if (view == this.imageButton_leave) {
            AlertDialogCenter.showCustomAlertDialog(getContext(), 2131230947, 2131689618, 2131689617, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.AnonymousChatFragment.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    AnonymousChatFragment.this.s(false);
                    AnonymousChatFragment.this.J("leave room");
                }
            }, (View.OnClickListener) null);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b.a aVar = new b.a();
        aVar.yT = new String[]{"websocket"};
        try {
            this.jZ = b.a("https://secret-chat.wakamoment.gq", aVar);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.jZ.a("action", this.ke);
        this.jZ.a("response", this.kf);
        this.jZ.a("connect", this.kd);
        this.jZ.eJ();
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427394, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        s(true);
    }

    public void s(boolean z) {
        if (this.jW == null || this.jW.getUserId() == null) {
            return;
        }
        this.jZ.b("action", this.gson.toJson(new AnonymousChatActionDataObject("LEAVE_MATCHING", null, new AnonymousChatDataObject(null, this.jW.getUserId(), null, null, null)), AnonymousChatActionDataObject.class));
        if (z) {
            Toast.makeText(getActivity(), "LEAVE", 0).show();
            this.jZ.eO();
            this.jZ.c("action", this.ke);
        }
    }
}
