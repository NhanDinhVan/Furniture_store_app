package vn.dvn.portraitstores.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import vn.dvn.portraitstores.presentation.LoginScreen.LoginScreen
import vn.dvn.portraitstores.presentation.home_screen.HomeScreen
import vn.dvn.portraitstores.ui.theme.PortraitStoresTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show()
        setContent {
            PortraitStoresTheme {
                Surface {
                    val navController = rememberNavController()
                    
                    NavHost(navController =navController,
                        startDestination = Screen.HomeScreen.route
                    ){
                        composable(
                            route= Screen.HomeScreen.route
                        ){
                            HomeScreen(navController = navController)
                        }
                    }
                }
            }
        }
        }
                                                                                                                                                                                                                                                                                                                    }

