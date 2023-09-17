package jp.matsuura.makehabit.androidapp.domain

import jp.matsuura.makehabit.androidapp.data.repository.CategoryRepository
import jp.matsuura.makehabit.androidapp.data.toModel
import jp.matsuura.makehabit.androidapp.model.CategoryModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository,
) {

    suspend operator fun invoke(categoryId: Int): CategoryModel {
        return repository.getCategory(categoryId = categoryId).toModel()
    }

}