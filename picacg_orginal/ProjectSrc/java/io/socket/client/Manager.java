package io.socket.client;

import io.socket.b.a;
import io.socket.client.c;
import io.socket.engineio.client.Socket;
import io.socket.f.c;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
/* loaded from: picacg_1.jar:io/socket/client/Manager.class */
public class Manager extends io.socket.b.a {
    private static final Logger logger = Logger.getLogger(Manager.class.getName());
    static SSLContext wE;
    static HostnameVerifier wF;
    ReadyState wG;
    private boolean wH;
    private boolean wI;
    private boolean wJ;
    private boolean wK;
    private int wL;
    private long wM;
    private long wN;
    private double wO;
    private io.socket.a.a wP;
    private long wQ;
    private Set<d> wR;
    private Date wS;
    private URI wT;
    private List<io.socket.f.b> wU;
    private Queue<c.a> wV;
    private c wW;
    Socket wX;
    private c.C0027c wY;
    private c.b wZ;
    ConcurrentHashMap<String, d> xa;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.socket.client.Manager$3  reason: invalid class name */
    /* loaded from: picacg_1.jar:io/socket/client/Manager$3.class */
    public class AnonymousClass3 extends TimerTask {
        final /* synthetic */ Manager xd;

        AnonymousClass3(Manager manager) {
            this.xd = manager;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            io.socket.g.a.b(new Runnable() { // from class: io.socket.client.Manager.3.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AnonymousClass3.this.xd.wI) {
                        return;
                    }
                    Manager.logger.fine("attempting reconnect");
                    int eu = AnonymousClass3.this.xd.wP.eu();
                    AnonymousClass3.this.xd.a("reconnect_attempt", Integer.valueOf(eu));
                    AnonymousClass3.this.xd.a("reconnecting", Integer.valueOf(eu));
                    if (AnonymousClass3.this.xd.wI) {
                        return;
                    }
                    AnonymousClass3.this.xd.a(new b() { // from class: io.socket.client.Manager.3.1.1
                        @Override // io.socket.client.Manager.b
                        public void b(Exception exc) {
                            if (exc == null) {
                                Manager.logger.fine("reconnect success");
                                AnonymousClass3.this.xd.eF();
                                return;
                            }
                            Manager.logger.fine("reconnect attempt error");
                            AnonymousClass3.this.xd.wJ = false;
                            AnonymousClass3.this.xd.reconnect();
                            AnonymousClass3.this.xd.a("reconnect_error", exc);
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: picacg_1.jar:io/socket/client/Manager$ReadyState.class */
    public enum ReadyState {
        CLOSED,
        OPENING,
        OPEN
    }

    /* loaded from: picacg_1.jar:io/socket/client/Manager$a.class */
    private static class a extends Socket {
        a(URI uri, Socket.a aVar) {
            super(uri, aVar);
        }
    }

    /* loaded from: picacg_1.jar:io/socket/client/Manager$b.class */
    public interface b {
        void b(Exception exc);
    }

    /* loaded from: picacg_1.jar:io/socket/client/Manager$c.class */
    public static class c extends Socket.a {
        public int xo;
        public long xp;
        public long xq;
        public double xr;
        public boolean xn = true;
        public long xs = 20000;
    }

    public Manager() {
        this(null, null);
    }

    public Manager(URI uri, c cVar) {
        this.wR = new HashSet();
        c cVar2 = cVar == null ? new c() : cVar;
        if (cVar2.path == null) {
            cVar2.path = "/socket.io";
        }
        if (cVar2.yp == null) {
            cVar2.yp = wE;
        }
        if (cVar2.hostnameVerifier == null) {
            cVar2.hostnameVerifier = wF;
        }
        this.wW = cVar2;
        this.xa = new ConcurrentHashMap<>();
        this.wV = new LinkedList();
        H(cVar2.xn);
        ah(cVar2.xo != 0 ? cVar2.xo : Integer.MAX_VALUE);
        g(cVar2.xp != 0 ? cVar2.xp : 1000L);
        h(cVar2.xq != 0 ? cVar2.xq : 5000L);
        b(cVar2.xr != 0.0d ? cVar2.xr : 0.5d);
        this.wP = new io.socket.a.a().e(ew()).f(ey()).a(ex());
        i(cVar2.xs);
        this.wG = ReadyState.CLOSED;
        this.wT = uri;
        this.wK = false;
        this.wU = new ArrayList();
        this.wY = new c.C0027c();
        this.wZ = new c.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(io.socket.f.b bVar) {
        b("packet", bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Exception exc) {
        logger.log(Level.FINE, "error", (Throwable) exc);
        a("error", exc);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Object... objArr) {
        b(str, objArr);
        for (d dVar : this.xa.values()) {
            dVar.b(str, objArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aF(String str) {
        this.wZ.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aG(String str) {
        logger.fine("onclose");
        cleanup();
        this.wP.reset();
        this.wG = ReadyState.CLOSED;
        b("close", str);
        if (!this.wH || this.wI) {
            return;
        }
        reconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte[] bArr) {
        this.wZ.h(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanup() {
        logger.fine("cleanup");
        while (true) {
            c.a poll = this.wV.poll();
            if (poll == null) {
                this.wU.clear();
                this.wK = false;
                this.wS = null;
                this.wZ.destroy();
                return;
            }
            poll.destroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eB() {
        logger.fine("open");
        cleanup();
        this.wG = ReadyState.OPEN;
        b("open", new Object[0]);
        Socket socket = this.wX;
        this.wV.add(io.socket.client.c.a(socket, "data", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.5
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                Object obj = objArr[0];
                if (obj instanceof String) {
                    Manager.this.aF((String) obj);
                } else if (obj instanceof byte[]) {
                    Manager.this.b((byte[]) obj);
                }
            }
        }));
        this.wV.add(io.socket.client.c.a(socket, "ping", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.6
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                Manager.this.eC();
            }
        }));
        this.wV.add(io.socket.client.c.a(socket, "pong", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.7
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                Manager.this.eD();
            }
        }));
        this.wV.add(io.socket.client.c.a(socket, "error", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.8
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                Manager.this.a((Exception) objArr[0]);
            }
        }));
        this.wV.add(io.socket.client.c.a(socket, "close", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.9
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                Manager.this.aG((String) objArr[0]);
            }
        }));
        this.wV.add(io.socket.client.c.a(this.wZ, c.b.zY, new a.InterfaceC0017a() { // from class: io.socket.client.Manager.10
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                Manager.this.a((io.socket.f.b) objArr[0]);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eC() {
        this.wS = new Date();
        a("ping", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eD() {
        a("pong", Long.valueOf(this.wS != null ? new Date().getTime() - this.wS.getTime() : 0L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eE() {
        if (this.wU.isEmpty() || this.wK) {
            return;
        }
        b(this.wU.remove(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void eF() {
        int eu = this.wP.eu();
        this.wJ = false;
        this.wP.reset();
        ev();
        a("reconnect", Integer.valueOf(eu));
    }

    private void ev() {
        for (d dVar : this.xa.values()) {
            dVar.id = this.wX.id();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ez() {
        if (!this.wJ && this.wH && this.wP.eu() == 0) {
            reconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnect() {
        if (this.wJ || this.wI) {
            return;
        }
        if (this.wP.eu() >= this.wL) {
            logger.fine("reconnect failed");
            this.wP.reset();
            a("reconnect_failed", new Object[0]);
            this.wJ = false;
            return;
        }
        long et = this.wP.et();
        logger.fine(String.format("will wait %dms before reconnect attempt", Long.valueOf(et)));
        this.wJ = true;
        final Timer timer = new Timer();
        timer.schedule(new AnonymousClass3(this), et);
        this.wV.add(new c.a() { // from class: io.socket.client.Manager.4
            @Override // io.socket.client.c.a
            public void destroy() {
                timer.cancel();
            }
        });
    }

    public Manager H(boolean z) {
        this.wH = z;
        return this;
    }

    public Manager a(final b bVar) {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.client.Manager.1
            @Override // java.lang.Runnable
            public void run() {
                Manager.logger.fine(String.format("readyState %s", Manager.this.wG));
                if (Manager.this.wG == ReadyState.OPEN || Manager.this.wG == ReadyState.OPENING) {
                    return;
                }
                Manager.logger.fine(String.format("opening %s", Manager.this.wT));
                Manager.this.wX = new a(Manager.this.wT, Manager.this.wW);
                final Socket socket = Manager.this.wX;
                final Manager manager = Manager.this;
                Manager.this.wG = ReadyState.OPENING;
                Manager.this.wI = false;
                socket.a("transport", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.1.1
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        manager.b("transport", objArr);
                    }
                });
                final c.a a2 = io.socket.client.c.a(socket, "open", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.1.2
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        manager.eB();
                        if (bVar != null) {
                            bVar.b(null);
                        }
                    }
                });
                c.a a3 = io.socket.client.c.a(socket, "error", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.1.3
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        Exception exc = null;
                        Exception exc2 = objArr.length > 0 ? objArr[0] : null;
                        Manager.logger.fine("connect_error");
                        manager.cleanup();
                        manager.wG = ReadyState.CLOSED;
                        manager.a("connect_error", exc2);
                        if (bVar == null) {
                            manager.ez();
                            return;
                        }
                        if (exc2 instanceof Exception) {
                            exc = exc2;
                        }
                        bVar.b(new SocketIOException("Connection error", exc));
                    }
                });
                if (Manager.this.wQ >= 0) {
                    final long j = Manager.this.wQ;
                    Manager.logger.fine(String.format("connection attempt will timeout after %d", Long.valueOf(j)));
                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() { // from class: io.socket.client.Manager.1.4
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            io.socket.g.a.b(new Runnable() { // from class: io.socket.client.Manager.1.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Manager.logger.fine(String.format("connect attempt timed out after %d", Long.valueOf(j)));
                                    a2.destroy();
                                    socket.eW();
                                    socket.b("error", new SocketIOException("timeout"));
                                    manager.a("connect_timeout", Long.valueOf(j));
                                }
                            });
                        }
                    }, j);
                    Manager.this.wV.add(new c.a() { // from class: io.socket.client.Manager.1.5
                        @Override // io.socket.client.c.a
                        public void destroy() {
                            timer.cancel();
                        }
                    });
                }
                Manager.this.wV.add(a2);
                Manager.this.wV.add(a3);
                Manager.this.wX.eR();
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(d dVar) {
        this.wR.remove(dVar);
        if (this.wR.isEmpty()) {
            close();
        }
    }

    public d aE(String str) {
        d dVar = this.xa.get(str);
        final d dVar2 = dVar;
        if (dVar == null) {
            dVar2 = new d(this, str);
            d putIfAbsent = this.xa.putIfAbsent(str, dVar2);
            if (putIfAbsent != null) {
                dVar2 = putIfAbsent;
            } else {
                dVar2.a("connecting", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.11
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        this.wR.add(dVar2);
                    }
                });
                dVar2.a("connect", new a.InterfaceC0017a() { // from class: io.socket.client.Manager.12
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        dVar2.id = this.wX.id();
                    }
                });
            }
        }
        return dVar2;
    }

    public Manager ah(int i) {
        this.wL = i;
        return this;
    }

    public Manager b(double d) {
        this.wO = d;
        if (this.wP != null) {
            this.wP.a(d);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(io.socket.f.b bVar) {
        logger.fine(String.format("writing packet %s", bVar));
        if (this.wK) {
            this.wU.add(bVar);
            return;
        }
        this.wK = true;
        this.wY.a(bVar, new c.C0027c.a() { // from class: io.socket.client.Manager.2
            @Override // io.socket.f.c.C0027c.a
            public void a(Object[] objArr) {
                for (Object obj : objArr) {
                    if (obj instanceof String) {
                        this.wX.write((String) obj);
                    } else if (obj instanceof byte[]) {
                        this.wX.write((byte[]) obj);
                    }
                }
                this.wK = false;
                this.eE();
            }
        });
    }

    void close() {
        logger.fine("disconnect");
        this.wI = true;
        this.wJ = false;
        if (this.wG != ReadyState.OPEN) {
            cleanup();
        }
        this.wP.reset();
        this.wG = ReadyState.CLOSED;
        if (this.wX != null) {
            this.wX.eW();
        }
    }

    public Manager eA() {
        return a((b) null);
    }

    public final long ew() {
        return this.wM;
    }

    public final double ex() {
        return this.wO;
    }

    public final long ey() {
        return this.wN;
    }

    public Manager g(long j) {
        this.wM = j;
        if (this.wP != null) {
            this.wP.e(j);
        }
        return this;
    }

    public Manager h(long j) {
        this.wN = j;
        if (this.wP != null) {
            this.wP.f(j);
        }
        return this;
    }

    public Manager i(long j) {
        this.wQ = j;
        return this;
    }
}
