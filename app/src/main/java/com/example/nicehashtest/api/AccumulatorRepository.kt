package com.example.nicehashtest.api

interface AccumulatorRepository {
    fun getResult(instructionsTest: String): Int
}
