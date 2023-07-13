package com.example.nicehashtest.data.repository

import com.example.nicehashtest.api.ReadFileRepository

class FakeReadFileRepository : ReadFileRepository {
    var readFile = false
    override fun readFile(resourceId: Int): String {
        readFile = true
        return "test"
    }
}
