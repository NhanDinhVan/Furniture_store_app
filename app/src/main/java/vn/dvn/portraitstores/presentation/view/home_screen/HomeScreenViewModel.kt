package vn.dvn.portraitstores.presentation.view.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.use_cases.banner_usecase.BannerUseCase
import vn.dvn.portraitstores.domain.use_cases.brand_usecase.BrandUseCase
import vn.dvn.portraitstores.domain.use_cases.product_usecase.GetAllProductUseCase
import vn.dvn.portraitstores.domain.use_cases.product_usecase.ProductUsecase
import vn.dvn.portraitstores.domain.use_cases.user_usecase.CheckLoginUseCase
import vn.dvn.portraitstores.presentation.navigation.CheckLoginState
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val productUseCase: ProductUsecase,
    private val brandUseCase: BrandUseCase,
    private val bannerUseCase: BannerUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel()  {

    private val _productListState = MutableStateFlow(ProductListState())
    val productListState: StateFlow<ProductListState> get() = _productListState

    private val _brandListState = MutableStateFlow(BrandListState())
    val brandListState: StateFlow<BrandListState> get() = _brandListState

    private val _bannerListState = MutableStateFlow(BannerListState())
    val bannerListState: StateFlow<BannerListState> get() = _bannerListState


    private val _user = MutableStateFlow(CheckLoginState())

    val user: StateFlow<CheckLoginState> get() = _user

    fun setUser(user: User) {
        _user.value = CheckLoginState(user = user)
    }

//Search Bar
    private val _searchContent = MutableStateFlow("")
    val searchContent: StateFlow<String> get() = _searchContent

    private val _isFocused = MutableStateFlow(false)
    val isFocused: StateFlow<Boolean> get() = _isFocused

    fun onChanceSearchContent(searchContent : String) {
        _searchContent.value = searchContent
    }
    fun onSearch() {
        _isFocused.value = false
    }
    fun onClearSearchContent() {
        _searchContent.value = ""
    }
    fun onChangeIsFocused(isFocused: Boolean = false) {
        _isFocused.value = isFocused
    }


    init {
        getProducts()
        getBanners()
        getBrands()
    }


    fun getProducts()
    {
        productUseCase.getAllProductUseCase().onEach {result->
            when(result){
                is Resource.Success -> {
                    _productListState.value = ProductListState(productList = result.data?: emptyList() )
                }
                is Resource.Error -> {
                    _productListState.value = ProductListState(
                       error = result.message?: "An unexpected error occured"
                   )
                }
                is Resource.Loading -> {
                    _productListState.value = ProductListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getBanners()
    {
        bannerUseCase.getAllBannerUseCase().onEach {result->
            when(result){
                is Resource.Success -> {
                    _bannerListState.value = BannerListState(bannerList = result.data?: emptyList() )
                }
                is Resource.Error -> {
                    _bannerListState.value = BannerListState(
                        error = result.message?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _bannerListState.value = BannerListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun getBrands()
    {
        brandUseCase.getAllBrandUseCase().onEach {result->
            when(result){
                is Resource.Success -> {
                    _brandListState.value = BrandListState(brandList = result.data?: emptyList() )
                }
                is Resource.Error -> {
                    _brandListState.value = BrandListState(
                        error = result.message?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _brandListState.value = BrandListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}