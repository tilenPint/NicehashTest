package com.example.nicehashtest.logic.accumulator

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class ExecutionKtTest {

    @Test
    fun testAccumulatorShort() {
        executeInstructions(testData) shouldBe 5
    }
}

private val testData = "nop +0\n" +
    "acc +1\n" +
    "jmp +4\n" +
    "acc +3\n" +
    "jmp -3\n" +
    "acc -99\n" +
    "acc +1\n" +
    "jmp -4\n" +
    "acc +6"
