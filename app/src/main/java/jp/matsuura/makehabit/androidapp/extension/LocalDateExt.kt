package jp.matsuura.makehabit.androidapp.extension

import java.time.DayOfWeek
import java.time.LocalDate

fun LocalDate.toDayOfWeek(): String {
    return when (dayOfWeek) {
        DayOfWeek.MONDAY -> "月"
        DayOfWeek.TUESDAY -> "火"
        DayOfWeek.WEDNESDAY -> "水"
        DayOfWeek.THURSDAY -> "木"
        DayOfWeek.FRIDAY -> "金"
        DayOfWeek.SATURDAY -> "土"
        DayOfWeek.SUNDAY -> "日"
    }
}