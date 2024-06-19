package vn.dvn.portraitstores.presentation.view.RegisterScreen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenderSelectionRow(
    onGenderSelected: (List<String>) -> Unit
) {
    var selectedGenders by remember { mutableStateOf(emptyList<String>()) }

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()) {
        GenderCheckbox(
            text = "Male",
            isChecked = selectedGenders.contains("Male"),
            onCheckedChange = { isChecked ->
                selectedGenders = updateSelectedGenders("Male", isChecked, selectedGenders)
                onGenderSelected(selectedGenders)
            }
        )
        GenderCheckbox(
            text = "Female",
            isChecked = selectedGenders.contains("Female"),
            onCheckedChange = { isChecked ->
                selectedGenders = updateSelectedGenders("Female", isChecked, selectedGenders)
                onGenderSelected(selectedGenders)
            }
        )
        Spacer(modifier = Modifier.width(90.dp))
        GenderCheckbox(
            text = "Other",
            isChecked = selectedGenders.contains("Other"),
            onCheckedChange = { isChecked ->
                selectedGenders = updateSelectedGenders("Other", isChecked, selectedGenders)
                onGenderSelected(selectedGenders)
            }
        )
    }
}

@Composable
fun GenderCheckbox(
    text: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.size(30.dp)
        )
        Text(text = text)
    }
}

private fun updateSelectedGenders(
    gender: String,
    isChecked: Boolean,
    selectedGenders: List<String>
): List<String> {
    val updatedList = selectedGenders.toMutableList()
    if (isChecked) {
        updatedList.clear()
        updatedList.add(gender)
    } else {
        updatedList.remove(gender)
    }
    return updatedList
}

