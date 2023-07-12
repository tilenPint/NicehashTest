package com.example.nicehashtest.screen.accumulator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.nicehashtest.data.AccumulatorTest
import com.example.nicehashtest.data.getData
import com.example.nicehashtest.data.getDataTitle
import com.example.nicehashtest.logic.read.readTextFileFromResources
import kotlinx.coroutines.launch

@Composable
fun AccumulatorScreen(
    viewModel: AccumulatorViewModel,
    navigateToBack: () -> Unit,
) {
    val viewState = viewModel.uiState.collectAsState().value
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // to trigger when screen is created
        val fileResult = context.readTextFileFromResources(viewState.data.getData())
        viewModel.onTriggerEvent(AccumulatorViewEvent.GetResult(fileResult))
    }

    LaunchedEffect(viewModel.uiEvent) {
        launch {
            viewModel.uiEvent.collect {
                when (it) {
                    is AccumulatorViewEvent.ChangeTestData -> {
                        val fileResult = context.readTextFileFromResources(it.data.getData())
                        viewModel.onTriggerEvent(AccumulatorViewEvent.GetResult(fileResult))
                    }

                    else -> {}
                }
            }
        }
    }

    BaseScaffold(topBar = {
        BaseTopBar(
            text = stringResource(id = R.string.accumulator_title),
            actionNavigation = navigateToBack,
            actions = {
                BaseDropdownMenuBox(
                    AccumulatorTest.values().map { it.name },
                    viewState.data.name,
                ) {
                    viewModel.onTriggerEvent(AccumulatorViewEvent.ChangeTestData(enumValueOf(it)))
                }
            },
        )
    }) {
        Box(modifier = Modifier.padding(it)) {
            Content(viewState)
        }
    }
}

@Composable
private fun Content(data: AccumulatorState) {
    val scroll = rememberScrollState(0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 24.dp),
    ) {
        TitleText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = data.data.getDataTitle()),
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxHeight = 300.dp)
                .verticalScroll(scroll),
            text = data.fileText,
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))

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
    }
}

@Preview(showBackground = true)
@Composable
fun AccumulatorScreenPreview() {
    AccumulatorScreen(hiltViewModel()) {}
}
