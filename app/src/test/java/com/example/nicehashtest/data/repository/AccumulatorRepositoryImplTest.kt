package com.example.nicehashtest.data.repository

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class AccumulatorRepositoryImplTest {
    private val repo = AccumulatorRepositoryImpl()

    @Test
    internal fun `accumulation should be 5`() {
        repo.getResult(testData) shouldBe 5
    }
}

private const val testData = "nop +0\n" +
    "acc +1\n" +
    "jmp +4\n" +
    "acc +3\n" +
    "jmp -3\n" +
    "acc -99\n" +
    "acc +1\n" +
    "jmp -4\n" +
    "acc +6"
