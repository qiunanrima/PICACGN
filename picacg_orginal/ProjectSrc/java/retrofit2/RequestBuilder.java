package retrofit2;

import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.c;
import okio.d;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: picacg_1.jar:retrofit2/RequestBuilder.class */
public final class RequestBuilder {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final String PATH_SEGMENT_ALWAYS_ENCODE_SET = " \"<>^`{}|\\?#";
    private final HttpUrl baseUrl;
    @Nullable
    private RequestBody body;
    @Nullable
    private MediaType contentType;
    @Nullable
    private FormBody.Builder formBuilder;
    private final boolean hasBody;
    private final String method;
    @Nullable
    private MultipartBody.Builder multipartBuilder;
    @Nullable
    private String relativeUrl;
    private final Request.Builder requestBuilder = new Request.Builder();
    @Nullable
    private HttpUrl.Builder urlBuilder;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: picacg_1.jar:retrofit2/RequestBuilder$ContentTypeOverridingRequestBody.class */
    public static class ContentTypeOverridingRequestBody extends RequestBody {
        private final MediaType contentType;
        private final RequestBody delegate;

        ContentTypeOverridingRequestBody(RequestBody requestBody, MediaType mediaType) {
            this.delegate = requestBody;
            this.contentType = mediaType;
        }

        @Override // okhttp3.RequestBody
        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }

        @Override // okhttp3.RequestBody
        public MediaType contentType() {
            return this.contentType;
        }

        @Override // okhttp3.RequestBody
        public void writeTo(d dVar) throws IOException {
            this.delegate.writeTo(dVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RequestBuilder(String str, HttpUrl httpUrl, @Nullable String str2, @Nullable Headers headers, @Nullable MediaType mediaType, boolean z, boolean z2, boolean z3) {
        this.method = str;
        this.baseUrl = httpUrl;
        this.relativeUrl = str2;
        this.contentType = mediaType;
        this.hasBody = z;
        if (headers != null) {
            this.requestBuilder.headers(headers);
        }
        if (z2) {
            this.formBuilder = new FormBody.Builder();
        } else if (z3) {
            this.multipartBuilder = new MultipartBody.Builder();
            this.multipartBuilder.setType(MultipartBody.FORM);
        }
    }

    private static String canonicalizeForPath(String str, boolean z) {
        int i;
        int length = str.length();
        int i2 = 0;
        while (true) {
            i = i2;
            if (i >= length) {
                return str;
            }
            int codePointAt = str.codePointAt(i);
            if (codePointAt < 32 || codePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                break;
            }
            i2 = i + Character.charCount(codePointAt);
        }
        c cVar = new c();
        cVar.c(str, 0, i);
        canonicalizeForPath(cVar, str, i, length, z);
        return cVar.fI();
    }

    private static void canonicalizeForPath(c cVar, String str, int i, int i2, boolean z) {
        c cVar2;
        c cVar3 = null;
        while (true) {
            c cVar4 = cVar3;
            if (i >= i2) {
                return;
            }
            int codePointAt = str.codePointAt(i);
            if (z) {
                cVar2 = cVar4;
                if (codePointAt != 9) {
                    cVar2 = cVar4;
                    if (codePointAt != 10) {
                        cVar2 = cVar4;
                        if (codePointAt != 12) {
                            if (codePointAt == 13) {
                                cVar2 = cVar4;
                            }
                        }
                    }
                }
                i += Character.charCount(codePointAt);
                cVar3 = cVar2;
            }
            if (codePointAt < 32 || codePointAt >= 127 || PATH_SEGMENT_ALWAYS_ENCODE_SET.indexOf(codePointAt) != -1 || (!z && (codePointAt == 47 || codePointAt == 37))) {
                c cVar5 = cVar4;
                if (cVar4 == null) {
                    cVar5 = new c();
                }
                cVar5.al(codePointAt);
                while (true) {
                    cVar2 = cVar5;
                    if (!cVar5.fB()) {
                        int readByte = cVar5.readByte() & 255;
                        cVar.at(37);
                        cVar.at(HEX_DIGITS[(readByte >> 4) & 15]);
                        cVar.at(HEX_DIGITS[readByte & 15]);
                    }
                }
            } else {
                cVar.al(codePointAt);
                cVar2 = cVar4;
            }
            i += Character.charCount(codePointAt);
            cVar3 = cVar2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addFormField(String str, String str2, boolean z) {
        if (z) {
            this.formBuilder.addEncoded(str, str2);
        } else {
            this.formBuilder.add(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addHeader(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.requestBuilder.addHeader(str, str2);
            return;
        }
        MediaType parse = MediaType.parse(str2);
        if (parse != null) {
            this.contentType = parse;
            return;
        }
        throw new IllegalArgumentException("Malformed content type: " + str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addPart(Headers headers, RequestBody requestBody) {
        this.multipartBuilder.addPart(headers, requestBody);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addPart(MultipartBody.Part part) {
        this.multipartBuilder.addPart(part);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addPathParam(String str, String str2, boolean z) {
        if (this.relativeUrl == null) {
            throw new AssertionError();
        }
        String str3 = this.relativeUrl;
        this.relativeUrl = str3.replace("{" + str + "}", canonicalizeForPath(str2, z));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addQueryParam(String str, @Nullable String str2, boolean z) {
        if (this.relativeUrl != null) {
            this.urlBuilder = this.baseUrl.newBuilder(this.relativeUrl);
            if (this.urlBuilder == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
            this.relativeUrl = null;
        }
        if (z) {
            this.urlBuilder.addEncodedQueryParameter(str, str2);
        } else {
            this.urlBuilder.addQueryParameter(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request build() {
        HttpUrl resolve;
        HttpUrl.Builder builder = this.urlBuilder;
        if (builder != null) {
            resolve = builder.build();
        } else {
            resolve = this.baseUrl.resolve(this.relativeUrl);
            if (resolve == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.baseUrl + ", Relative: " + this.relativeUrl);
            }
        }
        RequestBody requestBody = this.body;
        FormBody formBody = requestBody;
        if (requestBody == null) {
            if (this.formBuilder != null) {
                formBody = this.formBuilder.build();
            } else if (this.multipartBuilder != null) {
                formBody = this.multipartBuilder.build();
            } else {
                formBody = requestBody;
                if (this.hasBody) {
                    formBody = RequestBody.create((MediaType) null, new byte[0]);
                }
            }
        }
        MediaType mediaType = this.contentType;
        ContentTypeOverridingRequestBody contentTypeOverridingRequestBody = formBody;
        if (mediaType != null) {
            if (formBody != null) {
                contentTypeOverridingRequestBody = new ContentTypeOverridingRequestBody(formBody, mediaType);
            } else {
                this.requestBuilder.addHeader("Content-Type", mediaType.toString());
                contentTypeOverridingRequestBody = formBody;
            }
        }
        return this.requestBuilder.url(resolve).method(this.method, contentTypeOverridingRequestBody).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBody(RequestBody requestBody) {
        this.body = requestBody;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRelativeUrl(Object obj) {
        this.relativeUrl = obj.toString();
    }
}
