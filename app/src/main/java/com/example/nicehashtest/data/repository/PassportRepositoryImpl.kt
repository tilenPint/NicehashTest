package com.example.nicehashtest.data.repository

import com.example.nicehashtest.api.PassportRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PassportRepositoryImpl : PassportRepository {
    override fun getResultSingleThread(data: String): List<String> {
        val resultList = mutableListOf<String>()

        splitString(data).forEach { passport ->
            val fields = passport.split(" ")
            val fieldKeys = fields.map { it.split(":")[0] }.toSet()

            if (REQUIRED_FIELDS.all { fieldKeys.contains(it) }) {
                resultList.add(passport)
            }
        }

        return resultList
    }

    override suspend fun getResultMultiThread(data: String): List<String> =
        coroutineScope {
            splitString(data).map { passport ->
                async(Dispatchers.Default) {
                    val fields = passport.split(" ")
                    val fieldKeys = fields.map { it.split(":")[0] }.toSet()

                    if (REQUIRED_FIELDS.all { fieldKeys.contains(it) }) {
                        passport
                    } else {
                        null
                    }
                }
            }.awaitAll().filterNotNull()
        }

    private fun splitString(data: String): List<String> =
        data.split("\n\n").map { it.replace("\n", " ") }

    companion object {
        private val REQUIRED_FIELDS = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    }
}
