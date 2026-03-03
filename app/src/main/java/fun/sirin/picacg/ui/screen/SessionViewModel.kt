package fun.sirin.picacg.ui.screen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fun.sirin.picacg.core.network.ApiResult
import fun.sirin.picacg.core.network.ComicDetail
import fun.sirin.picacg.core.network.PicaCategory
import fun.sirin.picacg.core.network.PicaComic
import fun.sirin.picacg.core.network.PicaEpisode
import fun.sirin.picacg.core.network.PicacgNetworkModule
import fun.sirin.picacg.data.auth.AuthRepository
import fun.sirin.picacg.data.auth.AuthTokenStore
import fun.sirin.picacg.data.auth.ReaderPrefsStore
import fun.sirin.picacg.data.browse.BrowseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

data class SessionUiState(
    val loading: Boolean = false,
    val loggedIn: Boolean = false,
    val message: String? = null,
    val categories: List<PicaCategory> = emptyList(),
    val comics: List<PicaComic> = emptyList(),
    val selectedCategory: String? = null,
    val searchQuery: String = "",
    val selectedComicDetail: ComicDetail? = null,
    val currentComicId: String? = null,
    val episodes: List<PicaEpisode> = emptyList(),
    val currentEpisode: PicaEpisode? = null,
    val pageUrls: List<String> = emptyList(),
    val currentPageIndex: Int = 0
)

class SessionViewModel(
    private val authRepository: AuthRepository,
    private val browseRepository: BrowseRepository,
    private val readerPrefsStore: ReaderPrefsStore
) : ViewModel() {
    private val _uiState = MutableStateFlow(SessionUiState())
    val uiState: StateFlow<SessionUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, message = null)
            when (val result = authRepository.login(email, password)) {
                is ApiResult.Success -> {
                    _uiState.value = _uiState.value.copy(loggedIn = true, loading = false, message = "登录成功")
                    loadCategories()
                }
                is ApiResult.Error -> _uiState.value = _uiState.value.copy(loading = false, message = result.message)
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun searchComics() {
        val query = _uiState.value.searchQuery.trim()
        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(message = "请输入搜索关键词")
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, selectedCategory = null)
            when (val result = browseRepository.searchComics(query)) {
                is ApiResult.Success -> _uiState.value = _uiState.value.copy(loading = false, comics = result.data)
                is ApiResult.Error -> _uiState.value = _uiState.value.copy(loading = false, message = result.message)
            }
        }
    }

    fun loadCategories() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            when (val result = browseRepository.loadCategories()) {
                is ApiResult.Success -> {
                    val categories = result.data.filter { it.isWeb != true }
                    val first = categories.firstOrNull()?.title
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        categories = categories,
                        selectedCategory = first,
                        message = if (categories.isEmpty()) "未获取到分类" else null
                    )
                    if (!first.isNullOrBlank()) {
                        loadComicsByCategory(first)
                    }
                }
                is ApiResult.Error -> _uiState.value = _uiState.value.copy(loading = false, message = result.message)
            }
        }
    }

    fun loadComicsByCategory(category: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, selectedCategory = category)
            when (val result = browseRepository.loadComicsByCategory(category)) {
                is ApiResult.Success -> _uiState.value = _uiState.value.copy(loading = false, comics = result.data)
                is ApiResult.Error -> _uiState.value = _uiState.value.copy(loading = false, message = result.message)
            }
        }
    }

    fun loadComicDetail(comicId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            when (val result = browseRepository.loadComicDetail(comicId)) {
                is ApiResult.Success -> _uiState.value = _uiState.value.copy(
                    loading = false,
                    selectedComicDetail = result.data,
                    currentComicId = result.data.id
                )
                is ApiResult.Error -> _uiState.value = _uiState.value.copy(loading = false, message = result.message)
            }
        }
    }

    fun startReaderFromDetail() {
        val detail = _uiState.value.selectedComicDetail ?: return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            when (val epsResult = browseRepository.loadEpisodes(detail.id)) {
                is ApiResult.Success -> {
                    val saved = readerPrefsStore.getProgress(detail.id)
                    val firstEpisode = epsResult.data.firstOrNull { it.order == saved?.episodeOrder } ?: epsResult.data.firstOrNull()
                    if (firstEpisode == null) {
                        _uiState.value = _uiState.value.copy(loading = false, message = "没有可阅读章节")
                        return@launch
                    }
                    _uiState.value = _uiState.value.copy(
                        episodes = epsResult.data,
                        currentComicId = detail.id,
                        selectedComicDetail = null
                    )
                    openEpisode(firstEpisode, initialPage = if (saved?.comicId == detail.id) saved.pageIndex else 0)
                }
                is ApiResult.Error -> _uiState.value = _uiState.value.copy(loading = false, message = epsResult.message)
            }
        }
    }

    fun openEpisode(episode: PicaEpisode, initialPage: Int = 0) {
        val comicId = _uiState.value.currentComicId ?: return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, currentEpisode = episode)
            when (val pagesResult = browseRepository.loadPageUrls(comicId, episode.order)) {
                is ApiResult.Success -> {
                    val safePage = initialPage.coerceIn(0, (pagesResult.data.size - 1).coerceAtLeast(0))
                    _uiState.value = _uiState.value.copy(loading = false, pageUrls = pagesResult.data, currentPageIndex = safePage)
                    readerPrefsStore.saveProgress(comicId, episode.order, safePage)
                }
                is ApiResult.Error -> _uiState.value = _uiState.value.copy(loading = false, message = pagesResult.message)
            }
        }
    }

    fun nextPage() {
        val state = _uiState.value
        val next = state.currentPageIndex + 1
        if (next < state.pageUrls.size) {
            _uiState.value = state.copy(currentPageIndex = next)
            persistProgress()
        }
    }

    fun prevPage() {
        val state = _uiState.value
        val prev = state.currentPageIndex - 1
        if (prev >= 0) {
            _uiState.value = state.copy(currentPageIndex = prev)
            persistProgress()
        }
    }

    private fun persistProgress() {
        val state = _uiState.value
        val comicId = state.currentComicId ?: return
        val episode = state.currentEpisode ?: return
        viewModelScope.launch {
            readerPrefsStore.saveProgress(comicId, episode.order, state.currentPageIndex)
        }
    }

    fun closeReader() {
        persistProgress()
        _uiState.value = _uiState.value.copy(
            pageUrls = emptyList(),
            currentPageIndex = 0,
            currentEpisode = null,
            episodes = emptyList()
        )
    }

    fun clearComicDetail() {
        _uiState.value = _uiState.value.copy(selectedComicDetail = null)
    }

    companion object {
        fun factory(context: Context): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    val tokenStore = AuthTokenStore(context.applicationContext)
                    val api = PicacgNetworkModule.createApiService {
                        kotlinx.coroutines.runBlocking { tokenStore.tokenFlow.firstOrNull() }
                    }
                    @Suppress("UNCHECKED_CAST")
                    return SessionViewModel(
                        authRepository = AuthRepository(api, tokenStore),
                        browseRepository = BrowseRepository(api),
                        readerPrefsStore = ReaderPrefsStore(context.applicationContext)
                    ) as T
                }
            }
        }
    }
}
