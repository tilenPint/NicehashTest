package com.example.nicehashtest.data.repository

import com.example.nicehashtest.api.AccumulatorRepository
import com.example.nicehashtest.data.Instruction

class AccumulatorRepositoryImpl : AccumulatorRepository {
    override fun getResult(instructionsTest: String): Int {
        var accumulator = 0
        var pointer = 0
        val visited = mutableSetOf<Int>()
        val instructions = parseInstructions(instructionsTest)

        while (pointer < instructions.size) {
            if (visited.contains(pointer)) {
                return accumulator
            }

            visited.add(pointer)
            val instruction = instructions[pointer]

            when (instruction.operation) {
                "acc" -> {
                    accumulator += instruction.argument
                    pointer++
                }

                "jmp" -> pointer += instruction.argument
                "nop" -> pointer++
            }
        }

        return accumulator
    }

    private fun parseInstructions(input: String): List<Instruction> {
        val lines = input.lines().map { it.trim() }.filter { it.isNotEmpty() }
        return lines.map { line ->
            val parts = line.split(" ")
            Instruction(parts[0], parts[1].toInt())
        }
    }
}
