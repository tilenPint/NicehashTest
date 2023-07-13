package com.example.nicehashtest.data.repository

import android.content.Context
import com.example.nicehashtest.api.ReadFileRepository
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class ReadFileRepositoryImpl @Inject constructor(
    private val context: Context,
) : ReadFileRepository {

    override fun readFile(resourceId: Int): String {
        val inputStream = context.resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        var previousLineWasBlank = false
        try {
            while (reader.readLine().also { line = it } != null) {
                if (line?.isNotBlank() == true) {
                    stringBuilder.append(line).append('\n')
                    previousLineWasBlank = false
                } else if (!previousLineWasBlank) {
                    stringBuilder.append('\n')
                    previousLineWasBlank = true
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                reader.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return stringBuilder.toString()
    }
}
