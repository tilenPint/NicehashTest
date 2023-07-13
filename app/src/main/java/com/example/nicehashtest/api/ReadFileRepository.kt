package com.example.nicehashtest.api

interface ReadFileRepository {
    fun readFile(resourceId: Int): String
}
