package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: picacg_1.jar:okio/m.class */
public final class m implements e {
    public final q AE;
    public final c buffer = new c();
    boolean closed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(q qVar) {
        if (qVar == null) {
            throw new NullPointerException("source == null");
        }
        this.AE = qVar;
    }

    @Override // okio.e
    public long a(byte b) throws IOException {
        return a(b, 0L, Long.MAX_VALUE);
    }

    public long a(byte b, long j, long j2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long a = this.buffer.a(b, j, j2);
            if (a != -1) {
                return a;
            }
            long j3 = this.buffer.size;
            if (j3 >= j2 || this.AE.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // okio.e
    public long a(p pVar) throws IOException {
        if (pVar != null) {
            long j = 0;
            while (this.AE.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                long fD = this.buffer.fD();
                if (fD > 0) {
                    j += fD;
                    pVar.write(this.buffer, fD);
                }
            }
            long j2 = j;
            if (this.buffer.size() > 0) {
                j2 = j + this.buffer.size();
                pVar.write(this.buffer, this.buffer.size());
            }
            return j2;
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // okio.e
    public String a(Charset charset) throws IOException {
        if (charset != null) {
            this.buffer.a(this.AE);
            return this.buffer.a(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.e
    public void a(c cVar, long j) throws IOException {
        try {
            l(j);
            this.buffer.a(cVar, j);
        } catch (EOFException e) {
            cVar.a((q) this.buffer);
            throw e;
        }
    }

    @Override // okio.e
    public boolean a(long j, ByteString byteString) throws IOException {
        return a(j, byteString, 0, byteString.size());
    }

    public boolean a(long j, ByteString byteString, int i, int i2) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || i < 0 || i2 < 0 || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = i3 + j;
            if (!m(1 + j2) || this.buffer.n(j2) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.AE.close();
        this.buffer.clear();
    }

    @Override // okio.e
    public boolean fB() throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.buffer.fB() && this.AE.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
    }

    @Override // okio.e
    public InputStream fC() {
        return new InputStream() { // from class: okio.m.1
            @Override // java.io.InputStream
            public int available() throws IOException {
                if (m.this.closed) {
                    throw new IOException("closed");
                }
                return (int) Math.min(m.this.buffer.size, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                m.this.close();
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                if (m.this.closed) {
                    throw new IOException("closed");
                }
                if (m.this.buffer.size == 0 && m.this.AE.read(m.this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return m.this.buffer.readByte() & 255;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (m.this.closed) {
                    throw new IOException("closed");
                }
                s.checkOffsetAndCount(bArr.length, i, i2);
                if (m.this.buffer.size == 0 && m.this.AE.read(m.this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return m.this.buffer.read(bArr, i, i2);
            }

            public String toString() {
                return m.this + ".inputStream()";
            }
        };
    }

    @Override // okio.e
    public short fE() throws IOException {
        l(2L);
        return this.buffer.fE();
    }

    @Override // okio.e
    public int fF() throws IOException {
        l(4L);
        return this.buffer.fF();
    }

    @Override // okio.e
    public long fG() throws IOException {
        int i;
        byte n;
        l(1L);
        int i2 = 0;
        while (true) {
            i = i2;
            int i3 = i + 1;
            if (!m(i3)) {
                break;
            }
            n = this.buffer.n(i);
            if ((n < 48 || n > 57) && !(i == 0 && n == 45)) {
                break;
            }
            i2 = i3;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(n)));
        }
        return this.buffer.fG();
    }

    @Override // okio.e
    public long fH() throws IOException {
        int i;
        byte n;
        l(1L);
        int i2 = 0;
        while (true) {
            i = i2;
            int i3 = i + 1;
            if (!m(i3)) {
                break;
            }
            n = this.buffer.n(i);
            if ((n < 48 || n > 57) && ((n < 97 || n > 102) && (n < 65 || n > 70))) {
                break;
            }
            i2 = i3;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(n)));
        }
        return this.buffer.fH();
    }

    @Override // okio.e
    public String fJ() throws IOException {
        return q(Long.MAX_VALUE);
    }

    @Override // okio.e
    public byte[] fL() throws IOException {
        this.buffer.a(this.AE);
        return this.buffer.fL();
    }

    @Override // okio.e
    public c fx() {
        return this.buffer;
    }

    @Override // okio.e
    public void l(long j) throws IOException {
        if (!m(j)) {
            throw new EOFException();
        }
    }

    @Override // okio.e
    public boolean m(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.closed) {
            throw new IllegalStateException("closed");
        } else {
            while (this.buffer.size < j) {
                if (this.AE.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // okio.e
    public ByteString o(long j) throws IOException {
        l(j);
        return this.buffer.o(j);
    }

    public String q(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long a = a((byte) 10, 0L, j2);
        if (a != -1) {
            return this.buffer.r(a);
        }
        if (j2 < Long.MAX_VALUE && m(j2) && this.buffer.n(j2 - 1) == 13 && m(1 + j2) && this.buffer.n(j2) == 10) {
            return this.buffer.r(j2);
        }
        c cVar = new c();
        this.buffer.a(cVar, 0L, Math.min(32L, this.buffer.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.buffer.size(), j) + " content=" + cVar.readByteString().fU() + (char) 8230);
    }

    @Override // okio.e
    public int read(byte[] bArr, int i, int i2) throws IOException {
        long j = i2;
        s.checkOffsetAndCount(bArr.length, i, j);
        if (this.buffer.size == 0 && this.AE.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.buffer.read(bArr, i, (int) Math.min(j, this.buffer.size));
    }

    @Override // okio.q
    public long read(c cVar, long j) throws IOException {
        if (cVar != null) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.closed) {
                throw new IllegalStateException("closed");
            } else {
                if (this.buffer.size == 0 && this.AE.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1L;
                }
                return this.buffer.read(cVar, Math.min(j, this.buffer.size));
            }
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // okio.e
    public byte readByte() throws IOException {
        l(1L);
        return this.buffer.readByte();
    }

    @Override // okio.e
    public void readFully(byte[] bArr) throws IOException {
        try {
            l(bArr.length);
            this.buffer.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (this.buffer.size <= 0) {
                    throw e;
                }
                int read = this.buffer.read(bArr, i2, (int) this.buffer.size);
                if (read == -1) {
                    throw new AssertionError();
                }
                i = i2 + read;
            }
        }
    }

    @Override // okio.e
    public int readInt() throws IOException {
        l(4L);
        return this.buffer.readInt();
    }

    @Override // okio.e
    public long readLong() throws IOException {
        l(8L);
        return this.buffer.readLong();
    }

    @Override // okio.e
    public short readShort() throws IOException {
        l(2L);
        return this.buffer.readShort();
    }

    @Override // okio.e
    public byte[] s(long j) throws IOException {
        l(j);
        return this.buffer.s(j);
    }

    @Override // okio.e
    public void t(long j) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.buffer.size == 0 && this.AE.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.buffer.size());
            this.buffer.t(min);
            j -= min;
        }
    }

    @Override // okio.q
    public r timeout() {
        return this.AE.timeout();
    }

    public String toString() {
        return "buffer(" + this.AE + ")";
    }
}
