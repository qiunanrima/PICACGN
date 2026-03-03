package fun.sirin.picacg.data.auth

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "picacg_auth")

class AuthTokenStore(private val context: Context) {
    private object Keys {
        val token = stringPreferencesKey("token")
    }

    val tokenFlow: Flow<String?> = context.dataStore.data.map { it[Keys.token] }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs: MutablePreferences ->
            prefs[Keys.token] = token
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { prefs: MutablePreferences ->
            prefs.remove(Keys.token)
        }
    }
}
