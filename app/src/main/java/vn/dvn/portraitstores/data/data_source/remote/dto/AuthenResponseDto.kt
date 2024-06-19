package vn.dvn.portraitstores.data.data_source.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class AuthenResponseDto (
    val authenticated: Boolean,
    val token: String
)