package jp.matsuura.makehabit.androidapp.domain

import jp.matsuura.makehabit.androidapp.data.repository.CategoryRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    suspend operator fun invoke(categoryName: String) {
        categoryRepository.insertCategory(
            categoryName = categoryName,
        )
    }
}