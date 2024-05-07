package vn.dvn.portraitstores.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import vn.dvn.portraitstores.presentation.LoginScreen.LoginScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show()
        setContent {
           LoginScreen()
            }
        }
                                                                                                                                                                                                                                                                                                                    }

