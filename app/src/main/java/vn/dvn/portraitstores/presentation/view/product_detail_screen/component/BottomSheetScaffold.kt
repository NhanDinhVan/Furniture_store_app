package vn.dvn.portraitstores.presentation.view.product_detail_screen.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.presentation.view.Cart_Screen.QuantitySelector
import vn.dvn.portraitstores.ui.theme.paledark

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetScaffoldState(){
    val scaffoldState = androidx.compose.material.rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = {
            coroutineScope.launch {
                    scaffoldState.show()
            }
        }) {
            Text("Show Bottom Sheet")
        }
    }

    ModalBottomSheetLayout(
        sheetState = scaffoldState,
        sheetContent = {
            BottomSheetContent(onCloseButtonClick = {
                coroutineScope.launch {
                    scaffoldState.hide()
                }
            })
        },
        sheetBackgroundColor = Color.White,
        sheetElevation = 8.dp,
    ) {}
}

@Composable
fun BottomSheetContent(onCloseButtonClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 400.dp) // Adjust the height as needed
            .padding(16.dp)
    ) {
        Column {
            Text("This is the bottom sheet", modifier = Modifier.padding(bottom = 16.dp))
            Button(onClick = onCloseButtonClick) {
                Text("Close Bottom Sheet")
            }
        }
    }
}
@Composable
@Preview(showBackground = true, showSystemUi = true)
fun test() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 200.dp, max = 400.dp) // Adjust the height as needed
            .padding(16.dp)
    ) {
        Column {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {  }
                    .background(Color.White)
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                val (divider, image, name, tag, priceSpacer, price, remove, quantity) = createRefs()
                createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
//                SnackImage(
////                    imageUrl = Constants.IMAGE_PATH + itemDetail.imagePath,
////                    contentDescription = null,
////                    modifier = Modifier
////                        .size(80.dp)
////                        .constrainAs(image) {
////                            top.linkTo(parent.top, margin = 16.dp)
////                            bottom.linkTo(parent.bottom, margin = 16.dp)
////                            start.linkTo(parent.start)
////                        }
////                )
                Image(painterResource(id = R.drawable.unsplash), contentDescription = "",
                    modifier = Modifier
                        .size(80.dp)
                        .constrainAs(image) {
                            top.linkTo(parent.top, margin = 16.dp)
                            bottom.linkTo(parent.bottom, margin = 16.dp)
                            start.linkTo(parent.start)
                        })
                androidx.compose.material.Text(
                    text = "itemDetail.name",
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black,
                    modifier = Modifier.constrainAs(name) {
                        linkTo(
                            start = image.end,
                            startMargin = 16.dp,
                            end = remove.start,
                            endMargin = 16.dp,
                            bias = 0f
                        )
                    }
                )
                androidx.compose.material.IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .constrainAs(remove) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        }
                        .padding(top = 5.dp, end = 0.dp)
                ) {

                }
                androidx.compose.material.Text(
//                    text = "${itemDetail.brand.name}",
                    text = "brand",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.constrainAs(tag) {
                        linkTo(
                            start = image.end,
                            startMargin = 16.dp,
                            end = parent.end,
                            endMargin = 16.dp,
                            bias = 0f
                        )
                    }
                )
                Spacer(
                    Modifier
                        .height(8.dp)
                        .constrainAs(priceSpacer) {
                            linkTo(top = tag.bottom, bottom = price.top)
                        }
                )
                androidx.compose.material.Text(
//                    text = "${itemDetail.price}",
                    text = "1000.00",
                    style = MaterialTheme.typography.subtitle1,
                    color = paledark,
                    modifier = Modifier.constrainAs(price) {
                        linkTo(
                            start = image.end,
                            end = quantity.start,
                            startMargin = 16.dp,
                            endMargin = 16.dp,
                            bias = 0f
                        )
                    }
                )
                QuantitySelector(
                    count = 1,
                    decreaseItemCount = {  }  ,
                    increaseItemCount = {  }  ,
                    modifier = Modifier.constrainAs(quantity) {
                        baseline.linkTo(price.baseline)
                        end.linkTo(parent.end)
                    }
                )

            }
            vn.dvn.portraitstores.presentation.components.Divider(color = Color.LightGray)
        }


    }
}
