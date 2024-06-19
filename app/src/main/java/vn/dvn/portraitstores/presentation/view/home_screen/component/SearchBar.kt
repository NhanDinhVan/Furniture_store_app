package vn.dvn.portraitstores.presentation.view.home_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import vn.dvn.portraitstores.presentation.navigation.Screen
import vn.dvn.portraitstores.presentation.view.home_screen.HomeScreenViewModel
import vn.dvn.portraitstores.ui.theme.paledark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    text: String,
    onClickCart:()-> Unit,
    isFocused: Boolean,
    homeScreenViewModel: HomeScreenViewModel
    )
{
    // FocusRequester to request focus
    val focusRequester = remember { FocusRequester() }

    // FocusManager to manage focus
    val focusManager = LocalFocusManager.current

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .clip(RoundedCornerShape(15.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround) {

        // TextField with focus change listener
        OutlinedTextField(
            value = text,
            onValueChange = { homeScreenViewModel.onChanceSearchContent(it)},
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .weight(8f)
                .padding(start = 16.dp, top = 5.dp, bottom = 10.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        onTextFieldFocused()
                    }
                    homeScreenViewModel.onChangeIsFocused(focusState.isFocused)
                },
            label = { Text("Search products") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    homeScreenViewModel.onSearch()
                }
            ),
            trailingIcon = { IconButton(onClick = {
                focusManager.clearFocus()
                homeScreenViewModel.onClearSearchContent()
            }) {
                if(text != "") {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear text",
                        tint = Color.Gray
                    )
                }
            }},
            leadingIcon = {
                Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Clear text",
                tint = Color.Gray
            )}
        )
        IconButton(onClick = { onClickCart() }
        ,modifier = Modifier.fillMaxWidth()
                .weight(1.6f)
                .padding(end = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Shopping cart",
                tint = paledark,
                modifier = Modifier.size(25.dp)
            )}
        }
        

    }


@Composable
fun FocusStateExample() {
    // State to hold the text
    var text by remember { mutableStateOf("") }

    // FocusRequester to request focus
    val focusRequester = remember { FocusRequester() }

    // FocusManager to manage focus
    val focusManager = LocalFocusManager.current

    // State to track if the TextField is focused
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        // TextField with focus change listener
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged { focusState ->
                    if (focusState.isFocused) {
                        onTextFieldFocused()
                    }
                    isFocused = focusState.isFocused
                },
            label = { Text("Enter text") },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Additional UI elements
        Text("TextField is ${if (isFocused) "focused" else "not focused"}")

        Button(onClick = { focusRequester.requestFocus() }) {
            Text("Request Focus")
        }
    }
}

// Function to be called when TextField is focused
fun onTextFieldFocused() {
    // Add your desired functionality here
    println("TextField is focused")
}