package com.picacomic.fregata.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picacomic.fregata.a.a;
import com.picacomic.fregata.activities.BaseActivity;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.adapters.ChatroomRecyclerViewAdapter;
import com.picacomic.fregata.adapters.b;
import com.picacomic.fregata.b.c;
import com.picacomic.fregata.objects.AtMeObject;
import com.picacomic.fregata.objects.ChatBaseObject;
import com.picacomic.fregata.objects.ChatMessageObject;
import com.picacomic.fregata.objects.ChatSystemObject;
import com.picacomic.fregata.objects.ChatroomToObject;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.objects.chatroomObjects.ChangeTitleAction;
import com.picacomic.fregata.objects.chatroomObjects.ChatroomSystemAction;
import com.picacomic.fregata.objects.chatroomObjects.ImageAction;
import com.picacomic.fregata.objects.chatroomObjects.MuteAction;
import com.picacomic.fregata.objects.chatroomObjects.SetAvatarAction;
import com.picacomic.fregata.objects.chatroomObjects.TimeAction;
import com.picacomic.fregata.objects.requests.UpdateUserTitleBody;
import com.picacomic.fregata.objects.responses.ChatroomBlacklistObject;
import com.picacomic.fregata.objects.responses.GeneralResponse;
import com.picacomic.fregata.objects.responses.RegisterResponse;
import com.picacomic.fregata.objects.responses.UserProfileResponse;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.f;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import com.squareup.picasso.Picasso;
import io.socket.b.a;
import io.socket.client.b;
import io.socket.client.d;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChatroomFragment.class */
public class ChatroomFragment extends BaseImagePickFragment implements TextToSpeech.OnInitListener, a {
    public static final String TAG = "ChatroomFragment";
    ArrayList<ChatBaseObject> arrayList;
    @BindView(2131296312)
    Button button_channelPrivate;
    @BindView(2131296313)
    Button button_channelPublic;
    @BindView(2131296314)
    Button button_controlAI;
    @BindView(2131296315)
    Button button_controlChangeTitle;
    @BindView(2131296316)
    Button button_controlHideAvatar;
    @BindView(2131296317)
    Button button_controlHidePanelButtons;
    @BindView(2131296318)
    Button button_controlImage;
    @BindView(2131296319)
    Button button_controlMute;
    @BindView(2131296320)
    Button button_controlTime;
    @BindView(2131296321)
    Button button_saveImage;
    @BindView(2131296322)
    Button button_sendMessage;
    @BindView(2131296323)
    Button button_viewProfile;
    CountDownTimer countDownTimer;
    @BindView(2131296451)
    EditText editText_textbox;
    @BindView(2131296500)
    FloatingActionButton fab_command;
    @BindView(2131296507)
    FrameLayout frameLayout_channelIndicator;
    @BindView(2131296508)
    FrameLayout frameLayout_chatOnlineCountBackground;
    @BindView(2131296509)
    FrameLayout frameLayout_chatOnlineCountContainer;
    @BindView(2131296526)
    FrameLayout frameLayout_container;
    @BindView(2131296528)
    FrameLayout frameLayout_nightMode;
    @BindView(2131296532)
    GridView gridView_emojiList;
    Gson gson;
    RelativeLayout.LayoutParams iI;
    private int iJ;
    private int iK;
    boolean iN;
    @BindView(2131296543)
    ImageButton imageButton_closePopup;
    @BindView(2131296544)
    ImageButton imageButton_hideChannel;
    @BindView(2131296542)
    ImageButton imageButton_sendAudio;
    @BindView(2131296545)
    ImageButton imageButton_sendEmoji;
    @BindView(2131296546)
    ImageButton imageButton_sendImage;
    @BindView(2131296547)
    ImageButton imageButton_setting;
    @BindView(2131296574)
    ImageView imageView_largeImage;
    LinearLayoutManager jQ;
    UserProfileObject jW;
    Call<GeneralResponse<UserProfileResponse>> jX;
    private d jZ;
    CountDownTimer kY;
    boolean lA;
    int lB;
    int lC;
    ChatroomToObject lD;
    ChatroomGameFragment lE;
    ArrayList<AtMeObject> lF;
    ArrayList<ChatroomBlacklistObject> lG;
    TextToSpeech lH;
    boolean lI;
    boolean lJ;
    boolean lK;
    Timer lX;
    long lb;
    long lc;
    CountDownTimer ld;
    ChatroomRecyclerViewAdapter le;
    b lf;
    Call<RegisterResponse> lg;
    CountDownTimer lh;
    CountDownTimer li;
    @BindView(2131296647)
    LinearLayout linearLayout_chatChannel;
    @BindView(2131296648)
    LinearLayout linearLayout_controlPanel;
    @BindView(2131296649)
    LinearLayout linearLayout_controlPanelButtons;
    CountDownTimer lj;
    CountDownTimer lk;
    Bitmap ll;
    String lm;
    String ln;
    String lo;
    int lr;
    String lw;
    boolean lx;
    boolean ly;
    boolean lz;
    @BindView(2131296755)
    RecyclerView recyclerView_chat;
    @BindView(2131296772)
    RelativeLayout relativeLayout_popup;
    @BindView(2131296849)
    TextView textView_atList;
    @BindView(2131296850)
    TextView textView_audioPlayingTimer;
    @BindView(2131296851)
    TextView textView_chatOnlineCount;
    @BindView(2131296852)
    TextView textView_chatOnlineCountTitle;
    @BindView(2131296853)
    TextView textView_reply;
    @BindView(2131296854)
    TextView textView_toastAtMe;
    @BindView(2131296855)
    TextView textView_toastMsg;
    @BindView(2131296856)
    TextView textView_totalUserCount;
    @BindView(2131297037)
    Toolbar toolbar;
    public int kW = 100;
    @Deprecated
    public int kX = 30;
    long iL = 0;
    String kZ = "https://chat.picacomic.com";
    private MediaRecorder la = null;
    private MediaPlayer ii = null;
    String[] lp = null;
    String lq = null;
    String at = "";
    String lt = "";
    String reply = "";
    String lu = "";
    String lv = null;
    private a.InterfaceC0017a lL = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.28
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.28.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ChatroomFragment.this.gson == null) {
                        ChatroomFragment.this.gson = new Gson();
                    }
                    JSONObject jSONObject = (JSONObject) objArr[0];
                    try {
                        String string = jSONObject.has("user_id") ? jSONObject.getString("user_id") : "";
                        String string2 = jSONObject.has("title") ? jSONObject.getString("title") : "";
                        if (ChatroomFragment.this.jW == null || !ChatroomFragment.this.jW.getUserId().equalsIgnoreCase(string)) {
                            return;
                        }
                        ChatroomFragment.this.jW.setTitle(string2);
                        String str = ChatroomFragment.TAG;
                        f.D(str, "Testing HERE ID= " + string + "  title = " + string2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private a.InterfaceC0017a lM = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.29
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.29.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ChatroomFragment.this.gson == null) {
                        ChatroomFragment.this.gson = new Gson();
                    }
                    int i = 0;
                    JSONObject jSONObject = (JSONObject) objArr[0];
                    try {
                        if (jSONObject.has("avatar")) {
                            jSONObject.getString("avatar");
                        }
                        String string = jSONObject.has("name") ? jSONObject.getString("name") : null;
                        String string2 = jSONObject.has("character") ? jSONObject.getString("character") : null;
                        String string3 = jSONObject.has("title") ? jSONObject.getString("title") : null;
                        int i2 = jSONObject.has("level") ? jSONObject.getInt("level") : 0;
                        String string4 = jSONObject.has("event_icon") ? jSONObject.getString("event_icon") : null;
                        String[] strArr = null;
                        if (jSONObject.has("event_colors")) {
                            JSONArray jSONArray = jSONObject.getJSONArray("event_colors");
                            strArr = null;
                            if (jSONArray != null) {
                                strArr = null;
                                if (jSONArray.length() > 0) {
                                    String[] strArr2 = new String[jSONArray.length()];
                                    while (true) {
                                        strArr = strArr2;
                                        if (i >= jSONArray.length()) {
                                            break;
                                        }
                                        strArr2[i] = jSONArray.getString(i);
                                        i++;
                                    }
                                }
                            }
                        }
                        if (ChatroomFragment.this.jW != null) {
                            ChatroomFragment.this.lm = string;
                            ChatroomFragment.this.lo = string2;
                            ChatroomFragment.this.ln = string3;
                            ChatroomFragment.this.lr = i2;
                            ChatroomFragment.this.lq = string4;
                            ChatroomFragment.this.lp = strArr;
                            if (string != null) {
                                ChatroomFragment.this.jW.setName(string);
                            }
                            if (string2 != null) {
                                ChatroomFragment.this.jW.setCharacter(string2);
                            }
                            if (string3 != null) {
                                ChatroomFragment.this.jW.setTitle(string3);
                            }
                            if (i2 > 0) {
                                ChatroomFragment.this.jW.setLevel(i2);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private a.InterfaceC0017a kd = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.30
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.30.1
                @Override // java.lang.Runnable
                public void run() {
                    f.D(ChatroomFragment.TAG, "Testing INIT");
                    ChatroomFragment.this.jZ.b("init", ChatroomFragment.this.gson.toJson(ChatroomFragment.this.jW, UserProfileObject.class));
                }
            });
        }
    };
    private a.InterfaceC0017a lN = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.31
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.31.1
                @Override // java.lang.Runnable
                public void run() {
                    f.D(ChatroomFragment.TAG, "Testing KICK");
                    if (ChatroomFragment.this.gson == null) {
                        ChatroomFragment.this.gson = new Gson();
                    }
                    JSONObject jSONObject = (JSONObject) objArr[0];
                    try {
                        String string = jSONObject.has("message") ? jSONObject.getString("message") : "";
                        Context context = ChatroomFragment.this.getContext();
                        Toast.makeText(context, string + "", 1).show();
                        ChatroomFragment.this.onDetach();
                        ChatroomFragment.this.getFragmentManager().popBackStack();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private a.InterfaceC0017a lO = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.32
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.32.1
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject jSONObject = (JSONObject) objArr[0];
                    String str = ChatroomFragment.TAG;
                    f.D(str, "ReceiveNotification: " + jSONObject.toString());
                    try {
                        ChatroomFragment.this.Q(jSONObject.has("message") ? jSONObject.getString("message") : null);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private a.InterfaceC0017a lP = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.33
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.33.1
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject jSONObject = (JSONObject) objArr[0];
                    try {
                        ChatroomFragment.this.R(jSONObject.has("connections") ? jSONObject.getString("connections") : "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private a.InterfaceC0017a lQ = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.35
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.35.1
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject jSONObject = (JSONObject) objArr[0];
                    try {
                        ChatroomFragment.this.S(jSONObject.has("connections") ? jSONObject.getString("connections") : "");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private a.InterfaceC0017a lR = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.36
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.36.1
                @Override // java.lang.Runnable
                public void run() {
                    JSONObject jSONObject = (JSONObject) objArr[0];
                    try {
                        if (jSONObject.has("character")) {
                            ChatroomFragment.this.lv = jSONObject.getString("character");
                        }
                        if (ChatroomFragment.this.jW == null || ChatroomFragment.this.lv == null || ChatroomFragment.this.lv.equalsIgnoreCase("")) {
                            return;
                        }
                        ChatroomFragment.this.jW.setCharacter(ChatroomFragment.this.lv);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private a.InterfaceC0017a lS = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.37
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.37.1
                @Override // java.lang.Runnable
                public void run() {
                    ChatMessageObject a = ChatroomFragment.this.a((JSONObject) objArr[0], 0);
                    if (a == null) {
                        return;
                    }
                    ChatroomFragment.this.a(a);
                }
            });
        }
    };
    private a.InterfaceC0017a lT = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.38
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.38.1
                @Override // java.lang.Runnable
                public void run() {
                    ChatMessageObject a = ChatroomFragment.this.a((JSONObject) objArr[0], 11);
                    if (a == null) {
                        return;
                    }
                    ChatroomFragment.this.b(a);
                }
            });
        }
    };
    private a.InterfaceC0017a lU = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.39
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.39.1
                @Override // java.lang.Runnable
                public void run() {
                    String[] strArr;
                    JSONArray jSONArray;
                    JSONObject jSONObject = (JSONObject) objArr[0];
                    ChatroomToObject chatroomToObject = new ChatroomToObject("", "", "");
                    try {
                        String string = jSONObject.has("id") ? jSONObject.getString("id") : "";
                        if (jSONObject.has("userId")) {
                            string = jSONObject.getString("userId");
                        }
                        String string2 = jSONObject.has("user_id") ? jSONObject.getString("user_id") : string;
                        String string3 = jSONObject.has("unique_id") ? jSONObject.getString("unique_id") : "";
                        int i = jSONObject.has("level") ? jSONObject.getInt("level") : 0;
                        String string4 = jSONObject.has("avatar") ? jSONObject.getString("avatar") : "";
                        String string5 = jSONObject.getString("name");
                        String string6 = jSONObject.has("title") ? jSONObject.getString("title") : "";
                        String string7 = jSONObject.has("message") ? jSONObject.getString("message") : "";
                        boolean z = jSONObject.has("verified") ? jSONObject.getBoolean("verified") : false;
                        String string8 = jSONObject.has("gender") ? jSONObject.getString("gender") : "";
                        String string9 = jSONObject.has("platform") ? jSONObject.getString("platform") : "";
                        String string10 = jSONObject.has("activation_date") ? jSONObject.getString("activation_date") : null;
                        if (jSONObject.has("activationDate")) {
                            string10 = jSONObject.getString("activationDate");
                        }
                        String string11 = jSONObject.has("at") ? jSONObject.getString("at") : "";
                        String string12 = jSONObject.has("reply_name") ? jSONObject.getString("reply_name") : "";
                        String string13 = jSONObject.has("reply") ? jSONObject.getString("reply") : "";
                        String string14 = jSONObject.has("email") ? jSONObject.getString("email") : "";
                        String string15 = jSONObject.has("block_user_id") ? jSONObject.getString("block_user_id") : "";
                        if (jSONObject.has("to")) {
                            JSONObject jSONObject2 = jSONObject.getJSONObject("to");
                            if (jSONObject2.has("name")) {
                                chatroomToObject.setName(jSONObject2.getString("name"));
                            }
                            if (jSONObject2.has("unique_id")) {
                                chatroomToObject.setName(jSONObject2.getString("unique_id"));
                            }
                            if (jSONObject2.has("user_id")) {
                                chatroomToObject.setName(jSONObject2.getString("user_id"));
                            }
                        }
                        String string16 = jSONObject.has("event_icon") ? jSONObject.getString("event_icon") : null;
                        if (!jSONObject.has("event_colors") || (jSONArray = jSONObject.getJSONArray("event_colors")) == null || jSONArray.length() <= 0) {
                            strArr = null;
                        } else {
                            strArr = new String[jSONArray.length()];
                            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                                strArr[i2] = jSONArray.getString(i2);
                            }
                        }
                        ChatroomFragment.this.a(string2, string3, i, string14, string4, string5, string6, string8, string9, string10, string11, string12, string13, string7, string15, false, z, jSONObject.has("character") ? jSONObject.getString("character") : null, chatroomToObject, string16, strArr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private a.InterfaceC0017a lV = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.40
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.40.1
                @Override // java.lang.Runnable
                public void run() {
                    ChatMessageObject a = ChatroomFragment.this.a((JSONObject) objArr[0], 1);
                    if (a == null) {
                        return;
                    }
                    ChatroomFragment.this.c(a);
                }
            });
        }
    };
    private a.InterfaceC0017a lW = new a.InterfaceC0017a() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.41
        @Override // io.socket.b.a.InterfaceC0017a
        public void a(final Object... objArr) {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.41.1
                @Override // java.lang.Runnable
                public void run() {
                    ChatMessageObject a = ChatroomFragment.this.a((JSONObject) objArr[0], 2);
                    if (a == null) {
                        return;
                    }
                    ChatroomFragment.this.d(a);
                }
            });
        }
    };
    private final String mFileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/audiorecordtest.3gp";
    int ls = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.picacomic.fregata.fragments.ChatroomFragment$46  reason: invalid class name */
    /* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/ChatroomFragment$46.class */
    public class AnonymousClass46 extends TimerTask {
        AnonymousClass46() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            ChatroomFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.46.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ChatroomFragment.this.ii == null || !ChatroomFragment.this.ii.isPlaying()) {
                        ChatroomFragment.this.lX.cancel();
                        ChatroomFragment.this.lX.purge();
                    } else if (ChatroomFragment.this.textView_audioPlayingTimer != null) {
                        ChatroomFragment.this.textView_audioPlayingTimer.post(new Runnable() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.46.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (ChatroomFragment.this.textView_audioPlayingTimer != null) {
                                    TextView textView = ChatroomFragment.this.textView_audioPlayingTimer;
                                    textView.setText("正在播放錄音(" + String.format("%.1f", Float.valueOf(ChatroomFragment.this.ii.getDuration() / 1000.0f)) + "s)：" + String.format("%.1f", Float.valueOf(ChatroomFragment.this.ii.getCurrentPosition() / 1000.0f)) + "s");
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    public static ChatroomFragment M(String str) {
        ChatroomFragment chatroomFragment = new ChatroomFragment();
        Bundle bundle = new Bundle();
        bundle.putString("KEY_CHATROOM_URL", str);
        chatroomFragment.setArguments(bundle);
        return chatroomFragment;
    }

    private void O(String str) {
        String str2 = "";
        if (this.jW != null) {
            str2 = "";
            if (this.jW.getName() != null) {
                str2 = this.jW.getName().trim();
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (this.jW == null) {
            AlertDialogCenter.userNotActivatedCannotCommentOrChat(getActivity());
            return;
        }
        this.editText_textbox.setText("");
        if (this.gson == null) {
            this.gson = new Gson();
        }
        ChatMessageObject chatMessageObject = new ChatMessageObject(this.jW.getUserId(), "", this.jW.getLevel(), this.jW.getEmail(), g.b(this.jW.getAvatar()), this.jW.getName(), this.jW.getTitle(), this.jW.getGender(), "android", this.jW.getActivationDate(), this.at, this.lt, this.reply, "", str, "", this.lu, 4, this.jW.isVerified(), this.jW.getCharacter(), this.jW.getCharactersStringArray(), this.lq, this.lp);
        this.jZ.b("send_image", this.gson.toJson(chatMessageObject, ChatMessageObject.class));
        c(chatMessageObject);
        cz();
        cA();
    }

    private void P(String str) {
        String str2 = "";
        if (this.jW != null) {
            str2 = "";
            if (this.jW.getName() != null) {
                str2 = this.jW.getName().trim();
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (this.jW == null) {
            AlertDialogCenter.userNotActivatedCannotCommentOrChat(getActivity());
            return;
        }
        this.editText_textbox.setText("");
        if (this.gson == null) {
            this.gson = new Gson();
        }
        ChatMessageObject chatMessageObject = new ChatMessageObject(this.jW.getUserId(), "", this.jW.getLevel(), this.jW.getEmail(), g.b(this.jW.getAvatar()), this.jW.getName(), this.jW.getTitle(), this.jW.getGender(), "android", this.jW.getActivationDate(), this.at, this.lt, this.reply, "", "", str, this.lu, 5, this.jW.isVerified(), this.jW.getCharacter(), this.jW.getCharactersStringArray(), this.lq, this.lp);
        this.jZ.b("send_audio", this.gson.toJson(chatMessageObject, ChatMessageObject.class));
        d(chatMessageObject);
        cz();
        cA();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ChatroomSystemAction chatroomSystemAction, Type type) {
        if (this.jW == null) {
            AlertDialogCenter.userNotActivatedCannotCommentOrChat(getActivity());
            return;
        }
        if (this.gson == null) {
            this.gson = new Gson();
        }
        this.jZ.b("system_action", this.gson.toJson(chatroomSystemAction, type));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cr() {
        String trim = this.editText_textbox.getText().toString().trim();
        if (trim.equalsIgnoreCase("@指令")) {
            this.lA = true;
        }
        String str = "";
        if (this.jW != null) {
            str = "";
            if (this.jW.getName() != null) {
                str = this.jW.getName().trim();
            }
        }
        if (this.ku != null) {
            this.ku.toString();
        }
        if (TextUtils.isEmpty(trim) || TextUtils.isEmpty(str)) {
            return;
        }
        if (this.jW == null) {
            AlertDialogCenter.userNotActivatedCannotCommentOrChat(getActivity());
            return;
        }
        String str2 = trim;
        if (!this.lz) {
            if (this.at == null || this.at.length() <= 0) {
                Toast.makeText(getContext(), 2131689989, 0).show();
                return;
            }
            str2 = "@private " + trim;
        }
        if (this.lI) {
            this.editText_textbox.setText("");
        }
        if (this.gson == null) {
            this.gson = new Gson();
        }
        ChatMessageObject chatMessageObject = new ChatMessageObject(this.jW.getUserId(), "", this.jW.getLevel(), this.jW.getEmail(), g.b(this.jW.getAvatar()), this.jW.getName(), this.jW.getTitle(), this.jW.getGender(), "android", this.jW.getActivationDate(), this.at, this.lt, this.reply, str2, "", "", this.lu, 3, this.jW.isVerified(), this.jW.getCharacter(), this.jW.getCharactersStringArray(), this.lq, this.lp);
        this.jZ.b("send_message", this.gson.toJson(chatMessageObject, ChatMessageObject.class));
        a(chatMessageObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cs() {
        String trim = this.editText_textbox.getText().toString().trim();
        String str = "";
        if (this.jW != null) {
            str = "";
            if (this.jW.getName() != null) {
                str = this.jW.getName().trim();
            }
        }
        if (this.ku != null) {
            this.ku.toString();
        }
        if (TextUtils.isEmpty(trim) || TextUtils.isEmpty(str)) {
            return;
        }
        if (this.jW == null) {
            AlertDialogCenter.userNotActivatedCannotCommentOrChat(getActivity());
            return;
        }
        this.editText_textbox.setText("");
        if (this.gson == null) {
            this.gson = new Gson();
        }
        ChatMessageObject chatMessageObject = new ChatMessageObject(this.jW.getUserId(), "", this.jW.getLevel(), this.jW.getEmail(), g.b(this.jW.getAvatar()), this.jW.getName(), this.jW.getTitle(), this.jW.getGender(), "android", this.jW.getActivationDate(), this.at, this.lt, this.reply, trim, "", "", this.lu, 3, this.jW.isVerified(), this.jW.getCharacter(), this.jW.getCharactersStringArray(), this.lq, this.lp);
        if (this.lD == null) {
            return;
        }
        chatMessageObject.setTo(this.lD);
        this.jZ.b("send_private_message", this.gson.toJson(chatMessageObject, ChatMessageObject.class));
        a(chatMessageObject.user_id, "", this.jW.getLevel(), chatMessageObject.getEmail(), chatMessageObject.avatar, chatMessageObject.getName(), chatMessageObject.getTitle(), chatMessageObject.getGender(), chatMessageObject.getPlatform(), chatMessageObject.getActivationDate(), chatMessageObject.getAt(), chatMessageObject.getReplyName(), chatMessageObject.getReply(), chatMessageObject.getMessage(), chatMessageObject.getBlockUserId(), true, chatMessageObject.isVerified(), chatMessageObject.getCharacter(), chatMessageObject.getTo(), this.lq, this.lp);
    }

    private void cw() {
        this.ii = new MediaPlayer();
        try {
            this.ii.setDataSource(this.mFileName);
            this.ii.prepare();
            this.ii.start();
            this.ii.getDuration();
            String str = TAG;
            f.D(str, "DURATION = " + this.ii.getDuration());
            this.ii.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.44
                @Override // android.media.MediaPlayer.OnCompletionListener
                public void onCompletion(MediaPlayer mediaPlayer) {
                    ChatroomFragment.this.u(false);
                }
            });
            if (this.textView_audioPlayingTimer != null) {
                this.textView_audioPlayingTimer.setVisibility(0);
                this.lX = new Timer();
                this.lX.scheduleAtFixedRate(new AnonymousClass46(), 0L, 100L);
            }
        } catch (IOException e) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cx() {
        if (this.textView_audioPlayingTimer != null) {
            this.textView_audioPlayingTimer.setVisibility(8);
        }
        if (this.lk != null) {
            this.lk.cancel();
        }
        if (this.ii != null) {
            this.ii.release();
            this.ii = null;
        }
    }

    private void startRecording() {
        this.la = new MediaRecorder();
        this.la.setAudioSource(1);
        this.la.setOutputFormat(1);
        this.la.setOutputFile(this.mFileName);
        this.la.setAudioEncoder(3);
        try {
            this.la.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.la.start();
    }

    private void stopRecording() {
        try {
            this.la.stop();
            this.la.release();
            this.la = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t(boolean z) {
        if (z) {
            cy();
            startRecording();
            return;
        }
        stopRecording();
        String aB = g.aB(this.mFileName);
        if (aB != null && this.lc != 0 && this.lb != 0 && this.lc - this.lb >= 1000) {
            P(aB);
            MediaPlayer.create(getActivity(), Uri.parse(this.mFileName)).getDuration();
        }
        this.lb = 0L;
        this.lc = 0L;
        if (this.ld != null) {
            this.ld.cancel();
            this.ld = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u(boolean z) {
        if (!z) {
            cx();
            return;
        }
        if (this.ii != null) {
            cx();
        }
        cw();
    }

    @Override // com.picacomic.fregata.a.a
    public void D(int i) {
        g.G(((ChatMessageObject) this.arrayList.get(i)).getAudio(), this.mFileName);
        u(true);
    }

    @Override // com.picacomic.fregata.a.a
    public void E(int i) {
        if (((ChatMessageObject) this.arrayList.get(i)).getImage() == null || ((ChatMessageObject) this.arrayList.get(i)).getImage().equalsIgnoreCase("")) {
            return;
        }
        if (((ChatMessageObject) this.arrayList.get(i)).getType() == 11) {
            if (((ChatMessageObject) this.arrayList.get(i)).getAt() != null) {
                g.A(getContext(), ((ChatMessageObject) this.arrayList.get(i)).getAt());
                return;
            }
            return;
        }
        byte[] decode = Base64.decode(((ChatMessageObject) this.arrayList.get(i)).getImage().substring(((ChatMessageObject) this.arrayList.get(i)).getImage().indexOf(44)), 0);
        this.ll = BitmapFactory.decodeByteArray(decode, 0, decode.length);
        this.imageView_largeImage.setImageBitmap(this.ll);
        this.relativeLayout_popup.setVisibility(0);
        if (this.ll == null) {
            this.button_saveImage.setVisibility(8);
        } else {
            this.button_saveImage.setVisibility(0);
        }
        this.button_viewProfile.setVisibility(8);
    }

    @Override // com.picacomic.fregata.a.a
    public void F(int i) {
        if (this.arrayList.size() <= i || this.arrayList.get(i) == null) {
            return;
        }
        if (((ChatMessageObject) this.arrayList.get(i)).type == 3 || ((ChatMessageObject) this.arrayList.get(i)).type == 5 || ((ChatMessageObject) this.arrayList.get(i)).type == 4) {
            this.kv = 1;
            cf();
        } else if (((ChatMessageObject) this.arrayList.get(i)).getUserId() != null) {
            this.relativeLayout_popup.setVisibility(0);
            this.button_viewProfile.setVisibility(0);
            this.lw = ((ChatMessageObject) this.arrayList.get(i)).getUserId();
            if (((ChatMessageObject) this.arrayList.get(i)).getAvatar() == null || ((ChatMessageObject) this.arrayList.get(i)).getAvatar().equalsIgnoreCase("")) {
                this.imageView_largeImage.setImageBitmap(null);
            } else {
                Picasso.with(getActivity()).load(((ChatMessageObject) this.arrayList.get(i)).getAvatar()).into(this.imageView_largeImage);
            }
        }
    }

    @Override // com.picacomic.fregata.a.a
    public void G(int i) {
        if (this.textView_atList == null || this.arrayList == null || this.arrayList.size() <= i) {
            return;
        }
        if (getActivity() != null && (getActivity() instanceof MainActivity) && ((MainActivity) getActivity()).iM) {
            ((MainActivity) getActivity()).i(((ChatMessageObject) this.arrayList.get(i)).getName(), ((ChatMessageObject) this.arrayList.get(i)).getUserId());
        } else if (cB()) {
            if (((ChatMessageObject) this.arrayList.get(i)).getEmail() == null || g.F(getContext(), ((ChatMessageObject) this.arrayList.get(i)).getEmail()) == 2) {
                return;
            }
            b(this.jW.getEmail(), ((ChatMessageObject) this.arrayList.get(i)).getName(), ((ChatMessageObject) this.arrayList.get(i)).getUserId(), this.jW.getName());
        } else if (cC()) {
            if (((ChatMessageObject) this.arrayList.get(i)).getEmail() != null) {
                c(this.jW.getEmail(), ((ChatMessageObject) this.arrayList.get(i)).getName(), ((ChatMessageObject) this.arrayList.get(i)).getUserId(), this.jW.getName());
            }
        } else if (cD()) {
            if (((ChatMessageObject) this.arrayList.get(i)).getEmail() != null) {
                e(this.jW.getEmail(), ((ChatMessageObject) this.arrayList.get(i)).getName(), ((ChatMessageObject) this.arrayList.get(i)).getUserId(), this.jW.getName());
            }
        } else {
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
    }

    @Override // com.picacomic.fregata.a.a
    public void H(int i) {
        if (this.textView_reply == null || this.arrayList.size() <= i) {
            return;
        }
        if (cB()) {
            if (((ChatMessageObject) this.arrayList.get(i)).getEmail() == null || g.F(getContext(), ((ChatMessageObject) this.arrayList.get(i)).getEmail()) == 2) {
                return;
            }
            b(this.jW.getEmail(), ((ChatMessageObject) this.arrayList.get(i)).getName(), ((ChatMessageObject) this.arrayList.get(i)).getUserId(), this.jW.getName());
        } else if (cC()) {
            if (((ChatMessageObject) this.arrayList.get(i)).getEmail() != null) {
                c(this.jW.getEmail(), ((ChatMessageObject) this.arrayList.get(i)).getName(), ((ChatMessageObject) this.arrayList.get(i)).getUserId(), this.jW.getName());
            }
        } else if (cD()) {
            if (((ChatMessageObject) this.arrayList.get(i)).getEmail() != null) {
                e(this.jW.getEmail(), ((ChatMessageObject) this.arrayList.get(i)).getName(), ((ChatMessageObject) this.arrayList.get(i)).getUserId(), this.jW.getName());
            }
        } else if (((ChatMessageObject) this.arrayList.get(i)).getType() == 11) {
            if (((ChatMessageObject) this.arrayList.get(i)).getAt() != null) {
                g.A(getContext(), ((ChatMessageObject) this.arrayList.get(i)).getAt());
            }
        } else if (((ChatMessageObject) this.arrayList.get(i)).getMessage() == null || ((ChatMessageObject) this.arrayList.get(i)).getName() == null) {
        } else {
            this.reply = ((ChatMessageObject) this.arrayList.get(i)).getMessage();
            this.lt = ((ChatMessageObject) this.arrayList.get(i)).getName();
            TextView textView = this.textView_reply;
            textView.setText(this.lt + ": " + this.reply);
            this.textView_reply.setVisibility(0);
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseImagePickFragment
    public void K(String str) {
        super.K(str);
        if (str != null && this.kv == 2) {
            O(g.f(g.b(getActivity(), Uri.parse(str))));
        } else if (getActivity() != null) {
            cd();
        }
    }

    public void N(String str) {
        if (this.textView_toastMsg != null) {
            TextView textView = this.textView_toastMsg;
            textView.setText(str + "");
        }
        cn();
        this.lh = new CountDownTimer(5000L, 500L) { // from class: com.picacomic.fregata.fragments.ChatroomFragment.25
            @Override // android.os.CountDownTimer
            public void onFinish() {
                ChatroomFragment.this.cn();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (ChatroomFragment.this.textView_toastMsg != null) {
                    ChatroomFragment.this.textView_toastMsg.setVisibility(0);
                }
            }
        };
        this.lh.start();
    }

    public void Q(String str) {
        if (str != null) {
            this.arrayList.add(0, new ChatSystemObject(str));
            this.le.notifyItemInserted(0);
            if (this.jQ.findFirstCompletelyVisibleItemPosition() == 0) {
                this.jQ.scrollToPosition(0);
            }
        }
    }

    public void R(String str) {
        try {
            TextView textView = this.textView_totalUserCount;
            textView.setText(str + "");
            this.frameLayout_chatOnlineCountBackground.setBackgroundResource(2131230872);
            this.frameLayout_chatOnlineCountContainer.setVisibility(0);
            this.textView_chatOnlineCountTitle.setText(2131689658);
            TextView textView2 = this.textView_chatOnlineCount;
            textView2.setText(str + "");
            cq();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void S(String str) {
        try {
            TextView textView = this.textView_totalUserCount;
            textView.setText(str + "");
            this.frameLayout_chatOnlineCountBackground.setBackgroundResource(2131230871);
            this.frameLayout_chatOnlineCountContainer.setVisibility(0);
            this.textView_chatOnlineCountTitle.setText(2131689657);
            TextView textView2 = this.textView_chatOnlineCount;
            textView2.setText(str + "");
            cq();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void T(final String str) {
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(2131689661).setSingleChoiceItems(2130903058, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.63
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ChatroomFragment.this.jW != null && ChatroomFragment.this.jW.getName() != null) {
                    switch (i) {
                        case 0:
                            ChatroomFragment.this.a(new TimeAction("time", str, true, ChatroomFragment.this.jW.getName()), TimeAction.class);
                            break;
                        case 1:
                            ChatroomFragment.this.a(new TimeAction("time", str, false, ChatroomFragment.this.jW.getName()), TimeAction.class);
                            break;
                    }
                }
                dialogInterface.dismiss();
            }
        }).show();
    }

    public void U(final String str) {
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(2131689660).setSingleChoiceItems(2130903058, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.64
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ChatroomFragment.this.jW != null && ChatroomFragment.this.jW.getName() != null) {
                    switch (i) {
                        case 0:
                            ChatroomFragment.this.a(new ImageAction("image", str, true, ChatroomFragment.this.jW.getName()), ImageAction.class);
                            break;
                        case 1:
                            ChatroomFragment.this.a(new ImageAction("image", str, false, ChatroomFragment.this.jW.getName()), ImageAction.class);
                            break;
                    }
                }
                dialogInterface.dismiss();
            }
        }).show();
    }

    public boolean V(String str) {
        if (str.equalsIgnoreCase("NIGHT ON")) {
            this.frameLayout_nightMode.setVisibility(0);
            return true;
        } else if (str.equalsIgnoreCase("NIGHT OFF")) {
            this.frameLayout_nightMode.setVisibility(8);
            return true;
        } else if (str.equalsIgnoreCase("TIME ON")) {
            this.le.n(true);
            Toast.makeText(getActivity(), "SHOW TIMESTAMP ON", 0).show();
            return true;
        } else if (str.equalsIgnoreCase("TIME OFF")) {
            this.le.n(false);
            return true;
        } else if (str.equalsIgnoreCase("FIX IMAGE SIZE ON")) {
            this.le.o(true);
            Toast.makeText(getActivity(), "FIX IMAGE SIZE ON", 0).show();
            return true;
        } else if (str.equalsIgnoreCase("FIX IMAGE SIZE OFF")) {
            this.le.o(false);
            Toast.makeText(getActivity(), "FIX IMAGE SIZE OFF", 0).show();
            return true;
        } else if (str.toUpperCase().startsWith("AUTO EARN PICA")) {
            return true;
        } else {
            if (str.toUpperCase().startsWith("MAXIMUM MESSAGE SIZE ")) {
                try {
                    this.kW = Integer.parseInt(str.toUpperCase().replace("MAXIMUM MESSAGE SIZE ", ""));
                    Toast.makeText(getActivity(), "SET MAX MESSAGE SIZE = " + this.kW, 0).show();
                    return true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return true;
                }
            } else if (!str.equalsIgnoreCase("@BLACKLIST")) {
                if (this.jW == null || !this.jW.getEmail().endsWith("@picacomic.com")) {
                    return false;
                }
                if (str.toUpperCase().startsWith("SET ADS INTERVAL ")) {
                    try {
                        this.kX = Integer.parseInt(str.toUpperCase().replace("SET ADS INTERVAL ", ""));
                        Toast.makeText(getActivity(), "SET ADS INTERVAL = " + this.kX, 0).show();
                        return true;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return true;
                    }
                } else if (str.equalsIgnoreCase("CONTROL PANEL")) {
                    if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
                        return true;
                    }
                    ((MainActivity) getActivity()).bY();
                    return true;
                } else if (str.equalsIgnoreCase("CLEAR TEXT ON")) {
                    this.lI = true;
                    Toast.makeText(getActivity(), "CLEAR TEXT ON", 0).show();
                    return true;
                } else if (str.equalsIgnoreCase("CLEAR TEXT OFF")) {
                    this.lI = false;
                    Toast.makeText(getActivity(), "CLEAR TEXT OFF", 0).show();
                    return true;
                } else {
                    return false;
                }
            } else {
                f.D(TAG, this.at + "   " + this.lu);
                String[] split = this.at.split("嗶咔_");
                String[] split2 = this.lu.split(",");
                if (split == null || split2 == null) {
                    return true;
                }
                for (int i = 0; i < split.length; i++) {
                    f.D(TAG, "AT " + i + " = " + split[i]);
                    f.D(TAG, "ID " + i + " = " + split2[i]);
                }
                if (split.length != 2 && split2.length != 2) {
                    Toast.makeText(getContext(), "Blacklist User Failed", 1).show();
                    return true;
                } else if (split[1].startsWith("嗶咔") || split[1].equalsIgnoreCase("ruff")) {
                    Toast.makeText(getContext(), "Blacklist User Failed", 1).show();
                    return true;
                } else {
                    if (this.lG == null) {
                        this.lG = new ArrayList<>();
                    }
                    this.lG.add(new ChatroomBlacklistObject(split[1], split2[1]));
                    for (int i2 = 0; i2 < this.lG.size(); i2++) {
                        f.D(TAG, "BLACKLIST " + i2 + ": " + this.lG.get(i2).getUserId() + " - " + this.lG.get(i2).getUsername());
                    }
                    e.u(getContext(), this.gson.toJson(this.lG));
                    Toast.makeText(getContext(), "Blacklist User: " + split[1] + " Success", 1).show();
                    return true;
                }
            }
        }
    }

    public boolean W(String str) {
        if (this.lG == null || this.lG.size() <= 0) {
            return false;
        }
        for (int i = 0; i < this.lG.size(); i++) {
            if (this.lG.get(i).getUserId().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void X(String str) {
        String[] split = str.split("\n@");
        int i = 0;
        String str2 = split[0];
        final String[] strArr = new String[split.length - 1];
        while (i < split.length - 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("@");
            int i2 = i + 1;
            sb.append(split[i2]);
            strArr[i] = sb.toString();
            i = i2;
        }
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(str2).setSingleChoiceItems(strArr, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.66
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i3) {
                String[] split2 = strArr[i3].split("\n");
                ChatroomFragment.this.editText_textbox.setText(split2[0]);
                ChatroomFragment.this.editText_textbox.setSelection(split2[0].length());
                String[] split3 = split2[0].split(" ");
                if (split3.length > 1 && (split3[1].contains("新的稱號") || split3[1].contains("嗶咔幣") || split3[1].contains("編號") || split3[1].contains("頁碼"))) {
                    ChatroomFragment.this.a(ChatroomFragment.this.getContext(), split3[0], split3[1]);
                }
                dialogInterface.dismiss();
            }
        }).show();
    }

    public ChatMessageObject a(JSONObject jSONObject, int i) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        ChatMessageObject chatMessageObject = new ChatMessageObject("", "", 0, "", "", "", "", "", "", null, "", "", "", "", "", "", "", i, false, null, null, null, null);
        try {
            if (jSONObject.has("id")) {
                chatMessageObject.setUserId(jSONObject.getString("id"));
            }
            if (jSONObject.has("userId")) {
                chatMessageObject.setUserId(jSONObject.getString("userId"));
            }
            if (jSONObject.has("user_id")) {
                chatMessageObject.setUserId(jSONObject.getString("user_id"));
            }
            if (jSONObject.has("unique_id")) {
                chatMessageObject.setUniqueId(jSONObject.getString("unique_id"));
            }
            if (jSONObject.has("level")) {
                chatMessageObject.setLevel(jSONObject.getInt("level"));
            }
            if (jSONObject.has("email")) {
                chatMessageObject.setEmail(jSONObject.getString("email"));
            }
            if (jSONObject.has("avatar")) {
                chatMessageObject.setAvatar(jSONObject.getString("avatar"));
            }
            if (jSONObject.has("name")) {
                chatMessageObject.setName(jSONObject.getString("name"));
            }
            if (jSONObject.has("title")) {
                chatMessageObject.setTitle(jSONObject.getString("title"));
            }
            if (jSONObject.has("gender")) {
                chatMessageObject.setGender(jSONObject.getString("gender"));
            }
            if (jSONObject.has("platform")) {
                chatMessageObject.setPlatform(jSONObject.getString("platform"));
            }
            if (jSONObject.has("activation_date")) {
                chatMessageObject.setActivationDate(jSONObject.getString("activation_date"));
            }
            if (jSONObject.has("activationDate")) {
                chatMessageObject.setActivationDate(jSONObject.getString("activationDate"));
            }
            if (jSONObject.has("at")) {
                chatMessageObject.setAt(jSONObject.getString("at"));
            }
            if (jSONObject.has("reply_name")) {
                chatMessageObject.setReplyName(jSONObject.getString("reply_name"));
            }
            if (jSONObject.has("reply")) {
                chatMessageObject.setReply(jSONObject.getString("reply"));
            }
            if (jSONObject.has("message")) {
                chatMessageObject.setMessage(jSONObject.getString("message"));
            }
            if (jSONObject.has("image")) {
                chatMessageObject.setImage(jSONObject.getString("image"));
            }
            if (jSONObject.has("audio")) {
                chatMessageObject.setAudio(jSONObject.getString("audio"));
            }
            if (jSONObject.has("block_user_id")) {
                chatMessageObject.setBlockUserId(jSONObject.getString("block_user_id"));
            }
            if (jSONObject.has("verified")) {
                chatMessageObject.setVerified(jSONObject.getBoolean("verified"));
            }
            if (jSONObject.has("character")) {
                chatMessageObject.setCharacter(jSONObject.getString("character"));
            }
            if (jSONObject.has("characters") && (jSONArray2 = jSONObject.getJSONArray("characters")) != null && jSONArray2.length() > 0) {
                String[] strArr = new String[jSONArray2.length()];
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    strArr[i2] = jSONArray2.getString(i2);
                }
                chatMessageObject.setCharacters(strArr);
            }
            if (jSONObject.has("event_icon")) {
                chatMessageObject.setEventIcon(jSONObject.getString("event_icon"));
            }
            if (jSONObject.has("event_colors") && (jSONArray = jSONObject.getJSONArray("event_colors")) != null && jSONArray.length() > 0) {
                String[] strArr2 = new String[jSONArray.length()];
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    strArr2[i3] = jSONArray.getString(i3);
                }
                chatMessageObject.setEventColors(strArr2);
            }
            return chatMessageObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void a(Context context, final String str, String str2) {
        if (context == null) {
            return;
        }
        final Dialog dialog = new Dialog(context, 2131755200);
        dialog.setContentView(2131427385);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.width = g.as(context);
        final EditText editText = (EditText) dialog.findViewById(2131296459);
        TextView textView = (TextView) dialog.findViewById(2131296932);
        Button button = (Button) dialog.findViewById(2131296371);
        Button button2 = (Button) dialog.findViewById(2131296370);
        textView.setText(str + "");
        editText.setHint(str2 + "");
        if (str2.contains("嗶咔幣") || str2.contains("編號") || str2.contains("頁碼")) {
            editText.setKeyListener(DigitsKeyListener.getInstance("0123456789"));
            editText.setInputType(3);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.68
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String str3 = str + " " + editText.getText().toString();
                ChatroomFragment.this.editText_textbox.setText(str3);
                ChatroomFragment.this.editText_textbox.setSelection(str3.length());
                dialog.dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.69
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void a(ChatMessageObject chatMessageObject) {
        String str = TAG;
        f.D(str, "CHAT = " + chatMessageObject.toString());
        if (W(chatMessageObject.getUserId())) {
            return;
        }
        if (this.jW != null && this.jW.getName() != null) {
            if (chatMessageObject.getAt() != null) {
                String at = chatMessageObject.getAt();
                if (at.contains("嗶咔_" + this.jW.getName())) {
                    AtMeObject atMeObject = new AtMeObject(chatMessageObject.getName(), -1);
                    if (this.lF != null) {
                        this.lF.add(0, atMeObject);
                    }
                    co();
                    cv();
                }
            }
            if (chatMessageObject.getReplyName() != null && chatMessageObject.getReplyName().equals(this.jW.getName())) {
                AtMeObject atMeObject2 = new AtMeObject(chatMessageObject.getName(), -1);
                if (this.lF != null) {
                    this.lF.add(0, atMeObject2);
                }
                co();
            }
            cv();
        }
        synchronized (this.arrayList) {
            this.arrayList.add(0, chatMessageObject);
            if (chatMessageObject.getType() == 3) {
                String str2 = TAG;
                f.D(str2, "Message Data = " + chatMessageObject.toString());
                cz();
                cA();
            } else {
                String str3 = TAG;
                f.D(str3, "Message Data = " + chatMessageObject.toString());
                if (this.lA && chatMessageObject.getMessage().contains("聊天室的指令")) {
                    X(chatMessageObject.getMessage());
                    this.lA = false;
                }
                if (Build.VERSION.SDK_INT >= 21 && this.lJ) {
                    try {
                        if (this.lK) {
                            TextToSpeech textToSpeech = this.lH;
                            textToSpeech.speak("：" + chatMessageObject.getName() + "說：" + chatMessageObject.getMessage() + "", 1, null, "");
                        } else {
                            TextToSpeech textToSpeech2 = this.lH;
                            textToSpeech2.speak(chatMessageObject.getMessage() + "", 1, null, "");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.le.notifyItemInserted(0);
        }
        cu();
        ct();
        if (this.jQ.findFirstCompletelyVisibleItemPosition() == 0) {
            this.jQ.scrollToPosition(0);
            return;
        }
        N(chatMessageObject.getName() + ": " + chatMessageObject.getMessage());
    }

    public void a(String str, String str2, int i, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, boolean z, boolean z2, String str15, ChatroomToObject chatroomToObject, String str16, String[] strArr) {
        cv();
        synchronized (this.arrayList) {
            try {
                if (z) {
                    this.arrayList.add(0, new ChatMessageObject(str, str2, i, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, "", "", str14, 10, z2, str15, null, str16, strArr, chatroomToObject));
                    cz();
                    cA();
                    this.lD = null;
                } else {
                    try {
                        this.arrayList.add(0, new ChatMessageObject(str, str2, i, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, "", "", str14, 9, z2, str15, null, str16, strArr, chatroomToObject));
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
                this.le.notifyItemInserted(0);
                cu();
                ct();
                if (this.jQ.findFirstCompletelyVisibleItemPosition() == 0) {
                    this.jQ.scrollToPosition(0);
                    return;
                }
                N(str5 + ": " + str13);
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public void a(final String str, final String str2, final String str3, final String str4, final String str5) {
        this.lg = new com.picacomic.fregata.b.d(getContext()).dO().a(e.z(getActivity()), str5, new UpdateUserTitleBody(str2));
        this.lg.enqueue(new Callback<RegisterResponse>() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.43
            @Override // retrofit2.Callback
            public void onFailure(Call<RegisterResponse> call, Throwable th) {
                th.printStackTrace();
                new c(ChatroomFragment.this.getActivity()).dN();
                Toast.makeText(ChatroomFragment.this.getContext(), "Update Title Failed", 0).show();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code() == 200) {
                    Toast.makeText(ChatroomFragment.this.getContext(), "Update Title Success", 0).show();
                    ChatroomFragment.this.a(new ChangeTitleAction("set_title", str, str2, str3, str4, str5), ChangeTitleAction.class);
                    return;
                }
                try {
                    new c(ChatroomFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toast.makeText(ChatroomFragment.this.getContext(), "Update Title Failed", 0).show();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.picacomic.fregata.fragments.ChatroomFragment$12] */
    public void b(final int i, final String str) {
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
        }
        if (this.jW != null) {
            this.jZ.b("send_message", this.gson.toJson(new ChatMessageObject(this.jW.getUserId(), "", this.jW.getLevel(), this.jW.getEmail(), g.b(this.jW.getAvatar()), this.jW.getName(), this.jW.getTitle(), this.jW.getGender(), "android", this.jW.getActivationDate(), this.at, this.lt, this.reply, "##server talk @" + str, "", "", this.lu, 3, this.jW.isVerified(), this.jW.getCharacter(), this.jW.getCharactersStringArray(), null, null), ChatMessageObject.class));
        }
        int i2 = i * 1000;
        if (i2 > 0) {
            long j = i2;
            this.countDownTimer = new CountDownTimer(j, j) { // from class: com.picacomic.fregata.fragments.ChatroomFragment.12
                @Override // android.os.CountDownTimer
                public void onFinish() {
                    ChatroomFragment.this.b(i, str);
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j2) {
                }
            }.start();
        }
    }

    @Override // com.picacomic.fregata.fragments.BaseImagePickFragment, com.picacomic.fregata.fragments.BaseFragment, com.picacomic.fregata.a.i
    public void b(View view) {
        if (this.relativeLayout_popup != null && this.relativeLayout_popup.getVisibility() == 0) {
            this.relativeLayout_popup.setVisibility(8);
            this.ll = null;
        } else if (getParentFragment() == null || !((getParentFragment() instanceof ChatroomContainerFragment) || (getParentFragment() instanceof CustomPicaAppContainerFragment))) {
            AlertDialogCenter.showCustomAlertDialog(getActivity(), 2131230957, 2131689548, 2131689547, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.49
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ChatroomFragment.this.getChildFragmentManager().popBackStack();
                    ChatroomFragment.this.getFragmentManager().popBackStack();
                }
            }, (View.OnClickListener) null);
        } else {
            AlertDialogCenter.showCustomAlertDialog(getActivity(), 2131230957, 2131689548, 2131689547, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.48
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ChatroomFragment.this.getParentFragment().getFragmentManager().popBackStack();
                    ChatroomFragment.this.getFragmentManager().popBackStack();
                }
            }, (View.OnClickListener) null);
        }
    }

    public void b(ChatMessageObject chatMessageObject) {
        cv();
        String str = TAG;
        f.D(str, "ADD ADS IMAGE = " + chatMessageObject.getImage());
        synchronized (this.arrayList) {
            this.arrayList.add(0, chatMessageObject);
            String str2 = TAG;
            f.D(str2, "ADS = " + chatMessageObject);
            if (Build.VERSION.SDK_INT >= 21 && this.lJ) {
                try {
                    if (this.lK) {
                        TextToSpeech textToSpeech = this.lH;
                        textToSpeech.speak("：" + chatMessageObject.getName() + "發了一個廣告", 1, null, "");
                    } else {
                        this.lH.speak("發了一個廣告", 1, null, "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.le.notifyItemInserted(0);
        }
        cu();
        ct();
        if (this.jQ.findFirstCompletelyVisibleItemPosition() == 0) {
            this.jQ.scrollToPosition(0);
            return;
        }
        N(chatMessageObject.getName() + ": " + getResources().getString(2131689668));
    }

    public void b(final String str, final String str2, final String str3, final String str4) {
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(str2).setSingleChoiceItems(2130903042, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.52
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ChatroomFragment.this.lB = i;
            }
        }).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.51
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ChatroomFragment.this.lB < 0) {
                    return;
                }
                ChatroomFragment.this.m(false);
                if (!str.equalsIgnoreCase("ruff@picacomic.com") && !str2.equalsIgnoreCase("ruff")) {
                    ChatroomFragment.this.a(new MuteAction("mute", str, new int[]{0, 1, 5, 15, 30, 60, 180, 1440, 4320, 10080}[ChatroomFragment.this.lB], str2, str3, str4), MuteAction.class);
                }
                ChatroomFragment.this.lB = -1;
                dialogInterface.dismiss();
            }
        }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.50
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ChatroomFragment.this.m(false);
                ChatroomFragment.this.lB = -1;
                dialogInterface.dismiss();
            }
        }).show();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        if (getActivity() != null && (getParentFragment() == null || (!(getParentFragment() instanceof ChatroomContainerFragment) && !(getParentFragment() instanceof CustomPicaAppContainerFragment)))) {
            AlertDialogCenter.chatroomRules(getActivity());
        }
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(8);
        }
        if (this.gson == null) {
            this.gson = new Gson();
        }
        String B = e.B(getContext());
        if (B != null) {
            this.jW = (UserProfileObject) this.gson.fromJson(B, (Class<Object>) UserProfileObject.class);
            if (this.lv != null && !this.lv.equalsIgnoreCase("")) {
                this.jW.setCharacter(this.lv);
            }
        }
        cd();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
        if (this.jW != null) {
            cF();
            if (this.lv == null || this.lv.equalsIgnoreCase("")) {
                return;
            }
            this.jW.setCharacter(this.lv);
        }
    }

    public void c(ChatMessageObject chatMessageObject) {
        cv();
        synchronized (this.arrayList) {
            this.arrayList.add(0, chatMessageObject);
            if (chatMessageObject.getType() == 4) {
                cz();
                cA();
            } else if (Build.VERSION.SDK_INT >= 21 && this.lJ) {
                try {
                    if (this.lK) {
                        TextToSpeech textToSpeech = this.lH;
                        textToSpeech.speak("：" + chatMessageObject.getName() + "發了一張圖片", 1, null, "");
                    } else {
                        this.lH.speak("發了一張圖片", 1, null, "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.le.notifyItemInserted(0);
        }
        cu();
        ct();
        if (this.jQ.findFirstCompletelyVisibleItemPosition() == 0) {
            this.jQ.scrollToPosition(0);
            return;
        }
        N(chatMessageObject.getName() + ": " + getResources().getString(2131689670));
    }

    public void c(final String str, final String str2, final String str3, final String str4) {
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(str2).setSingleChoiceItems(2130903041, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.55
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ChatroomFragment.this.lC = i;
            }
        }).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.54
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (ChatroomFragment.this.lC < 0 || ChatroomFragment.this.lC >= 7) {
                    ChatroomFragment.this.d(str, str2, str3, str4);
                } else {
                    ChatroomFragment.this.v(false);
                    ChatroomFragment.this.a(new SetAvatarAction("set_avatar", str, str2, str3, str4, ChatroomFragment.this.lC + 1), SetAvatarAction.class);
                    ChatroomFragment.this.lC = -1;
                }
                dialogInterface.dismiss();
            }
        }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.53
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ChatroomFragment.this.v(false);
                ChatroomFragment.this.lC = -1;
                dialogInterface.dismiss();
            }
        }).show();
    }

    public void cA() {
        this.textView_reply.setText("");
        this.reply = "";
        this.lt = "";
        this.textView_reply.setVisibility(8);
    }

    public boolean cB() {
        return this.iN;
    }

    public boolean cC() {
        return this.lx;
    }

    public boolean cD() {
        return this.ly;
    }

    public void cE() {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2131427390, (ViewGroup) getView().getParent(), false);
        final EditText editText = (EditText) inflate.findViewById(2131296483);
        final EditText editText2 = (EditText) inflate.findViewById(2131296484);
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(2131689528).setView(inflate).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.62
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText2 != null && editText2.getText() != null && editText2.getText().length() > 0) {
                    int i2 = 0;
                    try {
                        if (editText != null) {
                            i2 = 0;
                            if (editText.getText() != null) {
                                i2 = 0;
                                if (editText.getText().length() > 0) {
                                    i2 = Integer.parseInt(editText.getText().toString());
                                }
                            }
                        }
                    } catch (Exception e) {
                        i2 = 0;
                    }
                    ChatroomFragment.this.b(i2, editText2.getText().toString() + "");
                }
                dialogInterface.dismiss();
            }
        }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.61
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    public void cF() {
        if (getContext() == null || this.le == null) {
            return;
        }
        this.le.n(e.U(getContext()));
        this.le.o(e.V(getContext()));
        this.le.p(e.ad(getContext()));
        if (this.frameLayout_nightMode != null) {
            if (e.T(getContext())) {
                this.frameLayout_nightMode.setVisibility(0);
            } else {
                this.frameLayout_nightMode.setVisibility(8);
            }
        }
        this.kW = e.W(getContext());
        this.kX = e.X(getContext());
        if (this.jW != null && !e.ab(getContext()).equalsIgnoreCase("")) {
            this.jW.setCharacter(e.ab(getContext()));
        }
        if (this.jW != null && !e.ac(getContext()).equalsIgnoreCase("")) {
            this.jW.setName(e.ac(getContext()));
        }
        if (this.jW != null && this.jW.getEmail() != null && g.F(getContext(), this.jW.getEmail()) != 0) {
            if (this.linearLayout_controlPanel != null) {
                this.linearLayout_controlPanel.setVisibility(0);
            }
            if (this.imageButton_hideChannel != null) {
                this.imageButton_hideChannel.setVisibility(0);
            }
        }
        this.gson = new Gson();
        String ae = e.ae(getContext());
        if (ae != null) {
            this.lG = (ArrayList) this.gson.fromJson(ae, new TypeToken<List<ChatroomBlacklistObject>>() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.65
            }.getType());
        } else {
            this.lG = new ArrayList<>();
        }
        String str = TAG;
        f.D(str, "Updated UserProfile = " + this.jW.toString());
        if (this.lH != null && Build.VERSION.SDK_INT >= 21) {
            try {
                if (e.aa(getContext()).equalsIgnoreCase("cantonese")) {
                    this.lH.setLanguage(com.picacomic.fregata.c.b.aD("cantonese"));
                } else if (e.aa(getContext()).equalsIgnoreCase("english")) {
                    this.lH.setLanguage(com.picacomic.fregata.c.b.aD("english"));
                } else if (e.aa(getContext()).equalsIgnoreCase("japanese")) {
                    this.lH.setLanguage(com.picacomic.fregata.c.b.aD("japanese"));
                } else {
                    this.lH.setLanguage(com.picacomic.fregata.c.b.aD("chinese"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.lJ = e.Y(getContext());
        this.lK = e.Z(getContext());
        if (!this.lJ && this.lH != null && this.lH.isSpeaking()) {
            this.lH.stop();
        }
        this.le.notifyDataSetChanged();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.iI = new RelativeLayout.LayoutParams(-2, -2);
        this.fab_command.setLayoutParams(this.iI);
        this.fab_command.setOnTouchListener(new View.OnTouchListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.23
            int iR;
            int iS;
            boolean iT;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int rawX = (int) motionEvent.getRawX();
                int rawY = (int) motionEvent.getRawY();
                switch (motionEvent.getAction()) {
                    case 0:
                        this.iR = rawX;
                        this.iS = rawY;
                        this.iT = true;
                        ChatroomFragment.this.iL = System.currentTimeMillis();
                        ChatroomFragment.this.iJ = rawX - ChatroomFragment.this.iI.leftMargin;
                        ChatroomFragment.this.iK = rawY - ChatroomFragment.this.iI.topMargin;
                        return true;
                    case 1:
                        ChatroomFragment.this.fab_command.setLayoutParams(ChatroomFragment.this.iI);
                        if (System.currentTimeMillis() - ChatroomFragment.this.iL >= 500 || !this.iT) {
                            return true;
                        }
                        ChatroomFragment.this.editText_textbox.setText("@指令");
                        ChatroomFragment.this.cr();
                        return true;
                    case 2:
                        if (Math.abs(this.iR - rawX) >= 10 || Math.abs(this.iS - rawY) >= 10) {
                            this.iT = false;
                        } else {
                            this.iT = true;
                        }
                        ChatroomFragment.this.iI.leftMargin = rawX - ChatroomFragment.this.iJ;
                        ChatroomFragment.this.iI.topMargin = rawY - ChatroomFragment.this.iK;
                        ChatroomFragment.this.fab_command.setLayoutParams(ChatroomFragment.this.iI);
                        return true;
                    default:
                        return true;
                }
            }
        });
        this.gridView_emojiList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.34
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ChatroomFragment.this.editText_textbox.append(g.ae(ChatroomFragment.this.lf.jK[i]));
            }
        });
        this.editText_textbox.addTextChangedListener(new TextWatcher() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.45
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() > 0) {
                    if (ChatroomFragment.this.button_sendMessage.getVisibility() == 8) {
                        ChatroomFragment.this.imageButton_sendAudio.setVisibility(8);
                        ChatroomFragment.this.button_sendMessage.setVisibility(0);
                    }
                } else if (ChatroomFragment.this.imageButton_sendAudio.getVisibility() == 8) {
                    ChatroomFragment.this.imageButton_sendAudio.setVisibility(0);
                    ChatroomFragment.this.button_sendMessage.setVisibility(8);
                }
            }
        });
        this.button_sendMessage.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.56
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.V(ChatroomFragment.this.editText_textbox.getText().toString())) {
                    return;
                }
                if (ChatroomFragment.this.lD == null) {
                    ChatroomFragment.this.cr();
                } else {
                    ChatroomFragment.this.cs();
                }
                if (ChatroomFragment.this.gridView_emojiList != null) {
                    ChatroomFragment.this.gridView_emojiList.setVisibility(8);
                }
                View currentFocus = ChatroomFragment.this.getActivity().getCurrentFocus();
                if (currentFocus != null) {
                    try {
                        ((InputMethodManager) ChatroomFragment.this.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.imageButton_setting.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.67
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.getContext() != null) {
                    AlertDialogCenter.showChatroomSettingDialog(ChatroomFragment.this.getContext(), ChatroomFragment.this.jW, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.67.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            ChatroomFragment.this.cd();
                        }
                    }, new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.67.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            ChatroomFragment.this.cF();
                        }
                    });
                }
            }
        });
        this.imageButton_sendEmoji.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.70
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.gridView_emojiList.getVisibility() == 0) {
                    ChatroomFragment.this.gridView_emojiList.setVisibility(8);
                } else {
                    ChatroomFragment.this.gridView_emojiList.setVisibility(0);
                }
            }
        });
        this.imageButton_sendImage.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.71
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.gridView_emojiList != null) {
                    ChatroomFragment.this.gridView_emojiList.setVisibility(8);
                }
                ChatroomFragment.this.kv = 2;
                ChatroomFragment.this.cf();
            }
        });
        this.imageButton_sendAudio.setOnTouchListener(new View.OnTouchListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (ChatroomFragment.this.gridView_emojiList != null) {
                    ChatroomFragment.this.gridView_emojiList.setVisibility(8);
                }
                ((BaseActivity) ChatroomFragment.this.getActivity()).requestPermission();
                int checkSelfPermission = ContextCompat.checkSelfPermission(ChatroomFragment.this.getActivity(), "android.permission.WRITE_EXTERNAL_STORAGE");
                int checkSelfPermission2 = ContextCompat.checkSelfPermission(ChatroomFragment.this.getActivity(), "android.permission.RECORD_AUDIO");
                if (checkSelfPermission != 0 || checkSelfPermission2 != 0) {
                    Toast.makeText(ChatroomFragment.this.getActivity(), "No Permission", 0).show();
                    return true;
                }
                switch (motionEvent.getAction()) {
                    case 0:
                        ChatroomFragment.this.editText_textbox.setText(2131689665);
                        ChatroomFragment.this.lb = System.currentTimeMillis();
                        ChatroomFragment.this.t(true);
                        return true;
                    case 1:
                        ChatroomFragment.this.editText_textbox.setText("");
                        ChatroomFragment.this.lc = System.currentTimeMillis();
                        ChatroomFragment.this.t(false);
                        return true;
                    default:
                        return true;
                }
            }
        });
        this.button_saveImage.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.ll != null) {
                    g.a(ChatroomFragment.this.getContext(), ChatroomFragment.this.ll);
                } else {
                    Toast.makeText(ChatroomFragment.this.getContext(), 2131689662, 0).show();
                }
            }
        });
        this.imageButton_closePopup.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.relativeLayout_popup.setVisibility(8);
                ChatroomFragment.this.ll = null;
            }
        });
        this.imageView_largeImage.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.relativeLayout_popup.setVisibility(8);
                ChatroomFragment.this.ll = null;
            }
        });
        this.textView_toastMsg.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.recyclerView_chat == null || ChatroomFragment.this.arrayList == null || ChatroomFragment.this.arrayList.size() <= 0) {
                    return;
                }
                ChatroomFragment.this.recyclerView_chat.scrollToPosition(0);
            }
        });
        this.textView_toastAtMe.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.lF == null || ChatroomFragment.this.lF.size() <= 0) {
                    return;
                }
                if (ChatroomFragment.this.arrayList.size() - 1 <= ChatroomFragment.this.lF.get(0).getIndex() || ChatroomFragment.this.lF.get(0).getIndex() < 0) {
                    ChatroomFragment.this.cp();
                    return;
                }
                ChatroomFragment.this.recyclerView_chat.scrollToPosition(ChatroomFragment.this.lF.get(0).getIndex());
                ChatroomFragment.this.lF.remove(0);
                if (ChatroomFragment.this.lF.size() > 0) {
                    ChatroomFragment.this.co();
                } else {
                    ChatroomFragment.this.cp();
                }
            }
        });
        this.textView_atList.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.cz();
                if (ChatroomFragment.this.cB()) {
                    ChatroomFragment.this.lu = "";
                    ChatroomFragment.this.m(false);
                    ChatroomFragment.this.v(false);
                    ChatroomFragment.this.editText_textbox.setText("");
                }
            }
        });
        this.textView_reply.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.cA();
            }
        });
        this.textView_audioPlayingTimer.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.cx();
            }
        });
        this.button_controlTime.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.T(ChatroomFragment.this.jW.getEmail());
            }
        });
        this.button_controlImage.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.U(ChatroomFragment.this.jW.getEmail());
            }
        });
        this.button_controlHidePanelButtons.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.linearLayout_controlPanelButtons.getVisibility() == 0) {
                    ChatroomFragment.this.linearLayout_controlPanelButtons.setVisibility(8);
                } else {
                    ChatroomFragment.this.linearLayout_controlPanelButtons.setVisibility(0);
                }
            }
        });
        this.button_controlMute.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.15
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.cB()) {
                    ChatroomFragment.this.m(false);
                } else {
                    ChatroomFragment.this.m(true);
                }
            }
        });
        this.button_controlHideAvatar.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.cC()) {
                    ChatroomFragment.this.v(false);
                } else {
                    ChatroomFragment.this.v(true);
                }
            }
        });
        this.button_viewProfile.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.lw != null) {
                    ChatroomFragment.this.relativeLayout_popup.setVisibility(8);
                    ChatroomFragment.this.ll = null;
                    ChatroomFragment.this.E(ChatroomFragment.this.lw);
                }
            }
        });
        this.button_controlChangeTitle.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.cD()) {
                    ChatroomFragment.this.w(false);
                } else {
                    ChatroomFragment.this.w(true);
                }
            }
        });
        this.button_controlAI.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.countDownTimer != null) {
                    ChatroomFragment.this.countDownTimer.cancel();
                }
                ChatroomFragment.this.cE();
            }
        });
        this.textView_totalUserCount.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.button_channelPublic.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.x(true);
            }
        });
        this.button_channelPrivate.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ChatroomFragment.this.x(false);
            }
        });
        this.imageButton_hideChannel.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChatroomFragment.this.linearLayout_chatChannel.getVisibility() == 0) {
                    ChatroomFragment.this.imageButton_hideChannel.setImageResource(2131230882);
                    ChatroomFragment.this.linearLayout_chatChannel.setVisibility(8);
                    return;
                }
                ChatroomFragment.this.imageButton_hideChannel.setImageResource(2131230881);
                ChatroomFragment.this.linearLayout_chatChannel.setVisibility(0);
            }
        });
    }

    public void cd() {
        C(getResources().getString(2131689823));
        f.aA("Show Progress");
        this.jX = new com.picacomic.fregata.b.d(getContext()).dO().am(e.z(getActivity()));
        this.jX.enqueue(new Callback<GeneralResponse<UserProfileResponse>>() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.42
            @Override // retrofit2.Callback
            public void onFailure(Call<GeneralResponse<UserProfileResponse>> call, Throwable th) {
                th.printStackTrace();
                f.aA("dismiss progress");
                ChatroomFragment.this.bC();
                new c(ChatroomFragment.this.getActivity()).dN();
            }

            @Override // retrofit2.Callback
            public void onResponse(Call<GeneralResponse<UserProfileResponse>> call, Response<GeneralResponse<UserProfileResponse>> response) {
                if (response.code() != 200) {
                    try {
                        new c(ChatroomFragment.this.getActivity(), response.code(), response.errorBody().string()).dN();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (response.body() != null && response.body().data != null && response.body().data.getUser() != null) {
                    ChatroomFragment.this.jW = response.body().data.getUser();
                    if (ChatroomFragment.this.getActivity() != null) {
                        if (ChatroomFragment.this.gson == null) {
                            ChatroomFragment.this.gson = new Gson();
                        }
                        e.i(ChatroomFragment.this.getContext(), ChatroomFragment.this.gson.toJson(ChatroomFragment.this.jW));
                        if (ChatroomFragment.this.lm != null) {
                            ChatroomFragment.this.jW.setName(ChatroomFragment.this.lm);
                        }
                        if (ChatroomFragment.this.lo != null) {
                            ChatroomFragment.this.jW.setCharacter(ChatroomFragment.this.lo);
                        }
                        if (ChatroomFragment.this.ln != null) {
                            ChatroomFragment.this.jW.setTitle(ChatroomFragment.this.ln);
                        }
                        if (ChatroomFragment.this.lr > 0) {
                            ChatroomFragment.this.jW.setLevel(ChatroomFragment.this.lr);
                        }
                        ChatroomFragment.this.bI();
                    }
                }
                f.aA("dismiss progress");
                ChatroomFragment.this.bC();
            }
        });
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

    public void co() {
        f.D(TAG, "RUN SHOW TOAST AT ME");
        cp();
        if (this.textView_toastAtMe != null) {
            this.textView_toastAtMe.setVisibility(0);
            if (this.lF != null) {
                if (this.lF.size() > 1) {
                    TextView textView = this.textView_toastAtMe;
                    textView.setText(this.lF.size() + " 個人 " + getString(2131689656));
                } else if (this.jW == null || this.jW.getName() == null || !this.lF.get(0).getName().equals(this.jW.getName())) {
                    TextView textView2 = this.textView_toastAtMe;
                    textView2.setText(this.lF.get(0).getName() + " " + getString(2131689656));
                } else {
                    TextView textView3 = this.textView_toastAtMe;
                    textView3.setText(" " + getString(2131689666));
                }
            }
        }
        this.lj = new CountDownTimer(60000L, 1000L) { // from class: com.picacomic.fregata.fragments.ChatroomFragment.26
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (ChatroomFragment.this.lF != null) {
                    ChatroomFragment.this.lF.clear();
                }
                ChatroomFragment.this.cp();
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (ChatroomFragment.this.textView_toastAtMe != null) {
                    ChatroomFragment.this.textView_toastAtMe.setVisibility(0);
                }
            }
        };
        this.lj.start();
    }

    public void cp() {
        if (this.textView_toastAtMe != null) {
            this.textView_toastAtMe.setVisibility(8);
        }
        if (this.lj != null) {
            this.lj.cancel();
            this.lj = null;
        }
    }

    public void cq() {
        if (this.li != null) {
            this.li.cancel();
            this.li = null;
        }
        this.li = new CountDownTimer(2000L, 500L) { // from class: com.picacomic.fregata.fragments.ChatroomFragment.27
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (ChatroomFragment.this.frameLayout_chatOnlineCountContainer != null) {
                    ChatroomFragment.this.frameLayout_chatOnlineCountContainer.setVisibility(8);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
            }
        };
        this.li.start();
    }

    public void ct() {
        if (this.arrayList.size() > this.kW) {
            int size = this.arrayList.size() - this.kW;
            for (int i = 0; i < size; i++) {
                this.arrayList.remove(this.kW);
            }
            this.le.notifyItemRangeRemoved(this.kW, size);
        }
    }

    @Deprecated
    public void cu() {
    }

    public void cv() {
        if (this.lF == null || this.lF.size() <= 0) {
            return;
        }
        for (int i = 0; i < this.lF.size(); i++) {
            this.lF.get(i).addIndex();
        }
    }

    public void cy() {
        this.ld = new CountDownTimer(10000L, 500L) { // from class: com.picacomic.fregata.fragments.ChatroomFragment.47
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (ChatroomFragment.this.editText_textbox != null) {
                    ChatroomFragment.this.editText_textbox.setText("");
                }
                ChatroomFragment.this.lc = System.currentTimeMillis();
                ChatroomFragment.this.t(false);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                if (ChatroomFragment.this.editText_textbox != null) {
                    EditText editText = ChatroomFragment.this.editText_textbox;
                    editText.setText(ChatroomFragment.this.getResources().getString(2131689665) + ": " + ((int) Math.floor(((float) (10000 - j)) / 1000.0f)) + "s");
                }
            }
        };
        this.ld.start();
    }

    public void cz() {
        this.textView_atList.setText("");
        this.at = "";
        this.lu = "";
        this.textView_atList.setVisibility(8);
    }

    public void d(ChatMessageObject chatMessageObject) {
        cv();
        synchronized (this.arrayList) {
            this.arrayList.add(0, chatMessageObject);
            if (chatMessageObject.getType() == 5) {
                cz();
                cA();
            } else if (Build.VERSION.SDK_INT >= 21 && this.lJ) {
                try {
                    if (this.lK) {
                        TextToSpeech textToSpeech = this.lH;
                        textToSpeech.speak("：" + chatMessageObject.getName() + "發了一段語音", 1, null, "");
                    } else {
                        this.lH.speak("發了一段語音", 1, null, "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.le.notifyItemInserted(0);
        }
        cu();
        ct();
        if (this.jQ.findFirstCompletelyVisibleItemPosition() == 0) {
            this.jQ.scrollToPosition(0);
            return;
        }
        N(chatMessageObject.getName() + ": " + getResources().getString(2131689669));
    }

    public void d(final String str, final String str2, final String str3, final String str4) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2131427389, (ViewGroup) getView().getParent(), false);
        final EditText editText = (EditText) inflate.findViewById(2131296482);
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(str2).setView(inflate).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.58
            /* JADX WARN: Removed duplicated region for block: B:14:0x003c  */
            @Override // android.content.DialogInterface.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onClick(android.content.DialogInterface r11, int r12) {
                /*
                    r10 = this;
                    r0 = r10
                    android.widget.EditText r0 = r5
                    if (r0 == 0) goto L36
                    r0 = r10
                    android.widget.EditText r0 = r5
                    android.text.Editable r0 = r0.getText()
                    if (r0 == 0) goto L36
                    r0 = r10
                    android.widget.EditText r0 = r5
                    android.text.Editable r0 = r0.getText()
                    int r0 = r0.length()
                    if (r0 <= 0) goto L36
                    r0 = r10
                    android.widget.EditText r0 = r5     // Catch: java.lang.Exception -> L31
                    android.text.Editable r0 = r0.getText()     // Catch: java.lang.Exception -> L31
                    java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L31
                    int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Exception -> L31
                    r12 = r0
                    goto L38
                L31:
                    r13 = move-exception
                    r0 = r13
                    r0.printStackTrace()
                L36:
                    r0 = -1
                    r12 = r0
                L38:
                    r0 = r12
                    if (r0 <= 0) goto L6f
                    r0 = r10
                    com.picacomic.fregata.fragments.ChatroomFragment r0 = com.picacomic.fregata.fragments.ChatroomFragment.this
                    r1 = 0
                    r0.v(r1)
                    r0 = r10
                    com.picacomic.fregata.fragments.ChatroomFragment r0 = com.picacomic.fregata.fragments.ChatroomFragment.this
                    com.picacomic.fregata.objects.chatroomObjects.SetAvatarExtraAction r1 = new com.picacomic.fregata.objects.chatroomObjects.SetAvatarExtraAction
                    r2 = r1
                    java.lang.String r3 = "set_avatar"
                    r4 = r10
                    java.lang.String r4 = r6
                    r5 = r10
                    java.lang.String r5 = r7
                    r6 = r10
                    java.lang.String r6 = r8
                    r7 = r10
                    java.lang.String r7 = r9
                    r8 = r12
                    r2.<init>(r3, r4, r5, r6, r7, r8)
                    java.lang.Class<com.picacomic.fregata.objects.chatroomObjects.SetAvatarExtraAction> r2 = com.picacomic.fregata.objects.chatroomObjects.SetAvatarExtraAction.class
                    com.picacomic.fregata.fragments.ChatroomFragment.a(r0, r1, r2)
                    r0 = r10
                    com.picacomic.fregata.fragments.ChatroomFragment r0 = com.picacomic.fregata.fragments.ChatroomFragment.this
                    r1 = -1
                    r0.lC = r1
                L6f:
                    r0 = r11
                    r0.dismiss()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.picacomic.fregata.fragments.ChatroomFragment.AnonymousClass58.onClick(android.content.DialogInterface, int):void");
            }
        }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.57
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ChatroomFragment.this.v(false);
                ChatroomFragment.this.lC = -1;
                dialogInterface.dismiss();
            }
        }).show();
    }

    public void e(final String str, final String str2, final String str3, final String str4) {
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2131427391, (ViewGroup) getView().getParent(), false);
        TextView textView = (TextView) inflate.findViewById(2131296991);
        final EditText editText = (EditText) inflate.findViewById(2131296485);
        textView.setText(str2 + "");
        new AlertDialog.Builder(getActivity(), 2131755210).setTitle(2131689529).setView(inflate).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.60
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (editText != null && editText.getText() != null && editText.getText().length() > 0) {
                    ChatroomFragment.this.w(false);
                    ChatroomFragment chatroomFragment = ChatroomFragment.this;
                    String str5 = str;
                    chatroomFragment.a(str5, editText.getText().toString() + "", str4, str2, str3);
                }
                dialogInterface.dismiss();
            }
        }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.59
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ChatroomFragment.this.w(false);
                dialogInterface.dismiss();
            }
        }).show();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        if (this.lH == null) {
            this.lH = new TextToSpeech(getActivity(), this);
        }
        this.lJ = false;
        e.k(getContext(), false);
        this.lK = true;
        this.lI = true;
        this.iN = false;
        this.lx = false;
        this.ly = false;
        this.lz = true;
        this.lB = -1;
        this.lC = -1;
        e.t(getContext(), "");
        a(this.toolbar, 2131689971, true);
        this.lF = new ArrayList<>();
        this.arrayList = new ArrayList<>();
        this.jQ = new LinearLayoutManager(getContext(), 1, true);
        this.le = new ChatroomRecyclerViewAdapter(getActivity(), this.arrayList, this);
        this.recyclerView_chat.setLayoutManager(this.jQ);
        this.recyclerView_chat.setAdapter(this.le);
        this.lf = new b(getActivity());
        this.gridView_emojiList.setAdapter((ListAdapter) this.lf);
        this.gson = new Gson();
        String ae = e.ae(getContext());
        if (ae != null) {
            this.lG = (ArrayList) this.gson.fromJson(ae, new TypeToken<List<ChatroomBlacklistObject>>() { // from class: com.picacomic.fregata.fragments.ChatroomFragment.1
            }.getType());
        } else {
            this.lG = new ArrayList<>();
        }
    }

    public void m(boolean z) {
        this.iN = z;
        if (z) {
            this.button_controlMute.setBackgroundColor(ContextCompat.getColor(getContext(), 2131099705));
        } else {
            this.button_controlMute.setBackgroundColor(ContextCompat.getColor(getContext(), 2131099808));
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            b.a aVar = new b.a();
            aVar.yT = new String[]{"websocket"};
            try {
                this.jZ = io.socket.client.b.a(getArguments().getString("KEY_CHATROOM_URL", "https://chat.picacomic.com"), aVar);
            } catch (URISyntaxException e) {
                try {
                    this.jZ = io.socket.client.b.a("https://chat.picacomic.com", aVar);
                } catch (URISyntaxException e2) {
                }
            }
            this.jZ.a("broadcast_message", this.lS);
            this.jZ.a("broadcast_image", this.lV);
            this.jZ.a("broadcast_audio", this.lW);
            this.jZ.a("new_connection", this.lP);
            this.jZ.a("connection_close", this.lQ);
            this.jZ.a("got_private_message", this.lU);
            this.jZ.a("change_character_icon", this.lR);
            this.jZ.a("broadcast_ads", this.lT);
            this.jZ.a("change_title", this.lL);
            this.jZ.a("receive_notification", this.lO);
            this.jZ.a("kick", this.lN);
            this.jZ.a("set_profile", this.lM);
            this.jZ.a("connect", this.kd);
            this.jZ.eJ();
        }
        this.kv = 2;
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427399, viewGroup, false);
        a(inflate);
        this.lE = new ChatroomGameFragment();
        return inflate;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment, android.support.v4.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.jZ.eO();
        this.jZ.c("broadcast_message", this.lS);
        this.jZ.c("broadcast_image", this.lV);
        this.jZ.c("broadcast_audio", this.lW);
        this.jZ.c("new_connection", this.lP);
        this.jZ.c("connection_close", this.lQ);
        this.jZ.c("change_character_icon", this.lR);
        this.jZ.c("broadcast_ads", this.lT);
        this.jZ.c("change_title", this.lL);
        this.jZ.c("receive_notification", this.lO);
        this.jZ.c("kick", this.lN);
        this.jZ.c("set_profile", this.lM);
        this.jZ.c("connect", this.kd);
        if (this.kY != null) {
            this.kY.cancel();
        }
        if (this.jX != null) {
            this.jX.cancel();
        }
        if (this.lg != null) {
            this.lg.cancel();
        }
        if (this.li != null) {
            this.li.cancel();
            this.li = null;
        }
        if (this.ld != null) {
            this.ld.cancel();
            this.ld = null;
        }
        if (this.lk != null) {
            this.lk.cancel();
            this.lk = null;
        }
        if (this.lh != null) {
            this.lh.cancel();
            this.lh = null;
        }
        if (this.lj != null) {
            this.lj.cancel();
            this.lj = null;
        }
        if (this.lH != null) {
            this.lH.stop();
            this.lH.shutdown();
        }
    }

    @Override // android.speech.tts.TextToSpeech.OnInitListener
    public void onInit(int i) {
        try {
            if (i == 0) {
                int language = this.lH.setLanguage(com.picacomic.fregata.c.b.aD("chinese"));
                if (language == -1 || language == -2) {
                    Log.e("error", "This Language is not supported");
                }
            } else {
                Log.e("error", "Initilization Failed!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        if (this.textView_audioPlayingTimer != null) {
            this.textView_audioPlayingTimer.setVisibility(8);
        }
        if (this.la != null) {
            this.la.release();
            this.la = null;
        }
        if (this.lX != null) {
            this.lX.cancel();
            this.lX.purge();
        }
        if (this.ii != null) {
            this.ii.release();
            this.ii = null;
        }
    }

    public void v(boolean z) {
        this.lx = z;
        if (z) {
            this.button_controlHideAvatar.setBackgroundColor(ContextCompat.getColor(getContext(), 2131099705));
        } else {
            this.button_controlHideAvatar.setBackgroundColor(ContextCompat.getColor(getContext(), 2131099808));
        }
    }

    public void w(boolean z) {
        this.ly = z;
        if (z) {
            this.button_controlChangeTitle.setBackgroundColor(ContextCompat.getColor(getContext(), 2131099705));
        } else {
            this.button_controlChangeTitle.setBackgroundColor(ContextCompat.getColor(getContext(), 2131099808));
        }
    }

    public void x(boolean z) {
        this.lz = z;
        if (z) {
            this.frameLayout_channelIndicator.setBackgroundColor(ContextCompat.getColor(getContext(), 2131099704));
        } else {
            this.frameLayout_channelIndicator.setBackgroundColor(ContextCompat.getColor(getContext(), 2131099706));
        }
    }
}
