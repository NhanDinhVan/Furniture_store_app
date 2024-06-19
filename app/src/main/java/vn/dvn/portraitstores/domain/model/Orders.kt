package vn.dvn.portraitstores.domain.model

import kotlinx.serialization.Serializable
import vn.dvn.portraitstores.data.data_source.remote.dto.ProductDto

@Serializable
data class Orders(
    val orderId: String,
    val userId: String,
    val product: ProductDto,
    val transaction: Transaction,
    val status: Int,
    val quantity: Int,
    val amount: Double
)