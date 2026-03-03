package fun.sirin.picacg.core.network

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PicacgApiService {
    @POST("auth/sign-in")
    suspend fun signIn(@Body request: LoginRequest): ApiEnvelope<LoginResponseData>

    @GET("categories")
    suspend fun getCategories(): ApiEnvelope<CategoryData>

    @GET("comics")
    suspend fun getComics(
        @Query("page") page: Int,
        @Query("c") c: String,
        @Query("s") s: String = "ua"
    ): ApiEnvelope<ComicsData>

    @GET("comics/advanced-search")
    suspend fun searchComics(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("s") sort: String = "ua"
    ): ApiEnvelope<ComicsData>

    @GET("comics/{comicId}")
    suspend fun getComicDetail(
        @Path("comicId") comicId: String
    ): ApiEnvelope<ComicDetailData>

    @GET("comics/{comicId}/eps")
    suspend fun getEpisodes(
        @Path("comicId") comicId: String,
        @Query("page") page: Int = 1
    ): ApiEnvelope<EpisodesData>

    @GET("comics/{comicId}/order/{order}/pages")
    suspend fun getPages(
        @Path("comicId") comicId: String,
        @Path("order") order: Int,
        @Query("page") page: Int = 1
    ): ApiEnvelope<PagesData>
}
