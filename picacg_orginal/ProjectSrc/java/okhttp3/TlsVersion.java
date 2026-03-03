package okhttp3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: picacg_1.jar:okhttp3/TlsVersion.class */
public enum TlsVersion {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");
    
    final String javaName;

    TlsVersion(String str) {
        this.javaName = str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static TlsVersion forJavaName(String str) {
        boolean z;
        int hashCode = str.hashCode();
        if (hashCode == 79201641) {
            if (str.equals("SSLv3")) {
                z = true;
            }
            z = true;
        } else if (hashCode != 79923350) {
            switch (hashCode) {
                case -503070503:
                    if (str.equals("TLSv1.1")) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                case -503070502:
                    if (str.equals("TLSv1.2")) {
                        z = true;
                        break;
                    }
                    z = true;
                    break;
                case -503070501:
                    if (str.equals("TLSv1.3")) {
                        z = false;
                        break;
                    }
                    z = true;
                    break;
                default:
                    z = true;
                    break;
            }
        } else {
            if (str.equals("TLSv1")) {
                z = true;
            }
            z = true;
        }
        switch (z) {
            case false:
                return TLS_1_3;
            case true:
                return TLS_1_2;
            case true:
                return TLS_1_1;
            case true:
                return TLS_1_0;
            case true:
                return SSL_3_0;
            default:
                throw new IllegalArgumentException("Unexpected TLS version: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<TlsVersion> forJavaNames(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str : strArr) {
            arrayList.add(forJavaName(str));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String javaName() {
        return this.javaName;
    }
}
