package vn.dvn.portraitstores.domain.use_cases.user_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.RegisterDto
import vn.dvn.portraitstores.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class VerifyCodeUsecase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(email: String, code: String): Flow<Resource<Boolean>> = flow {
        try {
            emit(Resource.Loading<Boolean>())
        val  verifyCodeResult = userRepository.verifyCode(email,code)
            emit(Resource.Success<Boolean>(verifyCodeResult.result))
        }catch (e: HttpException)
        {
            emit(Resource.Error(message = e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException)
        {
            emit(Resource.Error(message = e.localizedMessage?:"IO Exception"))
        }
    }
}
