package ir.famastudio.melodify.data.repository

import ir.famastudio.melodify.data.api.ApiInterface
import ir.famastudio.melodify.data.model.remote.ApiResponseGetMusics
import ir.famastudio.melodify.utils.ApiErrorHandler
import javax.inject.Inject

class HomeRepository @Inject constructor() {
    @Inject
    lateinit var apiInterface: ApiInterface

    @Inject
    lateinit var apiErrorHandler: ApiErrorHandler

    suspend fun apiGetMusics(): Pair<ApiResponseGetMusics?, Int?> {
        return apiErrorHandler.handleApiError(apiInterface.apiGetMusics())
    }
}