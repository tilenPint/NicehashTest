package com.example.nicehashtest.screen.accumulator

import com.example.nicehashtest.api.AccumulatorRepository
import com.example.nicehashtest.api.ReadFileRepository

class FakeViewModel(
    readFileRepository: ReadFileRepository,
    accumulatorRepository: AccumulatorRepository,
) : AccumulatorViewModel(readFileRepository, accumulatorRepository)
