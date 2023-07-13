package com.example.nicehashtest.api

interface PassportRepository {
    fun getResultSingleThread(data: List<String>): List<String>
    suspend fun getResultMultiThread(data: List<String>): List<String>
}
