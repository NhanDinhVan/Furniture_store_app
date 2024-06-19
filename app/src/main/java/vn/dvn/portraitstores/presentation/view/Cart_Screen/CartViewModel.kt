package vn.dvn.portraitstores.presentation.view.Cart_Screen

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.CartUpdationRequestDto
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.use_cases.cart_usecase.CartUseCase
import vn.dvn.portraitstores.presentation.navigation.CheckLoginState
import vn.dvn.portraitstores.presentation.view.home_screen.ProductListState
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {
    private val _cartState = MutableStateFlow(CartListState())
    val cartState: StateFlow<CartListState> get() = _cartState

    private val _user = MutableStateFlow(CheckLoginState())
    val user: StateFlow<CheckLoginState> get() = _user

    fun setUser(user: User) {
        _user.value = CheckLoginState(user = user)
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


fun updateCart(cartDetail: Carts, quantity: Int)
    {
        val updatedList = _cartState.value.cartList.map {
            if (it.product.id == cartDetail.product.id) {
                if (quantity != 0 )
                {
                    it.copy(quantity = quantity)
                } else {
                    null
                }
            } else {
                it
            }
        }.filterNotNull()
        _cartState.value = CartListState(cartList = updatedList)
            cartUseCase.updateCartUseCase.invoke(authHeader = "${_user.value.user!!.token}", CartUpdationRequestDto(cartDetail.product.id, quantity)).onEach { result->
                when(result){
                    is Resource.Success -> {
//                        getCarts()
                    }
                    is Resource.Error -> {
                        _cartState.value = CartListState(
                            error = result.message?: "An unexpected error occured"
                        )
                    }
                    is Resource.Loading -> {
//                        _cartState.value = CartListState(isLoading = true)
                    }
                }
            }.launchIn(viewModelScope)
    }
}

data class CartListState(
    val isLoading: Boolean = false,
    val cartList:List<Carts> = emptyList(),
    val error: String = ""
)