package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
/* loaded from: picacg_1.jar:okio/l.class */
final class l implements d {
    public final p AD;
    public final c buffer = new c();
    boolean closed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(p pVar) {
        if (pVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.AD = pVar;
    }

    @Override // okio.d
    public long a(q qVar) throws IOException {
        if (qVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long read = qVar.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j;
            }
            j += read;
            fO();
        }
    }

    @Override // okio.d
    public d aV(String str) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.aV(str);
        return fO();
    }

    @Override // okio.d
    public d ar(int i) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.ar(i);
        return fO();
    }

    @Override // okio.d
    public d as(int i) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.as(i);
        return fO();
    }

    @Override // okio.d
    public d at(int i) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.at(i);
        return fO();
    }

    @Override // okio.d
    public d b(ByteString byteString) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.b(byteString);
        return fO();
    }

    @Override // okio.d
    public d b(byte[] bArr, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.b(bArr, i, i2);
        return fO();
    }

    @Override // okio.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Throwable th;
        if (this.closed) {
            return;
        }
        Throwable th2 = null;
        try {
            if (this.buffer.size > 0) {
                this.AD.write(this.buffer, this.buffer.size);
                th2 = null;
            }
        } catch (Throwable th3) {
            th2 = th3;
        }
        try {
            this.AD.close();
            th = th2;
        } catch (Throwable th4) {
            th = th2;
            if (th2 == null) {
                th = th4;
            }
        }
        this.closed = true;
        if (th != null) {
            s.c(th);
        }
    }

    @Override // okio.d
    public d fA() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long size = this.buffer.size();
        if (size > 0) {
            this.AD.write(this.buffer, size);
        }
        return this;
    }

    @Override // okio.d
    public d fO() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        long fD = this.buffer.fD();
        if (fD > 0) {
            this.AD.write(this.buffer, fD);
        }
        return this;
    }

    @Override // okio.d, okio.p, java.io.Flushable
    public void flush() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.buffer.size > 0) {
            this.AD.write(this.buffer, this.buffer.size);
        }
        this.AD.flush();
    }

    @Override // okio.d, okio.e
    public c fx() {
        return this.buffer;
    }

    @Override // okio.d
    public d k(byte[] bArr) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.k(bArr);
        return fO();
    }

    @Override // okio.p
    public r timeout() {
        return this.AD.timeout();
    }

    public String toString() {
        return "buffer(" + this.AD + ")";
    }

    @Override // okio.p
    public void write(c cVar, long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.write(cVar, j);
        fO();
    }

    @Override // okio.d
    public d x(long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.x(j);
        return fO();
    }

    @Override // okio.d
    public d y(long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.y(j);
        return fO();
    }

    @Override // okio.d
    public d z(long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        this.buffer.z(j);
        return fO();
    }
}
