package com.picacomic.fregata.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.R;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import butterknife.BindView;
import com.picacomic.fregata.activities.BaseActivity;
import com.picacomic.fregata.activities.LoginActivity;
import com.picacomic.fregata.activities.MainActivity;
import com.picacomic.fregata.services.ChatroomService;
import com.picacomic.fregata.utils.e;
import com.picacomic.fregata.utils.g;
import com.picacomic.fregata.utils.views.AlertDialogCenter;
import java.io.File;
import java.text.DecimalFormat;
/* loaded from: picacg_1.jar:com/picacomic/fregata/fragments/SettingFragment.class */
public class SettingFragment extends BaseFragment {
    public static final String TAG = "SettingFragment";
    @BindView(2131296393)
    Button button_logout;
    int hM;
    String[] rA;
    int rB;
    String[] rC;
    int rD;
    String[] rw;
    int rx;
    String[] ry;
    int rz;
    @BindView(2131296992)
    TextView textView_apkVersion;
    @BindView(2131296993)
    TextView textView_apkVersionTitle;
    @BindView(2131296994)
    TextView textView_autoPagingInterval;
    @BindView(2131296995)
    TextView textView_cache;
    @BindView(2131296996)
    TextView textView_cacheTitle;
    @BindView(2131296997)
    TextView textView_chatroomService;
    @BindView(2131296998)
    TextView textView_continueDownloadComic;
    @BindView(2131297000)
    TextView textView_faq;
    @BindView(2131297001)
    TextView textView_imageQuality;
    @BindView(2131297002)
    TextView textView_password;
    @BindView(2131297004)
    TextView textView_pin;
    @BindView(2131297005)
    TextView textView_pinTitle;
    @BindView(2131297006)
    TextView textView_screenOrientation;
    @BindView(2131297007)
    TextView textView_scrollDirection;
    @BindView(2131297008)
    TextView textView_themeColor;
    @BindView(2131297034)
    ToggleButton toggleButton_nightMode;
    @BindView(2131297035)
    ToggleButton toggleButton_performanceEnhancement;
    @BindView(2131297033)
    ToggleButton toggleButton_testingVersion;
    @BindView(2131297036)
    ToggleButton toggleButton_volumePagingControl;
    @BindView(2131297037)
    Toolbar toolbar;

    public static String d(long j) {
        if (j <= 0) {
            return "0 Bytes";
        }
        double d = j;
        int log10 = (int) (Math.log10(d) / Math.log10(1024.0d));
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.#");
        double pow = Math.pow(1024.0d, log10);
        Double.isNaN(d);
        sb.append(decimalFormat.format(d / pow));
        sb.append(" ");
        sb.append(new String[]{"Bytes", "kB", "MB", "GB", "TB"}[log10]);
        return sb.toString();
    }

    private long dK() {
        return f(getActivity().getCacheDir()) + 0 + f(getActivity().getExternalCacheDir());
    }

    public int F(boolean z) {
        return z ? 0 : 1;
    }

