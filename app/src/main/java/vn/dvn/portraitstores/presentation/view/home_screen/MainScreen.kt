package vn.dvn.portraitstores.presentation.view.home_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import vn.dvn.portraitstores.domain.model.User

@Composable
fun MainScreen(navController: NavController, user: User)
{
    val navGraphController = rememberNavController()

    Scaffold(
//        bottomBar = { BottomBar(navController = navGraphController) }
    ) {
        Column(modifier = Modifier.padding(it)) {
//            BottomNavGraph(navGraphController, navController, user)
        }
    }
}