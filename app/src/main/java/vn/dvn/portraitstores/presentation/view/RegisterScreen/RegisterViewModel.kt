package vn.dvn.portraitstores.presentation.view.RegisterScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.data.data_source.remote.dto.RegisterDto
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.use_cases.user_usecase.AuthenUsecase
import vn.dvn.portraitstores.domain.use_cases.user_usecase.UserUsecase
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.LoginScreen.LoginState
import javax.inject.Inject
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userUsecase: UserUsecase
) : ViewModel(){

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState: StateFlow<RegisterState> get() = _registerState


    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _fullname = MutableStateFlow("")
    val fullname: StateFlow<String> get() = _fullname

    private val _mobileNumber = MutableStateFlow("")
    val mobileNumber: StateFlow<String> get() = _mobileNumber

        private val _confirmPhoneNumber = MutableStateFlow(false)
    val confirmPhoneNumber: StateFlow<Boolean> get() = _confirmPhoneNumber

    private val _confirmEmail = MutableStateFlow(false)
    val confirmEmail: StateFlow<Boolean> get() = _confirmEmail


    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword: StateFlow<String> get() = _confirmPassword

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _confirm = MutableStateFlow(false)
    val confirm: StateFlow<Boolean> get() = _confirm

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun onChanceEmail(email : String) {
        if (isValidEmail(email)) {
            _confirmEmail.value = true
        } else {
            _confirmEmail.value = true
        }
        _email.value = email
    }
    fun onChancePassword(password : String) {
        _password.value = password
    }
    fun onChanceConfirmPassword(confirmPassword : String) {
        _confirmPassword.value = confirmPassword
        if (_password.value == _confirmPassword.value) {
            _confirm.value = true
        }else
        {
            _confirm.value = false
        }
    }

    fun onChanceMobileNumber(mobileNumber : String) {
        if (mobileNumber.all { it.isDigit() }) {
            if (mobileNumber.length in 10 .. 11) {
                _confirmPhoneNumber.value = true
            } else {
                _confirmPhoneNumber.value = false
            }
            _mobileNumber.value = mobileNumber
        }
    }
    fun onChanceFullname(fullname : String) {
        _fullname.value = fullname
    }

    fun onRegister(navController: NavController) {
        if(!_confirmEmail.value)
        {
            _errorMessage.value = "Incorrectly formatted emails"
            return
        }else if(!_confirmPhoneNumber.value)
        {
            _errorMessage.value = "Incorrectly formatted Phone Number"
            return
        }else if(!_confirm.value)
        {
            _errorMessage.value = "Password does not match"
            return
        }else if(_fullname.value.isEmpty()||_email.value.isEmpty()||_password.value.isEmpty()||_mobileNumber.value.isEmpty())
        {
            _errorMessage.value = "Please fill in all fields"
            return
        }
        val registerDto: RegisterDto = RegisterDto(name = _fullname.value, email = _email.value, password = _password.value, phoneNumber = _mobileNumber.value, purchased = 0, role = "", state = "")
        userUsecase.registerUsecase.invoke(registerDto).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.e("TAG", "success" )
                    _registerState.value = RegisterState(loginResult = result.data)
                    navController.navigate(Screen.VerificationScreen.route+"/${result.data?.email}")
                }
                is Resource.Error -> {
                    _registerState.value = RegisterState(error = result.message ?: "An unexpected error occurred")
                    _errorMessage.value = "Email was existed !"
                    Log.e("TAG", "error: ${result.message}")
                }

                is Resource.Loading -> {
                    _registerState.value = RegisterState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}



data class RegisterState(
    val isLoading: Boolean = false,
    val loginResult: RegisterDto? =null,
    val error: String = ""
)