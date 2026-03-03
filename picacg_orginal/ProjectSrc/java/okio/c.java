package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
/* loaded from: picacg_1.jar:okio/c.class */
public final class c implements Cloneable, d, e {
    private static final byte[] Ap = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
    @Nullable
    n Aq;
    long size;

    @Override // okio.e
    public long a(byte b) {
        return a(b, 0L, Long.MAX_VALUE);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long a(byte r10, long r11, long r13) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.c.a(byte, long, long):long");
    }

    @Override // okio.e
    public long a(p pVar) throws IOException {
        long j = this.size;
        if (j > 0) {
            pVar.write(this, j);
        }
        return j;
    }

    @Override // okio.d
    public long a(q qVar) throws IOException {
        if (qVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long j2 = j;
            long read = qVar.read(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (read == -1) {
                return j2;
            }
            j = j2 + read;
        }
    }

    public String a(long j, Charset charset) throws EOFException {
        s.checkOffsetAndCount(this.size, 0L, j);
        if (charset != null) {
            if (j > 2147483647L) {
                throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
            } else if (j == 0) {
                return "";
            } else {
                n nVar = this.Aq;
                if (nVar.pos + j > nVar.limit) {
                    return new String(s(j), charset);
                }
                String str = new String(nVar.data, nVar.pos, (int) j, charset);
                nVar.pos = (int) (nVar.pos + j);
                this.size -= j;
                if (nVar.pos == nVar.limit) {
                    this.Aq = nVar.gd();
                    o.b(nVar);
                }
                return str;
            }
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // okio.e
    public String a(Charset charset) {
        try {
            return a(this.size, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public c a(String str, int i, int i2, Charset charset) {
        if (str != null) {
            if (i < 0) {
                throw new IllegalAccessError("beginIndex < 0: " + i);
            } else if (i2 < i) {
                throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
            } else if (i2 <= str.length()) {
                if (charset != null) {
                    if (charset.equals(s.UTF_8)) {
                        return c(str, i, i2);
                    }
                    byte[] bytes = str.substring(i, i2).getBytes(charset);
                    return b(bytes, 0, bytes.length);
                }
                throw new IllegalArgumentException("charset == null");
            } else {
                throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
            }
        }
        throw new IllegalArgumentException("string == null");
    }

    public c a(String str, Charset charset) {
        return a(str, 0, str.length(), charset);
    }

    @Override // okio.d
    /* renamed from: a */
    public c b(ByteString byteString) {
        if (byteString != null) {
            byteString.a(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    public c a(c cVar, long j, long j2) {
        n nVar;
        long j3;
        long j4;
        if (cVar != null) {
            s.checkOffsetAndCount(this.size, j, j2);
            if (j2 == 0) {
                return this;
            }
            cVar.size += j2;
            n nVar2 = this.Aq;
            while (true) {
                n nVar3 = nVar2;
                nVar = nVar3;
                j3 = j;
                j4 = j2;
                if (j < nVar3.limit - nVar3.pos) {
                    break;
                }
                j -= nVar3.limit - nVar3.pos;
                nVar2 = nVar3.AI;
            }
            while (j4 > 0) {
                n nVar4 = new n(nVar);
                nVar4.pos = (int) (nVar4.pos + j3);
                nVar4.limit = Math.min(nVar4.pos + ((int) j4), nVar4.limit);
                if (cVar.Aq == null) {
                    nVar4.AJ = nVar4;
                    nVar4.AI = nVar4;
                    cVar.Aq = nVar4;
                } else {
                    cVar.Aq.AJ.a(nVar4);
                }
                j4 -= nVar4.limit - nVar4.pos;
                nVar = nVar.AI;
                j3 = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // okio.d
    /* renamed from: a */
    public c b(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = i2;
            s.checkOffsetAndCount(bArr.length, i, j);
            int i3 = i2 + i;
            while (i < i3) {
                n ap = ap(1);
                int min = Math.min(i3 - i, 8192 - ap.limit);
                System.arraycopy(bArr, i, ap.data, ap.limit, min);
                i += min;
                ap.limit += min;
            }
            this.size += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.e
    public void a(c cVar, long j) throws EOFException {
        if (this.size >= j) {
            cVar.write(this, j);
        } else {
            cVar.write(this, this.size);
            throw new EOFException();
        }
    }

    @Override // okio.e
    public boolean a(long j, ByteString byteString) {
        return a(j, byteString, 0, byteString.size());
    }

    public boolean a(long j, ByteString byteString, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.size - j < i2 || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (n(i3 + j) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.d
    /* renamed from: aU */
    public c aV(String str) {
        return c(str, 0, str.length());
    }

    public c al(int i) {
        if (i < 128) {
            at(i);
        } else if (i < 2048) {
            at((i >> 6) | 192);
            at((i & 63) | 128);
        } else if (i < 65536) {
            if (i < 55296 || i > 57343) {
                at((i >> 12) | 224);
                at(((i >> 6) & 63) | 128);
                at((i & 63) | 128);
            } else {
                at(63);
            }
        } else if (i > 1114111) {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        } else {
            at((i >> 18) | 240);
            at(((i >> 12) & 63) | 128);
            at(((i >> 6) & 63) | 128);
            at((i & 63) | 128);
        }
        return this;
    }

    @Override // okio.d
    /* renamed from: am */
    public c at(int i) {
        n ap = ap(1);
        byte[] bArr = ap.data;
        int i2 = ap.limit;
        ap.limit = i2 + 1;
        bArr[i2] = (byte) i;
        this.size++;
        return this;
    }

    @Override // okio.d
    /* renamed from: an */
    public c as(int i) {
        n ap = ap(2);
        byte[] bArr = ap.data;
        int i2 = ap.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        ap.limit = i3 + 1;
        this.size += 2;
        return this;
    }

    @Override // okio.d
    /* renamed from: ao */
    public c ar(int i) {
        n ap = ap(4);
        byte[] bArr = ap.data;
        int i2 = ap.limit;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        ap.limit = i5 + 1;
        this.size += 4;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0054, code lost:
        if (r0.AH == false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public okio.n ap(int r4) {
        /*
            r3 = this;
            r0 = r4
            r1 = 1
            if (r0 < r1) goto L63
            r0 = r4
            r1 = 8192(0x2000, float:1.14794E-41)
            if (r0 > r1) goto L63
            r0 = r3
            okio.n r0 = r0.Aq
            if (r0 != 0) goto L39
            r0 = r3
            okio.n r1 = okio.o.gf()
            r0.Aq = r1
            r0 = r3
            okio.n r0 = r0.Aq
            r5 = r0
            r0 = r3
            okio.n r0 = r0.Aq
            r6 = r0
            r0 = r3
            okio.n r0 = r0.Aq
            r7 = r0
            r0 = r6
            r1 = r7
            r0.AJ = r1
            r0 = r5
            r1 = r7
            r0.AI = r1
            r0 = r7
            return r0
        L39:
            r0 = r3
            okio.n r0 = r0.Aq
            okio.n r0 = r0.AJ
            r6 = r0
            r0 = r6
            int r0 = r0.limit
            r1 = r4
            int r0 = r0 + r1
            r1 = 8192(0x2000, float:1.14794E-41)
            if (r0 > r1) goto L57
            r0 = r6
            r7 = r0
            r0 = r6
            boolean r0 = r0.AH
            if (r0 != 0) goto L60
        L57:
            r0 = r6
            okio.n r1 = okio.o.gf()
            okio.n r0 = r0.a(r1)
            r7 = r0
        L60:
            r0 = r7
            return r0
        L63:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = r0
            r1.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.c.ap(int):okio.n");
    }

    public ByteString aq(int i) {
        return i == 0 ? ByteString.As : new SegmentedByteString(this, i);
    }

    public c c(String str, int i, int i2) {
        char charAt;
        if (str != null) {
            if (i < 0) {
                throw new IllegalArgumentException("beginIndex < 0: " + i);
            } else if (i2 < i) {
                throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
            } else if (i2 > str.length()) {
                throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
            } else {
                while (i < i2) {
                    char charAt2 = str.charAt(i);
                    if (charAt2 < 128) {
                        n ap = ap(1);
                        byte[] bArr = ap.data;
                        int i3 = ap.limit - i;
                        int min = Math.min(i2, 8192 - i3);
                        bArr[i + i3] = (byte) charAt2;
                        i++;
                        while (i < min && (charAt = str.charAt(i)) < 128) {
                            bArr[i + i3] = (byte) charAt;
                            i++;
                        }
                        int i4 = (i3 + i) - ap.limit;
                        ap.limit += i4;
                        this.size += i4;
                    } else if (charAt2 < 2048) {
                        at((charAt2 >> 6) | 192);
                        at((charAt2 & '?') | 128);
                        i++;
                    } else if (charAt2 < 55296 || charAt2 > 57343) {
                        at((charAt2 >> '\f') | 224);
                        at(((charAt2 >> 6) & 63) | 128);
                        at((charAt2 & '?') | 128);
                        i++;
                    } else {
                        int i5 = i + 1;
                        char charAt3 = i5 < i2 ? str.charAt(i5) : (char) 0;
                        if (charAt2 > 56319 || charAt3 < 56320 || charAt3 > 57343) {
                            at(63);
                            i = i5;
                        } else {
                            int i6 = (((charAt2 & 10239) << 10) | (9215 & charAt3)) + 65536;
                            at((i6 >> 18) | 240);
                            at(((i6 >> 12) & 63) | 128);
                            at(((i6 >> 6) & 63) | 128);
                            at((i6 & 63) | 128);
                            i += 2;
                        }
                    }
                }
                return this;
            }
        }
        throw new IllegalArgumentException("string == null");
    }

    public void clear() {
        try {
            t(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            c cVar = (c) obj;
            if (this.size != cVar.size) {
                return false;
            }
            long j = 0;
            if (this.size == 0) {
                return true;
            }
            n nVar = this.Aq;
            n nVar2 = cVar.Aq;
            int i = nVar.pos;
            int i2 = nVar2.pos;
            while (j < this.size) {
                long min = Math.min(nVar.limit - i, nVar2.limit - i2);
                int i3 = 0;
                while (i3 < min) {
                    if (nVar.data[i] != nVar2.data[i2]) {
                        return false;
                    }
                    i3++;
                    i++;
                    i2++;
                }
                if (i == nVar.limit) {
                    nVar = nVar.AI;
                    i = nVar.pos;
                }
                if (i2 == nVar2.limit) {
                    nVar2 = nVar2.AI;
                    i2 = nVar2.pos;
                }
                j += min;
            }
            return true;
        }
        return false;
    }

    @Override // okio.d
    public d fA() {
        return this;
    }

    @Override // okio.e
    public boolean fB() {
        return this.size == 0;
    }

    @Override // okio.e
    public InputStream fC() {
        return new InputStream() { // from class: okio.c.2
            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(c.this.size, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                if (c.this.size > 0) {
                    return c.this.readByte() & 255;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                return c.this.read(bArr, i, i2);
            }

            public String toString() {
                return c.this + ".inputStream()";
            }
        };
    }

    public long fD() {
        long j = this.size;
        if (j == 0) {
            return 0L;
        }
        n nVar = this.Aq.AJ;
        long j2 = j;
        if (nVar.limit < 8192) {
            j2 = j;
            if (nVar.AH) {
                j2 = j - (nVar.limit - nVar.pos);
            }
        }
        return j2;
    }

    @Override // okio.e
    public short fE() {
        return s.a(readShort());
    }

    @Override // okio.e
    public int fF() {
        return s.aw(readInt());
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00f7, code lost:
        if (r20 == 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00fa, code lost:
        r11 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0129, code lost:
        throw new java.lang.NumberFormatException("Expected leading [0-9] or '-' character but was 0x" + java.lang.Integer.toHexString(r0));
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x012e, code lost:
        if (r18 != r0) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0131, code lost:
        r6.Aq = r0.gd();
        okio.o.b(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0142, code lost:
        r0.pos = r18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x014b, code lost:
        if (r11 != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x014e, code lost:
        r9 = r7;
        r14 = r21;
        r15 = r11;
        r11 = r20;
     */
    @Override // okio.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long fG() {
        /*
            Method dump skipped, instructions count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.c.fG():long");
    }

    @Override // okio.e
    public long fH() {
        long j;
        int i;
        boolean z;
        byte b;
        int i2;
        if (this.size != 0) {
            int i3 = 0;
            long j2 = 0;
            boolean z2 = false;
            do {
                n nVar = this.Aq;
                byte[] bArr = nVar.data;
                int i4 = nVar.pos;
                int i5 = nVar.limit;
                j = j2;
                i = i3;
                while (true) {
                    z = z2;
                    if (i4 >= i5) {
                        break;
                    }
                    b = bArr[i4];
                    if (b >= 48 && b <= 57) {
                        i2 = b - 48;
                    } else if (b >= 97 && b <= 102) {
                        i2 = (b - 97) + 10;
                    } else if (b < 65 || b > 70) {
                        break;
                    } else {
                        i2 = (b - 65) + 10;
                    }
                    if (((-1152921504606846976L) & j) != 0) {
                        c at = new c().x(j).at(b);
                        throw new NumberFormatException("Number too large: " + at.fI());
                    }
                    j = (j << 4) | i2;
                    i4++;
                    i++;
                }
                if (i == 0) {
                    throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + Integer.toHexString(b));
                }
                z = true;
                if (i4 == i5) {
                    this.Aq = nVar.gd();
                    o.b(nVar);
                } else {
                    nVar.pos = i4;
                }
                if (z) {
                    break;
                }
                i3 = i;
                z2 = z;
                j2 = j;
            } while (this.Aq != null);
            this.size -= i;
            return j;
        }
        throw new IllegalStateException("size == 0");
    }

    public String fI() {
        try {
            return a(this.size, s.UTF_8);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // okio.e
    public String fJ() throws EOFException {
        return q(Long.MAX_VALUE);
    }

    public int fK() throws EOFException {
        int i;
        int i2;
        int i3;
        if (this.size != 0) {
            byte n = n(0L);
            if ((n & 128) == 0) {
                i = n & Byte.MAX_VALUE;
                i2 = 1;
                i3 = 0;
            } else if ((n & 224) == 192) {
                i = n & 31;
                i2 = 2;
                i3 = 128;
            } else if ((n & 240) == 224) {
                i = n & 15;
                i2 = 3;
                i3 = 2048;
            } else if ((n & 248) != 240) {
                t(1L);
                return 65533;
            } else {
                i = n & 7;
                i2 = 4;
                i3 = 65536;
            }
            long j = i2;
            if (this.size < j) {
                throw new EOFException("size < " + i2 + ": " + this.size + " (to read code point prefixed 0x" + Integer.toHexString(n) + ")");
            }
            for (int i4 = 1; i4 < i2; i4++) {
                long j2 = i4;
                byte n2 = n(j2);
                if ((n2 & 192) != 128) {
                    t(j2);
                    return 65533;
                }
                i = (i << 6) | (n2 & 63);
            }
            t(j);
            if (i > 1114111) {
                return 65533;
            }
            if ((i < 55296 || i > 57343) && i >= i3) {
                return i;
            }
            return 65533;
        }
        throw new EOFException();
    }

    @Override // okio.e
    public byte[] fL() {
        try {
            return s(this.size);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: fM */
    public c clone() {
        c cVar = new c();
        if (this.size == 0) {
            return cVar;
        }
        cVar.Aq = new n(this.Aq);
        n nVar = cVar.Aq;
        n nVar2 = cVar.Aq;
        n nVar3 = cVar.Aq;
        nVar2.AJ = nVar3;
        nVar.AI = nVar3;
        n nVar4 = this.Aq;
        while (true) {
            nVar4 = nVar4.AI;
            if (nVar4 == this.Aq) {
                cVar.size = this.size;
                return cVar;
            }
            cVar.Aq.AJ.a(new n(nVar4));
        }
    }

    public ByteString fN() {
        if (this.size <= 2147483647L) {
            return aq((int) this.size);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.size);
    }

    @Override // okio.d, okio.p, java.io.Flushable
    public void flush() {
    }

    @Override // okio.d, okio.e
    public c fx() {
        return this;
    }

    public OutputStream fy() {
        return new OutputStream() { // from class: okio.c.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            public String toString() {
                return c.this + ".outputStream()";
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                c.this.at((byte) i);
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                c.this.b(bArr, i, i2);
            }
        };
    }

    @Override // okio.d
    /* renamed from: fz */
    public c fO() {
        return this;
    }

    public int hashCode() {
        int i;
        n nVar;
        n nVar2 = this.Aq;
        if (nVar2 == null) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = nVar2.limit;
            i = i2;
            for (int i4 = nVar2.pos; i4 < i3; i4++) {
                i = (i * 31) + nVar2.data[i4];
            }
            nVar = nVar2.AI;
            nVar2 = nVar;
            i2 = i;
        } while (nVar != this.Aq);
        return i;
    }

    @Override // okio.d
    /* renamed from: j */
    public c k(byte[] bArr) {
        if (bArr != null) {
            return b(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // okio.e
    public void l(long j) throws EOFException {
        if (this.size < j) {
            throw new EOFException();
        }
    }

    @Override // okio.e
    public boolean m(long j) {
        return this.size >= j;
    }

    public byte n(long j) {
        s.checkOffsetAndCount(this.size, j, 1L);
        n nVar = this.Aq;
        while (true) {
            n nVar2 = nVar;
            long j2 = nVar2.limit - nVar2.pos;
            if (j < j2) {
                return nVar2.data[nVar2.pos + ((int) j)];
            }
            j -= j2;
            nVar = nVar2.AI;
        }
    }

    @Override // okio.e
    public ByteString o(long j) throws EOFException {
        return new ByteString(s(j));
    }

    public String p(long j) throws EOFException {
        return a(j, s.UTF_8);
    }

    public String q(long j) throws EOFException {
        c cVar;
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = Long.MAX_VALUE;
        if (j != Long.MAX_VALUE) {
            j2 = j + 1;
        }
        long a = a((byte) 10, 0L, j2);
        if (a != -1) {
            return r(a);
        }
        if (j2 < size() && n(j2 - 1) == 13 && n(j2) == 10) {
            return r(j2);
        }
        a(new c(), 0L, Math.min(32L, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), j) + " content=" + cVar.readByteString().fU() + (char) 8230);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String r(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (n(j2) == 13) {
                String p = p(j2);
                t(2L);
                return p;
            }
        }
        String p2 = p(j);
        t(1L);
        return p2;
    }

    @Override // okio.e
    public int read(byte[] bArr, int i, int i2) {
        s.checkOffsetAndCount(bArr.length, i, i2);
        n nVar = this.Aq;
        if (nVar == null) {
            return -1;
        }
        int min = Math.min(i2, nVar.limit - nVar.pos);
        System.arraycopy(nVar.data, nVar.pos, bArr, i, min);
        nVar.pos += min;
        this.size -= min;
        if (nVar.pos == nVar.limit) {
            this.Aq = nVar.gd();
            o.b(nVar);
        }
        return min;
    }

    @Override // okio.q
    public long read(c cVar, long j) {
        if (cVar != null) {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.size == 0) {
                return -1L;
            } else {
                long j2 = j;
                if (j > this.size) {
                    j2 = this.size;
                }
                cVar.write(this, j2);
                return j2;
            }
        }
        throw new IllegalArgumentException("sink == null");
    }

    @Override // okio.e
    public byte readByte() {
        if (this.size != 0) {
            n nVar = this.Aq;
            int i = nVar.pos;
            int i2 = nVar.limit;
            byte[] bArr = nVar.data;
            int i3 = i + 1;
            byte b = bArr[i];
            this.size--;
            if (i3 == i2) {
                this.Aq = nVar.gd();
                o.b(nVar);
            } else {
                nVar.pos = i3;
            }
            return b;
        }
        throw new IllegalStateException("size == 0");
    }

    public ByteString readByteString() {
        return new ByteString(fL());
    }

    @Override // okio.e
    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= bArr.length) {
                return;
            }
            int read = read(bArr, i2, bArr.length - i2);
            if (read == -1) {
                throw new EOFException();
            }
            i = i2 + read;
        }
    }

    @Override // okio.e
    public int readInt() {
        if (this.size < 4) {
            throw new IllegalStateException("size < 4: " + this.size);
        }
        n nVar = this.Aq;
        int i = nVar.pos;
        int i2 = nVar.limit;
        if (i2 - i < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = nVar.data;
        int i3 = i + 1;
        byte b = bArr[i];
        int i4 = i3 + 1;
        byte b2 = bArr[i3];
        int i5 = i4 + 1;
        byte b3 = bArr[i4];
        int i6 = i5 + 1;
        byte b4 = bArr[i5];
        this.size -= 4;
        if (i6 == i2) {
            this.Aq = nVar.gd();
            o.b(nVar);
        } else {
            nVar.pos = i6;
        }
        return ((b & 255) << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    @Override // okio.e
    public long readLong() {
        if (this.size < 8) {
            throw new IllegalStateException("size < 8: " + this.size);
        }
        n nVar = this.Aq;
        int i = nVar.pos;
        int i2 = nVar.limit;
        if (i2 - i < 8) {
            return ((readInt() & 4294967295L) << 32) | (4294967295L & readInt());
        }
        byte[] bArr = nVar.data;
        int i3 = i + 1;
        long j = bArr[i];
        int i4 = i3 + 1;
        long j2 = bArr[i3];
        int i5 = i4 + 1;
        long j3 = bArr[i4];
        int i6 = i5 + 1;
        long j4 = bArr[i5];
        int i7 = i6 + 1;
        long j5 = bArr[i6];
        int i8 = i7 + 1;
        long j6 = bArr[i7];
        int i9 = i8 + 1;
        long j7 = bArr[i8];
        int i10 = i9 + 1;
        long j8 = bArr[i9];
        this.size -= 8;
        if (i10 == i2) {
            this.Aq = nVar.gd();
            o.b(nVar);
        } else {
            nVar.pos = i10;
        }
        return (j8 & 255) | ((j7 & 255) << 8) | ((j & 255) << 56) | ((j2 & 255) << 48) | ((j3 & 255) << 40) | ((j4 & 255) << 32) | ((j5 & 255) << 24) | ((j6 & 255) << 16);
    }

    @Override // okio.e
    public short readShort() {
        if (this.size < 2) {
            throw new IllegalStateException("size < 2: " + this.size);
        }
        n nVar = this.Aq;
        int i = nVar.pos;
        int i2 = nVar.limit;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = nVar.data;
        int i3 = i + 1;
        byte b = bArr[i];
        int i4 = i3 + 1;
        byte b2 = bArr[i3];
        this.size -= 2;
        if (i4 == i2) {
            this.Aq = nVar.gd();
            o.b(nVar);
        } else {
            nVar.pos = i4;
        }
        return (short) (((b & 255) << 8) | (b2 & 255));
    }

    @Override // okio.e
    public byte[] s(long j) throws EOFException {
        s.checkOffsetAndCount(this.size, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    public long size() {
        return this.size;
    }

    @Override // okio.e
    public void t(long j) throws EOFException {
        while (j > 0) {
            if (this.Aq == null) {
                throw new EOFException();
            }
            int min = (int) Math.min(j, this.Aq.limit - this.Aq.pos);
            long j2 = min;
            this.size -= j2;
            long j3 = j - j2;
            this.Aq.pos += min;
            j = j3;
            if (this.Aq.pos == this.Aq.limit) {
                n nVar = this.Aq;
                this.Aq = nVar.gd();
                o.b(nVar);
                j = j3;
            }
        }
    }

    @Override // okio.p
    public r timeout() {
        return r.NONE;
    }

    public String toString() {
        return fN().toString();
    }

    @Override // okio.d
    /* renamed from: u */
    public c z(long j) {
        n ap = ap(8);
        byte[] bArr = ap.data;
        int i = ap.limit;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >>> 56) & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j >>> 48) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j >>> 40) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j >>> 32) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((j >>> 24) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((j >>> 16) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j >>> 8) & 255);
        bArr[i8] = (byte) (j & 255);
        ap.limit = i8 + 1;
        this.size += 8;
        return this;
    }

    @Override // okio.d
    /* renamed from: v */
    public c y(long j) {
        if (j == 0) {
            return at(48);
        }
        boolean z = false;
        int i = 1;
        long j2 = j;
        if (j < 0) {
            j2 = -j;
            if (j2 < 0) {
                return aV("-9223372036854775808");
            }
            z = true;
        }
        if (j2 >= 100000000) {
            i = j2 < 1000000000000L ? j2 < 10000000000L ? j2 < 1000000000 ? 9 : 10 : j2 < 100000000000L ? 11 : 12 : j2 < 1000000000000000L ? j2 < 10000000000000L ? 13 : j2 < 100000000000000L ? 14 : 15 : j2 < 100000000000000000L ? j2 < 10000000000000000L ? 16 : 17 : j2 < 1000000000000000000L ? 18 : 19;
        } else if (j2 >= 10000) {
            i = j2 < 1000000 ? j2 < 100000 ? 5 : 6 : j2 < 10000000 ? 7 : 8;
        } else if (j2 >= 100) {
            i = j2 < 1000 ? 3 : 4;
        } else if (j2 >= 10) {
            i = 2;
        }
        int i2 = i;
        if (z) {
            i2 = i + 1;
        }
        n ap = ap(i2);
        byte[] bArr = ap.data;
        int i3 = ap.limit + i2;
        while (j2 != 0) {
            i3--;
            bArr[i3] = Ap[(int) (j2 % 10)];
            j2 /= 10;
        }
        if (z) {
            bArr[i3 - 1] = (byte) 45;
        }
        ap.limit += i2;
        this.size += i2;
        return this;
    }

    @Override // okio.d
    /* renamed from: w */
    public c x(long j) {
        if (j == 0) {
            return at(48);
        }
        int numberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        n ap = ap(numberOfTrailingZeros);
        byte[] bArr = ap.data;
        int i = ap.limit;
        for (int i2 = (ap.limit + numberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = Ap[(int) (15 & j)];
            j >>>= 4;
        }
        ap.limit += numberOfTrailingZeros;
        this.size += numberOfTrailingZeros;
        return this;
    }

    @Override // okio.p
    public void write(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (cVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        s.checkOffsetAndCount(cVar.size, 0L, j);
        while (j > 0) {
            if (j < cVar.Aq.limit - cVar.Aq.pos) {
                n nVar = this.Aq != null ? this.Aq.AJ : null;
                if (nVar != null && nVar.AH) {
                    if ((nVar.limit + j) - (nVar.AG ? 0 : nVar.pos) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        cVar.Aq.a(nVar, (int) j);
                        cVar.size -= j;
                        this.size += j;
                        return;
                    }
                }
                cVar.Aq = cVar.Aq.au((int) j);
            }
            n nVar2 = cVar.Aq;
            long j2 = nVar2.limit - nVar2.pos;
            cVar.Aq = nVar2.gd();
            if (this.Aq == null) {
                this.Aq = nVar2;
                n nVar3 = this.Aq;
                n nVar4 = this.Aq;
                n nVar5 = this.Aq;
                nVar4.AJ = nVar5;
                nVar3.AI = nVar5;
            } else {
                this.Aq.AJ.a(nVar2).ge();
            }
            cVar.size -= j2;
            this.size += j2;
            j -= j2;
        }
    }
}
