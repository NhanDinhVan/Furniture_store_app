package vn.dvn.portraitstores.ui.LoginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel()
{
    var email by mutableStateOf("")
    var password by mutableStateOf("")
}