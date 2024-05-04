package vn.dvn.portraitstores

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import vn.dvn.portraitstores.ui.App.AppActivity
import vn.dvn.portraitstores.ui.LoginScreen.LoginScreen
import vn.dvn.portraitstores.ui.components.home.IntroScreen
import vn.dvn.portraitstores.ui.components.products.ProductCard
import vn.dvn.portraitstores.ui.theme.PortraitStoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show()
        setContent {
           LoginScreen()
            }
        }
                                                                                                                                                                                                                                                                                                                    }

