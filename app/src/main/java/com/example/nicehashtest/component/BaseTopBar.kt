package com.example.nicehashtest.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseTopBar(
    modifier: Modifier = Modifier,
    text: String,
    actionNavigation: () -> Unit = {},
    title: @Composable () -> Unit = {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium,
        )
    },
    navigationIcon: @Composable () -> Unit = {
        IconButton(onClick = actionNavigation) {
            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "ArrowBack")
        }
    },
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = navigationIcon,
        actions = actions,
    )
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {
    BaseTopBar(
        modifier = Modifier,
        text = "Value",
    )
}
