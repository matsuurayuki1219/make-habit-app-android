package jp.matsuura.makehabit.androidapp.data

import jp.matsuura.makehabit.androidapp.data.database.entity.CategoryDbEntity
import jp.matsuura.makehabit.androidapp.data.database.entity.TransactionDbEntity
import jp.matsuura.makehabit.androidapp.model.CategoryModel
import jp.matsuura.makehabit.androidapp.model.TransactionModel

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