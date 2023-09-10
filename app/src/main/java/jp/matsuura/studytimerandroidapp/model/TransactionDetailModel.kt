package jp.matsuura.studytimerandroidapp.model

import jp.matsuura.studytimerandroidapp.extension.toOffsetDateTime
import java.time.Instant
import java.util.Date

data class TransactionDetailModel(
    val transactionId: Int,
    val categoryId: Int,
    val categoryName: String,
    val startedAt: Instant,
    val endedAt: Instant,
    val durationMillSec: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
) {

    val durationSec = durationMillSec / 1000
    val dateOfStartedAt = startedAt.toOffsetDateTime()
    val dateOfEndedAt = endedAt.toOffsetDateTime()

}