package io.socket.g;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
/* loaded from: picacg_1.jar:io/socket/g/a.class */
public class a extends Thread {
    private static a Ab;
    private static ExecutorService service;
    private static final Logger logger = Logger.getLogger(a.class.getName());
    private static final ThreadFactory Aa = new ThreadFactory() { // from class: io.socket.g.a.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            a unused = a.Ab = new a(runnable);
            a.Ab.setName("EventThread");
            a.Ab.setDaemon(Thread.currentThread().isDaemon());
            return a.Ab;
        }
    };
    private static int Ac = 0;

    private a(Runnable runnable) {
        super(runnable);
    }

    public static void b(Runnable runnable) {
        if (fp()) {
            runnable.run();
        } else {
            c(runnable);
        }
    }

    public static void c(final Runnable runnable) {
        ExecutorService executorService;
        synchronized (a.class) {
            try {
                Ac++;
                if (service == null) {
                    service = Executors.newSingleThreadExecutor(Aa);
                }
                executorService = service;
            } catch (Throwable th) {
                throw th;
            }
        }
        executorService.execute(new Runnable() { // from class: io.socket.g.a.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                    synchronized (a.class) {
                        try {
                            a.fr();
                            if (a.Ac == 0) {
                                a.service.shutdown();
                                ExecutorService unused = a.service = null;
                                a unused2 = a.Ab = null;
                            }
                        } finally {
                        }
                    }
                } catch (Throwable th2) {
                    try {
                        a.logger.log(Level.SEVERE, "Task threw exception", th2);
                        throw th2;
                    } catch (Throwable th3) {
                        synchronized (a.class) {
                            try {
                                a.fr();
                                if (a.Ac == 0) {
                                    a.service.shutdown();
                                    ExecutorService unused3 = a.service = null;
                                    a unused4 = a.Ab = null;
                                }
                                throw th3;
                            } finally {
                            }
                        }
                    }
                }
            }
        });
    }

    public static boolean fp() {
        return currentThread() == Ab;
    }

    static /* synthetic */ int fr() {
        int i = Ac;
        Ac = i - 1;
        return i;
    }
}
