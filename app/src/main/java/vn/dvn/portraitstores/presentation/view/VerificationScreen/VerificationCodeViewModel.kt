package vn.dvn.portraitstores.presentation.view.VerificationScreen
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
import vn.dvn.portraitstores.domain.use_cases.user_usecase.UserUsecase
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.RegisterScreen.RegisterState
import javax.inject.Inject

@HiltViewModel
class VerificationCodeViewModel @Inject constructor(
    private val userUsecase: UserUsecase
) : ViewModel() {

    private val _verificationState = MutableStateFlow(VerificationState())
    val verificationState: StateFlow<VerificationState> get() = _verificationState

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> get() = _errorMessage

    private val _code = MutableStateFlow(List(5) { "" })
    val code: StateFlow<List<String>> get() = _code

    private val _isCodeValid = MutableStateFlow(false)
    val isCodeValid: StateFlow<Boolean> get() = _isCodeValid




    fun onCodeChanged(index: Int, value: String) {
        if (value.length <= 1 && value.all { it.isDigit() }) {
            val updatedCode = _code.value.toMutableList()
            updatedCode[index] = value
            _code.value = updatedCode
            _isCodeValid.value = updatedCode.all { it.isNotEmpty() }
        }
    }

    fun onSubmit(email: String,navController: NavController) {
        val verificationCode = _code.value.joinToString("")
        // Handle verification code submission
        println("Verification Code: $verificationCode")
        println("Verification email: $email")
        userUsecase.verifyCodeUsecase.invoke(email, verificationCode).onEach {  result ->
            when (result) {
                is Resource.Success -> {
                    Log.e("TAG", "success" )
                    _verificationState.value = VerificationState(result = result.data)
                    if (result.data == true)
                    {
                        Log.e("TAG", "true" )
                        navController.navigate(Screen.LoginScreen.route)
                        navController.popBackStack(Screen.VerificationScreen.route+"/{email}", inclusive = true)
                        navController.popBackStack(Screen.Register.route, inclusive = true)
                    }
                    else
                    {
                        Log.e("TAG", "false" )
                        _errorMessage.value = "Invalid verification code"
                        _verificationState.value = VerificationState(error = "Invalid verification code")
                    }
                }
                is Resource.Error -> {
                    _verificationState.value = VerificationState(error = result.message ?: "An unexpected error occurred")
                    Log.e("TAG", "error: ${result.message}")
                }

                is Resource.Loading -> {
                    _verificationState.value = VerificationState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }
}

data class VerificationState(
    val isLoading: Boolean = false,
    val result: Boolean?= null,
    val error: String = ""
)