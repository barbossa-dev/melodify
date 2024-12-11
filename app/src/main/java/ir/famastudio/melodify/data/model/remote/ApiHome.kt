package ir.famastudio.melodify.data.model.remote

import com.google.gson.annotations.SerializedName

data class ApiResponseGetMusics(
    @SerializedName("musics")
    val musics: ArrayList<ApiResponseGetMusicsData>
)

data class ApiResponseGetMusicsData(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("music")
    val music: String
)