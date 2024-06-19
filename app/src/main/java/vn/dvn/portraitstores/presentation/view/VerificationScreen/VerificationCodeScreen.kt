package vn.dvn.portraitstores.presentation.view.VerificationScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import vn.dvn.portraitstores.presentation.components.LoadingScreen
import vn.dvn.portraitstores.presentation.view.RegisterScreen.RegisterGradientButton
import vn.dvn.portraitstores.ui.theme.Ocean7
import vn.dvn.portraitstores.ui.theme.Ocean8
import vn.dvn.portraitstores.ui.theme.Ocean9

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VerificationCodeScreen(viewModel: VerificationCodeViewModel = hiltViewModel(), email : String, navController: NavController) {
    val focusRequesters = List(5) { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val code by viewModel.code.collectAsState()
    val isCodeValid by viewModel.isCodeValid.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter Verification Code",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 0..4) {
                OutlinedTextField(
                    value = code[i],
                    onValueChange = { newValue ->
                        viewModel.onCodeChanged(i, newValue)
                        if (newValue.isNotEmpty() && i < 4) {
                            focusRequesters[i + 1].requestFocus()
                        } else if (newValue.isEmpty() && i > 0) {
                            focusRequesters[i - 1].requestFocus()
                        }
                    },
                    modifier = Modifier
                        .width(50.dp)
                        .focusRequester(focusRequesters[i]),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colors.primary,
                        unfocusedBorderColor = Color.Gray,
                        backgroundColor = Color.Transparent,
                        cursorColor = MaterialTheme.colors.primary
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        androidx.compose.material3.Text(
            modifier = Modifier,
            text = errorMessage,
            style = TextStyle(
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Card(onClick = {},
            backgroundColor = Color.Transparent,
            elevation = 0.dp){
            androidx.compose.material3.Text(
                modifier = Modifier,
                text = "Sending again.",
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }

        Spacer(modifier = Modifier.padding(65.dp))
//        Button(
//            onClick = {
//                viewModel.onSubmit(email,navController)
//            },
//            enabled = isCodeValid
//        ) {
//            Text("Submit")
//        }
        val gradientColor = listOf(Ocean7, Ocean8, Ocean9)
        val cornerRadius = 16.dp
        RegisterGradientButton(
            gradientColors = if(isCodeValid) gradientColor else listOf(Color.LightGray, Color.Gray),
            cornerRadius = cornerRadius,
            nameButton = "Register",
            roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),
            onClick = {
                viewModel.onSubmit(email,navController)
            },
            enabled = isCodeValid
        )
    }
    if (viewModel.verificationState.collectAsState().value.isLoading) {
        LoadingScreen()
    }

    androidx.compose.material3.Card(modifier = Modifier.offset(10.dp, 10.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(containerColor = Color.LightGray.copy(0.4f)),
        onClick = { navController.popBackStack() }) {
        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Back")
    }
}
