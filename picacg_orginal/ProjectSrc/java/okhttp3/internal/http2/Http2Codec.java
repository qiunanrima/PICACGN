package okhttp3.internal.http2;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.connection.StreamAllocation;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http.RealResponseBody;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.ByteString;
import okio.g;
import okio.k;
import okio.p;
import okio.q;
/* loaded from: picacg_1.jar:okhttp3/internal/http2/Http2Codec.class */
public final class Http2Codec implements HttpCodec {
    private final OkHttpClient client;
    private final Http2Connection connection;
    private Http2Stream stream;
    final StreamAllocation streamAllocation;
    private static final ByteString CONNECTION = ByteString.aW("connection");
    private static final ByteString HOST = ByteString.aW("host");
    private static final ByteString KEEP_ALIVE = ByteString.aW("keep-alive");
    private static final ByteString PROXY_CONNECTION = ByteString.aW("proxy-connection");
    private static final ByteString TRANSFER_ENCODING = ByteString.aW("transfer-encoding");
    private static final ByteString TE = ByteString.aW("te");
    private static final ByteString ENCODING = ByteString.aW("encoding");
    private static final ByteString UPGRADE = ByteString.aW("upgrade");
    private static final List<ByteString> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE, Header.TARGET_METHOD, Header.TARGET_PATH, Header.TARGET_SCHEME, Header.TARGET_AUTHORITY);
    private static final List<ByteString> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableList(CONNECTION, HOST, KEEP_ALIVE, PROXY_CONNECTION, TE, TRANSFER_ENCODING, ENCODING, UPGRADE);

    /* loaded from: picacg_1.jar:okhttp3/internal/http2/Http2Codec$StreamFinishingSource.class */
    class StreamFinishingSource extends g {
        StreamFinishingSource(q qVar) {
            super(qVar);
        }

        @Override // okio.g, okio.q, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            Http2Codec.this.streamAllocation.streamFinished(false, Http2Codec.this);
            super.close();
        }
    }

    public Http2Codec(OkHttpClient okHttpClient, StreamAllocation streamAllocation, Http2Connection http2Connection) {
        this.client = okHttpClient;
        this.streamAllocation = streamAllocation;
        this.connection = http2Connection;
    }

    public static List<Header> http2HeadersList(Request request) {
        Headers headers = request.headers();
        ArrayList arrayList = new ArrayList(headers.size() + 4);
        arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
        arrayList.add(new Header(Header.TARGET_PATH, RequestLine.requestPath(request.url())));
        String header = request.header("Host");
        if (header != null) {
            arrayList.add(new Header(Header.TARGET_AUTHORITY, header));
        }
        arrayList.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            ByteString aW = ByteString.aW(headers.name(i).toLowerCase(Locale.US));
            if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(aW)) {
                arrayList.add(new Header(aW, headers.value(i)));
            }
        }
        return arrayList;
    }

    public static Response.Builder readHttp2HeadersList(List<Header> list) throws IOException {
        StatusLine statusLine;
        Headers.Builder builder;
        Headers.Builder builder2 = new Headers.Builder();
        int size = list.size();
        int i = 0;
        StatusLine statusLine2 = null;
        while (i < size) {
            Header header = list.get(i);
            if (header == null) {
                statusLine = statusLine2;
                builder = builder2;
                if (statusLine2 != null) {
                    statusLine = statusLine2;
                    builder = builder2;
                    if (statusLine2.code == 100) {
                        builder = new Headers.Builder();
                        statusLine = null;
                    }
                }
            } else {
                ByteString byteString = header.name;
                String fP = header.value.fP();
                if (byteString.equals(Header.RESPONSE_STATUS)) {
                    statusLine = StatusLine.parse("HTTP/1.1 " + fP);
                    builder = builder2;
                } else {
                    statusLine = statusLine2;
                    builder = builder2;
                    if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(byteString)) {
                        Internal.instance.addLenient(builder2, byteString.fP(), fP);
                        builder = builder2;
                        statusLine = statusLine2;
                    }
                }
            }
            i++;
            statusLine2 = statusLine;
            builder2 = builder;
        }
        if (statusLine2 != null) {
            return new Response.Builder().protocol(Protocol.HTTP_2).code(statusLine2.code).message(statusLine2.message).headers(builder2.build());
        }
        throw new ProtocolException("Expected ':status' header not present");
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void cancel() {
        if (this.stream != null) {
            this.stream.closeLater(ErrorCode.CANCEL);
        }
    }

    @Override // okhttp3.internal.http.HttpCodec
    public p createRequestBody(Request request, long j) {
        return this.stream.getSink();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void finishRequest() throws IOException {
        this.stream.getSink().close();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void flushRequest() throws IOException {
        this.connection.flush();
    }

    @Override // okhttp3.internal.http.HttpCodec
    public ResponseBody openResponseBody(Response response) throws IOException {
        return new RealResponseBody(response.headers(), k.b(new StreamFinishingSource(this.stream.getSource())));
    }

    @Override // okhttp3.internal.http.HttpCodec
    public Response.Builder readResponseHeaders(boolean z) throws IOException {
        Response.Builder readHttp2HeadersList = readHttp2HeadersList(this.stream.takeResponseHeaders());
        if (z && Internal.instance.code(readHttp2HeadersList) == 100) {
            return null;
        }
        return readHttp2HeadersList;
    }

    @Override // okhttp3.internal.http.HttpCodec
    public void writeRequestHeaders(Request request) throws IOException {
        if (this.stream != null) {
            return;
        }
        this.stream = this.connection.newStream(http2HeadersList(request), request.body() != null);
        this.stream.readTimeout().timeout(this.client.readTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.stream.writeTimeout().timeout(this.client.writeTimeoutMillis(), TimeUnit.MILLISECONDS);
    }
}
