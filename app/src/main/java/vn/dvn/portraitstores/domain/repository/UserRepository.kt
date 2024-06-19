package vn.dvn.portraitstores.domain.repository

import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.AuthenResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.RegisterDto
import vn.dvn.portraitstores.domain.model.User

interface UserRepository {
    suspend fun Authenticate(email:String, password: String): ApiResponseDto<AuthenResponseDto>

    suspend fun register(registerDto: RegisterDto): ApiResponseDto<RegisterDto>

    suspend fun verifyCode(email:String, code: String): ApiResponseDto<Boolean>

    suspend fun getUserWithTrueState(): User

    suspend fun saveUser(user: User)
}