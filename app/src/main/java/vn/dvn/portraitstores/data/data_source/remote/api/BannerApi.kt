package vn.dvn.portraitstores.data.data_source.remote.api

import retrofit2.http.GET
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.domain.model.Banner

interface BannerApi {

    @GET("v1/banner")
    suspend fun getAll() : ApiResponseDto<List<Banner>>
}