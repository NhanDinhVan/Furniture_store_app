package vn.dvn.portraitstores.presentation.view.Check_out_Screen

import CartItem
import CartList
import SnackImage
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.model.Product
import vn.dvn.portraitstores.presentation.components.Divider
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.Cart_Screen.CartViewModel
import vn.dvn.portraitstores.presentation.view.Cart_Screen.QuantitySelector
import vn.dvn.portraitstores.presentation.view.Cart_Screen.component.CheckOutButton
import vn.dvn.portraitstores.presentation.view.Cart_Screen.component.PriceDetail
import vn.dvn.portraitstores.presentation.view.RegisterScreen.CustomTextfield
import vn.dvn.portraitstores.presentation.view.product_detail_screen.ProductDetailBottomBar
import vn.dvn.portraitstores.ui.theme.paledark

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CheckoutScreen(modifier : Modifier = Modifier,
                   navController: NavController,
                   checkoutViewModel: CheckoutViewModel = hiltViewModel())
{
    LaunchedEffect(Unit) {
        checkoutViewModel.getCarts()
    }
    val cartState = checkoutViewModel.cartState.collectAsState().value
    val cartList = cartState.cartList

        Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
            Image(
                painter = painterResource(id = R.drawable.checkout_background),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp, 60.dp, 10.dp, 10.dp)) {

                PriceDetail(cartList, cardColor = CardDefaults.cardColors(containerColor = Color.White),
                    modifier = Modifier.clip(RoundedCornerShape(20.dp)))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(5.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.elevatedCardElevation(5.dp),
            ) {
                LazyColumn(modifier = Modifier.background(Color.LightGray))
                {
                                    items(cartList)
                                    { cart ->
                                        checkoutItem(
                                            cartItem = cart,
                                        )
                                    }
                    item {

                    }

                }
            }
                CheckOutButton(modifier = Modifier
                    .padding(top = 20.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                    icon = Icons.Default.ExitToApp, text = "Continue to checkout",
                    { checkoutViewModel.onShowBottomSheet()})
        }

        }



    ModalBottomSheetLayout(
        sheetState = checkoutViewModel.sheetState.collectAsState().value,
        sheetContent = {
            LaunchedEffect(Unit) {
                checkoutViewModel.getCarts()
            }
            val cartState = checkoutViewModel.cartState.collectAsState().value
            val cartList = cartState.cartList
            val message = checkoutViewModel.message.collectAsState().value
            val address = checkoutViewModel.address.collectAsState().value
            val selectedPaymentMethod = checkoutViewModel._selectedPaymentMethod.collectAsState().value
            val paymentMethod = checkoutViewModel.paymentMethods

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.checkout_background),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, 60.dp, 10.dp, 10.dp)
                ) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(450.dp)
                            .padding(10.dp),
                        shape = RoundedCornerShape(5.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.elevatedCardElevation(5.dp),
                    ) {
                        CustomTextfield(
                            text = address,
                            onChangeText = { checkoutViewModel.onChangeAddress(it) },
                            content = "Delivery Address"
                        )
                        Spacer(modifier = Modifier.height(5.dp))

                        CustomTextfield(
                            text = message,
                            onChangeText = { checkoutViewModel.onChangeMessage(it) },
                            content = "Message"
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        LazyColumn(modifier = Modifier.background(Color.LightGray))
                        {
                            items(paymentMethod)
                            { method ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .padding(vertical = 8.dp)
                                        .fillMaxWidth()
                                        .background(Color.Transparent)
                                ) {
                                    RadioButton(modifier = Modifier,
                                        selected = selectedPaymentMethod == method,
                                        onClick = { checkoutViewModel.onChangePaymentMethod(method) }
                                    )
                                    androidx.compose.material3.Text(
                                        text = method,
                                        style = androidx.compose.material3.MaterialTheme.typography.displaySmall,
                                        modifier = Modifier.padding(start = 8.dp)
                                    )
                                }

                            }
                        }

                        CheckOutButton(modifier = Modifier
                            .padding(top = 20.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth(),
                            icon = Icons.Default.ExitToApp, text = "Checkout",
                            { checkoutViewModel.checkout()})
                    }

                }

            }
        },
        sheetBackgroundColor = Color.White,
        sheetElevation = 8.dp,
    ) {}
}

@Composable
fun checkoutItem(
    cartItem: Carts,
    modifier: Modifier = Modifier
)
{
    val itemDetail = cartItem.product
    Column(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(horizontal = 20.dp)
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
        }
        Divider(color = Color.LightGray)
    }
}

