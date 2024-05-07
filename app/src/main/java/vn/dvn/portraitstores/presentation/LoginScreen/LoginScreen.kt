package vn.dvn.portraitstores.presentation.LoginScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardOptions.Companion.Default
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.ui.theme.BgColor
import vn.dvn.portraitstores.ui.theme.Orange
import kotlin.apply as apply1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel()
)
{

    Column(modifier = Modifier
        .offset(0.dp, -20.dp)
        .fillMaxSize()
        .background(Color.White)
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image( painterResource(id = R.drawable.splash_image), contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.55f)
               )


        TextFields(loginViewModel.email,icon = Icons.Default.Email, onChange = { it ->
            loginViewModel.email = it
        }, label = "Email", placeHolder = "Enter your email")

        Spacer(modifier = Modifier.height(15.dp))

        TextFields(loginViewModel.password,icon = Icons.Default.Lock,onChange = { it ->
            loginViewModel.password  = it
        }, placeHolder = "Password", label = "Enter your email")

        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
        , horizontalArrangement = Arrangement.SpaceAround) {
            Text(text = "Forgot password ?"
                ,modifier = Modifier
            , style = MaterialTheme.typography.bodySmall)


            Text(text = "Sign up"
                ,modifier = Modifier
                , style = MaterialTheme.typography.bodySmall)

        }

           Row {
               Buttons(modifier = Modifier
                   .width(200.dp)
                   .height(40.dp)
                   ,"Login"
                   ,{}
                   , Orange)
           }
        Text(text = "Or continue with"
        , style = MaterialTheme.typography.bodySmall
        , color = Color.Gray
        ,modifier = Modifier.padding(vertical = 20.dp))


        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
        , horizontalArrangement = Arrangement.SpaceAround) {


           OutlinedCard(modifier = Modifier,
               onClick = {}) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                  Image(painterResource(id = R.drawable.facebook), contentDescription = "",
                      modifier = Modifier
                          .size(20.dp)
                          .padding(horizontal = 10.dp))
                  Text(text = "Facebook",modifier = Modifier.padding(10.dp),
                      style = MaterialTheme.typography.bodySmall)
              }
           }

            OutlinedCard(modifier = Modifier,
                onClick = {}) {
               Row(verticalAlignment = Alignment.CenterVertically) {
                   Image(painterResource(id = R.drawable.google_icon), contentDescription = "",
                       modifier = Modifier
                           .size(20.dp)
                           .padding(horizontal = 10.dp))
                   Text(text = "Google",modifier = Modifier.padding(10.dp),
                       style = MaterialTheme.typography.bodySmall)
               }
            }

        }


    }
}

@Composable
fun TextFields (valueTextField:String,
                onChange:(String)->Unit,
                icon: ImageVector,
                label:String="",
                placeHolder:String="",
    modifier: Modifier = Modifier){


    TextField(
        value = valueTextField,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Gray,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = BgColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Gray,
            focusedPlaceholderColor = Color.Gray,
        ),
        onValueChange = onChange,
        keyboardOptions = Default,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.LightGray
            ) },
        label = {
                Text(text = label,
                    style = MaterialTheme.typography.bodySmall)
        },
        placeholder = {
                      Text(text = placeHolder,
                          style = MaterialTheme.typography.bodySmall
                      )
        },
        modifier = modifier
            .width(330.dp)
            .height(50.dp)
            .padding(start = 5.dp)
            .border(0.7.dp, Color.Gray, RoundedCornerShape(5.dp))
            )
}

@Composable
fun Buttons(modifier:Modifier = Modifier
            ,content:String
            ,onClickEvent:(String)->Unit
            ,color:Color)
{
    OutlinedButton(
        modifier = modifier
        ,colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        )
        , shape = RoundedCornerShape(15.dp)
        ,onClick = {onClickEvent}) {
        Text(text = content,
            style = MaterialTheme.typography.bodySmall
        , fontWeight = FontWeight.Medium)
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun review()
{
    LoginScreen()

}