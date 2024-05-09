package vn.dvn.portraitstores.presentation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("HomeScreen")
    object LoginScreen: Screen("LoginScreen")
    object Register: Screen("RegisterScreen")
    object ProductDetailScreen: Screen("ProductDetailScreen")
}