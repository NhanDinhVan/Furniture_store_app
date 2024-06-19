package vn.dvn.portraitstores.presentation.view.Check_out_Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.presentation.view.Cart_Screen.component.CheckOutButton
import vn.dvn.portraitstores.presentation.view.Cart_Screen.component.PriceDetail
import vn.dvn.portraitstores.presentation.view.RegisterScreen.CustomTextfield


@Composable
fun PaymentScreen(modifier : Modifier = Modifier,
                   navController: NavController,
                   checkoutViewModel: CheckoutViewModel = hiltViewModel()
)
{
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
                Spacer(modifier = Modifier.height(35.dp))

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
                            Text(
                                text = method,
                                style = MaterialTheme.typography.labelLarge,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                    }
            }
                Spacer(modifier = Modifier.height(100.dp))
                CheckOutButton(modifier = Modifier
                    .padding(top = 20.dp, bottom = 5.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                    icon = Icons.Default.ExitToApp, text = "Checkout",
                    { checkoutViewModel.checkout() })
            }

        }

    }}