package okio;

import java.util.Arrays;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: picacg_1.jar:okio/SegmentedByteString.class */
public final class SegmentedByteString extends ByteString {
    final transient byte[][] AL;
    final transient int[] AM;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Type inference failed for: r1v4, types: [byte[], byte[][]] */
    public SegmentedByteString(c cVar, int i) {
        super(null);
        s.checkOffsetAndCount(cVar.size, 0L, i);
        n nVar = cVar.Aq;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            if (nVar.limit == nVar.pos) {
                throw new AssertionError("s.limit == s.pos");
            }
            i2 += nVar.limit - nVar.pos;
            i3++;
            nVar = nVar.AI;
        }
        this.AL = new byte[i3];
        this.AM = new int[i3 * 2];
        n nVar2 = cVar.Aq;
        int i4 = 0;
        int i5 = 0;
        while (i5 < i) {
            this.AL[i4] = nVar2.data;
            int i6 = i5 + (nVar2.limit - nVar2.pos);
            i5 = i6;
            if (i6 > i) {
                i5 = i;
            }
            this.AM[i4] = i5;
            this.AM[this.AL.length + i4] = nVar2.pos;
            nVar2.AG = true;
            i4++;
            nVar2 = nVar2.AI;
        }
    }

    private int av(int i) {
        int binarySearch = Arrays.binarySearch(this.AM, 0, this.AL.length, i + 1);
        if (binarySearch < 0) {
            binarySearch ^= -1;
        }
        return binarySearch;
    }

    private ByteString gg() {
        return new ByteString(toByteArray());
    }

    private Object writeReplace() {
        return gg();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // okio.ByteString
    public void a(c cVar) {
        int length = this.AL.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i >= length) {
                cVar.size += i3;
                return;
            }
            int i4 = this.AM[length + i];
            int i5 = this.AM[i];
            n nVar = new n(this.AL[i], i4, (i4 + i5) - i3);
            if (cVar.Aq == null) {
                nVar.AJ = nVar;
                nVar.AI = nVar;
                cVar.Aq = nVar;
            } else {
                cVar.Aq.AJ.a(nVar);
            }
            i++;
            i2 = i5;
        }
    }

    @Override // okio.ByteString
    public boolean a(int i, ByteString byteString, int i2, int i3) {
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int av = av(i);
        while (i3 > 0) {
            int i4 = av == 0 ? 0 : this.AM[av - 1];
            int min = Math.min(i3, ((this.AM[av] - i4) + i4) - i);
            if (!byteString.a(i2, this.AL[av], (i - i4) + this.AM[this.AL.length + av], min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            av++;
        }
        return true;
    }

    @Override // okio.ByteString
    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int av = av(i);
        while (i3 > 0) {
            int i4 = av == 0 ? 0 : this.AM[av - 1];
            int min = Math.min(i3, ((this.AM[av] - i4) + i4) - i);
            if (!s.a(this.AL[av], (i - i4) + this.AM[this.AL.length + av], bArr, i2, min)) {
                return false;
            }
            i += min;
            i2 += min;
            i3 -= min;
            av++;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (a(0, r0, 0, size()) != false) goto L11;
     */
    @Override // okio.ByteString
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            r8 = r0
            r0 = r7
            r1 = r6
            if (r0 != r1) goto L9
            r0 = 1
            return r0
        L9:
            r0 = r7
            boolean r0 = r0 instanceof okio.ByteString
            if (r0 == 0) goto L31
            r0 = r7
            okio.ByteString r0 = (okio.ByteString) r0
            r7 = r0
            r0 = r7
            int r0 = r0.size()
            r1 = r6
            int r1 = r1.size()
            if (r0 != r1) goto L31
            r0 = r6
            r1 = 0
            r2 = r7
            r3 = 0
            r4 = r6
            int r4 = r4.size()
            boolean r0 = r0.a(r1, r2, r3, r4)
            if (r0 == 0) goto L31
            goto L33
        L31:
            r0 = 0
            r8 = r0
        L33:
            r0 = r8
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.SegmentedByteString.equals(java.lang.Object):boolean");
    }

    @Override // okio.ByteString
    public String fP() {
        return gg().fP();
    }

    @Override // okio.ByteString
    public String fQ() {
        return gg().fQ();
    }

    @Override // okio.ByteString
    public ByteString fR() {
        return gg().fR();
    }

    @Override // okio.ByteString
    public ByteString fS() {
        return gg().fS();
    }

    @Override // okio.ByteString
    public ByteString fT() {
        return gg().fT();
    }

    @Override // okio.ByteString
    public String fU() {
        return gg().fU();
    }

    @Override // okio.ByteString
    public ByteString fV() {
        return gg().fV();
    }

    @Override // okio.ByteString
    public byte getByte(int i) {
        s.checkOffsetAndCount(this.AM[this.AL.length - 1], i, 1L);
        int av = av(i);
        return this.AL[av][(i - (av == 0 ? 0 : this.AM[av - 1])) + this.AM[this.AL.length + av]];
    }

    @Override // okio.ByteString
    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int length = this.AL.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < length) {
            byte[] bArr = this.AL[i2];
            int i5 = this.AM[length + i2];
            int i6 = this.AM[i2];
            for (int i7 = i5; i7 < (i6 - i3) + i5; i7++) {
                i4 = (i4 * 31) + bArr[i7];
            }
            i2++;
            i3 = i6;
        }
        this.hashCode = i4;
        return i4;
    }

    @Override // okio.ByteString
    public ByteString m(int i, int i2) {
        return gg().m(i, i2);
    }

    @Override // okio.ByteString
    public int size() {
        return this.AM[this.AL.length - 1];
    }

    @Override // okio.ByteString
    public byte[] toByteArray() {
        byte[] bArr = new byte[this.AM[this.AL.length - 1]];
        int length = this.AL.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i >= length) {
                return bArr;
            }
            int i4 = this.AM[length + i];
            int i5 = this.AM[i];
            System.arraycopy(this.AL[i], i4, bArr, i3, i5 - i3);
            i++;
            i2 = i5;
        }
    }

    @Override // okio.ByteString
    public String toString() {
        return gg().toString();
    }
}
