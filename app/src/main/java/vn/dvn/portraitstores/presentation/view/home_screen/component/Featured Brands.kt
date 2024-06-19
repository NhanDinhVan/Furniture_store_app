package vn.dvn.portraitstores.presentation.view.home_screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import vn.dvn.portraitstores.Common.Constants
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.domain.model.Brands

@Composable
fun FeaturedBrand(
    modifier: Modifier = Modifier,
    brandList: List<Brands>
)
{
    Card(
        modifier = Modifier.fillMaxWidth()
            ,
        shape = RectangleShape,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(5.dp)
    ) {
        LazyRow(modifier = modifier.padding(vertical = 10.dp))
        {
            items(brandList.size){
                FeaturedBrandItem(Modifier, brandList[it])
            }
        }
    }
}

@Composable
fun FeaturedBrandItem(
    modifier: Modifier = Modifier,
    brand: Brands
)
{
    Card(
        modifier = modifier
            .size(100.dp, 115.dp)
            .background(Color.White)
            .padding(horizontal = 5.dp)
        ,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        AsyncImage(model = ImageRequest.Builder(context = LocalContext.current).data(Constants.IMAGE_PATH + brand.imagePath)
            .crossfade(true).build(),
            contentDescription = "",
            error = painterResource(id = R.drawable.ic_broken_image),
            placeholder = painterResource(id = R.drawable.unsplash_2),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(75.dp)
                .clip(CircleShape)
                .aspectRatio(1f / 1f)
        )

        Text(text = brand.name,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 5.dp)
        ,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    }


//@Composable
//@Preview(showBackground = true, showSystemUi = true)
//fun FeaturedBrandItemRev()
//{
//      Column(modifier = Modifier
//          .fillMaxSize()
//          .background(Color.White)) {
//          FeaturedBrandItem(Modifier)
//
//          FeaturedBrand()
//      }
//}