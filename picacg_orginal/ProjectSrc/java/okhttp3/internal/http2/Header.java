package okhttp3.internal.http2;

import okhttp3.internal.Util;
import okio.ByteString;
/* loaded from: picacg_1.jar:okhttp3/internal/http2/Header.class */
public final class Header {
    final int hpackSize;
    public final ByteString name;
    public final ByteString value;
    public static final ByteString PSEUDO_PREFIX = ByteString.aW(":");
    public static final ByteString RESPONSE_STATUS = ByteString.aW(":status");
    public static final ByteString TARGET_METHOD = ByteString.aW(":method");
    public static final ByteString TARGET_PATH = ByteString.aW(":path");
    public static final ByteString TARGET_SCHEME = ByteString.aW(":scheme");
    public static final ByteString TARGET_AUTHORITY = ByteString.aW(":authority");

    public Header(String str, String str2) {
        this(ByteString.aW(str), ByteString.aW(str2));
    }

    public Header(ByteString byteString, String str) {
        this(byteString, ByteString.aW(str));
    }

    public Header(ByteString byteString, ByteString byteString2) {
        this.name = byteString;
        this.value = byteString2;
        this.hpackSize = byteString.size() + 32 + byteString2.size();
    }

    public boolean equals(Object obj) {
        if (obj instanceof Header) {
            Header header = (Header) obj;
            boolean z = false;
            if (this.name.equals(header.name)) {
                z = false;
                if (this.value.equals(header.value)) {
                    z = true;
                }
            }
            return z;
        }
        return false;
    }

    public int hashCode() {
        return ((527 + this.name.hashCode()) * 31) + this.value.hashCode();
    }

    public String toString() {
        return Util.format("%s: %s", this.name.fP(), this.value.fP());
    }
}
