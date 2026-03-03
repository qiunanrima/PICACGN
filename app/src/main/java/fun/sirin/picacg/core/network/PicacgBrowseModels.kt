package fun.sirin.picacg.core.network

import com.squareup.moshi.Json

data class PicaCategory(
    @Json(name = "title") val title: String,
    @Json(name = "active") val active: Boolean? = null,
    @Json(name = "isWeb") val isWeb: Boolean? = null
)

data class CategoryData(
    @Json(name = "categories") val categories: List<PicaCategory>
)

data class PicaComic(
    @Json(name = "_id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "author") val author: String? = null,
    @Json(name = "likesCount") val likesCount: Int? = null
)

data class ComicPage(
    @Json(name = "docs") val docs: List<PicaComic> = emptyList(),
    @Json(name = "page") val page: Int = 1,
    @Json(name = "pages") val pages: Int = 1
)

data class ComicsData(
    @Json(name = "comics") val comics: ComicPage
)

data class ComicDetailData(
    @Json(name = "comic") val comic: ComicDetail
)

data class ComicDetail(
    @Json(name = "_id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "author") val author: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "likesCount") val likesCount: Int? = null,
    @Json(name = "epsCount") val epsCount: Int? = null,
    @Json(name = "pagesCount") val pagesCount: Int? = null
)

data class EpisodesData(
    @Json(name = "eps") val eps: EpisodePage
)

data class EpisodePage(
    @Json(name = "docs") val docs: List<PicaEpisode> = emptyList(),
    @Json(name = "page") val page: Int = 1,
    @Json(name = "pages") val pages: Int = 1
)

data class PicaEpisode(
    @Json(name = "_id") val id: String,
    @Json(name = "title") val title: String,
    @Json(name = "order") val order: Int
)

data class PagesData(
    @Json(name = "pages") val pages: ReaderPage
)

data class ReaderPage(
    @Json(name = "docs") val docs: List<ComicImage> = emptyList(),
    @Json(name = "page") val page: Int = 1,
    @Json(name = "pages") val pages: Int = 1
)

data class ComicImage(
    @Json(name = "media") val media: MediaRef
)

data class MediaRef(
    @Json(name = "fileServer") val fileServer: String,
    @Json(name = "path") val path: String
) {
    fun toUrl(): String = "$fileServer/static/$path"
}
