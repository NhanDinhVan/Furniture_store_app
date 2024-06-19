package vn.dvn.portraitstores.presentation.navigation

import vn.dvn.portraitstores.R

sealed class Screen(val route: String,
    val title: String = "",
    val icon: Int= 0,
    val iconFocused: Int = 0
    ) {
    object HomeScreen: Screen(
        route = "HomeScreen",
        title = "Home",
        icon = R.drawable.ic_bottom_home,
        iconFocused = R.drawable.ic_bottom_home_focused
    )
    object Report: Screen(
        route = "report",
        title = "Report",
        icon = R.drawable.ic_bottom_report,
        iconFocused = R.drawable.ic_bottom_report_focused
    )

    // for report
    object Profile: Screen(
        route = "ProfileScreen",
        title = "Profile",
        icon = R.drawable.ic_bottom_profile,
        iconFocused = R.drawable.ic_bottom_profile_focused
    )

    object SearchScreen: Screen(
        route = "SearchScreen",
        title = "Search",
                icon = R.drawable.ic_bottom_profile,
        iconFocused = R.drawable.ic_bottom_profile_focused
    )
    object LoginScreen: Screen("LoginScreen")
    object CartScreen: Screen("CartScreen")
    object MainScreen: Screen("MainScreen")
    object Register: Screen("RegisterScreen")
    object ProductDetailScreen: Screen("ProductDetailScreen")
    object SplashScreen: Screen("SplashScreen")
    object DashboardScreen: Screen("DashboardScreen")
    object ProductListScreen: Screen("ProductListScreen")
    object VerificationScreen: Screen("VerificationScreen")
    object LoadingScreen: Screen("LoadingScreen")
    object ErrorScreen: Screen("ErrorScreen")
    object PaymentScreen: Screen("PaymentScreen")
    object CheckoutScreen: Screen("CheckoutScreen")

}