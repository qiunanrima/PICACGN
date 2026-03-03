package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;
/* loaded from: picacg_1.jar:okhttp3/Interceptor.class */
public interface Interceptor {

    /* loaded from: picacg_1.jar:okhttp3/Interceptor$Chain.class */
    public interface Chain {
        @Nullable
        Connection connection();

        Response proceed(Request request) throws IOException;

        Request request();
    }

    Response intercept(Chain chain) throws IOException;
}
