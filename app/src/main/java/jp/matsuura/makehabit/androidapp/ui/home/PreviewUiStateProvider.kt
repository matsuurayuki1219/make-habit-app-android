package jp.matsuura.makehabit.androidapp.ui.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import jp.matsuura.makehabit.androidapp.model.TransactionDetailModel
import java.time.Instant
import java.time.LocalDate

class PreviewUiStateProvider : PreviewParameterProvider<HomeViewModel.UiState> {
    override val values: Sequence<HomeViewModel.UiState>
        get() = sequenceOf(
            HomeViewModel.UiState(
                isProgressVisible = false,
                uiItems = listOf(
                    HomeViewModel.UiItem.Header(
                        date = LocalDate.now(),
                    ),
                    HomeViewModel.UiItem.Section(
                        transaction = TransactionDetailModel(
                            transactionId = 1,
                            categoryId = 1,
                            categoryName = "ランニング",
                            startedAt = Instant.now(),
                            endedAt = Instant.now(),
                            durationMillSec = 1000,
                            createdAt = Instant.now(),
                            updatedAt = Instant.now(),
                        ),
                    ),
                    HomeViewModel.UiItem.Section(
                        transaction = TransactionDetailModel(
                            transactionId = 1,
                            categoryId = 1,
                            categoryName = "ランニング",
                            startedAt = Instant.now(),
                            endedAt = Instant.now(),
                            durationMillSec = 1000,
                            createdAt = Instant.now(),
                            updatedAt = Instant.now(),
                        ),
                    ),
                    HomeViewModel.UiItem.Header(
                        date = LocalDate.now(),
                    ),
                    HomeViewModel.UiItem.Section(
                        transaction = TransactionDetailModel(
                            transactionId = 1,
                            categoryId = 1,
                            categoryName = "ランニング",
                            startedAt = Instant.now(),
                            endedAt = Instant.now(),
                            durationMillSec = 1000,
                            createdAt = Instant.now(),
                            updatedAt = Instant.now(),
                        ),
                    ),
                    HomeViewModel.UiItem.Section(
                        transaction = TransactionDetailModel(
                            transactionId = 1,
                            categoryId = 1,
                            categoryName = "ランニング",
                            startedAt = Instant.now(),
                            endedAt = Instant.now(),
                            durationMillSec = 1000,
                            createdAt = Instant.now(),
                            updatedAt = Instant.now(),
                        ),
                    ),
                )
            )
        )
}