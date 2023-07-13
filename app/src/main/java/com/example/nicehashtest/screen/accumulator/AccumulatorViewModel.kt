package com.example.nicehashtest.screen.accumulator

import androidx.lifecycle.viewModelScope
import com.example.nicehashtest.api.AccumulatorRepository
import com.example.nicehashtest.api.ReadFileRepository
import com.example.nicehashtest.base.BaseViewModel
import com.example.nicehashtest.base.IViewEvent
import com.example.nicehashtest.data.TestSpec
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class AccumulatorViewModel @Inject constructor(
    private val readFileRepository: ReadFileRepository,
    private val accumulatorRepository: AccumulatorRepository,
) : BaseViewModel<AccumulatorState, AccumulatorViewEvent>() {

    override fun createInitialState() = AccumulatorState()

    init {
        viewModelScope.launch {
            onTriggerEvent(AccumulatorViewEvent.ChangeTestData(currentState.data))
        }
    }

    override fun onTriggerEvent(event: AccumulatorViewEvent) {
        viewModelScope.launch {
            when (event) {
                is AccumulatorViewEvent.ChangeTestData -> {
                    setState { currentState.copy(isLoading = true, data = event.data) }
                    val readFile = readFileRepository.readFile(currentState.dataFileNameRes)
                    val result = accumulatorRepository.getResult(readFile)
                    setState {
                        currentState.copy(
                            fileResult = result,
                            isLoading = false,
                            fileText = readFile,
                        )
                    }
                }
            }
        }
    }
}

sealed class AccumulatorViewEvent : IViewEvent {
    data class ChangeTestData(val data: TestSpec) : AccumulatorViewEvent()
//    data class GetResult(val data: String) : AccumulatorViewEvent()
}
