package vn.dvn.portraitstores.presentation.home_screen

import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.domain.model.Products

data class ProductListState(
    val isLoading: Boolean = false,
    val productList:List<Products> = emptyList(),
    val error: String = ""
)