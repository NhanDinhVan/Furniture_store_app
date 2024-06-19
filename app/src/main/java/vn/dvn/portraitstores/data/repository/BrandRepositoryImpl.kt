package vn.dvn.portraitstores.data.repository

import vn.dvn.portraitstores.data.data_source.remote.api.BrandApi
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.domain.model.Brands
import vn.dvn.portraitstores.domain.repository.BrandRepository
import javax.inject.Inject

class BrandRepositoryImpl @Inject constructor(
    private val brandApi: BrandApi
): BrandRepository {
    override suspend fun getAll(): ApiResponseDto<List<Brands>> {
        return brandApi.getAll()
    }
}