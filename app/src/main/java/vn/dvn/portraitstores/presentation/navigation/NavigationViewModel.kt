package vn.dvn.portraitstores.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import vn.dvn.portraitstores.Common.Resource
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.domain.use_cases.user_usecase.CheckLoginUseCase
import vn.dvn.portraitstores.presentation.components.ErrorScreen
import vn.dvn.portraitstores.presentation.view.home_screen.BrandListState
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val checkLoginUseCase: CheckLoginUseCase
): ViewModel()
{


    private val _checkLoginState = MutableStateFlow(CheckLoginState())
    val checkLoginState = _checkLoginState

    private val _desScreen = MutableStateFlow(Screen.LoadingScreen.route)
    val desScreen : StateFlow<String> get() = _desScreen


     fun checkLogin(navController: NavController)
    {
        checkLoginUseCase().onEach {result->
            when(result){
                is Resource.Success -> {
                    _checkLoginState.value = CheckLoginState(user = result.data)
                    if(_checkLoginState.value.user==null)
                    {
                        navController.navigate(Screen.SplashScreen.route)
                        navController.popBackStack("check", inclusive = true)

//                        _desScreen.value = Screen.SplashScreen.route

                    }else
                    {
                        val userJson = Gson().toJson(_checkLoginState.value.user)
//                        _desScreen.value = Screen.HomeScreen.route+"/$userJson"
                        navController.navigate(Screen.HomeScreen.route+"/$userJson")
                    }
                }
                is Resource.Error -> {
                    _checkLoginState.value = CheckLoginState(
                        error = result.message?: "An unexpected error occured"
                    )
                    navController.navigate(Screen.ErrorScreen.route)
                }
                is Resource.Loading -> {
                    _checkLoginState.value = CheckLoginState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }


}
