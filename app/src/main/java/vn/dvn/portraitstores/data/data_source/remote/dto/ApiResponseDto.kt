package vn.dvn.portraitstores.data.data_source.remote.dto

import vn.dvn.portraitstores.domain.model.Products

data class ApiResponseDto<T>(
    val code: Int? =1000,
    val message: Any?=null,
    val result: T?=null
)