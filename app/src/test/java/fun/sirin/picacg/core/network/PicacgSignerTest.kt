package fun.sirin.picacg.core.network

import org.junit.Assert.assertEquals
import org.junit.Test

class PicacgSignerTest {
    @Test
    fun sign_returnsExpectedHash() {
        val signature = PicacgSigner.sign(
            relativeUrl = "comics?page=1&c=嗶咔漢化",
            time = "1710000000",
            nonce = "b1ab87b4800d4d4590a11701b8551afa",
            method = "GET",
            apiKey = "C69BAF41DA5ABD1FFEDC6D2FEA56B",
            secretKey = "~d}$Q7$eIni=V)9\\RK/P.RM4;9[7|@   CA}b~OW!3?EV`:<>M7pddUBL5n|0/*Cn"
        )

        assertEquals("083d5931cc01d108064a55bf021175704d7bfad6271f363fac4abce1b49877bd", signature)
    }
}
