package ir.famastudio.melodify.data.db.key

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreKey {
    val userIsLoggedIn = booleanPreferencesKey("user_is_logged_in")
}