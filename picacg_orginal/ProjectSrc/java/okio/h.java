package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
/* loaded from: picacg_1.jar:okio/h.class */
public class h extends r {
    private r Au;

    public h(r rVar) {
        if (rVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.Au = rVar;
    }

    public final h a(r rVar) {
        if (rVar != null) {
            this.Au = rVar;
            return this;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    @Override // okio.r
    public r clearDeadline() {
        return this.Au.clearDeadline();
    }

    @Override // okio.r
    public r clearTimeout() {
        return this.Au.clearTimeout();
    }

    @Override // okio.r
    public long deadlineNanoTime() {
        return this.Au.deadlineNanoTime();
    }

    @Override // okio.r
    public r deadlineNanoTime(long j) {
        return this.Au.deadlineNanoTime(j);
    }

    public final r fW() {
        return this.Au;
    }

    @Override // okio.r
    public boolean hasDeadline() {
        return this.Au.hasDeadline();
    }

    @Override // okio.r
    public void throwIfReached() throws IOException {
        this.Au.throwIfReached();
    }

    @Override // okio.r
    public r timeout(long j, TimeUnit timeUnit) {
        return this.Au.timeout(j, timeUnit);
    }

    @Override // okio.r
    public long timeoutNanos() {
        return this.Au.timeoutNanos();
    }
}
