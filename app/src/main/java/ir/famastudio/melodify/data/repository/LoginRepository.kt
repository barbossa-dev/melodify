package ir.famastudio.melodify.data.repository

import android.util.Log
import ir.famastudio.melodify.data.api.ApiInterface
import ir.famastudio.melodify.data.db.IO.DataBaseInputOutput
import ir.famastudio.melodify.data.db.key.DataStoreKey
import ir.famastudio.melodify.data.model.remote.ApiRequestLogin
import ir.famastudio.melodify.utils.ApiErrorHandler
import javax.inject.Inject
import javax.inject.Named

class LoginRepository @Inject constructor() {
    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var apiErrorHandler: ApiErrorHandler
    @Inject
    lateinit var dataBaseInputOutput: DataBaseInputOutput
    suspend fun apiLogin(body: ApiRequestLogin): Boolean {
        val (_, code) = apiErrorHandler.handleApiError(apiInterface.apiLogin(body))
        return code == 200
    }

    fun saveLoginData(){
       dataBaseInputOutput.saveData {
           it[DataStoreKey.userIsLoggedIn] = true
       }
    }
}