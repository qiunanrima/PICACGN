package fun.sirin.picacg.data.auth

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.readerDataStore by preferencesDataStore(name = "picacg_reader")

data class ReaderProgress(
    val comicId: String,
    val episodeOrder: Int,
    val pageIndex: Int
)

class ReaderPrefsStore(private val context: Context) {
    private object Keys {
        val comicId = stringPreferencesKey("comic_id")
        val episodeOrder = intPreferencesKey("episode_order")
        val pageIndex = intPreferencesKey("page_index")
    }

    val progressFlow: Flow<ReaderProgress?> = context.readerDataStore.data.map { prefs ->
        val comicId = prefs[Keys.comicId] ?: return@map null
        ReaderProgress(
            comicId = comicId,
            episodeOrder = prefs[Keys.episodeOrder] ?: 1,
            pageIndex = prefs[Keys.pageIndex] ?: 0
        )
    }

    suspend fun saveProgress(comicId: String, episodeOrder: Int, pageIndex: Int) {
        context.readerDataStore.edit { prefs: MutablePreferences ->
            prefs[Keys.comicId] = comicId
            prefs[Keys.episodeOrder] = episodeOrder
            prefs[Keys.pageIndex] = pageIndex
        }
    }
}
