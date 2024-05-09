package vn.dvn.portraitstores.presentation.home_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import vn.dvn.portraitstores.presentation.components.ProductCard
import vn.dvn.portraitstores.presentation.components.ProductList

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel(),
)
{
    val state = homeScreenViewModel.state.value

   ProductList(productList = state.productList)

    if(state.error.isNotBlank())
    {
        Text(
            text = state.error,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
        )
    }
    if(state.isLoading) {
        Text(
            text ="Loading",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxSize()
        )
    }

}