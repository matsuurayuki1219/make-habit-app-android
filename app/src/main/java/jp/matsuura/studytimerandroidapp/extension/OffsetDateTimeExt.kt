package jp.matsuura.studytimerandroidapp.extension

import java.time.DayOfWeek
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

fun OffsetDateTime.toTime(): String {
    val pattern = "HH:mm"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return format(formatter)
}

fun OffsetDateTime.toDate(): String {
    val pattern = "MM/dd"
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val date = format(formatter)
    val dayOfWeek = when (dayOfWeek) {
        DayOfWeek.MONDAY -> "月"
        DayOfWeek.TUESDAY -> "火"
        DayOfWeek.WEDNESDAY -> "水"
        DayOfWeek.THURSDAY -> "木"
        DayOfWeek.FRIDAY -> "金"
        DayOfWeek.SATURDAY -> "土"
        DayOfWeek.SUNDAY -> "日"
    }
    return "$date($dayOfWeek)"
}