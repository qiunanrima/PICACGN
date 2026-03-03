package android.support.v4.content;

import android.os.AsyncTask;
import java.util.concurrent.Executor;
@Deprecated
/* loaded from: picacg_1.jar:android/support/v4/content/ParallelExecutorCompat.class */
public final class ParallelExecutorCompat {
    private ParallelExecutorCompat() {
    }

    @Deprecated
    public static Executor getParallelExecutor() {
        return AsyncTask.THREAD_POOL_EXECUTOR;
    }
}
