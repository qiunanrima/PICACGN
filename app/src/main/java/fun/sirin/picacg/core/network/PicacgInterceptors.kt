package fun.sirin.picacg.core.network

import java.util.Locale
import java.util.UUID
import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY = "C69BAF41DA5ABD1FFEDC6D2FEA56B"
private const val SECRET_KEY = "~d}$Q7$eIni=V)9\\RK/P.RM4;9[7|@   CA}b~OW!3?EV`:<>M7pddUBL5n|0/*Cn"

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("api-key", API_KEY)
            .header("accept", "application/vnd.picacomic.com.v1+json")
            .header("Content-Type", "application/json; charset=UTF-8")
            .header("User-Agent", "okhttp/4.12.0")
            .header("app-channel", "2")
            .header("app-platform", "android")
            .header("app-version", "2.2.1.3.3.4")
            .header("app-build-version", "45")
            .header("image-quality", "original")
            .header("app-uuid", "android-client")
            .build()
        return chain.proceed(request)
    }
}

class SignatureInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath.removePrefix("/")
        val query = request.url.encodedQuery?.let { "?$it" }.orEmpty()
        val relativeUrl = (path + query).lowercase(Locale.ROOT)
        val time = (System.currentTimeMillis() / 1000L).toString()
        val nonce = UUID.randomUUID().toString().replace("-", "")
        val method = request.method.uppercase(Locale.ROOT)
        val signature = PicacgSigner.sign(
            relativeUrl = relativeUrl,
            time = time,
            nonce = nonce,
            method = method,
            apiKey = API_KEY,
            secretKey = SECRET_KEY
        )
        val signed = request.newBuilder()
            .header("time", time)
            .header("nonce", nonce)
            .header("signature", signature)
            .build()
        return chain.proceed(signed)
    }
}

class AuthInterceptor(
    private val tokenProvider: () -> String?
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenProvider()
        val req = if (token.isNullOrBlank()) chain.request() else {
            chain.request().newBuilder().header("authorization", token).build()
        }
        return chain.proceed(req)
    }
}

