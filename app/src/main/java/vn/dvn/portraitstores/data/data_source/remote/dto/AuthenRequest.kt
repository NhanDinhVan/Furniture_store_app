package vn.dvn.portraitstores.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthenticateRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)