package android.support.v7.widget;

import android.annotation.TargetApi;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.BuildCompat;
import android.view.View;
/* loaded from: picacg_1.jar:android/support/v7/widget/TooltipCompat.class */
public class TooltipCompat {
    private static final ViewCompatImpl IMPL;

    @TargetApi(26)
    /* loaded from: picacg_1.jar:android/support/v7/widget/TooltipCompat$Api26ViewCompatImpl.class */
    private static class Api26ViewCompatImpl implements ViewCompatImpl {
        private Api26ViewCompatImpl() {
        }

        @Override // android.support.v7.widget.TooltipCompat.ViewCompatImpl
        public void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
            view.setTooltipText(charSequence);
        }
    }

    /* loaded from: picacg_1.jar:android/support/v7/widget/TooltipCompat$BaseViewCompatImpl.class */
    private static class BaseViewCompatImpl implements ViewCompatImpl {
        private BaseViewCompatImpl() {
        }

        @Override // android.support.v7.widget.TooltipCompat.ViewCompatImpl
        public void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
            TooltipCompatHandler.setTooltipText(view, charSequence);
        }
    }

    /* loaded from: picacg_1.jar:android/support/v7/widget/TooltipCompat$ViewCompatImpl.class */
    private interface ViewCompatImpl {
        void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence);
    }

    static {
        if (BuildCompat.isAtLeastO()) {
            IMPL = new Api26ViewCompatImpl();
        } else {
            IMPL = new BaseViewCompatImpl();
        }
    }

    private TooltipCompat() {
    }

    public static void setTooltipText(@NonNull View view, @Nullable CharSequence charSequence) {
        IMPL.setTooltipText(view, charSequence);
    }
}
