package vn.dvn.portraitstores.presentation.view.Check_out_Screen

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.OrderRequestDto
import vn.dvn.portraitstores.data.data_source.remote.dto.TransactionRequestDto
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.model.Transaction
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.use_cases.cart_usecase.CartUseCase
import vn.dvn.portraitstores.domain.use_cases.transaction_usecase.TransactionUsecase
import vn.dvn.portraitstores.presentation.navigation.CheckLoginState
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.Cart_Screen.CartListState
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val transactionUsecase: TransactionUsecase,
    private val cartUsecase: CartUseCase
) : ViewModel() {

    private val _cartState = MutableStateFlow(CartListState())
    val cartState: StateFlow<CartListState> get() = _cartState

    private val _checkoutState = MutableStateFlow(CheckoutState())
    val checkoutState: StateFlow<CheckoutState> get() = _checkoutState

    private val _user = MutableStateFlow(CheckLoginState())

    val user: StateFlow<CheckLoginState> get() = _user

    fun setUser(user: User) {
        _user.value = CheckLoginState(user = user)
    }

    val cartList : List<Carts> = _cartState.value.cartList

    //quantity
    private val _message = MutableStateFlow("")
    val message: StateFlow<String> get() = _message

    fun onChangeMessage(message: String) {
        _message.value = message
    }

    //quantity
    private val _address = MutableStateFlow("")
    val address: StateFlow<String> get() = _address

    fun onChangeAddress(address: String) {
        _address.value = address
    }

    val paymentMethods = listOf("Credit Card", "COD")
//    var _selectedPaymentMethod = MutableStateFlow(paymentMethods[0])



    val _selectedPaymentMethod = MutableStateFlow("Credit Card")
    val selectedPaymentMethod: StateFlow<String> = _selectedPaymentMethod

    fun onChangePaymentMethod(method: String) {

            _selectedPaymentMethod.value = method

    }

    fun getCarts()
    {
        cartUsecase.getAllCartUseCase.invoke(authHeader = "${_user.value.user!!.token}").onEach { result->
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

    fun checkout()
    {
        getCarts()
        val orderList: List<OrderRequestDto> = cartList.map { cartItem ->
            OrderRequestDto(
                userId = _user.value.user!!.id.toString(),
            productId = cartItem.product.id,
            quantity =cartItem.quantity,
            amount = cartItem.product.price*cartItem.quantity
            )
        }
        val transactionRequestDto: TransactionRequestDto = TransactionRequestDto(
            amount = orderList.sumOf { it.amount },
        message = _message.value,
        payment = _selectedPaymentMethod.value,
        userId = _user.value.user!!.id.toString(),
        address = _address.value,
        ordersList = orderList
        )

        transactionUsecase.checkOutUsecase.invoke(authHeader = "${_user.value.user!!.token}", transactionRequestDto).onEach { result->
            when(result){
                is Resource.Success -> {
                    _checkoutState.value = CheckoutState(transaction = result.data)
                    Log.e("Tag", "checkout: " + orderList.toString())
                    Log.e("Tag", "user: " + transactionRequestDto.toString())
                }
                is Resource.Error -> {
                    _checkoutState.value = CheckoutState(
                        error = result.message?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _checkoutState.value = CheckoutState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
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
}

data class CheckoutState(
    val isLoading: Boolean = false,
    val transaction:Transaction ?= null,
    val error: String = ""
)