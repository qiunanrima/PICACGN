package io.socket.engineio.client;

import io.socket.b.a;
import io.socket.engineio.client.Transport;
import io.socket.engineio.client.a.c;
import io.socket.engineio.parser.b;
import java.net.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import org.json.JSONException;
/* loaded from: picacg_1.jar:io/socket/engineio/client/Socket.class */
public class Socket extends io.socket.b.a {
    private static SSLContext wE;
    private static HostnameVerifier wF;
    String hostname;
    private HostnameVerifier hostnameVerifier;
    private String id;
    private String path;
    int port;
    public Proxy proxy;
    private boolean secure;
    private long xY;
    private long xZ;
    private boolean yb;
    private boolean yc;
    private boolean yd;
    private boolean ye;
    private int yf;
    private int yg;
    private String yh;
    private List<String> yi;
    private List<String> yj;
    private Map<String, String> yk;
    LinkedList<b> yl;
    Transport ym;
    private Future yn;
    private Future yo;
    private SSLContext yp;
    public String yq;
    public String yr;
    private ReadyState ys;
    private ScheduledExecutorService yt;
    private final a.InterfaceC0017a yu;
    private static final Logger logger = Logger.getLogger(Socket.class.getName());
    private static boolean ya = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.socket.engineio.client.Socket$18  reason: invalid class name */
    /* loaded from: picacg_1.jar:io/socket/engineio/client/Socket$18.class */
    public class AnonymousClass18 implements a.InterfaceC0017a {
        final /* synthetic */ String val$name;
        final /* synthetic */ Socket yB;
        final /* synthetic */ boolean[] yP;
        final /* synthetic */ Runnable[] yQ;
        final /* synthetic */ Transport[] yx;

        AnonymousClass18(boolean[] zArr, String str, Transport[] transportArr, Socket socket, Runnable[] runnableArr) {
            this.yP = zArr;
            this.val$name = str;
            this.yx = transportArr;
            this.yB = socket;
            this.yQ = runnableArr;
        }

