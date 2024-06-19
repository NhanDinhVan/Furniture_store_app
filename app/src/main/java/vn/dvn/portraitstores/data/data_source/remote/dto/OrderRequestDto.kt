package vn.dvn.portraitstores.data.data_source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class OrderRequestDto(
    val userId: String,
    val productId: String,
    val quantity: Int,
    val amount: Double
)