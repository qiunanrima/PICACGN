package okio;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
/* loaded from: picacg_1.jar:okio/p.class */
public interface p extends Closeable, Flushable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    void flush() throws IOException;

    r timeout();

    void write(c cVar, long j) throws IOException;
}
