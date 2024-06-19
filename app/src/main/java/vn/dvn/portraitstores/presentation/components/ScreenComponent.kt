package vn.dvn.portraitstores.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.ui.theme.LightGreen

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
   Column(modifier = Modifier.fillMaxSize()
       .background(Color.Cyan.copy(0.5f)),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center) {
//       Image(
//           modifier = modifier.size(200.dp),
//                   painter = painterResource(R.drawable.loading_img),
//           contentDescription = "Loading..."
//       )

       LoadingAnimation()
   }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier, message:String) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White.copy(0.6f))
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .offset(0.dp, 0.dp),
        ) {
            Column(modifier = modifier
                .fillMaxSize()
            , verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
                )
                Text(text = message, modifier = Modifier.padding(10.dp),
                    style = TextStyle(
                        fontSize = 10.sp
                    )
                )
                Button(onClick = retryAction) {
                    Text("Retry.")
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun ErrorReview()
//{
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .background(Color.Red)){
//
//
//        LoadingScreen()
//    }
//}