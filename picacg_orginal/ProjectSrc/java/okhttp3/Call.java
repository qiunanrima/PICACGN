package okhttp3;

import java.io.IOException;
/* loaded from: picacg_1.jar:okhttp3/Call.class */
public interface Call extends Cloneable {

    /* loaded from: picacg_1.jar:okhttp3/Call$Factory.class */
    public interface Factory {
        Call newCall(Request request);
    }

    void cancel();

    Call clone();

    void enqueue(Callback callback);

    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request();
}
