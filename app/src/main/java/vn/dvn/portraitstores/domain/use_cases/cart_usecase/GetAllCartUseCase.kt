package vn.dvn.portraitstores.domain.use_cases.cart_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.repository.CartRepository
import java.io.IOException
import javax.inject.Inject

class GetAllCartUseCase @Inject constructor(
    private val cartRepository: CartRepository
){
    operator fun invoke(authHeader: String): Flow<Resource<List<Carts>>> = flow {
        try {
            emit(Resource.Loading<List<Carts>>())

            val cartList = cartRepository.getAll(Constants.BEARER_TOKEN + authHeader).result

            if(cartList!=null)
            {
                emit(Resource.Success<List<Carts>>(cartList))
            }else
            {
                emit(Resource.Success<List<Carts>>(emptyList()))
            }
        }catch (e: HttpException)
        {
            emit(Resource.Error<List<Carts>>(message = e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException)
        {
            emit(Resource.Error<List<Carts>>(message = e.localizedMessage?:"IO Exception"))
        }
    }
}
