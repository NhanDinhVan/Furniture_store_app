package vn.dvn.portraitstores.presentation.view.LoginScreen

import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.ApiResponseDto
import vn.dvn.portraitstores.data.data_source.remote.dto.AuthenResponseDto
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.use_cases.user_usecase.AuthenUsecase
import vn.dvn.portraitstores.presentation.navigation.Navigation
import vn.dvn.portraitstores.presentation.navigation.NavigationViewModel
import vn.dvn.portraitstores.presentation.navigation.Screen
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenUsecase: AuthenUsecase,
) : ViewModel() {


    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email


    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage


    private val _stateLogin = MutableStateFlow(LoginState())
    val stateLogin: StateFlow<LoginState> get() = _stateLogin

    fun onChanceEmail(email : String) {
        _email.value = email
    }
    fun onChancePassword(password : String) {
        _password.value = password
    }

    fun login(navController: NavController, navigationViewModel: NavigationViewModel) {
        Log.e("TAG", "login: ",)
        Log.e("TAG", _email.value.toString(),)
        Log.e("TAG", _password.value.toString(),)

        authenUsecase(_email.value, _password.value).onEach { result ->

            when (result) {
                is Resource.Success -> {

                    _stateLogin.value = LoginState(loginResult = result.data?: null)

                        Log.e("TAG", "not null" )

                    navigationViewModel.checkLogin(navController)
                    navController.popBackStack(Screen.LoginScreen.route, inclusive = true)
                    navController.popBackStack(Screen.SplashScreen.route, inclusive = true)

//                    navController.navigate(Screen.HomeScreen.route+ "/${Gson().toJson(result.data)}")
                }

                is Resource.Error -> {

                    _errorMessage.value = "Check your email or password!"

                    _stateLogin.value = LoginState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _stateLogin.value = LoginState(isLoading = true)
//                    navController.navigate(Screen.LoadingScreen.route)
                }
            }
        }.launchIn(viewModelScope)
    }



}





data class LoginState(
    val isLoading: Boolean = false,
    val loginResult: User? =null,
    val error: String = ""
)