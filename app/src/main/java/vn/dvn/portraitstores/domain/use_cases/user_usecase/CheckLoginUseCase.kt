package vn.dvn.portraitstores.domain.use_cases.user_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class CheckLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())

            val result = userRepository.getUserWithTrueState()

            emit(Resource.Success<User>(result))
        }catch (e: HttpException)
        {
            emit(Resource.Error(message = e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException)
        {
            emit(Resource.Error(message = e.localizedMessage?:"IO Exception"))
        }
    }
}