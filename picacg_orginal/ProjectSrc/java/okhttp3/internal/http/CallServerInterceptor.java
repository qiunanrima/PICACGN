package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.StreamAllocation;
import okio.d;
import okio.k;
/* loaded from: picacg_1.jar:okhttp3/internal/http/CallServerInterceptor.class */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        HttpCodec httpStream = realInterceptorChain.httpStream();
        StreamAllocation streamAllocation = realInterceptorChain.streamAllocation();
        RealConnection realConnection = (RealConnection) realInterceptorChain.connection();
        Request request = realInterceptorChain.request();
        long currentTimeMillis = System.currentTimeMillis();
        httpStream.writeRequestHeaders(request);
        Response.Builder builder = null;
        Response.Builder builder2 = null;
        if (HttpMethod.permitsRequestBody(request.method())) {
            builder2 = null;
            if (request.body() != null) {
                if ("100-continue".equalsIgnoreCase(request.header("Expect"))) {
                    httpStream.flushRequest();
                    builder = httpStream.readResponseHeaders(true);
                }
                if (builder == null) {
                    d b = k.b(httpStream.createRequestBody(request, request.body().contentLength()));
                    request.body().writeTo(b);
                    b.close();
                    builder2 = builder;
                } else {
                    builder2 = builder;
                    if (!realConnection.isMultiplexed()) {
                        streamAllocation.noNewStreams();
                        builder2 = builder;
                    }
                }
            }
        }
        httpStream.finishRequest();
        Response.Builder builder3 = builder2;
        if (builder2 == null) {
            builder3 = httpStream.readResponseHeaders(false);
        }
        Response build = builder3.request(request).handshake(streamAllocation.connection().handshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        int code = build.code();
        Response build2 = (this.forWebSocket && code == 101) ? build.newBuilder().body(Util.EMPTY_RESPONSE).build() : build.newBuilder().body(httpStream.openResponseBody(build)).build();
        if ("close".equalsIgnoreCase(build2.request().header("Connection")) || "close".equalsIgnoreCase(build2.header("Connection"))) {
            streamAllocation.noNewStreams();
        }
        if ((code == 204 || code == 205) && build2.body().contentLength() > 0) {
            throw new ProtocolException("HTTP " + code + " had non-zero Content-Length: " + build2.body().contentLength());
        }
        return build2;
    }
}
