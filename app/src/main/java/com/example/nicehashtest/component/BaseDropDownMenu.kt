package com.example.nicehashtest.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BaseDropdownMenuBox(data: List<String>, selectedItem: String, selected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedItem) }

    Box {
        IconButton(
            modifier = Modifier.testTag("expandable icon"),
            onClick = { expanded = !expanded },
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More",
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            data.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier.testTag("expandable items"),
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        selected(item)
                    },
                    enabled = selectedText != item,
                )
            }
        }
    }
}

@Preview
@Composable
fun BaseDropdownMenuBoxPreview() {
    BaseDropdownMenuBox(
        listOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha"),
        "Americano",
        {},
    )
}
