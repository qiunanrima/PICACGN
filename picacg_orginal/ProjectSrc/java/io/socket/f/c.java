package io.socket.f;

import io.socket.f.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONTokener;
/* loaded from: picacg_1.jar:io/socket/f/c.class */
public class c {
    private static final Logger logger = Logger.getLogger(c.class.getName());
    public static int wB = 4;
    public static String[] zV = {"CONNECT", "DISCONNECT", "EVENT", "ACK", "ERROR", "BINARY_EVENT", "BINARY_ACK"};

    /* loaded from: picacg_1.jar:io/socket/f/c$a.class */
    static class a {
        public io.socket.f.b zW;
        List<byte[]> zX = new ArrayList();

        a(io.socket.f.b bVar) {
            this.zW = bVar;
        }

        public void fo() {
            this.zW = null;
            this.zX = new ArrayList();
        }

        public io.socket.f.b g(byte[] bArr) {
            this.zX.add(bArr);
            if (this.zX.size() == this.zW.zU) {
                io.socket.f.b a = io.socket.f.a.a(this.zW, (byte[][]) this.zX.toArray(new byte[this.zX.size()]));
                fo();
                return a;
            }
            return null;
        }
    }

    /* loaded from: picacg_1.jar:io/socket/f/c$b.class */
    public static class b extends io.socket.b.a {
        public static String zY = "decoded";
        a zZ = null;

        /* JADX WARN: Type inference failed for: r1v31, types: [T, java.lang.Object] */
        private static io.socket.f.b aS(String str) {
            int i;
            int i2;
            io.socket.f.b bVar = new io.socket.f.b();
            int length = str.length();
            bVar.type = Character.getNumericValue(str.charAt(0));
            if (bVar.type < 0 || bVar.type > c.zV.length - 1) {
                return c.fn();
            }
            if (5 != bVar.type && 6 != bVar.type) {
                i = 0;
            } else if (!str.contains("-") || length <= 1) {
                return c.fn();
            } else {
                StringBuilder sb = new StringBuilder();
                i = 0;
                while (true) {
                    i++;
                    if (str.charAt(i) == '-') {
                        break;
                    }
                    sb.append(str.charAt(i));
                }
                bVar.zU = Integer.parseInt(sb.toString());
            }
            int i3 = i + 1;
            if (length <= i3 || '/' != str.charAt(i3)) {
                bVar.xC = "/";
            } else {
                StringBuilder sb2 = new StringBuilder();
                int i4 = i;
                do {
                    i = i4 + 1;
                    char charAt = str.charAt(i);
                    if (',' == charAt) {
                        break;
                    }
                    sb2.append(charAt);
                    i4 = i;
                } while (i + 1 != length);
                bVar.xC = sb2.toString();
            }
            int i5 = i + 1;
            int i6 = i;
            if (length > i5) {
                i6 = i;
                if (Character.getNumericValue(Character.valueOf(str.charAt(i5)).charValue()) > -1) {
                    StringBuilder sb3 = new StringBuilder();
                    while (true) {
                        int i7 = i + 1;
                        char charAt2 = str.charAt(i7);
                        if (Character.getNumericValue(charAt2) >= 0) {
                            sb3.append(charAt2);
                            i = i7;
                            if (i7 + 1 == length) {
                                i2 = i7;
                                break;
                            }
                        } else {
                            i2 = i7 - 1;
                            break;
                        }
                    }
                    try {
                        bVar.id = Integer.parseInt(sb3.toString());
                        i6 = i2;
                    } catch (NumberFormatException e) {
                        return c.fn();
                    }
                }
            }
            int i8 = i6 + 1;
            if (length > i8) {
                try {
                    str.charAt(i8);
                    bVar.data = new JSONTokener(str.substring(i8)).nextValue();
                } catch (JSONException e2) {
                    c.logger.log(Level.WARNING, "An error occured while retrieving data from JSONTokener", (Throwable) e2);
                    return c.fn();
                }
            }
            c.logger.fine(String.format("decoded %s as %s", str, bVar));
            return bVar;
        }

        public void add(String str) {
            io.socket.f.b aS = aS(str);
            if (5 != aS.type && 6 != aS.type) {
                b(zY, aS);
                return;
            }
            this.zZ = new a(aS);
            if (this.zZ.zW.zU == 0) {
                b(zY, aS);
            }
        }

        public void destroy() {
            if (this.zZ != null) {
                this.zZ.fo();
            }
        }

        public void h(byte[] bArr) {
            if (this.zZ == null) {
                throw new RuntimeException("got binary data when not reconstructing a packet");
            }
            io.socket.f.b g = this.zZ.g(bArr);
            if (g != null) {
                this.zZ = null;
                b(zY, g);
            }
        }
    }

    /* renamed from: io.socket.f.c$c  reason: collision with other inner class name */
    /* loaded from: picacg_1.jar:io/socket/f/c$c.class */
    public static class C0027c {

        /* renamed from: io.socket.f.c$c$a */
        /* loaded from: picacg_1.jar:io/socket/f/c$c$a.class */
        public interface a {
            void a(Object[] objArr);
        }

        private void b(io.socket.f.b bVar, a aVar) {
            a.C0026a f = io.socket.f.a.f(bVar);
            String g = g(f.zS);
            ArrayList arrayList = new ArrayList(Arrays.asList(f.zT));
            arrayList.add(0, g);
            aVar.a(arrayList.toArray());
        }

        private String g(io.socket.f.b bVar) {
            boolean z;
            StringBuilder sb = new StringBuilder();
            sb.append(bVar.type);
            if (5 == bVar.type || 6 == bVar.type) {
                sb.append(bVar.zU);
                sb.append("-");
            }
            if (bVar.xC == null || bVar.xC.length() == 0 || "/".equals(bVar.xC)) {
                z = false;
            } else {
                sb.append(bVar.xC);
                z = true;
            }
            boolean z2 = z;
            if (bVar.id >= 0) {
                z2 = z;
                if (z) {
                    sb.append(",");
                    z2 = false;
                }
                sb.append(bVar.id);
            }
            if (bVar.data != 0) {
                if (z2) {
                    sb.append(",");
                }
                sb.append(bVar.data);
            }
            c.logger.fine(String.format("encoded %s as %s", bVar, sb));
            return sb.toString();
        }

        public void a(io.socket.f.b bVar, a aVar) {
            c.logger.fine(String.format("encoding packet %s", bVar));
            if (5 == bVar.type || 6 == bVar.type) {
                b(bVar, aVar);
            } else {
                aVar.a(new String[]{g(bVar)});
            }
        }
    }

    private c() {
    }

    private static io.socket.f.b<String> fm() {
        return new io.socket.f.b<>(4, "parser error");
    }

    static /* synthetic */ io.socket.f.b fn() {
        return fm();
    }
}
