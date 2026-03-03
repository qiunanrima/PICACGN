package com.picacomic.fregata.services;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.gson.Gson;
import com.picacomic.fregata.a.a;
import com.picacomic.fregata.activities.LoginActivity;
import com.picacomic.fregata.adapters.ChatroomRecyclerViewAdapter;
import com.picacomic.fregata.objects.ChatBaseObject;
import com.picacomic.fregata.objects.ChatMessageObject;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import io.socket.b.a;
import io.socket.client.b;
import io.socket.client.d;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: picacg_1.jar:com/picacomic/fregata/services/ChatroomService.class */
public class ChatroomService extends Service implements View.OnClickListener, a {
    public static final String TAG = "ChatroomService";
    ArrayList<ChatBaseObject> arrayList;
    @BindView(2131296388)
    Button button_close;
    @BindView(2131296389)
    Button button_messageMode;
    @BindView(2131296390)
    Button button_move;
    @BindView(2131296391)
    Button button_send;
    @BindView(2131296392)
    Button button_sizeController;
    @BindView(2131296481)
    EditText editText_input;
    Handler handler;
    private int iK;
    LinearLayoutManager jQ;
    UserProfileObject jW;
    private d jZ;
    private LayoutInflater jc;
    private a.InterfaceC0017a lP;
    private a.InterfaceC0017a lQ;
    private a.InterfaceC0017a lS;
    ChatroomRecyclerViewAdapter le;
    CountDownTimer lh;
    @BindView(2131296769)
    RecyclerView recyclerView_chat;
    @BindView(2131296778)
    RelativeLayout relativeLayout_input;
    private int screenWidth;
    private WindowManager tC;
    View tD;
    WindowManager.LayoutParams tE;
    ChatMessageObject tH;
    @BindView(2131296849)
    TextView textView_atList;
    @BindView(2131296853)
    TextView textView_reply;
    @BindView(2131296855)
    TextView textView_toastMsg;
    @BindView(2131296989)
    TextView textView_totalUserCount;
    private int tF = ItemTouchHelper.Callback.DEFAULT_DRAG_ANIMATION_DURATION;
    boolean tG = false;
    String at = "";
    String lt = "";
    String reply = "";
    String lu = "";

