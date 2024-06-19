//package vn.dvn.portraitstores.presentation.view.setting_screen
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.hilt.navigation.compose.hiltViewModel
//
//
//@Composable
//fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
//    val notificationsEnabled by viewModel.notificationsEnabled.collectAsState().value
//    val darkModeEnabled by viewModel.darkModeEnabled
//    val selectedLanguage by viewModel.selectedLanguage
//    val username by viewModel.username
//    val email by viewModel.email
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        Text("Settings", style = MaterialTheme.typography.h4)
//
//        // Switch for notifications
//        SettingItem(
//            title = "Enable Notifications",
//            checked = notificationsEnabled,
//            onCheckedChange = viewModel::onNotificationsChanged
//        )
//
//        // Switch for dark mode
//        SettingItem(
//            title = "Enable Dark Mode",
//            checked = darkModeEnabled,
//            onCheckedChange = viewModel::onDarkModeChanged
//        )
//
//        // Dropdown menu for language selection
//        LanguageSelection(
//            selectedLanguage = selectedLanguage,
//            onLanguageSelected = viewModel::onLanguageSelected
//        )
//
//        // Text field for username
//        OutlinedTextField(
//            value = username,
//            onValueChange = viewModel::onUsernameChanged,
//            label = { Text("Username") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        // Text field for email
//        OutlinedTextField(
//            value = email,
//            onValueChange = viewModel::onEmailChanged,
//            label = { Text("Email") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Save button
//        Button(
//            onClick = { /* Handle save action */ },
//            modifier = Modifier.align(Alignment.End)
//        ) {
//            Text("Save")
//        }
//    }
//}
//
//@Composable
//fun SettingItem(
//    title: String,
//    checked: Boolean,
//    onCheckedChange: (Boolean) -> Unit
//) {
//    Row(
//        modifier = Modifier.fillMaxWidth(),
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(title)
//        Switch(
//            checked = checked,
//            onCheckedChange = onCheckedChange
//        )
//    }
//}
//
//@Composable
//fun LanguageSelection(
//    selectedLanguage: String,
//    onLanguageSelected: (String) -> Unit
//) {
//    var expanded by remember { mutableStateOf(false) }
//    val languages = listOf("English", "Spanish", "French", "German")
//
//    Box(modifier = Modifier.fillMaxWidth()) {
//        OutlinedTextField(
//            value = selectedLanguage,
//            onValueChange = {},
//            readOnly = true,
//            label = { Text("Language") },
//            modifier = Modifier.fillMaxWidth(),
//            trailingIcon = {
//                IconButton(onClick = { expanded = true }) {
//                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
//                }
//            }
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            languages.forEach { language ->
//                DropdownMenuItem(onClick = {
//                    onLanguageSelected(language)
//                    expanded = false
//                }) {
//                    Text(language)
//                }
//            }
//        }
//    }
//}
