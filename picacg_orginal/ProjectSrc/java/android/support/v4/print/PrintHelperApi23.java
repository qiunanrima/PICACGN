package android.support.v4.print;

import android.content.Context;
import android.print.PrintAttributes;
import android.support.annotation.RequiresApi;
@RequiresApi(23)
/* loaded from: picacg_1.jar:android/support/v4/print/PrintHelperApi23.class */
class PrintHelperApi23 extends PrintHelperApi20 {
    /* JADX INFO: Access modifiers changed from: package-private */
    public PrintHelperApi23(Context context) {
        super(context);
        this.mIsMinMarginsHandlingCorrect = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.print.PrintHelperKitkat
    public PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes) {
        PrintAttributes.Builder copyAttributes = super.copyAttributes(printAttributes);
        if (printAttributes.getDuplexMode() != 0) {
            copyAttributes.setDuplexMode(printAttributes.getDuplexMode());
        }
        return copyAttributes;
    }
}
