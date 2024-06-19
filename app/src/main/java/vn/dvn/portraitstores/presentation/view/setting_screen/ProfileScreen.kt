package vn.dvn.portraitstores.presentation.view.setting_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChangeCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.arvind.furnitureshop.view.BottomBar
import vn.dvn.portraitstores.R
import vn.dvn.portraitstores.domain.model.User
import vn.dvn.portraitstores.presentation.components.ClickableTextComponent

@Composable
fun ProfileScreen(navController: NavController, user : User){
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController,
                user = user
            )
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(it)) {
            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xffDCCDB8)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp).fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom) {
                    Text(text = "Dinh Van Nhan", color = Color.White,
                        fontWeight = FontWeight.Bold,)
                    Text(text = "vannhan2409@gmail.com", color = Color.White)
                }
            }
            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(modifier = Modifier.padding(start = 16.dp, top = 50.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon( Icons.Default.Settings, contentDescription = "")
                    Text(
                        text = "Setting & privacy",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon( Icons.Default.Notifications, contentDescription = "")
                    Text(
                        text = "Notification",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon( Icons.Default.ShoppingBag, contentDescription = "")
                    Text(
                        text = "Orders",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon( Icons.Default.Help, contentDescription = "")
                    Text(
                        text = "Help & Support",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon( Icons.Default.ChangeCircle, contentDescription = "")
                    Text(
                        text = "Change Password",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon( Icons.Default.ChangeCircle, contentDescription = "")
                    Text(
                        text = "Change email",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon( Icons.Default.ChangeCircle, contentDescription = "")
                    Text(
                        text = "Change Phone Number",
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 20.sp
                    )
                }
            }

            Card(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Transparent
                )
            ) {
                Row(modifier = Modifier.padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Icon( Icons.Default.ExitToApp, contentDescription = "")
                    Text(
                        text = "Log out",
                        modifier = Modifier.padding( 16.dp),
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}