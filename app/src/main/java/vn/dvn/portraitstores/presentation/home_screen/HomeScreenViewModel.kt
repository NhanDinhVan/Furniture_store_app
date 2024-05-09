package vn.dvn.portraitstores.presentation.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.ProductDto
import vn.dvn.portraitstores.domain.use_cases.product_usecase.GetAllProductUseCase
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase
): ViewModel()  {

    private val _state = mutableStateOf(ProductListState())
    val state: State<ProductListState> = _state

    init {
        getProducts()
    }

    private fun getProducts()
    {
        getAllProductUseCase().onEach {result->
            when(result){
                is Resource.Success -> {
                    _state.value = ProductListState(productList = result.data?: emptyList() )
                }
                is Resource.Error -> {
                   _state.value = ProductListState(
                       error = result.message?: "An unexpected error occured"
                   )
                }
                is Resource.Loading -> {
                    _state.value = ProductListState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }
}