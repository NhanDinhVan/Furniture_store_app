import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.presentation.components.Divider
import vn.dvn.portraitstores.presentation.view.Cart_Screen.component.CartBottomBar
import vn.dvn.portraitstores.presentation.view.Cart_Screen.component.CartTopBar
import vn.dvn.portraitstores.presentation.components.ErrorScreen
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.Cart_Screen.CartViewModel
import vn.dvn.portraitstores.presentation.view.Cart_Screen.QuantitySelector
import vn.dvn.portraitstores.ui.theme.paledark


@Composable
fun CartScreen(
    cartViewModel: CartViewModel = hiltViewModel(),
    navController: NavController
)
{
    LaunchedEffect(Unit) {
        cartViewModel.getCarts()
    }
    val cartState = cartViewModel.cartState.collectAsState().value

    Scaffold (
        topBar ={
            CartTopBar(title = "My Cart", icon = Icons.Default.Settings, onBackClick = { navController.popBackStack() } , onClickIcon = {navController.navigate(Screen.Profile.route+"/${Gson().toJson(cartViewModel.user.value.user)}")}, Color.Transparent)
        },
        bottomBar = {
            CartBottomBar(cartState.cartList,navController, user = cartViewModel.user.collectAsState().value.user)
        }

    ){
        
            CartList(Modifier.padding(it), cartState.cartList, navController, cartViewModel)
            
    }
    if(cartState.error.isNotBlank())
    {
        ErrorScreen(retryAction = {}, message = cartState.error)
    }
//    if(cartState.isLoading) {
//        LoadingScreen()
//    }
}

@Composable
fun CartList(modifier: Modifier = Modifier, cartList : List<Carts>, navController: NavController, cartViewModel: CartViewModel)
{
    if(!cartList.isEmpty()) {
        LazyColumn(modifier.background(Color.LightGray))
        {
            items(cartList)
            { cart ->
                CartItem(
                    cartItem = cart,
                    remove = { cartViewModel.updateCart(cart, 0) },
                    increaseItemCount = { cartViewModel.updateCart(cart, cart.quantity + 1) },
                    decreaseItemCount = { cartViewModel.updateCart(cart, cart.quantity - 1) },
                    onClick = {
                        navController.navigate(
                            Screen.ProductDetailScreen.route
                                    + "/${Gson().toJson(cartViewModel.user.value.user)}"
                                    + "/${Gson().toJson(cart.product)}"
                        )
                    }
                )
            }

        }
    }else
    {
       Column(modifier = Modifier.fillMaxWidth(),
           horizontalAlignment = Alignment.CenterHorizontally,) {
           Text(text = "No item in your cart.",
               modifier = Modifier.padding(top = 25.dp),
               fontWeight = FontWeight.Medium,
               fontSize = 24.sp,
               color = Color.Black.copy(0.7f)
           )
       }
    }
}

@Composable
fun CartItem(
    cartItem: Carts,
    remove: () -> Unit,
    increaseItemCount: () -> Unit,
    decreaseItemCount: () -> Unit,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
)
{
    val itemDetail = cartItem.product
    Column(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .clickable { onClick }
                .background(Color.White)
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            val (divider, image, name, tag, priceSpacer, price, remove, quantity) = createRefs()
            createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
            SnackImage(
                imageUrl = Constants.IMAGE_PATH + itemDetail.imagePath,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 16.dp)
                        bottom.linkTo(parent.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                    }
            )
            Text(
                text = itemDetail.name,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black,
                modifier = Modifier.constrainAs(name) {
                    linkTo(
                        start = image.end,
                        startMargin = 16.dp,
                        end = remove.start,
                        endMargin = 16.dp,
                        bias = 0f
                    )
                }
            )
            IconButton(
                onClick = { remove() },
                modifier = Modifier
                    .constrainAs(remove) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 5.dp, end = 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    tint = Color.Black,
                    contentDescription = stringResource(R.string.label_remove)
                )
            }
            Text(
                text = "${itemDetail.brand.name}",
                style = MaterialTheme.typography.body1,
                color = Color.Gray,
                modifier = Modifier.constrainAs(tag) {
                    linkTo(
                        start = image.end,
                        startMargin = 16.dp,
                        end = parent.end,
                        endMargin = 16.dp,
                        bias = 0f
                    )
                }
            )
            Spacer(
                Modifier
                    .height(8.dp)
                    .constrainAs(priceSpacer) {
                        linkTo(top = tag.bottom, bottom = price.top)
                    }
            )
            Text(
                text = "${itemDetail.price}",
                style = MaterialTheme.typography.subtitle1,
                color = paledark,
                modifier = Modifier.constrainAs(price) {
                    linkTo(
                        start = image.end,
                        end = quantity.start,
                        startMargin = 16.dp,
                        endMargin = 16.dp,
                        bias = 0f
                    )
                }
            )
            QuantitySelector(
                count = cartItem.quantity,
                decreaseItemCount = { decreaseItemCount() }  ,
                increaseItemCount = { increaseItemCount() }  ,
                modifier = Modifier.constrainAs(quantity) {
                    baseline.linkTo(price.baseline)
                    end.linkTo(parent.end)
                }
            )

        }
        Divider(color = Color.LightGray)
    }
}


@Composable
fun SnackImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp
) {
    Surface(
        color = Color.LightGray,
        elevation = elevation,
        shape = RoundedCornerShape(7.dp),
        modifier = modifier
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = contentDescription,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    }
}
