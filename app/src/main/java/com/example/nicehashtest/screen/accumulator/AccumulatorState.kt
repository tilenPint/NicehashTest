package com.example.nicehashtest.screen.accumulator

import com.example.nicehashtest.base.IViewState
import com.example.nicehashtest.data.AccumulatorTest

data class AccumulatorState(
    val data: AccumulatorTest = AccumulatorTest.LONG_TEST,
    val fileText: String = "",
    val fileResult: Int? = null,
    val isLoading: Boolean = false,
) : IViewState
