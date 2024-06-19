package vn.dvn.portraitstores.presentation.view.product_detail_screen

import SnackImage
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.presentation.components.Divider
import vn.dvn.portraitstores.presentation.components.DividerTextComponent
import vn.dvn.portraitstores.presentation.components.ErrorScreen
import vn.dvn.portraitstores.presentation.components.LoadingScreen
import vn.dvn.portraitstores.presentation.components.ProductItem
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.Cart_Screen.QuantitySelector
import vn.dvn.portraitstores.presentation.view.Cart_Screen.component.CartTopBar
import vn.dvn.portraitstores.ui.theme.BlueCustomed
import vn.dvn.portraitstores.ui.theme.paledark
@OptIn(ExperimentalFoundationApi::class, ExperimentalLayoutApi::class,
    ExperimentalMaterialApi::class
)
@Composable
fun ProductDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    productDetailViewModel: ProductDetailViewModel = hiltViewModel()
)
{
    val product = productDetailViewModel.product.collectAsState().value
    val user = productDetailViewModel.user.collectAsState().value
    val quantity = productDetailViewModel.quantity.collectAsState().value
    val sheetState = productDetailViewModel.sheetState.collectAsState().value


    LaunchedEffect(Unit) {
        productDetailViewModel.getCarts()
    }
    productDetailViewModel.getSuggestedProducts()
    val productList = productDetailViewModel.suggestedProductListState.collectAsState().value


    val listState = rememberLazyListState()
    val firstVisibleItemScrollOffset by remember { derivedStateOf { listState.firstVisibleItemScrollOffset } }
    val appBarColor by remember {
        derivedStateOf {
            if (firstVisibleItemScrollOffset > 100) Color.White else Color.Transparent
        }
    }
    val contentColor by remember {
        derivedStateOf {
            if (firstVisibleItemScrollOffset > 200) Color.Black else Color.White
        }
    }

    Scaffold(modifier = Modifier.fillMaxWidth(),
            bottomBar = {
                ProductDetailBottomBar(onAddToCartClick = { productDetailViewModel.onShowBottomSheet() }, onBuyNowClick = { productDetailViewModel.onShowBottomSheet() })
            }
    ) {
                Box(modifier = Modifier.fillMaxSize())
                {

                    CartTopBar(
                        title = product.product!!.name,
                        icon = Icons.Default.ShoppingCart,
                        onBackClick = { navController.popBackStack() },
                        onClickIcon = {navController.navigate(Screen.CartScreen.route+"/${Gson().toJson(user.user)}")},
                        backgroundColor = appBarColor,
                        modifier = Modifier.align(Alignment.TopCenter)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .padding(it),
                        state = listState
                    ) {
                        stickyHeader {
                        }

                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Card(
                                    elevation = CardDefaults.elevatedCardElevation(5.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color.White
                                    ),
                                    shape = RectangleShape
                                ) {
//                                    Image(
//                                        painterResource(id = R.drawable.furniture_6),
//                                        contentDescription = "",
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .aspectRatio(1f / 1f)
//                                    )

                                    AsyncImage(model = ImageRequest.Builder(context = LocalContext.current).data(
                                        Constants.IMAGE_PATH + product.product.imagePath)
                                        .crossfade(true).build(),
                                        contentDescription = "",
                                        error = painterResource(id = R.drawable.ic_broken_image),
                                        placeholder = painterResource(id = R.drawable.loading_img),
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1f / 1f)
                                    )
                                }

                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp, vertical = 10.dp)
                                ) {
                                    Text(
                                        text = product.product!!.name,
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.headlineMedium
                                    )
                                    Text(
                                        text = "This is a best choice for family.",
                                        color = Color.DarkGray.copy(0.8f),
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(vertical = 5.dp)
                                    )
                                    Text(
                                        text = "$ ${product.product!!.price}",
                                        color = BlueCustomed,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(vertical = 15.dp),
                                        style = MaterialTheme.typography.headlineMedium
                                    )
                                    Text(text = "Single Sofa")

                                    Divider(
                                        color = Color.LightGray,
                                        modifier = Modifier.padding(vertical = 15.dp)
                                    )
                                    QuantitySelector(
                                        count = quantity,
                                        decreaseItemCount = {
                                            if(quantity > 1)
                                                productDetailViewModel.setQuantity(quantity-1)
                                        },
                                        increaseItemCount = {
                                            if(quantity !=  product.product!!.quantity.toInt())
                                                productDetailViewModel.setQuantity(quantity+1)
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    DividerTextComponent("Description")

                                    Text(
                                        text = product.product!!.description,
                                        modifier = Modifier.padding(vertical = 15.dp)
                                    )
                                    DividerTextComponent("Suggested item")
                                }
                            }
                        }
                        item {
                            FlowRow {
                                val itemSize = (LocalConfiguration.current.screenWidthDp.dp/2)

                                productList.productList.fastForEachIndexed { i, product -> ProductItem(
                                    Modifier.width(itemSize),
                                    product = product,
                                    onProductSelect = {navController.navigate(
                                        Screen.ProductDetailScreen.route
                                                +"/${Gson().toJson(user)}"
                                                +"/${Gson().toJson(product)}")}
                                ) }
                            }

                        }
                    }
                }
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 300.dp, max = 600.dp) // Adjust the height as needed
            ) {
                Column {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .background(Color.White)
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        val (divider, image, name, tag, priceSpacer, price, remove, quantity_layout) = createRefs()
                        createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
                SnackImage(
                    imageUrl = Constants.IMAGE_PATH + product.product!!.imagePath,
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top, margin = 16.dp)
                            bottom.linkTo(parent.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                        }
                )
                        androidx.compose.material.Text(
                            text = "itemDetail.name",
                            style = androidx.compose.material.MaterialTheme.typography.subtitle1,
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
                        androidx.compose.material.IconButton(
                            onClick = {  },
                            modifier = Modifier
                                .constrainAs(remove) {
                                    top.linkTo(parent.top)
                                    end.linkTo(parent.end)
                                }
                                .padding(top = 5.dp, end = 0.dp)
                        ) {

                        }
                        androidx.compose.material.Text(
//                    text = "${itemDetail.brand.name}",
                            text = "brand",
                            style = androidx.compose.material.MaterialTheme.typography.body1,
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
                        androidx.compose.material.Text(
//                    text = "${itemDetail.price}",
                            text = "1000.00",
                            style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                            color = paledark,
                            modifier = Modifier.constrainAs(price) {
                                linkTo(
                                    start = image.end,
                                    end = quantity_layout.start,
                                    startMargin = 16.dp,
                                    endMargin = 16.dp,
                                    bias = 0f
                                )
                            }
                        )
                        QuantitySelector(
                            count = quantity,
                            decreaseItemCount = {

                                if(quantity > 1)
                                    productDetailViewModel.setQuantity(quantity-1)
                            }  ,
                            increaseItemCount = {
                                if(quantity !=  product.product!!.quantity.toInt())
                                    productDetailViewModel.setQuantity(quantity+1)
                            }  ,
                            modifier = Modifier.constrainAs(quantity_layout) {
                                baseline.linkTo(price.baseline)
                                end.linkTo(parent.end)
                            }
                        )

                    }
                    Spacer(modifier = Modifier.height(100.dp))
                    vn.dvn.portraitstores.presentation.components.Divider(color = Color.LightGray)
                    ProductDetailBottomBar(onAddToCartClick = { productDetailViewModel.updateCart(productId = product.product!!.id.toString()) },
                        onBuyNowClick = { productDetailViewModel.onHideBottomSheet() },
                        content_1 = "Cancel", content_2 = "Add to cart")
                }


            }
        },
        sheetBackgroundColor = Color.White,
        sheetElevation = 8.dp,
    ) {}

    if(productDetailViewModel.addToCartState.collectAsState().value.error.isNotBlank())
    {
        ErrorScreen(retryAction = {navController.navigate(Screen.HomeScreen.route + "/$user")}, message = productDetailViewModel.addToCartState.collectAsState().value.error)
    }
    if(productDetailViewModel.addToCartState.collectAsState().value.isLoading) {
        LoadingScreen()
    }
