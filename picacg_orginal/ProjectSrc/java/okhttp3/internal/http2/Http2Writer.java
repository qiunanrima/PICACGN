package okhttp3.internal.http2;

import android.support.v7.widget.ActivityChooserView;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.internal.Util;
import okhttp3.internal.http2.Hpack;
import okio.c;
import okio.d;
/* loaded from: picacg_1.jar:okhttp3/internal/http2/Http2Writer.class */
final class Http2Writer implements Closeable {
    private static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private boolean closed;
    private final c hpackBuffer = new c();
    final Hpack.Writer hpackWriter = new Hpack.Writer(this.hpackBuffer);
    private int maxFrameSize = 16384;
    private final d sink;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Http2Writer(d dVar, boolean z) {
        this.sink = dVar;
        this.client = z;
    }

    private void writeContinuationFrames(int i, long j) throws IOException {
        while (j > 0) {
            int min = (int) Math.min(this.maxFrameSize, j);
            long j2 = min;
            j -= j2;
            frameHeader(i, min, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.sink.write(this.hpackBuffer, j2);
        }
    }

    private static void writeMedium(d dVar, int i) throws IOException {
        dVar.at((i >>> 16) & 255);
        dVar.at((i >>> 8) & 255);
        dVar.at(i & 255);
    }

    public void applyAndAckSettings(Settings settings) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            this.maxFrameSize = settings.getMaxFrameSize(this.maxFrameSize);
            if (settings.getHeaderTableSize() != -1) {
                this.hpackWriter.setHeaderTableSizeSetting(settings.getHeaderTableSize());
            }
            frameHeader(0, 0, (byte) 4, (byte) 1);
            this.sink.flush();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (this) {
            this.closed = true;
            this.sink.close();
        }
    }

    public void connectionPreface() throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (this.client) {
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(Util.format(">> CONNECTION %s", Http2.CONNECTION_PREFACE.fU()));
                }
                this.sink.k(Http2.CONNECTION_PREFACE.toByteArray());
                this.sink.flush();
            }
        }
    }

    public void data(boolean z, int i, c cVar, int i2) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            byte b = 0;
            if (z) {
                b = (byte) 1;
            }
            dataFrame(i, b, cVar, i2);
        }
    }

    void dataFrame(int i, byte b, c cVar, int i2) throws IOException {
        frameHeader(i, i2, (byte) 0, b);
        if (i2 > 0) {
            this.sink.write(cVar, i2);
        }
    }

    public void flush() throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            this.sink.flush();
        }
    }

    public void frameHeader(int i, int i2, byte b, byte b2) throws IOException {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(Http2.frameLog(false, i, i2, b, b2));
        }
        if (i2 > this.maxFrameSize) {
            throw Http2.illegalArgument("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(this.maxFrameSize), Integer.valueOf(i2));
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            throw Http2.illegalArgument("reserved bit set: %s", Integer.valueOf(i));
        }
        writeMedium(this.sink, i2);
        this.sink.at(b & 255);
        this.sink.at(b2 & 255);
        this.sink.ar(i & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
    }

    public void goAway(int i, ErrorCode errorCode, byte[] bArr) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (errorCode.httpCode == -1) {
                throw Http2.illegalArgument("errorCode.httpCode == -1", new Object[0]);
            }
            frameHeader(0, bArr.length + 8, (byte) 7, (byte) 0);
            this.sink.ar(i);
            this.sink.ar(errorCode.httpCode);
            if (bArr.length > 0) {
                this.sink.k(bArr);
            }
            this.sink.flush();
        }
    }

    public void headers(int i, List<Header> list) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            headers(false, i, list);
        }
    }

    void headers(boolean z, int i, List<Header> list) throws IOException {
        if (this.closed) {
            throw new IOException("closed");
        }
        this.hpackWriter.writeHeaders(list);
        long size = this.hpackBuffer.size();
        int min = (int) Math.min(this.maxFrameSize, size);
        long j = min;
        byte b = size == j ? (byte) 4 : (byte) 0;
        byte b2 = b;
        if (z) {
            b2 = (byte) (b | 1);
        }
        frameHeader(i, min, (byte) 1, b2);
        this.sink.write(this.hpackBuffer, j);
        if (size > j) {
            writeContinuationFrames(i, size - j);
        }
    }

    public int maxDataLength() {
        return this.maxFrameSize;
    }

    public void ping(boolean z, int i, int i2) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            frameHeader(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
            this.sink.ar(i);
            this.sink.ar(i2);
            this.sink.flush();
        }
    }

    public void pushPromise(int i, int i2, List<Header> list) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            this.hpackWriter.writeHeaders(list);
            long size = this.hpackBuffer.size();
            int min = (int) Math.min(this.maxFrameSize - 4, size);
            long j = min;
            frameHeader(i, min + 4, (byte) 5, size == j ? (byte) 4 : (byte) 0);
            this.sink.ar(i2 & ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
            this.sink.write(this.hpackBuffer, j);
            if (size > j) {
                writeContinuationFrames(i, size - j);
            }
        }
    }

    public void rstStream(int i, ErrorCode errorCode) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (errorCode.httpCode == -1) {
                throw new IllegalArgumentException();
            }
            frameHeader(i, 4, (byte) 3, (byte) 0);
            this.sink.ar(errorCode.httpCode);
            this.sink.flush();
        }
    }

    public void settings(Settings settings) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            int i = 0;
            frameHeader(0, settings.size() * 6, (byte) 4, (byte) 0);
            while (i < 10) {
                if (settings.isSet(i)) {
                    this.sink.as(i == 4 ? 3 : i == 7 ? 4 : i);
                    this.sink.ar(settings.get(i));
                }
                i++;
            }
            this.sink.flush();
        }
    }

    public void synReply(boolean z, int i, List<Header> list) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            headers(z, i, list);
        }
    }

    public void synStream(boolean z, int i, int i2, List<Header> list) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            headers(z, i, list);
        }
    }

    public void windowUpdate(int i, long j) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IOException("closed");
            }
            if (j == 0 || j > 2147483647L) {
                throw Http2.illegalArgument("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            }
            frameHeader(i, 4, (byte) 8, (byte) 0);
            this.sink.ar((int) j);
            this.sink.flush();
        }
    }
}
