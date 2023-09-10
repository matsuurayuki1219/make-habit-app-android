package jp.matsuura.studytimerandroidapp.model

import java.time.Instant

data class TransactionModel(
    val transactionId: Int,
    val categoryId: Int,
    val startedAt: Instant,
    val endedAt: Instant,
    val durationSec: Int,
    val createdAt: Instant,
    val updatedAt: Instant,
)