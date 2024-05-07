package vn.dvn.portraitstores.data.repository

import vn.dvn.portraitstores.data.data_source.remote.api.ProductApi
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.ProductDto
import vn.dvn.portraitstores.domain.repository.ProductRepository
import javax.inject.Inject


class ProductRepositoryImpl  @Inject constructor(
    private val productApi: ProductApi
) : ProductRepository{
    override suspend fun getAll(): ApiResponseDto<List<ProductDto>> {
        return productApi.getAll();
    }

    override suspend fun getById(id: String): ApiResponseDto<ProductDto> {
        return productApi.getById(id);
    }
}