package vn.dvn.portraitstores.domain.use_cases.product_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.toProduct
import vn.dvn.portraitstores.domain.model.Products
import vn.dvn.portraitstores.domain.repository.ProductRepository
import java.io.IOException
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
){
    operator fun invoke(): Flow<Resource<List<Products>>> = flow {
        try {
            emit(Resource.Loading<List<Products>>())

            val productList = productRepository.getAll().result!!.map {
                it.toProduct()
            }
            emit(Resource.Success<List<Products>>(productList))
        }catch (e: HttpException)
        {
            emit(Resource.Error<List<Products>>(message = e.localizedMessage?: "An unexpected error occured"))
        }catch (e: IOException)
        {
            emit(Resource.Error<List<Products>>(message = e.localizedMessage?:"IO Exception"))
        }
    }
}
