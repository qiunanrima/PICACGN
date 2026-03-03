package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
/* loaded from: picacg_1.jar:okio/a.class */
public class a extends r {
    private static final long IDLE_TIMEOUT_MILLIS = TimeUnit.SECONDS.toMillis(60);
    private static final long IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(IDLE_TIMEOUT_MILLIS);
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    @Nullable
    static a head;
    private boolean inQueue;
    @Nullable
    private a next;
    private long timeoutAt;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: okio.a$a  reason: collision with other inner class name */
    /* loaded from: picacg_1.jar:okio/a$a.class */
    public static final class C0028a extends Thread {
        C0028a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:18:0x0024, code lost:
            r0.timedOut();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r3 = this;
            L0:
                java.lang.Class<okio.a> r0 = okio.a.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L30
                okio.a r0 = okio.a.awaitTimeout()     // Catch: java.lang.Throwable -> L2a java.lang.InterruptedException -> L30
                r4 = r0
                r0 = r4
                if (r0 != 0) goto L11
                java.lang.Class<okio.a> r0 = okio.a.class
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2a
                goto L0
            L11:
                r0 = r4
                okio.a r1 = okio.a.head     // Catch: java.lang.Throwable -> L2a
                if (r0 != r1) goto L20
                r0 = 0
                okio.a.head = r0     // Catch: java.lang.Throwable -> L2a
                java.lang.Class<okio.a> r0 = okio.a.class
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2a
                return
            L20:
                java.lang.Class<okio.a> r0 = okio.a.class
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2a
                r0 = r4
                r0.timedOut()     // Catch: java.lang.InterruptedException -> L30
                goto L0
            L2a:
                r4 = move-exception
                java.lang.Class<okio.a> r0 = okio.a.class
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L2a
                r0 = r4
                throw r0     // Catch: java.lang.InterruptedException -> L30
            L30:
                r4 = move-exception
                goto L0
            */
            throw new UnsupportedOperationException("Method not decompiled: okio.a.C0028a.run():void");
        }
    }

    @Nullable
    static a awaitTimeout() throws InterruptedException {
        a aVar = head.next;
        if (aVar == null) {
            long nanoTime = System.nanoTime();
            a.class.wait(IDLE_TIMEOUT_MILLIS);
            a aVar2 = null;
            if (head.next == null) {
                aVar2 = null;
                if (System.nanoTime() - nanoTime >= IDLE_TIMEOUT_NANOS) {
                    aVar2 = head;
                }
            }
            return aVar2;
        }
        long remainingNanos = aVar.remainingNanos(System.nanoTime());
        if (remainingNanos > 0) {
            long j = remainingNanos / 1000000;
            a.class.wait(j, (int) (remainingNanos - (1000000 * j)));
            return null;
        }
        head.next = aVar.next;
        aVar.next = null;
        return aVar;
    }

    private static boolean cancelScheduledTimeout(a aVar) {
        synchronized (a.class) {
            try {
                for (a aVar2 = head; aVar2 != null; aVar2 = aVar2.next) {
                    if (aVar2.next == aVar) {
                        aVar2.next = aVar.next;
                        aVar.next = null;
                        return false;
                    }
                }
                return true;
            } finally {
            }
        }
    }

    private long remainingNanos(long j) {
        return this.timeoutAt - j;
    }

    private static void scheduleTimeout(a aVar, long j, boolean z) {
        synchronized (a.class) {
            try {
                if (head == null) {
                    head = new a();
                    new C0028a().start();
                }
                long nanoTime = System.nanoTime();
                if (j != 0 && z) {
                    aVar.timeoutAt = Math.min(j, aVar.deadlineNanoTime() - nanoTime) + nanoTime;
                } else if (j != 0) {
                    aVar.timeoutAt = j + nanoTime;
                } else if (!z) {
                    throw new AssertionError();
                } else {
                    aVar.timeoutAt = aVar.deadlineNanoTime();
                }
                long remainingNanos = aVar.remainingNanos(nanoTime);
                a aVar2 = head;
                while (aVar2.next != null && remainingNanos >= aVar2.next.remainingNanos(nanoTime)) {
                    aVar2 = aVar2.next;
                }
                aVar.next = aVar2.next;
                aVar2.next = aVar;
                if (aVar2 == head) {
                    a.class.notify();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void enter() {
        if (this.inQueue) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long timeoutNanos = timeoutNanos();
        boolean hasDeadline = hasDeadline();
        if (timeoutNanos != 0 || hasDeadline) {
            this.inQueue = true;
            scheduleTimeout(this, timeoutNanos, hasDeadline);
        }
    }

    final IOException exit(IOException iOException) throws IOException {
        return !exit() ? iOException : newTimeoutException(iOException);
    }

    final void exit(boolean z) throws IOException {
        if (exit() && z) {
            throw newTimeoutException(null);
        }
    }

    public final boolean exit() {
        if (this.inQueue) {
            this.inQueue = false;
            return cancelScheduledTimeout(this);
        }
        return false;
    }

    protected IOException newTimeoutException(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    public final p sink(final p pVar) {
        return new p() { // from class: okio.a.1
            @Override // okio.p, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                a.this.enter();
                try {
                    try {
                        pVar.close();
                        a.this.exit(true);
                    } catch (IOException e) {
                        throw a.this.exit(e);
                    }
                } catch (Throwable th) {
                    a.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.p, java.io.Flushable
            public void flush() throws IOException {
                a.this.enter();
                try {
                    try {
                        pVar.flush();
                        a.this.exit(true);
                    } catch (IOException e) {
                        throw a.this.exit(e);
                    }
                } catch (Throwable th) {
                    a.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.p
            public r timeout() {
                return a.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + pVar + ")";
            }

            @Override // okio.p
            public void write(c cVar, long j) throws IOException {
                long j2;
                s.checkOffsetAndCount(cVar.size, 0L, j);
                while (true) {
                    long j3 = 0;
                    if (j <= 0) {
                        return;
                    }
                    n nVar = cVar.Aq;
                    while (true) {
                        n nVar2 = nVar;
                        j2 = j3;
                        if (j3 >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                            break;
                        }
                        j3 += cVar.Aq.limit - cVar.Aq.pos;
                        if (j3 >= j) {
                            j2 = j;
                            break;
                        }
                        nVar = nVar2.AI;
                    }
                    a.this.enter();
                    try {
                        try {
                            pVar.write(cVar, j2);
                            j -= j2;
                            a.this.exit(true);
                        } catch (IOException e) {
                            throw a.this.exit(e);
                        }
                    } catch (Throwable th) {
                        a.this.exit(false);
                        throw th;
                    }
                }
            }
        };
    }

    public final q source(final q qVar) {
        return new q() { // from class: okio.a.2
            @Override // okio.q, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    try {
                        qVar.close();
                        a.this.exit(true);
                    } catch (IOException e) {
                        throw a.this.exit(e);
                    }
                } catch (Throwable th) {
                    a.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.q
            public long read(c cVar, long j) throws IOException {
                a.this.enter();
                try {
                    try {
                        long read = qVar.read(cVar, j);
                        a.this.exit(true);
                        return read;
                    } catch (IOException e) {
                        throw a.this.exit(e);
                    }
                } catch (Throwable th) {
                    a.this.exit(false);
                    throw th;
                }
            }

            @Override // okio.q
            public r timeout() {
                return a.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + qVar + ")";
            }
        };
    }

    protected void timedOut() {
    }
}
