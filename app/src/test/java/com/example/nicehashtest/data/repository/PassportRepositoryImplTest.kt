package com.example.nicehashtest.data.repository

import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class PassportRepositoryImplTest {
    private val repo = PassportRepositoryImpl()

    @Test
    internal fun `single thread`() {
        repo.getResultSingleThread(testData).size shouldBe 2
    }

    @Test
    internal fun `multi thread`() {
        runBlocking {
            repo.getResultMultiThread(testData).size
        } shouldBe 2
    }
}

private const val testData = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
    "byr:1937 iyr:2017 cid:147 hgt:183cm\n" +
    "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n" +
    "\n" +
    "hcl:#cfa07d byr:1929\n" +
    "hcl:#ae17e1 iyr:2013\n" +
    "eyr:2024\n" +
    "ecl:brn pid:760753108 byr:1931\n" +
    "hgt:179cm\n" +
    "hcl:#cfa07d eyr:2025 pid:166559648\n" +
    "iyr:2011 ecl:brn hgt:59in"
