package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;
import okio.ByteString;
import okio.c;
import okio.q;
import okio.r;
/* loaded from: picacg_1.jar:okhttp3/internal/cache2/Relay.class */
final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    static final ByteString PREFIX_CLEAN = ByteString.aW("OkHttp cache v1\n");
    static final ByteString PREFIX_DIRTY = ByteString.aW("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    final long bufferMaxSize;
    boolean complete;
    RandomAccessFile file;
    private final ByteString metadata;
    int sourceCount;
    q upstream;
    long upstreamPos;
    Thread upstreamReader;
    final c upstreamBuffer = new c();
    final c buffer = new c();

    /* loaded from: picacg_1.jar:okhttp3/internal/cache2/Relay$RelaySource.class */
    class RelaySource implements q {
        private FileOperator fileOperator;
        private long sourcePos;
        private final r timeout = new r();

        RelaySource() {
            this.fileOperator = new FileOperator(Relay.this.file.getChannel());
        }

        @Override // okio.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.fileOperator == null) {
                return;
            }
            RandomAccessFile randomAccessFile = null;
            this.fileOperator = null;
            synchronized (Relay.this) {
                Relay.this.sourceCount--;
                if (Relay.this.sourceCount == 0) {
                    randomAccessFile = Relay.this.file;
                    Relay.this.file = null;
                }
            }
            if (randomAccessFile != null) {
                Util.closeQuietly(randomAccessFile);
            }
        }

        @Override // okio.q
        public long read(c cVar, long j) throws IOException {
            long j2;
            boolean z;
            if (this.fileOperator != null) {
                synchronized (Relay.this) {
                    while (true) {
                        long j3 = this.sourcePos;
                        j2 = Relay.this.upstreamPos;
                        if (j3 != j2) {
                            long size = j2 - Relay.this.buffer.size();
                            if (this.sourcePos >= size) {
                                long min = Math.min(j, j2 - this.sourcePos);
                                Relay.this.buffer.a(cVar, this.sourcePos - size, min);
                                this.sourcePos += min;
                                return min;
                            }
                            z = true;
                        } else if (!Relay.this.complete) {
                            if (Relay.this.upstreamReader == null) {
                                Relay.this.upstreamReader = Thread.currentThread();
                                z = true;
                                break;
                            }
                            this.timeout.waitUntilNotified(Relay.this);
                        } else {
                            return -1L;
                        }
                    }
                    if (z) {
                        long min2 = Math.min(j, j2 - this.sourcePos);
                        this.fileOperator.read(this.sourcePos + 32, cVar, min2);
                        this.sourcePos += min2;
                        return min2;
                    }
                    try {
                        long read = Relay.this.upstream.read(Relay.this.upstreamBuffer, Relay.this.bufferMaxSize);
                        if (read == -1) {
                            Relay.this.commit(j2);
                            synchronized (Relay.this) {
                                Relay.this.upstreamReader = null;
                                Relay.this.notifyAll();
                            }
                            return -1L;
                        }
                        long min3 = Math.min(read, j);
                        Relay.this.upstreamBuffer.a(cVar, 0L, min3);
                        this.sourcePos += min3;
                        this.fileOperator.write(j2 + 32, Relay.this.upstreamBuffer.clone(), read);
                        synchronized (Relay.this) {
                            Relay.this.buffer.write(Relay.this.upstreamBuffer, read);
                            if (Relay.this.buffer.size() > Relay.this.bufferMaxSize) {
                                Relay.this.buffer.t(Relay.this.buffer.size() - Relay.this.bufferMaxSize);
                            }
                            Relay.this.upstreamPos += read;
                        }
                        synchronized (Relay.this) {
                            Relay.this.upstreamReader = null;
                            Relay.this.notifyAll();
                        }
                        return min3;
                    } catch (Throwable th) {
                        synchronized (Relay.this) {
                            Relay.this.upstreamReader = null;
                            Relay.this.notifyAll();
                            throw th;
                        }
                    }
                }
            }
            throw new IllegalStateException("closed");
        }

        @Override // okio.q
        public r timeout() {
            return this.timeout;
        }
    }

    private Relay(RandomAccessFile randomAccessFile, q qVar, long j, ByteString byteString, long j2) {
        this.file = randomAccessFile;
        this.upstream = qVar;
        this.complete = qVar == null;
        this.upstreamPos = j;
        this.metadata = byteString;
        this.bufferMaxSize = j2;
    }

    public static Relay edit(File file, q qVar, ByteString byteString, long j) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Relay relay = new Relay(randomAccessFile, qVar, 0L, byteString, j);
        randomAccessFile.setLength(0L);
        relay.writeHeader(PREFIX_DIRTY, -1L, -1L);
        return relay;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
        c cVar = new c();
        fileOperator.read(0L, cVar, 32L);
        if (cVar.o(PREFIX_CLEAN.size()).equals(PREFIX_CLEAN)) {
            long readLong = cVar.readLong();
            long readLong2 = cVar.readLong();
            c cVar2 = new c();
            fileOperator.read(readLong + 32, cVar2, readLong2);
            return new Relay(randomAccessFile, null, readLong, cVar2.readByteString(), 0L);
        }
        throw new IOException("unreadable cache file");
    }

    private void writeHeader(ByteString byteString, long j, long j2) throws IOException {
        c cVar = new c();
        cVar.b(byteString);
        cVar.z(j);
        cVar.z(j2);
        if (cVar.size() != 32) {
            throw new IllegalArgumentException();
        }
        new FileOperator(this.file.getChannel()).write(0L, cVar, 32L);
    }

    private void writeMetadata(long j) throws IOException {
        c cVar = new c();
        cVar.b(this.metadata);
        new FileOperator(this.file.getChannel()).write(32 + j, cVar, this.metadata.size());
    }

    void commit(long j) throws IOException {
        writeMetadata(j);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j, this.metadata.size());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    boolean isClosed() {
        return this.file == null;
    }

    public ByteString metadata() {
        return this.metadata;
    }

    public q newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }
}
