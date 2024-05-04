package vn.dvn.portraitstores.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp as dp

@Composable
fun textShadowCustom(
    content:String
    , colorCustom:Color= Color.Black
    , styleText: TextStyle=MaterialTheme.typography.bodyLarge
    , fontSize: TextUnit
    ,fontWeight: FontWeight=FontWeight.Medium
    ,modifier: Modifier=Modifier
)
{
    Box(modifier.fillMaxSize()

    )
    {
        Text(
            text = content,
            style = styleText,
            modifier = Modifier.alpha(0.3f)
                .blur(0.7.dp)
                .offset(-5.dp,5.dp),
            color = colorCustom
            ,fontSize = fontSize
            ,fontWeight=fontWeight
        )
        Text(
            text = content,
            style = styleText,
            modifier = Modifier.blur(0.8.dp)
            , color = colorCustom
            ,fontSize = fontSize
            ,fontWeight=fontWeight

            )
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun review(){
    textShadowCustom(content = "Text custom", LightGreen, MaterialTheme.typography.bodyLarge, 25.sp
    ,modifier=Modifier.offset(0.dp,4.dp))
}