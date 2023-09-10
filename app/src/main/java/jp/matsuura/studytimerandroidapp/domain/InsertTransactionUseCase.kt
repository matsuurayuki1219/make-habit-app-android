package jp.matsuura.studytimerandroidapp.domain

import jp.matsuura.studytimerandroidapp.data.repository.TransactionRepository
import java.time.Instant
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertTransactionUseCase @Inject constructor(
    private val repository: TransactionRepository,
) {

    suspend operator fun invoke(
        categoryId: Int,
        startedAt: Instant,
        endedAt: Instant,
    ): Long {
        return repository.insertTransaction(
            categoryId = categoryId,
            startedAt = startedAt,
            endedAt = endedAt,
        )
    }
}