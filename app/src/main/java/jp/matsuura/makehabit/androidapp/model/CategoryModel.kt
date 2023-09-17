package jp.matsuura.makehabit.androidapp.model

import java.time.Instant
import java.time.OffsetDateTime

data class CategoryModel(
    val id: Int,
    val categoryName: String,
    val createdAt: Instant,
    val updatedAt: Instant,
)