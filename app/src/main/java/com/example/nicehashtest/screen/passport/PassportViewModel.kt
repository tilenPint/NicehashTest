package com.example.nicehashtest.screen.passport

import androidx.lifecycle.viewModelScope
import com.example.nicehashtest.api.PassportRepository
import com.example.nicehashtest.api.ReadFileRepository
import com.example.nicehashtest.base.BaseViewModel
import com.example.nicehashtest.base.IViewEvent
import com.example.nicehashtest.data.TestSpec
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class PassportViewModel @Inject constructor(
    private val readFileRepository: ReadFileRepository,
    private val passportRepository: PassportRepository,
) :
    BaseViewModel<PassportState, PassportViewEvent>() {

    init {
        viewModelScope.launch {
            onTriggerEvent(PassportViewEvent.ChangeTestData(currentState.data))
        }
    }

    override fun createInitialState() = PassportState()

    override fun onTriggerEvent(event: PassportViewEvent) {
        viewModelScope.launch {
            when (event) {
                is PassportViewEvent.ChangeTestData -> {
                    setState {
                        currentState.copy(
                            isLoadingSingle = true,
                            isLoadingMulti = true,
                            data = event.data,
                        )
                    }

                    val dataFileRes = currentState.dataFileNameRes
                    val readFile = readFileRepository.readFile(dataFileRes!!)

                    setState { currentState.copy(fileText = readFile) }

                    viewModelScope.launch {
                        val time = measureTimeMillis {
                            val validCount = passportRepository.getResultSingleThread(readFile)
                            setState { currentState.copy(fileResultSingle = validCount.size) }
                        }
                        setState { currentState.copy(isLoadingSingle = false, timeMsSingle = time) }
                    }

                    viewModelScope.launch {
                        val time = measureTimeMillis {
                            val validCount = passportRepository.getResultMultiThread(readFile)
                            setState { currentState.copy(fileResultMulti = validCount.size) }
                        }
                        setState { currentState.copy(isLoadingMulti = false, timeMsMulti = time) }
                    }
                }
            }
        }
    }
}

sealed class PassportViewEvent : IViewEvent {
    data class ChangeTestData(val data: TestSpec) : PassportViewEvent()
}
