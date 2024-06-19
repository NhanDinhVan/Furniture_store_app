package vn.dvn.portraitstores.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Category (
    val categoryId: String,
    val name: String
)