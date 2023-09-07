package jp.matsuura.studytimerandroidapp.data.repository

import jp.matsuura.studytimerandroidapp.data.database.dao.CategoryDao
import jp.matsuura.studytimerandroidapp.data.database.entity.CategoryDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao,
) {

    fun getAllCategories(): Flow<List<CategoryDbEntity>> {
        return categoryDao.getAll()
    }

    suspend fun getCategory(categoryId: Int): CategoryDbEntity {
        return withContext(Dispatchers.IO) {
            categoryDao.getById(categoryId = categoryId)
        }
    }

    suspend fun insertCategory(categoryName: String) {
        withContext(Dispatchers.IO) {
            val entity = CategoryDbEntity(
                id = 0,
                categoryName = categoryName,
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
            )
            categoryDao.insertCategory(entity)
        }
    }

}