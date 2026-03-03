package android.support.v4.os;

import android.os.AsyncTask;
@Deprecated
/* loaded from: picacg_1.jar:android/support/v4/os/AsyncTaskCompat.class */
public final class AsyncTaskCompat {
    private AsyncTaskCompat() {
    }

    @Deprecated
    public static <Params, Progress, Result> AsyncTask<Params, Progress, Result> executeParallel(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        if (asyncTask != null) {
            asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, paramsArr);
            return asyncTask;
        }
        throw new IllegalArgumentException("task can not be null");
    }
}
