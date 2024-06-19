package vn.dvn.portraitstores.data.data_source.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.RegisterDto

interface UserApi {
    @POST("v1/users")
    suspend fun register(@Body request: RegisterDto
    ): ApiResponseDto<RegisterDto>

    @POST("v1/users/verifyCode/{email}/{code}")
    suspend fun verifyCode(@Path("email") email: String, @Path("code") code: String
    ): ApiResponseDto<Boolean>
}