package com.arvind.furnitureshop.view

import android.graphics.drawable.shapes.Shape
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import kotlinx.coroutines.delay
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.ui.theme.LightGreen
import vn.dvn.portraitstores.ui.theme.lightBlue
import vn.dvn.portraitstores.ui.theme.paledark
import vn.dvn.portraitstores.ui.theme.texttitlewhite


//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
////@Preview(showSystemUi = true, showBackground = true)
//fun FirstSplashScreen(navController: NavController)
//{
//    val configuration = LocalConfiguration.current
//
//    val screenHeight = configuration.screenHeightDp.dp
//    val screenWidth = configuration.screenWidthDp.dp
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .background(Color.White)) {
//
//        Image(painter = painterResource(id = R.drawable.splash), contentDescription = "",
//            modifier = Modifier.fillMaxWidth(),
//            contentScale = ContentScale.Crop)
//
//        Text(
//            text = "Let's start enjoy my service",
//            color = paledark,
//            modifier = Modifier
//                .padding(8.dp, 5.dp)
//                .fillMaxWidth(),
//            fontWeight = FontWeight.ExtraBold,
//            textAlign = TextAlign.Center,
//            fontSize = 20.sp,
//        )
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Text(
//            text = "In a best traditions, constructed of hardwood\n" +
//                    "with padding of high-resilient foam.Created \n" +
//                    "by awarded winning duo of Manchesti.\n" ,
//            fontSize = 10.sp,
//            color = paledark.copy(0.7f),
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.fillMaxWidth()
//        )
//
//    }
//    Button(modifier = Modifier
//        .offset(screenWidth/2,screenHeight/5*4)
//        , colors = ButtonDefaults.buttonColors(
//            containerColor = paledark.copy(0.3f)
//        ),
//        onClick = {navController.navigate(Screen.LoginScreen.route)}
//        )
//    {
//        Text(
//            text = "Getting started. ",
//            color = paledark,
//            modifier = Modifier.padding(10.dp,7.dp),
//            fontWeight = FontWeight.Bold,
//            textAlign = TextAlign.Center,
//            fontSize = 16.sp,
//        )
//    }
//
//}

@Composable
fun SVGImageViewer(drawableResId: Int) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(drawableResId)
            .size(Size.ORIGINAL) // Hiển thị kích thước gốc của SVG
            .build()
    )

    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}


@Composable
fun SplashScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.unsplash_2),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )



        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(16.dp,16.dp,16.dp,50.dp)
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 40.dp))
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White.copy(0.5f)
                )
                .padding(40.dp)//,40.dp,40.dp,40.dp
        ) {



            Text(
                text = "Welcome.",
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                letterSpacing = (-1).sp,

                )

            Text(
                text = "Buy products from ice cream & frozen desserts at best prices and get them home ...",
                style = MaterialTheme.typography.h2,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = (-0.1).sp
            )

            Button(
                colors = ButtonDefaults.buttonColors(containerColor =  LightGreen),
                onClick = {
                    navController.popBackStack(Screen.SplashScreen.route, inclusive = true)
                    navController.navigate(Screen.LoginScreen.route)
                },
                modifier = Modifier
                    .padding(6.dp,50.dp,6.dp,6.dp)
                    .fillMaxWidth()
                    .height(65.dp)
                    .clip(RoundedCornerShape(15.dp))

            ) {

                Text(text = "Get Started",
                    style = MaterialTheme.typography.subtitle1,
                    color = paledark)

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "",
                    tint = paledark
                )

            }

        }


    }


}


@Preview
@Composable
fun SplashPreview() {


}
