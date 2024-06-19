package vn.dvn.portraitstores.domain.repository

import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.CartUpdationRequestDto
import vn.dvn.portraitstores.domain.model.Carts

interface CartRepository {
    suspend fun getAll( authHeader: String): ApiResponseDto<List<Carts>>;
    suspend fun update( authHeader: String, request: CartUpdationRequestDto): ApiResponseDto<Carts>;
    suspend fun addToCart( authHeader: String, request: CartUpdationRequestDto): ApiResponseDto<Carts>;
}