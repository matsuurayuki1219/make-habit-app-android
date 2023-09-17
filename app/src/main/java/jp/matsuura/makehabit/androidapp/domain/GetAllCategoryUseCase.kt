package jp.matsuura.makehabit.androidapp.domain

import jp.matsuura.makehabit.androidapp.data.repository.CategoryRepository
import jp.matsuura.makehabit.androidapp.data.toModel
import jp.matsuura.makehabit.androidapp.model.CategoryModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCategoryUseCase @Inject constructor(
    private val categoryRepository: CategoryRepository,
) {
    operator fun invoke(): Flow<List<CategoryModel>> {
        return categoryRepository.getAllCategories().map { categories ->
            categories.map { it.toModel() }
        }
    }
}