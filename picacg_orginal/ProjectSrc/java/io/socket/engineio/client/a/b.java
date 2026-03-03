package io.socket.engineio.client.a;

import io.socket.b.a;
import io.socket.engineio.client.Transport;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
/* loaded from: picacg_1.jar:io/socket/engineio/client/a/b.class */
public class b extends io.socket.engineio.client.a.a {
    private static final Logger logger = Logger.getLogger(b.class.getName());

    /* loaded from: picacg_1.jar:io/socket/engineio/client/a/b$a.class */
    public static class a extends io.socket.b.a {
        private byte[] data;
        private HostnameVerifier hostnameVerifier;
        private String method;
        private Proxy proxy;
        private SSLContext yp;
        private HttpURLConnection zA;
        private String zz;

        /* renamed from: io.socket.engineio.client.a.b$a$a  reason: collision with other inner class name */
        /* loaded from: picacg_1.jar:io/socket/engineio/client/a/b$a$a.class */
        public static class C0024a {
            public byte[] data;
            public HostnameVerifier hostnameVerifier;
            public String method;
            public Proxy proxy;
            public SSLContext yp;
            public String zz;
        }

        public a(C0024a c0024a) {
            this.method = c0024a.method != null ? c0024a.method : "GET";
            this.zz = c0024a.zz;
            this.data = c0024a.data;
            this.yp = c0024a.yp;
            this.hostnameVerifier = c0024a.hostnameVerifier;
            this.proxy = c0024a.proxy;
        }

        private void aL(String str) {
            b("data", str);
            onSuccess();
        }