//    if(productDetailViewModel.addToCartState.collectAsState().value.isSuccessful)
//    {
//        Toast.makeText(LocalContext.current, "This is a toast message", Toast.LENGTH_SHORT).show()
//        LaunchedEffect(Unit) {
//            delay(2000)
//        }
//    }

}

@Composable
fun ProductDetailBottomBar(
    modifier: Modifier = Modifier,
    onAddToCartClick: () -> Unit,
    onBuyNowClick: () -> Unit,
    content_1: String = "Add to cart",
    content_2: String = "Buy Now"
)
{
    Card(modifier = modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color.LightGray),
        elevation = CardDefaults.elevatedCardElevation(10.dp)) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Card(modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(5.dp,0.dp,0.dp,5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(1.dp, Color.LightGray),
                onClick = onAddToCartClick
            ) {
                Text(text = "Add to cart",
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black.copy(0.7f)
                )
            }
            Card(modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(0.dp,5.dp,5.dp,0.dp),
                colors = CardDefaults.cardColors(
                    containerColor = BlueCustomed.copy(0.8f),
                    contentColor = Color.White
                ),
                border = BorderStroke(1.dp, Color.LightGray),
                onClick = onBuyNowClick
            ) {
                Text(text = "Buy Now",
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
@Preview
fun DividerTextReview()
{
    DividerTextComponent("Suggested item")
}