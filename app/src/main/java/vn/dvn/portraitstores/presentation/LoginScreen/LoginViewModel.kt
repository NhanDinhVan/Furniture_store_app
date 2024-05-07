package vn.dvn.portraitstores.presentation.LoginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.domain.use_cases.product_usecase.GetAllProductUseCase
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

) : ViewModel()
{
    var email by mutableStateOf("")
    var password by mutableStateOf("")



}