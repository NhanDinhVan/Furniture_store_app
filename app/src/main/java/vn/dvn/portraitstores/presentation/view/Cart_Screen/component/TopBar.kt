package vn.dvn.portraitstores.presentation.view.Cart_Screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import vn.dvn.portraitstores.ui.theme.black
import vn.dvn.portraitstores.ui.theme.paledark

@Composable
fun CartTopBar(title: String,
                              icon: ImageVector,
                              onBackClick: () -> Unit,
                              onClickIcon: () -> Unit,
               backgroundColor: Color,
               modifier: Modifier = Modifier
) {
//    Card(modifier =Modifier.fillMaxWidth().background(backgroundColor),
//        shape = RoundedCornerShape(0.dp, 0.dp, 15.dp, 15.dp),
//        backgroundColor =backgroundColor
//        elevation = CardDefaults.elevatedCardElevation(10.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = backgroundColor)
//    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .zIndex(2f)
             ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackClick() },
                modifier = Modifier.padding(16.dp)) {
                Icon(
                    modifier = Modifier.size(32.dp, 32.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = black
                )
            }
            Text(
                text = title,
                color = paledark,
                modifier = Modifier,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )

            IconButton(onClick = {onClickIcon()},
                modifier = Modifier.padding(16.dp)) {
                Icon(
                    imageVector = icon,
                    contentDescription = ""
                )

            }

        }
//    }
}



@Preview
@Composable
fun TopBarRev()
{
    CartTopBar(title = "My cart",
        Icons.Default.ShoppingCart,
        {},
        {},
        Color.White
    )
}