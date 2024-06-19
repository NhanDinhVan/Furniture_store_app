package vn.dvn.portraitstores.data.data_source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CartUpdationRequestDto(
    val productId: String,
    val quantity: Int,
)