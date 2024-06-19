package vn.dvn.portraitstores.domain.use_cases.user_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class AuthenUsecase @Inject constructor(
    private val userRepository: UserRepository
){
    operator fun invoke(email: String, password: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading<User>())
        val  authenResult = userRepository.Authenticate(email,password)
            val user = User(token = authenResult.result?.token)
            userRepository.saveUser(user)
            emit(Resource.Success<User>(user))

        }catch (e: HttpException)
        {

            emit(Resource.Error(message = e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException)
        {
            emit(Resource.Error(message = e.localizedMessage?:"IO Exception"))
        }
    }
}
