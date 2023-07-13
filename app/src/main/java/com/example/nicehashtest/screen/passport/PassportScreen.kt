package com.example.nicehashtest.screen.passport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun PassportScreen(
    viewModel: PassportViewModel,
    navigateToBack: () -> Unit,
) {
    val viewState = viewModel.uiState.collectAsState().value

    BaseScaffold(topBar = {
        BaseTopBar(
            text = stringResource(id = R.string.passport_title),
            actionNavigation = navigateToBack,
            actions = {
                BaseDropdownMenuBox(
                    TestSpec.values().map { it.name },
                    viewState.data.name,
                ) {
                    viewModel.onTriggerEvent(PassportViewEvent.ChangeTestData(enumValueOf(it)))
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
private fun Content(data: PassportState) {
    val scroll = rememberScrollState(0)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 24.dp),
    ) {
        TitleText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = data.dataTitleRes),
        )
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceEvenly) {
            GetResult(
                modifier = Modifier.testTag("resultSingle"),
                stringResource(id = R.string.result_single_thread),
                data.isLoadingSingle,
                data.fileResultSingle.toString(),
                data.timeMsSingle,
            )
            GetResult(
                modifier = Modifier.testTag("resultMulti"),
                stringResource(id = R.string.result_multi_threads),
                data.isLoadingMulti,
                data.fileResultMulti.toString(),
                data.timeMsMulti,
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scroll),
            text = data.fileText,
        )
        Spacer(modifier = Modifier.padding(top = 24.dp))
    }
}

@Composable
private fun GetResult(
    modifier: Modifier,
    title: String,
    isLoading: Boolean,
    result: String,
    timeMsMulti: Long?,
) {
    if (timeMsMulti == null) return
    Column(
        modifier = modifier,
        Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TitleText(
            text = title,
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.padding(top = 14.dp))

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(24.dp),
            )
        } else {
            TitleText(text = result)
            Spacer(modifier = Modifier.padding(top = 14.dp))
            TitleText(
                text = stringResource(id = R.string.result_time_title),
                style = MaterialTheme.typography.titleMedium,
            )
            TitleText(text = stringResource(id = R.string.result_time_ms, timeMsMulti))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PassportScreenPreview() {
    PassportScreen(hiltViewModel()) {}
}
