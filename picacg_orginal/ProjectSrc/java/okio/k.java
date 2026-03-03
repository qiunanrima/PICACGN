package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
/* loaded from: picacg_1.jar:okio/k.class */
public final class k {
    static final Logger logger = Logger.getLogger(k.class.getName());

    private k() {
    }

    public static p a(OutputStream outputStream) {
        return a(outputStream, new r());
    }

    private static p a(final OutputStream outputStream, final r rVar) {
        if (outputStream != null) {
            if (rVar != null) {
                return new p() { // from class: okio.k.1
                    @Override // okio.p, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        outputStream.close();
                    }

                    @Override // okio.p, java.io.Flushable
                    public void flush() throws IOException {
                        outputStream.flush();
                    }

                    @Override // okio.p
                    public r timeout() {
                        return r.this;
                    }

                    public String toString() {
                        return "sink(" + outputStream + ")";
                    }

                    @Override // okio.p
                    public void write(c cVar, long j) throws IOException {
                        s.checkOffsetAndCount(cVar.size, 0L, j);
                        while (j > 0) {
                            r.this.throwIfReached();
                            n nVar = cVar.Aq;
                            int min = (int) Math.min(j, nVar.limit - nVar.pos);
                            outputStream.write(nVar.data, nVar.pos, min);
                            nVar.pos += min;
                            long j2 = min;
                            long j3 = j - j2;
                            cVar.size -= j2;
                            j = j3;
                            if (nVar.pos == nVar.limit) {
                                cVar.Aq = nVar.gd();
                                o.b(nVar);
                                j = j3;
                            }
                        }
                    }
                };
            }
            throw new IllegalArgumentException("timeout == null");
        }
        throw new IllegalArgumentException("out == null");
    }

    public static q a(InputStream inputStream) {
        return a(inputStream, new r());
    }

    private static q a(final InputStream inputStream, final r rVar) {
        if (inputStream != null) {
            if (rVar != null) {
                return new q() { // from class: okio.k.2
                    @Override // okio.q, java.io.Closeable, java.lang.AutoCloseable
                    public void close() throws IOException {
                        inputStream.close();
                    }

                    @Override // okio.q
                    public long read(c cVar, long j) throws IOException {
                        if (j < 0) {
                            throw new IllegalArgumentException("byteCount < 0: " + j);
                        } else if (j == 0) {
                            return 0L;
                        } else {
                            try {
                                r.this.throwIfReached();
                                n ap = cVar.ap(1);
                                int read = inputStream.read(ap.data, ap.limit, (int) Math.min(j, 8192 - ap.limit));
                                if (read == -1) {
                                    return -1L;
                                }
                                ap.limit += read;
                                long j2 = read;
                                cVar.size += j2;
                                return j2;
                            } catch (AssertionError e) {
                                if (k.isAndroidGetsocknameError(e)) {
                                    throw new IOException(e);
                                }
                                throw e;
                            }
                        }
                    }

                    @Override // okio.q
                    public r timeout() {
                        return r.this;
                    }

                    public String toString() {
                        return "source(" + inputStream + ")";
                    }
                };
            }
            throw new IllegalArgumentException("timeout == null");
        }
        throw new IllegalArgumentException("in == null");
    }

    public static p appendingSink(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static d b(p pVar) {
        return new l(pVar);
    }

    public static e b(q qVar) {
        return new m(qVar);
    }

    public static p b(Socket socket) throws IOException {
        if (socket != null) {
            a d = d(socket);
            return d.sink(a(socket.getOutputStream(), d));
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static q c(Socket socket) throws IOException {
        if (socket != null) {
            a d = d(socket);
            return d.source(a(socket.getInputStream(), d));
        }
        throw new IllegalArgumentException("socket == null");
    }

    private static a d(final Socket socket) {
        return new a() { // from class: okio.k.4
            @Override // okio.a
            protected IOException newTimeoutException(@Nullable IOException iOException) {
                SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            @Override // okio.a
            protected void timedOut() {
                try {
                    socket.close();
                } catch (AssertionError e) {
                    if (!k.isAndroidGetsocknameError(e)) {
                        throw e;
                    }
                    Logger logger2 = k.logger;
                    Level level = Level.WARNING;
                    logger2.log(level, "Failed to close timed out socket " + socket, (Throwable) e);
                } catch (Exception e2) {
                    Logger logger3 = k.logger;
                    Level level2 = Level.WARNING;
                    logger3.log(level2, "Failed to close timed out socket " + socket, (Throwable) e2);
                }
            }
        };
    }

    public static p gb() {
        return new p() { // from class: okio.k.3
            @Override // okio.p, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
            }

            @Override // okio.p, java.io.Flushable
            public void flush() throws IOException {
            }

            @Override // okio.p
            public r timeout() {
                return r.NONE;
            }

            @Override // okio.p
            public void write(c cVar, long j) throws IOException {
                cVar.t(j);
            }
        };
    }

    static boolean isAndroidGetsocknameError(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static p sink(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static q source(File file) throws FileNotFoundException {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }
}
