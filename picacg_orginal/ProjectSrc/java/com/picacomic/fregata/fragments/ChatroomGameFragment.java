package com.picacomic.fregata.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import com.google.gson.Gson;
import com.picacomic.fregata.adapters.ChatroomGameMessageRecyclerViewAdapter;
import com.picacomic.fregata.objects.chatroomGameObjects.ChatroomGameEmit;
import com.picacomic.fregata.utils.ChatroomGame.ChatroomGameView;
import com.picacomic.fregata.utils.f;
import io.socket.b.a;
import io.socket.client.b;
import io.socket.client.d;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChatroomGameFragment.class */
public class ChatroomGameFragment extends BaseFragment implements View.OnClickListener {
    public static final String TAG = "ChatroomGameFragment";
    ArrayList<String> arrayList;
    @BindView(2131296403)
    Button button_t1;
    @BindView(2131296404)
    Button button_t2;
    @BindView(2131296405)
    Button button_t3;
    @BindView(2131296406)
    Button button_t4;
    @BindView(2131296415)
    ChatroomGameView gameView;
    Gson gson;
    LinearLayoutManager jQ;
    private d jZ;
    ChatroomGameMessageRecyclerViewAdapter mF;
    ChatroomGameEmit mG;
    private a.InterfaceC0017a mH;
    private a.InterfaceC0017a mI;
    private a.InterfaceC0017a mJ;
    @BindView(2131296756)
    RecyclerView recyclerView_gameMessage;

    public ChatroomGameFragment() {
        b.a aVar = new b.a();
        aVar.yT = new String[]{"websocket"};
        try {
            this.jZ = b.a("https://game.picacomic.com", aVar);
        } catch (URISyntaxException e) {
        }
        this.mH = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomGameFragment.1
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                ChatroomGameFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomGameFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObject = (JSONObject) objArr[0];
                        try {
                            if (jSONObject.has("message")) {
                                jSONObject.getString("message");
                            }
                            f.D(ChatroomGameFragment.TAG, "get Profile");
                            Toast.makeText(ChatroomGameFragment.this.getContext(), "get profile", 0).show();
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        };
        this.mI = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomGameFragment.2
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                ChatroomGameFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomGameFragment.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObject = (JSONObject) objArr[0];
                        try {
                            if (jSONObject.has("message")) {
                                jSONObject.getString("message");
                            }
                            Toast.makeText(ChatroomGameFragment.this.getContext(), "get Explored", 0).show();
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        };
        this.mJ = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomGameFragment.3
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                ChatroomGameFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomGameFragment.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObject = (JSONObject) objArr[0];
                        try {
                            if (jSONObject.has("message")) {
                                jSONObject.getString("message");
                            }
                            Toast.makeText(ChatroomGameFragment.this.getContext(), "get Confirmed", 0).show();
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        };
    }

    private void cI() {
        cG();
        this.jZ.b("PICA_GAME", this.gson.toJson(new ChatroomGameEmit("INIT", "testId")));
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, com.picacomic.fregata.a.i
    public void b(View view) {
        Toast.makeText(getContext(), "Game BackPress", 0).show();
        ChatroomFragment chatroomFragment = (ChatroomFragment) getParentFragment();
        if (chatroomFragment != null) {
            chatroomFragment.b(view);
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        for (int i = 0; i < 30; i++) {
            this.arrayList.add("Message");
        }
        this.mF.notifyDataSetChanged();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    public void cG() {
        if (this.gson == null) {
            this.gson = new Gson();
        }
    }

    public void cH() {
        this.jZ.a("PICA_GAME/GOT_PROFILE", this.mH);
        this.jZ.a("PICA_GAME/EXPLORED", this.mI);
        this.jZ.a("PICA_GAME/CONFIRMED", this.mJ);
        this.jZ.eJ();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.recyclerView_gameMessage.setLayoutManager(this.jQ);
        this.recyclerView_gameMessage.setAdapter(this.mF);
        this.button_t1.setOnClickListener(this);
        this.button_t2.setOnClickListener(this);
        this.button_t3.setOnClickListener(this);
        this.button_t4.setOnClickListener(this);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        if (this.arrayList == null) {
            this.arrayList = new ArrayList<>();
        }
        if (this.mF == null) {
            this.mF = new ChatroomGameMessageRecyclerViewAdapter(getContext(), this.arrayList);
        }
        if (this.jQ == null) {
            this.jQ = new LinearLayoutManager(getContext(), 1, true);
        }
        cH();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.button_t1) {
            Toast.makeText(getContext(), "test 1 - INIT", 0).show();
            cI();
        }
        if (view == this.button_t2) {
            Toast.makeText(getContext(), "test 2", 0).show();
            this.gameView.getHeroSprite().setAction(1);
        }
        if (view == this.button_t3) {
            Toast.makeText(getContext(), "test 2", 0).show();
            this.gameView.getHeroSprite().setAction(2);
        }
        if (view == this.button_t4) {
            Toast.makeText(getContext(), "test 2", 0).show();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mG = new ChatroomGameEmit("test");
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427401, viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        if (this.gameView != null) {
            this.gameView.setRunning(false);
        }
        this.jZ.eO();
        this.jZ.c("PICA_GAME/GOT_PROFILE", this.mH);
        this.jZ.c("PICA_GAME/EXPLORED", this.mI);
        this.jZ.c("PICA_GAME/CONFIRMED", this.mJ);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.gameView != null) {
            this.gameView.setRunning(true);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        super.onStop();
        if (this.gameView != null) {
            this.gameView.setRunning(false);
        }
    }
}
