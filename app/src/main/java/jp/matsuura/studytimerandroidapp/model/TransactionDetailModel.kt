package jp.matsuura.studytimerandroidapp.model

import java.time.Instant

data class TransactionDetailModel(
    val transactionId: Int,
    val categoryId: Int,
    val categoryName: String,
    val startedAt: Instant,
    val endedAt: Instant,
    val durationSec: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
)