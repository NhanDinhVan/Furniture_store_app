package vn.dvn.portraitstores.data.data_source.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class TransactionRequestDto(
    val amount: Double,
    val message: String,
    val payment: String,
    val userId: String,
    val address: String,
    val ordersList: List<OrderRequestDto>
)