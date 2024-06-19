package vn.dvn.portraitstores.domain.use_cases.user_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.RegisterDto
import vn.dvn.portraitstores.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class RegisterUsecase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(registerDto: RegisterDto): Flow<Resource<RegisterDto>> = flow {
        try {
            emit(Resource.Loading<RegisterDto>())
        val  registerResult = userRepository.register(registerDto)
            emit(Resource.Success<RegisterDto>(registerResult.result))
        }catch (e: HttpException)
        {
            emit(Resource.Error(message = e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException)
        {
            emit(Resource.Error(message = e.localizedMessage?:"IO Exception"))
        }
    }
}
