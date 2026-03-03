package okio;

import java.io.IOException;
/* loaded from: picacg_1.jar:okio/d.class */
public interface d extends p {
    long a(q qVar) throws IOException;

    d aV(String str) throws IOException;

    d ar(int i) throws IOException;

    d as(int i) throws IOException;

    d at(int i) throws IOException;

    d b(ByteString byteString) throws IOException;

    d b(byte[] bArr, int i, int i2) throws IOException;

    d fA() throws IOException;

    d fO() throws IOException;

    @Override // okio.p, java.io.Flushable
    void flush() throws IOException;

    c fx();

    d k(byte[] bArr) throws IOException;

    d x(long j) throws IOException;

    d y(long j) throws IOException;

    d z(long j) throws IOException;
}
