package vn.dvn.portraitstores.presentation.navigation

import CartScreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.arvind.furnitureshop.view.ProductListScreen
import com.arvind.furnitureshop.view.SplashScreen
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import vn.dvn.portraitstores.domain.model.Product
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.presentation.components.ErrorScreen
import vn.dvn.portraitstores.presentation.components.LoadingScreen
import vn.dvn.portraitstores.presentation.view.Cart_Screen.CartViewModel
import vn.dvn.portraitstores.presentation.view.Check_out_Screen.CheckoutScreen
import vn.dvn.portraitstores.presentation.view.Check_out_Screen.CheckoutViewModel
import vn.dvn.portraitstores.presentation.view.Check_out_Screen.PaymentScreen
import vn.dvn.portraitstores.presentation.view.LoginScreen.LoginScreen
import vn.dvn.portraitstores.presentation.view.RegisterScreen.RegisterScreen
import vn.dvn.portraitstores.presentation.view.RegisterScreen.RegisterViewModel
import vn.dvn.portraitstores.presentation.view.VerificationScreen.VerificationCodeScreen
import vn.dvn.portraitstores.presentation.view.home_screen.HomeScreen
import vn.dvn.portraitstores.presentation.view.home_screen.HomeScreenViewModel
import vn.dvn.portraitstores.presentation.view.product_detail_screen.ProductDetailScreen
import vn.dvn.portraitstores.presentation.view.product_detail_screen.ProductDetailViewModel
import vn.dvn.portraitstores.presentation.view.product_detail_screen.component.BottomSheetScaffoldState
import vn.dvn.portraitstores.presentation.view.setting_screen.ProfileScreen

@Composable
fun Navigation(
    navigationViewModel: NavigationViewModel = hiltViewModel(),
) {

    val navController = rememberNavController()
    val checkoutViewModel: CheckoutViewModel = hiltViewModel()

    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NavHost(
                navController = navController,
                startDestination  = "check"
//                startDestination  = Screen.Register.route
            ) {

                composable(
                    route = Screen.LoginScreen.route
                ) {
                    LoginScreen(navController = navController, loginViewModel = hiltViewModel())
                }
                composable(
                    route = "check"
                ) {
                    navigationViewModel.checkLogin(navController)
                    navController.popBackStack("check", inclusive = true)
                }

                composable(
                    route = Screen.Register.route
                ) {
                    val registerViewModel: RegisterViewModel = hiltViewModel()
                    RegisterScreen(navController,registerViewModel)
//                    VerificationCodeScreen()
                }
                composable(
                    route = Screen.VerificationScreen.route+"/{email}"
                ) { backStackEntry ->
                    val email = backStackEntry.arguments?.getString("email")
                    VerificationCodeScreen(email = email.toString(), navController = navController)
                }

                composable(Screen.SplashScreen.route) {
                    SplashScreen(navController)
                }

                composable(Screen.ProductListScreen.route) {
                    ProductListScreen()
                }


                composable(Screen.ProductDetailScreen.route+ "/{user}/{product}",
                    arguments = listOf(navArgument("user") { type = NavType.StringType },
                        navArgument("product") { type = NavType.StringType })
                ) {backStackEntry ->
                    val userJson = backStackEntry.arguments?.getString("user")
                    val productJson = backStackEntry.arguments?.getString("product")

                    val user = Gson().fromJson(userJson, User::class.java)
                    val product = Gson().fromJson(productJson, Product::class.java)

                    val productDetailViewModel : ProductDetailViewModel = hiltViewModel()

                    if (user != null && product != null) {
                        productDetailViewModel.setUser(user)
                        productDetailViewModel.setProduct(product)

                        ProductDetailScreen(modifier = Modifier, productDetailViewModel = productDetailViewModel, navController = navController)
                    }

                }
                composable(Screen.LoadingScreen.route) {
                    LoadingScreen()
                }
                composable(Screen.ErrorScreen.route) {
                    ErrorScreen(
                        retryAction = {},
                        message = navigationViewModel.checkLoginState.collectAsState().value.error
                    )
                }
                composable(Screen.CartScreen.route + "/{user_cart}",
                    arguments = listOf(navArgument("user_cart") { type = NavType.StringType })
                ) { backStackEntry ->
                    val json = backStackEntry.arguments?.getString("user_cart")
                    val user = Gson().fromJson(json, User::class.java)
                    val cartViewModel: CartViewModel = hiltViewModel()
                    if (user != null) {
                        user.let { cartViewModel.setUser(it)
                        }
                        CartScreen(cartViewModel,navController = navController)
                    }

                }

                composable(
                    route = Screen.HomeScreen.route + "/{user}",
                    arguments = listOf(navArgument("user") { type = NavType.StringType })
                ) { backStackEntry ->
                   val json = backStackEntry.arguments?.getString("user")
                    val getted_user = Gson().fromJson(json, User::class.java)

                    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

                    getted_user?.let {
                        homeScreenViewModel.setUser(it)
                    }
                    HomeScreen(navController = navController, homeScreenViewModel)
                }

                composable(
                    route = Screen.Profile.route + "/{user}",
                    arguments = listOf(navArgument("user") { type = NavType.StringType })
                ) {backStackEntry ->

                        val json = backStackEntry.arguments?.getString("user")
                        val getted_user = Gson().fromJson(json, User::class.java)

                        getted_user?.let {
                            ProfileScreen(navController, user = it)
                        }
                }
                composable(
                    route = Screen.Report.route + "/{user}",
                    arguments = listOf(navArgument("user") { type = NavType.StringType })
                ) {backStackEntry ->

                        val json = backStackEntry.arguments?.getString("user")
                        val getted_user = Gson().fromJson(json, User::class.java)

//                        getted_user?.let {
//                            ProfileScreen(navController, user = it)
//                        }

                    BottomSheetScaffoldState()
                }
                composable(
                    route = Screen.CheckoutScreen.route + "/{user}",
                    arguments = listOf(navArgument("user") { type = NavType.StringType })
                ) {backStackEntry ->

                        val json = backStackEntry.arguments?.getString("user")
                        val getted_user = Gson().fromJson(json, User::class.java)
                        getted_user?.let {
                            checkoutViewModel.setUser(it)
                        }
                        CheckoutScreen(navController = navController, checkoutViewModel = checkoutViewModel)
                }

                composable(Screen.PaymentScreen.route){
                    PaymentScreen(navController = navController, checkoutViewModel = checkoutViewModel)
                }
            }
        }
    }
}


