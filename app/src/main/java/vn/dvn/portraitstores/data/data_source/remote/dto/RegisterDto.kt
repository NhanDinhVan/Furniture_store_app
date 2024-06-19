package vn.dvn.portraitstores.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import vn.dvn.portraitstores.domain.model.Brands
import vn.dvn.portraitstores.domain.model.Category

@Serializable
data class RegisterDto(
    @SerializedName("name")
    val name: String,
    val purchased: Int,
    val email: String,
    val phoneNumber: String,
    val password: String,
    val role: String,
    val state: String,
)
