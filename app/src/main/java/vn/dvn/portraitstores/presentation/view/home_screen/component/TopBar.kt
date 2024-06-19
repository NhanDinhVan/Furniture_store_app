package com.arvind.furnitureshop.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.dvn.portraitstores.ui.theme.lightBlue
import vn.dvn.portraitstores.ui.theme.paledark

@Composable
fun HomeTopBar(title: String, onBackClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp))
            ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.padding(start = 10.dp)) {
            Text(
                text = "Welcome",
                color = Color.LightGray,
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
            )
            Text(
                text = title,
                color = paledark,
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
            )
        }

            IconButton(
                modifier = Modifier.padding(end = 16.dp),
                onClick = { }) {
                Icon(modifier = Modifier.size(25.dp),
                    imageVector = Icons.Outlined.Person,
                    contentDescription = ""
                )

            }


    }

}
@Preview
@Composable
fun TopBarReview()
{
    HomeTopBar(title = "Dinh Van Nhan") {
        
    }
}