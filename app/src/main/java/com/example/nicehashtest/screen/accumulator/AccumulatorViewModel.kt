package com.example.nicehashtest.screen.accumulator

import androidx.lifecycle.viewModelScope
import com.example.nicehashtest.base.BaseViewModel
import com.example.nicehashtest.base.IViewEvent
import com.example.nicehashtest.data.AccumulatorTest
import com.example.nicehashtest.logic.accumulator.executeInstructions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class AccumulatorViewModel @Inject constructor() :
    BaseViewModel<AccumulatorState, AccumulatorViewEvent>() {

    override fun createInitialState() = AccumulatorState()

    override fun onTriggerEvent(event: AccumulatorViewEvent) {
        viewModelScope.launch {
            when (event) {
                is AccumulatorViewEvent.ChangeTestData -> {
                    setState { currentState.copy(data = event.data) }
                    setEvent(AccumulatorViewEvent.ChangeTestData(event.data))
                }

                is AccumulatorViewEvent.GetResult -> {
                    setState { currentState.copy(fileText = event.data, isLoading = true) }
                    val result = executeInstructions(event.data)
                    setState { currentState.copy(fileResult = result, isLoading = false) }
                }
            }
        }
    }
}

sealed class AccumulatorViewEvent : IViewEvent {
    data class ChangeTestData(val data: AccumulatorTest) : AccumulatorViewEvent()
    data class GetResult(val data: String) : AccumulatorViewEvent()
}
