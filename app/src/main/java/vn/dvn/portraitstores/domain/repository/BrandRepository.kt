package vn.dvn.portraitstores.domain.repository

import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.domain.model.Brands

interface BrandRepository {

    suspend fun getAll(): ApiResponseDto<List<Brands>>
}