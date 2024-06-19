package vn.dvn.portraitstores.data.data_source.remote.api

import retrofit2.http.GET
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.domain.model.Brands

interface BrandApi {

    @GET("v1/brands")
    suspend fun getAll(): ApiResponseDto<List<Brands>>
}