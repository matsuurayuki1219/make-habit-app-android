package jp.matsuura.makehabit.androidapp.domain

import jp.matsuura.makehabit.androidapp.data.repository.CategoryRepository
import jp.matsuura.makehabit.androidapp.data.repository.TransactionRepository
import jp.matsuura.makehabit.androidapp.data.toModel
import jp.matsuura.makehabit.androidapp.model.TransactionDetailModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDetailTransactionUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val transactionRepository: TransactionRepository,
) {
    suspend operator fun invoke(
        transactionId: Int,
        categoryId: Int,
    ): TransactionDetailModel {
        val transaction = transactionRepository.getTransaction(
            transactionId = transactionId,
            categoryId = categoryId,
        ).toModel()
        val category = categoryRepository.getCategory(categoryId = categoryId)
        return TransactionDetailModel(
            transactionId = transactionId,
            categoryId = categoryId,
            categoryName = category.categoryName,
            startedAt = transaction.startedAt,
            endedAt = transaction.endedAt,
            durationMillSec = transaction.durationSec,
            createdAt = transaction.createdAt,
            updatedAt = transaction.updatedAt,
        )
    }
}