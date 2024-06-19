package vn.dvn.portraitstores.presentation.view.home_screen

import vn.dvn.portraitstores.domain.model.Banner
import vn.dvn.portraitstores.domain.model.Brands
import vn.dvn.portraitstores.domain.model.Product

//sealed interface HomeUiState {
//    data class Success(val productList: List<Product>): HomeUiState
//    object Error: HomeUiState
//
//    object Loading: HomeUiState
//}

data class ProductListState(
    val isLoading: Boolean = false,
    val productList:List<Product> = emptyList(),
    val error: String = ""
)
data class BrandListState(
    val isLoading: Boolean = false,
    val brandList:List<Brands> = emptyList(),
    val error: String = ""
)
data class BannerListState(
    val isLoading: Boolean = false,
    val bannerList:List<Banner> = emptyList(),
    val error: String = ""
)
