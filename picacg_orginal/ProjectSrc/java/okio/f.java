package okio;

import java.io.IOException;
/* loaded from: picacg_1.jar:okio/f.class */
public abstract class f implements p {
    private final p delegate;

    public f(p pVar) {
        if (pVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = pVar;
    }

    @Override // okio.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    public final p delegate() {
        return this.delegate;
    }

    @Override // okio.p, java.io.Flushable
    public void flush() throws IOException {
        this.delegate.flush();
    }

    @Override // okio.p
    public r timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }

    @Override // okio.p
    public void write(c cVar, long j) throws IOException {
        this.delegate.write(cVar, j);
    }
}
