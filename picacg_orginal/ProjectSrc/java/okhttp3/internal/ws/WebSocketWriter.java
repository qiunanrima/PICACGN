package okhttp3.internal.ws;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.util.Random;
import okio.ByteString;
import okio.c;
import okio.d;
import okio.p;
import okio.r;
/* loaded from: picacg_1.jar:okhttp3/internal/ws/WebSocketWriter.class */
final class WebSocketWriter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    boolean activeWriter;
    final c buffer = new c();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    final byte[] maskBuffer;
    final byte[] maskKey;
    final Random random;
    final d sink;
    boolean writerClosed;

    /* loaded from: picacg_1.jar:okhttp3/internal/ws/WebSocketWriter$FrameSink.class */
    final class FrameSink implements p {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        @Override // okio.p, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, true);
            }
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }

        @Override // okio.p, java.io.Flushable
        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, false);
            }
            this.isFirstFrame = false;
        }

        @Override // okio.p
        public r timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        @Override // okio.p
        public void write(c cVar, long j) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(cVar, j);
            boolean z = this.isFirstFrame && this.contentLength != -1 && WebSocketWriter.this.buffer.size() > this.contentLength - PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            long fD = WebSocketWriter.this.buffer.fD();
            if (fD <= 0 || z) {
                return;
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, fD, this.isFirstFrame, false);
            }
            this.isFirstFrame = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketWriter(boolean z, d dVar, Random random) {
        if (dVar == null) {
            throw new NullPointerException("sink == null");
        }
        if (random == null) {
            throw new NullPointerException("random == null");
        }
        this.isClient = z;
        this.sink = dVar;
        this.random = random;
        this.maskKey = z ? new byte[4] : null;
        this.maskBuffer = z ? new byte[8192] : null;
    }

    private void writeControlFrameSynchronized(int i, ByteString byteString) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        int size = byteString.size();
        if (size > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.sink.at(i | 128);
        if (this.isClient) {
            this.sink.at(size | 128);
            this.random.nextBytes(this.maskKey);
            this.sink.k(this.maskKey);
            byte[] byteArray = byteString.toByteArray();
            WebSocketProtocol.toggleMask(byteArray, byteArray.length, this.maskKey, 0L);
            this.sink.k(byteArray);
        } else {
            this.sink.at(size);
            this.sink.b(byteString);
        }
        this.sink.flush();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p newMessageSink(int i, long j) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        this.frameSink.formatOpcode = i;
        this.frameSink.contentLength = j;
        this.frameSink.isFirstFrame = true;
        this.frameSink.closed = false;
        return this.frameSink;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeClose(int i, ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.As;
        if (i != 0 || byteString != null) {
            if (i != 0) {
                WebSocketProtocol.validateCloseCode(i);
            }
            c cVar = new c();
            cVar.as(i);
            if (byteString != null) {
                cVar.b(byteString);
            }
            byteString2 = cVar.readByteString();
        }
        synchronized (this) {
            writeControlFrameSynchronized(8, byteString2);
            this.writerClosed = true;
        }
    }

    void writeMessageFrameSynchronized(int i, long j, boolean z, boolean z2) throws IOException {
        if (this.writerClosed) {
            throw new IOException("closed");
        }
        if (!z) {
            i = 0;
        }
        int i2 = i;
        if (z2) {
            i2 = i | 128;
        }
        this.sink.at(i2);
        int i3 = this.isClient ? 128 : 0;
        if (j <= 125) {
            this.sink.at(i3 | ((int) j));
        } else if (j <= 65535) {
            this.sink.at(i3 | 126);
            this.sink.as((int) j);
        } else {
            this.sink.at(i3 | 127);
            this.sink.z(j);
        }
        if (this.isClient) {
            this.random.nextBytes(this.maskKey);
            this.sink.k(this.maskKey);
            long j2 = 0;
            while (true) {
                long j3 = j2;
                if (j3 >= j) {
                    break;
                }
                int read = this.buffer.read(this.maskBuffer, 0, (int) Math.min(j, this.maskBuffer.length));
                if (read == -1) {
                    throw new AssertionError();
                }
                long j4 = read;
                WebSocketProtocol.toggleMask(this.maskBuffer, j4, this.maskKey, j3);
                this.sink.b(this.maskBuffer, 0, read);
                j2 = j3 + j4;
            }
        } else {
            this.sink.write(this.buffer, j);
        }
        this.sink.fA();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePing(ByteString byteString) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(9, byteString);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePong(ByteString byteString) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(10, byteString);
        }
    }
}
