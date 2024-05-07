package vn.dvn.portraitstores.domain.model

data class Products(
    val brand: String,
    val creationTime: String,
    val description: String,
    val discount: Double,
    val id: String,
    val imagePath: String,
    val name: String,
    val price: Double,
    val quantity: Int,
    val sold: Int,
    val state: String,
    val updationTime: String,
    val view: Int
)
