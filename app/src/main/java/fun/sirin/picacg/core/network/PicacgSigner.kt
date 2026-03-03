package fun.sirin.picacg.core.network

import java.util.Locale
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

internal object PicacgSigner {
    fun sign(
        relativeUrl: String,
        time: String,
        nonce: String,
        method: String,
        apiKey: String,
        secretKey: String
    ): String {
        val raw = "$relativeUrl$time$nonce$method$apiKey".lowercase(Locale.ROOT)
        val mac = Mac.getInstance("HmacSHA256")
        mac.init(SecretKeySpec(secretKey.toByteArray(), "HmacSHA256"))
        return mac.doFinal(raw.toByteArray()).joinToString("") { "%02x".format(it) }
    }
}
