package vn.dvn.portraitstores.domain.model


data class Carts (
    val cartId: String,
    val userId: String,
    val product: Product,
    val quantity: Int
)