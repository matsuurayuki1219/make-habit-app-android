package jp.matsuura.studytimerandroidapp.data

import jp.matsuura.studytimerandroidapp.data.database.entity.CategoryDbEntity
import jp.matsuura.studytimerandroidapp.data.database.entity.TransactionDbEntity
import jp.matsuura.studytimerandroidapp.model.CategoryModel
import jp.matsuura.studytimerandroidapp.model.TransactionModel

fun CategoryDbEntity.toModel(): CategoryModel {
    return CategoryModel(
        id = id,
        categoryName = categoryName,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}

fun TransactionDbEntity.toModel(): TransactionModel {
    return TransactionModel(
        transactionId = id,
        categoryId = categoryId,
        startedAt = startedAt,
        endedAt = endedAt,
        createdAt = createdAt,
        updatedAt = updatedAt,
        durationSec = durationSec,
    )
}