package jp.matsuura.makehabit.androidapp.ui.category_selection

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jp.matsuura.makehabit.androidapp.model.CategoryModel
import java.time.Instant

class PreviewUiStateProvider: PreviewParameterProvider<CategorySelectionViewModel.UiState> {
    override val values: Sequence<CategorySelectionViewModel.UiState>
        get() = sequenceOf(
            CategorySelectionViewModel.UiState(
                isProgressVisible = false,
                categories = listOf(
                    CategoryModel(
                        id = 1,
                        categoryName = "ランニング",
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                    CategoryModel(
                        id = 2,
                        categoryName = "シャドーイング",
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                    CategoryModel(
                        id = 3,
                        categoryName = "読書",
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                    CategoryModel(
                        id = 4,
                        categoryName = "英単語",
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                ),
                selectedCategory = null,
            ),
            CategorySelectionViewModel.UiState(
                isProgressVisible = false,
                categories = listOf(
                    CategoryModel(
                        id = 1,
                        categoryName = "ランニング",
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                    CategoryModel(
                        id = 2,
                        categoryName = "シャドーイング",
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                    CategoryModel(
                        id = 3,
                        categoryName = "読書",
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                    CategoryModel(
                        id = 4,
                        categoryName = "英単語",
                        createdAt = Instant.now(),
                        updatedAt = Instant.now(),
                    ),
                ),
                selectedCategory = CategoryModel(
                    id = 3,
                    categoryName = "読書",
                    createdAt = Instant.now(),
                    updatedAt = Instant.now(),
                ),
            ),
        )
}