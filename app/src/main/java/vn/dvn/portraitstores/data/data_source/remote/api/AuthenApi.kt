package vn.dvn.portraitstores.data.data_source.remote.api

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.AuthenResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.AuthenticateRequest

interface AuthenApi {
    @POST("v1/auth/token")
    suspend fun authenticate(@Body request: AuthenticateRequest
    ):ApiResponseDto<AuthenResponseDto>

}