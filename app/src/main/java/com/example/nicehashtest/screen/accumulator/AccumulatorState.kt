package com.example.nicehashtest.screen.accumulator

import com.example.nicehashtest.base.IViewState
import com.example.nicehashtest.data.TestSpec
import com.example.nicehashtest.data.getAccumulatorFile
import com.example.nicehashtest.data.getAccumulatorTitle

data class AccumulatorState(
    val data: TestSpec = TestSpec.LONG_TEST,
    val valueText: String = "",
    val fileResult: Int? = null,
    val isLoading: Boolean = false,
) : IViewState {
    val dataTitleRes = data.getAccumulatorTitle()
    val dataFileNameRes = data.getAccumulatorFile()
}
