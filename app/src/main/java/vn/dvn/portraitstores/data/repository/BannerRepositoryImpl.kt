package vn.dvn.portraitstores.data.repository

import vn.dvn.portraitstores.data.data_source.remote.api.BannerApi
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.domain.model.Banner
import vn.dvn.portraitstores.domain.repository.BannerRepository

class BannerRepositoryImpl constructor(
    private val bannerApi: BannerApi
): BannerRepository {
    override suspend fun getAll(): ApiResponseDto<List<Banner>> {
        return bannerApi.getAll()
    }
}