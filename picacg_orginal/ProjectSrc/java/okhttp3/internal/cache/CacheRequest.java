package okhttp3.internal.cache;

import java.io.IOException;
import okio.p;
/* loaded from: picacg_1.jar:okhttp3/internal/cache/CacheRequest.class */
public interface CacheRequest {
    void abort();

    p body() throws IOException;
}
