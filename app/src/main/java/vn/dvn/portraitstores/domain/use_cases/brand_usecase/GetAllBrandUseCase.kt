package vn.dvn.portraitstores.domain.use_cases.brand_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.domain.model.Brands
import vn.dvn.portraitstores.domain.repository.BrandRepository
import java.io.IOException
import javax.inject.Inject

class GetAllBrandUseCase @Inject constructor(
    private val brandRepository: BrandRepository
) {
    operator fun invoke(): Flow<Resource<List<Brands>>> = flow {
        try {
            emit(Resource.Loading<List<Brands>>())
            val brandList = brandRepository.getAll().result
            emit(Resource.Success<List<Brands>>(brandList!!))
        }catch (e: HttpException)
        {
            emit(Resource.Error<List<Brands>>(message = e.localizedMessage?: "An unexpected error occured"))

        }catch (e: IOException)
        {
            emit(Resource.Error<List<Brands>>(message = e.localizedMessage?:"IO Exception"))

        }
    }

}