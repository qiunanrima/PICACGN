package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
/* loaded from: picacg_1.jar:okio/i.class */
public final class i implements q {
    private final Inflater Aw;
    private final j Ax;
    private final e source;
    private int Av = 0;
    private final CRC32 crc = new CRC32();

    public i(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.Aw = new Inflater(true);
        this.source = k.b(qVar);
        this.Ax = new j(this.source, this.Aw);
    }

    private void b(c cVar, long j, long j2) {
        n nVar;
        int i;
        n nVar2 = cVar.Aq;
        while (true) {
            nVar = nVar2;
            if (j < nVar.limit - nVar.pos) {
                break;
            }
            j -= nVar.limit - nVar.pos;
            nVar2 = nVar.AI;
        }
        while (j2 > 0) {
            int min = (int) Math.min(nVar.limit - i, j2);
            this.crc.update(nVar.data, (int) (nVar.pos + j), min);
            j2 -= min;
            nVar = nVar.AI;
            j = 0;
        }
    }

    private void d(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }

    private void fX() throws IOException {
        this.source.l(10L);
        byte n = this.source.fx().n(3L);
        boolean z = ((n >> 1) & 1) == 1;
        if (z) {
            b(this.source.fx(), 0L, 10L);
        }
        d("ID1ID2", 8075, this.source.readShort());
        this.source.t(8L);
        if (((n >> 2) & 1) == 1) {
            this.source.l(2L);
            if (z) {
                b(this.source.fx(), 0L, 2L);
            }
            long fE = this.source.fx().fE();
            this.source.l(fE);
            if (z) {
                b(this.source.fx(), 0L, fE);
            }
            this.source.t(fE);
        }
        if (((n >> 3) & 1) == 1) {
            long a = this.source.a((byte) 0);
            if (a == -1) {
                throw new EOFException();
            }
            if (z) {
                b(this.source.fx(), 0L, a + 1);
            }
            this.source.t(a + 1);
        }
        if (((n >> 4) & 1) == 1) {
            long a2 = this.source.a((byte) 0);
            if (a2 == -1) {
                throw new EOFException();
            }
            if (z) {
                b(this.source.fx(), 0L, a2 + 1);
            }
            this.source.t(a2 + 1);
        }
        if (z) {
            d("FHCRC", this.source.fE(), (short) this.crc.getValue());
            this.crc.reset();
        }
    }

    private void fY() throws IOException {
        d("CRC", this.source.fF(), (int) this.crc.getValue());
        d("ISIZE", this.source.fF(), (int) this.Aw.getBytesWritten());
    }

    @Override // okio.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.Ax.close();
    }

    @Override // okio.q
    public long read(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (j == 0) {
            return 0L;
        } else {
            if (this.Av == 0) {
                fX();
                this.Av = 1;
            }
            if (this.Av == 1) {
                long j2 = cVar.size;
                long read = this.Ax.read(cVar, j);
                if (read != -1) {
                    b(cVar, j2, read);
                    return read;
                }
                this.Av = 2;
            }
            if (this.Av == 2) {
                fY();
                this.Av = 3;
                if (this.source.fB()) {
                    return -1L;
                }
                throw new IOException("gzip finished without exhausting source");
            }
            return -1L;
        }
    }

    @Override // okio.q
    public r timeout() {
        return this.source.timeout();
    }
}
