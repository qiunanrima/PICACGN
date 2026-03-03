package android.support.v7.app;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.Window;
/* JADX INFO: Access modifiers changed from: package-private */
@RequiresApi(26)
/* loaded from: picacg_1.jar:android/support/v7/app/AppCompatDelegateImplO.class */
public class AppCompatDelegateImplO extends AppCompatDelegateImplN {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AppCompatDelegateImplO(Context context, Window window, AppCompatCallback appCompatCallback) {
        super(context, window, appCompatCallback);
    }

    @Override // android.support.v7.app.AppCompatDelegateImplBase, android.support.v7.app.AppCompatDelegate
    public boolean checkActionBarFocusKey(KeyEvent keyEvent) {
        return false;
    }
}
