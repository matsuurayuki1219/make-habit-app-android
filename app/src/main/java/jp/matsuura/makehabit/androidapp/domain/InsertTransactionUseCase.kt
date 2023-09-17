package jp.matsuura.makehabit.androidapp.domain

import jp.matsuura.makehabit.androidapp.data.repository.TransactionRepository
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
        durationSec: Int,
    ): Long {
        return repository.insertTransaction(
            categoryId = categoryId,
            startedAt = startedAt,
            endedAt = endedAt,
            durationSec = durationSec,
        )
    }
}