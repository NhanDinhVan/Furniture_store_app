package vn.dvn.portraitstores.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Transaction(
    val transactionId: String,
    val userId: String,
    val status:  Int,
    val amount: Double,
    val message: String,
    val payment: String,
    val paymentInfo: String,
    val address: String,
    val creationTime: String
)