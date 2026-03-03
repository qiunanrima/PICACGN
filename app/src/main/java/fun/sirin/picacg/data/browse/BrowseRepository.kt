package fun.sirin.picacg.data.browse

import fun.sirin.picacg.core.network.ApiResult
import fun.sirin.picacg.core.network.ComicDetail
import fun.sirin.picacg.core.network.PicaCategory
import fun.sirin.picacg.core.network.PicaComic
import fun.sirin.picacg.core.network.PicaEpisode
import fun.sirin.picacg.core.network.PicacgApiService

class BrowseRepository(private val apiService: PicacgApiService) {
    suspend fun loadCategories(): ApiResult<List<PicaCategory>> {
        return runCatching {
            val res = apiService.getCategories()
            ApiResult.Success(res.data?.categories.orEmpty())
        }.getOrElse { ApiResult.Error(it.message ?: "加载分类失败", cause = it) }
    }

    suspend fun loadComicsByCategory(category: String, page: Int = 1): ApiResult<List<PicaComic>> {
        return runCatching {
            val res = apiService.getComics(page = page, c = category)
            ApiResult.Success(res.data?.comics?.docs.orEmpty())
        }.getOrElse { ApiResult.Error(it.message ?: "加载漫画失败", cause = it) }
    }

    suspend fun searchComics(query: String, page: Int = 1): ApiResult<List<PicaComic>> {
        return runCatching {
            val res = apiService.searchComics(query = query, page = page)
            ApiResult.Success(res.data?.comics?.docs.orEmpty())
        }.getOrElse { ApiResult.Error(it.message ?: "搜索失败", cause = it) }
    }

    suspend fun loadComicDetail(comicId: String): ApiResult<ComicDetail> {
        return runCatching {
            val res = apiService.getComicDetail(comicId)
            val detail = res.data?.comic ?: return@runCatching ApiResult.Error("详情为空", res.code)
            ApiResult.Success(detail)
        }.getOrElse { ApiResult.Error(it.message ?: "加载详情失败", cause = it) }
    }

    suspend fun loadEpisodes(comicId: String): ApiResult<List<PicaEpisode>> {
        return runCatching {
            val all = mutableListOf<PicaEpisode>()
            var page = 1
            var totalPages = 1
            do {
                val res = apiService.getEpisodes(comicId = comicId, page = page)
                val epsPage = res.data?.eps
                val docs = epsPage?.docs.orEmpty()
                all += docs
                totalPages = epsPage?.pages ?: page
                page += 1
            } while (page <= totalPages)
            ApiResult.Success(all.distinctBy { it.id }.sortedBy { it.order })
        }.getOrElse { ApiResult.Error(it.message ?: "加载章节失败", cause = it) }
    }

    suspend fun loadPageUrls(comicId: String, order: Int): ApiResult<List<String>> {
        return runCatching {
            val allUrls = mutableListOf<String>()
            var page = 1
            var totalPages = 1
            do {
                val res = apiService.getPages(comicId = comicId, order = order, page = page)
                val readerPage = res.data?.pages
                val docs = readerPage?.docs.orEmpty()
                allUrls += docs.map { it.media.toUrl() }
                totalPages = readerPage?.pages ?: page
                page += 1
            } while (page <= totalPages)
            ApiResult.Success(allUrls)
        }.getOrElse { ApiResult.Error(it.message ?: "加载页面失败", cause = it) }
    }
}
