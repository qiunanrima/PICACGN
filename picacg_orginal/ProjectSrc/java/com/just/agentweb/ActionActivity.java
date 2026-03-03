package com.just.agentweb;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: picacg_1.jar:com/just/agentweb/ActionActivity.class */
public final class ActionActivity extends Activity {
    private static final String TAG = "ActionActivity";
    private static c cs;
    private static b ct;
    private static a cu;
    private Action cv;
    private Uri mUri;

    /* loaded from: picacg_1.jar:com/just/agentweb/ActionActivity$a.class */
    public interface a {
        void a(int i, int i2, Intent intent);
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/ActionActivity$b.class */
    public interface b {
        void a(@NonNull String[] strArr, @NonNull int[] iArr, Bundle bundle);
    }

    /* loaded from: picacg_1.jar:com/just/agentweb/ActionActivity$c.class */
    public interface c {
        void a(boolean z, Bundle bundle);
    }

    private void a(int i, Intent intent) {
        if (cu != null) {
            cu.a(596, i, intent);
            cu = null;
        }
        finish();
    }

    public static void a(Activity activity, Action action) {
        Intent intent = new Intent(activity, ActionActivity.class);
        intent.putExtra("KEY_ACTION", action);
        activity.startActivity(intent);
    }

    private void a(Action action) {
        if (cu == null) {
            finish();
        }
        u();
    }

    public static void a(b bVar) {
        ct = bVar;
    }

    private void b(Action action) {
        ArrayList<String> r = action.r();
        if (h.a(r)) {
            ct = null;
            cs = null;
            finish();
            return;
        }
        boolean z = false;
        if (cs == null) {
            if (ct != null) {
                requestPermissions((String[]) r.toArray(new String[0]), 1);
                return;
            }
            return;
        }
        Iterator<String> it = r.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            boolean shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale(it.next());
            z = shouldShowRequestPermissionRationale;
            if (shouldShowRequestPermissionRationale) {
                z = shouldShowRequestPermissionRationale;
                break;
            }
        }
        cs.a(z, new Bundle());
        cs = null;
        finish();
    }

    private void t() {
        cu = null;
        ct = null;
        cs = null;
    }

    private void u() {
        try {
            if (cu == null) {
                finish();
                return;
            }
            Intent intent = (Intent) getIntent().getParcelableExtra("KEY_FILE_CHOOSER_INTENT");
            if (intent == null) {
                t();
            } else {
                startActivityForResult(intent, 596);
            }
        } catch (Throwable th) {
            ag.c(TAG, "找不到文件选择器");
            a(-1, (Intent) null);
            if (ag.ap()) {
                th.printStackTrace();
            }
        }
    }

    private void v() {
        try {
            if (cu == null) {
                finish();
            }
            File i = h.i(this);
            if (i == null) {
                cu.a(596, 0, null);
                cu = null;
                finish();
            }
            Intent d = h.d(this, i);
            this.mUri = (Uri) d.getParcelableExtra("output");
            startActivityForResult(d, 596);
        } catch (Throwable th) {
            ag.d(TAG, "找不到系统相机");
            if (cu != null) {
                cu.a(596, 0, null);
            }
            cu = null;
            if (ag.ap()) {
                th.printStackTrace();
            }
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 596) {
            if (this.mUri != null) {
                intent = new Intent().putExtra("KEY_URI", this.mUri);
            }
            a(i2, intent);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            String str = TAG;
            ag.c(str, "savedInstanceState:" + bundle);
            return;
        }
        this.cv = (Action) getIntent().getParcelableExtra("KEY_ACTION");
        if (this.cv == null) {
            t();
            finish();
        } else if (this.cv.getAction() == 1) {
            b(this.cv);
        } else if (this.cv.getAction() == 3) {
            v();
        } else {
            a(this.cv);
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (ct != null) {
            Bundle bundle = new Bundle();
            bundle.putInt("KEY_FROM_INTENTION", this.cv.s());
            ct.a(strArr, iArr, bundle);
        }
        ct = null;
        finish();
    }
}