        private void b(Map<String, List<String>> map) {
            b("requestHeaders", map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(Exception exc) {
            b("error", exc);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(Map<String, List<String>> map) {
            b("responseHeaders", map);
        }

        private void c(byte[] bArr) {
            b("data", bArr);
            onSuccess();
        }

        private void cleanup() {
            if (this.zA == null) {
                return;
            }
            this.zA.disconnect();
            this.zA = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:52:0x015a A[Catch: IOException -> 0x0183, TRY_ENTER, TryCatch #4 {IOException -> 0x0183, blocks: (B:38:0x011f, B:52:0x015a), top: B:81:0x0015 }] */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0151 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0173 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:87:0x016a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void fk() {
            /*
                Method dump skipped, instructions count: 405
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.socket.engineio.client.a.b.a.fk():void");
        }

        private void onSuccess() {
            b("success", new Object[0]);
        }

        public void create() {
            try {
                b.logger.fine(String.format("xhr open %s: %s", this.method, this.zz));
                URL url = new URL(this.zz);
                this.zA = this.proxy != null ? (HttpURLConnection) url.openConnection(this.proxy) : (HttpURLConnection) url.openConnection();
                this.zA.setRequestMethod(this.method);
                this.zA.setConnectTimeout(10000);
                if (this.zA instanceof HttpsURLConnection) {
                    if (this.yp != null) {
                        ((HttpsURLConnection) this.zA).setSSLSocketFactory(this.yp.getSocketFactory());
                    }
                    if (this.hostnameVerifier != null) {
                        ((HttpsURLConnection) this.zA).setHostnameVerifier(this.hostnameVerifier);
                    }
                }
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                if ("POST".equals(this.method)) {
                    this.zA.setDoOutput(true);
                    treeMap.put("Content-type", new LinkedList(Arrays.asList("application/octet-stream")));
                }
                b(treeMap);
                for (Map.Entry<String, List<String>> entry : treeMap.entrySet()) {
                    for (String str : entry.getValue()) {
                        this.zA.addRequestProperty(entry.getKey(), str);
                    }
                }
                b.logger.fine(String.format("sending xhr with url %s | data %s", this.zz, this.data));
                new Thread(new Runnable() { // from class: io.socket.engineio.client.a.b.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Throwable th;
                        BufferedOutputStream bufferedOutputStream = null;
                        try {
                            try {
                                if (this.data != null) {
                                    a.this.zA.setFixedLengthStreamingMode(this.data.length);
                                    bufferedOutputStream = new BufferedOutputStream(a.this.zA.getOutputStream());
                                    try {
                                        bufferedOutputStream.write(this.data);
                                        bufferedOutputStream.flush();
                                    } catch (IOException e) {
                                        e = e;
                                        this.c(e);
                                        if (bufferedOutputStream == null) {
                                            return;
                                        }
                                        bufferedOutputStream.close();
                                    } catch (NullPointerException e2) {
                                        e = e2;
                                        this.c(e);
                                        if (bufferedOutputStream == null) {
                                            return;
                                        }
                                        bufferedOutputStream.close();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (bufferedOutputStream != null) {
                                            try {
                                                bufferedOutputStream.close();
                                            } catch (IOException e3) {
                                            }
                                        }
                                        throw th;
                                    }
                                }
                                BufferedOutputStream bufferedOutputStream2 = bufferedOutputStream;
                                this.c(a.this.zA.getHeaderFields());
                                BufferedOutputStream bufferedOutputStream3 = bufferedOutputStream;
                                int responseCode = a.this.zA.getResponseCode();
                                if (200 == responseCode) {
                                    this.fk();
                                } else {
                                    a aVar = this;
                                    BufferedOutputStream bufferedOutputStream4 = bufferedOutputStream;
                                    BufferedOutputStream bufferedOutputStream5 = bufferedOutputStream;
                                    IOException iOException = new IOException(Integer.toString(responseCode));
                                    BufferedOutputStream bufferedOutputStream6 = bufferedOutputStream;
                                    aVar.c(iOException);
                                }
                                if (bufferedOutputStream == null) {
                                    return;
                                }
                            } catch (Throwable th3) {
                                bufferedOutputStream = null;
                                th = th3;
                            }
                        } catch (IOException e4) {
                            e = e4;
                            bufferedOutputStream = null;
                        } catch (NullPointerException e5) {
                            e = e5;
                            bufferedOutputStream = null;
                        }
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e6) {
                        }
                    }
                }).start();
            } catch (IOException e) {
                c(e);
            }
        }
    }

    public b(Transport.a aVar) {
        super(aVar);
    }

    protected a a(a.C0024a c0024a) {
        a.C0024a c0024a2 = c0024a;
        if (c0024a == null) {
            c0024a2 = new a.C0024a();
        }
        c0024a2.zz = ff();
        c0024a2.yp = this.yp;
        c0024a2.hostnameVerifier = this.hostnameVerifier;
        c0024a2.proxy = this.proxy;
        a aVar = new a(c0024a2);
        aVar.a("requestHeaders", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.b.2
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                this.b("requestHeaders", objArr[0]);
            }
        }).a("responseHeaders", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.b.1
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.a.b.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        this.b("responseHeaders", objArr[0]);
                    }
                });
            }
        });
        return aVar;
    }

    @Override // io.socket.engineio.client.a.a
    protected void c(byte[] bArr, final Runnable runnable) {
        a.C0024a c0024a = new a.C0024a();
        c0024a.method = "POST";
        c0024a.data = bArr;
        a a2 = a(c0024a);
        a2.a("success", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.b.3
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(Object... objArr) {
                io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.a.b.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        runnable.run();
                    }
                });
            }
        });
        a2.a("error", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.b.4
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.a.b.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        this.b("xhr post error", (objArr.length <= 0 || !(objArr[0] instanceof Exception)) ? null : (Exception) objArr[0]);
                    }
                });
            }
        });
        a2.create();
    }

    @Override // io.socket.engineio.client.a.a
    protected void fg() {
        logger.fine("xhr poll");
        a fi = fi();
        fi.a("data", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.b.5
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.a.b.5.1
                    /* JADX WARN: Multi-variable type inference failed */
                    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Object[]] */
                    /* JADX WARN: Type inference failed for: r0v16 */
                    @Override // java.lang.Runnable
                    public void run() {
                        byte[] bArr = objArr.length > 0 ? objArr[0] : null;
                        if (bArr instanceof String) {
                            this.aL(bArr);
                        } else if (bArr instanceof byte[]) {
                            this.c(bArr);
                        }
                    }
                });
            }
        });
        fi.a("error", new a.InterfaceC0017a() { // from class: io.socket.engineio.client.a.b.6
            @Override // io.socket.b.a.InterfaceC0017a
            public void a(final Object... objArr) {
                io.socket.g.a.b(new Runnable() { // from class: io.socket.engineio.client.a.b.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        this.b("xhr poll error", (objArr.length <= 0 || !(objArr[0] instanceof Exception)) ? null : (Exception) objArr[0]);
                    }
                });
            }
        });
        fi.create();
    }

    protected a fi() {
        return a((a.C0024a) null);
    }
}
