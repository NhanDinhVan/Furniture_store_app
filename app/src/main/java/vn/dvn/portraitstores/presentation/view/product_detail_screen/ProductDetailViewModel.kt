package vn.dvn.portraitstores.presentation.view.product_detail_screen

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.CartUpdationRequestDto
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.model.Product
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.use_cases.cart_usecase.CartUseCase
import vn.dvn.portraitstores.domain.use_cases.product_usecase.ProductUsecase
import vn.dvn.portraitstores.presentation.navigation.CheckLoginState
import vn.dvn.portraitstores.presentation.view.Cart_Screen.CartListState
import vn.dvn.portraitstores.presentation.view.home_screen.ProductListState
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val productUseCase: ProductUsecase,
    private val cartUseCase: CartUseCase
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartListState())
    val cartState: StateFlow<CartListState> get() = _cartState

    private val update_quantity = MutableStateFlow(0)


// suggested product
    private val _suggestedProductListState = MutableStateFlow(ProductListState())
    val suggestedProductListState: StateFlow<ProductListState> get() = _suggestedProductListState

    private val _addToCartState = MutableStateFlow(AddToCartState())
    val addToCartState: StateFlow<AddToCartState> get() = _addToCartState


//storage user
    private val _user = MutableStateFlow(CheckLoginState())

    val user: StateFlow<CheckLoginState> get() = _user

    fun setUser(user: User) {
        _user.value = CheckLoginState(user = user)
    }
//product detail
    private val _product = MutableStateFlow(ProductState())

    val product: StateFlow<ProductState> get() = _product

    fun setProduct(product: Product) {
        _product.value = ProductState(product = product)
    }
//quantity
    private val _quantity = MutableStateFlow(1)
    val quantity: StateFlow<Int> get() = _quantity

    fun setQuantity(quantity: Int) {
        _quantity.value = quantity
    }

//ModalBottomSheet

    @OptIn(ExperimentalMaterialApi::class)
    private val _sheetState = MutableStateFlow(ModalBottomSheetState(ModalBottomSheetValue.Hidden))
    @OptIn(ExperimentalMaterialApi::class)
    val sheetState: StateFlow<ModalBottomSheetState> get() = _sheetState

    @OptIn(ExperimentalMaterialApi::class)
    fun onShowBottomSheet() {
        _sheetState.value = ModalBottomSheetState(ModalBottomSheetValue.Expanded)
    }

    @OptIn(ExperimentalMaterialApi::class)
    fun onHideBottomSheet() {
        _sheetState.value = ModalBottomSheetState(ModalBottomSheetValue.Hidden)
    }

    fun getSuggestedProducts()
    {
        productUseCase.getProductByCategoryId.invoke(_product.value.product!!.category.categoryId).onEach {result->
            when(result){
                is Resource.Success -> {
                    _suggestedProductListState.value = ProductListState(productList = result.data?: emptyList() )
                }
                is Resource.Error -> {
                    _suggestedProductListState.value = ProductListState(
                        error = result.message?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _suggestedProductListState.value = ProductListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateCart(productId: String)
    {
        var updatedQuantity = 0
        val updatedList = _cartState.value.cartList
        val existingItem = updatedList.find { it.product.id == productId }
        if (existingItem != null) {
            updatedQuantity = existingItem.quantity + _quantity.value
            Log.e("Tag", "not null")
        }else{
            updatedQuantity = _quantity.value
            Log.e("Tag", "null " )
        }

        cartUseCase.updateCartUseCase.invoke(authHeader = "${_user.value.user!!.token}", CartUpdationRequestDto(productId, updatedQuantity )).onEach { result->
            when(result){
                is Resource.Success -> {
                    onHideBottomSheet()
                    _addToCartState.value = AddToCartState(isSuccessful = true)
                }
                is Resource.Error -> {
                    _addToCartState.value = AddToCartState(
                        error = result.message?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _addToCartState.value = AddToCartState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCarts()
    {
        cartUseCase.getAllCartUseCase.invoke(authHeader = "${_user.value.user!!.token}").onEach { result->
            when(result){
                is Resource.Success -> {
                    _cartState.value = CartListState(cartList = result.data?: emptyList() )
                    Log.e("Tag", "getCarts: " + _cartState.value.cartList.toString() )
                    Log.e("Tag", "user: " + _user.toString())
                }
                is Resource.Error -> {
                    _cartState.value = CartListState(
                        error = result.message?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _cartState.value = CartListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}

data class ProductState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val error: String = ""
)
data class AddToCartState(
    val isLoading: Boolean = false,
    val isSuccessful: Boolean = false,
    val error: String = ""
)