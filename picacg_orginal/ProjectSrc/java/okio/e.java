package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
/* loaded from: picacg_1.jar:okio/e.class */
public interface e extends q {
    long a(byte b) throws IOException;

    long a(p pVar) throws IOException;

    String a(Charset charset) throws IOException;

    void a(c cVar, long j) throws IOException;

    boolean a(long j, ByteString byteString) throws IOException;

    boolean fB() throws IOException;

    InputStream fC();

    short fE() throws IOException;

    int fF() throws IOException;

    long fG() throws IOException;

    long fH() throws IOException;

    String fJ() throws IOException;

    byte[] fL() throws IOException;

    c fx();

    void l(long j) throws IOException;

    boolean m(long j) throws IOException;

    ByteString o(long j) throws IOException;

    int read(byte[] bArr, int i, int i2) throws IOException;

    byte readByte() throws IOException;

    void readFully(byte[] bArr) throws IOException;

    int readInt() throws IOException;

    long readLong() throws IOException;

    short readShort() throws IOException;

    byte[] s(long j) throws IOException;

    void t(long j) throws IOException;
}
