package ir.famastudio.melodify.data.model.remote

import com.google.gson.annotations.SerializedName

data class ApiRequestLogin(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String
)