package retrofit2;

import java.io.IOException;
import okhttp3.Request;
/* loaded from: picacg_1.jar:retrofit2/Call.class */
public interface Call<T> extends Cloneable {
    void cancel();

    /* renamed from: clone */
    Call<T> mo574clone();

    void enqueue(Callback<T> callback);

    Response<T> execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
