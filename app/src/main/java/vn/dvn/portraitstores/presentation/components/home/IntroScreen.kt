package vn.dvn.portraitstores.presentation.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.ui.theme.LightGreen
import vn.dvn.portraitstores.ui.theme.LightOrange
import vn.dvn.portraitstores.ui.theme.PinkIntro
import vn.dvn.portraitstores.ui.theme.textShadowCustom


@Composable
fun circle(sizeCircle:Int,elevationCircle:Int , colorCircle:Color,modifier: Modifier)
{

    Card(modifier = modifier
        .size(sizeCircle.dp)
        , colors = CardDefaults.cardColors(colorCircle)
    , shape = CircleShape
    , elevation = CardDefaults.cardElevation(elevationCircle.dp))
    {

    }
}

@Composable
fun rhombus(sizeRhombus:Int,elevationRhombus:Int , colorRhombus:Color,modifier: Modifier)
{

    Card(modifier = modifier
        .size(sizeRhombus.dp)
        , colors = CardDefaults.cardColors(colorRhombus)
        , shape = AbsoluteCutCornerShape(50)
        , elevation = CardDefaults.cardElevation(elevationRhombus.dp))
    {

    }
}
//@Preview
//@Composable
//fun review()
//{
//    rhombus(sizeRhombus = 200, elevationRhombus = 20, colorRhombus = LightGreen, modifier = Modifier)
//}

@Composable
fun IntroScreen()
{

    ConstraintLayout(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),31
    ) {

        circle( 406,10,  LightGreen,
            modifier = Modifier.offset(-157.dp,-168.dp))

        circle( 80,10, LightGreen,
            modifier = Modifier.offset(285.dp,155.dp))


        circle( 177,10,  PinkIntro,
            modifier = Modifier.offset(0.dp,237.dp))

        circle( 326,10,  PinkIntro,
            modifier = Modifier.offset(213.dp,407.dp))

        textShadowCustom( "Portrait Stores"
            , LightOrange
            ,MaterialTheme.typography.bodyLarge
            ,30.sp
            ,modifier = Modifier.offset(21.dp,600.dp)
        , fontWeight = FontWeight.Bold
        )

        rhombus(sizeRhombus = 55, elevationRhombus = 8, colorRhombus = LightGreen
            , modifier = Modifier.offset(30.dp, 533.dp))

        rhombus(sizeRhombus = 50, elevationRhombus = 8, colorRhombus = PinkIntro
            , modifier = Modifier.offset(88.dp, 112.dp))


        Image(painter = painterResource(id = R.drawable.portrait_logo), contentDescription = ""
        ,modifier = Modifier
                .size(145.dp)
                .offset(120.dp, 305.dp))

    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun introScreenReview()
{
    IntroScreen()
}