package fun.sirin.picacg.core.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PicacgNetworkModule {
    private const val BASE_URL = "https://picaapi.picacomic.com/"

    fun createApiService(tokenProvider: () -> String?): PicacgApiService {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
        val client = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(SignatureInterceptor())
            .addInterceptor(AuthInterceptor(tokenProvider))
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(PicacgApiService::class.java)
    }
}
