package vn.dvn.portraitstores.presentation.components

import android.widget.RatingBar
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
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
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.domain.model.Product
import vn.dvn.portraitstores.ui.theme.PortraitStoresTheme

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ProductCard(product:Product,
//                onClick:()-> Unit)
//{
//        Card(modifier = Modifier
//            .width(190.dp)
//            .height(255.dp)
//            .padding(5.dp)
//            , elevation = CardDefaults.cardElevation(10.dp)
//            , colors = CardDefaults.cardColors(containerColor = Color.White)
//            , onClick = onClick
//        ) {
//            Column (modifier = Modifier.padding(15.dp,15.dp,15.dp,15.dp)){
//
////                Image(painter = painterResource(id = R.drawable.unsplash)
////                    , contentDescription =""
////                    ,modifier = Modifier
////                        .clip(RoundedCornerShape(10.dp))
////                        .aspectRatio(1f / 1f))
//
//                AsyncImage(model = ImageRequest.Builder(context = LocalContext.current).data(product.imagePath)
//                    .crossfade(true).build(),
//
//                    contentDescription = "",
//                    error = painterResource(id = R.drawable.ic_broken_image),
//                    placeholder = painterResource(id = R.drawable.loading_img),
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(10.dp))
//                        .aspectRatio(1f / 1f)
//                )
//
//
//                Text(text = product.name
//                    , style = MaterialTheme.typography.displayLarge
//                    ,modifier = Modifier.padding(top = 0.dp)
//                , maxLines = 2
//
//                )
//
//
//                Row (modifier = Modifier.fillMaxWidth()
//                    ,horizontalArrangement = Arrangement.SpaceBetween){
//                  Column(modifier = Modifier.weight(7f)) {
//                      Text(text = "${product.price}"
//                          , style = MaterialTheme.typography.displayLarge
//                          ,modifier = Modifier.padding(top = 8.dp)
//                          , color = Color.Yellow)
//
//                      Text(text = "${product.sold}"
//                          , style = MaterialTheme.typography.displayMedium
//                          ,modifier = Modifier
//                          , color = Color.Gray)
//                  }
//
//                    Column(modifier = Modifier.weight(3f)) {
//                       Row (modifier = Modifier
//                           .padding(top = 8.dp)
//                           .fillMaxWidth()
//                           , horizontalArrangement = Arrangement.SpaceAround){
//                            IconButton(modifier = Modifier.size(18.dp)
//                                    ,onClick = { /*TODO*/ }) {
//                                Icon( Icons.Default.FavoriteBorder, contentDescription = "")
//                            }
//                           IconButton(modifier = Modifier.size(18.dp)
//                                   ,onClick = { /*TODO*/ }) {
//                               Icon( Icons.Default.ShoppingCart, contentDescription = "")
//                           }
//                       }
//                    }
//
//                }
//            }
//        }
//    }

@Composable
fun ProductList(productList: List<Product>)
{
    LazyVerticalGrid(columns = GridCells.Adaptive(150.dp))
    {
        items(key = { product-> product.id}, items = productList)
        {
                product-> ProductItem(product = product, onProductSelect = {})
        }
    }
}
//
//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun ProductCard()
//{
//    Card(
//        modifier = Modifier
//            .width(170.dp)
//        ,
//        elevation = CardDefaults.cardElevation(10.dp)
//
//        ,onClick = { /*TODO*/ }) {
//        Column(modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.White)

