package android.support.design.widget;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
/* loaded from: picacg_1.jar:android/support/design/widget/BottomSheetDialogFragment.class */
public class BottomSheetDialogFragment extends AppCompatDialogFragment {
    @Override // android.support.v7.app.AppCompatDialogFragment, android.support.v4.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new BottomSheetDialog(getContext(), getTheme());
    }
}
