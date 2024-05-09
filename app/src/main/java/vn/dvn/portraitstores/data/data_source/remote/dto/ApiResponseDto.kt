package vn.dvn.portraitstores.data.data_source.remote.dto

import kotlinx.serialization.Serializable
import vn.dvn.portraitstores.domain.model.Products
@Serializable
data class ApiResponseDto<T>(
    val code: Int? =1000,
    val message: String?=null,
    val result: T?=null
)