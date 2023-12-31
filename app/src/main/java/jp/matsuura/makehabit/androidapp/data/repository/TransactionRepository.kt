package jp.matsuura.makehabit.androidapp.data.repository

import jp.matsuura.makehabit.androidapp.data.database.dao.TransactionDao
import jp.matsuura.makehabit.androidapp.data.database.entity.TransactionDbEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
) {

    fun getAllTransaction(): Flow<List<TransactionDbEntity>> {
        return transactionDao.getAll()
    }

    suspend fun insertTransaction(
        categoryId: Int,
        startedAt: Instant,
        endedAt: Instant,
        durationSec: Int,
    ): Long {
        return withContext(Dispatchers.IO) {
            val entity = TransactionDbEntity(
                id = 0,
                categoryId = categoryId,
                startedAt = startedAt,
                endedAt = endedAt,
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
                durationSec = durationSec,
            )
            transactionDao.insert(transaction = entity)
        }
    }

    suspend fun getTransaction(
        transactionId: Int,
        categoryId: Int,
    ): TransactionDbEntity {
        return withContext(Dispatchers.IO) {
            transactionDao.getById(
                transactionId = transactionId,
                categoryId = categoryId,
            )
        }
    }
}