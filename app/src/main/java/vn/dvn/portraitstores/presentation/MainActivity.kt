package vn.dvn.portraitstores.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import vn.dvn.portraitstores.presentation.navigation.Navigation
import vn.dvn.portraitstores.presentation.navigation.NavigationViewModel
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.LoginScreen.LoginScreen
import vn.dvn.portraitstores.presentation.view.LoginScreen.LoginViewModel
import vn.dvn.portraitstores.presentation.view.home_screen.HomeScreen
import vn.dvn.portraitstores.ui.theme.PortraitStoresTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            PortraitStoresTheme {
                Surface {
                    val navigationViewModel: NavigationViewModel = hiltViewModel()
                    navigationViewModel.checkLoginState
                    Navigation(navigationViewModel)
                    }
                }
            }
        }

                                                                                                                                                                                                                                                                                                                    }

