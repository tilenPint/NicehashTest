package com.example.nicehashtest.data

import com.example.nicehashtest.R

enum class AccumulatorTest {
    LONG_TEST,
    SHORT_TEST,
}

fun AccumulatorTest.getData(): Int {
    return when (this) {
        AccumulatorTest.LONG_TEST -> R.raw.test1
        AccumulatorTest.SHORT_TEST -> R.raw.test1_short
    }
}

fun AccumulatorTest.getDataTitle(): Int {
    return when (this) {
        AccumulatorTest.LONG_TEST -> R.string.test1_title
        AccumulatorTest.SHORT_TEST -> R.string.test1_short_title
    }
}
