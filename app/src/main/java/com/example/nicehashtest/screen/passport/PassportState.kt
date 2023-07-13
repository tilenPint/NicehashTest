package com.example.nicehashtest.screen.passport

import com.example.nicehashtest.base.IViewState
import com.example.nicehashtest.data.TestSpec
import com.example.nicehashtest.data.getPassportFile
import com.example.nicehashtest.data.getPassportTitle

data class PassportState(
    val data: TestSpec = TestSpec.SHORT_TEST,
    val valueText: String = "",
    val fileResultSingle: Int? = null,
    val fileResultMulti: Int? = null,
    val timeMsSingle: Long? = null,
    val timeMsMulti: Long? = null,
    val isLoadingSingle: Boolean = false,
    val isLoadingMulti: Boolean = false,
) : IViewState {
    val dataTitleRes = data.getPassportTitle()
    val dataFileNameRes = data.getPassportFile()
}
