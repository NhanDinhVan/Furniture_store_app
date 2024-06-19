package vn.dvn.portraitstores.domain.repository

import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.ProductDto

interface ProductRepository {
    suspend fun getAll():ApiResponseDto<List<ProductDto>>;

    suspend fun getById(id: String):ApiResponseDto<ProductDto>;
    suspend fun getByCategoryId(categoryId: String):ApiResponseDto<List<ProductDto>>;


}