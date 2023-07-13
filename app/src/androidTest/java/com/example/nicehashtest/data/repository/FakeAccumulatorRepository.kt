package com.example.nicehashtest.data.repository

import com.example.nicehashtest.api.AccumulatorRepository

class FakeAccumulatorRepository : AccumulatorRepository {
    var getResult = false
    override fun getResult(instructionsTest: String): Int {
        getResult = true
        return 0
    }
}
