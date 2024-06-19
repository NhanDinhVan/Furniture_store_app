package vn.dvn.portraitstores.data.data_source.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.CartUpdationRequestDto
import vn.dvn.portraitstores.domain.model.Carts

interface CartApi {
    @GET("v1/carts")
    suspend fun getAll(@Header("Authorization") authHeader: String): ApiResponseDto<List<Carts>>

    @PUT("v1/carts")
    suspend fun update(@Header("Authorization") authHeader: String,
                       @Body request: CartUpdationRequestDto) : ApiResponseDto<Carts>
    @POST("v1/carts")
    suspend fun addToCart(@Header("Authorization") authHeader: String,
                       @Body request: CartUpdationRequestDto) : ApiResponseDto<Carts>
}