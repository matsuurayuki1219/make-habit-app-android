package jp.matsuura.studytimerandroidapp.data

import jp.matsuura.studytimerandroidapp.data.database.entity.CategoryDbEntity
import jp.matsuura.studytimerandroidapp.model.CategoryModel

fun CategoryDbEntity.toModel(): CategoryModel {
    return CategoryModel(
        id = id,
        categoryName = categoryName,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}