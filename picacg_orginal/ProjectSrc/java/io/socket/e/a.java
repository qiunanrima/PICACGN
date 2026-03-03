package io.socket.e;

import java.util.HashMap;
import java.util.Map;
/* loaded from: picacg_1.jar:io/socket/e/a.class */
public class a {
    public static Map<String, String> aR(String str) {
        HashMap hashMap = new HashMap();
        for (String str2 : str.split("&")) {
            String[] split = str2.split("=");
            hashMap.put(io.socket.c.a.aQ(split[0]), split.length > 1 ? io.socket.c.a.aQ(split[1]) : "");
        }
        return hashMap;
    }

    public static String d(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(io.socket.c.a.aP(entry.getKey()));
            sb.append("=");
            sb.append(io.socket.c.a.aP(entry.getValue()));
        }
        return sb.toString();
    }
}
