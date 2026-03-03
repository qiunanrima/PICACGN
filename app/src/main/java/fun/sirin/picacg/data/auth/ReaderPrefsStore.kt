package fun.sirin.picacg.data.auth

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.readerDataStore by preferencesDataStore(name = "picacg_reader")

data class ReaderProgress(
    val comicId: String,
    val episodeOrder: Int,
    val pageIndex: Int
)

class ReaderPrefsStore(private val context: Context) {

    suspend fun getProgress(comicId: String): ReaderProgress? {
        val prefs = context.readerDataStore.data.first()
        val entry = prefs[stringPreferencesKey(progressKey(comicId))]
            ?: return null
        val parts = entry.split(":")
        if (parts.size != 2) return null
        val episode = parts[0].toIntOrNull() ?: return null
        val page = parts[1].toIntOrNull() ?: return null
        return ReaderProgress(comicId = comicId, episodeOrder = episode, pageIndex = page)
    }

    suspend fun saveProgress(comicId: String, episodeOrder: Int, pageIndex: Int) {
        context.readerDataStore.edit { prefs: MutablePreferences ->
            prefs[stringPreferencesKey(progressKey(comicId))] = "$episodeOrder:$pageIndex"
        }
    }

    private fun progressKey(comicId: String): String = "progress_$comicId"
}
