package com.example.nicehashtest.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.nicehashtest.R
import com.example.nicehashtest.ui.theme.NicehashTestTheme

@Composable
fun HomeScreen(
    navigateToAcc: () -> Unit,
    navigateToPassport: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxSize().weight(1f)) {
            Image(
                modifier = Modifier.size(128.dp).align(Alignment.Center),
                painter = painterResource(id = R.drawable.ic_main),
                contentDescription = "logo",
            )
        }
        Column(
            modifier = Modifier.fillMaxSize().weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(
                modifier = Modifier.fillMaxWidth().padding(24.dp),
                onClick = navigateToAcc,
            ) {
                Text(stringResource(id = R.string.accumulator_title))
            }

            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
                onClick = navigateToPassport,
            ) {
                Text(stringResource(id = R.string.passport_title))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NicehashTestTheme {
        HomeScreen(navigateToAcc = {}, navigateToPassport = {})
    }
}
