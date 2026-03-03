package io.socket.client;

import java.net.URL;
import java.util.regex.Pattern;
/* loaded from: picacg_1.jar:io/socket/client/e.class */
public class e {
    private static Pattern xO = Pattern.compile("^http|ws$");
    private static Pattern xP = Pattern.compile("^(http|ws)s$");

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0060, code lost:
        if (r0.length() == 0) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        if (r0.matches("^https?|wss?$") == false) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.net.URL a(java.net.URI r4) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.socket.client.e.a(java.net.URI):java.net.URL");
    }

    public static String b(URL url) {
        String protocol = url.getProtocol();
        int port = url.getPort();
        int i = port;
        if (port == -1) {
            if (xO.matcher(protocol).matches()) {
                i = 80;
            } else {
                i = port;
                if (xP.matcher(protocol).matches()) {
                    i = 443;
                }
            }
        }
        return protocol + "://" + url.getHost() + ":" + i;
    }
}
