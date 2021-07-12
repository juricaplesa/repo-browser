package dev.plesa.data.common

import okhttp3.Headers
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

fun Headers.getLinks(): Map<String, String> {
    val map = HashMap<String, String>()
    val linkHeader = get("Link")
    if (!linkHeader.isNullOrEmpty()) {
        val links = linkHeader.split(",")
        for (link in links) {
            val key = link.substringAfter("rel=\"", "").substringBefore("\"", "")
            val value = link.substringAfter("<", "").substringBefore(">", "")
            if (key.isNotEmpty() && value.isNotEmpty()) {
                map[key] = value
            }
        }
    }
    return map
}

fun String.remoteDateToDomain(): Date? {
    val inputPattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    return try {
        val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")
        inputFormat.parse(this)
    } catch (exception: Exception) {
        null
    }
}