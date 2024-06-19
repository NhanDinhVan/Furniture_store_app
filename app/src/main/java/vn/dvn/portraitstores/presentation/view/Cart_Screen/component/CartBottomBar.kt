package vn.dvn.portraitstores.presentation.view.Cart_Screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.gson.Gson
import vn.dvn.portraitstores.domain.model.Carts
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.presentation.components.Divider
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.ui.theme.BlueCustomed
import java.util.Locale

@Composable
//@Preview(showBackground = true, showSystemUi = true)
fun CartBottomBar(cartList: List<Carts>,navController: NavController, user: User ?= null)
{

    Card(modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)) {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                PriceDetail(cartList)
                CheckOutButton(modifier =Modifier.padding(top = 10.dp , bottom = 5.dp),
                    icon = Icons.Default.ExitToApp, text = "Order Now", {navController.navigate(Screen.CheckoutScreen.route + "/${Gson().toJson(user)}")})
            }
        }
    }
}

@Composable
fun PriceDetail(cartList: List<Carts>, modifier : Modifier = Modifier, cardColor: CardColors = CardDefaults.cardColors(containerColor = Color.Transparent))
{
    var totalPrice = 0.0
    for (cart in cartList) {
        totalPrice += cart.product.price * cart.quantity
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        shape = RoundedCornerShape(5.dp),
        colors = cardColor,
        elevation = CardDefaults.elevatedCardElevation(5.dp),
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding( 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Sub Total: ")
                Text(text = "$  ${String.format(Locale.US, "%.2f", totalPrice)}")
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Discount: ")
                Text(text = "0%")
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Delivery: ")
                Text(text = "$0")
            }
            Divider(color = Color.LightGray.copy(0.9f))
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 5.dp, end = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = "Total Payable: ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(text = "$ ${String.format(Locale.US, "%.2f", totalPrice)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun CheckOutButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
)
{
    Card(modifier = modifier.fillMaxWidth(0.9f)
        , colors = CardDefaults.cardColors(
            containerColor = BlueCustomed,
            contentColor = Color.White
        )
        , shape = RoundedCornerShape(2.dp),
        onClick = onClick
        ) {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center){
            Icon(imageVector = icon, contentDescription = "",
                modifier = Modifier.padding())
            Text(text = text,
                modifier = Modifier
                    .padding(10.dp),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
