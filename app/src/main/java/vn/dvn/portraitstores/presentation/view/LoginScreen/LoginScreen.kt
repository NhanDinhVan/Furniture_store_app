package vn.dvn.portraitstores.presentation.view.LoginScreen
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.presentation.components.ClickableLoginTextComponent
import vn.dvn.portraitstores.presentation.components.ClickableTextComponent
import vn.dvn.portraitstores.presentation.components.DividerTextComponent
import vn.dvn.portraitstores.presentation.components.LoadingScreen
import vn.dvn.portraitstores.presentation.navigation.NavigationViewModel
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.RegisterScreen.CustomTextfield
import vn.dvn.portraitstores.presentation.view.RegisterScreen.PasswordTextField
import vn.dvn.portraitstores.presentation.view.RegisterScreen.RegisterGradientButton
import vn.dvn.portraitstores.ui.theme.BgColor
import vn.dvn.portraitstores.ui.theme.DarkGray
import vn.dvn.portraitstores.ui.theme.LightGreen
import vn.dvn.portraitstores.ui.theme.MediumBlue
import vn.dvn.portraitstores.ui.theme.Ocean7
import vn.dvn.portraitstores.ui.theme.Ocean8
import vn.dvn.portraitstores.ui.theme.Ocean9
import vn.dvn.portraitstores.ui.theme.SkinColorGradient1
import vn.dvn.portraitstores.ui.theme.SkinColorGradient2
import vn.dvn.portraitstores.ui.theme.SkinColorGradient3
import vn.dvn.portraitstores.ui.theme.Teal200
import vn.dvn.portraitstores.ui.theme.paledark



@Composable
fun LoginScreen(navController: NavController,
     loginViewModel: LoginViewModel= hiltViewModel()) {
    val navigationViewModel: NavigationViewModel = hiltViewModel()
    val state by loginViewModel.stateLogin.collectAsState()
    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.password.collectAsState()
    val errorMessage by loginViewModel.errorMessage.collectAsState()

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//
//        Image(
//            painter = painterResource(id = R.drawable.unsplash_1),
//            contentDescription = "",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//
//
//
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
////                .navigationBarsPadding()
//                .padding(16.dp,16.dp, 16.dp, 70.dp)
//                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 40.dp))
//                .background(
//                    shape = RoundedCornerShape(16.dp),
//                    color = Color.White.copy(0.6f)
//                )
//                .padding(40.dp)//,40.dp,40.dp,40.dp
//                .align(Alignment.BottomCenter)
//                , horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//                Text(
//                    text = "",
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    style = MaterialTheme.typography.displayMedium,
//                    fontSize = 22.sp,
//                    color = Teal200,
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
////                SimpleOutlinedTextFieldSample(
////                    text = email,
////                    onChangeText = { it -> loginViewModel.onChanceEmail(it) })
//
////
////                SimpleOutlinedPasswordTextField(
////                    password = password,
////                    onChangePassword = { it -> loginViewModel.onChancePassword(it) })
////
//
//                CustomTextfield(
//                    text = email,
//                    onChangeText = {  it -> loginViewModel.onChanceEmail(it)  },
//                    content = "Email address")
//
//                Spacer(modifier = Modifier.padding(3.dp))
//
//                PasswordTextField(
//                    password = password,
//                    onChangePassword = { it -> loginViewModel.onChancePassword(it)  },
//                    content = "Password")
//
//

//
//                Spacer(modifier = Modifier.height(5.dp))
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Absolute.SpaceAround
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(0.5f),
//                        horizontalArrangement = Arrangement.Start,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Checkbox(
//                            checked = false,
//                            onCheckedChange = {},
//                            modifier = Modifier.size(5.dp)
//                        )
//                        Spacer(modifier = Modifier.size(10.dp))
//                        Text(
//                            "Remember me", style = TextStyle(
//                                fontSize = 10.sp
//                            ), color = paledark
//                        )
//                    }
//                    Spacer(modifier = Modifier.height(5.dp))
//                    ClickableTextComponent(
//                        text = "Forgot Password?",
//                        onTextSelected = {},
//                        modifier = Modifier.fillMaxWidth(0.5f)
//                    )
//                }
//
//
//                val gradientColor = listOf(SkinColorGradient1, SkinColorGradient2, SkinColorGradient3)
//                val cornerRadius = 16.dp
//
//
//                Spacer(modifier = Modifier.padding(70.dp))
//
//                GradientButton(
//                    gradientColors = gradientColor,
//                    cornerRadius = cornerRadius,
//                    nameButton = "Login",
//                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
//                    onClick = { loginViewModel.login(navController,navigationViewModel) }
//                )
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                DividerTextComponent()
//
//                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
//                    navController.navigate(Screen.Register.route)
//                })
//
//
//        }
//
//
//    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 25.dp)) {
        Spacer(modifier = Modifier.height(220.dp))
        Text(text = "Login",
            style = MaterialTheme.typography.displaySmall,
            color = MediumBlue,
            fontWeight = FontWeight.Medium,
            modifier =Modifier.padding(horizontal = 10.dp))

        Text(text = "Please login to your account.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier =Modifier.padding(horizontal = 10.dp))

        Spacer(modifier = Modifier.height(35.dp))

        CustomTextfield(
            text = email,
            onChangeText = { it -> loginViewModel.onChanceEmail(it) },
            content = "Email address")
        Spacer(modifier = Modifier.height(5.dp))

        PasswordTextField(
            password = password,
            onChangePassword = { it -> loginViewModel.onChancePassword(it)  },
            content = "Password")
                        Spacer(modifier = Modifier.height(5.dp))
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                text = errorMessage,
                style = TextStyle(
                    fontSize = 10.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Absolute.SpaceAround
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = false,
                            onCheckedChange = {},
                            modifier = Modifier.size(5.dp)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            "Remember me", style = TextStyle(
                                fontSize = 10.sp
                            ), color = paledark
                        )
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    ClickableTextComponent(
                        text = "Forgot Password?",
                        onTextSelected = {},
                        modifier = Modifier.fillMaxWidth(0.5f)
                    )
                }

        val gradientColor = listOf(Ocean7, Ocean8, Ocean9)
        val cornerRadius = 16.dp


        Spacer(modifier = Modifier.padding(35.dp))

        RegisterGradientButton(
            gradientColors = gradientColor,
            cornerRadius = cornerRadius,
            nameButton = "Login",
            roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
            onClick = {
                loginViewModel.login(navController,navigationViewModel)
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        DividerTextComponent()
        Spacer(modifier = Modifier.height(5.dp))
        IconRow()
        Spacer(modifier = Modifier.height(25.dp))
        ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
            navController.navigate(Screen.Register.route)
        })

    }
    if (state.isLoading) {
            LoadingScreen()
        }
}

