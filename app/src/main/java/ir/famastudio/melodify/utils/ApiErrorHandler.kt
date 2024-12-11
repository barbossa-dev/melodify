package ir.famastudio.melodify.utils

import android.util.Log
import retrofit2.Call
import retrofit2.awaitResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiErrorHandler @Inject constructor() {
    suspend fun <T> handleApiError(call: Call<T>): Pair<T?, Int?> {
        return try {
            val response = call.awaitResponse()
            Pair(response.body(), response.code())
        } catch (e: Exception) {
            Log.i("jjj", "handleApiError: ${e.message}")
            Pair(null, null)
        }
    }
}