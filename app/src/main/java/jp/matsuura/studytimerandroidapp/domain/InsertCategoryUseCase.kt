package jp.matsuura.studytimerandroidapp.domain

import jp.matsuura.studytimerandroidapp.data.repository.CategoryRepository
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