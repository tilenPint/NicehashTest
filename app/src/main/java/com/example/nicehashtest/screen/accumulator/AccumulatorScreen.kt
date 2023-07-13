package com.example.nicehashtest.screen.accumulator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.nicehashtest.R
import com.example.nicehashtest.component.BaseDropdownMenuBox
import com.example.nicehashtest.component.BaseScaffold
import com.example.nicehashtest.component.BaseTopBar
import com.example.nicehashtest.component.TitleText
import com.example.nicehashtest.data.TestSpec
import kotlinx.coroutines.launch

@Composable
fun AccumulatorScreen(
    viewModel: AccumulatorViewModel,
    navigateToBack: () -> Unit,
) {
    val viewState = viewModel.uiState.collectAsState().value
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    AccumulatorViewEvent.ShowError -> {
                        snackbarHostState.showSnackbar(context.getString(R.string.error_parse))
                    }

                    else -> {}
                }
            }
        }
    }

    BaseScaffold(
        topBar = {
            BaseTopBar(
                text = stringResource(id = R.string.accumulator_title),
                actionNavigation = navigateToBack,
                actions = {
                    BaseDropdownMenuBox(
                        TestSpec.values().map { it.name },
                        viewState.data.name,
                    ) {
                        viewModel.onTriggerEvent(AccumulatorViewEvent.ChangeTestData(enumValueOf(it)))
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onTriggerEvent(
                    AccumulatorViewEvent.ChangeTestData(viewState.data),
                )
            }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "refresh",
                )
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            Content(
                viewState,
            ) {
                viewModel.onTriggerEvent(it)
            }
        }
    }
}

@Composable
private fun Content(data: AccumulatorState, triggerEvent: (AccumulatorViewEvent) -> Unit) {
    val scroll = rememberScrollState(0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 24.dp),
    ) {
        TitleText(
            modifier = Modifier.fillMaxWidth().testTag("title"),
            text = stringResource(id = data.dataTitleRes),
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        TitleText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.result),
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.padding(top = 14.dp))

        if (data.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally).size(24.dp),
            )
        } else {
            TitleText(
                modifier = Modifier.fillMaxWidth().testTag("result"),
                text = data.fileResult.toString(),
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scroll),
            value = data.fileText,
            onValueChange = { triggerEvent(AccumulatorViewEvent.OnDataChange(it)) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AccumulatorScreenPreview() {
    AccumulatorScreen(hiltViewModel()) {}
}
