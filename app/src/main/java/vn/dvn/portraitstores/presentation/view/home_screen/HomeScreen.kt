package vn.dvn.portraitstores.presentation.view.home_screen



import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.arvind.furnitureshop.component.HomeTopBar
import com.arvind.furnitureshop.view.BottomBar
import com.arvind.furnitureshop.view.CategoryBestOffers
import com.arvind.furnitureshop.view.CategoryChairs
import com.arvind.furnitureshop.view.CategoryMore
import com.arvind.furnitureshop.view.Header
import com.google.gson.Gson
import vn.dvn.portraitstores.presentation.components.ErrorScreen
import vn.dvn.portraitstores.presentation.components.LoadingScreen
import vn.dvn.portraitstores.presentation.components.ProductItem
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.home_screen.component.FeaturedBrand
import vn.dvn.portraitstores.presentation.view.home_screen.component.ImageSlide
import vn.dvn.portraitstores.presentation.view.home_screen.component.SearchBar


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
)
{
    val productState = homeScreenViewModel.productListState.collectAsState().value
    val bannerState = homeScreenViewModel.bannerListState.collectAsState().value
    val brandState = homeScreenViewModel.brandListState.collectAsState().value
    val user = homeScreenViewModel.user.collectAsState().value.user
    val itemList = listOf("Chairs", "Sofas", "Beds", "Tables")

    val searchContent by homeScreenViewModel.searchContent.collectAsState()
    val isFocused by homeScreenViewModel.isFocused.collectAsState()


    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                user = user
            )
        }
    ) {

        LazyColumn(modifier = Modifier
            .background(Color.White)
            .padding(it)) {

            stickyHeader {
                Card(modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(0.dp,0.dp,15.dp,15.dp),
                    elevation = CardDefaults.elevatedCardElevation(10.dp)
                ) {
                    HomeTopBar(title = "Dinh Van Nhan"){}
                    SearchBar(
                        text = searchContent,
                        onClickCart = { navController.navigate(Screen.CartScreen.route+"/${Gson().toJson(user)}") },
                        isFocused = isFocused,
                        homeScreenViewModel = homeScreenViewModel
                    )
                }
            }
            item {
                val images = bannerState.bannerList
                ImageSlide(modifier = Modifier, images)
                Spacer(modifier = Modifier.padding(5.dp))
                Header("Featured Brand")
                Spacer(modifier = Modifier.padding(10.dp))
                FeaturedBrand(modifier = Modifier, brandState.brandList)
                Spacer(modifier = Modifier.padding(14.dp))
                Header("Best Seller")
                Spacer(modifier = Modifier.padding(10.dp))
                CategoryChairs(itemList)
                Spacer(modifier = Modifier.padding(24.dp))
                Header("Best Offers")
                Spacer(modifier = Modifier.padding(24.dp))
                CategoryBestOffers()
                Spacer(modifier = Modifier.padding(24.dp))
                CategoryMore()
                Spacer(modifier = Modifier.padding(24.dp))

            }

            item {
                FlowRow {
                    val itemSize = (LocalConfiguration.current.screenWidthDp.dp/2)

                    productState.productList.fastForEachIndexed { i, product -> ProductItem(
                        Modifier.width(itemSize),
                        product = product,
                        onProductSelect = {navController.navigate(Screen.ProductDetailScreen.route
                        +"/${Gson().toJson(user)}"
                        +"/${Gson().toJson(product)}")}
                    ) }
                }

            }
        }

    }

    if(bannerState.error.isNotBlank()||brandState.error.isNotBlank()||productState.error.isNotBlank())
    {
        ErrorScreen(retryAction = {navController.navigate(Screen.HomeScreen.route + "/$user")}, message = productState.error)
    }
    if(bannerState.isLoading||brandState.isLoading||productState.isLoading) {
        LoadingScreen()
    }


}
