package ir.famastudio.melodify.data.api

import ir.famastudio.melodify.data.model.remote.ApiRequestLogin
import ir.famastudio.melodify.data.model.remote.ApiResponseGetMusics
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("/api/login")
    fun apiLogin(@Body body: ApiRequestLogin): Call<Any>

    @GET("/api/get/musics")
    fun apiGetMusics(): Call<ApiResponseGetMusics>
}