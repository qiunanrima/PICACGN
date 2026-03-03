package io.socket.engineio.client.a;

import io.socket.b.a;
import io.socket.engineio.client.Transport;
import io.socket.engineio.parser.c;
import io.socket.utf8.UTF8Exception;
import java.util.logging.Logger;
/* loaded from: picacg_1.jar:io/socket/engineio/client/a/a.class */
public abstract class a extends Transport {
    private static final Logger logger = Logger.getLogger(a.class.getName());
    private boolean zk;

    public a(Transport.a aVar) {
        super(aVar);
        this.name = "polling";
    }

    private void c(Object obj) {
        logger.fine(String.format("polling got data %s", obj));
        c.a aVar = new c.a() { // from class: io.socket.engineio.client.a.a.2
            @Override // io.socket.engineio.parser.c.a
            public boolean a(io.socket.engineio.parser.b bVar, int i, int i2) {
                if (this.zc == Transport.ReadyState.OPENING) {
                    this.eS();
                }
                if ("close".equals(bVar.type)) {
                    this.onClose();
                    return false;
                }
                this.a(bVar);
                return true;
            }
        };
        if (obj instanceof String) {
            io.socket.engineio.parser.c.a((String) obj, aVar);
        } else if (obj instanceof byte[]) {
            io.socket.engineio.parser.c.a((byte[]) obj, aVar);
        }
        if (this.zc != Transport.ReadyState.CLOSED) {
            this.zk = false;
            b("pollComplete", new Object[0]);
            if (this.zc == Transport.ReadyState.OPEN) {
                fe();
            } else {
                logger.fine(String.format("ignoring poll - transport state '%s'", this.zc));
            }
        }
    }

    private void fe() {
        logger.fine("polling");
        this.zk = true;
        fg();
        b("poll", new Object[0]);
    }

    public void a(final Runnable runnable) {
        io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.a.a.1
            @Override // java.lang.Runnable
            public void run() {
                final a aVar = a.this;
                a.this.zc = Transport.ReadyState.PAUSED;
                final Runnable runnable2 = new Runnable() { // from class: io.socket.engineio.client.a.a.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.logger.fine("paused");
                        aVar.zc = Transport.ReadyState.PAUSED;
                        runnable.run();
                    }
                };
                if (!a.this.zk && a.this.za) {
                    runnable2.run();
                    return;
                }
                final int[] iArr = {0};
                if (a.this.zk) {
                    a.logger.fine("we are currently polling - waiting to pause");
                    iArr[0] = iArr[0] + 1;
                    a.this.b("pollComplete", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.a.1.2
                        @Override // io.socket.b.a.InterfaceC0017a
                        public void a(Object... objArr) {
                            a.logger.fine("pre-pause polling complete");
                            int[] iArr2 = iArr;
                            int i = iArr2[0] - 1;
                            iArr2[0] = i;
                            if (i == 0) {
                                runnable2.run();
                            }
                        }
                    });
                }
                if (a.this.za) {
                    return;
                }
                a.logger.fine("we are currently writing - waiting to pause");
                iArr[0] = iArr[0] + 1;
                a.this.b("drain", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.a.1.3
                    @Override // io.socket.b.a.InterfaceC0017a
                    public void a(Object... objArr) {
                        a.logger.fine("pre-pause writing complete");
                        int[] iArr2 = iArr;
                        int i = iArr2[0] - 1;
                        iArr2[0] = i;
                        if (i == 0) {
                            runnable2.run();
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.socket.engineio.client.Transport
    public void aL(String str) {
        c(str);
    }

    @Override // io.socket.engineio.client.Transport
    protected void b(io.socket.engineio.parser.b[] bVarArr) throws UTF8Exception {
        this.za = false;
        final Runnable runnable = new Runnable() { // from class: io.socket.engineio.client.a.a.4
            @Override // java.lang.Runnable
            public void run() {
                this.za = true;
                this.b("drain", new Object[0]);
            }
        };
        io.socket.engineio.parser.c.a(bVarArr, new c.b<byte[]>() { // from class: io.socket.engineio.client.a.a.5
            @Override // io.socket.engineio.parser.c.b
            public void d(byte[] bArr) {
                this.c(bArr, runnable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.socket.engineio.client.Transport
    public void c(byte[] bArr) {
        c((Object) bArr);
    }

    protected abstract void c(byte[] bArr, Runnable runnable);

    @Override // io.socket.engineio.client.Transport
    protected void fc() {
        fe();
    }

    @Override // io.socket.engineio.client.Transport
    protected void fd() {
        a.InterfaceC0017a interfaceC0017a = new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.a.3
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                a.logger.fine("writing close packet");
                try {
                    this.b(new io.socket.engineio.parser.b[]{new io.socket.engineio.parser.b("close")});
                } catch (UTF8Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        if (this.zc == Transport.ReadyState.OPEN) {
            logger.fine("transport open - closing");
            interfaceC0017a.a(new Object[0]);
            return;
        }
        logger.fine("transport not open - deferring close");
        b("open", interfaceC0017a);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0070, code lost:
        if (r4.port != 80) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String ff() {
        /*
            Method dump skipped, instructions count: 303
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: io.socket.engineio.client.a.a.ff():java.lang.String");
    }

    protected abstract void fg();
}
