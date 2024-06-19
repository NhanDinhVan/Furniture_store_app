package vn.dvn.portraitstores.data.repository

import vn.dvn.portraitstores.data.data_source.remote.api.CartApi
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.CartUpdationRequestDto
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartApi: CartApi
) : CartRepository {
    override suspend fun getAll(authHeader: String): ApiResponseDto<List<Carts>> {
        return cartApi.getAll(authHeader)
    }

    override suspend fun update(
        authHeader: String,
        request: CartUpdationRequestDto,
    ): ApiResponseDto<Carts> {
        return cartApi.update(authHeader, request)
    }

    override suspend fun addToCart(
        authHeader: String,
        request: CartUpdationRequestDto,
    ): ApiResponseDto<Carts> {
        return cartApi.addToCart(authHeader, request)
    }

}