    public boolean ab(int i) {
        return i == 0;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bH() {
        super.bH();
        this.toolbar.setTitle(2131689985);
        if (getActivity() != null && (getActivity() instanceof MainActivity)) {
            ((MainActivity) getActivity()).t(0);
        }
        TextView textView = this.textView_apkVersionTitle;
        textView.setText(getString(2131689923) + " (" + getString(2131689625) + ")");
        this.textView_screenOrientation.setText(this.rw[this.rx]);
        this.textView_scrollDirection.setText(this.ry[this.rz]);
        this.textView_imageQuality.setText(this.rA[this.rB]);
        this.textView_themeColor.setText(this.rC[this.rD]);
        this.toggleButton_nightMode.setChecked(e.L(getActivity()));
        this.toggleButton_volumePagingControl.setChecked(e.Q(getActivity()));
        this.toggleButton_performanceEnhancement.setChecked(e.x(getActivity()));
        this.toggleButton_testingVersion.setChecked(e.w(getActivity()));
        TextView textView2 = this.textView_autoPagingInterval;
        textView2.setText(String.format("%.1f", Float.valueOf(this.hM / 1000.0f)) + " " + getString(2131689894));
        TextView textView3 = this.textView_cacheTitle;
        textView3.setText(getString(2131689896) + " (~" + d(dK()) + ")");
        String y = e.y(getContext());
        if (y == null || y.length() <= 0) {
            this.textView_pin.setText(2131689916);
            this.textView_pinTitle.setText(2131689918);
            return;
        }
        this.textView_pin.setText(2131689917);
        this.textView_pinTitle.setText(2131689920);
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void bI() {
        super.bI();
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void ca() {
        super.ca();
        this.textView_screenOrientation.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new AlertDialog.Builder(SettingFragment.this.getActivity(), 2131755210).setTitle(2131689903).setSingleChoiceItems(2130903051, SettingFragment.this.rx, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.1.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        SettingFragment.this.rx = i;
                        SettingFragment.this.textView_screenOrientation.setText(SettingFragment.this.rw[SettingFragment.this.rx]);
                        e.e(SettingFragment.this.getActivity(), SettingFragment.this.ab(SettingFragment.this.rx));
                    }
                }).show();
            }
        });
        this.textView_scrollDirection.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new AlertDialog.Builder(SettingFragment.this.getActivity(), 2131755210).setTitle(2131689904).setSingleChoiceItems(2130903052, SettingFragment.this.rz, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.10.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        SettingFragment.this.rz = i;
                        SettingFragment.this.textView_scrollDirection.setText(SettingFragment.this.ry[SettingFragment.this.rz]);
                        e.f(SettingFragment.this.getActivity(), SettingFragment.this.ab(SettingFragment.this.rz));
                    }
                }).show();
            }
        });
        this.textView_imageQuality.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                new AlertDialog.Builder(SettingFragment.this.getActivity(), 2131755210).setTitle(2131689901).setSingleChoiceItems(2130903049, SettingFragment.this.rB, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.11.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        SettingFragment.this.rB = i;
                        SettingFragment.this.textView_imageQuality.setText(SettingFragment.this.rA[SettingFragment.this.rB]);
                        e.c(SettingFragment.this.getActivity(), SettingFragment.this.rB);
                    }
                }).show();
            }
        });
        this.textView_themeColor.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= 21) {
                    new AlertDialog.Builder(SettingFragment.this.getActivity(), 2131755210).setTitle(2131689922).setSingleChoiceItems(2130903053, SettingFragment.this.rD, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.12.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (SettingFragment.this.rD != i) {
                                SettingFragment.this.rD = i;
                                SettingFragment.this.textView_themeColor.setText(SettingFragment.this.rC[SettingFragment.this.rD]);
                                e.h(SettingFragment.this.getActivity(), SettingFragment.this.rD);
                                SettingFragment.this.getActivity().startActivity(new Intent(SettingFragment.this.getActivity(), MainActivity.class));
                                SettingFragment.this.getActivity().finish();
                            }
                        }
                    }).show();
                } else {
                    AlertDialogCenter.versionNotSupport(SettingFragment.this.getContext());
                }
            }
        });
        this.textView_autoPagingInterval.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                View inflate = ((LayoutInflater) SettingFragment.this.getActivity().getSystemService("layout_inflater")).inflate(2131427382, (ViewGroup) view.getParent(), false);
                final TextView textView = (TextView) inflate.findViewById(2131296999);
                SeekBar seekBar = (SeekBar) inflate.findViewById(2131296807);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.13.1
                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onProgressChanged(SeekBar seekBar2, int i, boolean z) {
                        SettingFragment.this.hM = (i * 100) + 1000;
                        float f = SettingFragment.this.hM / 1000.0f;
                        TextView textView2 = textView;
                        textView2.setText(SettingFragment.this.getResources().getString(2131689733) + " 【 " + String.format("%.1f", Float.valueOf(f)) + SettingFragment.this.getResources().getString(2131689894) + " 】");
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStartTrackingTouch(SeekBar seekBar2) {
                    }

                    @Override // android.widget.SeekBar.OnSeekBarChangeListener
                    public void onStopTrackingTouch(SeekBar seekBar2) {
                    }
                });
                if (SettingFragment.this.hM - 1000 < 0) {
                    seekBar.setProgress(0);
                } else {
                    seekBar.setProgress((SettingFragment.this.hM - 1000) / 100);
                }
                new AlertDialog.Builder(SettingFragment.this.getActivity(), 2131755210).setTitle(2131689899).setView(inflate).setPositiveButton(2131689846, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.13.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        e.b((Context) SettingFragment.this.getActivity(), SettingFragment.this.hM);
                        TextView textView2 = SettingFragment.this.textView_autoPagingInterval;
                        textView2.setText(String.format("%.1f", Float.valueOf(SettingFragment.this.hM / 1000.0f)) + " " + SettingFragment.this.getString(2131689894));
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton(2131689630, new DialogInterface.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.13.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SettingFragment.this.hM = e.O(SettingFragment.this.getActivity());
                        dialogInterface.dismiss();
                    }
                }).show();
            }
        });
        this.textView_continueDownloadComic.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                g.av(SettingFragment.this.getContext());
            }
        });
        this.toggleButton_nightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.15
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SettingFragment.this.toggleButton_nightMode.setChecked(true);
                    e.d((Context) SettingFragment.this.getActivity(), true);
                    return;
                }
                SettingFragment.this.toggleButton_nightMode.setChecked(false);
                e.d((Context) SettingFragment.this.getActivity(), false);
            }
        });
        this.toggleButton_volumePagingControl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.16
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SettingFragment.this.toggleButton_volumePagingControl.setChecked(true);
                    e.g((Context) SettingFragment.this.getActivity(), true);
                    return;
                }
                SettingFragment.this.toggleButton_volumePagingControl.setChecked(false);
                e.g((Context) SettingFragment.this.getActivity(), false);
            }
        });
        this.toggleButton_performanceEnhancement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.17
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SettingFragment.this.toggleButton_performanceEnhancement.setChecked(true);
                    e.b((Context) SettingFragment.this.getActivity(), true);
                    return;
                }
                SettingFragment.this.toggleButton_performanceEnhancement.setChecked(false);
                e.b((Context) SettingFragment.this.getActivity(), false);
            }
        });
        this.toggleButton_testingVersion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SettingFragment.this.toggleButton_testingVersion.setChecked(true);
                    e.a((Context) SettingFragment.this.getActivity(), true);
                    return;
                }
                SettingFragment.this.toggleButton_testingVersion.setChecked(false);
                e.a((Context) SettingFragment.this.getActivity(), false);
            }
        });
        this.button_logout.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                e.h(SettingFragment.this.getActivity(), "");
                e.f(SettingFragment.this.getActivity(), "");
                e.a((Context) SettingFragment.this.getActivity(), -1);
                e.i(SettingFragment.this.getActivity(), "");
                SettingFragment.this.getActivity().startActivity(new Intent(SettingFragment.this.getActivity(), LoginActivity.class));
                SettingFragment.this.getActivity().finish();
            }
        });
        this.textView_chatroomService.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (e.B(SettingFragment.this.getContext()).equalsIgnoreCase("")) {
                    Toast.makeText(SettingFragment.this.getContext(), 2131689664, 0).show();
                } else if (Build.VERSION.SDK_INT < 23) {
                    Intent intent = new Intent(SettingFragment.this.getContext(), ChatroomService.class);
                    intent.setAction("com.picacomic.fregata.interfaces.foregroundservice.action.startforeground");
                    SettingFragment.this.getContext().startService(intent);
                } else if (((BaseActivity) SettingFragment.this.getActivity()).bE()) {
                    Intent intent2 = new Intent(SettingFragment.this.getContext(), ChatroomService.class);
                    intent2.setAction("com.picacomic.fregata.interfaces.foregroundservice.action.startforeground");
                    SettingFragment.this.getContext().startService(intent2);
                }
            }
        });
        this.textView_apkVersion.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new ApkVersionListFragment(), ApkVersionListFragment.TAG).addToBackStack(ApkVersionListFragment.TAG).commit();
            }
        });
        this.textView_faq.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlertDialogCenter.showFaqAlertDialog(SettingFragment.this.getContext(), "https://www.picacomic.com/faq", null);
            }
        });
        this.textView_cache.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", SettingFragment.this.getActivity().getPackageName(), null));
                SettingFragment.this.startActivity(intent);
            }
        });
        this.textView_pin.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new ChangePinFragment(), ChangePinFragment.TAG).addToBackStack(ChangePinFragment.TAG).commit();
            }
        });
        this.textView_password.setOnClickListener(new View.OnClickListener() { // from class: com.picacomic.fregata.fragments.SettingFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingFragment.this.getFragmentManager().beginTransaction().setCustomAnimations(2130772004, 2130772005, 2130772006, 2130772007).replace(R.id.container, new ChangePasswordFragment(), ChangePasswordFragment.TAG).addToBackStack(ChangePasswordFragment.TAG).commit();
            }
        });
    }

    public long f(File file) {
        long j;
        File[] listFiles = file.listFiles();
        int length = listFiles.length;
        long j2 = 0;
        int i = 0;
        while (i < length) {
            File file2 = listFiles[i];
            if (file2 == null || !file2.isDirectory()) {
                j = j2;
                if (file2 != null) {
                    j = j2;
                    if (file2.isFile()) {
                        j = j2 + file2.length();
                    }
                }
            } else {
                j = j2 + f(file2);
            }
            i++;
            j2 = j;
        }
        return j2;
    }

    @Override // com.picacomic.fregata.fragments.BaseFragment
    public void init() {
        super.init();
        this.rw = getResources().getStringArray(2130903051);
        this.ry = getResources().getStringArray(2130903052);
        this.rA = getResources().getStringArray(2130903049);
        this.rC = getResources().getStringArray(2130903053);
        this.rx = F(e.M(getActivity()));
        this.rz = F(e.N(getActivity()));
        this.rB = e.R(getActivity());
        this.rD = e.al(getActivity());
        this.hM = e.O(getActivity());
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2131427433, viewGroup, false);
        a(inflate);
        return inflate;
    }
}
