package okhttp3.internal.cache2;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import okio.c;
/* loaded from: picacg_1.jar:okhttp3/internal/cache2/FileOperator.class */
final class FileOperator {
    private static final int BUFFER_SIZE = 8192;
    private final byte[] byteArray = new byte[8192];
    private final ByteBuffer byteBuffer = ByteBuffer.wrap(this.byteArray);
    private final FileChannel fileChannel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public void read(long j, c cVar, long j2) throws IOException {
        if (j2 < 0) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            try {
                this.byteBuffer.limit((int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_URI, j2));
                if (this.fileChannel.read(this.byteBuffer, j) == -1) {
                    throw new EOFException();
                }
                int position = this.byteBuffer.position();
                cVar.b(this.byteArray, 0, position);
                long j3 = position;
                j += j3;
                j2 -= j3;
            } finally {
                this.byteBuffer.clear();
            }
        }
    }

    public void write(long j, c cVar, long j2) throws IOException {
        if (j2 < 0 || j2 > cVar.size()) {
            throw new IndexOutOfBoundsException();
        }
        while (j2 > 0) {
            try {
                int min = (int) Math.min((long) PlaybackStateCompat.ACTION_PLAY_FROM_URI, j2);
                cVar.read(this.byteArray, 0, min);
                this.byteBuffer.limit(min);
                long j3 = j;
                do {
                    j = j3 + this.fileChannel.write(this.byteBuffer, j3);
                    j3 = j;
                } while (this.byteBuffer.hasRemaining());
                j2 -= min;
            } finally {
                this.byteBuffer.clear();
            }
        }
    }
}
