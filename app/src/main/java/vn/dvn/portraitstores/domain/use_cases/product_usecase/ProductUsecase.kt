package vn.dvn.portraitstores.domain.use_cases.product_usecase

data class ProductUsecase(
    val getAllProductUseCase: GetAllProductUseCase,
    val getProductByCategoryId: GetProductByCategoryId
)
