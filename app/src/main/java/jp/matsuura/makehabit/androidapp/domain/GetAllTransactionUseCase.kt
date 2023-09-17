package jp.matsuura.makehabit.androidapp.domain

import jp.matsuura.makehabit.androidapp.data.repository.CategoryRepository
import jp.matsuura.makehabit.androidapp.data.repository.TransactionRepository
import jp.matsuura.makehabit.androidapp.model.TransactionDetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllTransactionUseCase @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
) {

    operator fun invoke(): Flow<List<TransactionDetailModel>> {
        return combine(
            transactionRepository.getAllTransaction(),
            categoryRepository.getAllCategories(),
            ::Pair
        ).map { (transactions, categories) ->
            transactions.map { transaction ->
                val category = categories.find { category -> category.id == transaction.categoryId }
                    ?: throw IllegalStateException()
                TransactionDetailModel(
                    transactionId = transaction.id,
                    categoryId = category.id,
                    categoryName = category.categoryName,
                    startedAt = transaction.startedAt,
                    endedAt = transaction.endedAt,
                    durationMillSec = transaction.durationSec,
                    createdAt = transaction.createdAt,
                    updatedAt = transaction.updatedAt,
                )
            }
        }
    }
}