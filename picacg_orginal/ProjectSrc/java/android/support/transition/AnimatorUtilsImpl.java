package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
/* loaded from: picacg_1.jar:android/support/transition/AnimatorUtilsImpl.class */
interface AnimatorUtilsImpl {
    void addPauseListener(@NonNull Animator animator, @NonNull AnimatorListenerAdapter animatorListenerAdapter);

    void pause(@NonNull Animator animator);

    void resume(@NonNull Animator animator);
}
