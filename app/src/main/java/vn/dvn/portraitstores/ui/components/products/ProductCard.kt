package vn.dvn.portraitstores.ui.components.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.ui.theme.PortraitStoresTheme
import vn.dvn.portraitstores.ui.theme.PopinsFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard()
{
//   Box(modifier = Modifier.border(0.dp, Color.Gray)
//        ) {
//        val (image, text) = createRefs()
//       Card(modifier = Modifier
//           .width(190.dp)
//           .height(255.dp)
//           ,shape = RoundedCornerShape(10.dp)
//           ,elevation = CardDefaults.cardElevation(10.dp)
//           ,colors = CardDefaults.cardColors(containerColor = Color.White) )
//       {
//           Column(modifier = Modifier.fillMaxSize()) {
//               Image(painter = painterResource(id = R.drawable.image1), contentDescription ="")
//
//           }
//       }
//   }


        Card(modifier = Modifier
            .width(190.dp)
            .height(255.dp)
            .padding(5.dp)
            , elevation = CardDefaults.cardElevation(10.dp)
            , colors = CardDefaults.cardColors(containerColor = Color.White)
            , onClick = {}
        ) {
            Column (modifier = Modifier.padding(15.dp,15.dp,15.dp,15.dp)){

                Image(painter = painterResource(id = R.drawable.unsplash)
                    , contentDescription =""
                    ,modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .aspectRatio(1f / 1f))



                Text(text = "Title-Title"
                    , style = MaterialTheme.typography.displayLarge
                    ,modifier = Modifier.padding(top = 0.dp)
                , maxLines = 2

                )



                Row (modifier = Modifier.fillMaxWidth()
                    ,horizontalArrangement = Arrangement.SpaceBetween){
                  Column(modifier = Modifier.weight(7f)) {
                      Text(text = "220.000 vnd"
                          , style = MaterialTheme.typography.displayMedium
                          ,modifier = Modifier.padding(top = 8.dp)
                          , color = Color.Yellow)

                      Text(text = "Đã bán 165."
                          , style = MaterialTheme.typography.displayMedium
                          ,modifier = Modifier
                          , color = Color.Gray)
                  }

                    Column(modifier = Modifier.weight(3f)) {
                       Row (modifier = Modifier
                           .padding(top = 8.dp)
                           .fillMaxWidth()
                           , horizontalArrangement = Arrangement.SpaceAround){
                            IconButton(modifier = Modifier.size(18.dp)
                                    ,onClick = { /*TODO*/ }) {
                                Icon( Icons.Default.FavoriteBorder, contentDescription = "")
                            }
                           IconButton(modifier = Modifier.size(18.dp)
                                   ,onClick = { /*TODO*/ }) {
                               Icon( Icons.Default.ShoppingCart, contentDescription = "")
                           }
                       }
                    }


                }
            }
        }
    }



@Composable
@Preview(showSystemUi = true, showBackground = true)
fun cardPreview()
{
    PortraitStoresTheme {
        Column (modifier = Modifier
            .fillMaxSize()
            .border(0.dp, Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            ProductCard()
        }
    }

}
