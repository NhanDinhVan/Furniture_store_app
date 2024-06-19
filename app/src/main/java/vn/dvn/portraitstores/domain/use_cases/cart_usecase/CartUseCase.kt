package vn.dvn.portraitstores.domain.use_cases.cart_usecase

data class CartUseCase(
    val getAllCartUseCase: GetAllCartUseCase,
    val addToCartUseCase: AddToCartUseCase,
    val updateCartUseCase: UpdateCartUseCase
)