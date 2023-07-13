package com.example.nicehashtest.data

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.nicehashtest.R

enum class TestSpec {
    LONG_TEST,
    SHORT_TEST,
}

@RawRes
fun TestSpec.getAccumulatorFile(): Int {
    return when (this) {
        TestSpec.LONG_TEST -> R.raw.test1
        TestSpec.SHORT_TEST -> R.raw.test1_short
    }
}

@StringRes
fun TestSpec.getAccumulatorTitle(): Int {
    return when (this) {
        TestSpec.LONG_TEST -> R.string.accumulator_test1_title
        TestSpec.SHORT_TEST -> R.string.accumulator_test1_short_title
    }
}

@RawRes
fun TestSpec.getPassportFile(): Int {
    return when (this) {
        TestSpec.LONG_TEST -> R.raw.test2
        TestSpec.SHORT_TEST -> R.raw.test2_short
    }
}

@StringRes
fun TestSpec.getPassportTitle(): Int {
    return when (this) {
        TestSpec.LONG_TEST -> R.string.passport_test2_title
        TestSpec.SHORT_TEST -> R.string.passport_test2_short_title
    }
}
