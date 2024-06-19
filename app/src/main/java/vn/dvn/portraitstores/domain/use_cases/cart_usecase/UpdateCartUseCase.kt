package vn.dvn.portraitstores.domain.use_cases.cart_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.CartUpdationRequestDto
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.repository.CartRepository
import java.io.IOException
import javax.inject.Inject

class UpdateCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
){
    operator fun invoke(authHeader: String, cartUpdationRequestDto: CartUpdationRequestDto): Flow<Resource<Carts>> = flow {
        try {
            emit(Resource.Loading<Carts>())

            val result = cartRepository.update(Constants.BEARER_TOKEN + authHeader , cartUpdationRequestDto).result

            emit(Resource.Success<Carts>(result!!))
        }catch (e: HttpException)
        {
            emit(Resource.Error<Carts>(message = e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException)
        {
            emit(Resource.Error<Carts>(message = e.localizedMessage?:"IO Exception"))
        }
    }
}
