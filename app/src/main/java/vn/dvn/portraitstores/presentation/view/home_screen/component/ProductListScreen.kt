package com.arvind.furnitureshop.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arvind.furnitureshop.component.HomeTopBar
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.ui.theme.LightGreen
import vn.dvn.portraitstores.ui.theme.black
import vn.dvn.portraitstores.ui.theme.platinum
import vn.dvn.portraitstores.ui.theme.texttitlewhite
import vn.dvn.portraitstores.ui.theme.white

@Preview(showBackground = true)
@Composable
fun ProductListScreen() {
    Box(Modifier.verticalScroll(rememberScrollState())) {

        Column() {
            HomeTopBar(
                title = "Armchairs",
                onBackClick = {

                },
            )
            Column() {
                ContentList()

            }
        }

    }
}

@Composable
fun ContentList() {
    Column() {
        SortFilter()
        Spacer(modifier = Modifier.padding(24.dp))
        Products()
    }
}

@Composable
fun SortFilter() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { },
            border = BorderStroke(1.dp, platinum),
            colors = ButtonDefaults.buttonColors(containerColor = white),
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = "Sort",
                color = black
            )


        }

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(containerColor = white),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(8.dp),
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "",
                tint = black,
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(
                text = "Filter",
                color = black
            )


        }
    }
}


@Composable
fun Products() {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            LeftSide()
            RightSide()
        }
    }

}


@Composable
fun LeftSide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        LeftItem(
            imagePainter = painterResource(id = R.drawable.furniture_5),
            title = "Primorse Accent",
            price = "$761"
        )
        LeftItem(
            imagePainter = painterResource(id = R.drawable.furniture_6),
            title = "Crandall 21",
            price = "$761"
        )
    }
}

@Composable
fun LeftItem(
    imagePainter: Painter,
    title: String = "",
    price: String = ""
) {

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clickable {
            },
        elevation = CardDefaults.elevatedCardElevation(20.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGreen
        ),

    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp)
                .background(LightGreen),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = title, color = texttitlewhite)
            Text(text = price, fontWeight = FontWeight.Bold)
        }
    }

    Box(
        modifier = Modifier
            .offset
                (25.dp, (-180).dp)
            .height(120.dp)
    ) {
        Image(
            contentScale = ContentScale.Fit,
            painter = imagePainter,
            contentDescription = "",
            modifier = Modifier.aspectRatio(1f)
        )
    }


}

@Composable
fun RightSide() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, start = 16.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        RightItem(
            imagePainter = painterResource(id = R.drawable.furniture_1),
            title = "Araceli Armchair",
            price = "$240"
        )
        RightItem(
            imagePainter = painterResource(id = R.drawable.furniture_2),
            title = "Nolin Armchair",
            price = "$332.0"
        )
        RightItem(
            imagePainter = painterResource(id = R.drawable.furniture_3),
            title = "Donham Armchair",
            price = "$555.0"
        )
    }
}

@Composable
fun RightItem(
    imagePainter: Painter,
    title: String = "",
    price: String = ""
) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clickable {
            },
        elevation = CardDefaults.elevatedCardElevation(10.dp),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = title, color = texttitlewhite)
            Text(text = price, fontWeight = FontWeight.Bold)
        }
    }

    Box(
        modifier = Modifier
            .offset
                (25.dp, (-180).dp)
            .height(120.dp)
    ) {
        Image(
            contentScale = ContentScale.Fit,
            painter = imagePainter,
            contentDescription = "",
            modifier = Modifier.aspectRatio(1f)
        )
    }
}
//
//@Composable
//fun Products() {
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(2), // Số lượng cột
//        contentPadding = PaddingValues(16.dp),
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        horizontalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        items(10)
//        {
//            RightItem(
//                imagePainter = painterResource(id = R.drawable.furniture_1),
//                title = "Araceli Armchair",
//                price = "$240"
//            )
//        }
//    }
//
//
//
//}