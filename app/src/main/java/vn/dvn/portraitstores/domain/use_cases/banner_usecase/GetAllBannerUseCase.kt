package vn.dvn.portraitstores.domain.use_cases.banner_usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.domain.model.Banner
import vn.dvn.portraitstores.domain.repository.BannerRepository
import java.io.IOException
import javax.inject.Inject

class GetAllBannerUseCase @Inject constructor(
    private val bannerRepository: BannerRepository
){
    operator fun invoke(): Flow<Resource<List<Banner>>> = flow {
        try {
            emit(Resource.Loading<List<Banner>>())
            val bannerList = bannerRepository.getAll().result
            emit(Resource.Success<List<Banner>>(bannerList!!))
        }catch (e: HttpException)
        {
            emit(Resource.Error<List<Banner>>(message = e.localizedMessage?: "An unexpected error occured"))

        }catch (e: IOException)
        {
            emit(Resource.Error<List<Banner>>(message = e.localizedMessage?:"IO Exception"))

        }
    }
}