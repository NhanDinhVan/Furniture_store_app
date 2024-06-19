package vn.dvn.portraitstores.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import vn.dvn.portraitstores.domain.model.Brands
import vn.dvn.portraitstores.domain.model.Category
import vn.dvn.portraitstores.domain.model.Product
@Serializable
data class ProductDto(
    val brand: Brands,
    @SerializedName("creation_time")
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
    @SerializedName("updationtime")
    val updationTime: String,
    val view: Int,
    val category: Category
)

fun ProductDto.toProduct():Product
{
    return Product(
       brand = brand,
        creationTime = creationTime,
        description = description,
        discount = discount,
        id = id,
        imagePath = imagePath,
        name = name,
        price = price,
        quantity = quantity,
        sold = sold,
        state = state,
        updationTime = updationTime,
        view = view,
        category= category
    )
}