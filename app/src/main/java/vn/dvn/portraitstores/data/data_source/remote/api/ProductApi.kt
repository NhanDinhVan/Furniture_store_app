package vn.dvn.portraitstores.data.data_source.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.ProductDto

interface ProductApi {
    @GET("v1/products")
    suspend fun getAll():ApiResponseDto<List<ProductDto>>;

    @GET("v1/products/{id}")
    suspend fun getById(@Path("id") id: String): ApiResponseDto<ProductDto>;
    @GET("v1/products/category/{categoryId}")
    suspend fun getByCategoryId(@Path("categoryId") categoryId: String): ApiResponseDto<List<ProductDto>>;

}