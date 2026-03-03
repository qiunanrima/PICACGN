package okio;

import java.io.Closeable;
import java.io.IOException;
/* loaded from: picacg_1.jar:okio/q.class */
public interface q extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    long read(c cVar, long j) throws IOException;

    r timeout();
}
