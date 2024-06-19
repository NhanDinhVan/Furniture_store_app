package vn.dvn.portraitstores.data.repository

import vn.dvn.portraitstores.data.data_source.local.UserDao
import vn.dvn.portraitstores.data.data_source.remote.api.AuthenApi
import vn.dvn.portraitstores.data.data_source.remote.api.UserApi
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.AuthenResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.AuthenticateRequest
import vn.dvn.portraitstores.data.data_source.remote.dto.RegisterDto
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authenApi: AuthenApi,
    private val userApi: UserApi,
    private val userDao: UserDao
) : UserRepository {
    override suspend fun Authenticate(
        email: String,
        password: String,
    ): ApiResponseDto<AuthenResponseDto> {
        return authenApi.authenticate(AuthenticateRequest(email,password))
    }

    override suspend fun register(registerDto: RegisterDto): ApiResponseDto<RegisterDto> {
        return userApi.register(registerDto);
    }

    override suspend fun verifyCode(email: String, code: String): ApiResponseDto<Boolean> {
        return userApi.verifyCode(email,code)
    }


    override suspend fun getUserWithTrueState(): User {
        return userDao.getUserByState(true)
    }

    override suspend fun saveUser(user: User) {
       userDao.insertAll(user)
    }
}