package okhttp3;

import java.nio.charset.Charset;
import okio.ByteString;
/* loaded from: picacg_1.jar:okhttp3/Credentials.class */
public final class Credentials {
    private Credentials() {
    }

    public static String basic(String str, String str2) {
        return basic(str, str2, Charset.forName("ISO-8859-1"));
    }

    public static String basic(String str, String str2, Charset charset) {
        String fQ = ByteString.l((str + ":" + str2).getBytes(charset)).fQ();
        return "Basic " + fQ;
    }
}
