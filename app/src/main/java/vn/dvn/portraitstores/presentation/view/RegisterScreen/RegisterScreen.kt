package vn.dvn.portraitstores.presentation.view.RegisterScreen

import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.presentation.components.ClickableLoginTextComponent
import vn.dvn.portraitstores.presentation.components.ClickableTextComponent
import vn.dvn.portraitstores.presentation.components.Divider
import vn.dvn.portraitstores.presentation.components.DividerTextComponent
import vn.dvn.portraitstores.presentation.components.LoadingScreen
import vn.dvn.portraitstores.presentation.navigation.NavigationViewModel
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.LoginScreen.GradientButton
import vn.dvn.portraitstores.presentation.view.LoginScreen.IconRow
import vn.dvn.portraitstores.presentation.view.LoginScreen.LoginViewModel
import vn.dvn.portraitstores.presentation.view.LoginScreen.SimpleOutlinedPasswordTextField
import vn.dvn.portraitstores.presentation.view.LoginScreen.SimpleOutlinedTextFieldSample
import vn.dvn.portraitstores.ui.theme.BlueCustomed
import vn.dvn.portraitstores.ui.theme.DarkGray
import vn.dvn.portraitstores.ui.theme.MediumBlue
import vn.dvn.portraitstores.ui.theme.Ocean7
import vn.dvn.portraitstores.ui.theme.Ocean8
import vn.dvn.portraitstores.ui.theme.Ocean9
import vn.dvn.portraitstores.ui.theme.Shadow3
import vn.dvn.portraitstores.ui.theme.SkinColorGradient1
import vn.dvn.portraitstores.ui.theme.SkinColorGradient2
import vn.dvn.portraitstores.ui.theme.SkinColorGradient3
import vn.dvn.portraitstores.ui.theme.Teal200
import vn.dvn.portraitstores.ui.theme.paledark


@Composable
fun RegisterScreen(
    navController: NavController,
                registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val navigationViewModel: NavigationViewModel = hiltViewModel()
//    val state by registerViewModel.stateLogin.collectAsState()
    val email by registerViewModel.email.collectAsState()
    val password by registerViewModel.password.collectAsState()
    val fullname by registerViewModel.fullname.collectAsState()
    val mobileNumber by registerViewModel.mobileNumber.collectAsState()
    val confirmPassword by registerViewModel.confirmPassword.collectAsState()
    val confirm by registerViewModel.confirm.collectAsState()
    val errorMessage by registerViewModel.errorMessage.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 25.dp)) {
        Spacer(modifier = Modifier.height(130.dp))
        Text(text = "Register",
            style = MaterialTheme.typography.displaySmall,
            color = MediumBlue,
            fontWeight = FontWeight.Medium,
            modifier =Modifier.padding(horizontal = 10.dp))

        Text(text = "Please enter details to register",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            fontWeight = FontWeight.Medium,
            modifier =Modifier.padding(horizontal = 10.dp))

        Spacer(modifier = Modifier.height(35.dp))


        CustomTextfield(
            text = fullname,
            onChangeText = { registerViewModel.onChanceFullname(it) },
            content = "Full name")

        Spacer(modifier = Modifier.height(5.dp))

        CustomTextfield(
            text = email,
            onChangeText = { registerViewModel.onChanceEmail(it) },
            content = "Email address")
        Spacer(modifier = Modifier.height(5.dp))

        CustomTextfield(
                text = mobileNumber,
                onChangeText = { registerViewModel.onChanceMobileNumber(it) },
                content = "Mobile number")

        Spacer(modifier = Modifier.height(5.dp))

        PasswordTextField(
            password = password,
            onChangePassword = { registerViewModel.onChancePassword(it) },
            content = "Password")

        Spacer(modifier = Modifier.height(5.dp))

        PasswordTextField(
            password = confirmPassword,
            onChangePassword = { registerViewModel.onChanceConfirmPassword(it) },
            content = "Confirm Password")
        val gradientColor = listOf(Ocean7,Ocean8, Ocean9)
        val cornerRadius = 16.dp

        Spacer(modifier = Modifier.height(10.dp))

            Text(
                modifier = Modifier
                    .padding(3.dp)
                    .align(Alignment.CenterHorizontally),
                text = errorMessage,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )


        Spacer(modifier = Modifier.padding(25.dp))

        RegisterGradientButton(
            gradientColors = gradientColor,
            cornerRadius = cornerRadius,
            nameButton = "Register",
            roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
            onClick = {
                registerViewModel.onRegister(navController)
            },
//            enabled = confirm
        )
        Spacer(modifier = Modifier.height(5.dp))
        DividerTextComponent()
        Spacer(modifier = Modifier.height(5.dp))
        IconRow()
        Spacer(modifier = Modifier.height(5.dp))
        ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
            navController.navigate(Screen.LoginScreen.route)
        })

    }

    if (registerViewModel.registerState.collectAsState().value.isLoading) {
        LoadingScreen()
    }

}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextfield(text: String,
                                  onChangeText: (String) -> Unit,
                                  content: String = "Name or Email Address") {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column {
        var isFocused by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = text,
            onValueChange =  onChangeText,
            shape = RoundedCornerShape(30),
            label = {
                Text(content,
                    color =  if (isFocused) Shadow3 else Color.Gray,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 15.sp,
                    modifier = Modifier.background(Color.White.copy(0f))
                ) },
//
//            placeholder = { Text(text = content,
//                style = MaterialTheme.typography.labelMedium,
//                fontSize = 12.sp,
//                color = if (isFocused) Shadow3 else Color.Gray) },
//
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),

            colors = TextFieldDefaults.colors(
                errorIndicatorColor = Color.Red,

                focusedTextColor = BlueCustomed,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

                disabledIndicatorColor = Color.Gray,
                focusedPlaceholderColor = Color.Transparent,
            ),

            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },

            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    // do something here
                }
            ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

        )
        Divider(
            color = if (isFocused) BlueCustomed else Color.LightGray,
            thickness = if (isFocused) 3.dp else 1.5.dp,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordTextField(password: String,
                                    onChangePassword: (String) -> Unit,
                                    content: String = "Enter Password") {

    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    Column {
        var isFocused by remember { mutableStateOf(false) }
        OutlinedTextField(
            value = password,
            onValueChange = onChangePassword,
            shape = RoundedCornerShape(30),
            label = {
                Text(content,
                    color =  if (isFocused) Shadow3 else Color.Gray,
                    style = MaterialTheme.typography.labelMedium,
                    fontSize = 14.sp,
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

                focusedTextColor = BlueCustomed,

                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,

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

            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                },

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
        Divider(
            color = if (isFocused) BlueCustomed else Color.LightGray,
            thickness = if (isFocused) 3.dp else 1.5.dp,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .align(Alignment.CenterHorizontally)
        )
    }
}
@Composable
fun RegisterGradientButton(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    onClick: ()-> Unit,
    enabled:Boolean = true
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
        shape = RoundedCornerShape(cornerRadius),
        enabled = enabled
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
