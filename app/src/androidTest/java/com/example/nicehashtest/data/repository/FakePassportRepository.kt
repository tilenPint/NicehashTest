package com.example.nicehashtest.data.repository

import com.example.nicehashtest.api.PassportRepository

class FakePassportRepository : PassportRepository {
    var getSingleResult = false
    var getMultiResult = false

    override fun getResultSingleThread(data: String): List<String> {
        getSingleResult = true
        return listOf()
    }

    override suspend fun getResultMultiThread(data: String): List<String> {
        getMultiResult = true
        return listOf()
    }
}
