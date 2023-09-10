package jp.matsuura.studytimerandroidapp.extension

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun OffsetDateTime.toTime(): String {
    // val pattern = "yyyy-MM-dd HH:mm:ssXXX"
    val pattern = "HH:mm"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return format(formatter)
}