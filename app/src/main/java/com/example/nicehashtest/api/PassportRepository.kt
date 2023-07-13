package com.example.nicehashtest.api

interface PassportRepository {
    fun getResultSingleThread(data: String): List<String>
    suspend fun getResultMultiThread(data: String): List<String>
}
