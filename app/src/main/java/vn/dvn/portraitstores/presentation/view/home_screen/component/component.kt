package com.arvind.furnitureshop.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import retrofit2.http.Header
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.ui.theme.Purple500
import vn.dvn.portraitstores.ui.theme.black
import vn.dvn.portraitstores.ui.theme.cottonBall
import vn.dvn.portraitstores.ui.theme.lightBlue
import vn.dvn.portraitstores.ui.theme.paledark
import vn.dvn.portraitstores.ui.theme.texttitlewhite
import vn.dvn.portraitstores.ui.theme.white

@Composable
fun Header(title: String) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        paledark,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(title)
                }

            },
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier,
            fontSize = 18.sp

        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        paledark
                    )
                ) {
                    append("more...")
                }
            },
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Light,
            modifier = Modifier,
            fontSize = 12.sp

        )
    }
}

@Composable
fun CategoryChairs(itemList: List<String>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        items(itemList.size) { item ->
            Box(
                modifier = Modifier.border(
                    color = if (item == 0) paledark else Color.Transparent,
                    width = 2.dp,
                    shape = RoundedCornerShape(24.dp)
                )
            ) {
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 8.dp,
                        bottom = 8.dp
                    ),
                    text = itemList[item],
                    color = if (item == 0) paledark else Color.LightGray
                )

                Spacer(modifier = Modifier.padding(8.dp))
            }
        }
    }

    Spacer(modifier = Modifier.padding(10.dp))

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ChairsItem(
                imagePainter = painterResource(id = R.drawable.furniture_1),
                title = "Matteo\n" +
                        "Armchair",
                price = "$240"
            )
        }

        item {
            ChairsItem(
                imagePainter = painterResource(id = R.drawable.furniture_2),
                title = "Araceli\n" +
                        "Armchair",
                price = "$240"
            )
        }

        item {
            ChairsItem(
                imagePainter = painterResource(id = R.drawable.furniture_3),
                title = "Primose\n" +
                        "Armchair",
                price = "$240"
            )
        }

    }
}


@Composable
fun ChairsItem(
    imagePainter: Painter,
    title: String = "",
    price: String = ""
) {
    Card(
        modifier = Modifier
            .width(170.dp)
            .clickable {
            },
        elevation = CardDefaults.elevatedCardElevation(20.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = imagePainter, contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )
            Text(text = title, color = texttitlewhite)
            Text(text = price, fontWeight = FontWeight.Bold)
        }
    }
}
@Composable
fun HeaderText(title: String)
{
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    paledark,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(title)
            }
        },
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier,
        fontSize = 24.sp
    )
}
@Composable
fun CategoryBestOffers() {
    Card(modifier = Modifier
        .fillMaxWidth()
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {


            CategoryBestOffersItems(
                imagePainter = painterResource(id = R.drawable.sofa_1),
                title = "Ingrit MV",
                subtitle = "Sofa",
                price = "$2689",
                backgroundColor = Color.White
            )

            CategoryBestOffersItems(
                imagePainter = painterResource(id = R.drawable.sofa_2),
                title = "Montesque",
                subtitle = "Bed",
                price = "$1240",
                backgroundColor = Color.White
            )

            CategoryBestOffersItems(
                imagePainter = painterResource(id = R.drawable.sofa_3),
                title = "Nolin Sofa",
                subtitle = "Sofa",
                price = "$240",
                backgroundColor = Color.White
            )
        }
    }

}


@Composable
fun CategoryBestOffersItems(
    imagePainter: Painter,
    title: String = "",
    subtitle: String = "",
    price: String = "",
    backgroundColor: Color = Color.White,
) {
    Card (modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
    , elevation = CardDefaults.cardElevation(10.dp)
        ){
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(backgroundColor)
                .padding(16.dp)

        ) {
            Box(
                modifier = Modifier
                    .height(90.dp)
                    .fillMaxWidth(0.2f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .padding(8.dp),
                    painter = imagePainter,
                    contentDescription = "",
                )
            }

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .offset((-60).dp)
                    .wrapContentHeight()
                    .background(backgroundColor),
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = black,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = Color.Gray,
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxHeight(0.75f)
                    .wrapContentHeight()
                    .background(backgroundColor),
            ) {
                Text(
                    text = price,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = black,
                    textAlign = TextAlign.Right
                )

            }
        }
    }

}
//
//@Composable
//@Preview(showSystemUi = true, showBackground = true)
//fun reviewBestOfferItem()
//{
//    val navController = rememberNavController()
//    BottomBar(navController = navController )
//}

@Composable
fun CategoryMore() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "New",
            color = paledark,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Soon",
            color = texttitlewhite,
            fontSize = 14.sp,
            modifier = Modifier.offset((-60).dp)
        )

        Button(
            onClick = {

            },
            modifier = Modifier
                .height(70.dp)
                .width(110.dp)
                .offset((20).dp),
            elevation = null,
            shape = RoundedCornerShape(
                topStartPercent = 50,
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = paledark
            ),
        ) {
            Text(
                text = "more",
                color = white,
                fontSize = 12.sp
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "",
                modifier = Modifier.padding(start = 5.dp),
                tint = white
            )

        }
    }
}

@Composable
fun BottomBar(navController: NavController, user: User?) {
    val screens = listOf(
        Screen.HomeScreen,
        Screen.Report,
        Screen.Profile
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Card(
        modifier = Modifier.fillMaxWidth()
        ,
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        shape = RoundedCornerShape(0.dp,0.dp,0.dp,0.dp)

    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
            ,
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController,
                    user = user
                )
            }
        }
    }

}

@Composable
fun AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavController,
    user: User?
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route+"/{user}" } == true

//    val background =
//        if (selected) Color.White.copy(alpha = 0.6f) else Color.Transparent

//    val contentColor =
//        if (selected) Color.White else Color.Black

    Box(
        modifier = Modifier
            .padding(vertical = 7.dp)
            .height(40.dp)
            .clip(CircleShape)
//            .background(background)
            .clickable(onClick = {
                navController.navigate(screen.route+"/${Gson().toJson(user)}") {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            })
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(id = if (selected) screen.iconFocused else screen.icon),
                contentDescription = "icon",
//                tint = contentColor
            )
            AnimatedVisibility(visible = selected) {
                Text(
                    text = screen.title,
//                    color = contentColor
                )
            }
        }
    }
}
