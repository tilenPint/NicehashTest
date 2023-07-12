package com.example.nicehashtest.logic.read

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun Context.readTextFileFromResources(resourceId: Int): String {
    val inputStream = resources.openRawResource(resourceId)
    val reader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()
    var line: String?
    try {
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line).append('\n')
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
