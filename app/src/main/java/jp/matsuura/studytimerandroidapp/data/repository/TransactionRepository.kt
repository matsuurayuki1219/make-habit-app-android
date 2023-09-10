package jp.matsuura.studytimerandroidapp.data.repository

import jp.matsuura.studytimerandroidapp.data.database.dao.TransactionDao
import jp.matsuura.studytimerandroidapp.data.database.entity.TransactionDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
) {

    suspend fun insertTransaction(
        categoryId: Int,
        startedAt: Instant,
        endedAt: Instant,
    ): Long{
        return withContext(Dispatchers.IO) {
            val entity = TransactionDbEntity(
                id = 0,
                categoryId = categoryId,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
            )
            transactionDao.insert(transaction = entity)
        }
    }
}