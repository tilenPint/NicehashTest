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
            val fieldMap = splitStringToMap(passport)
            if (REQUIRED_FIELDS.all { fieldMap.containsKey(it) } &&
                areRequiredFieldValuesValid(fieldMap)
            ) {
                resultList.add(passport)
            }
        }

        return resultList
    }

    override suspend fun getResultMultiThread(data: String): List<String> =
        coroutineScope {
            splitString(data).map { passport ->
                async(Dispatchers.Default) {
                    val fieldMap = splitStringToMap(passport)

                    if (REQUIRED_FIELDS.all { fieldMap.containsKey(it) } &&
                        areRequiredFieldValuesValid(fieldMap)
                    ) {
                        passport
                    } else {
                        null
                    }
                }
            }.awaitAll().filterNotNull()
        }

    private fun splitStringToMap(passport: String): Map<String, String> {
        val fields = passport.split(" ")
        return fields.associate {
            val keyValue = it.split(":")
            if (keyValue.size == 2) {
                val (key, value) = keyValue
                key to value
            } else {
                "" to ""
            }
        }
    }

    private fun areRequiredFieldValuesValid(fieldMap: Map<String, String>): Boolean {
        return fieldMap.all { (key, value) ->
            when (key) {
                "byr" -> value.toIntOrNull() != null
                "iyr" -> value.toIntOrNull() != null
                "eyr" -> value.toIntOrNull() != null
                "hgt" -> isValidHeight(value)
                "hcl" -> value.matches(Regex("#[0-9a-f]{6}"))
                "ecl" -> value.matches(Regex("(amb|blu|brn|gry|grn|hzl|oth)"))
                "pid" -> value.matches(Regex("\\d{9}"))
                else -> true // Optional field (cid)
            }
        }
    }

    private fun isValidHeight(height: String): Boolean {
        if (height.endsWith("cm")) {
            val value = height.removeSuffix("cm").toIntOrNull()
            return value != null
        }
        if (height.endsWith("in")) {
            val value = height.removeSuffix("in").toIntOrNull()
            return value != null
        }
        return false
    }

    private fun splitString(data: String): List<String> =
        data.split("\n\n").map { it.replace("\n", " ") }

    companion object {
        private val REQUIRED_FIELDS = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    }
}