//...........................................................................
@Composable
fun GradientButton(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    onClick: ()-> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        onClick  =onClick ,

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                /*.background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )*/
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = nameButton,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}


//email id
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleOutlinedTextFieldSample(text: String,
                                  onChangeText: (String) -> Unit,
                                  content: String = "Name or Email Address") {
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = text,
        onValueChange =  onChangeText,
        shape = RoundedCornerShape(30),
        label = {
            Text("Name or Email Address",
                color = paledark,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp,
                modifier = Modifier.background(Color.White.copy(0f))
            ) },

        placeholder = { Text(text = content,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 12.sp) },

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
            ),

        colors = TextFieldDefaults.colors(
            errorIndicatorColor = Color.Red,

            focusedTextColor = Color.Gray,

            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,

            focusedIndicatorColor = paledark,
            unfocusedIndicatorColor = paledark.copy(0.5f),

            disabledIndicatorColor = Color.Gray,
            focusedPlaceholderColor = Color.Transparent,
        ),

        singleLine = true,
        modifier = Modifier.fillMaxWidth(),

        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
                }
            ),
        textStyle = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

    )
}

//password
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleOutlinedPasswordTextField(password: String,
                                    onChangePassword: (String) -> Unit,
                                    content: String = "Enter Password") {

    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    OutlinedTextField(
        value = password,
        onValueChange = onChangePassword,
        shape = RoundedCornerShape(30),
        label = {
            Text(content,
                color = paledark,
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp,
                modifier = Modifier.background(Color.White.copy(0f))
            ) },

        visualTransformation =
            if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
            ),

        colors = TextFieldDefaults.colors(
            errorIndicatorColor = Color.Red,

            focusedTextColor = Color.Gray,

            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,

            focusedIndicatorColor = paledark,
            unfocusedIndicatorColor = paledark.copy(0.5f),

            disabledIndicatorColor = Color.Gray,
            focusedPlaceholderColor = Color.Transparent,
        ),

        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                // Please provide localized description for accessibility services
                val description = if (passwordHidden) "Show password" else "Hide password"
                Icon(imageVector = visibilityIcon, contentDescription = description)
                }
            },

        modifier = Modifier.fillMaxWidth(),

        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
                }
            ),

        textStyle = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    )
}

@Composable
//@Preview(showBackground = true, showSystemUi = true)
fun IconRow()
{
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {

        Card(onClick = {  },
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            shape = CircleShape,
            elevation = CardDefaults.elevatedCardElevation(5.dp),
            colors = CardDefaults.cardColors(
                contentColor = Ocean9
            ) ){
            Icon(painterResource(id = R.drawable.facebook), contentDescription = "",
                modifier = Modifier.padding(7.dp))
        }
        Card(onClick = {  },
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            shape = CircleShape,
            elevation = CardDefaults.elevatedCardElevation(5.dp),
            colors = CardDefaults.cardColors(
                contentColor = Ocean9
            ) ){
            Icon(painterResource(id = R.drawable.twitter), contentDescription = "",
                modifier = Modifier.padding(10.dp))
        }

    }
}