package jp.matsuura.studytimerandroidapp.domain

import jp.matsuura.studytimerandroidapp.data.repository.CategoryRepository
import jp.matsuura.studytimerandroidapp.data.toModel
import jp.matsuura.studytimerandroidapp.model.CategoryModel
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