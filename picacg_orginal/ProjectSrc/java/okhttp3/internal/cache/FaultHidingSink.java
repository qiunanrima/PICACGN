package okhttp3.internal.cache;

import java.io.IOException;
import okio.c;
import okio.f;
import okio.p;
/* loaded from: picacg_1.jar:okhttp3/internal/cache/FaultHidingSink.class */
class FaultHidingSink extends f {
    private boolean hasErrors;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FaultHidingSink(p pVar) {
        super(pVar);
    }

    @Override // okio.f, okio.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    @Override // okio.f, okio.p, java.io.Flushable
    public void flush() throws IOException {
        if (this.hasErrors) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    protected void onException(IOException iOException) {
    }

    @Override // okio.f, okio.p
    public void write(c cVar, long j) throws IOException {
        if (this.hasErrors) {
            cVar.t(j);
            return;
        }
        try {
            super.write(cVar, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }
}