//            ,
//            horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//            Image(painterResource(id = R.drawable.furniture_4),
//                contentDescription = "",
//                modifier = Modifier.fillMaxWidth()
//                    .aspectRatio(1f/1f),
//                contentScale = ContentScale.Crop)
//
//            Text(text = "Lorem is the best choice...",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(0.dp)
//            ,
//                textAlign = TextAlign.Start,
//                style = MaterialTheme.typography.labelMedium,
//                fontWeight = FontWeight.Bold,
//                fontSize = 20.sp,
//                maxLines = 2
//            )
//
//            Row (modifier = Modifier.fillMaxWidth()
//                    ,horizontalArrangement = Arrangement.SpaceBetween){
//                  Column(modifier = Modifier.weight(7f)) {
//                      Text(text = "${product.price}"
//                          , style = MaterialTheme.typography.displayLarge
//                          ,modifier = Modifier.padding(top = 8.dp)
//                          , color = Color.Yellow)
//
//                      Text(text = "${product.sold}"
//                          , style = MaterialTheme.typography.displayMedium
//                          ,modifier = Modifier
//                          , color = Color.Gray)
//                  }
//
//                    Column(modifier = Modifier.weight(3f)) {
//                       Row (modifier = Modifier
//                           .padding(top = 8.dp)
//                           .fillMaxWidth()
//                           , horizontalArrangement = Arrangement.SpaceAround){
//                            IconButton(modifier = Modifier.size(18.dp)
//                                    ,onClick = { /*TODO*/ }) {
//                                Icon( Icons.Default.FavoriteBorder, contentDescription = "")
//                            }
//                           IconButton(modifier = Modifier.size(18.dp)
//                                   ,onClick = { /*TODO*/ }) {
//                               Icon( Icons.Default.ShoppingCart, contentDescription = "")
//                           }
//                       }
//                    }
//
//                }
//
//        }
//    }
//}
//
@Composable
fun ProductItem(modifier: Modifier = Modifier, product: Product,onProductSelect: (Product)->Unit) {
    Card(
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(0.25.dp, Color.LightGray),
        modifier =
        modifier
            .padding(8.dp)
            .height(320.dp)
            .fillMaxWidth()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .clickable(enabled = true, onClick = { onProductSelect(product) })
        ) {
            val (image, title, description,descriptionColumn) = createRefs()

            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current).data(Constants.IMAGE_PATH + product.imagePath)
                .crossfade(true).build(),
                contentDescription = "",
                error = painterResource(id = R.drawable.ic_broken_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .aspectRatio(1f / 1f)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(150.dp)
                    .fillMaxWidth()
            )
            Text(
                text = product.name,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier =
                Modifier
                    .constrainAs(title) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .absolutePadding(8.dp, 8.dp, 8.dp, 0.dp)
            )
//            Text(
//                text = product.description.trim(),
//                color = Color(0xFFFFC400),
//                fontSize = 14.sp,
//                maxLines = 3,
//                overflow = TextOverflow.Ellipsis,
//                modifier =
//                Modifier
//                    .constrainAs(description) {
//                        top.linkTo(title.bottom)
//                        start.linkTo(parent.start)
//                        end.linkTo(parent.end)
//                        bottom.linkTo(descriptionColumn.top)
//                        height = Dimension.fillToConstraints
//                    }
//                    .fillMaxWidth()
//                    .absolutePadding(8.dp, 8.dp, 8.dp, 0.dp)
//            )
            Column(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .absolutePadding(8.dp, 8.dp, 8.dp, 0.dp)
                    .constrainAs(descriptionColumn) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                horizontalAlignment = Alignment.Start

            ) {

                Column(modifier = Modifier.fillMaxWidth()) {
                    if(product.discount != 0.0){
                        Text(
                            text = "${product.price}",
                            color = Color.Black,
                            maxLines = 1,
                            modifier = Modifier.absolutePadding(top = 8.dp,right = 8.dp),
                            fontSize = 16.sp,
                            style = TextStyle(textDecoration = TextDecoration.LineThrough)
                        )
                    }
                    Text(
//                        text = "%,1f".format(product.price* product.discount /100),
                        text = "${product.price}",
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.absolutePadding(top = 8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }

//                RatingBar(
//                    rating = 5.toFloat(),
//                    modifier =
//                    Modifier
//                        .absolutePadding(top = 0.dp)
//                        .height(12.dp),
//                    color = Color(0xFFC71E9D)
//
//                )
                Text(
//                    text = "%d Variant(s)".format(product.view),
                    text = "View: ${product.view}",
                    color = Color.DarkGray,
                    modifier = Modifier.absolutePadding(top = 8.dp,bottom = 8.dp),
                    fontSize = 12.sp
                )
            }
        }
    }
}