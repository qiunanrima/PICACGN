package android.support.v4.print;

import android.content.Context;
import android.support.annotation.RequiresApi;
@RequiresApi(24)
/* loaded from: picacg_1.jar:android/support/v4/print/PrintHelperApi24.class */
class PrintHelperApi24 extends PrintHelperApi23 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PrintHelperApi24(Context context) {
        super(context);
        this.mIsMinMarginsHandlingCorrect = true;
        this.mPrintActivityRespectsOrientation = true;
    }
}