        @Override // io.socket.b.a.InterfaceC0017a
        public void a(Object... objArr) {
            if (this.yP[0]) {
                return;
            }
            Socket.logger.fine(String.format("probe transport '%s' opened", this.val$name));
            this.yx[0].a(new b[]{new b("ping", "probe")});
            this.yx[0].b("packet", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.18.1
                @Override // io.socket.b.a.InterfaceC0017a
                public void a(Object... objArr2) {
                    if (AnonymousClass18.this.yP[0]) {
                        return;
                    }
                    b bVar = (b) objArr2[0];
                    if (!"pong".equals(bVar.type) || !"probe".equals(bVar.data)) {
                        Socket.logger.fine(String.format("probe transport '%s' failed", AnonymousClass18.this.val$name));
                        EngineIOException engineIOException = new EngineIOException("probe error");
                        engineIOException.xU = AnonymousClass18.this.yx[0].name;
                        AnonymousClass18.this.yB.b("upgradeError", engineIOException);
                        return;
                    }
                    Socket.logger.fine(String.format("probe transport '%s' pong", AnonymousClass18.this.val$name));
                    AnonymousClass18.this.yB.yd = true;
                    AnonymousClass18.this.yB.b("upgrading", AnonymousClass18.this.yx[0]);
                    if (AnonymousClass18.this.yx[0] == null) {
                        return;
                    }
                    boolean unused = Socket.ya = "websocket".equals(AnonymousClass18.this.yx[0].name);
                    Socket.logger.fine(String.format("pausing current transport '%s'", AnonymousClass18.this.yB.ym.name));
                    ((io.socket.engineio.client.a.a) AnonymousClass18.this.yB.ym).a(new Runnable() { // from class: io.socket.engineio.client.Socket.18.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (AnonymousClass18.this.yP[0] || ReadyState.CLOSED == AnonymousClass18.this.yB.ys) {
                                return;
                            }
                            Socket.logger.fine("changing transport and sending upgrade packet");
                            AnonymousClass18.this.yQ[0].run();
                            AnonymousClass18.this.yB.a(AnonymousClass18.this.yx[0]);
                            AnonymousClass18.this.yx[0].a(new b[]{new b("upgrade")});
                            AnonymousClass18.this.yB.b("upgrade", AnonymousClass18.this.yx[0]);
                            AnonymousClass18.this.yx[0] = null;
                            AnonymousClass18.this.yB.yd = false;
                            AnonymousClass18.this.yB.flush();
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:io/socket/engineio/client/Socket$ReadyState.class */
    public enum ReadyState {
        OPENING,
        OPEN,
        CLOSING,
        CLOSED;

        @Override // java.lang.Enum
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    /* loaded from: picacg_1.jar:io/socket/engineio/client/Socket$a.class */
    public static class a extends Transport.a {
        public String host;
        public String[] yT;
        public String yU;
        public boolean yb = true;
        public boolean ye;

        /* JADX INFO: Access modifiers changed from: private */
        public static a a(URI uri, a aVar) {
            a aVar2 = aVar;
            if (aVar == null) {
                aVar2 = new a();
            }
            aVar2.host = uri.getHost();
            aVar2.secure = "https".equals(uri.getScheme()) || "wss".equals(uri.getScheme());
            aVar2.port = uri.getPort();
            String rawQuery = uri.getRawQuery();
            if (rawQuery != null) {
                aVar2.yU = rawQuery;
            }
            return aVar2;
        }
    }

    public Socket() {
        this(new a());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Socket(a aVar) {
        this.yl = new LinkedList<>();
        this.yu = new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.1
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                Socket.this.j(objArr.length > 0 ? ((Long) objArr[0]).longValue() : 0L);
            }
        };
        if (aVar.host != null) {
            String str = aVar.host;
            String str2 = str;
            if (str.split(":").length > 2) {
                int indexOf = str.indexOf(91);
                String substring = indexOf != -1 ? str.substring(indexOf + 1) : str;
                int lastIndexOf = substring.lastIndexOf(93);
                str2 = substring;
                if (lastIndexOf != -1) {
                    str2 = substring.substring(0, lastIndexOf);
                }
            }
            aVar.hostname = str2;
        }
        this.secure = aVar.secure;
        if (aVar.port == -1) {
            aVar.port = this.secure ? 443 : 80;
        }
        this.yp = aVar.yp != null ? aVar.yp : wE;
        this.hostname = aVar.hostname != null ? aVar.hostname : "localhost";
        this.port = aVar.port;
        this.yk = aVar.yU != null ? io.socket.e.a.aR(aVar.yU) : new HashMap();
        this.yb = aVar.yb;
        StringBuilder sb = new StringBuilder();
        sb.append((aVar.path != null ? aVar.path : "/engine.io").replaceAll("/$", ""));
        sb.append("/");
        this.path = sb.toString();
        this.yh = aVar.yh != null ? aVar.yh : "t";
        this.yc = aVar.yc;
        this.yi = new ArrayList(Arrays.asList(aVar.yT != null ? aVar.yT : new String[]{"polling", "websocket"}));
        this.yf = aVar.yf != 0 ? aVar.yf : 843;
        this.ye = aVar.ye;
        this.hostnameVerifier = aVar.hostnameVerifier != null ? aVar.hostnameVerifier : wF;
        this.proxy = aVar.proxy;
        this.yq = aVar.yq;
        this.yr = aVar.yr;
    }

    public Socket(URI uri, a aVar) {
        this(uri != null ? a.a(uri, aVar) : aVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Transport transport) {
        logger.fine(String.format("setting transport %s", transport.name));
        if (this.ym != null) {
            logger.fine(String.format("clearing existing transport %s", this.ym.name));
            this.ym.eQ();
        }
        this.ym = transport;
        transport.a("drain", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.17
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                this.eV();
            }
        }).a("packet", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.16
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                this.a(objArr.length > 0 ? (b) objArr[0] : null);
            }
        }).a("error", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.15
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                this.c(objArr.length > 0 ? (Exception) objArr[0] : null);
            }
        }).a("close", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.14
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                this.aK("transport close");
            }
        });
    }

    private void a(io.socket.engineio.client.a aVar) {
        b("handshake", aVar);
        this.id = aVar.xW;
        this.ym.yk.put("sid", aVar.xW);
        this.yj = b(Arrays.asList(aVar.xX));
        this.xY = aVar.xY;
        this.xZ = aVar.xZ;
        eS();
        if (ReadyState.CLOSED == this.ys) {
            return;
        }
        eT();
        c("heartbeat", this.yu);
        a("heartbeat", this.yu);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(b bVar) {
        if (this.ys != ReadyState.OPENING && this.ys != ReadyState.OPEN) {
            logger.fine(String.format("packet received with socket readyState '%s'", this.ys));
            return;
        }
        logger.fine(String.format("socket received: type '%s', data '%s'", bVar.type, bVar.data));
        b("packet", bVar);
        b("heartbeat", new Object[0]);
        if ("open".equals(bVar.type)) {
            try {
                a(new io.socket.engineio.client.a((String) bVar.data));
            } catch (JSONException e) {
                b("error", new EngineIOException(e));
            }
        } else if ("pong".equals(bVar.type)) {
            eT();
            b("pong", new Object[0]);
        } else if ("error".equals(bVar.type)) {
            EngineIOException engineIOException = new EngineIOException("server error");
            engineIOException.xV = bVar.data;
            c(engineIOException);
        } else if ("message".equals(bVar.type)) {
            b("data", bVar.data);
            b("message", bVar.data);
        }
    }

    private void a(b bVar, final Runnable runnable) {
        if (ReadyState.CLOSING == this.ys || ReadyState.CLOSED == this.ys) {
            return;
        }
        b("packetCreate", bVar);
        this.yl.offer(bVar);
        if (runnable != null) {
            b("flush", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.11
                @Override // io.socket.b.a.InterfaceC0017a
                public void a(Object... objArr) {
                    runnable.run();
                }
            });
        }
        flush();
    }

    private void a(String str, Exception exc) {
        if (ReadyState.OPENING == this.ys || ReadyState.OPEN == this.ys || ReadyState.CLOSING == this.ys) {
            logger.fine(String.format("socket close with reason: %s", str));
            if (this.yo != null) {
                this.yo.cancel(false);
            }
            if (this.yn != null) {
                this.yn.cancel(false);
            }
            if (this.yt != null) {
                this.yt.shutdown();
            }
            this.ym.aH("close");
            this.ym.fb();
            this.ym.eQ();
            this.ys = ReadyState.CLOSED;
            this.id = null;
            b("close", str, exc);
            this.yl.clear();
            this.yg = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, Runnable runnable) {
        a(new b(str, str2), runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, byte[] bArr, Runnable runnable) {
        a(new b(str, bArr), runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v31, types: [io.socket.engineio.client.Transport] */
    public Transport aI(String str) {
        c bVar;
        logger.fine(String.format("creating transport '%s'", str));
        HashMap hashMap = new HashMap(this.yk);
        hashMap.put("EIO", String.valueOf(3));
        hashMap.put("transport", str);
        if (this.id != null) {
            hashMap.put("sid", this.id);
        }
        Transport.a aVar = new Transport.a();
        aVar.yp = this.yp;
        aVar.hostname = this.hostname;
        aVar.port = this.port;
        aVar.secure = this.secure;
        aVar.path = this.path;
        aVar.yk = hashMap;
        aVar.yc = this.yc;
        aVar.yh = this.yh;
        aVar.yf = this.yf;
        aVar.zb = this;
        aVar.hostnameVerifier = this.hostnameVerifier;
        aVar.proxy = this.proxy;
        aVar.yq = this.yq;
        aVar.yr = this.yr;
        if ("websocket".equals(str)) {
            bVar = new c(aVar);
        } else if (!"polling".equals(str)) {
            throw new RuntimeException();
        } else {
            bVar = new io.socket.engineio.client.a.b(aVar);
        }
        b("transport", bVar);
        return bVar;
    }

    private void aJ(final String str) {
        logger.fine(String.format("probing transport '%s'", str));
        final Transport[] transportArr = {aI(str)};
        final boolean[] zArr = {false};
        ya = false;
        final AnonymousClass18 anonymousClass18 = new AnonymousClass18(zArr, str, transportArr, this, r0);
        final a.InterfaceC0017a interfaceC0017a = new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.19
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                if (zArr[0]) {
                    return;
                }
                zArr[0] = true;
                r6[0].run();
                transportArr[0].fb();
                transportArr[0] = null;
            }
        };
        final a.InterfaceC0017a interfaceC0017a2 = new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.20
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                EngineIOException engineIOException;
                Object obj = objArr[0];
                if (obj instanceof Exception) {
                    engineIOException = new EngineIOException("probe error", (Exception) obj);
                } else if (obj instanceof String) {
                    engineIOException = new EngineIOException("probe error: " + ((String) obj));
                } else {
                    engineIOException = new EngineIOException("probe error");
                }
                engineIOException.xU = transportArr[0].name;
                interfaceC0017a.a(new Object[0]);
                Socket.logger.fine(String.format("probe transport \"%s\" failed because of error: %s", str, obj));
                this.b("upgradeError", engineIOException);
            }
        };
        final a.InterfaceC0017a interfaceC0017a3 = new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.2
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                interfaceC0017a2.a("transport closed");
            }
        };
        final a.InterfaceC0017a interfaceC0017a4 = new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.3
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                interfaceC0017a2.a("socket closed");
            }
        };
        final a.InterfaceC0017a interfaceC0017a5 = new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.4
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                Transport transport = (Transport) objArr[0];
                if (transportArr[0] == null || transport.name.equals(transportArr[0].name)) {
                    return;
                }
                Socket.logger.fine(String.format("'%s' works - aborting '%s'", transport.name, transportArr[0].name));
                interfaceC0017a.a(new Object[0]);
            }
        };
        final Runnable[] runnableArr = {new Runnable() { // from class: io.socket.engineio.client.Socket.5
            @Override // java.lang.Runnable
            public void run() {
                transportArr[0].c("open", anonymousClass18);
                transportArr[0].c("error", interfaceC0017a2);
                transportArr[0].c("close", interfaceC0017a3);
                this.c("close", interfaceC0017a4);
                this.c("upgrading", interfaceC0017a5);
            }
        }};
        transportArr[0].b("open", anonymousClass18);
        transportArr[0].b("error", interfaceC0017a2);
        transportArr[0].b("close", interfaceC0017a3);
        b("close", interfaceC0017a4);
        b("upgrading", interfaceC0017a5);
        transportArr[0].fa();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aK(String str) {
        a(str, (Exception) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Exception exc) {
        logger.fine(String.format("socket error %s", exc));
        ya = false;
        b("error", exc);
        a("transport error", exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, Runnable runnable) {
        a(new b(str), runnable);
    }

    private void eS() {
        logger.fine("socket open");
        this.ys = ReadyState.OPEN;
        ya = "websocket".equals(this.ym.name);
        b("open", new Object[0]);
        flush();
        if (this.ys == ReadyState.OPEN && this.yb && (this.ym instanceof io.socket.engineio.client.a.a)) {
            logger.fine("starting upgrade probes");
            for (String str : this.yj) {
                aJ(str);
            }
        }
    }

    private void eT() {
        if (this.yo != null) {
            this.yo.cancel(false);
        }
        this.yo = eX().schedule(new Runnable() { // from class: io.socket.engineio.client.Socket.7
            @Override // java.lang.Runnable
            public void run() {
                io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.Socket.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Socket.logger.fine(String.format("writing ping packet - expecting pong within %sms", Long.valueOf(this.xZ)));
                        this.eU();
                        this.j(this.xZ);
                    }
                });
            }
        }, this.xY, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eU() {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.Socket.8
            @Override // java.lang.Runnable
            public void run() {
                Socket.this.c("ping", new Runnable() { // from class: io.socket.engineio.client.Socket.8.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Socket.this.b("ping", new Object[0]);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eV() {
        for (int i = 0; i < this.yg; i++) {
            this.yl.poll();
        }
        this.yg = 0;
        if (this.yl.size() == 0) {
            b("drain", new Object[0]);
        } else {
            flush();
        }
    }

    private ScheduledExecutorService eX() {
        if (this.yt == null || this.yt.isShutdown()) {
            this.yt = Executors.newSingleThreadScheduledExecutor();
        }
        return this.yt;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flush() {
        if (this.ys == ReadyState.CLOSED || !this.ym.za || this.yd || this.yl.size() == 0) {
            return;
        }
        logger.fine(String.format("flushing %d packets in socket", Integer.valueOf(this.yl.size())));
        this.yg = this.yl.size();
        this.ym.a((b[]) this.yl.toArray(new b[this.yl.size()]));
        b("flush", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(long j) {
        if (this.yn != null) {
            this.yn.cancel(false);
        }
        long j2 = j;
        if (j <= 0) {
            j2 = this.xY + this.xZ;
        }
        this.yn = eX().schedule(new Runnable() { // from class: io.socket.engineio.client.Socket.6
            @Override // java.lang.Runnable
            public void run() {
                io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.Socket.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (this.ys == ReadyState.CLOSED) {
                            return;
                        }
                        this.aK("ping timeout");
                    }
                });
            }
        }, j2, TimeUnit.MILLISECONDS);
    }

    public void a(String str, Runnable runnable) {
        b(str, runnable);
    }

    public void a(byte[] bArr, Runnable runnable) {
        b(bArr, runnable);
    }

    List<String> b(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (this.yi.contains(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public void b(final String str, final Runnable runnable) {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.Socket.9
            @Override // java.lang.Runnable
            public void run() {
                Socket.this.a("message", str, runnable);
            }
        });
    }

    public void b(final byte[] bArr, final Runnable runnable) {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.Socket.10
            @Override // java.lang.Runnable
            public void run() {
                Socket.this.a("message", bArr, runnable);
            }
        });
    }

    public Socket eR() {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.Socket.12
            @Override // java.lang.Runnable
            public void run() {
                String str;
                if (Socket.this.ye && Socket.ya && Socket.this.yi.contains("websocket")) {
                    str = "websocket";
                } else if (Socket.this.yi.size() == 0) {
                    final Socket socket = Socket.this;
                    io.socket.g.a.c(new Runnable() { // from class: io.socket.engineio.client.Socket.12.1
                        @Override // java.lang.Runnable
                        public void run() {
                            socket.b("error", new EngineIOException("No transports available"));
                        }
                    });
                    return;
                } else {
                    str = (String) Socket.this.yi.get(0);
                }
                Socket.this.ys = ReadyState.OPENING;
                Transport aI = Socket.this.aI(str);
                Socket.this.a(aI);
                aI.fa();
            }
        });
        return this;
    }

    public Socket eW() {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.Socket.13
            @Override // java.lang.Runnable
            public void run() {
                if (Socket.this.ys == ReadyState.OPENING || Socket.this.ys == ReadyState.OPEN) {
                    Socket.this.ys = ReadyState.CLOSING;
                    final Socket socket = Socket.this;
                    final Runnable runnable = new Runnable() { // from class: io.socket.engineio.client.Socket.13.1
                        @Override // java.lang.Runnable
                        public void run() {
                            socket.aK("forced close");
                            Socket.logger.fine("socket closing - telling transport to close");
                            socket.ym.fb();
                        }
                    };
                    final a.InterfaceC0017a[] interfaceC0017aArr = {new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.13.2
                        @Override // io.socket.b.a.InterfaceC0017a
                        public void a(Object... objArr) {
                            socket.c("upgrade", interfaceC0017aArr[0]);
                            socket.c("upgradeError", interfaceC0017aArr[0]);
                            runnable.run();
                        }
                    }};
                    final Runnable runnable2 = new Runnable() { // from class: io.socket.engineio.client.Socket.13.3
                        @Override // java.lang.Runnable
                        public void run() {
                            socket.b("upgrade", interfaceC0017aArr[0]);
                            socket.b("upgradeError", interfaceC0017aArr[0]);
                        }
                    };
                    if (Socket.this.yl.size() > 0) {
                        Socket.this.b("drain", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.Socket.13.4
                            @Override // io.socket.b.a.InterfaceC0017a
                            public void a(Object... objArr) {
                                if (Socket.this.yd) {
                                    runnable2.run();
                                } else {
                                    runnable.run();
                                }
                            }
                        });
                    } else if (Socket.this.yd) {
                        runnable2.run();
                    } else {
                        runnable.run();
                    }
                }
            }
        });
        return this;
    }

    public String id() {
        return this.id;
    }

    public void write(String str) {
        a(str, (Runnable) null);
    }

    public void write(byte[] bArr) {
        a(bArr, (Runnable) null);
    }
}
