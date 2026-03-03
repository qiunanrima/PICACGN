package com.just.agentweb.download;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: picacg_1.jar:com/just/agentweb/download/i.class */
public class i {
    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue(128);
    private final int CORE_POOL_SIZE;
    private final int MAXIMUM_POOL_SIZE;
    public String TAG;
    private final int gj;
    private final int gk;
    private ThreadPoolExecutor gl;
    private final ThreadFactory sThreadFactory;

    /* loaded from: picacg_1.jar:com/just/agentweb/download/i$a.class */
    static class a {
        private static final i gp = new i();
    }

    private i() {
        this.gj = Runtime.getRuntime().availableProcessors();
        this.CORE_POOL_SIZE = Math.max(2, Math.min(this.gj - 1, 4));
        this.MAXIMUM_POOL_SIZE = (this.gj * 2) + 1;
        this.gk = 15;
        this.TAG = getClass().getSimpleName();
        this.sThreadFactory = new ThreadFactory() { // from class: com.just.agentweb.download.i.1
            private ThreadGroup gn;
            private final AtomicInteger mCount = new AtomicInteger(1);
            private SecurityManager gm = System.getSecurityManager();

            {
                this.gn = this.gm != null ? this.gm.getThreadGroup() : Thread.currentThread().getThreadGroup();
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                ThreadGroup threadGroup = this.gn;
                Thread thread = new Thread(threadGroup, runnable, "pool-agentweb-thread-" + this.mCount.getAndIncrement());
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                thread.setPriority(1);
                return thread;
            }
        };
        bc();
    }

    private void bc() {
        if (this.gl != null && !this.gl.isShutdown()) {
            this.gl.shutdownNow();
        }
        this.gl = new ThreadPoolExecutor(this.CORE_POOL_SIZE, this.MAXIMUM_POOL_SIZE, 15L, TimeUnit.SECONDS, sPoolWorkQueue, this.sThreadFactory);
        this.gl.allowCoreThreadTimeOut(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static i bd() {
        return a.gp;
    }

    public Executor be() {
        return this.gl;
    }
}
