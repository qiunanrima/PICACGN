package com.picacomic.fregata.utils.views;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picacomic.fregata.c.b;
import com.picacomic.fregata.objects.LatestApplicationObject;
import com.picacomic.fregata.objects.UserProfileObject;
import com.picacomic.fregata.objects.responses.ChatroomBlacklistObject;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.g;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
/* loaded from: picacg_1.jar:com/picacomic/fregata/utils/views/AlertDialogCenter.class */
public class AlertDialogCenter {
    public static final String TAG = "AlertDialogCenter";

    public static void accountNotActivated(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689527);
    }

    public static void ageNotEnough(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689584);
    }

    public static void birthday(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689583);
    }

    public static void cannotComment(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689578);
    }

    public static void cannotStartWithPica(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689543);
    }

    public static void chatroomRules(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689545, 2131689544);
    }

    public static void commentOptions(final Context context, final View.OnClickListener onClickListener) {
        new AlertDialog.Builder(context, 2131755210).setTitle(2131689762).setSingleChoiceItems(2130903044, -1, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (i == 0) {
                    AlertDialogCenter.reportComment(context, onClickListener);
                }
            }
        }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    public static void commentOptionsAdvance(Context context, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context, 2131755210).setTitle(2131689762).setSingleChoiceItems(2130903045, -1, onClickListener).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    public static void continueDownloadComic(Context context, View.OnClickListener onClickListener) {
        showCustomAlertDialog(context, 2131230973, 2131689556, 2131689555, onClickListener, (View.OnClickListener) null);
    }

    public static void downloadNotReady(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689563);
    }

    public static void emailExist(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689530);
    }

    public static void emailLength(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689567);
    }

    public static void emptyComment(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689566);
    }

    public static void fakeEmail(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689570);
    }

    public static void forgotPasswordSuccess(Context context) {
        showCustomAlertDialog(context, 2131230975, 2131689571);
    }

    public static void generalError(Context context) {
        showCustomAlertDialog(context, 2131230979, 2131689572);
    }

    public static void generalError(Context context, String str, String str2) {
        showCustomAlertDialog(context, 2131230979, str, str2);
    }

    public static void giftNotReady(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689573);
    }

    public static void hideComment(Context context, View.OnClickListener onClickListener) {
        showCustomAlertDialog(context, 2131230947, 2131689575, 2131689574, onClickListener, (View.OnClickListener) null);
    }

    public static void invalidEmailOrPassword(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689576);
    }

    public static void leavePica(Context context, View.OnClickListener onClickListener) {
        showCustomAlertDialog(context, 2131230957, -1, 2131689577, onClickListener, (View.OnClickListener) null);
    }

    public static void levelUp(Context context) {
        showCustomAlertDialog(context, 2131230975, 2131689579);
    }

    public static void notFound(Context context) {
        showCustomAlertDialog(context, 2131230946, 2131689585);
    }

    public static void notSupportEmail(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689587);
    }

    public static void passwordLength(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689569);
    }

    public static void passwordNotMatch(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689586);
    }

    public static void postCommentTooFrequent(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689590);
    }

    public static void postReplyTooFrequent(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689591);
    }

    public static void punchedIn(Context context) {
        showCustomAlertDialog(context, 2131230975, -1, 2131689592);
    }

    public static void reportComment(Context context, View.OnClickListener onClickListener) {
        showCustomAlertDialog(context, 2131230947, 2131689601, 2131689600, onClickListener, (View.OnClickListener) null);
    }

    public static void resendActivation(Context context) {
        showCustomAlertDialog(context, 2131230975, 2131689603);
    }

    public static void showAnnouncementAlertDialog(Context context, String str, String str2, String str3, String str4, final View.OnClickListener onClickListener) {
        if (context == null) {
            return;
        }
        final Dialog dialog = new Dialog(context, 2131755200);
        dialog.setContentView(2131427381);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.width = g.as(context);
        final ScrollView scrollView = (ScrollView) dialog.findViewById(2131296791);
        ImageView imageView = (ImageView) dialog.findViewById(2131296597);
        final ImageView imageView2 = (ImageView) dialog.findViewById(2131296596);
        TextView textView = (TextView) dialog.findViewById(2131296926);
        TextView textView2 = (TextView) dialog.findViewById(2131296924);
        TextView textView3 = (TextView) dialog.findViewById(2131296925);
        FrameLayout frameLayout = (FrameLayout) dialog.findViewById(2131296520);
        Button button = (Button) dialog.findViewById(2131296365);
        Button button2 = (Button) dialog.findViewById(2131296364);
        String str5 = TAG;
        Log.e(str5, "Width = " + attributes.width + " Height = " + attributes.height);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.19
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                if (scrollView.getScrollY() <= 200) {
                    imageView2.setAlpha(scrollView.getScrollY() / 200.0f);
                } else {
                    imageView2.setAlpha(1.0f);
                }
            }
        });
        imageView2.setAlpha(0.0f);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.20
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.21
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onClickListener.onClick(view);
                dialog.dismiss();
            }
        });
        if (onClickListener == null) {
            button.setVisibility(8);
            frameLayout.setVisibility(8);
        }
        if (str2 != null) {
            textView.setText(str2 + "");
        } else {
            textView.setVisibility(8);
        }
        if (str3 != null) {
            textView2.setText(str3 + "");
        } else {
            textView2.setVisibility(8);
        }
        if (str4 != null) {
            textView3.setText(g.B(context, str4));
        } else {
            textView3.setVisibility(8);
        }
        if (str == null || str.equalsIgnoreCase("")) {
            imageView.setVisibility(8);
        } else {
            Picasso.with(context).load(str).placeholder(2131231027).into(imageView);
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showChatroomSettingDialog(final Context context, final UserProfileObject userProfileObject, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2) {
        if (context == null) {
            return;
        }
        final Dialog dialog = new Dialog(context, 2131755200);
        dialog.setContentView(2131427383);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.width = g.as(context);
        LinearLayout linearLayout = (LinearLayout) dialog.findViewById(2131296654);
        TextView textView = (TextView) dialog.findViewById(2131296873);
        TextView textView2 = (TextView) dialog.findViewById(2131296874);
        final TextView textView3 = (TextView) dialog.findViewById(2131296875);
        final ToggleButton toggleButton = (ToggleButton) dialog.findViewById(2131297029);
        final ToggleButton toggleButton2 = (ToggleButton) dialog.findViewById(2131297030);
        final ToggleButton toggleButton3 = (ToggleButton) dialog.findViewById(2131297027);
        final ToggleButton toggleButton4 = (ToggleButton) dialog.findViewById(2131297031);
        final ToggleButton toggleButton5 = (ToggleButton) dialog.findViewById(2131297032);
        final ToggleButton toggleButton6 = (ToggleButton) dialog.findViewById(2131297028);
        final EditText editText = (EditText) dialog.findViewById(2131296454);
        final EditText editText2 = (EditText) dialog.findViewById(2131296452);
        final EditText editText3 = (EditText) dialog.findViewById(2131296453);
        final EditText editText4 = (EditText) dialog.findViewById(2131296455);
        TextView textView4 = (TextView) dialog.findViewById(2131296871);
        TextView textView5 = (TextView) dialog.findViewById(2131296872);
        Button button = (Button) dialog.findViewById(2131296371);
        Button button2 = (Button) dialog.findViewById(2131296370);
        b.uP = "chinese";
        if (context != null) {
            toggleButton3.setChecked(e.V(context));
            toggleButton.setChecked(e.T(context));
            toggleButton2.setChecked(e.U(context));
            toggleButton6.setChecked(e.ad(context));
            editText.setText(e.W(context) + "");
            editText4.setText(e.af(context) + "");
            toggleButton4.setChecked(e.Y(context));
            toggleButton5.setChecked(e.Z(context));
            if (e.aa(context).equalsIgnoreCase("cantonese")) {
                textView3.setText(2131689691);
                b.uP = "cantonese";
            } else if (e.aa(context).equalsIgnoreCase("english")) {
                textView3.setText(2131689693);
                b.uP = "english";
            } else if (e.aa(context).equalsIgnoreCase("japanese")) {
                textView3.setText(2131689694);
                b.uP = "japanese";
            } else {
                textView3.setText(2131689692);
                b.uP = "chinese";
            }
        }
        editText3.setText(e.ab(context));
        textView3.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.24
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (b.uP != null) {
                    if (b.uP.equalsIgnoreCase("chinese")) {
                        textView3.setText(2131689691);
                        b.uP = "cantonese";
                    } else if (b.uP.equalsIgnoreCase("cantonese")) {
                        textView3.setText(2131689694);
                        b.uP = "japanese";
                    } else if (b.uP.equalsIgnoreCase("japanese")) {
                        textView3.setText(2131689693);
                        b.uP = "english";
                    } else {
                        textView3.setText(2131689692);
                        b.uP = "chinese";
                    }
                }
            }
        });
        if (userProfileObject != null) {
            linearLayout.setVisibility(8);
            textView.setText(userProfileObject.getName() + "\n" + context.getString(2131689813) + userProfileObject.getLevel() + " (" + userProfileObject.getExp() + "/" + g.Z(userProfileObject.getLevel() + 1) + ")");
            if (userProfileObject.getEmail().endsWith("@picacomic.com")) {
                linearLayout.setVisibility(0);
                editText2.setText(e.X(context) + "");
            }
        } else {
            linearLayout.setVisibility(8);
        }
        if (onClickListener == null) {
            textView2.setVisibility(8);
        } else {
            textView2.setVisibility(0);
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.25
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onClickListener.onClick(view);
                    dialog.dismiss();
                }
            });
        }
        if (e.ae(context) != null) {
            textView4.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.26
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Gson gson = new Gson();
                    String ae = e.ae(context);
                    ArrayList arrayList = ae != null ? (ArrayList) gson.fromJson(ae, new TypeToken<List<ChatroomBlacklistObject>>() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.26.1
                    }.getType()) : new ArrayList();
                    String[] strArr = new String[arrayList.size()];
                    final boolean[] zArr = new boolean[arrayList.size()];
                    for (int i = 0; i < arrayList.size(); i++) {
                        strArr[i] = ((ChatroomBlacklistObject) arrayList.get(i)).getUsername();
                        zArr[i] = false;
                    }
                    new AlertDialog.Builder(context, 2131755210).setTitle(2131689677).setMultiChoiceItems(strArr, zArr, new DialogInterface.OnMultiChoiceClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.26.5
                        @Override // android.content.DialogInterface.OnMultiChoiceClickListener
                        public void onClick(DialogInterface dialogInterface, int i2, boolean z) {
                            zArr[i2] = z;
                        }
                    }).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.26.4
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            Gson gson2 = new Gson();
                            String ae2 = e.ae(context);
                            ArrayList arrayList2 = ae2 != null ? (ArrayList) gson2.fromJson(ae2, new TypeToken<List<ChatroomBlacklistObject>>() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.26.4.1
                            }.getType()) : new ArrayList();
                            if (arrayList2.size() == zArr.length) {
                                for (int length = zArr.length - 1; length >= 0; length--) {
                                    if (zArr[length]) {
                                        arrayList2.remove(length);
                                    }
                                }
                                e.u(context, gson2.toJson(arrayList2));
                            } else {
                                Toast.makeText(context, 2131689679, 1).show();
                            }
                            dialogInterface.dismiss();
                        }
                    }).setNeutralButton(2131689678, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.26.3
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            e.u(context, null);
                            dialogInterface.dismiss();
                        }
                    }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.26.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                }
            });
        } else {
            textView4.setText(2131689680);
        }
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.27
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.28
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i;
                if (context != null) {
                    int i2 = 100;
                    try {
                        int parseInt = Integer.parseInt(editText.getText().toString());
                        i = parseInt;
                        if (parseInt < 1) {
                            i = 1;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        i = 100;
                    }
                    int i3 = 0;
                    try {
                        int parseInt2 = Integer.parseInt(editText4.getText().toString());
                        if (parseInt2 < 0) {
                            i2 = 0;
                        } else if (parseInt2 <= 100) {
                            i2 = parseInt2;
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        i2 = 70;
                    }
                    try {
                        int parseInt3 = Integer.parseInt(editText2.getText().toString());
                        if (parseInt3 >= 0) {
                            i3 = parseInt3;
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        i3 = 30;
                    }
                    e.h(context, toggleButton.isChecked());
                    e.i(context, toggleButton2.isChecked());
                    e.j(context, toggleButton3.isChecked());
                    e.m(context, toggleButton6.isChecked());
                    e.e(context, i);
                    e.g(context, i2);
                    e.k(context, toggleButton4.isChecked());
                    e.l(context, toggleButton5.isChecked());
                    if (b.uP != null) {
                        e.r(context, b.uP);
                    }
                    if (userProfileObject != null && userProfileObject.getEmail().endsWith("@picacomic.com")) {
                        e.f(context, i3);
                        if (editText3.getText().toString().startsWith("http://") || editText3.getText().toString().startsWith("https://")) {
                            if (userProfileObject.getEmail().startsWith("ruff") || userProfileObject.getEmail().startsWith("knight-ace") || userProfileObject.getEmail().startsWith("leader") || userProfileObject.getEmail().startsWith("server") || userProfileObject.getEmail().startsWith("kagu")) {
                                e.s(context, editText3.getText().toString());
                            }
                        } else if (!editText3.getText().toString().startsWith("改名")) {
                            e.s(context, "");
                        } else if (userProfileObject.getEmail().startsWith("ruff") || userProfileObject.getEmail().startsWith("leader") || userProfileObject.getEmail().startsWith("server") || userProfileObject.getEmail().startsWith("kagu")) {
                            e.t(context, editText3.getText().toString().replace("改名", ""));
                        }
                    }
                }
                onClickListener2.onClick(view);
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.show();
    }

    public static void showCustomAlertDialog(Context context, int i) {
        showCustomAlertDialog(context, -1, -1, i);
    }

    public static void showCustomAlertDialog(Context context, int i, int i2) {
        showCustomAlertDialog(context, i, -1, i2);
    }

    public static void showCustomAlertDialog(Context context, int i, int i2, int i3) {
        showCustomAlertDialog(context, i, i2, i3, (View.OnClickListener) null, (View.OnClickListener) null);
    }

    public static void showCustomAlertDialog(Context context, int i, int i2, int i3, final View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (context == null) {
            return;
        }
        final Dialog dialog = new Dialog(context, 2131755200);
        dialog.setContentView(2131427384);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 48;
        attributes.width = g.as(context) - 50;
        final ImageView imageView = (ImageView) dialog.findViewById(2131296598);
        TextView textView = (TextView) dialog.findViewById(2131296929);
        TextView textView2 = (TextView) dialog.findViewById(2131296928);
        FrameLayout frameLayout = (FrameLayout) dialog.findViewById(2131296523);
        FrameLayout frameLayout2 = (FrameLayout) dialog.findViewById(2131296522);
        Button button = (Button) dialog.findViewById(2131296367);
        Button button2 = (Button) dialog.findViewById(2131296366);
        imageView.getLayoutParams().width = (g.as(context) * 3) / 5;
        frameLayout.getLayoutParams().height = g.as(context) / 3;
        if (i2 == -1) {
            textView.setVisibility(8);
        } else {
            textView.setText(i2);
        }
        if (i3 == -1) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(i3);
            textView2.append("\n");
        }
        Callback callback = new Callback() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.11
            @Override // com.squareup.picasso.Callback
            public void onError() {
            }

            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f, 1, 0.0f, 1, 1.0f);
                scaleAnimation.setInterpolator(new AccelerateInterpolator());
                scaleAnimation.setDuration(300L);
                imageView.startAnimation(scaleAnimation);
            }
        };
        if (i == -1) {
            if (e.x(context)) {
                Picasso.with(context).load(2131230979).into(imageView);
            } else {
                Picasso.with(context).load(2131230979).into(imageView, callback);
            }
        } else if (e.x(context)) {
            Picasso.with(context).load(i).into(imageView);
        } else {
            Picasso.with(context).load(i).into(imageView, callback);
        }
        String str = TAG;
        Log.e(str, "Width = " + attributes.width + " Height = " + attributes.height);
        if (onClickListener == null && onClickListener2 == null) {
            frameLayout2.setVisibility(8);
            button2.setVisibility(8);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.12
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        } else if (onClickListener == null || onClickListener2 != null) {
            button.setOnClickListener(onClickListener);
            button2.setOnClickListener(onClickListener2);
        } else {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.13
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onClickListener.onClick(view);
                    dialog.dismiss();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.14
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showCustomAlertDialog(Context context, int i, String str, String str2) {
        showCustomAlertDialog(context, i, str, str2, (View.OnClickListener) null, (View.OnClickListener) null);
    }

    public static void showCustomAlertDialog(Context context, int i, String str, String str2, final View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        if (context == null) {
            return;
        }
        final Dialog dialog = new Dialog(context, 2131755200);
        dialog.setContentView(2131427384);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 48;
        attributes.width = g.as(context) - 50;
        final ImageView imageView = (ImageView) dialog.findViewById(2131296598);
        TextView textView = (TextView) dialog.findViewById(2131296929);
        TextView textView2 = (TextView) dialog.findViewById(2131296928);
        FrameLayout frameLayout = (FrameLayout) dialog.findViewById(2131296523);
        FrameLayout frameLayout2 = (FrameLayout) dialog.findViewById(2131296522);
        Button button = (Button) dialog.findViewById(2131296367);
        Button button2 = (Button) dialog.findViewById(2131296366);
        imageView.getLayoutParams().width = (g.as(context) * 3) / 5;
        frameLayout.getLayoutParams().height = g.as(context) / 3;
        if (str == null) {
            textView.setVisibility(8);
        } else {
            textView.setText(str + "");
        }
        if (str2 == null) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(str2 + "");
            textView2.append("\n");
        }
        Callback callback = new Callback() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.7
            @Override // com.squareup.picasso.Callback
            public void onError() {
            }

            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f, 1, 0.0f, 1, 1.0f);
                scaleAnimation.setInterpolator(new AccelerateInterpolator());
                scaleAnimation.setDuration(300L);
                imageView.startAnimation(scaleAnimation);
            }
        };
        if (i == -1) {
            if (e.x(context)) {
                Picasso.with(context).load(2131230979).into(imageView);
            } else {
                Picasso.with(context).load(2131230979).into(imageView, callback);
            }
        } else if (e.x(context)) {
            Picasso.with(context).load(i).into(imageView);
        } else {
            Picasso.with(context).load(i).into(imageView, callback);
        }
        String str3 = TAG;
        Log.e(str3, "Width = " + attributes.width + " Height = " + attributes.height);
        if (onClickListener == null && onClickListener2 == null) {
            frameLayout2.setVisibility(8);
            button2.setVisibility(8);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        } else if (onClickListener == null || onClickListener2 != null) {
            button.setOnClickListener(onClickListener);
            button2.setOnClickListener(onClickListener2);
        } else {
            button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onClickListener.onClick(view);
                    dialog.dismiss();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.10
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showFaqAlertDialog(Context context, String str, final View.OnClickListener onClickListener) {
        if (context == null) {
            return;
        }
        final Dialog dialog = new Dialog(context, 2131755200);
        dialog.setContentView(2131427387);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.width = g.as(context);
        FrameLayout frameLayout = (FrameLayout) dialog.findViewById(2131296524);
        Button button = (Button) dialog.findViewById(2131296369);
        Button button2 = (Button) dialog.findViewById(2131296368);
        ((WebView) dialog.findViewById(2131297062)).loadUrl(str);
        String str2 = TAG;
        Log.e(str2, "Width = " + attributes.width + " Height = " + attributes.height);
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.22
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                onClickListener.onClick(view);
                dialog.dismiss();
            }
        });
        if (onClickListener == null) {
            button.setVisibility(8);
            frameLayout.setVisibility(8);
        }
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void showUpdateApkAlertDialog(final Context context, final LatestApplicationObject latestApplicationObject, final boolean z) {
        if (context == null) {
            return;
        }
        final Dialog dialog = new Dialog(context, 2131755200);
        dialog.setContentView(2131427392);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 48;
        attributes.width = g.as(context) - 50;
        final ImageView imageView = (ImageView) dialog.findViewById(2131296599);
        TextView textView = (TextView) dialog.findViewById(2131296934);
        TextView textView2 = (TextView) dialog.findViewById(2131296933);
        FrameLayout frameLayout = (FrameLayout) dialog.findViewById(2131296525);
        Button button = (Button) dialog.findViewById(2131296374);
        Button button2 = (Button) dialog.findViewById(2131296373);
        Button button3 = (Button) dialog.findViewById(2131296372);
        imageView.getLayoutParams().width = (g.as(context) * 3) / 5;
        frameLayout.getLayoutParams().height = g.as(context) / 3;
        if (latestApplicationObject.getVersion() == null) {
            textView.setVisibility(8);
        } else {
            textView.setText(context.getResources().getString(2131689536) + latestApplicationObject.getVersion());
        }
        if (latestApplicationObject.getUpdateContent() == null) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(g.B(context, latestApplicationObject.getCreatedAt()) + "\n\n" + latestApplicationObject.getUpdateContent());
            textView2.append("\n");
        }
        Callback callback = new Callback() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.15
            @Override // com.squareup.picasso.Callback
            public void onError() {
            }

            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 0.0f, 1.0f, 1, 0.0f, 1, 1.0f);
                scaleAnimation.setInterpolator(new AccelerateInterpolator());
                scaleAnimation.setDuration(300L);
                imageView.startAnimation(scaleAnimation);
            }
        };
        if (e.x(context)) {
            Picasso.with(context).load(2131230947).into(imageView);
        } else {
            Picasso.with(context).load(2131230947).into(imageView, callback);
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.16
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LatestApplicationObject.this.getApk() == null) {
                    g.A(context, "https://picacomic.com");
                    return;
                }
                g.A(context, g.aC(LatestApplicationObject.this.getApk().getFileServer()) + LatestApplicationObject.this.getApk().getPath());
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.17
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LatestApplicationObject.this.getDownloadUrl() != null) {
                    g.A(context, LatestApplicationObject.this.getDownloadUrl());
                } else {
                    g.A(context, "https://picacomic.com");
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.18
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (z) {
                    Toast.makeText(context, 2131689533, 0).show();
                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
    }

    public static void sortingAdvancedCategoriesOptions(Context context, String[] strArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context, 2131755210).setTitle(2131689970).setMultiChoiceItems(strArr, zArr, onMultiChoiceClickListener).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(2131689846, onClickListener).show();
    }

    public static void sortingAdvancedOptions(Context context, int i, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context, 2131755210).setTitle(2131689928).setSingleChoiceItems(2130903054, i, onClickListener).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    public static void sortingFavouriteOptions(Context context, int i, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(context, 2131755210).setTitle(2131689928).setSingleChoiceItems(2130903055, i, onClickListener).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.utils.views.AlertDialogCenter.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    public static void timeIsNotSynchronize(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689608);
    }

    public static void tooManyRequests(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689610);
    }

    public static void underReview(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689611);
    }

    public static void userNotActivatedCannotCommentOrChat(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689612);
    }

    public static void usernameExist(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689531);
    }

    public static void usernameLength(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689568);
    }

    public static void usingMobileNetwork(Context context) {
        showCustomAlertDialog(context, 2131230947, 2131689613);
    }

    public static void validation(Context context) {
        showCustomAlertDialog(context, 2131230973, 2131689614);
    }

    public static void versionNotSupport(Context context) {
        showCustomAlertDialog(context, 2131230957, 2131689616, 2131689615);
    }
}
