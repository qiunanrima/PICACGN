package io.socket.engineio.parser;

import java.nio.ByteBuffer;
/* loaded from: picacg_1.jar:io/socket/engineio/parser/a.class */
class a {
    public static byte[] a(byte[][] bArr) {
        int i = 0;
        for (byte[] bArr2 : bArr) {
            i += bArr2.length;
        }
        return a(bArr, i);
    }

    public static byte[] a(byte[][] bArr, int i) {
        if (bArr.length == 0) {
            return new byte[0];
        }
        if (bArr.length == 1) {
            return bArr[0];
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        for (byte[] bArr2 : bArr) {
            allocate.put(bArr2);
        }
        return allocate.array();
    }
}
