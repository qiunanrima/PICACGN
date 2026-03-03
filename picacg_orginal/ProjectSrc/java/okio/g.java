package okio;

import java.io.IOException;
/* loaded from: picacg_1.jar:okio/g.class */
public abstract class g implements q {
    private final q delegate;

    public g(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = qVar;
    }

    @Override // okio.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.delegate.close();
    }

    public final q delegate() {
        return this.delegate;
    }

    @Override // okio.q
    public long read(c cVar, long j) throws IOException {
        return this.delegate.read(cVar, j);
    }

    @Override // okio.q
    public r timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }
}
