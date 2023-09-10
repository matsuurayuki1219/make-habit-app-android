package jp.matsuura.studytimerandroidapp.extension

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

fun Instant.toOffsetDateTime(): OffsetDateTime {
    val zoneId = ZoneId.systemDefault()
    val offset = zoneId.rules.getOffset(this)
    return atOffset(offset)
}