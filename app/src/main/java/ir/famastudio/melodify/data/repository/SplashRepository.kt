package ir.famastudio.melodify.data.repository


import ir.famastudio.melodify.data.db.IO.DataBaseInputOutput
import ir.famastudio.melodify.data.db.key.DataStoreKey
import javax.inject.Inject

class SplashRepository @Inject constructor() {
    @Inject
    lateinit var dataBaseInputOutput: DataBaseInputOutput

    suspend fun getLoginData(): Boolean {
        return dataBaseInputOutput.getData(DataStoreKey.userIsLoggedIn) ?: false
    }
}