    public ChatroomService() {
        try {
            this.jZ = b.aE("https://chat.picacomic.com");
        } catch (URISyntaxException e) {
        }
        this.lS = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.services.ChatroomService.12
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                ChatroomService.this.runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.services.ChatroomService.12.1
                    @Override // java.lang.Runnable
                    public void run() {
                        String[] strArr;
                        JSONArray jSONArray;
                        JSONObject jSONObject = (JSONObject) objArr[0];
                        try {
                            String string = jSONObject.has("id") ? jSONObject.getString("id") : "";
                            if (jSONObject.has("userId")) {
                                string = jSONObject.getString("userId");
                            }
                            String str = string;
                            if (jSONObject.has("user_id")) {
                                str = jSONObject.getString("user_id");
                            }
                            String string2 = jSONObject.has("unique_id") ? jSONObject.getString("unique_id") : "";
                            int i = jSONObject.has("level") ? jSONObject.getInt("level") : 0;
                            String string3 = jSONObject.has("avatar") ? jSONObject.getString("avatar") : "";
                            String string4 = jSONObject.getString("name");
                            String string5 = jSONObject.has("title") ? jSONObject.getString("title") : "";
                            String string6 = jSONObject.has("message") ? jSONObject.getString("message") : "";
                            boolean z = jSONObject.has("verified") ? jSONObject.getBoolean("verified") : false;
                            String string7 = jSONObject.has("gender") ? jSONObject.getString("gender") : "";
                            String string8 = jSONObject.has("platform") ? jSONObject.getString("platform") : "";
                            String string9 = jSONObject.has("activation_date") ? jSONObject.getString("activation_date") : null;
                            if (jSONObject.has("activationDate")) {
                                string9 = jSONObject.getString("activationDate");
                            }
                            String string10 = jSONObject.has("at") ? jSONObject.getString("at") : "";
                            String string11 = jSONObject.has("reply_name") ? jSONObject.getString("reply_name") : "";
                            String string12 = jSONObject.has("reply") ? jSONObject.getString("reply") : "";
                            String string13 = jSONObject.has("email") ? jSONObject.getString("email") : "";
                            String string14 = jSONObject.has("block_user_id") ? jSONObject.getString("block_user_id") : "";
                            String string15 = jSONObject.has("event_icon") ? jSONObject.getString("event_icon") : null;
                            if (!jSONObject.has("event_colors") || (jSONArray = jSONObject.getJSONArray("event_colors")) == null || jSONArray.length() <= 0) {
                                strArr = null;
                            } else {
                                strArr = new String[jSONArray.length()];
                                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                    strArr[i2] = jSONArray.getString(i2);
                                }
                            }
                            ChatroomService.this.a(str, string2, i, string13, string3, string4, string5, string7, string8, string9, string10, string11, string12, string6, string14, false, z, jSONObject.has("character") ? jSONObject.getString("character") : null, string15, strArr);
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        };
        this.lP = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.services.ChatroomService.2
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                ChatroomService.this.runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.services.ChatroomService.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObject = (JSONObject) objArr[0];
                        try {
                            String string = jSONObject.has("connections") ? jSONObject.getString("connections") : "";
                            ChatroomService.this.textView_totalUserCount.setText(2131689659);
                            TextView textView = ChatroomService.this.textView_totalUserCount;
                            textView.append(string + "");
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        };
        this.lQ = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.services.ChatroomService.3
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                ChatroomService.this.runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.services.ChatroomService.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        JSONObject jSONObject = (JSONObject) objArr[0];
                        try {
                            String string = jSONObject.has("connections") ? jSONObject.getString("connections") : "";
                            ChatroomService.this.textView_totalUserCount.setText(2131689659);
                            TextView textView = ChatroomService.this.textView_totalUserCount;
                            textView.append(string + "");
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cr() {
        if (this.jW != null) {
            String trim = this.editText_input.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                this.tG = false;
                D(false);
                return;
            }
            this.editText_input.setText("");
            Gson gson = new Gson();
            String str = null;
            if (this.tH != null) {
                str = this.tH.getUserId();
            }
            ChatMessageObject chatMessageObject = new ChatMessageObject(this.jW.getUserId(), "", this.jW.getLevel(), this.jW.getEmail(), g.b(this.jW.getAvatar()), this.jW.getName(), this.jW.getTitle(), this.jW.getGender(), "android S", this.jW.getActivationDate(), this.at, this.lt, this.reply, trim, "", "", str, 0, this.jW.isVerified(), this.jW.getCharacter(), this.jW.getCharactersStringArray(), null, null);
            this.jZ.b("send_message", gson.toJson(chatMessageObject, ChatMessageObject.class));
            a(chatMessageObject.user_id, "", this.jW.getLevel(), chatMessageObject.getEmail(), chatMessageObject.avatar, chatMessageObject.getName(), chatMessageObject.getTitle(), chatMessageObject.getGender(), chatMessageObject.getPlatform(), chatMessageObject.getActivationDate(), chatMessageObject.getAt(), chatMessageObject.getReplyName(), chatMessageObject.getReply(), chatMessageObject.getMessage(), chatMessageObject.getBlockUserId(), true, chatMessageObject.isVerified(), chatMessageObject.getCharacter(), null, null);
            this.tG = false;
            D(false);
            cz();
            cA();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runOnUiThread(Runnable runnable) {
        this.handler.post(runnable);
    }

    @Override // com.picacomic.fregata.a.a
    public void D(int i) {
    }

    public void D(boolean z) {
        if (z) {
            this.tE.flags = 0;
            this.tC.updateViewLayout(this.tD, this.tE);
            this.button_messageMode.setText(2131689671);
            this.relativeLayout_input.setVisibility(0);
            return;
        }
        this.tE.flags = 8;
        this.tC.updateViewLayout(this.tD, this.tE);
        this.button_messageMode.setText(2131689672);
        this.relativeLayout_input.setVisibility(8);
    }

    @Override // com.picacomic.fregata.a.a
    public void E(int i) {
    }

    @Override // com.picacomic.fregata.a.a
    public void F(int i) {
    }

    @Override // com.picacomic.fregata.a.a
    public void G(int i) {
        if (this.at == null || (this.at != null && this.at.equalsIgnoreCase(""))) {
            this.textView_atList.setText("");
            this.at = "";
        }
        this.at += "嗶咔_" + ((ChatMessageObject) this.arrayList.get(i)).getName();
        if (this.lu == null || (this.lu != null && this.lu.equalsIgnoreCase(""))) {
            this.lu = "";
        }
        this.lu += "," + ((ChatMessageObject) this.arrayList.get(i)).getUserId();
        this.textView_atList.append(" @" + ((ChatMessageObject) this.arrayList.get(i)).getName());
        this.textView_atList.setVisibility(0);
    }

    @Override // com.picacomic.fregata.a.a
    public void H(int i) {
        if (((ChatMessageObject) this.arrayList.get(i)).getMessage() == null || ((ChatMessageObject) this.arrayList.get(i)).getName() == null) {
            return;
        }
        this.reply = ((ChatMessageObject) this.arrayList.get(i)).getMessage();
        this.lt = ((ChatMessageObject) this.arrayList.get(i)).getName();
        TextView textView = this.textView_reply;
        textView.setText(this.lt + ": " + this.reply);
        this.textView_reply.setVisibility(0);
    }

    public void N(String str) {
        if (this.textView_toastMsg != null) {
            TextView textView = this.textView_toastMsg;
            textView.setText(str + "");
        }
        cn();
        this.lh = new CountDownTimer(5000L, 500L) { // from class: com.picacomic.fregata.services.ChatroomService.4
            @Override // android.os.CountDownTimer
            public void onFinish() {
                ChatroomService.this.cn();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (ChatroomService.this.textView_toastMsg != null) {
                    ChatroomService.this.textView_toastMsg.setVisibility(0);
                }
            }
        };
        this.lh.start();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16, types: [java.util.ArrayList<com.picacomic.fregata.objects.ChatBaseObject>] */
    public void a(String str, String str2, int i, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, boolean z, boolean z2, String str15, String str16, String[] strArr) {
        StringBuilder sb;
        Context context;
        if (str10 != null && str10.contains(this.jW.getName())) {
            Context applicationContext = getApplicationContext();
            Toast.makeText(applicationContext, str5 + " " + getString(2131689656), 0).show();
            sb = applicationContext;
        } else if (str11 != null && str11.equals(this.jW.getName())) {
            Context applicationContext2 = getApplicationContext();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str5);
            sb2.append(" ");
            sb2.append(getString(2131689667));
            Toast.makeText(applicationContext2, sb2.toString(), 0).show();
            sb = sb2;
        }
        ?? r0 = this.arrayList;
        synchronized (r0) {
            try {
                if (z) {
                    try {
                        ChatMessageObject chatMessageObject = new ChatMessageObject(str, str2, i, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, "", "", str14, 3, z2, str15, null, str16, strArr);
                        this.arrayList.add(0, chatMessageObject);
                        String str17 = TAG;
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Message Data = ");
                        sb3.append(chatMessageObject.toString());
                        f.D(str17, sb3.toString());
                    } catch (Throwable th) {
                        th = th;
                        context = r0;
                        throw th;
                    }
                } else {
                    ChatMessageObject chatMessageObject2 = new ChatMessageObject(str, str2, i, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, "", "", str14, 0, z2, str15, null, str16, strArr);
                    this.arrayList.add(0, chatMessageObject2);
                    String str18 = TAG;
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Message Data = ");
                    sb4.append(chatMessageObject2.toString());
                    f.D(str18, sb4.toString());
                }
                this.le.notifyItemInserted(0);
            } catch (Throwable th2) {
                th = th2;
                context = sb;
            }
        }
        cu();
        ct();
        if (this.jQ.findFirstCompletelyVisibleItemPosition() == 0) {
            this.jQ.scrollToPosition(0);
            return;
        }
        N(str5 + ": " + str13);
    }

    public void bF() {
        this.textView_atList.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.services.ChatroomService.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomService.this.cz();
            }
        });
        this.textView_reply.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.services.ChatroomService.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomService.this.cA();
            }
        });
        this.textView_toastMsg.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.services.ChatroomService.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomService.this.recyclerView_chat == null || ChatroomService.this.arrayList == null || ChatroomService.this.arrayList.size() <= 0) {
                    return;
                }
                ChatroomService.this.recyclerView_chat.scrollToPosition(0);
            }
        });
        this.button_close.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.services.ChatroomService.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomService.this.dP();
            }
        });
        this.button_send.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.services.ChatroomService.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomService.this.cr();
            }
        });
        this.button_messageMode.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.services.ChatroomService.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomService.this.tG) {
                    ChatroomService.this.tG = false;
                    ChatroomService.this.D(false);
                    return;
                }
                ChatroomService.this.tG = true;
                ChatroomService.this.D(true);
            }
        });
        this.button_move.setOnTouchListener(new View.OnTouchListener() { // from class: com.picacomic.fregata.services.ChatroomService.10
            int iS;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int rawY = (int) motionEvent.getRawY();
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.iS = rawY;
                    ChatroomService.this.iK = rawY - ChatroomService.this.tE.y;
                    return false;
                } else if (action != 2) {
                    return false;
                } else {
                    ChatroomService.this.tE.y = rawY - ChatroomService.this.iK;
                    ChatroomService.this.tC.updateViewLayout(ChatroomService.this.tD, ChatroomService.this.tE);
                    return false;
                }
            }
        });
        this.button_sizeController.setOnTouchListener(new View.OnTouchListener() { // from class: com.picacomic.fregata.services.ChatroomService.11
            int iS;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int rawY = (int) motionEvent.getRawY();
                int action = motionEvent.getAction();
                if (action == 0) {
                    this.iS = rawY;
                    ChatroomService.this.iK = ChatroomService.this.recyclerView_chat.getLayoutParams().height;
                    return false;
                } else if (action != 2) {
                    return false;
                } else {
                    int i = ChatroomService.this.iK - (this.iS - rawY);
                    int i2 = i;
                    if (i < 0) {
                        i2 = 0;
                    }
                    ChatroomService.this.recyclerView_chat.getLayoutParams().height = i2;
                    ChatroomService.this.tC.updateViewLayout(ChatroomService.this.tD, ChatroomService.this.tE);
                    return false;
                }
            }
        });
    }

    public void bH() {
        this.tC.addView(this.tD, this.tE);
        D(false);
    }

    public void cA() {
        this.textView_reply.setText("");
        this.reply = "";
        this.lt = "";
        this.textView_reply.setVisibility(8);
    }

    public void cn() {
        if (this.lh != null) {
            this.lh.cancel();
            this.lh = null;
        }
        if (this.textView_toastMsg != null) {
            this.textView_toastMsg.setVisibility(8);
        }
    }

    public void ct() {
        if (this.arrayList.size() > 50) {
            int size = this.arrayList.size() - 50;
            for (int i = 0; i < size; i++) {
                this.arrayList.remove(50);
            }
            this.le.notifyItemRangeRemoved(50, size);
        }
    }

    @Deprecated
    public void cu() {
    }

    public void cz() {
        this.textView_atList.setText("");
        this.at = "";
        this.lu = "";
        this.textView_atList.setVisibility(8);
    }

    public void dP() {
        this.tD.setVisibility(8);
        stopForeground(true);
        stopSelf();
        if (this.jZ != null) {
            this.jZ.eO();
            this.jZ.c("broadcast_message", this.lS);
            this.jZ.c("new_connection", this.lP);
            this.jZ.c("connection_close", this.lQ);
        }
        if (this.lh != null) {
            this.lh.cancel();
            this.lh = null;
        }
        if (this.handler != null) {
            this.handler = null;
        }
    }

    public void init() {
        this.tD = this.jc.inflate(2131427524, (ViewGroup) null);
        ButterKnife.bind(this, this.tD);
        this.handler = new Handler();
        this.tG = false;
        this.jZ.a("broadcast_message", this.lS);
        this.jZ.a("new_connection", this.lP);
        this.jZ.a("connection_close", this.lQ);
        this.jZ.eJ();
        Gson gson = new Gson();
        String B = e.B(this);
        if (B != null) {
            this.jW = (UserProfileObject) gson.fromJson(B, (Class<Object>) UserProfileObject.class);
            if (this.jW == null) {
                dP();
            }
        } else {
            dP();
        }
        this.arrayList = new ArrayList<>();
        this.jQ = new LinearLayoutManager(this, 1, true);
        this.le = new ChatroomRecyclerViewAdapter(this, this.arrayList, this);
        this.recyclerView_chat.setLayoutManager(this.jQ);
        this.recyclerView_chat.setAdapter(this.le);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        g.aw(this);
        this.jc = (LayoutInflater) getSystemService("layout_inflater");
        this.tC = (WindowManager) getSystemService("window");
        this.screenWidth = this.tC.getDefaultDisplay().getWidth();
        this.tE = new WindowManager.LayoutParams(-1, -2, 2002, 8, -3);
        this.tE.gravity = 51;
        this.tE.x = 0;
        this.tE.y = 100;
        init();
        bF();
        bH();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null || intent.getAction() == null) {
            String str = intent == null ? "intent" : "action";
            String str2 = TAG;
            Log.e(str2, str + " was null, flags=" + i + " bits=" + Integer.toBinaryString(i));
            return 1;
        } else if (intent.getAction().equals("com.picacomic.fregata.interfaces.foregroundservice.action.startforeground")) {
            Log.i(TAG, "Received Start Foreground Intent ");
            Intent intent2 = new Intent(this, LoginActivity.class);
            intent2.setAction("com.picacomic.fregata.interfaces.foregroundservice.action.main");
            intent2.setFlags(268468224);
            PendingIntent activity = PendingIntent.getActivity(this, 0, intent2, 0);
            Intent intent3 = new Intent(this, ChatroomService.class);
            intent3.setAction("com.picacomic.fregata.interfaces.foregroundservice.action.stopforeground");
            PendingIntent service = PendingIntent.getService(this, 0, intent3, 0);
            Intent intent4 = new Intent(this, ChatroomService.class);
            intent4.setAction("com.picacomic.fregata.interfaces.foregroundservice.action.hideforeground");
            startForeground(4301, new NotificationCompat.Builder(this).setContentTitle(getString(2131689971)).setTicker(getString(2131689971)).setContentText(getString(2131689688)).setSmallIcon(2131231009).setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), 2131231009), 128, 128, false)).setContentIntent(activity).setPriority(2).addAction(17301609, getResources().getString(2131689803), PendingIntent.getService(this, 0, intent4, 0)).addAction(17301610, getResources().getString(2131689873), service).build());
            return 1;
        } else if (intent.getAction().equals("com.picacomic.fregata.interfaces.foregroundservice.action.stopforeground")) {
            Log.i(TAG, "Received Stop Foreground Intent");
            dP();
            return 1;
        } else if (intent.getAction().equals("com.picacomic.fregata.interfaces.foregroundservice.action.hideforeground")) {
            Log.i(TAG, "Received Hide Foreground Intent");
            if (this.tD.getVisibility() == 0) {
                this.tD.setVisibility(8);
                return 1;
            }
            this.tD.setVisibility(0);
            return 1;
        } else {
            return 1;
        }
    }
}
