package okhttp3;

import java.net.Socket;
import javax.annotation.Nullable;
/* loaded from: picacg_1.jar:okhttp3/Connection.class */
public interface Connection {
    @Nullable
    Handshake handshake();

    Protocol protocol();

    Route route();

    Socket socket();
}
