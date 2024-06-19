package vn.dvn.portraitstores.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Brands(
    val brandId: String,
    val name: String,
    val imagePath: String
)